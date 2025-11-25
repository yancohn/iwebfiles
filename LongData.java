package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class LongData extends Rpc.Data
{
	public long	long_value;

	public LongData()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(long_value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		long_value = os.unmarshal_long();
		return os;
	}

	public Object clone()
	{
		try
		{
			LongData o = (LongData)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
