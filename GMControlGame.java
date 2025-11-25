package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMControlGame extends Protocol
{
	public int	xid;
	public int	worldtag;
	public Octets	command;

	public GMControlGame()
	{
		command = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(xid);
		os.marshal(worldtag);
		os.marshal(command);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		xid = os.unmarshal_int();
		worldtag = os.unmarshal_int();
		os.unmarshal(command);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMControlGame o = (GMControlGame)super.clone();
			o.command = (Octets)command.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
