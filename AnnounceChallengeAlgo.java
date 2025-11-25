package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class AnnounceChallengeAlgo extends Protocol
{
	public byte	challenge_algo;
	public int	auth_version;

	public AnnounceChallengeAlgo()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(challenge_algo);
		os.marshal(auth_version);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		challenge_algo = os.unmarshal_byte();
		auth_version = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			AnnounceChallengeAlgo o = (AnnounceChallengeAlgo)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
