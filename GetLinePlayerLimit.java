package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetLinePlayerLimit extends Rpc
{
	public int retcode;
	public LinePlayerNumberLimits	limits;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GetLinePlayerLimitArg arg = (GetLinePlayerLimitArg)argument;
		LinePlayerNumberLimits res = (LinePlayerNumberLimits)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GetLinePlayerLimitArg arg = (GetLinePlayerLimitArg)argument;
		LinePlayerNumberLimits res = (LinePlayerNumberLimits)result;

		synchronized( this )
		{
			retcode = 0;
			limits = res;
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
