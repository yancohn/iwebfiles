package protocol;

import com.goldhuman.Common.*;
import com.goldhuman.Common.Marshal.*;
import com.goldhuman.IO.Protocol.*;

import com.goldhuman.service.GMServiceImpl;
import com.goldhuman.service.interfaces.Complain;
import com.goldhuman.service.interfaces.GMService;
import com.goldhuman.service.interfaces.LogInfo;
import com.goldhuman.util.MySqlCon;
import com.goldhuman.util.LocalDB;

public final class Complain2GM extends Protocol 
{
	public int roleid;

	public int localsid;

	public Octets rolename;

	public Octets comp_rolename;

	public int zoneid;

	public Octets mapzone;

	public float posx;

	public float posy;

	public float posz;

	public Octets content;

	public Complain2GM() {
		rolename = new Octets();
		comp_rolename = new Octets();
		mapzone = new Octets();
		content = new Octets();
	}

	public OctetsStream marshal(OctetsStream os) {
		os.marshal(roleid);
		os.marshal(localsid);
		os.marshal(rolename);
		os.marshal(comp_rolename);
		os.marshal(zoneid);
		os.marshal(mapzone);
		os.marshal(posx);
		os.marshal(posy);
		os.marshal(posz);
		os.marshal(content);
		return os;
	}

	public OctetsStream unmarshal(OctetsStream os) throws MarshalException {
		roleid = os.unmarshal_int();
		localsid = os.unmarshal_int();
		os.unmarshal(rolename);
		os.unmarshal(comp_rolename);
		zoneid = os.unmarshal_int();
		os.unmarshal(mapzone);
		posx = os.unmarshal_float();
		posy = os.unmarshal_float();
		posz = os.unmarshal_float();
		os.unmarshal(content);
		return os;
	}

	public Object clone() {
		try {
			Complain2GM o = (Complain2GM) super.clone();
			o.rolename = (Octets) rolename.clone();
			o.comp_rolename = (Octets) comp_rolename.clone();
			o.mapzone = (Octets) mapzone.clone();
			o.content = (Octets) content.clone();
			return o;
		} catch (Exception e) {
		}
		return null;
	}

	public void Process(Manager manager, Session session)
			throws ProtocolException {
		Complain com = new Complain();
		com.roleid = roleid;
		com.zoneid = zoneid;
		try {
			com.rolename = rolename.getString();
			com.mapzone = mapzone.getString();
			byte[] tbytes = content.getBytes();
			com.com_type = new byte[4];
			com.com_type[0] = tbytes[0];
			com.com_type[1] = tbytes[1];
			com.com_type[2] = tbytes[2];
			com.com_type[3] = tbytes[3];
			com.content = new Octets(tbytes, 4, tbytes.length - 4).getString();
			System.out.println("=====================Complain2GM set content "
					+ com.content + "bs size " + com.com_type.length);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			com.com_rolename = comp_rolename.getString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		com.x = posx;
		com.y = posy;
		com.z = posz;
		String flag = Conf.GetInstance().find("public", "savecomplaintodb");
		if (flag.equals("true")) {
			// 把取到的数据放到数据库里
			GMService gs = new GMServiceImpl();
			LogInfo info = new LogInfo(-1, "", "投诉存放到数据库中!");
			Complain[] result = gs.fetchComplains(info);
			Complain complain = null;
			MySqlCon my = new MySqlCon();
			if (result != null) {
				String[] sqls = new String[result.length];
				for (int i = 0; i < result.length; i++) {
					complain = result[i];
					String sql = "insert into complains(comtype,roleid,rolename,comroleid,comrolename,zoneid,mapzone,x,y,z,content) values(";
					sql += "'" + complain.com_type + "',";
					sql += complain.roleid + ",";
					sql += "'" + complain.rolename + "',";
					sql += complain.com_roleid + ",";
					sql += "'" + complain.com_rolename + "',";
					sql += complain.zoneid + ",";
					sql += "'" + complain.mapzone + "',";
					sql += complain.x + ",";
					sql += complain.y + ",";
					sql += complain.z + ",";
					sql += "'" + complain.content + "')";
					sqls[i] = sql;

				}
				my.updateBatch(sqls);
				my.close();
			}// end if

		} else {
			// 把数据放到内存中
			LocalDB.getInstance(1000,10000,7200,false).addComplain(com);
		}
	}

}
