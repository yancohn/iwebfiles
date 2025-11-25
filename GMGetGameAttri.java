package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMGetGameAttri extends Rpc
{
	public int retcode;
	public Octets	value;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GMGetGameAttriArg arg = (GMGetGameAttriArg)argument;
		GMGetGameAttriRes res = (GMGetGameAttriRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GMGetGameAttriArg arg = (GMGetGameAttriArg)argument;
		GMGetGameAttriRes res = (GMGetGameAttriRes)result;

		synchronized( this )
		{
			retcode = 0;
			value = res.value;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			value = null;
			this.notify();
		}
	}

}
