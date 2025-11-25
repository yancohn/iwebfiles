package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RenameRole extends Rpc
{
	public int retcode;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RenameRoleArg arg = (RenameRoleArg)argument;
		RpcRetcode res = (RpcRetcode)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RenameRoleArg arg = (RenameRoleArg)argument;
		RpcRetcode res = (RpcRetcode)result;

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
