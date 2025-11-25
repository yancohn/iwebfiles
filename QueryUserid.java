package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class QueryUserid extends Rpc
{
	public int retcode;
	public QueryUseridRes queryUseridRes;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		QueryUseridArg arg = (QueryUseridArg)argument;
		QueryUseridRes res = (QueryUseridRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		QueryUseridArg arg = (QueryUseridArg)argument;
		QueryUseridRes res = (QueryUseridRes)result;
		synchronized(this)
		{
			retcode = res.result;
			if (0 == retcode)
				queryUseridRes = res;
			else
				queryUseridRes = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized(this)
		{
			retcode =4;
			queryUseridRes = null;
			this.notify();
		}
	}

}
