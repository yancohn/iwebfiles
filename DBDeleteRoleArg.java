package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBDeleteRoleArg extends Rpc.Data
{
	public int	roleid;
	public byte	create_rollback;

	public DBDeleteRoleArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(create_rollback);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		create_rollback = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBDeleteRoleArg o = (DBDeleteRoleArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
