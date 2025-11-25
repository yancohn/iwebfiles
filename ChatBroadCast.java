package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class ChatBroadCast extends Protocol
{
	public byte	channel;
	public byte	emotion;
	public int	srcroleid;
	public Octets	msg;
	public Octets	data;

	public ChatBroadCast()
	{
		msg = new Octets();
		data = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(channel);
		os.marshal(emotion);
		os.marshal(srcroleid);
		os.marshal(msg);
		os.marshal(data);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		channel = os.unmarshal_byte();
		emotion = os.unmarshal_byte();
		srcroleid = os.unmarshal_int();
		os.unmarshal(msg);
		os.unmarshal(data);
		return os;
	}

	public Object clone()
	{
		try
		{
			ChatBroadCast o = (ChatBroadCast)super.clone();
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
