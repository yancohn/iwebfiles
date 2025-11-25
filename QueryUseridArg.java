package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class QueryUseridArg extends Rpc.Data
{
	public Octets	rolename;

	public QueryUseridArg()
	{
		rolename = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(rolename);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(rolename);
		return os;
	}

	public Object clone()
	{
		try
		{
			QueryUseridArg o = (QueryUseridArg)super.clone();
			o.rolename = (Octets)rolename.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
