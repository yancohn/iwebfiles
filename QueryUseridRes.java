package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class QueryUseridRes extends Rpc.Data
{
	public int	result;
	public int	userid;
	public int	roleid;
	public int	level;

	public QueryUseridRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(result);
		os.marshal(userid);
		os.marshal(roleid);
		os.marshal(level);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		result = os.unmarshal_int();
		userid = os.unmarshal_int();
		roleid = os.unmarshal_int();
		level = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			QueryUseridRes o = (QueryUseridRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
