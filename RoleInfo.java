package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class RoleInfo extends Rpc.Data
{
	public int	roleid;
	public byte	gender;
	public byte	faceid;
	public byte	hairid;
	public byte	earid;
	public byte	tailid;
	public byte	occupation;
	public int	level;
	public Octets	name;
	public Rpc.Data.DataVector	equipment;
	public byte	status;
	public int	delete_time;
	public int	create_time;
	public int	lastlogin_time;
	public float	posx;
	public float	posy;
	public float	posz;
	public int	worldtag;
	public Octets	custom_status;
	public Octets	charactermode;
	public int	src_zoneid;
	public byte	fashionid;
	public short	cultivation;

	public RoleInfo()
	{
		name = new Octets();
		equipment = new Rpc.Data.DataVector(new GRoleInventory());
		custom_status = new Octets();
		charactermode = new Octets();
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(roleid);
		os.marshal(gender);
		os.marshal(faceid);
		os.marshal(hairid);
		os.marshal(earid);
		os.marshal(tailid);
		os.marshal(occupation);
		os.marshal(level);
		os.marshal(name);
		os.marshal(equipment);
		os.marshal(status);
		os.marshal(delete_time);
		os.marshal(create_time);
		os.marshal(lastlogin_time);
		os.marshal(posx);
		os.marshal(posy);
		os.marshal(posz);
		os.marshal(worldtag);
		os.marshal(custom_status);
		os.marshal(charactermode);
		os.marshal(src_zoneid);
		os.marshal(fashionid);
		os.marshal(cultivation);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		roleid = os.unmarshal_int();
		gender = os.unmarshal_byte();
		faceid = os.unmarshal_byte();
		hairid = os.unmarshal_byte();
		earid = os.unmarshal_byte();
		tailid = os.unmarshal_byte();
		occupation = os.unmarshal_byte();
		level = os.unmarshal_int();
		os.unmarshal(name);
		os.unmarshal(equipment);
		status = os.unmarshal_byte();
		delete_time = os.unmarshal_int();
		create_time = os.unmarshal_int();
		lastlogin_time = os.unmarshal_int();
		posx = os.unmarshal_float();
		posy = os.unmarshal_float();
		posz = os.unmarshal_float();
		worldtag = os.unmarshal_int();
		os.unmarshal(custom_status);
		os.unmarshal(charactermode);
		src_zoneid = os.unmarshal_int();
		fashionid = os.unmarshal_byte();
		cultivation = os.unmarshal_short();
		return os;
	}

	public Object clone()
	{
		try
		{
			RoleInfo o = (RoleInfo)super.clone();
			o.name = (Octets)name.clone();
			o.equipment = (Rpc.Data.DataVector)equipment.clone();
			o.custom_status = (Octets)custom_status.clone();
			o.charactermode = (Octets)charactermode.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
