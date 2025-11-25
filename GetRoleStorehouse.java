package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleStorehouse extends Rpc
{
	public int retcode;
	public GRoleStorehouse storehouse;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleStorehouseRes res = (RoleStorehouseRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleStorehouseRes res = (RoleStorehouseRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				storehouse = res.value;
			else
				storehouse = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			storehouse = null;
			this.notify();
		}
	}

}
