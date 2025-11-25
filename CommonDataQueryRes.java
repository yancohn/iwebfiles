package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class CommonDataQueryRes extends Rpc.Data
{
	public int	retcode;
	public int	value;

	public CommonDataQueryRes()
	{
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
		value = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			CommonDataQueryRes o = (CommonDataQueryRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
