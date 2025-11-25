package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMGetGameAttriRes extends Rpc.Data
{
	public Octets	value;

	public GMGetGameAttriRes()
	{
		value = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			GMGetGameAttriRes o = (GMGetGameAttriRes)super.clone();
			o.value = (Octets)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
