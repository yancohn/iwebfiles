package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GMForbidRole_Re extends Protocol
{
	public int	retcode;
	public byte	fbd_type;
	public int	dstroleid;
	public int	forbid_time;

	public GMForbidRole_Re()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(fbd_type);
		os.marshal(dstroleid);
		os.marshal(forbid_time);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		fbd_type = os.unmarshal_byte();
		dstroleid = os.unmarshal_int();
		forbid_time = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GMForbidRole_Re o = (GMForbidRole_Re)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
		DeliveryDB.GMForbidRole_Re( retcode, fbd_type, dstroleid, forbid_time );
	}

}
