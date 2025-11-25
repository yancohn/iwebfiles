package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Roleid2UidRes extends Rpc.Data
{
	public int	retcode;
	public int	userid;
	public int	src_zoneid;

	public Roleid2UidRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(userid);
		os.marshal(src_zoneid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		userid = os.unmarshal_int();
		src_zoneid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			Roleid2UidRes o = (Roleid2UidRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
