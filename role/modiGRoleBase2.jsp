<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.goldhuman.auth.AuthFilter"%>
<%@ page import="com.goldhuman.service.interfaces.LogInfo"%>
<%@ page import="com.goldhuman.service.interfaces.GMService"%>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@page import="protocol.*" %>

<%
String roleidStr = request.getParameter("roleid");
String bonus_withdrawStr = request.getParameter("bonus_withdraw");
LogFactory.getLog("modiGRoleBase2.jsp").info( "roleid=" + roleidStr + ",bonus_withdraw=" + bonus_withdrawStr + ",operator=" + AuthFilter.getRemoteUser(session));
if (roleidStr == null || roleidStr.length() <=0 || bonus_withdrawStr == null || bonus_withdrawStr.length() <= 0) {
	out.println("arg error!");
	return;
}
int roleid = 0;
int bonus_withdraw = 0;
boolean ret = false;
try {
	roleid = Integer.parseInt(roleidStr);
	bonus_withdraw = Integer.parseInt(bonus_withdrawStr);
}catch(Exception e) {
	out.println("arg parse error! ");
	return;
}
if (roleid == 0 || bonus_withdraw == 0) {
	out.println("arg error!roleid == 0 || bonus_withdraw == 0");
	return;
}
//LogInfo loginfo = new LogInfo(1, op, "modiGRoleBase2");
try {
	GRoleBase2 base2 = GameDB.getGRoleBase2(roleid);
	if (base2 == null) {
		out.println("get bonus data error!");
		return;
	}
	LogFactory.getLog("modiGRoleBase2.jsp").info( "roleid=" + roleidStr + ",old bonus_withdraw=" + base2.bonus_withdraw + ",operator=" + AuthFilter.getRemoteUser(session));
	base2.bonus_withdraw = bonus_withdraw;
	ret = GameDB.putGRoleBase2(roleid, base2);
	LogFactory.getLog("modiGRoleBase2.jsp").info( "roleid=" + roleidStr + ",bonus_withdraw=" + bonus_withdrawStr + ",ret=" + ret + ",operator=" + AuthFilter.getRemoteUser(session));
}catch(Exception e) {
	out.println(e);
	LogFactory.getLog("modiGRoleBase2.jsp").info( "roleid=" + roleidStr + ",bonus_withdraw=" + bonus_withdrawStr + ",exception=" + e.toString() + ",operator=" + AuthFilter.getRemoteUser(session));

}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>modify bonus</title>
		<link href="../include/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<%@include file="../include/header.jsp"%>

		<table width="100%" height="514" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

			<tr>
				<td align="center">
						<table>
							<tr>
								<td>
								return <%=ret%>
								</td>
							</tr>
						</table>
				</td>
			</tr>

		</table>
		<%@include file="../include/foot.jsp"%>
	</body>
</html>
