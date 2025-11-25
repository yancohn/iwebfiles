package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleBag extends Rpc.Data
{
	public int	capacity;
	public int	timestamp;
	public int	money;
	public DataVector	items;
	public DataVector	equipment;
	public int	reserved1;
	public int	reserved2;

	public GRoleBag()
	{
		items = new DataVector(new GRoleInventory());
		equipment = new DataVector(new GRoleInventory());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(capacity);
		os.marshal(timestamp);
		os.marshal(money);
		os.marshal(items);
		os.marshal(equipment);
		os.marshal(reserved1);
		os.marshal(reserved2);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		capacity = os.unmarshal_int();
		timestamp = os.unmarshal_int();
		money = os.unmarshal_int();
		os.unmarshal(items);
		os.unmarshal(equipment);
		reserved1 = os.unmarshal_int();
		reserved2 = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleBag o = (GRoleBag)super.clone();
			o.items = (DataVector)items.clone();
			o.equipment = (DataVector)equipment.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
