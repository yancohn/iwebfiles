package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GShopSetSaleScheme extends Protocol
{
	public int	scheme;

	public GShopSetSaleScheme()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(scheme);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		scheme = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GShopSetSaleScheme o = (GShopSetSaleScheme)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

	public void Process(Manager manager, Session session) throws ProtocolException
	{
	}

}
