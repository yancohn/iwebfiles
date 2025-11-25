package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Uid2Logicuid extends Rpc
{
	public int	retcode;
	public int	logicuid;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		Uid2LogicuidArg arg = (Uid2LogicuidArg)argument;
		Uid2LogicuidRes res = (Uid2LogicuidRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		Uid2LogicuidArg arg = (Uid2LogicuidArg)argument;
		Uid2LogicuidRes res = (Uid2LogicuidRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			logicuid = res.logicuid;
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
