package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMControlGameRes extends Rpc.Data
{
	public int	gsid;
	public int	retcode;

	public GMControlGameRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(gsid);
		os.marshal(retcode);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		gsid = os.unmarshal_int();
		retcode = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMControlGameRes o = (GMControlGameRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
