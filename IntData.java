package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class IntData extends Rpc.Data
{
	public int	int_value;

	public IntData()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(int_value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		int_value = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			IntData o = (IntData)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
