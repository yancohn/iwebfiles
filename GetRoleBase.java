package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleBase extends Rpc
{
	public int retcode;
	public GRoleBase base;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleBaseRes res = (RoleBaseRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleBaseRes res = (RoleBaseRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				base = res.value;
			else
				base = null;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			base = null;
			this.notify();
		}
	}

}
