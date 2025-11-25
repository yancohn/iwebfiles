package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class ClearStorehousePasswdArg extends Rpc.Data
{
	public int	roleid;
	public Octets	rolename;
	public Octets	reserved;

	public ClearStorehousePasswdArg()
	{
		rolename = new Octets();
		reserved = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(rolename);
		os.marshal(reserved);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		os.unmarshal(rolename);
		os.unmarshal(reserved);
		return os;
	}

	public Object clone()
	{
		try
		{
			ClearStorehousePasswdArg o = (ClearStorehousePasswdArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			o.reserved = (Octets)reserved.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
