package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class WorldChat extends Protocol
{
	public byte	channel;
	public byte	emotion;
	public int	roleid;
	public Octets	name;
	public Octets	msg;
	public Octets	data;
	public int	sender_zoneid;

	public WorldChat()
	{
		name = new Octets();
		msg = new Octets();
		data = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(channel);
		os.marshal(emotion);
		os.marshal(roleid);
		os.marshal(name);
		os.marshal(msg);
		os.marshal(data);
		os.marshal(sender_zoneid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		channel = os.unmarshal_byte();
		emotion = os.unmarshal_byte();
		roleid = os.unmarshal_int();
		os.unmarshal(name);
		os.unmarshal(msg);
		os.unmarshal(data);
		sender_zoneid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			WorldChat o = (WorldChat)super.clone();
			o.name = (Octets)name.clone();
			o.msg = (Octets)msg.clone();
			o.data = (Octets)data.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
