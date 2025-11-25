package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DefFactionRes extends Rpc.Data
{
	public int	retcode;

	public DefFactionRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DefFactionRes o = (DefFactionRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
