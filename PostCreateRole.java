package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class PostCreateRole extends Rpc
{
	public int retcode;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		PostCreateRoleArg arg = (PostCreateRoleArg)argument;
		PostCreateRoleRes res = (PostCreateRoleRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		PostCreateRoleArg arg = (PostCreateRoleArg)argument;
		PostCreateRoleRes res = (PostCreateRoleRes)result;

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
			retcode = 4;
			this.notify();
		}
	}

}
