package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetMaxOnlineNumRes extends Rpc.Data
{
	public int	retcode;
	public int	maxnum;
	public int	fake_maxnum;
	public int	curnum;

	public GetMaxOnlineNumRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(maxnum);
		os.marshal(fake_maxnum);
		os.marshal(curnum);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		maxnum = os.unmarshal_int();
		fake_maxnum = os.unmarshal_int();
		curnum = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GetMaxOnlineNumRes o = (GetMaxOnlineNumRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
