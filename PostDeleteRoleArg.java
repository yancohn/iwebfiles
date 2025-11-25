package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PostDeleteRoleArg extends Rpc.Data
{
	public int	userid;
	public int	zoneid;
	public int	roleid;
	public OctetsData	namelist;

	public PostDeleteRoleArg()
	{
		namelist = new OctetsData();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(userid);
		os.marshal(zoneid);
		os.marshal(roleid);
		os.marshal(namelist);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		userid = os.unmarshal_int();
		zoneid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		os.unmarshal(namelist);
		return os;
	}

	public Object clone()
	{
		try
		{
			PostDeleteRoleArg o = (PostDeleteRoleArg)super.clone();
			o.namelist = (OctetsData)namelist.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
