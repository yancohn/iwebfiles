<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.lang.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%
int roleid = Integer.parseInt(request.getParameter("roleid"));

RoleBean role = (RoleBean)session.getAttribute("gamedb_rolebean");
role.base.id = roleid;

//role.user.cash = Integer.parseInt(request.getParameter("cash"));

role.status.level = Short.parseShort(request.getParameter("level"));
role.status.exp = Integer.parseInt(request.getParameter("exp"));
role.status.pp = Integer.parseInt(request.getParameter("pp"));
role.status.hp = Integer.parseInt(request.getParameter("hp"));
role.status.mp = Integer.parseInt(request.getParameter("mp"));
role.status.posx = Integer.parseInt(request.getParameter("posx"));
role.status.posy = Integer.parseInt(request.getParameter("posy"));
role.status.posz = Integer.parseInt(request.getParameter("posz"));
role.status.pkvalue = Integer.parseInt(request.getParameter("pkvalue"));
role.status.reputation = Integer.parseInt(request.getParameter("reputation"));
role.status.produceskill = Integer.parseInt(request.getParameter("produceskill"));
role.status.produceexp = Integer.parseInt(request.getParameter("produceexp"));
if( (new String("on")).equals(request.getParameter("clearstorehousepasswd")) )
	role.status.storehousepasswd.clear();

role.pocket.money = Integer.parseInt(request.getParameter("money"));

boolean success = false;
try {
	success = GameDB.update( role );
	if( success && role.base.id >= 16 && role.base.id < 31 )
	{
		int newroleid = ( 0 == role.base.id % 2 ) ? (role.base.id+1) : (role.base.id-1);
		role.base.id = newroleid;
		success = GameDB.update( role );
	}
}
catch (Exception e) { out.println(e.toString()); return; }
LogFactory.getLog("saverole.jsp").info("putRoleInfo, "+role.getLogString()+",result="+success+",operator=" + AuthFilter.getRemoteUser(session) );
%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title><%= LocaleUtil.getMessage(request,"role_saverole_title") %></title>
</head>

<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

<table width="30%" height="200" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td height="350">
<%
	if( success )
		out.print( LocaleUtil.getMessage(request,"role_saverole_saveok") + "&nbsp;&nbsp;&nbsp;&nbsp;" );
	else if( null == session.getAttribute("gamedb_rolebean") )
		out.print( LocaleUtil.getMessage(request,"role_saverole_savetimeout") + "&nbsp;&nbsp;&nbsp;&nbsp;" );
	else
		out.print( LocaleUtil.getMessage(request,"role_saverole_savefail") + "&nbsp;&nbsp;&nbsp;&nbsp;" );
%>
    </td>
  </tr>
</table>

</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>

