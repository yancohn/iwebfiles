package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class UserArg extends Rpc.Data
{
	public int	id;
	public int	loginip;

	public UserArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(id);
		os.marshal(loginip);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		id = os.unmarshal_int();
		loginip = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			UserArg o = (UserArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
