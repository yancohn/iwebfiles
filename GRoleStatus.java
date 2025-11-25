package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleStatus extends Rpc.Data
{
	public byte	version;
	public int	id;
	public byte	occupation;
	public short	level;
	public short	cur_title;
	public long	exp;
	public int	pp;
	public int	hp;
	public int	mp;
	public float	posx;
	public float	posy;
	public float	posz;
	public int	pkvalue;
	public int	worldtag;
	public int	time_used;
	public int	reputation;
	public int	produceskill;
	public int	produceexp;
	public Octets	custom_status;
	public Octets	filter_data;
	public Octets	charactermode;
	public Octets	instancekeylist;
	public Octets	dbltime_data;
	public Octets	petcorral;
	public Octets	var_data;
	public Octets	skills;
	public Octets	storehousepasswd;
	public Octets	coolingtime;
	public Octets	recipes;
	public Octets	waypointlist;
	public Octets	credit;
	public Octets	titlelist;
	public int	contribution;
	public int	combatkills;
	public int	devotion;
	public int	talismanscore;
	public int	updatetime;
	public int	battlescore;
	public Octets	petdata;
	public Octets	reborndata;
	public short	cultivation;
	public int	reserved1;
	public Octets	fashion_hotkey;
	public Octets	raid_data;
	public Octets	five_year;
	public Octets	treasure_info;

	public GRoleStatus()
	{
		custom_status = new Octets();
		filter_data = new Octets();
		charactermode = new Octets();
		instancekeylist = new Octets();
		dbltime_data = new Octets();
		petcorral = new Octets();
		var_data = new Octets();
		skills = new Octets();
		storehousepasswd = new Octets();
		coolingtime = new Octets();
		recipes = new Octets();
		waypointlist = new Octets();
		credit = new Octets();
		titlelist = new Octets();
		petdata = new Octets();
		reborndata = new Octets();
		fashion_hotkey = new Octets();
		raid_data = new Octets();
		five_year = new Octets();
		treasure_info = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(version);
		os.marshal(id);
		os.marshal(occupation);
		os.marshal(level);
		os.marshal(cur_title);
		os.marshal(exp);
		os.marshal(pp);
		os.marshal(hp);
		os.marshal(mp);
		os.marshal(posx);
		os.marshal(posy);
		os.marshal(posz);
		os.marshal(pkvalue);
		os.marshal(worldtag);
		os.marshal(time_used);
		os.marshal(reputation);
		os.marshal(produceskill);
		os.marshal(produceexp);
		os.marshal(custom_status);
		os.marshal(filter_data);
		os.marshal(charactermode);
		os.marshal(instancekeylist);
		os.marshal(dbltime_data);
		os.marshal(petcorral);
		os.marshal(var_data);
		os.marshal(skills);
		os.marshal(storehousepasswd);
		os.marshal(coolingtime);
		os.marshal(recipes);
		os.marshal(waypointlist);
		os.marshal(credit);
		os.marshal(titlelist);
		os.marshal(contribution);
		os.marshal(combatkills);
		os.marshal(devotion);
		os.marshal(talismanscore);
		os.marshal(updatetime);
		os.marshal(battlescore);
		os.marshal(petdata);
		os.marshal(reborndata);
		os.marshal(cultivation);
		os.marshal(reserved1);
		os.marshal(fashion_hotkey);
		os.marshal(raid_data);
		os.marshal(five_year);
		os.marshal(treasure_info);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		version = os.unmarshal_byte();
		id = os.unmarshal_int();
		occupation = os.unmarshal_byte();
		level = os.unmarshal_short();
		cur_title = os.unmarshal_short();
		exp = os.unmarshal_long();
		pp = os.unmarshal_int();
		hp = os.unmarshal_int();
		mp = os.unmarshal_int();
		posx = os.unmarshal_float();
		posy = os.unmarshal_float();
		posz = os.unmarshal_float();
		pkvalue = os.unmarshal_int();
		worldtag = os.unmarshal_int();
		time_used = os.unmarshal_int();
		reputation = os.unmarshal_int();
		produceskill = os.unmarshal_int();
		produceexp = os.unmarshal_int();
		os.unmarshal(custom_status);
		os.unmarshal(filter_data);
		os.unmarshal(charactermode);
		os.unmarshal(instancekeylist);
		os.unmarshal(dbltime_data);
		os.unmarshal(petcorral);
		os.unmarshal(var_data);
		os.unmarshal(skills);
		os.unmarshal(storehousepasswd);
		os.unmarshal(coolingtime);
		os.unmarshal(recipes);
		os.unmarshal(waypointlist);
		os.unmarshal(credit);
		os.unmarshal(titlelist);
		contribution = os.unmarshal_int();
		combatkills = os.unmarshal_int();
		devotion = os.unmarshal_int();
		talismanscore = os.unmarshal_int();
		updatetime = os.unmarshal_int();
		battlescore = os.unmarshal_int();
		os.unmarshal(petdata);
		os.unmarshal(reborndata);
		cultivation = os.unmarshal_short();
		reserved1 = os.unmarshal_int();
		os.unmarshal(fashion_hotkey);
		os.unmarshal(raid_data);
		os.unmarshal(five_year);
		os.unmarshal(treasure_info);
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleStatus o = (GRoleStatus)super.clone();
			o.custom_status = (Octets)custom_status.clone();
			o.filter_data = (Octets)filter_data.clone();
			o.charactermode = (Octets)charactermode.clone();
			o.instancekeylist = (Octets)instancekeylist.clone();
			o.dbltime_data = (Octets)dbltime_data.clone();
			o.petcorral = (Octets)petcorral.clone();
			o.var_data = (Octets)var_data.clone();
			o.skills = (Octets)skills.clone();
			o.storehousepasswd = (Octets)storehousepasswd.clone();
			o.coolingtime = (Octets)coolingtime.clone();
			o.recipes = (Octets)recipes.clone();
			o.waypointlist = (Octets)waypointlist.clone();
			o.credit = (Octets)credit.clone();
			o.titlelist = (Octets)titlelist.clone();
			o.petdata = (Octets)petdata.clone();
			o.reborndata = (Octets)reborndata.clone();
			o.fashion_hotkey = (Octets)fashion_hotkey.clone();
			o.raid_data = (Octets)raid_data.clone();
			o.five_year = (Octets)five_year.clone();
			o.treasure_info = (Octets)treasure_info.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
