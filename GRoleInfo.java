package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleInfo extends Rpc.Data
{
	public byte	status;
	public int	uptime;
	public int	id;
	public int	userid;
	public Octets	name;
	public byte	faceid;
	public byte	hairid;
	public byte	earid;
	public byte	tailid;
	public byte	gender;
	public byte	occupation;
	public int	level;
	public int	spouse;
	public int	sectid;
	public int	familyid;
	public byte	title;
	public float	posx;
	public float	posy;
	public float	posz;
	public int	worldtag;
	public Octets	custom_status;
	public Octets	charactermode;
	public Rpc.Data.DataVector	equipment;
	public byte	delete_flag;
	public byte	remote_central_type;
	public int	remote_roleid;
	public int	create_time;
	public int	delete_time;
	public int	lastlogin_time;
	public Rpc.Data.DataVector	forbid;
	public Octets	config_data;
	public Octets	help_states;
	public Octets	reborndata;
	public int	reborn_cnt;
	public short	cultivation;
	public int	data_timestamp;
	public int	src_zoneid;
	public byte	fashionid;
	public int	ct_leave_cool;
	public int	ct_teamid;
	public int	ct_score;
	public int	ct_last_max_score;
	public int	ct_last_punish_time;
	public int	ct_last_battle_count;
	public int	ct_last_jointeam_time;
	public long	fac_coupon_add;

	public GRoleInfo()
	{
		name = new Octets();
		custom_status = new Octets();
		charactermode = new Octets();
		equipment = new Rpc.Data.DataVector(new GRoleInventory());
		forbid = new Rpc.Data.DataVector(new GRoleForbid());
		config_data = new Octets();
		help_states = new Octets();
		reborndata = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(status);
		os.marshal(uptime);
		os.marshal(id);
		os.marshal(userid);
		os.marshal(name);
		os.marshal(faceid);
		os.marshal(hairid);
		os.marshal(earid);
		os.marshal(tailid);
		os.marshal(gender);
		os.marshal(occupation);
		os.marshal(level);
		os.marshal(spouse);
		os.marshal(sectid);
		os.marshal(familyid);
		os.marshal(title);
		os.marshal(posx);
		os.marshal(posy);
		os.marshal(posz);
		os.marshal(worldtag);
		os.marshal(custom_status);
		os.marshal(charactermode);
		os.marshal(equipment);
		os.marshal(delete_flag);
		os.marshal(remote_central_type);
		os.marshal(remote_roleid);
		os.marshal(create_time);
		os.marshal(delete_time);
		os.marshal(lastlogin_time);
		os.marshal(forbid);
		os.marshal(config_data);
		os.marshal(help_states);
		os.marshal(reborndata);
		os.marshal(reborn_cnt);
		os.marshal(cultivation);
		os.marshal(data_timestamp);
		os.marshal(src_zoneid);
		os.marshal(fashionid);
		os.marshal(ct_leave_cool);
		os.marshal(ct_teamid);
		os.marshal(ct_score);
		os.marshal(ct_last_max_score);
		os.marshal(ct_last_punish_time);
		os.marshal(ct_last_battle_count);
		os.marshal(ct_last_jointeam_time);
		os.marshal(fac_coupon_add);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		status = os.unmarshal_byte();
		uptime = os.unmarshal_int();
		id = os.unmarshal_int();
		userid = os.unmarshal_int();
		os.unmarshal(name);
		faceid = os.unmarshal_byte();
		hairid = os.unmarshal_byte();
		earid = os.unmarshal_byte();
		tailid = os.unmarshal_byte();
		gender = os.unmarshal_byte();
		occupation = os.unmarshal_byte();
		level = os.unmarshal_int();
		spouse = os.unmarshal_int();
		sectid = os.unmarshal_int();
		familyid = os.unmarshal_int();
		title = os.unmarshal_byte();
		posx = os.unmarshal_float();
		posy = os.unmarshal_float();
		posz = os.unmarshal_float();
		worldtag = os.unmarshal_int();
		os.unmarshal(custom_status);
		os.unmarshal(charactermode);
		os.unmarshal(equipment);
		delete_flag = os.unmarshal_byte();
		remote_central_type = os.unmarshal_byte();
		remote_roleid = os.unmarshal_int();
		create_time = os.unmarshal_int();
		delete_time = os.unmarshal_int();
		lastlogin_time = os.unmarshal_int();
		os.unmarshal(forbid);
		os.unmarshal(config_data);
		os.unmarshal(help_states);
		os.unmarshal(reborndata);
		reborn_cnt = os.unmarshal_int();
		cultivation = os.unmarshal_short();
		data_timestamp = os.unmarshal_int();
		src_zoneid = os.unmarshal_int();
		fashionid = os.unmarshal_byte();
		ct_leave_cool = os.unmarshal_int();
		ct_teamid = os.unmarshal_int();
		ct_score = os.unmarshal_int();
		ct_last_max_score = os.unmarshal_int();
		ct_last_punish_time = os.unmarshal_int();
		ct_last_battle_count = os.unmarshal_int();
		ct_last_jointeam_time = os.unmarshal_int();
		fac_coupon_add = os.unmarshal_long();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleInfo o = (GRoleInfo)super.clone();
			o.name = (Octets)name.clone();
			o.custom_status = (Octets)custom_status.clone();
			o.charactermode = (Octets)charactermode.clone();
			o.equipment = (Rpc.Data.DataVector)equipment.clone();
			o.forbid = (Rpc.Data.DataVector)forbid.clone();
			o.config_data = (Octets)config_data.clone();
			o.help_states = (Octets)help_states.clone();
			o.reborndata = (Octets)reborndata.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
