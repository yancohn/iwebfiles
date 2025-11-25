package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetMaxOnlineNumArg extends Rpc.Data
{
	public int	padding;

	public GetMaxOnlineNumArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(padding);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		padding = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GetMaxOnlineNumArg o = (GetMaxOnlineNumArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
