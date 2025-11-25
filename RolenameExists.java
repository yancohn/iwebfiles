package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RolenameExists extends Rpc
{
	public int retcode;
	public int zoneid;
	public int roleid;
	public int status;
	public int time;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RolenameExistsArg arg = (RolenameExistsArg)argument;
		RolenameExistsRes res = (RolenameExistsRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RolenameExistsArg arg = (RolenameExistsArg)argument;
		RolenameExistsRes res = (RolenameExistsRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			zoneid = res.zoneid;
			roleid = res.roleid;
			status = res.status;
			time = res.time;
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
