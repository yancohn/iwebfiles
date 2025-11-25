package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBVerifyMaster extends Rpc
{
	public int retcode;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		DBVerifyMasterArg arg = (DBVerifyMasterArg)argument;
		DefFactionRes res = (DefFactionRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		DBVerifyMasterArg arg = (DBVerifyMasterArg)argument;
		DefFactionRes res = (DefFactionRes)result;

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
			retcode = -1;
			this.notify();
		}
	}

}
