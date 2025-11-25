package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class User extends Rpc.Data
{
	public int	logicuid;
	public int	rolelist;
	public int	cash;
	public int	money;
	public int	cash_add;
	public int	cash_buy;
	public int	cash_sell;
	public int	cash_used;
	public int	add_serial;
	public int	use_serial;
	public Rpc.Data.DataVector	exg_log;
	public Octets	cash_password;
	public Rpc.Data.DataVector	autolock;
	public byte	status;
	public Rpc.Data.DataVector	forbid;
	public int	referrer;
	public byte	flag;
	public int	lastlogin_ip;
	public Rpc.Data.DataVector	data_group;
	public Rpc.Data.DataVector	unprocessed_orders;
	public short	reserved3;

	public User()
	{
		exg_log = new Rpc.Data.DataVector(new StockLog());
		cash_password = new Octets();
		autolock = new Rpc.Data.DataVector(new GPair());
		forbid = new Rpc.Data.DataVector(new GRoleForbid());
		data_group = new Rpc.Data.DataVector(new GPair());
		unprocessed_orders = new Rpc.Data.DataVector(new LongData());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(logicuid);
		os.marshal(rolelist);
		os.marshal(cash);
		os.marshal(money);
		os.marshal(cash_add);
		os.marshal(cash_buy);
		os.marshal(cash_sell);
		os.marshal(cash_used);
		os.marshal(add_serial);
		os.marshal(use_serial);
		os.marshal(exg_log);
		os.marshal(cash_password);
		os.marshal(autolock);
		os.marshal(status);
		os.marshal(forbid);
		os.marshal(referrer);
		os.marshal(flag);
		os.marshal(lastlogin_ip);
		os.marshal(data_group);
		os.marshal(unprocessed_orders);
		os.marshal(reserved3);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		logicuid = os.unmarshal_int();
		rolelist = os.unmarshal_int();
		cash = os.unmarshal_int();
		money = os.unmarshal_int();
		cash_add = os.unmarshal_int();
		cash_buy = os.unmarshal_int();
		cash_sell = os.unmarshal_int();
		cash_used = os.unmarshal_int();
		add_serial = os.unmarshal_int();
		use_serial = os.unmarshal_int();
		os.unmarshal(exg_log);
		os.unmarshal(cash_password);
		os.unmarshal(autolock);
		status = os.unmarshal_byte();
		os.unmarshal(forbid);
		referrer = os.unmarshal_int();
		flag = os.unmarshal_byte();
		lastlogin_ip = os.unmarshal_int();
		os.unmarshal(data_group);
		os.unmarshal(unprocessed_orders);
		reserved3 = os.unmarshal_short();
		return os;
	}

	public Object clone()
	{
		try
		{
			User o = (User)super.clone();
			o.exg_log = (Rpc.Data.DataVector)exg_log.clone();
			o.cash_password = (Octets)cash_password.clone();
			o.autolock = (Rpc.Data.DataVector)autolock.clone();
			o.forbid = (Rpc.Data.DataVector)forbid.clone();
			o.data_group = (Rpc.Data.DataVector)data_group.clone();
			o.unprocessed_orders = (Rpc.Data.DataVector)unprocessed_orders.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
