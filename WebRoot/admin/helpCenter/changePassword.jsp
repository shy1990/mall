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
		      <p><strong>修改密码</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		     <p>
		              点击顶部通栏的“我的账户”进入会员中心
		     </p>
		     <div align="center">
		      <img src="images/wdzhanghu.jpg" align="middle">
		     </div>
		     <p>直接点击右侧账户管理面板下的”修改密码”，或者点击左侧导航“账户安全“进入账户安全页面然后点击登录密码后方的”修改”进入修改密码界面；</p>
		     <div align="center">
		      <img src="images/wdzhanghu_02.jpg" align="middle">
		     </div>
		     <p>如果未绑定手机，则先点击“绑定手机“进行验证绑定。然后再设置新的密码</p>
		     <div align="center">
		      <img src="images/bdshouji.jpg" align="middle">
		     </div>
		     <p>如果已经绑定，则直接验证，并设置新密码</p>
		     <div align="center">
		      <img src="images/szxmima.jpg" align="middle">
		     </div>
		     <div align="center">
		      <img src="images/zhanquan_02.jpg" align="middle">
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
