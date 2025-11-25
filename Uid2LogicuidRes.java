package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Uid2LogicuidRes extends Rpc.Data
{
	public int	retcode;
	public int	logicuid;

	public Uid2LogicuidRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(logicuid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		logicuid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			Uid2LogicuidRes o = (Uid2LogicuidRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
