package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RenameRoleArg extends Rpc.Data
{
	public int	roleid;
	public Octets	oldname;
	public Octets	newname;

	public RenameRoleArg()
	{
		oldname = new Octets();
		newname = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(oldname);
		os.marshal(newname);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		os.unmarshal(oldname);
		os.unmarshal(newname);
		return os;
	}

	public Object clone()
	{
		try
		{
			RenameRoleArg o = (RenameRoleArg)super.clone();
			o.oldname = (Octets)oldname.clone();
			o.newname = (Octets)newname.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
