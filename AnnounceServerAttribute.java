package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class AnnounceServerAttribute extends Protocol
{
	public long	attr;

	public AnnounceServerAttribute()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(attr);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		attr = os.unmarshal_long();
		return os;
	}

	public Object clone()
	{
		try
		{
			AnnounceServerAttribute o = (AnnounceServerAttribute)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
