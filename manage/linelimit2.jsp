<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.lang.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%
LinePlayerNumberLimits limits = (LinePlayerNumberLimits)session.getAttribute( "linelimit_limits" );
boolean success = false;
if( null != limits )
{
	for( int i=0; i<limits.limits.size(); i++ )
	{
		LinePlayerLimit l = (LinePlayerLimit)limits.limits.get(i);
		int old_max_num = l.max_num;
		l.max_num = Integer.parseInt(request.getParameter("line"+l.line_id));
		LogFactory.getLog("linelimit2.jsp").info("linelimit2.jsp, line_id=" + l.line_id + ", old max_num=" + old_max_num + ", new max_num=" + l.max_num );
	}
	success = DeliveryDB.SetLinePlayerLimit( limits );
}
LogFactory.getLog("linelimit2.jsp").info("linelimit2.jsp, set ret=" + success + ", size=" + limits.limits.size() + ", perator=" + AuthFilter.getRemoteUser(session) );
%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title><%= LocaleUtil.getMessage(request,"manage_linelimit2_title")%></title>
</head>

<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

<table width="30%" height="200" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td height="350">
<%
	if( success )
	{
		out.print( LocaleUtil.getMessage(request,"manage_linelimit2_success")+"&nbsp;&nbsp;&nbsp;&nbsp;" );
	}
	else
	{
		out.print( LocaleUtil.getMessage(request,"manage_linelimit2_fail")+"&nbsp;&nbsp;&nbsp;&nbsp;" );
	}
%>
    </td>
  </tr>
</table>

</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>

