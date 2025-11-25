package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleIdRes extends Rpc.Data
{
	public int	retcode;
	public int	roleid;
	public Octets	newname;

	public GetRoleIdRes()
	{
		newname = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(roleid);
		os.marshal(newname);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		roleid = os.unmarshal_int();
		os.unmarshal(newname);
		return os;
	}

	public Object clone()
	{
		try
		{
			GetRoleIdRes o = (GetRoleIdRes)super.clone();
			o.newname = (Octets)newname.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
