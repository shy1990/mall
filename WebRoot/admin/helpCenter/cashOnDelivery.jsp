<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/staticLink.css">
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
						<p>
							<strong>货到付款</strong>
						</p>
					</div>
					<div class="about_mian_ret_02">
						<strong>货到付款</strong>
						<p style="text-indent:2em;">1.您可以通过POS机刷卡支付货款：POS机刷卡：目前仅支持带有银联标识的银行卡，
							信用卡POS机刷卡消费赠送积分以各银行为准，具体信息请致电发卡行确认。 </p>
						<p style="text-indent:2em;">2.会员在三际采购网每单购买3台以上手机或者配件满300元，即可享受免费配送的服务
							（偏远地区或选择极速达配送服务方式除外）否则需自付运费6元。</p>

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
