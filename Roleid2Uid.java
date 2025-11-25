package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class Roleid2Uid extends Rpc
{
	public int	retcode;
	public int	userid;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		Roleid2UidArg arg = (Roleid2UidArg)argument;
		Roleid2UidRes res = (Roleid2UidRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		Roleid2UidArg arg = (Roleid2UidArg)argument;
		Roleid2UidRes res = (Roleid2UidRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			userid = res.userid;
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
