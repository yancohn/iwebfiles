package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class UserPair extends Rpc.Data
{
	public UserID	key;
	public User	value;

	public UserPair()
	{
		key = new UserID();
		value = new User();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(key);
		os.marshal(value);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(key);
		os.unmarshal(value);
		return os;
	}

	public Object clone()
	{
		try
		{
			UserPair o = (UserPair)super.clone();
			o.key = (UserID)key.clone();
			o.value = (User)value.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
