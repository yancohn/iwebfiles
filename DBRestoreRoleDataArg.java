package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBRestoreRoleDataArg extends Rpc.Data
{
	public int	roleid;
	public int	force;

	public DBRestoreRoleDataArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(force);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		force = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBRestoreRoleDataArg o = (DBRestoreRoleDataArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
