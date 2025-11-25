package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class UserRoleCountRes extends Rpc.Data
{
	public int	retcode;
	public int	count;
	public int	rolelist;

	public UserRoleCountRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(count);
		os.marshal(rolelist);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		count = os.unmarshal_int();
		rolelist = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			UserRoleCountRes o = (UserRoleCountRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
