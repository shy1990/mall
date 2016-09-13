<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>退款申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  退款申请页<br>
     <form action="" method="post">
     <!-- 只退款只需要填写金额和原因 -->
     	
     	金额：<input type="text" name="returnMoney" /><br>
     
     	取消原因：
     	<textarea rows="" cols="" name="cause" ></textarea><br>
     	
     	<!-- 隐藏的订单Id -->
     	<input type="hidden" name="id" value="${msg.obj.id}" />
     	<input type="button" value="提交" />
     </form>
  </body>
</html>
