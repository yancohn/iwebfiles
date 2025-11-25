package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class OctetsData extends Rpc.Data
{
	public Octets	octets_value;

	public OctetsData()
	{
		octets_value = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(octets_value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(octets_value);
		return os;
	}

	public Object clone()
	{
		try
		{
			OctetsData o = (OctetsData)super.clone();
			o.octets_value = (Octets)octets_value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
