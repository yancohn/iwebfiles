<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.goldhuman.auth.AuthFilter"%>
<%@ page import="com.goldhuman.service.interfaces.LogInfo"%>
<%@ page import="com.goldhuman.service.interfaces.GMService"%>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@page import="protocol.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>put bonus</title>
		<link href="../include/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<%@include file="../include/header.jsp"%>

		<table width="100%" height="514" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

			<tr>
				<td align="center">
					<form name="form1" action="modiGRoleBase2.jsp" method="post">
						<table>
							<tr>
								<td>
									<%= LocaleUtil.getMessage(request,"role_logstatus_inputroleid") %>
									<input type="text" name="roleid" value="" size="16" maxlength="10" /> <br>
									bonus:
									<input type="text" name="bonus_withdraw" value="" size="16" maxlength="10" />
									&nbsp;&nbsp;
									<input type="submit" value="<%= LocaleUtil.getMessage(request,"role_logstatus_submit") %>">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>

		</table>
		<%@include file="../include/foot.jsp"%>
	</body>
</html>
