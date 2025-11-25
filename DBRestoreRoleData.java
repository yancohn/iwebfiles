package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBRestoreRoleData extends Rpc
{
	public int retcode; 
	public DBRestoreRoleDataRes restoreRoleDataRes;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		DBRestoreRoleDataArg arg = (DBRestoreRoleDataArg)argument;
		DBRestoreRoleDataRes res = (DBRestoreRoleDataRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		DBRestoreRoleDataArg arg = (DBRestoreRoleDataArg)argument;
		DBRestoreRoleDataRes res = (DBRestoreRoleDataRes)result;
		synchronized(this) {
			retcode = res.retcode;
			restoreRoleDataRes = res;
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
