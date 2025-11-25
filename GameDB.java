// GameDB

package protocol;

import java.util.*;
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.IO.Protocol.Rpc.Data.DataVector;
import com.goldhuman.Common.Security.*;

public class GameDB
{
	public static final int LEVEL         =0X00000001;
	public static final int EXP           =0X00000002;
	public static final int POCKET_MONEY  =0X00000004;
	public static final int STORE_MONEY   =0X00000008;
	public static final int PKVALUE       =0X00000010;
	public static final int REPUTATION    =0X00000020;
	public static final int POTENTIAL     =0X00000040;
	public static final int OCCUPATION    =0X00000080;
	public static final int CLEARINVENTORY=0x00000100;


	public GameDB ()
	{
	}

	public static final Object locker = new Object();
	public static ClientManager mgr = ClientManager.GetInstance();
	public static boolean putrd = true; //是否关闭角色修改

	public static void init( ) throws InterruptedException
	{
		/*
		try {
			Conf conf = Conf.GetInstance("/etc/iweb.conf", null);
			String tmp = conf.find("iweb", "putrd");
			if ( tmp != null && tmp.length() > 0)
				putrd = tmp.equals("true");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		*/

//		synchronized( mgr )
//		{
//			if( null == mgr.s )
//			{
//				Protocol.Client(mgr);
//				mgr.wait();
//			}
//		}
	}

	//QueryUserid return:userid roleid level
	public static QueryUseridRes getUseridByName(String rolename) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return null;
		QueryUseridArg arg = new QueryUseridArg();
		arg.rolename.setString(rolename);

		QueryUserid rpc = (QueryUserid)Rpc.Call("QueryUserid", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getUseridByName)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getUseridByName)" );
		return rpc.queryUseridRes;
	}

	public static int getRoleIdByName(String rolename) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;
		GetRoleIdArg arg = new GetRoleIdArg();
		arg.rolename.setString(rolename);

		GetRoleId rpc = (GetRoleId)Rpc.Call("GetRoleId", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getRoleIdByName)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleIdByName)" );
		return rpc.roleid;
	}

	/* 扩充说明：
	   GetUserRoles 包含: rolelist(vector) data(vector)
	   			分别由getRoleBriefList getRolelist调用返回
				getRolelist的保留是为了兼容
	   */

	public static DataVector getRoleBriefList(int userid) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return null;
		GetUserRolesArg arg = new GetUserRolesArg();
		arg.userid = userid;

		GetUserRoles rpc = (GetUserRoles)Rpc.Call("GetUserRoles", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getRolelist)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleList)" );
		if( 0 != rpc.retcode || rpc.userid == -1 ) {
			return null;
		}
		return rpc.data;
	}

	public static DataVector getRolelist(int userid) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return null;
		GetUserRolesArg arg = new GetUserRolesArg();
		arg.userid = userid;

		GetUserRoles rpc = (GetUserRoles)Rpc.Call("GetUserRoles", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getRolelist)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleList)" );
		if( 0 != rpc.retcode || rpc.userid == -1 ) {
			return null;
		}
		return rpc.roles;
	}	

	public static int clearStorehousePasswd(int roleid, String rolename) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		ClearStorehousePasswdArg arg = new ClearStorehousePasswdArg();
		arg.roleid = roleid;
		arg.rolename.setString( rolename );

		ClearStorehousePasswd rpc = (ClearStorehousePasswd)Rpc.Call("ClearStorehousePasswd", arg);
		if( !mgr.Send(mgr.s, rpc) )	return -1;
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		return rpc.retcode;
	}

	public static int canChangeRolename(String rolename,int setcanchange) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;
		CanChangeRolenameArg arg = new CanChangeRolenameArg();
		arg.rolename.setString(rolename);
		arg.setcanchange = setcanchange;

		CanChangeRolename rpc = (CanChangeRolename)Rpc.Call("CanChangeRolename", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(canChangeRolename)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(canChangeRolename)" );
		return rpc.retcode;
	}

	public static int renameRole(int roleid, String oldname, String newname) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		RenameRoleArg arg = new RenameRoleArg();
		arg.roleid = roleid;
		arg.oldname.setString( oldname );
		arg.newname.setString( newname );

		RenameRole rpc = (RenameRole)Rpc.Call("RenameRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	return -1;
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		return rpc.retcode;
	}

	public static int Uid2Logicuid( int userid ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		Uid2LogicuidArg arg = new Uid2LogicuidArg();
		arg.userid = userid;

		Uid2Logicuid rpc = (Uid2Logicuid)Rpc.Call("Uid2Logicuid", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(Uid2Logicuid)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(Uid2Logicuid)" );
		if( 0 == rpc.retcode )
			return rpc.logicuid;
		return -1;
	}

	public static int Roleid2Uid( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		Roleid2UidArg arg = new Roleid2UidArg();
		arg.roleid = roleid;

		Roleid2Uid rpc = (Roleid2Uid)Rpc.Call("Roleid2Uid", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(Roleid2Uid)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(Roleid2Uid)" );
		if( 0 == rpc.retcode )
			return rpc.userid;
		return -1;
	}

	public static int DBVerifyMaster(String rolename, String factionname) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;
		DBVerifyMasterArg arg = new DBVerifyMasterArg();
		arg.name.setString(rolename);
		arg.faction.setString(factionname);

		DBVerifyMaster rpc = (DBVerifyMaster)Rpc.Call("DBVerifyMaster", arg);
		if( !mgr.Send(mgr.s, rpc) )	return -1;
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		return rpc.retcode;
	}

	public static int createRole( int userid, int logicuid, int roleid, XmlRole.Role role ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		DBCreateRoleArg arg = new DBCreateRoleArg();
		arg.userid = userid;
		arg.logicuid = logicuid;

		arg.roleid = roleid;
		arg.roleinfo.roleid = roleid;
		//TODO arg.roleinfo.race = (byte)role.base.race;
		arg.roleinfo.gender = role.base.gender;
		arg.roleinfo.occupation = (byte)0;
		arg.roleinfo.level = role.status.level;
		arg.roleinfo.name = role.base.name;

		DBCreateRole rpc = (DBCreateRole)Rpc.Call("DBCreateRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(createRole)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(createRole)" );
		if( 0 == rpc.retcode )
		{
			return rpc.roleid;
		}
		return rpc.retcode > 0 ? -rpc.retcode : rpc.retcode;
	}

	public static boolean deleteRolePermanent( int roleid ,OctetsData namelist) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		DBDeleteRoleArg arg = new DBDeleteRoleArg();
		arg.roleid = roleid;

		DBDeleteRole rpc = (DBDeleteRole)Rpc.Call("DBDeleteRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(deleteRolePermanent)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(deleteRolePermanent)" );
		namelist=rpc.namelist;
		return (0 == rpc.retcode);
	}

	public static boolean moveRole( int roleid, int newuserid )
			throws InterruptedException, Exception
	{
		return false;
		/*
		XmlRole.Role role = XmlRole.getRoleFromDB( roleid );

		if( null == role )
		{
			throw new Exception( "GameDB moveRole. get roleid failed. roleid=" + roleid );
		}
		if( createRole( newuserid, role ) < 0 )
		{
			throw new Exception( "GameDB moveRole. create new role failed. newuserid=" + newuserid );
		}
		if( !XmlRole.putRoleToDB( role.base.id, role ) )
		{
			if( !deleteRolePermanent( role.base.id ) )
				throw new Exception( "GameDB moveRole, put new role error and not left not delete. newroleid="
									+ role.base.id + "oldroleid=" + roleid );
			throw new Exception( "GameDB moveRole. put new role failed. newroleid=" + role.base.id );
		}
		if( !deleteRolePermanent( roleid ) )
		{
			if( !deleteRolePermanent( role.base.id ) )
				throw new Exception( "GameDB moveRole, delete old role err and new role not delete. newroleid="
									+ role.base.id + "oldroleid=" + roleid );
			throw new Exception( "GameDB moveRole, delete old role failed. old roleid=" + roleid );
		}
		return true;
		*/
	}

	public static boolean putUser( int userid, User user ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == user )	return false;

		UserPair arg = new UserPair();
		arg.key.id = userid;
		arg.value = user;

		PutUser rpc = (PutUser)Rpc.Call("PutUser", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putUser)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putUser)" );
		return (0 == rpc.retcode);
	}

	/*
	public static boolean putRoleBase( int roleid, GRoleBase base )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == base )	return false;

		synchronized( locker )
		{
			RoleBasePair arg = new RoleBasePair();
			arg.key.id = roleid;
			arg.value = base;

			PutRoleBase rpc = (PutRoleBase)Rpc.Call("PutRoleBase", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleBase)" );
			PollIO.WakeUp();
			locker.wait();
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRoleBase)" );
			return (0 == rpc.retcode);
		}
	}

	public static boolean putRoleStatus( int roleid, GRoleStatus status )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == status )	return false;

		synchronized( locker )
		{
			RoleStatusPair arg = new RoleStatusPair();
			arg.key.id = roleid;
			arg.value = status;

			PutRoleStatus rpc = (PutRoleStatus)Rpc.Call("PutRoleStatus", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleStatus)" );
			PollIO.WakeUp();
			locker.wait();
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRoleStatus)" );
			return (0 == rpc.retcode);
		}
	}

	public static boolean putRolePocket( int roleid, GRolePocket pocket )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == pocket )	return false;

		synchronized( locker )
		{
			RolePocketPair arg = new RolePocketPair();
			arg.key.id = roleid;
			arg.value = pocket;

			PutRolePocket rpc = (PutRolePocket)Rpc.Call("PutRolePocket", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRolePocket)" );
			PollIO.WakeUp();
			locker.wait();
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRolePocket)" );
			return (0 == rpc.retcode);
		}
	}

	public static boolean putRoleStorehouse( int roleid, GRoleStorehouse storehouse )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == storehouse )	return false;

		synchronized( locker )
		{
			RoleStorehousePair arg = new RoleStorehousePair();
			arg.key.id = roleid;
			arg.value = storehouse;

			PutRoleStorehouse rpc = (PutRoleStorehouse)Rpc.Call("PutRoleStorehouse", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleStorehouse)" );
			PollIO.WakeUp();
			locker.wait();
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRoleStorehouse)" );
			return (0 == rpc.retcode);
		}
	}

	public static boolean putRoleTask( int roleid, GRoleTask task )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == task )	return false;

		synchronized( locker )
		{
			RoleTaskPair arg = new RoleTaskPair();
			arg.key.id = roleid;
			arg.value = task;

			PutRoleTask rpc = (PutRoleTask)Rpc.Call("PutRoleTask", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleTask)" );
			PollIO.WakeUp();
			locker.wait();
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRoleTask)" );
			return (0 == rpc.retcode);
		}
	}

	*/
	public static User getUser(int userid) throws Exception
	{
		init( );
		if(  null == mgr.s )
			return null;
		UserArg arg = new UserArg();
		arg.id = userid;
		arg.loginip = 0;

		GetUser rpc = (GetUser)Rpc.Call("GetUser", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getUser)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getUser)" );
		if( 0 != rpc.retcode )
			return null;
		return rpc.user;
	}	

	public static GRoleBase getRoleBase( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleBase rpc = (GetRoleBase)Rpc.Call("GetRoleBase", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleBase)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleBase)" );
		return rpc.base;
	}

	public static GRoleStatus getRoleStatus( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if( null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleStatus rpc = (GetRoleStatus)Rpc.Call("GetRoleStatus", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getRoleStatus)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleStatus)" );
		return rpc.status;
	}

	public static GRolePocket getRolePocket( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if( null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRolePocket rpc = (GetRolePocket)Rpc.Call("GetRolePocket", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRolePocket)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRolePocket)" );
		return rpc.pocket;
	}

	public static GRoleStorehouse getRoleStorehouse( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if( null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleStorehouse rpc = (GetRoleStorehouse)Rpc.Call("GetRoleStorehouse", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleStorehouse)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleStorehouse)" );
		return rpc.storehouse;
	}

	public static GRoleTask getRoleTask( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if( null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleTask rpc = (GetRoleTask)Rpc.Call("GetRoleTask", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleTask)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleTask)" );
		return rpc.task;
	}

	public static GRoleData getRoleData( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if( null == mgr.s ) return null;
		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleData rpc = (GetRoleData)Rpc.Call("GetRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleData)" );
		return rpc.value;
	}

	public static boolean putRoleData( int roleid, GRoleData data ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return false;
		if( null == data )	return false;
		RoleDataPair arg = new RoleDataPair();
		arg.key.id = roleid;
		arg.overwrite = (byte)1;
		arg.value = data;

		PutRoleData rpc = (PutRoleData)Rpc.Call("PutRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(putRoleData)" );
		return (0 == rpc.retcode);
	}

	public static RoleBean get( int roleid )
				throws InterruptedException, Exception
	{
		RoleBean bean = new RoleBean();
		bean.user = getUser( GameDB.Roleid2Uid(roleid) ); 
		if (null == bean.user) return null;

		GRoleData roledata = getRoleData(roleid);
		bean.base = roledata.base;
		bean.status = roledata.status;
		bean.pocket = roledata.pocket;
		bean.storehouse = roledata.storehouse;
		bean.task = roledata.task;

		/*
		bean.base = getRoleBase( roleid );
		bean.status = getRoleStatus( roleid );
		bean.pocket = getRolePocket( roleid );
		bean.storehouse = getRoleStorehouse( roleid );
		bean.task = getRoleTask( roleid );
		*/

		if( null == bean.pocket )		bean.pocket = new GRolePocket();
		if( null == bean.storehouse )	bean.storehouse = new GRoleStorehouse();
		if( null == bean.task )			bean.task = new GRoleTask();
		if( null == bean.base || null == bean.status )
			return null;
		return bean;
	}

	public static boolean update( RoleBean bean )
				throws InterruptedException, Exception
	{
		GRoleData roledata = new GRoleData();
		roledata.base = bean.base;
		roledata.status = bean.status;
		roledata.pocket = bean.pocket;
		roledata.storehouse = bean.storehouse;
		roledata.task = bean.task;
		return putRoleData(bean.base.id, roledata);
		/*
		//if( !putUser( GameDB.Roleid2Uid(bean.base.id), bean.user ) )
		//	return false;

		if( !putRoleStatus( bean.base.id, bean.status ) )
			return false;
		if( !putRolePocket( bean.base.id, bean.pocket ) )
			return false;
		if( !putRoleStorehouse( bean.base.id, bean.storehouse ) )
			return false;
		if( !putRoleTask( bean.base.id, bean.task ) )
			return false;
		return true;
		*/
	}

	public static int modifyRoleLevel(int roleid,int level) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == level )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=LEVEL;
		arg.level = level;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRoleExp(int roleid,long exp) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == exp )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=EXP;
		arg.exp = exp;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRolePocketMoney(int roleid,int pocketmoney) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == pocketmoney )return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=POCKET_MONEY;;
		arg.pocket_money = pocketmoney;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRoleStoreMoney(int roleid,int storemoney) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == storemoney )return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=STORE_MONEY;;
		arg.store_money = storemoney;;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRolePkvalue(int roleid,int pkvalue) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == pkvalue )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=PKVALUE;
		arg.pkvalue = pkvalue;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRoleReputation(int roleid,int reputation) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == reputation )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=REPUTATION;
		arg.reputation = reputation;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRolePotential(int roleid,int potential) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == potential )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=POTENTIAL;
		arg.potential = potential;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static int modifyRoleOccupation(int roleid,int occupation) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		if( 0 == occupation )	return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |=OCCUPATION;
		arg.occupation = occupation;


		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}


	public static int modifyRoleAllMoney(int roleid, int pocket_money, int store_money) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s ) return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |= POCKET_MONEY;
		arg.mask |= STORE_MONEY;
		arg.pocket_money = pocket_money;
		arg.store_money = store_money;
		

		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		return  rpc.retcode;
	}

	public static long modifyRoleClearAllMoney(int roleid) throws InterruptedException, Exception
	{
		/*
		init( );
		if(  null == mgr.s ) return -1;
		DBModifyRoleDataArg arg = new DBModifyRoleDataArg();
		arg.roleid = roleid;
		arg.mask |= CLEARINVENTORY;
		

		DBModifyRoleData rpc = (DBModifyRoleData)Rpc.Call("DBModifyRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBModifyRoleData)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBModifyRoleData)" );
		System.out.println("modifyRoleClearAllMoney roleid=" + roleid + " retcode=" + rpc.retcode);
		return  0 == rpc.retcode ? rpc.datares.total_money : -1L;
		*/
		return -1L;
	}
	public static DBRestoreRoleDataRes dbRestoreRoleData(int roleid, boolean forceDelete) throws InterruptedException, Exception
	{
		init(); 
		if (null == mgr.s) return null;
		DBRestoreRoleDataArg arg = new DBRestoreRoleDataArg();
		arg.roleid = roleid;
		if (forceDelete) arg.force = 1;
		else arg.force = 0;
		DBRestoreRoleData rpc = (DBRestoreRoleData)Rpc.Call("DBRestoreRoleData", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(DBRestoreRoleDataRes)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(DBRestoreRoleDataRes)" );
		return rpc.restoreRoleDataRes;
	}

	/* 诛仙鸿利数据*/
	public static GRoleBase2 getGRoleBase2( int roleid ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return null;

		RoleId arg = new RoleId();
		arg.id = roleid;

		GetRoleBase2 rpc = (GetRoleBase2)Rpc.Call("GetRoleBase2", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(getRoleBase2)" );
		PollIO.WakeUp();
		synchronized(rpc) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(getRoleBase2)" );
		return rpc.base2;
	}

	public static boolean putGRoleBase2( int roleid, GRoleBase2 base2 )
				throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		if( null == base2 )	return false;

		synchronized( locker )
		{
			RoleBase2Pair arg = new RoleBase2Pair();
			arg.key.id = roleid;
			arg.value = base2;

			PutRoleBase2 rpc = (PutRoleBase2)Rpc.Call("PutRoleBase2", arg);
			if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(putRoleBase2)" );
			PollIO.WakeUp();
			synchronized(rpc) { rpc.wait(); }
			if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。2(putRoleBase)" );
			return (0 == rpc.retcode);
		}
	}

}

