package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SysRecoveredObjMail extends Protocol
{
	public int	tid;
	public byte	sys_type;
	public int	receiver;
	public Octets	title;
	public Octets	context;
	public Octets	obj;
	public Octets	checksum;

	public SysRecoveredObjMail()
	{
		title = new Octets();
		context = new Octets();
		obj = new Octets();
		checksum = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(tid);
		os.marshal(sys_type);
		os.marshal(receiver);
		os.marshal(title);
		os.marshal(context);
		os.marshal(obj);
		os.marshal(checksum);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		tid = os.unmarshal_int();
		sys_type = os.unmarshal_byte();
		receiver = os.unmarshal_int();
		os.unmarshal(title);
		os.unmarshal(context);
		os.unmarshal(obj);
		os.unmarshal(checksum);
		return os;
	}

	public Object clone()
	{
		try
		{
			SysRecoveredObjMail o = (SysRecoveredObjMail)super.clone();
			o.title = (Octets)title.clone();
			o.context = (Octets)context.clone();
			o.obj = (Octets)obj.clone();
			o.checksum = (Octets)checksum.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
