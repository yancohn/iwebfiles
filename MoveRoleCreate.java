package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class MoveRoleCreate extends Rpc
{
	public int retcode;
	public int roleid;

	public void Server(Data argument, Data result) throws ProtocolException
	{
		MoveRoleCreateArg arg = (MoveRoleCreateArg)argument;
		MoveRoleCreateRes res = (MoveRoleCreateRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		MoveRoleCreateArg arg = (MoveRoleCreateArg)argument;
		MoveRoleCreateRes res = (MoveRoleCreateRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			roleid = res.roleid;
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
