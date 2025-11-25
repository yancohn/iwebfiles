package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetUserRolesRes extends Rpc.Data
{
	public int	retcode;
	public Rpc.Data.DataVector	roles;
	public Rpc.Data.DataVector	data;

	public GetUserRolesRes()
	{
		roles = new Rpc.Data.DataVector(new IntOctets());
		data = new Rpc.Data.DataVector(new GRoleBrief());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(roles);
		os.marshal(data);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		os.unmarshal(roles);
		try {
			os.unmarshal(data);
		} catch (Exception e) {}
		return os;
	}

	public Object clone()
	{
		try
		{
			GetUserRolesRes o = (GetUserRolesRes)super.clone();
			o.roles = (Rpc.Data.DataVector)roles.clone();
			o.data = (Rpc.Data.DataVector)data.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
