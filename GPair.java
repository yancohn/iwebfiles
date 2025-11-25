package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GPair extends Rpc.Data
{
	public int	key;
	public int	value;

	public GPair()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(key);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		key = os.unmarshal_int();
		value = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GPair o = (GPair)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
