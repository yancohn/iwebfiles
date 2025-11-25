<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.goldhuman.util.*"%>
<%@page import="common.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="com.goldhuman.util.*"%>
<%@page import="com.goldhuman.Common.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.service.interfaces.*"%>
<%@page import="com.goldhuman.service.*"%>
<%@page import="javax.management.*"%>
<%@page import="javax.management.remote.*"%>
<%@page import="java.rmi.*"%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title></title>
<script language=javascript>
</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>
<% request.setCharacterEncoding("UTF-8"); %>
<a name=top></a>
<table align=center>
<%
String op = AuthFilter.getRemoteUser(session);
String logname = "GMService_queryRoleComsumption_response.jsp";
String tmp = "";
Integer GMService_queryRoleComsumption_roleid = null;
LogInfo loginfo = new LogInfo(0, "iweb", "");
tmp = request.getParameter("GMService_queryRoleComsumption_roleid");
try { 
	GMService_queryRoleComsumption_roleid = Integer.valueOf(tmp); 
} catch(Exception e) { 
	 e.printStackTrace(System.out); 
	 out.println("<tr><td align=center>input error:" +"roleid" + "(GMService_queryRoleComsumption_roleid),right type is Integer</td></tr>"); 
	 out.println("<tr><td align=center><a href=\"javascript:window.history.back(-1);\">Return</a></td></tr>"); 
	 return;
}

try {
LogFactory.getLog(logname).info(logname+". op="+ op);
GMServiceImpl gs = new GMServiceImpl();
Long ret = gs.queryRoleComsumption(GMService_queryRoleComsumption_roleid, loginfo);
if (ret != null) { out.println("<tr><td>return:" + ret+"</td></tr>"); } else { out.println("<tr><td>method=queryRoleComsumption" + "" + " invoke error</td></tr>"); }
} catch(Exception e) { e.printStackTrace(System.out); out.println("invoke queryRoleComsumption exception"); return; }
%>
<tr><td align=center><a href="javascript:window.history.back(-1);">Return</a></td></tr>
</table>
</tr></td></table><%@include file="../include/foot.jsp"%>
</body>
</html>

