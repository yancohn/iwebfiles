package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleBaseRes extends Rpc.Data
{
	public int	retcode;
	public GRoleBase	value;

	public RoleBaseRes()
	{
		value = new GRoleBase();
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
			RoleBaseRes o = (RoleBaseRes)super.clone();
			o.value = (GRoleBase)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
