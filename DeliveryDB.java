
package protocol;

import java.util.*; 
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.Common.Security.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DeliveryDB
{
		private DeliveryDB ()
		{
		}
		static private DeliveryDB instance = new DeliveryDB();
		static private final Log log = LogFactory.getLog(DeliveryDB.class);

		static {
			new java.util.Timer().schedule(new java.util.TimerTask() {
					public void run()
					{
						try { init(); } catch(Exception ex) { ex.printStackTrace(); }
					}
				},1000*30,1000*60*2);

			new java.util.Timer().schedule(new java.util.TimerTask() {
					public void run()
					{
						try
						{
							synchronized( map )
							{
								for ( Iterator it = map.values().iterator(); it.hasNext(); )
								{
									ProtocolWrapper pw = ((ProtocolWrapper) it.next());
									pw.ttl -= 1;
									if( pw.ttl <= 0 )
									{
										it.remove();
										synchronized( pw )	{ pw.notify();	}
									}
								}
							}
						} catch(Exception ex) { ex.printStackTrace(); }
					}
				},1000*5,1000);
		}

		public static final Object locker = new Object();
		public static DeliveryClientManager mgr = DeliveryClientManager.GetInstance();

		public static class ProtocolWrapper
		{
			public int	ttl;
			public Protocol	protocol;
			public int	retcode;
			public Object	cookie;
			public ProtocolWrapper(int ttl,Protocol protocol)
			{
				this.ttl = ttl;
				this.protocol = protocol;
				retcode = -1;
			}
		};
		private static java.util.concurrent.atomic.AtomicInteger tid_seed = new java.util.concurrent.atomic.AtomicInteger(0);
	private static Integer getTid_seed() {
		Integer tid =  tid_seed.incrementAndGet();
		if (tid >= 0x40000000) tid_seed.set(0);
		return tid;
	}
		private static Map	map = new TreeMap();

		public static void init( ) throws Exception
		{
//			synchronized( mgr )
//			{
//				if( null == mgr.s )
//				{
//					System.out.println("mgr.s is null , Protocol.Client(mgr)");
//					Protocol.Client(mgr);
//					mgr.wait();
//				}
//			}
		}

		public static boolean SysSendMail(int receiver, String title, String context,
							GRoleInventory attach_obj, int attach_money ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			SysSendMail pc = (SysSendMail)Protocol.Create("SysSendMail");
			pc.tid = getTid_seed();
			pc.sysid = 32;
			pc.sys_type = 3;
			pc.receiver = receiver;
			pc.title.setString( title );
			pc.context.setString( context );
			pc.attach_obj = attach_obj;
			pc.attach_money = attach_money;

			ProtocolWrapper pw = new ProtocolWrapper(35,pc);
			synchronized( map )
			{
				map.put( new Integer(pc.tid), pw );
			}

			synchronized( pw )
			{
				if( !mgr.Send(mgr.s,pc) )
					return false;
				PollIO.WakeUp();
				pw.wait();
				return (0 == pw.retcode);
			}
		}
		public static void SysSendMail_Re(short retcode, int tid)
		{
			ProtocolWrapper pw = null;
			synchronized( map )
			{
				pw = (ProtocolWrapper)map.get( new Integer(tid) );
				map.remove( new Integer(tid) );
			}

			synchronized( pw )
			{
				pw.retcode = retcode;
				pw.notify();
			}
		}
		public static GMControlGame_Re GMControlGame(int worldtag, String command ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return null;

			GMControlGame pc = (GMControlGame)Protocol.Create("GMControlGame");
			pc.xid = getTid_seed();
			pc.worldtag = worldtag;
			pc.command.replace( command.getBytes("GBK") );

			ProtocolWrapper pw = new ProtocolWrapper(36,pc);
			synchronized( map )
			{
				map.put( new Integer(pc.xid), pw );
			}

			synchronized( pw )
			{
				if( !mgr.Send(mgr.s,pc) )
					return null;
				PollIO.WakeUp();
				pw.wait();
				return (GMControlGame_Re)pw.cookie;
			}
		}
		public static void GMControlGame_Re(GMControlGame_Re re)
		{
			ProtocolWrapper pw = null;
			synchronized( map )
			{
				pw = (ProtocolWrapper)map.get( new Integer(re.xid) );
				map.remove( new Integer(re.xid) );
			}

			synchronized( pw )
			{
				pw.retcode = re.retcode;
				pw.cookie = re;
				pw.notify();
			}
		}
		public static int GMForbidRole(byte fbd_type, int gmroleid, int localsid,
							int dstroleid, int forbid_time, String reason ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return -1;

			GMForbidRole pc = (GMForbidRole)Protocol.Create("GMForbidRole");
			pc.fbd_type = fbd_type;
			pc.gmroleid = gmroleid;
			pc.localsid = localsid;
			pc.dstroleid = dstroleid;
			pc.forbid_time = forbid_time;
			pc.reason.setString( reason );

			//ProtocolWrapper pw = new ProtocolWrapper(35,pc);
			//synchronized( map )
			//{
			//	map.put( new Integer(-dstroleid), pw );
			//}

			//synchronized( pw )
			{
				if( !mgr.Send(mgr.s,pc) )
					return -1;
				PollIO.WakeUp();
				return 0;
				//pw.wait();
				//return pw.retcode;
			}
		}
		public static void GMForbidRole_Re(int retcode, byte fbd_type, int dstroleid, int forbid_time)
		{
			ProtocolWrapper pw = null;
			synchronized( map )
			{
				pw = (ProtocolWrapper)map.get( new Integer(-dstroleid) );
				map.remove( new Integer(-dstroleid) );
			}

			synchronized( pw )
			{
				pw.retcode = retcode;
				pw.notify();
			}
		}
		public static boolean GMRestartServer( int gmroleid, int restart_time ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			GMRestartServer restart = (GMRestartServer)Protocol.Create("GMRestartServer");
			restart.gmroleid = gmroleid;
			restart.restart_time = restart_time;
			boolean r = mgr.Send(mgr.s, restart);
			PollIO.WakeUp();
			return r;
		}
		public static boolean GMShutdownLine( int gmroleid, int localsid, int gs_id ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			GMShutdownLine shutdown = (GMShutdownLine)Protocol.Create("GMShutdownLine");
			shutdown.gmroleid = gmroleid;
			shutdown.localsid = localsid;
			shutdown.gs_id = gs_id;
			boolean r = mgr.Send(mgr.s, shutdown);
			PollIO.WakeUp();
			return r;
		}
		public static boolean GMPrivilegeChange( int userid ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			GMPrivilegeChange gmpc = (GMPrivilegeChange)Protocol.Create("GMPrivilegeChange");
			gmpc.userid = userid;
			boolean r = mgr.Send(mgr.s, gmpc);
			PollIO.WakeUp();
			return r;
		}
		public static int getRoleLogStatus(int roleid) throws Exception
		{
			init();
			if( null == mgr.s )
				return -1;
			RoleId arg = new RoleId();
			arg.id = roleid;

			GMQueryRoleInfo rpc = (GMQueryRoleInfo)Rpc.Call("GMQueryRoleInfo", arg);
			if( !mgr.Send(mgr.s, rpc) )
				return -1;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			return rpc.status;
		}
		public static boolean broadcast(byte type, int roleid ,String msg) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			PublicChat pc = (PublicChat)Protocol.Create("PublicChat");
			pc.channel = type;
			pc.emotion = (byte)0;
			pc.roleid = roleid;
			pc.msg.setString(msg);
			boolean r = mgr.Send(mgr.s,pc);
			PollIO.WakeUp();
			return r;
		}
		public static boolean replyComplain(int roleid, int gmroleid, String gmrolename, String msg)
			throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			PrivateChat pc = (PrivateChat)Protocol.Create("PrivateChat");
			pc.msg.setString(msg);
			pc.channel = 5;
			pc.emotion = (byte)0;
			pc.srcroleid = gmroleid;
			pc.src_name.setString(gmrolename);
			pc.dstroleid = roleid;
			pc.referrer = 0;
			boolean r = mgr.Send(mgr.s,pc);
			PollIO.WakeUp();
			return r;
		}
		public static boolean SetMaxOnlineNum( int maxnum, int fakemaxnum ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;
			SetMaxOnlineNum proto = (SetMaxOnlineNum)Protocol.Create("SetMaxOnlineNum");
			proto.maxnum = maxnum;
			proto.fake_maxnum = fakemaxnum;
			boolean r = mgr.Send(mgr.s, proto);
			PollIO.WakeUp();
			return r;
		}
		public static boolean GetMaxOnlineNum( Integer[] curmax ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			GetMaxOnlineNumArg arg = new GetMaxOnlineNumArg();
			arg.padding = 0;
			GetMaxOnlineNum rpc = (GetMaxOnlineNum)Rpc.Call("GetMaxOnlineNum", arg);
			if( !mgr.Send(mgr.s, rpc) )
				return false;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			if( 0 == rpc.retcode )
			{
				curmax[0] = new Integer(rpc.maxnum);
				curmax[1] = new Integer(rpc.fakemaxnum);
				curmax[2] = new Integer(rpc.curnum);
			}
			return (0 == rpc.retcode);
		}
		public static boolean SetLinePlayerLimit( LinePlayerNumberLimits limits ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			SetLinePlayerLimit rpc = (SetLinePlayerLimit)Rpc.Call("SetLinePlayerLimit", limits);
			if( !mgr.Send(mgr.s, rpc) )
				return false;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			return (0 == rpc.retcode);
		}
		public static boolean GetLinePlayerLimit( LinePlayerNumberLimits limits ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			GetLinePlayerLimitArg arg = new GetLinePlayerLimitArg();
			GetLinePlayerLimit rpc = (GetLinePlayerLimit)Rpc.Call("GetLinePlayerLimit", arg);
			if( !mgr.Send(mgr.s, rpc) )
				return false;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			if( 0 == rpc.retcode )
				limits.limits = rpc.limits.limits;
			return (0 == rpc.retcode);
		}
		public static boolean GMGetGameAttri( int gmroleid, byte attribute, Octets[] values ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			GMGetGameAttriArg arg = new GMGetGameAttriArg();
			arg.gmroleid = gmroleid;
			arg.localsid = -1;
			arg.attribute = attribute;
			GMGetGameAttri rpc = (GMGetGameAttri)Rpc.Call("GMGetGameAttri", arg);
			if( !mgr.Send(mgr.s, rpc) )
				return false;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			values[0] = rpc.value;
			return null != rpc.value && rpc.value.size() > 0;
		}
		public static boolean GMSetGameAttri( int gmroleid, byte attribute, Octets value ) throws Exception
		{
			init( );
			if( null == mgr.s )
				return false;

			GMSetGameAttriArg arg = new GMSetGameAttriArg();
			arg.gmroleid = gmroleid;
			arg.localsid = -1;
			arg.attribute = attribute;
			arg.value = value;
			GMSetGameAttri rpc = (GMSetGameAttri)Rpc.Call("GMSetGameAttri", arg);
			if( !mgr.Send(mgr.s, rpc) )
				return false;
			PollIO.WakeUp();
			synchronized( rpc ) { rpc.wait(); }
			return (0 == rpc.retcode);
		}
		public static byte GMGetGameAttriByte( byte type ) throws Exception
		{
			Octets[] values = new Octets[1];
			if( GMGetGameAttri( -1, type, values ) && null != values[0] && values[0].size() >= 1 )
			{
				OctetsStream os = new OctetsStream(values[0]);
				byte status = os.unmarshal_byte();
				return  status;
			}
			return -1;
		}
		public static boolean GMSetGameAttriByte( byte type, byte value ) throws Exception
		{
			OctetsStream os = new OctetsStream();
			os.marshal( value );
			return GMSetGameAttri( -1, type, os );
		}
		/* 双倍经验作废
		public static byte GMGetDoubleExp( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)204 );
		}
		public static boolean GMSetDoubleExp( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)204, b ? (byte)1 : (byte)0 );
		}
		*/
		public static byte GMGetMultiExp( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)204 );
		}
		public static boolean GMSetMultiExp( int b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)204, (byte)b);
		}
		public static int GMGetLambda( ) throws Exception
		{
			Octets[] values = new Octets[1];
			if( GMGetGameAttri( -1, (byte)205, values ) && null != values[0] && values[0].size() >= 1 )
			{
				OctetsStream os = new OctetsStream(values[0]);
				byte temp = os.unmarshal_byte();
				int lambda = (0xFF & (int)temp);
				return  lambda;
			}
			return -1;
		}
		public static boolean GMSetLambda( int lambda ) throws Exception
		{
			byte temp = (byte)lambda;
			return GMSetGameAttriByte( (byte)205, temp );
		}
		public static byte GMGetNoTrade( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)207 );
		}
		public static boolean GMSetNoTrade( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)207, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetNoAuction( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)208 );
		}
		public static boolean GMSetNoAuction( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)208, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetNoMail( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)209 );
		}
		public static boolean GMSetNoMail( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)209, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetNoFaction( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)210 );
		}
		public static boolean GMSetNoFaction( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)210, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetDoubleMoney( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)211 );
		}
		public static boolean GMSetDoubleMoney( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)211, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetDoubleObject( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)212 );
		}
		public static boolean GMSetDoubleObject( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)212, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetDoubleSP( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)213 );
		}
		public static boolean GMSetDoubleSP( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)213, b ? (byte)1 : (byte)0 );
		}
		public static byte GMGetNoSellPoint( ) throws Exception
		{
			return GMGetGameAttriByte( (byte)214 );
		}
		public static boolean GMSetNoSellPoint( boolean b ) throws Exception
		{
			return GMSetGameAttriByte( (byte)214, b ? (byte)1 : (byte)0 );
		}

		public static GRoleForbid DlyForbidUser(int operation, int userid, int time, String reason) throws Exception
		{
			init();
			if (null == mgr.s)
				return null;
			ForbidUserArg arg = new ForbidUserArg();
			arg.operation = (byte)operation;
			arg.userid = userid;
			arg.time = time;
			arg.reason.setString(reason);

			ForbidUser rpc = (ForbidUser)Rpc.Call("ForbidUser", arg);
			if( !mgr.Send(mgr.s,rpc) ) throw new Exception("网络链接未建立.(ForbidUser");
			PollIO.WakeUp();
			synchronized(rpc) { rpc.wait(); }
			log.info("DlyForbiduser(), userid=" + userid + ",time="+time+",operation=" + operation + "(0 query, 1 forbid, 2 unforbid) return=" + rpc.retcode);
			if (4 == rpc.retcode) throw new Exception("网络通讯超时.(ForbidUser)");
			return 0 == rpc.retcode ? rpc.groleforbid : null;

		}
		public static boolean setSaleScheme(int scheme) throws Exception {
			init( );
			if( null == mgr.s )
				return false;
			GShopSetSaleScheme pc = (GShopSetSaleScheme)Protocol.Create("GShopSetSaleScheme");
			pc.scheme = scheme;
			boolean r = mgr.Send(mgr.s,pc);
			PollIO.WakeUp();
			return r;
		}
		public static boolean setDiscountScheme(int scheme) throws Exception {
			init( );
			if( null == mgr.s )
				return false;
			GShopSetDiscountScheme pc = (GShopSetDiscountScheme)Protocol.Create("GShopSetDiscountScheme");
			pc.scheme = scheme;
			boolean r = mgr.Send(mgr.s,pc);
			PollIO.WakeUp();
			return r;
		}
		public static GShopScheme getShopScheme() throws Exception {
			init();
			if (null == mgr.s)
				return null;
			IntData arg = new IntData();
			arg.int_value = 0; //dummy

			GShopGetScheme rpc = (GShopGetScheme)Rpc.Call("GShopGetScheme", arg);
			if( !mgr.Send(mgr.s,rpc) ) throw new Exception("网络链接未建立.(GShopGetScheme");
			PollIO.WakeUp();
			synchronized(rpc) { rpc.wait(); }
			log.info("GShopGetScheme(), return=" + rpc.retcode);
			if (4 == rpc.retcode) throw new Exception("网络通讯超时.(GShopGetScheme)");
			return 0 == rpc.retcode ? rpc.gshopscheme : null;
		}
		public static boolean DisableAutolock(int userid) throws Exception
		{
			init(); 
			if (null == mgr.s)  return false;
			DisableAutolock pc = (DisableAutolock)Protocol.Create("DisableAutolock");
			pc.userid = userid;
			if (!mgr.Send(mgr.s, pc)) { return false; }
			PollIO.WakeUp();
			return true;
		}
		public static Integer getCommonDataQuery(Integer key) throws Exception {
			init();
			if (null == mgr.s)
				return null;
			CommonDataQueryArg arg = new CommonDataQueryArg();
			arg.key = key;

			CommonDataQuery rpc = (CommonDataQuery)Rpc.Call("CommonDataQuery", arg);
			if( !mgr.Send(mgr.s,rpc) ) throw new Exception("网络链接未建立.(CommonDataQueryRes");
			PollIO.WakeUp();
			synchronized(rpc) { rpc.wait(); }
			log.info("CommonDataQuery(), return=" + rpc.retcode);
			if (4 == rpc.retcode) throw new Exception("网络通讯超时.(CommonDataQuery)");
			return rpc.value;
		}

		public static SysRecoveredObjMail_Re SysRecoveredObjMail(int receiver, String title, String context, String obj, String checksum) throws Exception
		{
			init( );
			if( null == mgr.s )
				return null;

			SysRecoveredObjMail pc = (SysRecoveredObjMail)Protocol.Create("SysRecoveredObjMail");
			pc.tid = getTid_seed();

			pc.sys_type = 3;
			pc.receiver = receiver;
			pc.title.setString(title);
			pc.context.setString(context);
			pc.obj.insert(0, obj.getBytes("iso-8859-1"));
			pc.checksum.insert(0, checksum.getBytes("iso-8859-1"));

			ProtocolWrapper pw = new ProtocolWrapper(37,pc);
			synchronized( map )
			{
				map.put( new Integer(pc.tid), pw );
			}

			synchronized( pw )
			{
				if( !mgr.Send(mgr.s,pc) )
					return null;
				PollIO.WakeUp();
				pw.wait();
				return (SysRecoveredObjMail_Re)pw.cookie;
			}
		}
		public static void SysRecoveredObjMail_Re(SysRecoveredObjMail_Re re)
		{
			ProtocolWrapper pw = null;
			synchronized( map )
			{
				pw = (ProtocolWrapper)map.get( new Integer(re.tid) );
				map.remove( new Integer(re.tid) );
			}

			synchronized( pw )
			{
				pw.retcode = re.retcode;
				pw.cookie = re;
				pw.notify();
			}
		}
		public static String operationCmd(int type,String code) throws Exception {
			init();
			if (null == mgr.s){
				log.info("DeliveryDB::operationCmd("+type+","+code+"),session null");
				return null;
			}

			OperationCmdArg arg = new OperationCmdArg();
			arg.operationtype = type;
			try{
				arg.code.replace(code.getBytes("UTF-8"));
			}catch(Exception e){
				e.printStackTrace();
			}

			OperationCmd rpc = (OperationCmd)Rpc.Call("OperationCmd", arg);
			if( !mgr.Send(mgr.s,rpc) ) throw new Exception("网络链接未建立.(OperationCmd");
			PollIO.WakeUp();
			synchronized(rpc) { rpc.wait(); }
			log.info("OperationCmd(), return=" + rpc.retcode);
			if (4 == rpc.retcode) throw new Exception("网络通讯超时.(OperationCmd)");
			return "retcode="+rpc.retcode+";errmsg="+rpc.errmsg;
		}


}
