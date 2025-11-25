package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMGetGameAttriArg extends Rpc.Data
{
	public int	gmroleid;
	public int	localsid;
	public byte	attribute;

	public GMGetGameAttriArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(attribute);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		attribute = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMGetGameAttriArg o = (GMGetGameAttriArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
