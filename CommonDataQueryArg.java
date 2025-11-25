package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class CommonDataQueryArg extends Rpc.Data
{
	public int	key;

	public CommonDataQueryArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(key);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		key = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			CommonDataQueryArg o = (CommonDataQueryArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
