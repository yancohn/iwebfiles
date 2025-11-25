package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBCreateRoleArg extends Rpc.Data
{
	public int	userid;
	public int	logicuid;
	public int	roleid;
	public RoleInfo	roleinfo;
	public int	real_referrer;
	public int	au_suggest_referrer;
	public int	player_suggest_referrer;

	public DBCreateRoleArg()
	{
		roleinfo = new RoleInfo();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(userid);
		os.marshal(logicuid);
		os.marshal(roleid);
		os.marshal(roleinfo);
		os.marshal(real_referrer);
		os.marshal(au_suggest_referrer);
		os.marshal(player_suggest_referrer);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		userid = os.unmarshal_int();
		logicuid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		os.unmarshal(roleinfo);
		real_referrer = os.unmarshal_int();
		au_suggest_referrer = os.unmarshal_int();
		player_suggest_referrer = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBCreateRoleArg o = (DBCreateRoleArg)super.clone();
			o.roleinfo = (RoleInfo)roleinfo.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
