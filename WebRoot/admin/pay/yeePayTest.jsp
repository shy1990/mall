<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>" />
    <title>选择支付界面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
    <div>易宝支付产品通用接口测试</div>
		<div id="input">
		<form method="post" action="yeePay/toYEEPayRetrun.html">
			<table>
				<tr>
					<td>订单号<input type="text" name="orderId" value="dacafafd7d994101a18743a97eddd0a7" > </td>
					<td><input type="submit" value="去付款"></td>
				</tr>
			</table>
			
			</form>
		</div>
  </body>
</html>
