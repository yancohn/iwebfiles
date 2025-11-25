package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SetLinePlayerLimit extends Rpc
{
	public int retcode;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		LinePlayerNumberLimits arg = (LinePlayerNumberLimits)argument;
		SetLinePlayerLimitRes res = (SetLinePlayerLimitRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		LinePlayerNumberLimits arg = (LinePlayerNumberLimits)argument;
		SetLinePlayerLimitRes res = (SetLinePlayerLimitRes)result;

		synchronized( this )
		{
			retcode = (int)res.retcode;
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
