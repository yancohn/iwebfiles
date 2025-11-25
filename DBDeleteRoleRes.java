package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBDeleteRoleRes extends Rpc.Data
{
	public int	retcode;
	public int	userid;
	public int	rolelist;
	public int	faction;
	public int	family;
	public int	circleid;
	public OctetsData	namelist;

	public DBDeleteRoleRes()
	{
		namelist = new OctetsData();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(userid);
		os.marshal(rolelist);
		os.marshal(faction);
		os.marshal(family);
		os.marshal(circleid);
		os.marshal(namelist);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		userid = os.unmarshal_int();
		rolelist = os.unmarshal_int();
		faction = os.unmarshal_int();
		family = os.unmarshal_int();
		circleid = os.unmarshal_int();
		os.unmarshal(namelist);
		return os;
	}

	public Object clone()
	{
		try
		{
			DBDeleteRoleRes o = (DBDeleteRoleRes)super.clone();
			o.namelist = (OctetsData)namelist.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
