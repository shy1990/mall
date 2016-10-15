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
		      <p><strong>支付方式</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		     <p style="text-indent:2em;">
		     	1.如果您想使用网上银行（包括开通了网银功能的储蓄卡和信用卡）支付货款，请在“提交订单”页面选择“银行卡支付”，然后选择您网银所属的银行
				2.提交订单后，商城页面会提示您“立即支付”，请单击立即支付按钮；商城页面会自动进入您所选择银行的网银支付界面；
		     </p>
		     <p style="text-indent:2em;">
		             集团属下共有8个独立法人公司，分别为山东金怡华通信、山东三际科技、山东戴革通信，山东三际电子商务、
		             济南天鹰电子、山东怡华时代伯乐、山东德信怡佳、山东巨龙赢。
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
