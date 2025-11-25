package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GQueryPasswdRes extends Rpc.Data
{
	public int	retcode;
	public int	userid;
	public Octets	response;

	public GQueryPasswdRes()
	{
		response = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(retcode);
		os.marshal(userid);
		os.marshal(response);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		retcode = os.unmarshal_int();
		userid = os.unmarshal_int();
		os.unmarshal(response);
		return os;
	}

	public Object clone()
	{
		try
		{
			GQueryPasswdRes o = (GQueryPasswdRes)super.clone();
			o.response = (Octets)response.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
