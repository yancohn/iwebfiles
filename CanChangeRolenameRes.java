package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class CanChangeRolenameRes extends Rpc.Data
{
	public int	retcode;
	public int	roleid;

	public CanChangeRolenameRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(roleid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		roleid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			CanChangeRolenameRes o = (CanChangeRolenameRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
