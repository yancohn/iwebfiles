package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class SysSendMail_Re extends Protocol
{
	public short	retcode;
	public int	tid;

	public SysSendMail_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(tid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_short();
		tid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			SysSendMail_Re o = (SysSendMail_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		DeliveryDB.SysSendMail_Re( retcode, tid );
	}

}
