package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleBase2 extends Rpc.Data
{
	public int	id;
	public int	bonus_withdraw;
	public int	bonus_reward;
	public int	bonus_used;
	public long	exp_withdraw_today;
	public int	exp_withdraw_time;
	public Octets	composkills;
	public Octets	tower_raid;
	public short	deity_level;
	public int	data_timestamp;
	public int	src_zoneid;
	public long	deity_exp;
	public int	dp;
	public Octets	littlepet;
	public byte	flag_mask;
	public Octets	ui_transfer;
	public Octets	collision_info;
	public int	runescore;
	public long	comsumption;
	public Octets	astrology_info;
	public Octets	liveness_info;
	public Octets	sale_promotion_info;
	public Octets	propadd;
	public Octets	multi_exp;
	public Octets	fuwen_info;
	public Rpc.Data.DataVector	datagroup;
	public Octets	phase;
	public Octets	award_info_6v6;
	public Octets	hide_and_seek_info;
	public Octets	newyear_award_info;
	public Octets	child_info;
	public Octets	guaji_child_info;
	public Octets	card_info;
	public Octets	challenge_info;
	public byte	reserved15;
	public int	remote_roleid;

	public GRoleBase2()
	{
		composkills = new Octets();
		tower_raid = new Octets();
		littlepet = new Octets();
		ui_transfer = new Octets();
		collision_info = new Octets();
		astrology_info = new Octets();
		liveness_info = new Octets();
		sale_promotion_info = new Octets();
		propadd = new Octets();
		multi_exp = new Octets();
		fuwen_info = new Octets();
		datagroup = new Rpc.Data.DataVector(new GPairLong());
		phase = new Octets();
		award_info_6v6 = new Octets();
		hide_and_seek_info = new Octets();
		newyear_award_info = new Octets();
		child_info = new Octets();
		guaji_child_info = new Octets();
		card_info = new Octets();
		challenge_info = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(id);
		os.marshal(bonus_withdraw);
		os.marshal(bonus_reward);
		os.marshal(bonus_used);
		os.marshal(exp_withdraw_today);
		os.marshal(exp_withdraw_time);
		os.marshal(composkills);
		os.marshal(tower_raid);
		os.marshal(deity_level);
		os.marshal(data_timestamp);
		os.marshal(src_zoneid);
		os.marshal(deity_exp);
		os.marshal(dp);
		os.marshal(littlepet);
		os.marshal(flag_mask);
		os.marshal(ui_transfer);
		os.marshal(collision_info);
		os.marshal(runescore);
		os.marshal(comsumption);
		os.marshal(astrology_info);
		os.marshal(liveness_info);
		os.marshal(sale_promotion_info);
		os.marshal(propadd);
		os.marshal(multi_exp);
		os.marshal(fuwen_info);
		os.marshal(datagroup);
		os.marshal(phase);
		os.marshal(award_info_6v6);
		os.marshal(hide_and_seek_info);
		os.marshal(newyear_award_info);
		os.marshal(child_info);
		os.marshal(guaji_child_info);
		os.marshal(card_info);
		os.marshal(challenge_info);
		os.marshal(reserved15);
		os.marshal(remote_roleid);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		id = os.unmarshal_int();
		bonus_withdraw = os.unmarshal_int();
		bonus_reward = os.unmarshal_int();
		bonus_used = os.unmarshal_int();
		exp_withdraw_today = os.unmarshal_long();
		exp_withdraw_time = os.unmarshal_int();
		os.unmarshal(composkills);
		os.unmarshal(tower_raid);
		deity_level = os.unmarshal_short();
		data_timestamp = os.unmarshal_int();
		src_zoneid = os.unmarshal_int();
		deity_exp = os.unmarshal_long();
		dp = os.unmarshal_int();
		os.unmarshal(littlepet);
		flag_mask = os.unmarshal_byte();
		os.unmarshal(ui_transfer);
		os.unmarshal(collision_info);
		runescore = os.unmarshal_int();
		comsumption = os.unmarshal_long();
		os.unmarshal(astrology_info);
		os.unmarshal(liveness_info);
		os.unmarshal(sale_promotion_info);
		os.unmarshal(propadd);
		os.unmarshal(multi_exp);
		os.unmarshal(fuwen_info);
		os.unmarshal(datagroup);
		os.unmarshal(phase);
		os.unmarshal(award_info_6v6);
		os.unmarshal(hide_and_seek_info);
		os.unmarshal(newyear_award_info);
		os.unmarshal(child_info);
		os.unmarshal(guaji_child_info);
		os.unmarshal(card_info);
		os.unmarshal(challenge_info);
		reserved15 = os.unmarshal_byte();
		remote_roleid = os.unmarshal_int();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleBase2 o = (GRoleBase2)super.clone();
			o.composkills = (Octets)composkills.clone();
			o.tower_raid = (Octets)tower_raid.clone();
			o.littlepet = (Octets)littlepet.clone();
			o.ui_transfer = (Octets)ui_transfer.clone();
			o.collision_info = (Octets)collision_info.clone();
			o.astrology_info = (Octets)astrology_info.clone();
			o.liveness_info = (Octets)liveness_info.clone();
			o.sale_promotion_info = (Octets)sale_promotion_info.clone();
			o.propadd = (Octets)propadd.clone();
			o.multi_exp = (Octets)multi_exp.clone();
			o.fuwen_info = (Octets)fuwen_info.clone();
			o.datagroup = (Rpc.Data.DataVector)datagroup.clone();
			o.phase = (Octets)phase.clone();
			o.award_info_6v6 = (Octets)award_info_6v6.clone();
			o.hide_and_seek_info = (Octets)hide_and_seek_info.clone();
			o.newyear_award_info = (Octets)newyear_award_info.clone();
			o.child_info = (Octets)child_info.clone();
			o.guaji_child_info = (Octets)guaji_child_info.clone();
			o.card_info = (Octets)card_info.clone();
			o.challenge_info = (Octets)challenge_info.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
