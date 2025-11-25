package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class OperationCmdRes extends Rpc.Data
{
	public int	retcode;
	public Octets	errmsg;

	public OperationCmdRes()
	{
		errmsg = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(errmsg);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		os.unmarshal(errmsg);
		return os;
	}

	public Object clone()
	{
		try
		{
			OperationCmdRes o = (OperationCmdRes)super.clone();
			o.errmsg = (Octets)errmsg.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
