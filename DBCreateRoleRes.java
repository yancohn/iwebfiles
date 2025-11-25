package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBCreateRoleRes extends Rpc.Data
{
	public int	retcode;
	public int	roleid;
	public int	rolelist;
	public GRoleInfo	roleinfo;
	public int	real_referrer;
	public int	refretcode;

	public DBCreateRoleRes()
	{
		roleinfo = new GRoleInfo();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(roleid);
		os.marshal(rolelist);
		os.marshal(roleinfo);
		os.marshal(real_referrer);
		os.marshal(refretcode);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		roleid = os.unmarshal_int();
		rolelist = os.unmarshal_int();
		os.unmarshal(roleinfo);
		real_referrer = os.unmarshal_int();
		refretcode = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBCreateRoleRes o = (DBCreateRoleRes)super.clone();
			o.roleinfo = (GRoleInfo)roleinfo.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
