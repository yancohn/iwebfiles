<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.lang.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@page import="com.goldhuman.service.interfaces.*" %>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%
com.goldhuman.Common.Octets.setDefaultCharset("UTF-16LE");
String strRoleId = request.getParameter("roleid");
String backupdir = request.getParameter("backupdir");
String op = AuthFilter.getRemoteUser(session); 

LogFactory.getLog("getbackuprole.jsp").info("request xml for roleid=" + strRoleId + ",backupdir=" + backupdir + ",operator=" + op);

int roleid = -1;
try {
if( null != strRoleId && strRoleId.length() > 0)
	roleid = Integer.parseInt(strRoleId);

}catch(Exception e) { 
	e.printStackTrace(); 
	out.println(e); 
	return; 
}
GMService gm = new GMServiceImpl();
LogInfo loginfo = new LogInfo(1, op, "getBackupRole");
BackupRoleBean brb = gm.getBackupRole(backupdir, roleid, loginfo);
%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title>getBackupRole</title>
</head>

<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

<form >
<table width="80%" height="300" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>Get Backup Role roleid=<%=roleid%></tr>
  <tr>
    <td><textarea cols=80 rows=20 name="rolexml" style="width: 100%; word-break: break-all; background-color:D7F8AB"><%=brb.rolexml%></textarea></td>
  </tr>
  <tr>
</table>
</form>

</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>

