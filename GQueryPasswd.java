package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GQueryPasswd extends Rpc
{
	public void Server(Data argument, Data result) throws ProtocolException
	{
		GQueryPasswdArg arg = (GQueryPasswdArg)argument;
		GQueryPasswdRes res = (GQueryPasswdRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GQueryPasswdArg arg = (GQueryPasswdArg)argument;
		GQueryPasswdRes res = (GQueryPasswdRes)result;
	}

	public void OnTimeout()
	{
	}

}
