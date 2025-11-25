package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMQueryRoleInfo extends Rpc
{
	public int retcode;
	public int status = -1;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		GMQueryRoleInfoRes res = (GMQueryRoleInfoRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		GMQueryRoleInfoRes res = (GMQueryRoleInfoRes)result;
		synchronized( this )
        {
			retcode = 0;
			status = res.status;
            this.notify();
        }
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			status = -1;   
            this.notify();
        }
	}

}
