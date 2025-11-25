package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.IO.Protocol.Rpc.Data.DataVector;

public final class GetUserRoles extends Rpc
{
	public int retcode;
	public int userid;
	public DataVector	roles;
	public DataVector	data;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		GetUserRolesArg arg = (GetUserRolesArg)argument;
		GetUserRolesRes res = (GetUserRolesRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		GetUserRolesArg arg = (GetUserRolesArg)argument;
		GetUserRolesRes res = (GetUserRolesRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
			{
				userid = arg.userid;
				roles = res.roles;
				data = res.data;
			}
			else
			{
				userid = -1;
				roles = null;
				data = null;
			}
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			userid = -1;
			roles = null;
			data = null;
			this.notify();
		}
	}

}
