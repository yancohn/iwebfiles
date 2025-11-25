package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SetMaxOnlineNum_Re extends Protocol
{
	public int	retcode;

	public SetMaxOnlineNum_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			SetMaxOnlineNum_Re o = (SetMaxOnlineNum_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		System.out.println( "SetMaxOnlineNum_Re, retcode=" + retcode );
	}

}
