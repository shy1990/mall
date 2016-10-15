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
							<strong>申请退换货</strong>
						</p>
					</div>
					<div class="about_mian_ret_02">
						<strong>细则</strong> <strong>三际采购网承诺符合以下条件，自签收日期起30日（含当天）内可以退货。具体退换货标准如下：
						</strong>
						<div align="center">
							<img src="images/shxize_01.jpg" width="100%" align="middle" />
						</div>
						<strong>注意事项（请您务必仔细阅读）：</strong>
						<p>
							1、关于退换货有效期的判断退换货有效期是以您签收日（含）起算，以您在三际采购网的"我的订单"页面成功提交退换货申请为准。例如您在3月1日签收了该商品，那么您最晚需在3月31日在三际采购网提交退货申请，或最晚在3月31日提交换货申请，超出这个日期则不能享受退换货服务。为保障您的权益，请您务必在第一时间到三际采购网“我的订单”页面提交退换货申请。如果是质量问题，请尽快将商品送到售后网点进行检测。<br />
							2、关于退回商品是否满足入库标准的判断首先，您需要到三际采购网个人中心“我的订单”页面提交退换货申请，我们一般会在24小时内为您审核。退换货申请初审通过后，请您尽快将必要物品寄回，仓库在确认物件齐全且符合退换货标准后进行入库，并换新或退款。不同退换货场景下需要寄回的物品请参照下表：
						</p>
						<p>
							<strong> 说明：</strong>
						<p>
							①退货需寄回发票，换货无需寄回发票。<br /> ②发票、保修卡、检测报告等凭证不能进行涂改。<br />
							③商品的完整标配指原厂包装内的充电器、数据线、保修卡等，具体请参考对应商品详情页的“包装清单”。<br />
							④完整原厂包装指商品出厂时的包装，包括彩印纸盒和纸盒里的内胆包装。建议外包装在收货之日起保留15天。<br />
							⑤仅指在网点检测的用户需寄回，直接寄回商城仓库检测用户无需寄回。<br /> ⑥赠品指价格为零的赠送商品。
						</p>
						<div align="center">
							<img src="images/shxize_02.jpg" align="middle" />
						</div>
						<strong> 关于物流费用归属的判断 </strong>
						<p>
							1）开箱损:
							 时间由之前的到货当天的5小时内，调整为收货当天反馈有效(与收款时间无关），过夜视为超时；例：2016.6.6到货，当日发现问题只要在当天均可报备开箱损，过了当日（2016.6.7）无效。苹果激活无效。
							报备方式：订单号+串号+报备原因+图片证明<br /> 
							2）无理由（客户原因）退货：
							 运费由客户自行承担，不支持混发，如混发，混发快递所涉的所有费用视为无理由退货费用；
							发现无理由（客户原因）退货使用公司顺丰月结号的，按20元/台收费。<br /> 
							3） 非我司出售的机器:
							      对于不开机、无法获取串码的售后机，送修官方售后获取串号核实是否我司所售。我司销售正常售后流程，非我司销售机器所产生的任何费用由代理商承担(物流费用、官方报价等)代理商可以和客户收取费用。
						</p>
						<strong>关于可更换商品的型号说明</strong>
						<p>商品换货只支持更换同型号、同规格、同颜色的商品。</p>
						<strong>关于配件及赠品的服务政策说明 </strong>
						<p>
							1）请您注意区分标配、配件和赠品的不同概念：原厂包装里面的为标配，额外购买的为配件，随活动赠送且价格为零的为赠品。<br />
							2）标配若有质量问题建议您到网点申请办理。三包范围内的配件，例如电池、外接有线耳机、充电器、数据卡，可按三包政策进行退换货和保修（保护壳、贴膜、饰品等非三包类商品请联系商城客服咨询办理）。三包范围内的赠品如果有质量问题可以申请换货。
						</p>
						<strong>其他不予办理退换货的情况</strong>
						<p>
							1)任何非三际采购网出售的商品（序列号或串号不符）。<br /> 2)过保商品（超过三包保修期的商品）。<br />
							3)未经授权的维修、误用、碰撞、疏忽、滥用、进液、事故、改动、不正确的安装所造成的商品质量问题，或撕毁、涂改标贴、机器序号、防伪标记。<br />
							4)未完整寄回三际采购网要求寄回的物品（请参考上文“寄回清单”表格）。<br />
							5)无法提供商品的发票、保修卡等三包凭证或者三包凭证信息与商品不符及被涂改的。<br />
							6)以物流损坏、漏发或错发商品为原因的退换货，客户已签收，并且无法提供物流公司开具的相关凭据。<br />
							7)虚拟商品、软件类商品，不参与商城数码产品的三包服务。<br /> 8)购买时明确说明不支持退换货的商品。
						</p>
						<strong>免责条款 </strong>
						<p>
							1)三际采购网网页上提供的图片，由于拍摄灯光变化及显示器色差等原因可能与实物有细微区别，效果演示图和示意图仅供参考（图片为合成图、模拟演示图），有关产品的外观（包括但不限于颜色）请以实物为准。<br />
							2)限于篇幅，三际采购网中所包含的信息（包括但不限于产品规格、功能说明等）可能不完整，请以有关产品使用说明书的具体信息为准。<br />
							3)由于您的技术水平、使用环境限制造成的问题，三际采购网将不予受理退换货。<br />
							4)对于存储类商品，三际采购网概不提供数据导出服务，请务必在返回三际采购网前将里面的数据自行导出，否则若有数据遗失、损坏、泄露等三际采购网概不承担相应的责任。<br />
							5)手机、平板电脑、上网卡等商品通过软件升级可以排除故障的情况下，只要送至品牌授权售的服务中心升级即可，不予退换货。
						</p>
						<strong>退换货服务承诺</strong>
						<p>
							1、网上申请，全国联保请您在有退换货需求的情况下第一时间登录我们的网站 www.3j1688.com，在"我的订单"页面提交退换货申请，三际采购网工作人员会尽快受理与审核。如需帮助，请您联系客服。<br />
							2、7日×10小时工作制三际采购网售后审核人员工作时间：周一至周日，上午9：00至12：00，下午13：30至20：30。一般情况下，我们会在24小时内对您的申请进行审核，初次审核通过仅表示三际采购网受理您的退换货申请，但不代表同意您的退换货，具体判断有赖于商品质量问题检测报告以及其他退换货条件的满足。<br />
							3、热线咨询服务<br />
							1)在产品退换货期限内，如果您有相关问题需要咨询，欢迎您联系三际采购网客服热线。400-937-1688<br />
							2)超出退换货期限，但处在保修期内，欢迎您拨打售后维保热线：400-937-1688<br /> 4、退换货服务处理周期<br />
							1）退货处理周期：自您退回的完整商品及相关物件成功入库之日算起，一般情况下3-5个工作日（不包括银行到账周期）。<br />
							2）换货处理周期：自您退回的完整商品及相关物件成功入库之日算起，一般情况下7个工作日内为您寄出换货商品。<br />
							3）如遇国家法定节假日、与退换货政策存在出入、客户个人原因或者有其他需要协商的问题，则可能会增加协商和处理的时间。
						</p>

						<p>
							<strong>退换货流程</strong>
						<p style="text-indent: 2em;">
							在您有退换货需求时，请第一时间到商城的“我的订单”页面提交退换货申请，并按照以下流程进行退换货的操作，以便更快的获得服务：</p>
						<div align="center">
							<img src="images/thliucheng.jpg" align="middle">
						</div>
						<p>
							①仅表示三际采购网受理您的退换货申请，但不代表同意您的退换货，具体判断有赖于商品质量问题检测报告以及其他退换货条件的满足。<br />
							②您的退换货申请状态将显示为“审核未通过”，具体原因将会显示在您的退换货申请详情页面。<br />
							③商品质量问题可选择直接寄回三际采购网总部进行检测，如检测不符合退换货标准将原路到付寄回或返厂自费维修，运费由您承担。<br />
							④签收后请务必要求投递员在场共同检验包裹，若发现物流损坏、缺件或发错货等情况，请当场拒收并要求快递员存证，同时请您拍照存证，并在商城提交退换货申请。若已签收，需要提供物流公司开具的凭据，并在退换货申请受理之后提交附件，也可联系客服办理。<br />
							⑤网点只可换货，不可退货。
						</P>
						<strong>操作指导</strong>
						<p>
							<strong>步骤1：请第一时间提交退换货申请</strong>
							1）为确保您在国家三包的有效期内（7天包退、15天包换）完成退换货申请，请第一时间在商城的“我的订单”页面填写并提交退换货申请，我们将以您提交在线申请的时间判断是否在退换货期限内。<br />
							2）如您不确定手机是属于故障问题还是操作问题，可咨询三际采购网，客服热线400-937-1688。也可下载相关产品手册进行查阅。<br />
							<strong>步骤2：客服审核</strong>
							1）商城客服一般会在您提交退换货申请后的24小时内为您审核。如遇法定节假日，审核时间可能会延长。<br />
							2）初次审核通过仅表示三际采购网受理您的退换货申请，但不代表同意您的退换货，具体判断有赖于质量检测报告以及其他退换货条件的满足<br />。
							3）对于不符合退换货标准的申请（如超出退换货时间、商品不支持退换货等）客服审核后，您的退换货申请状态显示为“审核未通过”，具体不通过原因将会显示在您的退换货申请详情页面。<br />
							<strong>步骤3：客户寄回三际采购网总部检测或到网点开具检测报告</strong>
							如果是商品质量问题的退换货，请您在提交退换货申请并初次审核通过后，选择寄回三际采购网总部进行检测开具检测报告，也可凭发票和保修卡尽快到授权服务网点进行商品的检测，并开具检测报告。请您尽快完成此步骤，因为这会影响到您最后完成退换货的时间。<br />
							<strong>步骤4：寄回商城检测协议阅读及判断</strong>
							请您认真阅读寄回商城检测的流程与协议，对于寄回后检测质量问题或功能性障碍属实的，商城会为您办理退换货，对于寄回后检测质量问题或功能性障碍不属实或手机人为损坏①的，商城将原物到付寄回。①人为损坏包括但不限于：未经授权的维修、外力造成的碰撞破损、进液以及外观刮花划伤、掉漆等。<br />
							<strong>步骤5：检测报告阅读及判断</strong>
							请您认真阅读网点开具的检测报告，对于报告中说明属于商品质量问题或功能性障碍的，可以寄回商城进行退换货。<br /> <strong>步骤6：客户准备寄回物件并联系快递公司</strong>
							1）退换货申请初审通过后，请您尽快将必要物品寄回。不同退换货场景下需要寄回的物品请参照下表：<br />
							2）寄回时请务必选择运费到付，建议首选圆通。<br /> <strong>步骤7：退换货及退款办理</strong>
							1）办理地点：如果是质量问题，为节约您的时间，建议您到附近的售后网点当面更换，可先电话向网点咨询相关事宜。您也可选择在商城进行换货，但退货只能寄回三际采购网办理。<br />
							2）退货及退款说明：自您退回的完整商品及相关物件成功入库之日算起，商城会尽快将款项退回您用于支付的支付宝账号或银行账号，一般情况下3-5个工作日完成退款处理，但具体的到账时间还取决于支付宝和对应银行的到账时间。<br />
							3）换货说明：自您退回的完整商品及相关物件成功入库之日算起，一般情况下7个工作日内为您寄出换货商品。<br />
							4）运费说明：退换货商品寄回三际采购网时请统一选择到付。符合国家三包规定的质量问题、物流损坏或商城原因不扣运费；客户个人原因统一扣20元运费（超出顺丰首重和偏远地区除外）。

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
