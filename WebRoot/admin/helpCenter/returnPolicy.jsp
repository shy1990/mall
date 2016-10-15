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
		      <p><strong>退换货政策</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		    <strong> 总则:</strong>
		     <p style="text-indent:2em;">
				三际采购网所售商品均为正品行货，三际采购网将严格按照国家三包政策，对所售商品提供30天（含）退货、30天（含）换货、30天调货和保修服务。属于三包范围内的商品，保修期内您可以凭保修卡在全国各地品牌授权的售后服务中心享受保修服务。保护壳、贴膜、饰品等商品请联系客服协商办理。(苹果三星高端机不支持30天退换货，仅支持办理开箱损。具体请联系业务核实。)</p>
				<strong>温馨提示：</strong>
				<p style="text-indent:2em;">1.请仔细阅读以下的细则及注意事项，以免为您的退换货办理带来不必要的耽误和麻烦。</p>
				<p style="text-indent:2em;">2.请您在寄回物品前先到三际采购网个人中心“我的订单”页面提交退换货申请，否则三际采购网无法受理。</p>
				<p style="text-indent:2em;">3.如用顺丰以外其他快递公司到付退回退换货商品，仓库将直接拒收退回。</p>
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
