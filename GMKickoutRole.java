package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMKickoutRole extends Protocol
{
	public int	gmroleid;
	public int	localsid;
	public int	kickroleid;
	public int	forbid_time;
	public Octets	reason;

	public GMKickoutRole()
	{
		reason = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(kickroleid);
		os.marshal(forbid_time);
		os.marshal(reason);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		kickroleid = os.unmarshal_int();
		forbid_time = os.unmarshal_int();
		os.unmarshal(reason);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMKickoutRole o = (GMKickoutRole)super.clone();
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
