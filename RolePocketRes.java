package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RolePocketRes extends Rpc.Data
{
	public int	retcode;
	public GRolePocket	value;

	public RolePocketRes()
	{
		value = new GRolePocket();
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
			RolePocketRes o = (RolePocketRes)super.clone();
			o.value = (GRolePocket)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
