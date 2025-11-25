package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleForbid extends Rpc.Data
{
	public byte	type;
	public int	time;
	public int	createtime;
	public Octets	reason;

	public GRoleForbid()
	{
		reason = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(type);
		os.marshal(time);
		os.marshal(createtime);
		os.marshal(reason);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		type = os.unmarshal_byte();
		time = os.unmarshal_int();
		createtime = os.unmarshal_int();
		os.unmarshal(reason);
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleForbid o = (GRoleForbid)super.clone();
			o.reason = (Octets)reason.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
