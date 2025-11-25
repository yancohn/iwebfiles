package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class AnnounceLinkType extends Protocol
{
	public byte link_type;

	public AnnounceLinkType()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(link_type);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		link_type = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			AnnounceLinkType o = (AnnounceLinkType)super.clone();
			o.link_type = link_type;
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
