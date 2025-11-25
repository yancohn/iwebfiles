package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleDataPair extends Rpc.Data
{
	public RoleId	key;
	public byte	overwrite;
	public GRoleData	value;

	public RoleDataPair()
	{
		key = new RoleId();
		value = new GRoleData();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(key);
		os.marshal(overwrite);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(key);
		overwrite = os.unmarshal_byte();
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			RoleDataPair o = (RoleDataPair)super.clone();
			o.key = (RoleId)key.clone();
			o.value = (GRoleData)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
