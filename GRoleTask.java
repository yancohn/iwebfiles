package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleTask extends Rpc.Data
{
	public Octets	task_data;
	public Octets	task_complete;
	public Octets	task_finishtime;
	public DataVector	task_inventory;

	public GRoleTask()
	{
		task_data = new Octets();
		task_complete = new Octets();
		task_finishtime = new Octets();
		task_inventory = new DataVector(new GRoleInventory());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(task_data);
		os.marshal(task_complete);
		os.marshal(task_finishtime);
		os.marshal(task_inventory);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(task_data);
		os.unmarshal(task_complete);
		os.unmarshal(task_finishtime);
		os.unmarshal(task_inventory);
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleTask o = (GRoleTask)super.clone();
			o.task_data = (Octets)task_data.clone();
			o.task_complete = (Octets)task_complete.clone();
			o.task_finishtime = (Octets)task_finishtime.clone();
			o.task_inventory = (DataVector)task_inventory.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
