package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleIdArg extends Rpc.Data
{
	public Octets	rolename;
	public byte	reason;

	public GetRoleIdArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(rolename);
		os.marshal(reason);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(rolename);
		reason = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			GetRoleIdArg o = (GetRoleIdArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
