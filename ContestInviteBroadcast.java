package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class ContestInviteBroadcast extends Protocol
{
	public int	remain_time;

	public ContestInviteBroadcast()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(remain_time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		remain_time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			ContestInviteBroadcast o = (ContestInviteBroadcast)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
