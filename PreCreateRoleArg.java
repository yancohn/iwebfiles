package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PreCreateRoleArg extends Rpc.Data
{
	public int	zoneid;
	public int	userid;
	public int	uselogic;
	public Octets	rolename;

	public PreCreateRoleArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(zoneid);
		os.marshal(userid);
		os.marshal(uselogic);
		os.marshal(rolename);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		zoneid = os.unmarshal_int();
		userid = os.unmarshal_int();
		uselogic = os.unmarshal_int();
		os.unmarshal(rolename);
		return os;
	}

	public Object clone()
	{
		try
		{
			PreCreateRoleArg o = (PreCreateRoleArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
