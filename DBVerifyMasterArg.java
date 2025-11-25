package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class DBVerifyMasterArg extends Rpc.Data
{
	public Octets	name;
	public Octets	faction;

	public DBVerifyMasterArg()
	{
		name = new Octets();
		faction = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(name);
		os.marshal(faction);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(name);
		os.unmarshal(faction);
		return os;
	}

	public Object clone()
	{
		try
		{
			DBVerifyMasterArg o = (DBVerifyMasterArg)super.clone();
			o.name = (Octets)name.clone();
			o.faction = (Octets)faction.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
