package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PostCreateRoleArg extends Rpc.Data
{
	public byte	success;
	public int	userid;
	public int	zoneid;
	public int	roleid;
	public Octets	rolename;

	public PostCreateRoleArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(success);
		os.marshal(userid);
		os.marshal(zoneid);
		os.marshal(roleid);
		os.marshal(rolename);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		success = os.unmarshal_byte();
		userid = os.unmarshal_int();
		zoneid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		os.unmarshal(rolename);
		return os;
	}

	public Object clone()
	{
		try
		{
			PostCreateRoleArg o = (PostCreateRoleArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
