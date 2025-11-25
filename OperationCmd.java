package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class OperationCmd extends Rpc
{
	public int retcode;
	public String errmsg;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		OperationCmdArg arg = (OperationCmdArg)argument;
		OperationCmdRes res = (OperationCmdRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		OperationCmdArg arg = (OperationCmdArg)argument;
		OperationCmdRes res = (OperationCmdRes)result;
		synchronized(this){
			this.retcode=res.retcode;
			this.errmsg=res.errmsg.getString("UTF-8");;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized(this){
			this.retcode=4;
			this.notify();
		}
	}

}
