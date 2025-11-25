<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.goldhuman.util.LocaleUtil" %>
<%@ page import="com.goldhuman.service.interfaces.LogInfo"%>
<%@ page import="com.goldhuman.service.interfaces.ShopSchemeBean"%>
<%@ page import="com.goldhuman.service.GMServiceImpl"%>
<%@ page import="com.goldhuman.service.util.*"%>

<%
	String scheme = (String)request.getParameter("formsetdiscountValue");
   int discount = DiscountSchemeParser.parseStringToInt(scheme);
   LogInfo loginfo = new LogInfo(1,"GM", "setdiscountscheme");
	boolean setok = new GMServiceImpl().setDiscountScheme(discount, loginfo);
	if (setok) {
		out.println("设置商城打折信息成功, 方案列表"+scheme);
	} else {
		out.println("设置商城打折信息失败, 方案列表"+scheme);
	}
%>