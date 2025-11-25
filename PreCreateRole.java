package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PreCreateRole extends Rpc
{
	public int retcode;
	public int roleid;
	public int logicuid; 

	public void Server(Data argument, Data result) throws ProtocolException
	{
		PreCreateRoleArg arg = (PreCreateRoleArg)argument;
		PreCreateRoleRes res = (PreCreateRoleRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		PreCreateRoleArg arg = (PreCreateRoleArg)argument;
		PreCreateRoleRes res = (PreCreateRoleRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			roleid = res.roleid;
			logicuid = res.logicuid;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			roleid = -1;
			this.notify();
		}
	}

}
