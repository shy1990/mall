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
							<strong>保修政策</strong>
						</p>
					</div>
					<div class="about_mian_ret_02">
						<p style="text-indent:2em;"></p>
						<strong> 一、服务条款</strong>
						<p style="text-indent:2em;">凡在三际采购网购买的终端产品，包括：手机，移动宽带，家庭终端，智能设备，都可享受我们为您提供的三包服务（积分礼品、外壳、饰品除外）。</p>
						<strong>二、主机保修条例</strong>
						   <p style="text-indent:2em;">
							1.商品主机（请在签收时确认，不宜退货的商品除外）自订单日期起，30天内商品完好，在产品包装和配件等齐全且不影响二次销售，同时手机外观无损且无人为损坏等问题，可享受“30天退货”</P>
							<p style="text-indent:2em;">2.订单日期起，30天内如出现非人为损坏的性能故障，凭官方授权售后网点检测结果，可选择退货或更换同型号同规格商品或者免费维修服务。</P>
							<p style="text-indent:2em;">3.商品订单日期起主机如在两年内出现非人为损坏的性能故障，消费者可在三际采购网享受免费维修服务。</P>
							<p style="text-indent:2em;">4.商品外观有任何磨损或刮花的，均不享受退换机服务。</p>
						<strong> 三、配件保修细则</strong><br/>
							<p style="text-indent:2em;">
							1.官方标配自带的的原装配件如有质量问题同样享受三包服务充电器保修三个月，原装电池六个月，原装耳机三个月。</p>
							<p style="text-indent:2em;">2.单独购买的配件根据相应配件质保政策享受质保服务（贴膜，外壳，饰品等保护类配件除外）。</p>
						<strong>四、保修条款限制 </strong><br/>
							<p style="text-indent:2em;">1.超过保修期；</p>
							<p style="text-indent:2em;">2.手机外壳，饰品，赠品等不享受三包服务； 3.未按产品使用说明书要求使用、维护、保养造成损坏的；</P>
							<p style="text-indent:2em;">4.由于使用失误如坠落、挤压、浸水而造成的损坏； 5.由于水灾、火灾、雷击等不可抗力造成的损坏；</P>
							<p style="text-indent:2em;">6.由非官方授权维修中心维修过的机器； 7.使用非原厂配件，导致手机故障或事故的；</P>
							<p style="text-indent:2em;">8.任何非三际采购网出售的产品，及产品上表明的型号、编号和制造号已经更改、删除、 移动或不可辨认；</P>
							<p style="text-indent:2em;">9.只包括硬件保养服务、一切配置及附件、如软件、光碟、及用户指南等均不包括在保养服务内；</P>
							<p style="text-indent:2em;">10.不包括任何软件导致问题，评估手机故障起因为硬件或软件所致，三际采购网保留最终决定权；</P>
							<p style="text-indent:2em;">11.不负责恢复工厂模式外的服务。如果用户要求安装软件，需用户提供所需的软件且三际采购网不承担任何责任；</P>
							以上所列，若有变更，以本公司新制定的有关规定或国家更新的相关法律法规为准。具体保修条款请参照商品说明书保修条例，也可联系业务核实。</P>

					</div>
					 <div align="center">
		                     <img src="images/bxzc.png" width="100%" align="middle"/>
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
