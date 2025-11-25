package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

import org.apache.commons.logging.LogFactory; 
import org.apache.commons.logging.Log;
public final class ForbidUser extends Rpc
{
	public int retcode;
	public GRoleForbid groleforbid = new GRoleForbid();

	private static final Log log = LogFactory.getLog(ForbidUser.class);
	public void Server(Data argument, Data result) throws ProtocolException
	{
		ForbidUserArg arg = (ForbidUserArg)argument;
		ForbidUserRes res = (ForbidUserRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		ForbidUserArg arg = (ForbidUserArg)argument;
		ForbidUserRes res = (ForbidUserRes)result;

		synchronized(this) {
			retcode = res.retcode;
			groleforbid = res.forbid;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized(this) {
			retcode = 4;
			this.notify();
		}
	}

}
