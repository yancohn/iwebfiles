package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GShopScheme extends Rpc.Data
{
	public int	discount;
	public int	sale;

	public GShopScheme()
	{
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(discount);
		os.marshal(sale);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		discount = os.unmarshal_int();
		sale = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GShopScheme o = (GShopScheme)super.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
