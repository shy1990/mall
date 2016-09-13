<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>" />
    <title>转向E宝支付</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>

  </head>
  <script type="text/javascript">
   $(function() {
		//$("#yeePayRefund_FormId").submit();
	}); 
  </script>
  <body>
    <form id="yeePayRefund_FormId" action="https://www.yeepay.com/app-merchant-proxy/node" method="post" >
			<input type="text" name="p0_Cmd"   value="${yeePayPojo.p0_Cmd}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="p1_MerId" value="${yeePayPojo.p1_MerId}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="pb_TrxId" value="${yeePayPojo.pb_TrxId}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="p3_Amt"   value="${yeePayPojo.p3_Amt}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="p4_Cur"   value="${yeePayPojo.p4_Cur}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="p5_Desc"   value="${yeePayPojo.p5_Desc}" readonly="readonly" style="border:0px;"><br/>
			<input type="text" name="hmac"     value="${yeePayPojo.hmac}" readonly="readonly" style="border:0px;" ><br/>	
			<input type="submit" value="提交">
		</form>
  </body>
</html>
