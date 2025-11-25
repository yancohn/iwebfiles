package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GetRoleBase2 extends Rpc
{
	public int retcode;
	public GRoleBase2 base2;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleBase2Res res = (RoleBase2Res)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		RoleId arg = (RoleId)argument;
		RoleBase2Res res = (RoleBase2Res)result;
		synchronized( this )
		{
			retcode = res.retcode;
			if( 0 == res.retcode )
			{
				base2 = res.value;
			}
			else
			{
				System.out.println("GetRoleBase2 error retcode =" + retcode);
				base2 = null;
			}
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			base2 = null;
			this.notify();
		}
	}

}
