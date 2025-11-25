package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleBase2Pair extends Rpc.Data
{
	public RoleId	key;
	public GRoleBase2	value;

	public RoleBase2Pair()
	{
		key = new RoleId();
		value = new GRoleBase2();
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
			RoleBase2Pair o = (RoleBase2Pair)super.clone();
			o.key = (RoleId)key.clone();
			o.value = (GRoleBase2)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
