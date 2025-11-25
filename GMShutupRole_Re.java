package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMShutupRole_Re extends Protocol
{
	public int	retcode;
	public int	dstroleid;
	public int	forbid_time;

	public GMShutupRole_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(dstroleid);
		os.marshal(forbid_time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		dstroleid = os.unmarshal_int();
		forbid_time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMShutupRole_Re o = (GMShutupRole_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
