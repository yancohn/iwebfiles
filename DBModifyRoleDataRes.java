package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBModifyRoleDataRes extends Rpc.Data
{
	public int	retcode;
	public long	total_money;

	public DBModifyRoleDataRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(total_money);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		total_money = os.unmarshal_long();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBModifyRoleDataRes o = (DBModifyRoleDataRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
