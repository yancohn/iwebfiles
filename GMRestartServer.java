package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMRestartServer extends Protocol
{
	public int	gmroleid;
	public int	localsid;
	public int	gsid;
	public int	restart_time;

	public GMRestartServer()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(gsid);
		os.marshal(restart_time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		gsid = os.unmarshal_int();
		restart_time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMRestartServer o = (GMRestartServer)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
