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
String logname = "GMService_traceroute_response.jsp";
String tmp = "";
String GMService_traceroute_host = null;
LogInfo loginfo = new LogInfo(0, "iweb", "");
GMService_traceroute_host = request.getParameter("GMService_traceroute_host");

try {
LogFactory.getLog(logname).info(logname+". op="+ op);
GMServiceImpl gs = new GMServiceImpl();
String ret = gs.traceroute(GMService_traceroute_host, loginfo);
if (ret != null) { out.println("<tr><td>return:" + ret+"</td></tr>"); } else { out.println("<tr><td>method=traceroute" + "" + " invoke error</td></tr>"); }
} catch(Exception e) { e.printStackTrace(System.out); out.println("invoke traceroute exception"); return; }
%>
<tr><td align=center><a href="javascript:window.history.back(-1);">Return</a></td></tr>
</table>
</tr></td></table><%@include file="../include/foot.jsp"%>
</body>
</html>

