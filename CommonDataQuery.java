package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class CommonDataQuery extends Rpc
{
	int retcode;
	int value;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		CommonDataQueryArg arg = (CommonDataQueryArg)argument;
		CommonDataQueryRes res = (CommonDataQueryRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		CommonDataQueryArg arg = (CommonDataQueryArg)argument;
		CommonDataQueryRes res = (CommonDataQueryRes)result;
		synchronized(this) {
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
			value = -1;
			this.notify();
		}
	}

}
