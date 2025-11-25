package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBModifyRoleDataArg extends Rpc.Data
{
	public int	roleid;
	public int	mask;
	public int	level;
	public long	exp;
	public int	pocket_money;
	public int	store_money;
	public int	pkvalue;
	public int	reputation;
	public int	potential;
	public int	occupation;

	public DBModifyRoleDataArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(mask);
		os.marshal(level);
		os.marshal(exp);
		os.marshal(pocket_money);
		os.marshal(store_money);
		os.marshal(pkvalue);
		os.marshal(reputation);
		os.marshal(potential);
		os.marshal(occupation);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		mask = os.unmarshal_int();
		level = os.unmarshal_int();
		exp = os.unmarshal_long();
		pocket_money = os.unmarshal_int();
		store_money = os.unmarshal_int();
		pkvalue = os.unmarshal_int();
		reputation = os.unmarshal_int();
		potential = os.unmarshal_int();
		occupation = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			DBModifyRoleDataArg o = (DBModifyRoleDataArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
