package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetMaxOnlineNum extends Rpc
{
	public int	retcode;
	public int	maxnum;
	public int	fakemaxnum;
	public int	curnum;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GetMaxOnlineNumArg arg = (GetMaxOnlineNumArg)argument;
		GetMaxOnlineNumRes res = (GetMaxOnlineNumRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GetMaxOnlineNumArg arg = (GetMaxOnlineNumArg)argument;
		GetMaxOnlineNumRes res = (GetMaxOnlineNumRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			maxnum = res.maxnum;
			fakemaxnum = res.fake_maxnum;
			curnum = res.curnum;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			maxnum = -1;
			fakemaxnum = -1;
			curnum = -1;
			this.notify();
		}
	}

}
