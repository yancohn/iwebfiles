
package protocol;

 
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.Common.Security.*;


public class UniquenameDB
{
	static public class ID
	{
		public int roleid;
		public int logicuid;
		public ID(int _roleid,int _logicuid)
		{
			roleid		= _roleid;
			logicuid	= _logicuid;
		}
	}

	public UniquenameDB ()
	{
	}

	public static final Object locker = new Object();
	public static UniquenameClientManager mgr = new UniquenameClientManager();

	public static void init( ) throws Exception
	{
		synchronized( mgr )
		{
			if( null == mgr.s )
			{
				Protocol.Client(mgr);
				mgr.wait();
			}
		}
	}

	public static ID PreCreateRole( int zoneid, int userid, String rolename ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return new ID(-1,-1);

		PreCreateRoleArg arg = new PreCreateRoleArg();
		arg.zoneid = zoneid;
		arg.userid = userid;
		arg.rolename.setString( rolename );

		PreCreateRole rpc = (PreCreateRole)Rpc.Call("PreCreateRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(PreCreateRole)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(PreCreateRole)" );
		if( 0 == rpc.retcode )
			return new ID( rpc.roleid, rpc.logicuid );
		return new ID( rpc.retcode > 0 ? -rpc.retcode : rpc.retcode, -1 );
	}

	public static boolean PostCreateRole( byte success, int userid, int zoneid, int roleid, String rolename ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return false;

		PostCreateRoleArg arg = new PostCreateRoleArg();
		arg.success = success;
		arg.userid = userid;
		arg.zoneid = zoneid;
		arg.roleid = roleid;
		arg.rolename.setString( rolename );

		PostCreateRole rpc = (PostCreateRole)Rpc.Call("PostCreateRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(PostCreateRole)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(PostCreateRole)" );
		return (0 == rpc.retcode);
	}

	public static boolean PostDeleteRole( int userid, int zoneid, int roleid, OctetsData namelist ) throws InterruptedException, Exception
	{
		
		init( );
		if(  null == mgr.s )
			return false;

		PostDeleteRoleArg arg = new PostDeleteRoleArg();
		arg.userid = userid;
		arg.zoneid = zoneid;
		arg.roleid = roleid;
		arg.namelist=namelist;

		PostDeleteRole rpc = (PostDeleteRole)Rpc.Call("PostDeleteRole", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(PostDeleteRole)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(PostDeleteRole)" );
		return (0 == rpc.retcode);
		
	}

	public static int RolenameExists( String rolename ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		RolenameExistsArg arg = new RolenameExistsArg();
		arg.rolename.setString( rolename );

		RolenameExists rpc = (RolenameExists)Rpc.Call("RolenameExists", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(RolenameExists)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(RolenameExists)" );
		if( 0 == rpc.retcode )	return 0;
		if( 60 == rpc.retcode )	return 1;
		return -1;
	}

	public static int UserRoleCount( int userid ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return -1;

		UserRoleCountArg arg = new UserRoleCountArg();
		arg.userid = userid;

		UserRoleCount rpc = (UserRoleCount)Rpc.Call("UserRoleCount", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(UserRoleCount)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(UserRoleCount)" );
		if( 0 == rpc.retcode )
			return rpc.count;
		return -1;
	}

	public static ID MoveRoleCreate( int fromzoneid, int zoneid, int userid, String rolename ) throws InterruptedException, Exception
	{
		init( );
		if(  null == mgr.s )
			return new ID(-1,-1);

		MoveRoleCreateArg arg = new MoveRoleCreateArg();
		arg.fromzoneid = fromzoneid;
		arg.zoneid = zoneid;
		arg.userid = userid;
		arg.rolename.setString( rolename );

		MoveRoleCreate rpc = (MoveRoleCreate)Rpc.Call("MoveRoleCreate", arg);
		if( !mgr.Send(mgr.s, rpc) )	throw new Exception( "网络连接未建立。(MoveRoleCreate)" );
		PollIO.WakeUp();
		synchronized( rpc ) { rpc.wait(); }
		if( 4 == rpc.retcode )	throw new Exception( "网络通讯超时。(MoveRoleCreate)" );
		if( 0 == rpc.retcode )
		{
			int logicuid = rpc.roleid & ~0x0000000F; 
			return new ID(rpc.roleid,logicuid);
		}
		return new ID( rpc.retcode > 0 ? -rpc.retcode : rpc.retcode, -1 );
	}


}

