package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Uid2LogicuidArg extends Rpc.Data
{
	public int	userid;

	public Uid2LogicuidArg()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(userid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		userid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			Uid2LogicuidArg o = (Uid2LogicuidArg)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
