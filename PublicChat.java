package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PublicChat extends Protocol
{
	public byte	channel;
	public byte	emotion;
	public int	roleid;
	public int	localsid;
	public Octets	msg;
	public Octets	data;
	public int	item_pos;

	public PublicChat()
	{
		msg = new Octets();
		data = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(channel);
		os.marshal(emotion);
		os.marshal(roleid);
		os.marshal(localsid);
		os.marshal(msg);
		os.marshal(data);
		os.marshal(item_pos);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		channel = os.unmarshal_byte();
		emotion = os.unmarshal_byte();
		roleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		os.unmarshal(msg);
		os.unmarshal(data);
		item_pos = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			PublicChat o = (PublicChat)super.clone();
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
