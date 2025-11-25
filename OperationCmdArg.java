package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class OperationCmdArg extends Rpc.Data
{
	public int	operationtype;
	public Octets	code;

	public OperationCmdArg()
	{
		code = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(operationtype);
		os.marshal(code);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		operationtype = os.unmarshal_int();
		os.unmarshal(code);
		return os;
	}

	public Object clone()
	{
		try
		{
			OperationCmdArg o = (OperationCmdArg)super.clone();
			o.code = (Octets)code.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
