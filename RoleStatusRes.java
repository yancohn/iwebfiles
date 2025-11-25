package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleStatusRes extends Rpc.Data
{
	public int	retcode;
	public GRoleStatus	value;

	public RoleStatusRes()
	{
		value = new GRoleStatus();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			RoleStatusRes o = (RoleStatusRes)super.clone();
			o.value = (GRoleStatus)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
