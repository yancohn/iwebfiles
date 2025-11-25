package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMForbidRole extends Protocol
{
	public byte	fbd_type;
	public int	gmroleid;
	public int	localsid;
	public int	dstroleid;
	public int	forbid_time;
	public Octets	reason;

	public GMForbidRole()
	{
		reason = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(fbd_type);
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(dstroleid);
		os.marshal(forbid_time);
		os.marshal(reason);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		fbd_type = os.unmarshal_byte();
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		dstroleid = os.unmarshal_int();
		forbid_time = os.unmarshal_int();
		os.unmarshal(reason);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMForbidRole o = (GMForbidRole)super.clone();
			o.reason = (Octets)reason.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
