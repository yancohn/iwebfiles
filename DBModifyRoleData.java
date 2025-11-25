package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBModifyRoleData extends Rpc
{
	public int retcode;
	public DBModifyRoleDataRes datares;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		DBModifyRoleDataArg arg = (DBModifyRoleDataArg)argument;
		DBModifyRoleDataRes res = (DBModifyRoleDataRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		DBModifyRoleDataArg arg = (DBModifyRoleDataArg)argument;
		DBModifyRoleDataRes res = (DBModifyRoleDataRes)result;
		
		synchronized( this )
		{
			retcode = res.retcode;
			datares = res;
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
