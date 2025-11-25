<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>




<%
String strMax = new String("-1");
String strFakeMax = new String("-1");
String strCur = new String("-1");
String strNewMax = new String("5000");
String strNewFakeMax = new String("5000");
{
	Integer[] curmax = new Integer[3];
	if( !DeliveryDB.GetMaxOnlineNum( curmax ) )
		LogFactory.getLog("index.jsp").info("GetMaxOnlineNum error." );
	if( null == curmax[0] )	curmax[0] = new Integer(-1);
	if( null == curmax[1] )	curmax[1] = new Integer(-1);
	if( null == curmax[2] )	curmax[2] = new Integer(-1);
	if( -1 != curmax[0].intValue() ) strNewMax=(new Integer(curmax[0].intValue())).toString();
	if( -1 != curmax[1].intValue() ) strNewFakeMax=(new Integer(curmax[1].intValue())).toString();
	strMax = curmax[0].toString();
	strFakeMax = curmax[1].toString();
	strCur = curmax[2].toString();
}

String strDoubleExp = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetMultiExp();
	strDoubleExp = Byte.toString(status);
}

String strLambda = "-1";
{
	int lambda = DeliveryDB.GMGetLambda();
	strLambda = (new Integer(lambda)).toString();
}

String strNoTrade_check = "checked=\"checked\"";
String strNoTrade = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetNoTrade();
	if( (byte)1 == status )			strNoTrade = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strNoTrade = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strNoTrade_check = "";
}

String strNoAuction_check = "checked=\"checked\"";
String strNoAuction = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetNoAuction();
	if( (byte)1 == status )			strNoAuction = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strNoAuction = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strNoAuction_check = "";
}

String strNoMail_check = "checked=\"checked\"";
String strNoMail = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetNoMail();
	if( (byte)1 == status )			strNoMail = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strNoMail = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strNoMail_check = "";
}

String strNoFaction_check = "checked=\"checked\"";
String strNoFaction = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetNoFaction();
	if( (byte)1 == status )			strNoFaction = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strNoFaction = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strNoFaction_check = "";
}

String strDoubleMoney_check = "checked=\"checked\"";
String strDoubleMoney = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetDoubleMoney();
	if( (byte)1 == status )			strDoubleMoney = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strDoubleMoney = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strDoubleMoney_check = "";
}

String strDoubleObject_check = "checked=\"checked\"";
String strDoubleObject = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetDoubleObject();
	if( (byte)1 == status )			strDoubleObject = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strDoubleObject = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strDoubleObject_check = "";
}

String strDoubleSP_check = "checked=\"checked\"";
String strDoubleSP = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetDoubleSP();
	if( (byte)1 == status )			strDoubleSP = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strDoubleSP = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strDoubleSP_check = "";
}

String strNoSellPoint_check = "checked=\"checked\"";
String strNoSellPoint = LocaleUtil.getMessage(request,"manage_index_unknow");
{
	byte status = DeliveryDB.GMGetNoSellPoint();
	if( (byte)1 == status )			strNoSellPoint = LocaleUtil.getMessage(request,"manage_index_yes");
	else if( (byte)0 == status )	strNoSellPoint = LocaleUtil.getMessage(request,"manage_index_no");
	if( (byte)1 == status )			strNoSellPoint_check = "";
}
%>
<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title><%= LocaleUtil.getMessage(request,"manage_index_title") %></title>
<script language=javascript>
function f_doubleexp_submit()
{                                                                                             
	        document.form_doubleexp.doubleexp.value = document.form_doubleexp.expselect.options[document.form_doubleexp.expselect.selectedIndex].value;
		if (document.form_doubleexp.doubleexp.value == "") { 
			alert("��ѡ��þ��鱶��!");
			return false;
		}       
		document.form_doubleexp.submit();
}       
function onsetmaxonlinenum()
{
	if( document.formsetmaxonlinenum.fakemaxnum > document.formsetmaxonlinenum.maxnum.value )
	{
		alert(LocaleUtil.getMessage(request,"manage_index_alert1"));
	}
	return true;
}
</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

&nbsp;<br>
<table border cellpadding=2 align="center" width=600>
<tr><th align=left width=40%><%= LocaleUtil.getMessage(request,"manage_index_application") %></th><th align=left><%= LocaleUtil.getMessage(request,"manage_index_explain") %></th></tr>

<tr><td>
<form name="formsetmaxonlinenum" action="setmaxonlinenum.jsp" method="post">
<%= LocaleUtil.getMessage(request,"manage_index_inmaxonline") %><input type="text" name="maxnum" value="<%=strNewMax%>" size="7" maxlength="6">&nbsp;<br><%= LocaleUtil.getMessage(request,"manage_index_virmaxonline") %><input type="text" name="fakemaxnum" value="<%=strNewFakeMax%>" size="7" maxlength="6"><input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>">&nbsp<a href ="linelimit.jsp"><%= LocaleUtil.getMessage(request,"manage_index_multiplelineset")%></a></form>
</td><td>&nbsp;<%=LocaleUtil.getMessage(request,"manage_index_setmaxonline")%><%=strCur%>/<%=strMax%>/<%=strFakeMax%>&nbsp;</td></tr>

<tr><td>
<form action="setmultiexp.jsp" name="form_doubleexp" method="post">
<input type="hidden" name="doubleexp" value=""/>
<label>multi experience:
	<select name="expselect">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
	</select>
</label>
<input type="button" name="doubleexp_button" value="set multi experience" onclick="javascript:f_doubleexp_submit()">
</form>
</td><td>&nbsp;current multi experience:<%=strDoubleExp%>&nbsp;</td></tr>

<tr><td>
<form action="setlambda.jsp" method="post">
<%= LocaleUtil.getMessage(request,"manage_index_lambda") %><input type="text" name="lambda" value="" size="7" maxlength="6">&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_lambda2")%><%=strLambda%>&nbsp;</td></tr>

<tr><td>
<form action="setnotrade.jsp" method="post">
<input type="checkbox" name="notrade" value="true" <%=strNoTrade_check%> ><%= LocaleUtil.getMessage(request,"manage_index_forbidbusi") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_forbidbusi2")%><%=strNoTrade%>&nbsp;</td></tr>

<tr><td>
<form action="setnoauction.jsp" method="post">
<input type="checkbox" name="noauction" value="true" <%=strNoAuction_check%> ><%= LocaleUtil.getMessage(request,"manage_index_forbidsale") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_forbidsale2") %><%=strNoAuction%>&nbsp;</td></tr>

<tr><td>
<form action="setnomail.jsp" method="post">
<input type="checkbox" name="nomail" value="true" <%=strNoMail_check%> ><%= LocaleUtil.getMessage(request,"manage_index_forbidmail") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_forbidmail2") %><%=strNoMail%>&nbsp;</td></tr>

<tr><td>
<form action="setnofaction.jsp" method="post">
<input type="checkbox" name="nofaction" value="true" <%=strNoFaction_check%> ><%= LocaleUtil.getMessage(request,"manage_index_forbidfaction") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_forbidfaction2") %><%=strNoFaction%>&nbsp;</td></tr>

<tr><td>
<form action="setdoublemoney.jsp" method="post">
<input type="checkbox" name="doublemoney" value="true" <%=strDoubleMoney_check%> ><%= LocaleUtil.getMessage(request,"manage_index_doublemoney") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_doublemoney2")%><%=strDoubleMoney%>&nbsp;</td></tr>

<tr><td>
<form action="setdoubleobject.jsp" method="post">
<input type="checkbox" name="doubleobject" value="true" <%=strDoubleObject_check%> ><%= LocaleUtil.getMessage(request,"manage_index_doubleobject") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_doubleobject2") %><%=strDoubleObject%>&nbsp;</td></tr>

<tr><td>
<form action="setdoublesp.jsp" method="post">
<input type="checkbox" name="doublesp" value="true" <%=strDoubleSP_check%> ><%= LocaleUtil.getMessage(request,"manage_index_doubleSP") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_doubleSP2") %><%=strDoubleSP%>&nbsp;</td></tr>

<tr><td>
<form action="setnosellpoint.jsp" method="post">
<input type="checkbox" name="nosellpoint" value="true" <%=strNoSellPoint_check%> ><%= LocaleUtil.getMessage(request,"manage_index_forbidsellpoint") %>&nbsp;&nbsp;<input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_set") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_forbidsellpoint2") %><%=strNoSellPoint%>&nbsp;</td></tr>

<tr><td>
<form action="shutdowngamefriendly.jsp" method="post">
<%= LocaleUtil.getMessage(request,"manage_index_inwaittime") %><input type="text" name="waitsecs" value="300" size="7" maxlength="4"><input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_amityshutdown") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_amityshutdown2") %>&nbsp;</td></tr>

<tr><td>
<form action="shutdownline.jsp" method="post" onsubmit="javascript:return confirm('<%= LocaleUtil.getMessage(request,"manage_index_inlineconfirm")%>');">
<%= LocaleUtil.getMessage(request,"manage_index_inline")%><input type="text" name="linenum" value="" size="7" maxlength="4"><input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_inlinesubmit")%>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_inlinenotice")%>&nbsp;</td></tr>
<tr><td>
<form action="broadcast.jsp" method="post">
<%= LocaleUtil.getMessage(request,"manage_index_inbroadcast") %><br><input type="text" name="msg" value="" size="45" maxlength="200"><br><input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_broadcast") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_broadcast") %>&nbsp;</td></tr>

<tr><td>
<form action="worldtag.jsp" method="post">
<%= LocaleUtil.getMessage(request,"manage_index_worldID") %><input type="text" name="worldtag" value="" size="7" maxlength="10"><br>
<%= LocaleUtil.getMessage(request,"manage_index_comm")%><br><input type="text" name="command" value="" size="45" maxlength="200"><br><input type="submit" value="<%= LocaleUtil.getMessage(request,"manage_index_sendcomm") %>"></form>
</td><td>&nbsp;<%= LocaleUtil.getMessage(request,"manage_index_sendtoworld")%>&nbsp;</td></tr>
<tr>
	<td>
		<a href="shopscheme.jsp">商城销售和打折信息</a>
	</td>
	<td>
		商城销售和打折信息
	</td>
</tr>
<tr><td>
<form action="commondataquery.jsp" method="post">
查询跨服功能开放：<input type="text" name="key" value="" size="6" maxlength="10"><input type="submit" value="submit"></form>
</td><td>输入key值</td></tr>

<TR> <TD> <a href="getGRoleBase2_src_zoneid.jsp">get src_zoneid</a> </TD> <TD> get src_zoneid </TD> </TR> <TR>
<TD> <a href="putGRoleBase2_src_zoneid.jsp">modify src_zoneid</a> </TD> <TD> modify src_zoneid </TD> </TR>
</table>

&nbsp;<br>
&nbsp;<br>

</td></tr></table>
<%@include file="../include/foot.jsp"%>
</body>
</html>

