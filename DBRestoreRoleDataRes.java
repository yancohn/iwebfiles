package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBRestoreRoleDataRes extends Rpc.Data
{
	public int	retcode;
	public int	restore_time;

	public DBRestoreRoleDataRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(restore_time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		restore_time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBRestoreRoleDataRes o = (DBRestoreRoleDataRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
