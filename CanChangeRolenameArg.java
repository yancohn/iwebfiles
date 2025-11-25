package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class CanChangeRolenameArg extends Rpc.Data
{
	public Octets	rolename;
	public int	setcanchange;

	public CanChangeRolenameArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(rolename);
		os.marshal(setcanchange);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(rolename);
		setcanchange = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			CanChangeRolenameArg o = (CanChangeRolenameArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
