<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>" />
    <title>×ªÏòE±¦Ö§¸¶</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>

  </head>
  <script type="text/javascript">
   $(function() {
		$("#yeePayFormId").submit();
	}); 
  </script>
  <body>
    <%--<form id="yeePayFormId" action="https://www.yeepay.com/app-merchant-proxy/node" method="post" >--%>
    <form id="yeePayFormId" action="http://115.28.92.73:28088/order" method="post" >
			<input type="hidden" name="p0_Cmd"   value="${yeePayPojo.p0_Cmd}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p1_MerId" value="${yeePayPojo.p1_MerId}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p2_Order" value="${yeePayPojo.p2_Order}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p3_Amt"   value="${yeePayPojo.p3_Amt}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p4_Cur"   value="${yeePayPojo.p4_Cur}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p5_Pid"   value="${yeePayPojo.p5_Pid}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p6_Pcat"  value="${yeePayPojo.p6_Pcat}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p7_Pdesc" value="${yeePayPojo.p7_Pdesc}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p8_Url"   value="${yeePayPojo.p8_Url}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="p9_SAF"   value="${yeePayPojo.p9_SAF}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="pa_MP"    value="${yeePayPojo.pa_MP}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="pd_FrpId" value="${yeePayPojo.pd_FrpId}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="pr_NeedResponse"  value="${yeePayPojo.pr_NeedResponse}" readonly="readonly" style="border:0px;"><br/>
			<input type="hidden" name="hmac"     value="${yeePayPojo.hmac}" readonly="readonly" style="border:0px;" ><br/>
			
		</form>
  </body>
</html>
