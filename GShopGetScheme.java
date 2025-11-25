package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GShopGetScheme extends Rpc
{
	int retcode;
	GShopScheme gshopscheme;
	public void Server(Data argument, Data result) throws ProtocolException
	{
		IntData arg = (IntData)argument;
		GShopScheme res = (GShopScheme)result;
	}

	public void Client(Data argument, Data result) throws ProtocolException
	{
		IntData arg = (IntData)argument;
		GShopScheme res = (GShopScheme)result;
		synchronized(this) {
			retcode = 0;
			gshopscheme = res;
			this.notify();
		}
	}

	public void OnTimeout()
	{
		synchronized( this )
		{
			retcode = 4;
			gshopscheme = null;
			this.notify();
		}
	}

}
