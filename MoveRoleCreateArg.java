package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class MoveRoleCreateArg extends Rpc.Data
{
	public int	fromzoneid;
	public int	zoneid;
	public int	userid;
	public Octets	rolename;

	public MoveRoleCreateArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(fromzoneid);
		os.marshal(zoneid);
		os.marshal(userid);
		os.marshal(rolename);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		fromzoneid = os.unmarshal_int();
		zoneid = os.unmarshal_int();
		userid = os.unmarshal_int();
		os.unmarshal(rolename);
		return os;
	}

	public Object clone()
	{
		try
		{
			MoveRoleCreateArg o = (MoveRoleCreateArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
