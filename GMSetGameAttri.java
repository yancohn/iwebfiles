package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMSetGameAttri extends Rpc
{
	public int retcode;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GMSetGameAttriArg arg = (GMSetGameAttriArg)argument;
		GMSetGameAttriRes res = (GMSetGameAttriRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GMSetGameAttriArg arg = (GMSetGameAttriArg)argument;
		GMSetGameAttriRes res = (GMSetGameAttriRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			this.notify();
		}
	}

}
