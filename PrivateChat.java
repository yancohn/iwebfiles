package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PrivateChat extends Protocol
{
	public byte	channel;
	public byte	emotion;
	public Octets	src_name;
	public int	srcroleid;
	public Octets	dst_name;
	public int	dstroleid;
	public Octets	msg;
	public Octets	data;
	public int	srcrolelevel;
	public int	srcfactionid;
	public int	srcsectid;
	public byte	route;
	public int	referrer;

	public PrivateChat()
	{
		src_name = new Octets();
		dst_name = new Octets();
		msg = new Octets();
		data = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(channel);
		os.marshal(emotion);
		os.marshal(src_name);
		os.marshal(srcroleid);
		os.marshal(dst_name);
		os.marshal(dstroleid);
		os.marshal(msg);
		os.marshal(data);
		os.marshal(srcrolelevel);
		os.marshal(srcfactionid);
		os.marshal(srcsectid);
		os.marshal(route);
		os.marshal(referrer);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		channel = os.unmarshal_byte();
		emotion = os.unmarshal_byte();
		os.unmarshal(src_name);
		srcroleid = os.unmarshal_int();
		os.unmarshal(dst_name);
		dstroleid = os.unmarshal_int();
		os.unmarshal(msg);
		os.unmarshal(data);
		srcrolelevel = os.unmarshal_int();
		srcfactionid = os.unmarshal_int();
		srcsectid = os.unmarshal_int();
		route = os.unmarshal_byte();
		referrer = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			PrivateChat o = (PrivateChat)super.clone();
			o.src_name = (Octets)src_name.clone();
			o.dst_name = (Octets)dst_name.clone();
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
