package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetUser extends Rpc
{
	public int retcode;
	public User user;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		UserArg arg = (UserArg)argument;
		UserRes res = (UserRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		UserArg arg = (UserArg)argument;
		UserRes res = (UserRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				user = res.value;
			else
				user = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			user = null;
			this.notify();
		}
	}

}
