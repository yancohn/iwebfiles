package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Roleid2UidArg extends Rpc.Data
{
	public int	roleid;

	public Roleid2UidArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			Roleid2UidArg o = (Roleid2UidArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
