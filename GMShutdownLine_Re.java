package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMShutdownLine_Re extends Protocol
{
	public int	retcode;
	public int	gmroleid;
	public int	localsid;
	public int	gs_id;

	public GMShutdownLine_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(gs_id);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		gs_id = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMShutdownLine_Re o = (GMShutdownLine_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
