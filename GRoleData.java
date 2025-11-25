package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleData extends Rpc.Data
{
	public GRoleBase	base;
	public GRoleStatus	status;
	public GRolePocket	pocket;
	public GRoleStorehouse	storehouse;
	public GRoleTask	task;
	public GRoleBase2	base2;
	public GRoleAchievement	achievement;

	public GRoleData()
	{
		base = new GRoleBase();
		status = new GRoleStatus();
		pocket = new GRolePocket();
		storehouse = new GRoleStorehouse();
		task = new GRoleTask();
		base2 = new GRoleBase2();
		achievement = new GRoleAchievement();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(base);
		os.marshal(status);
		os.marshal(pocket);
		os.marshal(storehouse);
		os.marshal(task);
		os.marshal(base2);
		os.marshal(achievement);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		os.unmarshal(base);
		os.unmarshal(status);
		os.unmarshal(pocket);
		os.unmarshal(storehouse);
		os.unmarshal(task);
		os.unmarshal(base2);
		os.unmarshal(achievement);
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleData o = (GRoleData)super.clone();
			o.base = (GRoleBase)base.clone();
			o.status = (GRoleStatus)status.clone();
			o.pocket = (GRolePocket)pocket.clone();
			o.storehouse = (GRoleStorehouse)storehouse.clone();
			o.task = (GRoleTask)task.clone();
			o.base2 = (GRoleBase2)base2.clone();
			o.achievement = (GRoleAchievement)achievement.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
