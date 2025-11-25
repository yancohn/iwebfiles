package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

import com.goldhuman.service.GMServiceImpl;
import com.goldhuman.service.interfaces.ChatInfo;
import com.goldhuman.service.interfaces.GMService;
import com.goldhuman.service.interfaces.LogInfo;
import com.goldhuman.util.LocalDB;

public final class ForwardChat extends Protocol
{
	public int	zoneid;
	public int	lineid;
	public int	userid;
	public int	roleid;
	public Octets	name;
	public Octets	msg;

	public ForwardChat()
	{
		name = new Octets();
		msg = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(zoneid);
		os.marshal(lineid);
		os.marshal(userid);
		os.marshal(roleid);
		os.marshal(name);
		os.marshal(msg);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		zoneid = os.unmarshal_int();
		lineid = os.unmarshal_int();
		userid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		os.unmarshal(name);
		os.unmarshal(msg);
		return os;
	}

	public Object clone()
	{
		try
		{
			ForwardChat o = (ForwardChat)super.clone();
			o.name = (Octets)name.clone();
			o.msg = (Octets)msg.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		try {
			ChatInfo ci = new ChatInfo();
			ci.time = (int)(System.currentTimeMillis() / 1000);
			ci.zoneid = zoneid;
			ci.lineid = lineid;
			ci.userid = userid;
			ci.roleid = roleid;
			ci.name = new String(name.getBytes(),"UTF-16LE");
			ci.msg = new String(msg.getBytes(),"UTF-16LE");
			LocalDB.getInstance(1000,10000,7200,false).addChatInfo(ci);
			System.out.println( "ForwardChat, zoneid="+zoneid+",lineid="+lineid+",userid="+userid+",roleid="+roleid+",name="+ci.name+",msg="+ci.msg );
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
