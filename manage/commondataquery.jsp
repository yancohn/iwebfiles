<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.lang.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil"%>
<%
Integer key = Integer.valueOf(request.getParameter("key").trim());

Integer result = DeliveryDB.getCommonDataQuery(key);
LogFactory.getLog("commondataquery.jsp").info("commondataquery, key=" + key + ",result=" + result + ",operator=" + AuthFilter.getRemoteUser(session) );
%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title>get commondataquery </title>
</head>

<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

<table width="30%" height="200" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td height="350">
<%
	out.print( "return value=" + result);
%>
    </td>
  </tr>
</table>

</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>

