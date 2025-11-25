package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleBasePair extends Rpc.Data
{
	public RoleId	key;
	public GRoleBase	value;

	public RoleBasePair()
	{
		key = new RoleId();
		value = new GRoleBase();
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
			RoleBasePair o = (RoleBasePair)super.clone();
			o.key = (RoleId)key.clone();
			o.value = (GRoleBase)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
