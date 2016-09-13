<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三际手机采购网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/staticLink.css">
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="js/helpCenter/helpCenter.js"></script>
  </head>
  
  <body>
    <div id="container">

	<div id="header">
	<h1></h1>
	 <jsp:include page="../common/head.jsp"></jsp:include>
	</div>
	
	<div id="content">
		<div class="about_mian">
		  <jsp:include page="../common/aboutNav.jsp"></jsp:include>
		  <div class="about_mian_ret">
		    <div class="about_mian_ret_01">
		      <p><strong>修改地址</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		     <p style="text-indent:2em;">
		               半年只有一次修改机会，并且只能修改县级以下单位；如有特殊情况，请拨打客服或者片区业务经理电话联系说明。
		     </p>
		    </div>
		  </div>
		<div class="clear"></div>
</div>
			
	</div>
	
	<div id="footer">
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
	
	</div>
  </body>
</html>
