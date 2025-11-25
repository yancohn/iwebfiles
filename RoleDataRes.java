package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleDataRes extends Rpc.Data
{
	public int	retcode;
	public GRoleData	value;

	public RoleDataRes()
	{
		value = new GRoleData();
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
			RoleDataRes o = (RoleDataRes)super.clone();
			o.value = (GRoleData)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
