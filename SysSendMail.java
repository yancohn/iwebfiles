package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SysSendMail extends Protocol
{
	public int	tid;
	public int	sysid;
	public byte	sys_type;
	public int	receiver;
	public Octets	title;
	public Octets	context;
	public GRoleInventory	attach_obj;
	public int	attach_money;

	public SysSendMail()
	{
		title = new Octets();
		context = new Octets();
		attach_obj = new GRoleInventory();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(tid);
		os.marshal(sysid);
		os.marshal(sys_type);
		os.marshal(receiver);
		os.marshal(title);
		os.marshal(context);
		os.marshal(attach_obj);
		os.marshal(attach_money);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		tid = os.unmarshal_int();
		sysid = os.unmarshal_int();
		sys_type = os.unmarshal_byte();
		receiver = os.unmarshal_int();
		os.unmarshal(title);
		os.unmarshal(context);
		os.unmarshal(attach_obj);
		attach_money = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			SysSendMail o = (SysSendMail)super.clone();
			o.title = (Octets)title.clone();
			o.context = (Octets)context.clone();
			o.attach_obj = (GRoleInventory)attach_obj.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
