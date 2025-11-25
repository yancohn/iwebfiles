package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMSetGameAttriRes extends Rpc.Data
{
	public int	retcode;

	public GMSetGameAttriRes()
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
			GMSetGameAttriRes o = (GMSetGameAttriRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
