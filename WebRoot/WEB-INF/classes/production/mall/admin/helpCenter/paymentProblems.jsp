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
		      <p><strong>支付常见问题</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		      <div>
			    <strong>进行网上在线支付前，银行卡需要开通网上银行吗？</strong>
				<p>您好，普通银行卡的“网银支付”，需要事先开通网上银行（具体程序请您咨询相关银行）</p>
				<strong>在线支付会有手续费吗？</strong>
				<p>您好，三际采购网网上支付是不收取任何手续费用，如果在支付过程中有费用收取情况，请您咨询相关银行。</p>
				<strong>三际采购网支持境外银行卡支付吗？</strong>
				<p>您好，很抱歉，三际采购网暂时不支持境外银行卡支付，我们在积极地与银行方面协商，争取早日实现此功能，我们会及时发布通知，请您持续关注。</p>
				<strong>银行支付有限额要求吗？</strong>
				<p>您好，为了保证在线支付的安全性，银行会根据各个商户、消费者情况的不一致进行支付金额限制，由于各银行的限额标准并不完全一致，并且会随国家政策进行调整，请您在办理网上银行时，问明限额标准，或者拨打银行热线电话进行咨询。您也可以参考商城页面各个银行限额标准。</p>
				<strong>三际采购网有支付限额吗？</strong>
				<p>您好，三际采购网是未对用户做出支付限额的，限额都是您支付银行所限制，标准不一，如您有问题建议您咨询对应银行客服。</p>
				<strong>如何知道在线支付是否成功？</strong>
				<p>您好，一般情况下，您支付成功后在三际采购网的“订单中心”中，此笔订单的状态是“支付完成”。您的每笔在线支付的订单，支付成功后，银行页面都会有“支付成功”的字样进行提示，如果没在当时就看到支付结果，您也可以通过银行网站查询交易明细，或者致电银行的客服。</p>
				<strong>三际采购网的付款流程是怎样的？</strong>
				<p>您好，三际采购网目前支持网上银行（包括开通了网银功能的储蓄卡和信用卡）付款与支付宝付款，请在支付方式处选择适合您的方式。您提交订单后，商城页面会提示您“立即支付”，请单击立即支付按钮，商城页面会自动进入支付宝或对应银行的网银支付界面，按照网页提示进行操作便可成功付款。</p>
				<strong>三际采购网支持什么支付方式？</strong>
				<p>您好，商城目前支持网上银行在线支付、支付宝、支付宝扫码支付、财付通在线支付、信用卡快捷支付，请在支付方式处选择适合您的支付方式。</p>
				<strong>支付时提示我超过支付限额怎么办？</strong>
				<p>您好，如果您使用银行卡支付，不同的银行都有不同的支付限额，建议您拨打银行客服热线提高支付限额，或使用第三方支付平台，将钱分批存入第三方支付平台后进行支付。</p>
				<strong>付了款但又提示我没货怎么办？</strong>
				<p>您好，您在下单的时候网页将有提示，无货商品不支持购买只支持到货通知。</p>
				<strong>如何修改我的支付方式？</strong>
				<p>您好，在您未支付前，都是可以点击“去付款”重新选择支付方式的。</p>
				<strong>如何联系银行开通网上银行？</strong>
				<p>您好，如您是第一次进行网上在线支付，建议事先拨打银行卡所属发卡银行的热线电话，详细咨询可在其网上进行在线支付的银行卡种类及相关开通手续。</p>
				<strong>下订单后一直未付款，订单将保留多长时间？</strong>
				<p>您好，您下单成功后，订单一般将为您保留24小时，实际以成功提交订单后所规定的完成支付时间为准，请您及时支付。</p>
				<strong>网银查询已经支付成功，但订单仍显示待支付怎么办？</strong>
				<p>您好，商城后台更新支付状态可能需要一定的时间，如果您的确成功支付了，请您保留支付成功凭证，并主动联系客服人员提供您的订单号和联系方式，我们会尽快核实为您更新支付状态。建议您半小时后再行查看支付状态，给您带来不便，实在抱歉。</p>
				<strong>同一个订单超出支付限额，能不能分开支付？</strong>
				<p>您好，同一个订单，只能一次性完成支付，建议您拨打银行客服热线提高支付限额，或使用第三方支付平台，将钱分批存入第三方支付平台后进行支付。</p>
				<strong>客户支付时提示商家限额，无法支付？</strong>
				<p>您好，三际采购网未设置任何支付限额，您无法支付可能是您的开户银行或者支付宝等第三方支付平台有限额导致，建议您及时联系他们的客服提高限额处理。</p>
				<strong>为什么现在支付不了（或点击不了支付按钮，或支付不成功）？</strong>
				<p>您好，如果您现在支付不了，或点击按钮没有反应，可能是集中支付的人过多。由于支付是可以在24小时内完成的（活动订单除外），不影响您已抢购到的订单，您可以稍后完成支付。（提醒：但请注意，超时未支付订单会被作废，请安排好您的时间。）</p>
				<strong>支付成功之后，个人点击“取消订单"按钮，退款如何办理？</strong>
				<p>您好，我们会在您取消订单后7个工作日内为您退款</p>
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
