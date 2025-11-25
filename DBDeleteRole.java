package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBDeleteRole extends Rpc
{
	public int retcode;
	public OctetsData namelist=new OctetsData();

	public void Server(Data argument, Data result) throws ProtocolException
	{
		DBDeleteRoleArg arg = (DBDeleteRoleArg)argument;
		DBDeleteRoleRes res = (DBDeleteRoleRes)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		DBDeleteRoleArg arg = (DBDeleteRoleArg)argument;
		DBDeleteRoleRes res = (DBDeleteRoleRes)result;

		synchronized( this )
		{
			retcode = res.retcode;
			namelist=res.namelist;
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
