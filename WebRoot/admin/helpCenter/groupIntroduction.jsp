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
		      <p><strong>集团介绍</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		      <p style="text-indent:2em;">
		                  山东德信集团——山东省内具有综合性实力的手机营销服务平台，创建于2008年。从始至今，一直专注于手机批发零售的核心业务，
		                  建立起集运营商平台、电子商务（B2B、 B2C）、物流、售后服务、投资管理等多产业版块于一体的现代商业运营模式，全力打造
		                  智慧型、 创新型和服务型的运营集团。
		     </p>
		     <p style="text-indent:2em;">
		               山东德信集团坐落于山东省济南市天桥区凤凰山路3号凤凰山电子商务产业园内，办公面积近一万平方米，办公环境优越，现有员工900余人。
		               集团为员工配备宿舍、食堂、健身房、多功能厅等。下属公司有：山东三际电子商务有限公司、山东网格供应链管理有限公司、山东金怡华通
		               信发展有限公司、山东戴革通信数码有限公司、济南新松联信息科技有限公司、山东怡华时代伯乐股权投资管理有限公司、济南天鹰电子商务
		               有限公司、山东奇酷通讯科技有限公司、山东合酷胜通信科技有限公司。
		     </p> 
		     <p style="text-indent:2em;">
		              德信集团，在近二十年的奋斗历程中，始终做到“至臻品质，挚诚服务”，成为华为、荣耀、中兴、酷派、联想、小米、魅族、奇酷、三星等国
		              内外知名品牌核心代理商，同时成为山东移动、山东电信、山东联通战略合作伙伴。
		     </p>
		     <p style="text-indent:2em;">
		             创新是第一生产力。集团实行互联网+企业管理，建立现代企业管理制度和标准化业务流程。公司高管团队绝大多数成员，来自于市场一线，拥有
		             平均超过15年以上的通信行业运营经验，具备强大的通信市场经验储备与零售运营管理能力。
		     </p>
		     <p style="text-indent:2em;">
		             集团董事长徐桂琴女士是山东省工商联常委、山东省电子通信业商会会长。在徐董事长的带领下，集团励精图治，专注于电子商务与运营商业务，
		             秉承“以德为本，以信立业”的宗旨，坚持“以客户为中心，以奋斗者为本，长期坚持艰苦奋斗”的核心价值观，2014年度实现营业额32亿元，
		     2015年实现营业额40.98亿元。
		     </p>
		     <p style="text-indent:2em;">
			     旗下山东三际电子商务有限公司（以下简称三际数码）业务涉及手机及手机配件销售、智能终端、软件开发应用和平台开发运营，主要是基于天猫/淘宝、
			     当当网、亚马逊等第三方电商平台开展零售业务。
		     </p>
		     <p style="text-indent:2em;">
		              从2010年开始，三际数码经过迅猛发展，经营模式日渐成熟，现已成为手机线上销售天猫(淘宝)排名前列商家；是全省具备实力的手机零售商，
		              手机销售量为250万台，月手机平均销量达到20万台以上，全国累计服务用户超过500万人次，会员达400余万人。公司已成为山东通信界在全
		              国的一张名片，销售能力及规模得到业界的高度认可，形成了良好的口碑。
		     </p>
		     <p style="text-indent:2em;">
		             旗下山东网格供应链管理有限公司是集团为适应市场发展变化而注册成立的一家将传统零售批发升级再造并与互联网相结合的新型电商公司。自营
		     B2B业务，自建以手机及周边产品为主的独立B2B垂直网站；致力于服务中小卖家，以打造线下零售电商化为己任。通过整合上游厂家，运营商、
		             银联支付、物流体系，为广大手机零售卖家提供全面的产品型号、具备优势的产品价格、完善的运营商政策、全新的行业资讯以及完善的售后服务
		             体系，让采购更简单，让生意更好做。在山东区域内已布点17地市80个县区，684个乡镇，注册会员10000多家，活跃用户8000多家。
		     </p>
		     <p style="text-indent:2em;">
		             网格通过全品类供应链，全电商化运营管理，全国零售商覆盖等强大优势，上线至今已得到了运营商、厂家、下游终端客户的充分认可。所有交易数据
		             实时呈现，为市场经济研究提供可靠的科学数据具有很高的推广价值。
		     </p>
		     <p style="text-indent:2em;">
		             旗下山东金怡华通信发展有限公司成立于1998年，从档口放货到深度分销，服务于山东及周边省份代理商，为山东电信的实体渠道、直销渠道、
		             电子渠道做好服务支撑。经过十几年的迅猛发展，网络覆盖山东17地市、144县区及重点乡镇，线下客户3000多家，核心客户及门店1400多家。
		             自2010年开始与中国电信合作，2012年成为山东电信的战略合作伙伴，华为金钻俱乐部成员，酷派GP客户，联想顾委会成员等。与山东省内95%以上
		             的手机经销商保持着良好的业务合作关系，是山东省内终端分销领域实力强、规模大、影响广的专业分销商之一。
		     </p>
		     <p style="text-indent:2em;">
			     旗下山东戴革通信数码有限公司，2003年，成功完成三星手机的山东省移动入围，开了三星第一个省级代理商移动入围的先河，一直是山东移动省级核心战略
			     供货服务平台。
		     </p>
		     <p style="text-indent:2em;">
		              山东戴革在产品销售与推广、物流配送和售后服务体系方面,建立了高效的管理机制。自进入4G时代以来，山东戴革与山东各地市移动和社会渠道客户开展了更
		              加广泛深入的合作,并提供了优质的产品和服务；同时也承载了三星、华为、酷派、中兴等主流厂商的移动4G智能终端在山东市场的拓展工作,成为众多品牌厂
		              商在山东市场的主要合作伙伴。在2013年成为三星、中兴、华为、酷派山东省级核心平台。
		     </p>
		     <p style="text-indent:2em;">
		     2014年，与深圳时代伯乐创业投资管理有限公司强强联合，成立山东怡华时代伯乐股权投资管理有限公司，进军通信行业项目直投、PE、资产管理等全新业务领域。
		     </p>
		     <p style="text-indent:2em;">
		     2015年，济南新松联信息科技有限公司成立，开启国包商合作新模式。
		     </p>
		     <p style="text-indent:2em;">
		     2015年，山东奇酷通讯科技有限公司成立，德信集团成为山东360奇酷超级品牌持有人、品牌加盟商。
		     </p>
		     <p style="text-indent:2em;">
		     2015年，山东合酷胜通信科技有限公司成立，开启厂商一体化模式。
		     </p>
		     <p style="text-indent:2em;">
		            在集团高速发展的同时，集团和徐桂琴董事长也先后收获诸多荣誉。
		     </p>
		     <p style="text-indent:2em;">
		            集团下属公司先后被评为“山东省商贸流通先进企业 ” “山东省十强通讯经营企业”“山东省工商联电子通信业商会会长单位”“山东省电子通信诚信经营规范服务
		            示范单位”“全国线上零售百强企业 ” “天猫智囊团成员”“天猫手机行业委员会委员” “联想MVP杰出贡献奖”“诺基亚最佳贡献奖” “济南市守合同、重信用企
		            业”“山东省电子商务示范企业” “山东省优秀电子商务企业” “卓越电商”等。
		     </p>
		     <p style="text-indent:2em;">
		            徐董事长个人获得“山东省优秀民营企业家”“山东省通信行业十大杰出人物”“2014年度风云人物奖”“2014影响济南年度创业精英”“济南市民营企业先进个人”
		     “2015年度风云人物奖”等。
		     </p>
		     <p style="text-indent:2em;">
		           徐董事长积极服务社会，回报社会，先后获得“山东省工商联系统抗震救灾先进个人”、“齐鲁光彩奖章”、“改革开放30年山东民营经济最具影响力人物”、山东
		    “最美巾帼劳动者”等荣誉称号；
		     </p>
		    <p style="text-indent:2em;">
		            我们坚信，德信人必将在建设现代化企业的崭新征程中焕发出更大的创业激情，展现出卓越的创新精神，创造出超凡的经营业绩。在德信集团企业文化的引导下，
		            一定能够使德信集团不断走向辉煌，发展成为通讯行业的标杆性企业。
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
