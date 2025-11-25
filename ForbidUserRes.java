package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class ForbidUserRes extends Rpc.Data
{
	public int	retcode;
	public GRoleForbid	forbid;

	public ForbidUserRes()
	{
		forbid = new GRoleForbid();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(forbid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		os.unmarshal(forbid);
		return os;
	}

	public Object clone()
	{
		try
		{
			ForbidUserRes o = (ForbidUserRes)super.clone();
			o.forbid = (GRoleForbid)forbid.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
