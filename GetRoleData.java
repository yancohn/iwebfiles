package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleData extends Rpc
{
	public int retcode;
	public GRoleData value;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleDataRes res = (RoleDataRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleDataRes res = (RoleDataRes)result;
		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				value = res.value;
			else
				value = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			value = null;
			this.notify();
		}
	}

}
