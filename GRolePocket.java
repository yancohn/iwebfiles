package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRolePocket extends Rpc.Data
{
	public int	capacity;
	public int	timestamp;
	public int	money;
	public Rpc.Data.DataVector	items;
	public Rpc.Data.DataVector	equipment;
	public Rpc.Data.DataVector	petbadge;
	public Rpc.Data.DataVector	petequip;
	public short	pocket_capacity;
	public Rpc.Data.DataVector	pocket_items;
	public Rpc.Data.DataVector	fashion;
	public Octets	mountwing;
	public Rpc.Data.DataVector	gifts;

	public GRolePocket()
	{
		items = new Rpc.Data.DataVector(new GRoleInventory());
		equipment = new Rpc.Data.DataVector(new GRoleInventory());
		petbadge = new Rpc.Data.DataVector(new GRoleInventory());
		petequip = new Rpc.Data.DataVector(new GRoleInventory());
		pocket_items = new Rpc.Data.DataVector(new GPocketInventory());
		fashion = new Rpc.Data.DataVector(new GRoleInventory());
		mountwing = new Octets();
		gifts = new Rpc.Data.DataVector(new GRoleInventory());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(capacity);
		os.marshal(timestamp);
		os.marshal(money);
		os.marshal(items);
		os.marshal(equipment);
		os.marshal(petbadge);
		os.marshal(petequip);
		os.marshal(pocket_capacity);
		os.marshal(pocket_items);
		os.marshal(fashion);
		os.marshal(mountwing);
		os.marshal(gifts);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		capacity = os.unmarshal_int();
		timestamp = os.unmarshal_int();
		money = os.unmarshal_int();
		os.unmarshal(items);
		os.unmarshal(equipment);
		os.unmarshal(petbadge);
		os.unmarshal(petequip);
		pocket_capacity = os.unmarshal_short();
		os.unmarshal(pocket_items);
		os.unmarshal(fashion);
		os.unmarshal(mountwing);
		os.unmarshal(gifts);
		return os;
	}

	public Object clone()
	{
		try
		{
			GRolePocket o = (GRolePocket)super.clone();
			o.items = (Rpc.Data.DataVector)items.clone();
			o.equipment = (Rpc.Data.DataVector)equipment.clone();
			o.petbadge = (Rpc.Data.DataVector)petbadge.clone();
			o.petequip = (Rpc.Data.DataVector)petequip.clone();
			o.pocket_items = (Rpc.Data.DataVector)pocket_items.clone();
			o.fashion = (Rpc.Data.DataVector)fashion.clone();
			o.mountwing = (Octets)mountwing.clone();
			o.gifts = (Rpc.Data.DataVector)gifts.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
