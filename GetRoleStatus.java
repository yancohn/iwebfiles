package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleStatus extends Rpc
{
	public int retcode;
	public GRoleStatus status;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleStatusRes res = (RoleStatusRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleStatusRes res = (RoleStatusRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				status = res.value;
			else
				status = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			status = null;
			this.notify();
		}
	}

}
