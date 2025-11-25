package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMRestartServer_Re extends Protocol
{
	public int	retcode;
	public int	gmroleid;
	public int	localsid;
	public int	gsid;

	public GMRestartServer_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(gsid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		gsid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMRestartServer_Re o = (GMRestartServer_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		System.out.println( "GMRestartServer_Re, retcode=" + retcode + ",gmroleid=" + gmroleid );
	}

}
