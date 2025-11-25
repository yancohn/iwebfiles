package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RolePocketPair extends Rpc.Data
{
	public RoleId	key;
	public GRolePocket	value;

	public RolePocketPair()
	{
		key = new RoleId();
		value = new GRolePocket();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(key);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(key);
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			RolePocketPair o = (RolePocketPair)super.clone();
			o.key = (RoleId)key.clone();
			o.value = (GRolePocket)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
