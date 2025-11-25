package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SetMaxOnlineNum extends Protocol
{
	public int	maxnum;
	public int	fake_maxnum;

	public SetMaxOnlineNum()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(maxnum);
		os.marshal(fake_maxnum);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		maxnum = os.unmarshal_int();
		fake_maxnum = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			SetMaxOnlineNum o = (SetMaxOnlineNum)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
