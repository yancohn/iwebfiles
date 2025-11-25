<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.goldhuman.auth.AuthFilter"%>
<%@ page import="com.goldhuman.service.interfaces.LogInfo"%>
<%@ page import="com.goldhuman.service.interfaces.GMService"%>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@page import="com.goldhuman.service.interfaces.LogInfo" %>
<%
LogFactory.getLog("listBackups.jsp").info("listBackups operator="+ AuthFilter.getRemoteUser(session));
String op = AuthFilter.getRemoteUser(session);
LogInfo loginfo = new LogInfo(1, op, "listBackups");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>listbackup</title>
		<link href="../include/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<%@include file="../include/header.jsp"%>
		<table width="100%" height="514" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
					<TABLE border="1" cellpadding="0" cellspacing="1" width="400px" align="center">
						<TR>
							<TH>
								backup list= <%= new GMServiceImpl().listBackups(loginfo) %>
							</TH>
						</TR>
					</TABLE>

			</td>
			</tr>
		</table>
		<%@include file="../include/foot.jsp"%>
	</body>
</html>
