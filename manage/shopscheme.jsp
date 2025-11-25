<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@ page import="com.goldhuman.service.interfaces.LogInfo"%>
<%@ page import="com.goldhuman.service.interfaces.ShopSchemeBean"%>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@ page import="com.goldhuman.service.util.*"%>
<%
   
   LogInfo loginfo = new LogInfo(1,"GM", "getShopScheme");
	ShopSchemeBean bean = new GMServiceImpl().getShopScheme(loginfo);
	if (bean == null) {
		out.println("获取商城信息失败");
		return;
	}
%>
<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title>商城方案</title>
<script language=javascript>
function setdiscountClick()
{
        var len = document.formsetdiscount.discountid.length;
        document.formsetdiscount.formsetdiscountValue.value = "";
        for (i=0; i<len; i++)
        {
                if ( document.formsetdiscount.discountid[i].checked == true)
                {
                        document.formsetdiscount.formsetdiscountValue.value += document.formsetdiscount.discountid[i].value;
                        document.formsetdiscount.formsetdiscountValue.value += ",";
                }
                        
        }
        document.formsetdiscount.submit();
        return true;
}
function setsaleClick()
{
        var len = document.formsetsale.saleid.length;
        document.formsetsale.formsetsaleValue.value = "";
        for (i=0; i<len; i++)
        {
                if ( document.formsetsale.saleid[i].checked == true)
                {
                        document.formsetsale.formsetsaleValue.value += document.formsetsale.saleid[i].value;
                        document.formsetsale.formsetsaleValue.value += ",";
                }
                        
        }

        document.formsetsale.submit();
        return true;
}
</script>
</head>
<body>
<%@include file="../include/header.jsp"%>
<table border cellpadding=2 align="center" width=600 bgcolor="#FFFFFF">
<tr><th align=left width=40%>应用</th><th align=left>说明</th></tr>

<tr>
<td>
<form name="formsetdiscount" action="setdiscountscheme.jsp" ><br>
<input type="hidden" value="" name="formsetdiscountValue"/>
<% 
	out.println(DiscountSchemeParser.getHtml());
%>
<input type="button" value="设置" onclick="javascript:setdiscountClick()"/>&nbsp;&nbsp;&nbsp;&nbsp;
</form>
</td>
<td>
<%
	out.println(DiscountSchemeParser.parseIntToHtml(bean.discount));
%>
</td>
</tr>

<tr><td>
<form name="formsetsale" action="setsalescheme.jsp" ><br>
<input type="hidden" value="" name="formsetsaleValue"/>
<% 
	out.println(SaleSchemeParser.getHtml());
%>
<input type="button" value="设置" onclick="javascript:setsaleClick()"/>&nbsp;&nbsp;&nbsp;&nbsp;
</form>
</td>
<td>
<%
	out.println(SaleSchemeParser.parseIntToHtml(bean.sale));
%>
</td>
</tr>
</table>
<%@include file="../include/foot.jsp"%>
</body>
</html>
