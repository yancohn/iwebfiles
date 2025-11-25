package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetUserRolesArg extends Rpc.Data
{
	public int	userid;

	public GetUserRolesArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(userid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		userid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GetUserRolesArg o = (GetUserRolesArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
