package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class ForbidUserArg extends Rpc.Data
{
	public byte	operation;
	public int	gmuserid;
	public int	source;
	public int	userid;
	public int	time;
	public Octets	reason;
	public int	ac_score;

	public ForbidUserArg()
	{
		reason = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(operation);
		os.marshal(gmuserid);
		os.marshal(source);
		os.marshal(userid);
		os.marshal(time);
		os.marshal(reason);
		os.marshal(ac_score);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		operation = os.unmarshal_byte();
		gmuserid = os.unmarshal_int();
		source = os.unmarshal_int();
		userid = os.unmarshal_int();
		time = os.unmarshal_int();
		os.unmarshal(reason);
		ac_score = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			ForbidUserArg o = (ForbidUserArg)super.clone();
			o.reason = (Octets)reason.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
