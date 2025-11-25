package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class AchievementMessage extends Protocol
{
	public int	roleid;
	public short	achieve_id;
	public int	param;
	public Octets	rolename;
	public int	localsid;
	public int	timestamp;

	public AchievementMessage()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(achieve_id);
		os.marshal(param);
		os.marshal(rolename);
		os.marshal(localsid);
		os.marshal(timestamp);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		achieve_id = os.unmarshal_short();
		param = os.unmarshal_int();
		os.unmarshal(rolename);
		localsid = os.unmarshal_int();
		timestamp = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			AchievementMessage o = (AchievementMessage)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
