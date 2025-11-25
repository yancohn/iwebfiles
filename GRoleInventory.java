package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleInventory extends Rpc.Data
{
	public int	id;
	public int	pos;
	public int	count;
	public short	client_size;
	public short	max_count;
	public Octets	data;
	public int	proctype;
	public int	expire_date;
	public int	guid1;
	public int	guid2;

	public GRoleInventory()
	{
		data = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(id);
		os.marshal(pos);
		os.marshal(count);
		os.marshal(client_size);
		os.marshal(max_count);
		os.marshal(data);
		os.marshal(proctype);
		os.marshal(expire_date);
		os.marshal(guid1);
		os.marshal(guid2);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		id = os.unmarshal_int();
		pos = os.unmarshal_int();
		count = os.unmarshal_int();
		client_size = os.unmarshal_short();
		max_count = os.unmarshal_short();
		os.unmarshal(data);
		proctype = os.unmarshal_int();
		expire_date = os.unmarshal_int();
		guid1 = os.unmarshal_int();
		guid2 = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleInventory o = (GRoleInventory)super.clone();
			o.data = (Octets)data.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
