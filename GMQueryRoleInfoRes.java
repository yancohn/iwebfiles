package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMQueryRoleInfoRes extends Rpc.Data
{
	public int	status;

	public GMQueryRoleInfoRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(status);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		status = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMQueryRoleInfoRes o = (GMQueryRoleInfoRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
