package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GPocketInventory extends Rpc.Data
{
	public int	id;
	public short	pos;
	public short	count;

	public GPocketInventory()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(id);
		os.marshal(pos);
		os.marshal(count);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		id = os.unmarshal_int();
		pos = os.unmarshal_short();
		count = os.unmarshal_short();
		return os;
	}

	public Object clone()
	{
		try
		{
			GPocketInventory o = (GPocketInventory)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
