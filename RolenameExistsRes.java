package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RolenameExistsRes extends Rpc.Data
{
	public int	retcode;
	public int	zoneid;
	public int	roleid;
	public int	status;
	public int	time;

	public RolenameExistsRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(zoneid);
		os.marshal(roleid);
		os.marshal(status);
		os.marshal(time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		zoneid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		status = os.unmarshal_int();
		time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			RolenameExistsRes o = (RolenameExistsRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
