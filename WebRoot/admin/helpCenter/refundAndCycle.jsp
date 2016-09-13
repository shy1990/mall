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
		      <p><strong>退款方式和周期</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		     <strong>商品退款方式： </strong>
		     <p>
		           1.如果您支付是使用的是易宝，商城将会直接将款项退回您的易宝账号。<br/>
                   2.如果您支付时使用的是网上银行，商城会将款项退回您的银行账号。<br/>
                   3.3.可以退款到您帐户的钱包里边。
		     </p>
		     <strong>商品退款周期：</strong>
		     <p>
		             您申请退款后经工作人员确认审核通过，仓库在接收到客户退货的商品、发票原件、保修卡、检测报告、附件、赠品齐全，并且检验符合退换货标准后，商城将会直接将款项退回您用于支付的易宝账号或银行账号，但具体的到账时间还取决于易宝和对应银行的到账时间。一般在商品入库后，3-5个工作日内可完成退款。
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
