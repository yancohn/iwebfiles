package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SetLinePlayerLimitRes extends Rpc.Data
{
	public byte	retcode;

	public SetLinePlayerLimitRes()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			SetLinePlayerLimitRes o = (SetLinePlayerLimitRes)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
