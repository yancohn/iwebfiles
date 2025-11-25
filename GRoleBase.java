package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.Common.Security.*;
import com.goldhuman.IO.Protocol.*;

public final class GRoleBase extends Rpc.Data
{
	public byte	version;
	public int	id;
	public Octets	name;
	public byte	faceid;
	public byte	hairid;
	public byte	gender;
	public byte	status;
	public int	delete_time;
	public int	create_time;
	public int	lastlogin_time;
	public int	familyid;
	public byte	title;
	public Octets	config_data;
	public Octets	help_states;
	public Rpc.Data.DataVector	forbid;
	public int	spouse;
	public int	jointime;
	public int	userid;
	public int	sectid;
	public short	initiallevel;
	public byte	earid;
	public byte	tailid;
	public Octets	circletrack;
	public byte	fashionid;
	public Rpc.Data.DataVector	datagroup;
	public byte	remote_central_type;

	public GRoleBase()
	{
		name = new Octets();
		config_data = new Octets();
		help_states = new Octets();
		forbid = new Rpc.Data.DataVector(new GRoleForbid());
		circletrack = new Octets();
		datagroup = new Rpc.Data.DataVector(new GPair());
	}

	public OctetsStream marshal(OctetsStream os)
	{
		os.marshal(version);
		os.marshal(id);
		os.marshal(name);
		os.marshal(faceid);
		os.marshal(hairid);
		os.marshal(gender);
		os.marshal(status);
		os.marshal(delete_time);
		os.marshal(create_time);
		os.marshal(lastlogin_time);
		os.marshal(familyid);
		os.marshal(title);
		os.marshal(config_data);
		os.marshal(help_states);
		os.marshal(forbid);
		os.marshal(spouse);
		os.marshal(jointime);
		os.marshal(userid);
		os.marshal(sectid);
		os.marshal(initiallevel);
		os.marshal(earid);
		os.marshal(tailid);
		os.marshal(circletrack);
		os.marshal(fashionid);
		os.marshal(datagroup);
		os.marshal(remote_central_type);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException
	{
		version = os.unmarshal_byte();
		id = os.unmarshal_int();
		os.unmarshal(name);
		faceid = os.unmarshal_byte();
		hairid = os.unmarshal_byte();
		gender = os.unmarshal_byte();
		status = os.unmarshal_byte();
		delete_time = os.unmarshal_int();
		create_time = os.unmarshal_int();
		lastlogin_time = os.unmarshal_int();
		familyid = os.unmarshal_int();
		title = os.unmarshal_byte();
		os.unmarshal(config_data);
		os.unmarshal(help_states);
		os.unmarshal(forbid);
		spouse = os.unmarshal_int();
		jointime = os.unmarshal_int();
		userid = os.unmarshal_int();
		sectid = os.unmarshal_int();
		initiallevel = os.unmarshal_short();
		earid = os.unmarshal_byte();
		tailid = os.unmarshal_byte();
		os.unmarshal(circletrack);
		fashionid = os.unmarshal_byte();
		os.unmarshal(datagroup);
		remote_central_type = os.unmarshal_byte();
		return os;
	}

	public Object clone()
	{
		try
		{
			GRoleBase o = (GRoleBase)super.clone();
			o.name = (Octets)name.clone();
			o.config_data = (Octets)config_data.clone();
			o.help_states = (Octets)help_states.clone();
			o.forbid = (Rpc.Data.DataVector)forbid.clone();
			o.circletrack = (Octets)circletrack.clone();
			o.datagroup = (Rpc.Data.DataVector)datagroup.clone();
			return o;
		}
		catch (Exception e) { }
		return null;
	}

}
