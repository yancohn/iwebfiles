package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRolePocket extends Rpc
{
	public int retcode;
	public GRolePocket pocket;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RolePocketRes res = (RolePocketRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RolePocketRes res = (RolePocketRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				pocket = res.value;
			else
				pocket = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			pocket = null;
			this.notify();
		}
	}

}
