package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleId extends Rpc.Data
{
	public int	id;

	public RoleId()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(id);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		id = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			RoleId o = (RoleId)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
