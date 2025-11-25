package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetLinePlayerLimitArg extends Rpc.Data
{
	public int	version;

	public GetLinePlayerLimitArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(version);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		version = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GetLinePlayerLimitArg o = (GetLinePlayerLimitArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
