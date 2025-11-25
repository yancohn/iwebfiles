<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.lang.*"%>
<%@page import="protocol.*"%>
<%@page import="com.goldhuman.auth.*"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="com.goldhuman.util.LocaleUtil"%>
<%
LinePlayerNumberLimits limits = new LinePlayerNumberLimits();
boolean success = DeliveryDB.GetLinePlayerLimit( limits );
if( success )
	session.setAttribute( "linelimit_limits", limits );
LogFactory.getLog("linelimit.jsp").info("linelimit.jsp, get ret=" + success + ", size=" + limits.limits.size() + ", perator=" + AuthFilter.getRemoteUser(session) );
%>

<html>
<head>
<link href="../include/style.css" rel="stylesheet" type="text/css">
<title><%= LocaleUtil.getMessage(request,"manage_linelimit_title")%></title>
</head>

<body>
<%@include file="../include/header.jsp"%>
<table width="100%" height="350"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr><td>

<table width="80%" height="200" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td height="350" align="center">
	<form name="linelimit" action="linelimit2.jsp" method="post">
<%
	if( success )
	{
		for( int i=0; i<limits.limits.size(); i++ )
		{
			LinePlayerLimit l = (LinePlayerLimit)limits.limits.get(i);
			out.println( LocaleUtil.getMessage(request,"manage_linelimit_line")+":&nbsp;ID=" + l.line_id + "&nbsp;&nbsp;"+LocaleUtil.getMessage(request,"manage_linelimit_online")+"=" + l.cur_num + "&nbsp;&nbsp;"+LocaleUtil.getMessage(request,"manage_linelimit_maxonline")+"=<input name=\"line" + l.line_id + "\" value=\"" + l.max_num + "\">" );
			out.println( "<br>" );
		}
	}
	else
	{
		out.print( LocaleUtil.getMessage(request,"manage_linelimit_fail")+"&nbsp;&nbsp;&nbsp;&nbsp;" );
	}
%>
	<br>&nbsp;<input type="submit" value="<%=LocaleUtil.getMessage(request,"manage_linelimit_modify")%>">
	</form>
    </td>
  </tr>
</table>


<table border cellpadding=1 width=100%>
<tr>
  <td align=center>link1-eth1 Octets(bits/sec)<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fif%2Flink1-eth1;inst=0;dslist=ifInOctets%2CifOutOctets;range=151255;rand=892></td>
  <td align=center>link2-eth1 Octets(bits/sec)<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fif%2Flink2-eth1;inst=0;dslist=ifInOctets%2CifOutOctets;range=151255;rand=743></td>
</tr>
<tr>
<td align=center>link1 CPU<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Flink1;inst=0;dslist=ssCpuUser%2CssCpuIrq%2CssCpuSystem%2CssCpuIdle;range=151255;rand=852></td>
  <td align=center>link1 memory<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Flink1;inst=0;dslist=memTotalSwap%2CmemAvailSwap%2CmemTotalReal%2CmemAvailReal%2CmemTotalFree;range=151255;rand=881></td>
</tr>
<tr>
  <td align=center>link2 CPU<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Flink2;inst=0;dslist=ssCpuUser%2CssCpuIrq%2CssCpuSystem%2CssCpuIdle;range=151255;rand=852></td>
  <td align=center>link2 memory<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Flink2;inst=0;dslist=memTotalSwap%2CmemAvailSwap%2CmemTotalReal%2CmemAvailReal%2CmemTotalFree;range=151255;rand=881></td>
  </tr>
<tr>
  <td align=center>game1 CPU<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame1;inst=0;dslist=ssCpuUser%2CssCpuIrq%2CssCpuSystem%2CssCpuIdle;range=151255;rand=852></td>
  <td align=center>game1 memory<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame1;inst=0;dslist=memTotalSwap%2CmemAvailSwap%2CmemTotalReal%2CmemAvailReal%2CmemTotalFree;range=151255;rand=881></td>
</tr>
<tr>
  <td align=center>game2 CPU<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame2;inst=0;dslist=ssCpuUser%2CssCpuIrq%2CssCpuSystem%2CssCpuIdle;range=151255;rand=852></td>
  <td align=center>game2 memory<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame2;inst=0;dslist=memTotalSwap%2CmemAvailSwap%2CmemTotalReal%2CmemAvailReal%2CmemTotalFree;range=151255;rand=881></td>
  </tr>
<tr>
  <td align=center>game3 CPU<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame3;inst=0;dslist=ssCpuUser%2CssCpuIrq%2CssCpuSystem%2CssCpuIdle;range=151255;rand=852></td>
  <td align=center>game3 memory<br><img width=100% height=255 src=/cricket/mini-graph.cgi?type=png;target=%2Fsystem%2Fperf%2Fgame3;inst=0;dslist=memTotalSwap%2CmemAvailSwap%2CmemTotalReal%2CmemAvailReal%2CmemTotalFree;range=151255;rand=881></td>
</tr>
</table>






<%@include file="../include/foot.jsp"%>


</body>
</html>

