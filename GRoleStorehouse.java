package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleStorehouse extends Rpc.Data
{
	public int	capacity;
	public int	money;
	public Rpc.Data.DataVector	items;
	public byte	capacity2;
	public Rpc.Data.DataVector	items2;
	public Rpc.Data.DataVector	fuwen;
	public Rpc.Data.DataVector	card;
	public Rpc.Data.DataVector	card_matrix;
	public byte	reserved1;
	public int	reserved2;

	public GRoleStorehouse()
	{
		items = new Rpc.Data.DataVector(new GRoleInventory());
		items2 = new Rpc.Data.DataVector(new GRoleInventory());
		fuwen = new Rpc.Data.DataVector(new GRoleInventory());
		card = new Rpc.Data.DataVector(new GRoleInventory());
		card_matrix = new Rpc.Data.DataVector(new GRoleInventory());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(capacity);
		os.marshal(money);
		os.marshal(items);
		os.marshal(capacity2);
		os.marshal(items2);
		os.marshal(fuwen);
		os.marshal(card);
		os.marshal(card_matrix);
		os.marshal(reserved1);
		os.marshal(reserved2);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		capacity = os.unmarshal_int();
		money = os.unmarshal_int();
		os.unmarshal(items);
		capacity2 = os.unmarshal_byte();
		os.unmarshal(items2);
		os.unmarshal(fuwen);
		os.unmarshal(card);
		os.unmarshal(card_matrix);
		reserved1 = os.unmarshal_byte();
		reserved2 = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleStorehouse o = (GRoleStorehouse)super.clone();
			o.items = (Rpc.Data.DataVector)items.clone();
			o.items2 = (Rpc.Data.DataVector)items2.clone();
			o.fuwen = (Rpc.Data.DataVector)fuwen.clone();
			o.card = (Rpc.Data.DataVector)card.clone();
			o.card_matrix = (Rpc.Data.DataVector)card_matrix.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
