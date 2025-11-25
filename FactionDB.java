
package protocol;

 
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.Common.Security.*;


public class FactionDB
{
	public FactionDB ()
	{
	}

	public static final Object locker = new Object();
	public static FactionClientManager mgr = new FactionClientManager();

	public static void init( ) throws Exception
	{
		synchronized( mgr )
		{
			if( null == mgr.s )
			{
				Protocol.Client(mgr);
				mgr.wait();
			}
		}
	}

}
