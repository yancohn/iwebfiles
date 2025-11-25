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
String src_zoneidStr = request.getParameter("src_zoneid");
LogFactory.getLog("modiGRoleBase2_src_zoneid.jsp").info( "roleid=" + roleidStr + ",src_zoneid=" + src_zoneidStr + ",operator=" + AuthFilter.getRemoteUser(session));
if (roleidStr == null || roleidStr.length() <=0 || src_zoneidStr == null || src_zoneidStr.length() <= 0) {
	out.println("arg error!");
	return;
}
int roleid = 0;
int src_zoneid = 0;
boolean ret = false;
try {
	roleid = Integer.parseInt(roleidStr);
	src_zoneid = Integer.parseInt(src_zoneidStr);
}catch(Exception e) {
	out.println("arg parse error! ");
	return;
}
if (roleid == 0 || src_zoneid == 0) {
	out.println("arg error!roleid == 0 || src_zoneid == 0");
	return;
}
//LogInfo loginfo = new LogInfo(1, op, "modiGRoleBase2");
try {
	GRoleBase2 base2 = GameDB.getGRoleBase2(roleid);
	if (base2 == null) {
		out.println("get GRoleBase2 data error!");
		return;
	}
	LogFactory.getLog("modiGRoleBase2_src_zoneid.jsp").info( "roleid=" + roleidStr + ",old src_zoneid=" + base2.src_zoneid + ",operator=" + AuthFilter.getRemoteUser(session));
	base2.src_zoneid = src_zoneid;
	ret = GameDB.putGRoleBase2(roleid, base2);
	LogFactory.getLog("modiGRoleBase2_src_zoneid.jsp").info( "roleid=" + roleidStr + ",src_zoneid=" + src_zoneidStr + ",ret=" + ret + ",operator=" + AuthFilter.getRemoteUser(session));
}catch(Exception e) {
	out.println(e);
	LogFactory.getLog("modiGRoleBase2_src_zoneid.jsp").info( "roleid=" + roleidStr + ",src_zoneid=" + src_zoneidStr + ",exception=" + e.toString() + ",operator=" + AuthFilter.getRemoteUser(session));

}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>modify src_zoneid</title>
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
