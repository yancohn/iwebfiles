package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleId extends Rpc
{
	public int retcode;
	public int roleid;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GetRoleIdArg arg = (GetRoleIdArg)argument;
		GetRoleIdRes res = (GetRoleIdRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GetRoleIdArg arg = (GetRoleIdArg)argument;
		GetRoleIdRes res = (GetRoleIdRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
				roleid = res.roleid;
			else
				roleid = -1;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			roleid = -1;
			this.notify();
		}
	}

}
