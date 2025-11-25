package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class StockLog extends Rpc.Data
{
	public int	tid;
	public int	time;
	public short	result;
	public short	volume;
	public int	cost;

	public StockLog()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(tid);
		os.marshal(time);
		os.marshal(result);
		os.marshal(volume);
		os.marshal(cost);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		tid = os.unmarshal_int();
		time = os.unmarshal_int();
		result = os.unmarshal_short();
		volume = os.unmarshal_short();
		cost = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			StockLog o = (StockLog)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
