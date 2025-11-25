package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleBase2Res extends Rpc.Data
{
	public int	retcode;
	public GRoleBase2	value;

	public RoleBase2Res()
	{
		value = new GRoleBase2();
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
			RoleBase2Res o = (RoleBase2Res)super.clone();
			o.value = (GRoleBase2)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
