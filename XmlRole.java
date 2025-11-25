//CREATE TIME: Wed May 21 11:35:02 CST 2014
//CREATE BY: iweb/tools/src/xmlrole/GenXmlRole.java
package protocol;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Random;
import org.apache.commons.lang.StringEscapeUtils;
import com.goldhuman.IO.*;
import com.goldhuman.Common.*;
import com.goldhuman.IO.Protocol.*;
import com.goldhuman.IO.Protocol.Rpc.Data.DataVector;
import com.goldhuman.Common.Security.*;
import protocol.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
public class XmlRole extends XmlRoleBase
{

	static public byte [] toXMLByteArray( Role role ) throws ParserConfigurationException, Exception, TransformerConfigurationException
	{
		Document doc = toXML( role );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.transform( new DOMSource(doc), new StreamResult(baos));
		return baos.toByteArray();
	}

	static public Role fromXML( InputStream inputStream ) throws XmlRoleException, UnsupportedEncodingException, ParserConfigurationException, TransformerConfigurationException, TransformerException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.transform( new StreamSource(inputStream), new DOMResult(doc));
		return fromXML( doc );
	}

	static public Role fromXML( byte [] byteArray ) throws XmlRoleException, UnsupportedEncodingException, ParserConfigurationException, TransformerConfigurationException, TransformerException
	{
		return fromXML( new ByteArrayInputStream( byteArray ) );
	}

	static public Role fromXML( File file ) throws XmlRoleException, UnsupportedEncodingException, ParserConfigurationException, TransformerConfigurationException, TransformerException, FileNotFoundException
	{
		return fromXML( new FileInputStream(file) );
	}

	static public void toXMLFile( Role role, File file ) throws ParserConfigurationException, Exception, TransformerConfigurationException
	{
		Document doc = toXML( role );
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.transform( new DOMSource(doc), new StreamResult(new FileOutputStream(file)));
	}

	static public Role fromXML( Document doc ) throws XmlRoleException, UnsupportedEncodingException
	{
		Role role = new XmlRole.Role();
		Element root = doc.getDocumentElement();
		NodeList list = root.getChildNodes();
		for( int i=0; i<list.getLength(); i++ )
		{
			Node node = list.item(i);
			String name = node.getNodeName();
			if (name.equals("#text"))
			continue;
			else if (name.equals("achievement"))
				role.achievement = getGRoleAchievement((Element)node, "achievement");
			else if (name.equals("base"))
				role.base = getGRoleBase((Element)node, "base");
			else if (name.equals("base2"))
				role.base2 = getGRoleBase2((Element)node, "base2");
			else if (name.equals("pocket"))
				role.pocket = getGRolePocket((Element)node, "pocket");
			else if (name.equals("status"))
				role.status = getGRoleStatus((Element)node, "status");
			else if (name.equals("storehouse"))
				role.storehouse = getGRoleStorehouse((Element)node, "storehouse");
			else if (name.equals("task"))
				role.task = getGRoleTask((Element)node, "task");
			else
				throw new XmlRoleException( "fromXML(Document). list.size=" + Integer.toString(list.getLength()) + ", node name=" + name );
		}
		return 0 == list.getLength() ? null : role;
	}
	static public class Role
	{
		public GRoleAchievement	achievement;
		public GRoleBase	base;
		public GRoleBase2	base2;
		public GRolePocket	pocket;
		public GRoleStatus	status;
		public GRoleStorehouse	storehouse;
		public GRoleTask	task;
		public Role(){}
	}
	static private GPair getGPair(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GPair gpair = new GPair();
		gpair.key = Integer.parseInt(getElementValue(currentnodelist, "key", parentName));
		gpair.value = Integer.parseInt(getElementValue(currentnodelist, "value", parentName));
		return gpair;
	}

	static private GPairLong getGPairLong(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GPairLong gpairlong = new GPairLong();
		gpairlong.key = Integer.parseInt(getElementValue(currentnodelist, "key", parentName));
		gpairlong.value = Long.parseLong(getElementValue(currentnodelist, "value", parentName));
		return gpairlong;
	}

	static private GPocketInventory getGPocketInventory(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GPocketInventory gpocketinventory = new GPocketInventory();
		gpocketinventory.count = Short.parseShort(getElementValue(currentnodelist, "count", parentName));
		gpocketinventory.id = Integer.parseInt(getElementValue(currentnodelist, "id", parentName));
		gpocketinventory.pos = Short.parseShort(getElementValue(currentnodelist, "pos", parentName));
		return gpocketinventory;
	}

	static private GRoleAchievement getGRoleAchievement(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleAchievement groleachievement = new GRoleAchievement();
		groleachievement.achieve_active = new Octets(hextoByteArray(getElementValue(currentnodelist, "achieve_active", parentName)));
		groleachievement.achieve_award_map = new Octets(hextoByteArray(getElementValue(currentnodelist, "achieve_award_map", parentName)));
		groleachievement.achieve_map = new Octets(hextoByteArray(getElementValue(currentnodelist, "achieve_map", parentName)));
		groleachievement.achieve_spec_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "achieve_spec_info", parentName)));
		groleachievement.reserved1 = Short.parseShort(getElementValue(currentnodelist, "reserved1", parentName));
		groleachievement.reserved2 = Integer.parseInt(getElementValue(currentnodelist, "reserved2", parentName));
		groleachievement.reserved3 = Integer.parseInt(getElementValue(currentnodelist, "reserved3", parentName));
		groleachievement.reserved4 = Byte.parseByte(getElementValue(currentnodelist, "reserved4", parentName));
		groleachievement.version = Integer.parseInt(getElementValue(currentnodelist, "version", parentName));
		return groleachievement;
	}

	static private GRoleBase getGRoleBase(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleBase grolebase = new GRoleBase();
		//special GRoleBase name
		String name = unescapeUnprintable(StringEscapeUtils.unescapeXml(getElementValue( currentnodelist, "name", parentName )));
		grolebase.name = new Octets( name.getBytes("UTF-16LE" ));
		grolebase.circletrack = new Octets(hextoByteArray(getElementValue(currentnodelist, "circletrack", parentName)));
		grolebase.config_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "config_data", parentName)));
		grolebase.create_time = Integer.parseInt(getElementValue(currentnodelist, "create_time", parentName));
		NodeList subnodelist3 = currentelement.getElementsByTagName("datagroup");
		grolebase.datagroup = getGPairVector(subnodelist3, parentName);
		grolebase.delete_time = Integer.parseInt(getElementValue(currentnodelist, "delete_time", parentName));
		grolebase.earid = Byte.parseByte(getElementValue(currentnodelist, "earid", parentName));
		grolebase.faceid = Byte.parseByte(getElementValue(currentnodelist, "faceid", parentName));
		grolebase.familyid = Integer.parseInt(getElementValue(currentnodelist, "familyid", parentName));
		grolebase.fashionid = Byte.parseByte(getElementValue(currentnodelist, "fashionid", parentName));
		NodeList subnodelist9 = currentelement.getElementsByTagName("forbid");
		grolebase.forbid = getGRoleForbidVector(subnodelist9, parentName);
		grolebase.gender = Byte.parseByte(getElementValue(currentnodelist, "gender", parentName));
		grolebase.hairid = Byte.parseByte(getElementValue(currentnodelist, "hairid", parentName));
		grolebase.help_states = new Octets(hextoByteArray(getElementValue(currentnodelist, "help_states", parentName)));
		grolebase.id = Integer.parseInt(getElementValue(currentnodelist, "id", parentName));
		grolebase.initiallevel = Short.parseShort(getElementValue(currentnodelist, "initiallevel", parentName));
		grolebase.jointime = Integer.parseInt(getElementValue(currentnodelist, "jointime", parentName));
		grolebase.lastlogin_time = Integer.parseInt(getElementValue(currentnodelist, "lastlogin_time", parentName));
		grolebase.remote_central_type = Byte.parseByte(getElementValue(currentnodelist, "remote_central_type", parentName));
		grolebase.sectid = Integer.parseInt(getElementValue(currentnodelist, "sectid", parentName));
		grolebase.spouse = Integer.parseInt(getElementValue(currentnodelist, "spouse", parentName));
		grolebase.status = Byte.parseByte(getElementValue(currentnodelist, "status", parentName));
		grolebase.tailid = Byte.parseByte(getElementValue(currentnodelist, "tailid", parentName));
		grolebase.title = Byte.parseByte(getElementValue(currentnodelist, "title", parentName));
		grolebase.userid = Integer.parseInt(getElementValue(currentnodelist, "userid", parentName));
		grolebase.version = Byte.parseByte(getElementValue(currentnodelist, "version", parentName));
		return grolebase;
	}

	static private GRoleBase2 getGRoleBase2(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleBase2 grolebase2 = new GRoleBase2();
		grolebase2.astrology_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "astrology_info", parentName)));
		grolebase2.award_info_6v6 = new Octets(hextoByteArray(getElementValue(currentnodelist, "award_info_6v6", parentName)));
		grolebase2.bonus_reward = Integer.parseInt(getElementValue(currentnodelist, "bonus_reward", parentName));
		grolebase2.bonus_used = Integer.parseInt(getElementValue(currentnodelist, "bonus_used", parentName));
		grolebase2.bonus_withdraw = Integer.parseInt(getElementValue(currentnodelist, "bonus_withdraw", parentName));
		grolebase2.card_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "card_info", parentName)));
		grolebase2.challenge_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "challenge_info", parentName)));
		grolebase2.child_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "child_info", parentName)));
		grolebase2.collision_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "collision_info", parentName)));
		grolebase2.composkills = new Octets(hextoByteArray(getElementValue(currentnodelist, "composkills", parentName)));
		grolebase2.comsumption = Long.parseLong(getElementValue(currentnodelist, "comsumption", parentName));
		grolebase2.data_timestamp = Integer.parseInt(getElementValue(currentnodelist, "data_timestamp", parentName));
		NodeList subnodelist12 = currentelement.getElementsByTagName("datagroup");
		grolebase2.datagroup = getGPairLongVector(subnodelist12, parentName);
		grolebase2.deity_exp = Long.parseLong(getElementValue(currentnodelist, "deity_exp", parentName));
		grolebase2.deity_level = Short.parseShort(getElementValue(currentnodelist, "deity_level", parentName));
		grolebase2.dp = Integer.parseInt(getElementValue(currentnodelist, "dp", parentName));
		grolebase2.exp_withdraw_time = Integer.parseInt(getElementValue(currentnodelist, "exp_withdraw_time", parentName));
		grolebase2.exp_withdraw_today = Long.parseLong(getElementValue(currentnodelist, "exp_withdraw_today", parentName));
		grolebase2.flag_mask = Byte.parseByte(getElementValue(currentnodelist, "flag_mask", parentName));
		grolebase2.fuwen_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "fuwen_info", parentName)));
		grolebase2.guaji_child_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "guaji_child_info", parentName)));
		grolebase2.hide_and_seek_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "hide_and_seek_info", parentName)));
		grolebase2.id = Integer.parseInt(getElementValue(currentnodelist, "id", parentName));
		grolebase2.littlepet = new Octets(hextoByteArray(getElementValue(currentnodelist, "littlepet", parentName)));
		grolebase2.liveness_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "liveness_info", parentName)));
		grolebase2.multi_exp = new Octets(hextoByteArray(getElementValue(currentnodelist, "multi_exp", parentName)));
		grolebase2.newyear_award_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "newyear_award_info", parentName)));
		grolebase2.phase = new Octets(hextoByteArray(getElementValue(currentnodelist, "phase", parentName)));
		grolebase2.propadd = new Octets(hextoByteArray(getElementValue(currentnodelist, "propadd", parentName)));
		grolebase2.remote_roleid = Integer.parseInt(getElementValue(currentnodelist, "remote_roleid", parentName));
		grolebase2.reserved15 = Byte.parseByte(getElementValue(currentnodelist, "reserved15", parentName));
		grolebase2.runescore = Integer.parseInt(getElementValue(currentnodelist, "runescore", parentName));
		grolebase2.sale_promotion_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "sale_promotion_info", parentName)));
		grolebase2.src_zoneid = Integer.parseInt(getElementValue(currentnodelist, "src_zoneid", parentName));
		grolebase2.tower_raid = new Octets(hextoByteArray(getElementValue(currentnodelist, "tower_raid", parentName)));
		grolebase2.ui_transfer = new Octets(hextoByteArray(getElementValue(currentnodelist, "ui_transfer", parentName)));
		return grolebase2;
	}

	static private GRoleForbid getGRoleForbid(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleForbid groleforbid = new GRoleForbid();
		groleforbid.createtime = Integer.parseInt(getElementValue(currentnodelist, "createtime", parentName));
		groleforbid.reason = new Octets(hextoByteArray(getElementValue(currentnodelist, "reason", parentName)));
		groleforbid.time = Integer.parseInt(getElementValue(currentnodelist, "time", parentName));
		groleforbid.type = Byte.parseByte(getElementValue(currentnodelist, "type", parentName));
		return groleforbid;
	}

	static private GRoleInventory getGRoleInventory(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleInventory groleinventory = new GRoleInventory();
		groleinventory.client_size = Short.parseShort(getElementValue(currentnodelist, "client_size", parentName));
		groleinventory.count = Integer.parseInt(getElementValue(currentnodelist, "count", parentName));
		groleinventory.data = new Octets(hextoByteArray(getElementValue(currentnodelist, "data", parentName)));
		groleinventory.expire_date = Integer.parseInt(getElementValue(currentnodelist, "expire_date", parentName));
		groleinventory.guid1 = Integer.parseInt(getElementValue(currentnodelist, "guid1", parentName));
		groleinventory.guid2 = Integer.parseInt(getElementValue(currentnodelist, "guid2", parentName));
		groleinventory.id = Integer.parseInt(getElementValue(currentnodelist, "id", parentName));
		groleinventory.max_count = Short.parseShort(getElementValue(currentnodelist, "max_count", parentName));
		groleinventory.pos = Integer.parseInt(getElementValue(currentnodelist, "pos", parentName));
		groleinventory.proctype = Integer.parseInt(getElementValue(currentnodelist, "proctype", parentName));
		return groleinventory;
	}

	static private GRolePocket getGRolePocket(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRolePocket grolepocket = new GRolePocket();
		grolepocket.capacity = Integer.parseInt(getElementValue(currentnodelist, "capacity", parentName));
		NodeList subnodelist1 = currentelement.getElementsByTagName("equipment");
		grolepocket.equipment = getGRoleInventoryVector(subnodelist1, parentName);
		NodeList subnodelist2 = currentelement.getElementsByTagName("fashion");
		grolepocket.fashion = getGRoleInventoryVector(subnodelist2, parentName);
		NodeList subnodelist3 = currentelement.getElementsByTagName("gifts");
		grolepocket.gifts = getGRoleInventoryVector(subnodelist3, parentName);
		NodeList subnodelist4 = currentelement.getElementsByTagName("items");
		grolepocket.items = getGRoleInventoryVector(subnodelist4, parentName);
		grolepocket.money = Integer.parseInt(getElementValue(currentnodelist, "money", parentName));
		grolepocket.mountwing = new Octets(hextoByteArray(getElementValue(currentnodelist, "mountwing", parentName)));
		NodeList subnodelist7 = currentelement.getElementsByTagName("petbadge");
		grolepocket.petbadge = getGRoleInventoryVector(subnodelist7, parentName);
		NodeList subnodelist8 = currentelement.getElementsByTagName("petequip");
		grolepocket.petequip = getGRoleInventoryVector(subnodelist8, parentName);
		grolepocket.pocket_capacity = Short.parseShort(getElementValue(currentnodelist, "pocket_capacity", parentName));
		NodeList subnodelist10 = currentelement.getElementsByTagName("pocket_items");
		grolepocket.pocket_items = getGPocketInventoryVector(subnodelist10, parentName);
		grolepocket.timestamp = Integer.parseInt(getElementValue(currentnodelist, "timestamp", parentName));
		return grolepocket;
	}

	static private GRoleStatus getGRoleStatus(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleStatus grolestatus = new GRoleStatus();
		grolestatus.battlescore = Integer.parseInt(getElementValue(currentnodelist, "battlescore", parentName));
		grolestatus.charactermode = new Octets(hextoByteArray(getElementValue(currentnodelist, "charactermode", parentName)));
		grolestatus.combatkills = Integer.parseInt(getElementValue(currentnodelist, "combatkills", parentName));
		grolestatus.contribution = Integer.parseInt(getElementValue(currentnodelist, "contribution", parentName));
		grolestatus.coolingtime = new Octets(hextoByteArray(getElementValue(currentnodelist, "coolingtime", parentName)));
		grolestatus.credit = new Octets(hextoByteArray(getElementValue(currentnodelist, "credit", parentName)));
		grolestatus.cultivation = Short.parseShort(getElementValue(currentnodelist, "cultivation", parentName));
		grolestatus.cur_title = Short.parseShort(getElementValue(currentnodelist, "cur_title", parentName));
		grolestatus.custom_status = new Octets(hextoByteArray(getElementValue(currentnodelist, "custom_status", parentName)));
		grolestatus.dbltime_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "dbltime_data", parentName)));
		grolestatus.devotion = Integer.parseInt(getElementValue(currentnodelist, "devotion", parentName));
		grolestatus.exp = Long.parseLong(getElementValue(currentnodelist, "exp", parentName));
		grolestatus.fashion_hotkey = new Octets(hextoByteArray(getElementValue(currentnodelist, "fashion_hotkey", parentName)));
		grolestatus.filter_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "filter_data", parentName)));
		grolestatus.five_year = new Octets(hextoByteArray(getElementValue(currentnodelist, "five_year", parentName)));
		grolestatus.hp = Integer.parseInt(getElementValue(currentnodelist, "hp", parentName));
		grolestatus.id = Integer.parseInt(getElementValue(currentnodelist, "id", parentName));
		grolestatus.instancekeylist = new Octets(hextoByteArray(getElementValue(currentnodelist, "instancekeylist", parentName)));
		grolestatus.level = Short.parseShort(getElementValue(currentnodelist, "level", parentName));
		grolestatus.mp = Integer.parseInt(getElementValue(currentnodelist, "mp", parentName));
		grolestatus.occupation = Byte.parseByte(getElementValue(currentnodelist, "occupation", parentName));
		grolestatus.petcorral = new Octets(hextoByteArray(getElementValue(currentnodelist, "petcorral", parentName)));
		grolestatus.petdata = new Octets(hextoByteArray(getElementValue(currentnodelist, "petdata", parentName)));
		grolestatus.pkvalue = Integer.parseInt(getElementValue(currentnodelist, "pkvalue", parentName));
		grolestatus.posx = Float.parseFloat(getElementValue(currentnodelist, "posx", parentName));
		grolestatus.posy = Float.parseFloat(getElementValue(currentnodelist, "posy", parentName));
		grolestatus.posz = Float.parseFloat(getElementValue(currentnodelist, "posz", parentName));
		grolestatus.pp = Integer.parseInt(getElementValue(currentnodelist, "pp", parentName));
		grolestatus.produceexp = Integer.parseInt(getElementValue(currentnodelist, "produceexp", parentName));
		grolestatus.produceskill = Integer.parseInt(getElementValue(currentnodelist, "produceskill", parentName));
		grolestatus.raid_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "raid_data", parentName)));
		grolestatus.reborndata = new Octets(hextoByteArray(getElementValue(currentnodelist, "reborndata", parentName)));
		grolestatus.recipes = new Octets(hextoByteArray(getElementValue(currentnodelist, "recipes", parentName)));
		grolestatus.reputation = Integer.parseInt(getElementValue(currentnodelist, "reputation", parentName));
		grolestatus.reserved1 = Integer.parseInt(getElementValue(currentnodelist, "reserved1", parentName));
		grolestatus.skills = new Octets(hextoByteArray(getElementValue(currentnodelist, "skills", parentName)));
		grolestatus.storehousepasswd = new Octets(hextoByteArray(getElementValue(currentnodelist, "storehousepasswd", parentName)));
		grolestatus.talismanscore = Integer.parseInt(getElementValue(currentnodelist, "talismanscore", parentName));
		grolestatus.time_used = Integer.parseInt(getElementValue(currentnodelist, "time_used", parentName));
		grolestatus.titlelist = new Octets(hextoByteArray(getElementValue(currentnodelist, "titlelist", parentName)));
		grolestatus.treasure_info = new Octets(hextoByteArray(getElementValue(currentnodelist, "treasure_info", parentName)));
		grolestatus.updatetime = Integer.parseInt(getElementValue(currentnodelist, "updatetime", parentName));
		grolestatus.var_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "var_data", parentName)));
		grolestatus.version = Byte.parseByte(getElementValue(currentnodelist, "version", parentName));
		grolestatus.waypointlist = new Octets(hextoByteArray(getElementValue(currentnodelist, "waypointlist", parentName)));
		grolestatus.worldtag = Integer.parseInt(getElementValue(currentnodelist, "worldtag", parentName));
		return grolestatus;
	}

	static private GRoleStorehouse getGRoleStorehouse(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleStorehouse grolestorehouse = new GRoleStorehouse();
		grolestorehouse.capacity = Integer.parseInt(getElementValue(currentnodelist, "capacity", parentName));
		grolestorehouse.capacity2 = Byte.parseByte(getElementValue(currentnodelist, "capacity2", parentName));
		NodeList subnodelist2 = currentelement.getElementsByTagName("card");
		grolestorehouse.card = getGRoleInventoryVector(subnodelist2, parentName);
		NodeList subnodelist3 = currentelement.getElementsByTagName("card_matrix");
		grolestorehouse.card_matrix = getGRoleInventoryVector(subnodelist3, parentName);
		NodeList subnodelist4 = currentelement.getElementsByTagName("fuwen");
		grolestorehouse.fuwen = getGRoleInventoryVector(subnodelist4, parentName);
		NodeList subnodelist5 = currentelement.getElementsByTagName("items");
		grolestorehouse.items = getGRoleInventoryVector(subnodelist5, parentName);
		NodeList subnodelist6 = currentelement.getElementsByTagName("items2");
		grolestorehouse.items2 = getGRoleInventoryVector(subnodelist6, parentName);
		grolestorehouse.money = Integer.parseInt(getElementValue(currentnodelist, "money", parentName));
		grolestorehouse.reserved1 = Byte.parseByte(getElementValue(currentnodelist, "reserved1", parentName));
		grolestorehouse.reserved2 = Integer.parseInt(getElementValue(currentnodelist, "reserved2", parentName));
		return grolestorehouse;
	}

	static private GRoleTask getGRoleTask(Element currentelement, String parentName) throws XmlRoleException, UnsupportedEncodingException
	{
		NodeList currentnodelist = currentelement.getElementsByTagName("variable");
		GRoleTask groletask = new GRoleTask();
		groletask.task_complete = new Octets(hextoByteArray(getElementValue(currentnodelist, "task_complete", parentName)));
		groletask.task_data = new Octets(hextoByteArray(getElementValue(currentnodelist, "task_data", parentName)));
		groletask.task_finishtime = new Octets(hextoByteArray(getElementValue(currentnodelist, "task_finishtime", parentName)));
		NodeList subnodelist3 = currentelement.getElementsByTagName("task_inventory");
		groletask.task_inventory = getGRoleInventoryVector(subnodelist3, parentName);
		return groletask;
	}





	static private DataVector getGPairVector(NodeList currentnodelist, String parentName) throws XmlRoleException, java.io.UnsupportedEncodingException
	{
		DataVector gpair = new DataVector(new GPair());
		for(int i=0; i<currentnodelist.getLength(); i++)
		{
			Element subnode = (Element)currentnodelist.item(i);
			if (!subnode.getParentNode().getNodeName().equals(parentName)) continue;
			GPair tmp = getGPair(subnode, subnode.getTagName());
			gpair.add(tmp);
		}
		return gpair;
	}
			static private DataVector getGRoleForbidVector(NodeList currentnodelist, String parentName) throws XmlRoleException, java.io.UnsupportedEncodingException
			{
		DataVector groleforbid = new DataVector(new GRoleForbid());
		for(int i=0; i<currentnodelist.getLength(); i++)
		{
			Element subnode = (Element)currentnodelist.item(i);
			if (!subnode.getParentNode().getNodeName().equals(parentName)) continue;
			GRoleForbid tmp = getGRoleForbid(subnode, subnode.getTagName());
			groleforbid.add(tmp);
		}
		return groleforbid;
	}

	static private DataVector getGPairLongVector(NodeList currentnodelist, String parentName) throws XmlRoleException, java.io.UnsupportedEncodingException
	{
		DataVector gpairlong = new DataVector(new GPairLong());
		for(int i=0; i<currentnodelist.getLength(); i++)
		{
			Element subnode = (Element)currentnodelist.item(i);
			if (!subnode.getParentNode().getNodeName().equals(parentName)) continue;
			GPairLong tmp = getGPairLong(subnode, subnode.getTagName());
			gpairlong.add(tmp);
		}
		return gpairlong;
	}



	static private DataVector getGRoleInventoryVector(NodeList currentnodelist, String parentName) throws XmlRoleException, java.io.UnsupportedEncodingException
	{
		DataVector groleinventory = new DataVector(new GRoleInventory());
		for(int i=0; i<currentnodelist.getLength(); i++)
		{
			Element subnode = (Element)currentnodelist.item(i);
			if (!subnode.getParentNode().getNodeName().equals(parentName)) continue;
			GRoleInventory tmp = getGRoleInventory(subnode, subnode.getTagName());
			groleinventory.add(tmp);
		}
		return groleinventory;
	}
			static private DataVector getGPocketInventoryVector(NodeList currentnodelist, String parentName) throws XmlRoleException, java.io.UnsupportedEncodingException
			{
		DataVector gpocketinventory = new DataVector(new GPocketInventory());
		for(int i=0; i<currentnodelist.getLength(); i++)
		{
			Element subnode = (Element)currentnodelist.item(i);
			if (!subnode.getParentNode().getNodeName().equals(parentName)) continue;
			GPocketInventory tmp = getGPocketInventory(subnode, subnode.getTagName());
			gpocketinventory.add(tmp);
		}
		return gpocketinventory;
	}




	static private Node createGPair(Document doc, GPair gpair, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"key","int",Integer.toString(gpair.key)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"value","int",Integer.toString(gpair.value)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGPairLong(Document doc, GPairLong gpairlong, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"key","int",Integer.toString(gpairlong.key)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"value","long",Long.toString(gpairlong.value)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGPocketInventory(Document doc, GPocketInventory gpocketinventory, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"count","short",Short.toString(gpocketinventory.count)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"id","int",Integer.toString(gpocketinventory.id)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"pos","short",Short.toString(gpocketinventory.pos)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleAchievement(Document doc, GRoleAchievement groleachievement, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"achieve_active","Octets",groleachievement.achieve_active));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"achieve_award_map","Octets",groleachievement.achieve_award_map));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"achieve_map","Octets",groleachievement.achieve_map));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"achieve_spec_info","Octets",groleachievement.achieve_spec_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved1","short",Short.toString(groleachievement.reserved1)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved2","int",Integer.toString(groleachievement.reserved2)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved3","int",Integer.toString(groleachievement.reserved3)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved4","byte",Byte.toString(groleachievement.reserved4)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"version","int",Integer.toString(groleachievement.version)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleBase(Document doc, GRoleBase grolebase, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"circletrack","Octets",grolebase.circletrack));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"config_data","Octets",grolebase.config_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"create_time","int",Integer.toString(grolebase.create_time)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolebase.datagroup.size(); i++)
		{
			GPair gpair = (GPair)grolebase.datagroup.get(i);
			node.appendChild(createGPair(doc, gpair, "datagroup"));
		}
		node.appendChild(createVariable(doc,"delete_time","int",Integer.toString(grolebase.delete_time)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"earid","byte",Byte.toString(grolebase.earid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"faceid","byte",Byte.toString(grolebase.faceid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"familyid","int",Integer.toString(grolebase.familyid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"fashionid","byte",Byte.toString(grolebase.fashionid)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolebase.forbid.size(); i++)
		{
			GRoleForbid groleforbid = (GRoleForbid)grolebase.forbid.get(i);
			node.appendChild(createGRoleForbid(doc, groleforbid, "forbid"));
		}
		node.appendChild(createVariable(doc,"gender","byte",Byte.toString(grolebase.gender)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"hairid","byte",Byte.toString(grolebase.hairid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"help_states","Octets",grolebase.help_states));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"id","int",Integer.toString(grolebase.id)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"initiallevel","short",Short.toString(grolebase.initiallevel)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"jointime","int",Integer.toString(grolebase.jointime)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"lastlogin_time","int",Integer.toString(grolebase.lastlogin_time)));
		node.appendChild(doc.createTextNode("\n"));
		//special GRoleBase name
		String tmp = StringEscapeUtils.escapeXml(new String(grolebase.name.getBytes(),"UTF-16LE"));
		String names = escapeUnprintable(tmp);
		node.appendChild( createVariable(doc,"name","Octets",names) );
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"remote_central_type","byte",Byte.toString(grolebase.remote_central_type)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"sectid","int",Integer.toString(grolebase.sectid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"spouse","int",Integer.toString(grolebase.spouse)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"status","byte",Byte.toString(grolebase.status)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"tailid","byte",Byte.toString(grolebase.tailid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"title","byte",Byte.toString(grolebase.title)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"userid","int",Integer.toString(grolebase.userid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"version","byte",Byte.toString(grolebase.version)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleBase2(Document doc, GRoleBase2 grolebase2, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"astrology_info","Octets",grolebase2.astrology_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"award_info_6v6","Octets",grolebase2.award_info_6v6));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"bonus_reward","int",Integer.toString(grolebase2.bonus_reward)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"bonus_used","int",Integer.toString(grolebase2.bonus_used)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"bonus_withdraw","int",Integer.toString(grolebase2.bonus_withdraw)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"card_info","Octets",grolebase2.card_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"challenge_info","Octets",grolebase2.challenge_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"child_info","Octets",grolebase2.child_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"collision_info","Octets",grolebase2.collision_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"composkills","Octets",grolebase2.composkills));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"comsumption","long",Long.toString(grolebase2.comsumption)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"data_timestamp","int",Integer.toString(grolebase2.data_timestamp)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolebase2.datagroup.size(); i++)
		{
			GPairLong gpairlong = (GPairLong)grolebase2.datagroup.get(i);
			node.appendChild(createGPairLong(doc, gpairlong, "datagroup"));
		}
		node.appendChild(createVariable(doc,"deity_exp","long",Long.toString(grolebase2.deity_exp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"deity_level","short",Short.toString(grolebase2.deity_level)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"dp","int",Integer.toString(grolebase2.dp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"exp_withdraw_time","int",Integer.toString(grolebase2.exp_withdraw_time)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"exp_withdraw_today","long",Long.toString(grolebase2.exp_withdraw_today)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"flag_mask","byte",Byte.toString(grolebase2.flag_mask)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"fuwen_info","Octets",grolebase2.fuwen_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"guaji_child_info","Octets",grolebase2.guaji_child_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"hide_and_seek_info","Octets",grolebase2.hide_and_seek_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"id","int",Integer.toString(grolebase2.id)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"littlepet","Octets",grolebase2.littlepet));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"liveness_info","Octets",grolebase2.liveness_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"multi_exp","Octets",grolebase2.multi_exp));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"newyear_award_info","Octets",grolebase2.newyear_award_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"phase","Octets",grolebase2.phase));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"propadd","Octets",grolebase2.propadd));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"remote_roleid","int",Integer.toString(grolebase2.remote_roleid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved15","byte",Byte.toString(grolebase2.reserved15)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"runescore","int",Integer.toString(grolebase2.runescore)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"sale_promotion_info","Octets",grolebase2.sale_promotion_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"src_zoneid","int",Integer.toString(grolebase2.src_zoneid)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"tower_raid","Octets",grolebase2.tower_raid));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"ui_transfer","Octets",grolebase2.ui_transfer));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleForbid(Document doc, GRoleForbid groleforbid, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"createtime","int",Integer.toString(groleforbid.createtime)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reason","Octets",groleforbid.reason));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"time","int",Integer.toString(groleforbid.time)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"type","byte",Byte.toString(groleforbid.type)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleInventory(Document doc, GRoleInventory groleinventory, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"client_size","short",Short.toString(groleinventory.client_size)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"count","int",Integer.toString(groleinventory.count)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"data","Octets",groleinventory.data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"expire_date","int",Integer.toString(groleinventory.expire_date)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"guid1","int",Integer.toString(groleinventory.guid1)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"guid2","int",Integer.toString(groleinventory.guid2)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"id","int",Integer.toString(groleinventory.id)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"max_count","short",Short.toString(groleinventory.max_count)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"pos","int",Integer.toString(groleinventory.pos)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"proctype","int",Integer.toString(groleinventory.proctype)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRolePocket(Document doc, GRolePocket grolepocket, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"capacity","int",Integer.toString(grolepocket.capacity)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolepocket.equipment.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.equipment.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "equipment"));
		}
		for (int i=0; i<grolepocket.fashion.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.fashion.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "fashion"));
		}
		for (int i=0; i<grolepocket.gifts.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.gifts.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "gifts"));
		}
		for (int i=0; i<grolepocket.items.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.items.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "items"));
		}
		node.appendChild(createVariable(doc,"money","int",Integer.toString(grolepocket.money)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"mountwing","Octets",grolepocket.mountwing));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolepocket.petbadge.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.petbadge.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "petbadge"));
		}
		for (int i=0; i<grolepocket.petequip.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolepocket.petequip.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "petequip"));
		}
		node.appendChild(createVariable(doc,"pocket_capacity","short",Short.toString(grolepocket.pocket_capacity)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolepocket.pocket_items.size(); i++)
		{
			GPocketInventory gpocketinventory = (GPocketInventory)grolepocket.pocket_items.get(i);
			node.appendChild(createGPocketInventory(doc, gpocketinventory, "pocket_items"));
		}
		node.appendChild(createVariable(doc,"timestamp","int",Integer.toString(grolepocket.timestamp)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleStatus(Document doc, GRoleStatus grolestatus, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"battlescore","int",Integer.toString(grolestatus.battlescore)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"charactermode","Octets",grolestatus.charactermode));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"combatkills","int",Integer.toString(grolestatus.combatkills)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"contribution","int",Integer.toString(grolestatus.contribution)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"coolingtime","Octets",grolestatus.coolingtime));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"credit","Octets",grolestatus.credit));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"cultivation","short",Short.toString(grolestatus.cultivation)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"cur_title","short",Short.toString(grolestatus.cur_title)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"custom_status","Octets",grolestatus.custom_status));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"dbltime_data","Octets",grolestatus.dbltime_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"devotion","int",Integer.toString(grolestatus.devotion)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"exp","long",Long.toString(grolestatus.exp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"fashion_hotkey","Octets",grolestatus.fashion_hotkey));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"filter_data","Octets",grolestatus.filter_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"five_year","Octets",grolestatus.five_year));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"hp","int",Integer.toString(grolestatus.hp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"id","int",Integer.toString(grolestatus.id)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"instancekeylist","Octets",grolestatus.instancekeylist));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"level","short",Short.toString(grolestatus.level)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"mp","int",Integer.toString(grolestatus.mp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"occupation","byte",Byte.toString(grolestatus.occupation)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"petcorral","Octets",grolestatus.petcorral));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"petdata","Octets",grolestatus.petdata));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"pkvalue","int",Integer.toString(grolestatus.pkvalue)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"posx","float",Float.toString(grolestatus.posx)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"posy","float",Float.toString(grolestatus.posy)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"posz","float",Float.toString(grolestatus.posz)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"pp","int",Integer.toString(grolestatus.pp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"produceexp","int",Integer.toString(grolestatus.produceexp)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"produceskill","int",Integer.toString(grolestatus.produceskill)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"raid_data","Octets",grolestatus.raid_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reborndata","Octets",grolestatus.reborndata));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"recipes","Octets",grolestatus.recipes));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reputation","int",Integer.toString(grolestatus.reputation)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved1","int",Integer.toString(grolestatus.reserved1)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"skills","Octets",grolestatus.skills));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"storehousepasswd","Octets",grolestatus.storehousepasswd));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"talismanscore","int",Integer.toString(grolestatus.talismanscore)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"time_used","int",Integer.toString(grolestatus.time_used)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"titlelist","Octets",grolestatus.titlelist));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"treasure_info","Octets",grolestatus.treasure_info));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"updatetime","int",Integer.toString(grolestatus.updatetime)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"var_data","Octets",grolestatus.var_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"version","byte",Byte.toString(grolestatus.version)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"waypointlist","Octets",grolestatus.waypointlist));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"worldtag","int",Integer.toString(grolestatus.worldtag)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleStorehouse(Document doc, GRoleStorehouse grolestorehouse, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"capacity","int",Integer.toString(grolestorehouse.capacity)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"capacity2","byte",Byte.toString(grolestorehouse.capacity2)));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<grolestorehouse.card.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolestorehouse.card.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "card"));
		}
		for (int i=0; i<grolestorehouse.card_matrix.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolestorehouse.card_matrix.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "card_matrix"));
		}
		for (int i=0; i<grolestorehouse.fuwen.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolestorehouse.fuwen.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "fuwen"));
		}
		for (int i=0; i<grolestorehouse.items.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolestorehouse.items.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "items"));
		}
		for (int i=0; i<grolestorehouse.items2.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)grolestorehouse.items2.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "items2"));
		}
		node.appendChild(createVariable(doc,"money","int",Integer.toString(grolestorehouse.money)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved1","byte",Byte.toString(grolestorehouse.reserved1)));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"reserved2","int",Integer.toString(grolestorehouse.reserved2)));
		node.appendChild(doc.createTextNode("\n"));
		return node;
	}
	static private Node createGRoleTask(Document doc, GRoleTask groletask, String nodename) throws Exception
	{
		Element node = doc.createElement(nodename);
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"task_complete","Octets",groletask.task_complete));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"task_data","Octets",groletask.task_data));
		node.appendChild(doc.createTextNode("\n"));
		node.appendChild(createVariable(doc,"task_finishtime","Octets",groletask.task_finishtime));
		node.appendChild(doc.createTextNode("\n"));
		for (int i=0; i<groletask.task_inventory.size(); i++)
		{
			GRoleInventory groleinventory = (GRoleInventory)groletask.task_inventory.get(i);
			node.appendChild(createGRoleInventory(doc, groleinventory, "task_inventory"));
		}
		return node;
	}

	static public Role getRoleFromDB( int roleid ) throws InterruptedException, Exception
	{
		GRoleData groledata = GameDB.getRoleData(roleid);
		if (null == groledata) return null;
		XmlRole.Role role = new XmlRole.Role();
		role.achievement = groledata.achievement;
		role.base = groledata.base;
		role.base2 = groledata.base2;
		role.pocket = groledata.pocket;
		role.status = groledata.status;
		role.storehouse = groledata.storehouse;
		role.task = groledata.task;
		if( null == role.base || null == role.status )
			return null;
		return role;
	}

	static public boolean putRoleToDB( int roleid, Role role ) throws InterruptedException, Exception
	{
		GRoleData groledata = new GRoleData();
		groledata.achievement = role.achievement;
		groledata.base = role.base;
		groledata.base2 = role.base2;
		groledata.pocket = role.pocket;
		groledata.status = role.status;
		groledata.storehouse = role.storehouse;
		groledata.task = role.task;
		return GameDB.putRoleData(roleid, groledata);
	}

	static public Document toXML( Role role ) throws ParserConfigurationException, Exception
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = doc.createElement("role");
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleAchievement(doc, role.achievement, "achievement"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleBase(doc, role.base, "base"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleBase2(doc, role.base2, "base2"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRolePocket(doc, role.pocket, "pocket"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleStatus(doc, role.status, "status"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleStorehouse(doc, role.storehouse, "storehouse"));
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( doc.createTextNode("\n") );
		root.appendChild( createGRoleTask(doc, role.task, "task"));
		root.appendChild( doc.createTextNode("\n") );
		doc.appendChild( root );
		root.appendChild( doc.createTextNode("\n") );
		return doc;
	}
	public static void main(String args[]) throws Exception
	{
		//gen(); reverse();
		test();
	}
	public static void reverse() throws Exception
	{
		File file1 = new File("role.txt");
		File file2 = new File("role2.txt");
		Role role2 = fromXML(file1);
		toXMLFile(role2, file2);
	}
	public static void gen() throws Exception
	{
		Role role = new Role();
		File file = new File("role.txt");
		role.achievement = new GRoleAchievement();
		role.achievement.achieve_active = new Octets();
		role.achievement.achieve_award_map = new Octets();
		role.achievement.achieve_map = new Octets();
		role.achievement.achieve_spec_info = new Octets();
		role.base = new GRoleBase();
		role.base.circletrack = new Octets();
		role.base.config_data = new Octets();
		role.base.datagroup.add(new GPair());
		role.base.forbid.add(new GRoleForbid());
		role.base.help_states = new Octets();
		role.base.name = new Octets();
		role.base2 = new GRoleBase2();
		role.base2.astrology_info = new Octets();
		role.base2.award_info_6v6 = new Octets();
		role.base2.card_info = new Octets();
		role.base2.challenge_info = new Octets();
		role.base2.child_info = new Octets();
		role.base2.collision_info = new Octets();
		role.base2.composkills = new Octets();
		role.base2.datagroup.add(new GPairLong());
		role.base2.fuwen_info = new Octets();
		role.base2.guaji_child_info = new Octets();
		role.base2.hide_and_seek_info = new Octets();
		role.base2.littlepet = new Octets();
		role.base2.liveness_info = new Octets();
		role.base2.multi_exp = new Octets();
		role.base2.newyear_award_info = new Octets();
		role.base2.phase = new Octets();
		role.base2.propadd = new Octets();
		role.base2.sale_promotion_info = new Octets();
		role.base2.tower_raid = new Octets();
		role.base2.ui_transfer = new Octets();
		role.pocket = new GRolePocket();
		role.pocket.equipment.add(new GRoleInventory());
		role.pocket.fashion.add(new GRoleInventory());
		role.pocket.gifts.add(new GRoleInventory());
		role.pocket.items.add(new GRoleInventory());
		role.pocket.mountwing = new Octets();
		role.pocket.petbadge.add(new GRoleInventory());
		role.pocket.petequip.add(new GRoleInventory());
		role.pocket.pocket_items.add(new GPocketInventory());
		role.status = new GRoleStatus();
		role.status.charactermode = new Octets();
		role.status.coolingtime = new Octets();
		role.status.credit = new Octets();
		role.status.custom_status = new Octets();
		role.status.dbltime_data = new Octets();
		role.status.fashion_hotkey = new Octets();
		role.status.filter_data = new Octets();
		role.status.five_year = new Octets();
		role.status.instancekeylist = new Octets();
		role.status.petcorral = new Octets();
		role.status.petdata = new Octets();
		role.status.raid_data = new Octets();
		role.status.reborndata = new Octets();
		role.status.recipes = new Octets();
		role.status.skills = new Octets();
		role.status.storehousepasswd = new Octets();
		role.status.titlelist = new Octets();
		role.status.treasure_info = new Octets();
		role.status.var_data = new Octets();
		role.status.waypointlist = new Octets();
		role.storehouse = new GRoleStorehouse();
		role.storehouse.card.add(new GRoleInventory());
		role.storehouse.card_matrix.add(new GRoleInventory());
		role.storehouse.fuwen.add(new GRoleInventory());
		role.storehouse.items.add(new GRoleInventory());
		role.storehouse.items2.add(new GRoleInventory());
		role.task = new GRoleTask();
		role.task.task_complete = new Octets();
		role.task.task_data = new Octets();
		role.task.task_finishtime = new Octets();
		role.task.task_inventory.add(new GRoleInventory());
		toXMLFile(role, file);
	}
	public static void test() throws Exception {
		File file = new File("/home/lijinhua2/repos/iweb/test/133466784.xml");
		Role role = fromXML(file);
		File file2 = new File("/home/lijinhua2/repos/iweb/test/133466784.2.xml");
		toXMLFile(role, file2);
	}
}
