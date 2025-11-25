package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleBrief extends Rpc.Data
{
	public int	roleid;
	public Octets	name;
	public int	level;
	public long	money;
	public int	cashadd;
	public int	cashused;
	public int	itemnum;

	public GRoleBrief()
	{
		name = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(name);
		os.marshal(level);
		os.marshal(money);
		os.marshal(cashadd);
		os.marshal(cashused);
		os.marshal(itemnum);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		os.unmarshal(name);
		level = os.unmarshal_int();
		money = os.unmarshal_long();
		cashadd = os.unmarshal_int();
		cashused = os.unmarshal_int();
		itemnum = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleBrief o = (GRoleBrief)super.clone();
			o.name = (Octets)name.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
