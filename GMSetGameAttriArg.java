package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMSetGameAttriArg extends Rpc.Data
{
	public int	gmroleid;
	public int	localsid;
	public byte	attribute;
	public Octets	value;

	public GMSetGameAttriArg()
	{
		value = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(gmroleid);
		os.marshal(localsid);
		os.marshal(attribute);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		gmroleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		attribute = os.unmarshal_byte();
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMSetGameAttriArg o = (GMSetGameAttriArg)super.clone();
			o.value = (Octets)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
