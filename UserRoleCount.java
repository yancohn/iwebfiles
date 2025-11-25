package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class UserRoleCount extends Rpc
{
	public int retcode;
	public int count;
	public int rolelist;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		UserRoleCountArg arg = (UserRoleCountArg)argument;
		UserRoleCountRes res = (UserRoleCountRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		UserRoleCountArg arg = (UserRoleCountArg)argument;
		UserRoleCountRes res = (UserRoleCountRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			count = res.count;
			rolelist = res.rolelist;
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
