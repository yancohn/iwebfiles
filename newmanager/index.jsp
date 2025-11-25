<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.goldhuman.util.LocaleUtil2"%>
<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title>newmanager</title>
<script language=javascript>
</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>
<table border=1 cellpadding=2 align=center width=900>
<tr><td align=center><a href=GMService_checkRelease_request.jsp><%=LocaleUtil2.getMessage(request,"Dummy_checkRelease_title")%></a></td><td aligen=center><%=LocaleUtil2.getMessage(request,"MSGMService_checkRelease_comment")%></td></tr>
<tr><td align=center><a href=GMService_queryRoleComsumption_request.jsp><%=LocaleUtil2.getMessage(request,"GMService_queryRoleComsumption_title")%></a></td><td aligen=center><%=LocaleUtil2.getMessage(request,"WGMService_GMGetActivityBonus_comment")%></td></tr>
<tr><td align=center><a href=GMService_traceroute_request.jsp><%=LocaleUtil2.getMessage(request,"GMService_traceroute_title")%></a></td><td aligen=center><%=LocaleUtil2.getMessage(request,"GMService_traceroute_comment")%></td></tr>
<tr><td colspan=2 align=center><a href="javascript:window.history.back(-1);">Return</a></td></tr>
</table>
</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>
