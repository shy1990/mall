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
		      <p><strong>查询订单</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		     <strong>查询订单</strong>
		     <p>点击顶部通栏的“我的账户”进入会员中心</p>
		     <div align="center">
		       <img src="images/hyzhongxin.jpg" align="middle">
		     </div>
		     <p>
		               点击左侧导航的“我的订单”或者订单概览后面的“查看全部”进入订单中心；
		     </p>
		     <div align="center">
		      <img src="images/wddingdan.jpg" align="middle">
		     </div>
		     <p>然后在订单搜索框，输入订单号或者商品名称来查询筛选相应订单</p>
		     <div align="center">
		      <img src="images/ddguanli.jpg" align="middle">
		     </div>
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
