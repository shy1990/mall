<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际数码三际手机采购网</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="js/home/zzsc.css" rel="stylesheet" type="text/css" />
<style>
        .hello-header {
            min-height: 16.43px;
            padding: 10px;
            background: #ffffff;
        }

        .modal-body {
            position: relative;
            padding: 0;
        }

        .text01{
            font-size: 16px;
            color:#9d9d9d ;

        }

        .text02{
            font-size: 16px;
            color: #FF0000;
        }

        .text03{
            font-size: 20px;
            color: #454545;
            font-weight: bold;
        }

        .text04{
            font-size: 20px;
            text-decoration: underline;
            font-weight: bold;
            color:#0066ff;
        }

        .text05{
            font-size: 12px;
            color: #d1d0d0;
        }

        /*bootstarp css*/
        .modal {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 1050;
            display: none;
            overflow: hidden;
            -webkit-overflow-scrolling: touch;
            outline: 0;
        }

        .fade.in {
            opacity: 1;
        }
            .modal-dialog {
                width: 600px;
                margin: 30px auto;
            }
            .modal-dialog {
                position: relative;
                width: auto;
                margin: 10px;
            }

        .modal-title {
            margin: 0;
            line-height: 1.42857143;
        }

        .modal-content {
            position: relative;
            background-color: #fff;
            -webkit-background-clip: padding-box;
            background-clip: padding-box;
            border: 1px solid #999;
            border: 1px solid rgba(0,0,0,.2);
            border-radius: 6px;
            outline: 0;
            -webkit-box-shadow: 0 3px 9px rgba(0,0,0,.5);
            box-shadow: 0 3px 9px rgba(0,0,0,.5);
        }
        .close {
            float: right;
            font-size: 21px;
            font-weight: 700;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            filter: alpha(opacity=20);
            opacity: .2;
        }


    </style>
<style>
.fr {
	float: right;
}

.fl {
	float: left;
}

.cf {
	clear: both;
}

.sj-show-area {
	height: 565px;
	clear: both;
}

.sj-show-area-content {
	width: 1095px;
	margin-left: auto;
	margin-right: auto;
}

.sj-show-area-head {
	height: 75px;
	text-align: center;
	background: url("./images/index/head-bg.png") no-repeat center;
}

.sj-show-area-head h2 {
	display: inline-block;
	color: #fff;
	font-size: 28px;
	line-height: 55px;
}

.sj-goods-big {
	margin: 10px;
	height: 475px;
	width: 335px;
	border: #f02424 solid 2px;
}

.sj-goods-big img {
	width: 331px;
	height: 416px;
	border-bottom: #f02424 solid 2px;
}

.sj-goods-big-bottom p {
	display: inline-block;
	width: 180px;
	height: 53px;
	line-height: 53px;
	padding-left: 35px;
	font-size: 14px;
	color: #f00;
	border-right: #f02424 dashed 2px;
	font-weight: bold;
}

.sj-goods-big-bottom .price {
	font-size: 32px;
}

.sj-goods-big-bottom button {
	height: 30px;
	width: 100px;
	font-size: 14px;
	background-color: #f00;
	border: none;
	color: #fff;
	font-size: 14px;
}

.sj-goods-small {
	position: relative;
	margin: 10px 5px;
	width: 350px;
	height: 230px;
	border: #f02424 solid 2px;
}

.sj-goods-small img {
	width: 350px;
	height: 230px;
}

.sj-goods-small-bottom {
	position: absolute;
	left: 215px;
	top: 123px;
	height: 110px;
	width: 135px;
}

.sj-goods-small-bottom p {
	color: #f00;
	font-size: 14px;
	font-weight: bold;
}

.sj-goods-small-bottom .price {
	font-size: 28px;
}

.sj-goods-small-bottom button {
	margin-top: 15px;
	height: 30px;
	width: 100px;
	font-size: 14px;
	background-color: #f00;
	border: none;
	color: #fff;
	font-size: 14px;
}

.xiangying {
	text-align: center;
}

.xiangying img {
	width: 100%;
	heigth: auto;
	display: block;
}

.home_main_03 {
	width: 100%;
	margin-left: -365px;
}
/* 添加红包浮层 */
.modal { /* 如果给伪元素设置绝对定位，需要先设置相对定位 */
	content: "";
	display: none;
	position: fixed;
	/*也可以设置绝对定位*/
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
	z-index: 10000;
	background-color: rgba(0, 0, 0, 0.8);
}

.red-packet-modal {
	display: none;
	position: fixed;
	z-index: 10001;
	width: 704px;
	height: 435px;
	top: 50%;
	left: 50%;
	margin-left: -352px;
	margin-top: -250px;
}

.red-packet-modal img {
	width: 100%;
	height: 100%;
}

.btn-close {
	float: right;
}

.icon-close {
    left:500px;
	padding: 15px 15px 0 15px;
	position: relative;
	background: url(images/red-packet/CLOSE1.png) no-repeat;
	right: 120px;
	top: 20px;
}

.icon-close:HOVER {
	background: url(images/red-packet/CLOSE2.png) no-repeat;
}
 #myModal {
      position: absolute;
      z-index: 10000;
      top: 0px;
      left: 0px;
      background: rgb(50, 50, 50);
      background: rgba(0, 0, 0, 0.5);
      display: none;
      
    }
    #content {
      position: absolute;
      width: 1000px;
      z-index: 999;
      border-radius: 5px;
      display: none;
    }

    .ultlon_content_body {
      text-align: center;
    }

</style>

<!-- 弹框显示css-->
</head>

<body>
	<script type="text/javascript">
		(function() {

			var methods = [ "info", "log" ];
			var csl = {};
			for ( var i = 0; i < methods.length; i++) {
				csl[methods[i]] = function() {
				};
			}
			if (!window.console) {
				window.console = csl;
			}
		})();
	</script>
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" src="js/jquery.vticker-min.js"></script>
	<script type="text/javascript" src="js/home/home.js"></script>
	<script type="text/javascript" src="js/home/slides.js"></script>
	<%-- <script type="text/javascript" src="js/bootstrap.min.js"></script> --%>
	<script type="text/javascript" >	

		$(function() {
			//GetMemberNum("admin_home_homeMemberNum");//获取商家数量
			setNav();//导航栏居中初始化
			selectHot('sku', 'day', '5', 'admin_home_home_goodsHot');//获取热销手机
			showRetTop("1");// 买家服务和入门鼠标效果
			loadHomeNews();//加载资讯
			DealRoll();//成交状态循环
			homeFloorNews();
			ImgContent("HomeFloorContent1", "HomeFloorNav1");
			ImgContent("HomeFloorContent2", "HomeFloorNav2");
			ImgContent("HomeFloorContent3", "HomeFloorNav3");
			ImgContent("HomeFloorContent4", "HomeFloorNav4");
			ImgContent("HomeFloorContent5", "HomeFloorNav5");
			ImgContent("HomeFloorContent6", "HomeFloorNav6");

			$("#dialog_close").on("click", function() {
				$("#myModal, .modal-content").hide();
			});

			$("#myModal, .modal-content").show();
			conPosition();

			$("#ultlon_btn_cl").click(function() {
				$("#myModal,.modal-content").hide();
			});
			$("#myModal").click(function() {
				$("#myModal, .modal-content").hide();
			});
			//点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
			$(window).resize(function() {
				conPosition();
			});
		});

		function conPosition() {
			var oBackground = document.getElementById("background");
			var dw = $(document).width();
			var dh = $(document).height();
			oBackground.style.width = dw + 'px';
			oBackground.style.height = dh + 'px';
			var oContent = document.getElementById("content");
			var scrollTop = document.documentElement.scrollTop
					|| document.body.scrollTop;
			var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
			var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 3)
					+ scrollTop;
			//oContent.style.left = l + 'px';
			oContent.style.top = t + 'px';
		}
	</script>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>

	<!--==================红包浮层 领取红包==========================-->
	<div class="modal J_modal"></div>
	<div class="red-packet-modal J_modal">
		<a class="J_close btn-close"><span class="icon-close"></span> </a> <a
			href="qb/gohome.html"><img src="images/red-packet/hongbao.png"
			title="领取红包" alt="领取红包" /> </a>
	</div>

	<!--======================首页banner开始============================-->

	<div class="home_main">
		<div class="home_main_banner">
			<div class="home_main_01">
				<div class="home_main_01_let" style="overflow: hidden;">
					<div class="home_main_01_let_top">
						<span>在线买家数:
							<h5 id="admin_home_homeMemberNum">17652</h5> <img
							src="images/home_xias.jpg" width="11" height="14" /> </span>实时采购
					</div>
					<div class="home_main_01_let_txt">
						<ul>
							<s:iterator value="#request.itemList" var="item">

								<li title="${item.name}"><span><img
										src="images/chengjiao.jpg" align="left" /> </span> <span> <a
										href="goods/detail/<s:property value='#item.getGoodsNum()'/>.html"
										target="_blank"> <c:if test="${fn:length(item.name)>15}"> ${fn:substring(item.name, 0, 11)}...
					                 </c:if> <c:if test="${fn:length(item.name)<=15}"> ${item.name}
					                 </c:if> </a>(<s:property value="#item.getStorage()" />) </span> <span>
										<strong> <s:property value="#item.getNums()" />台 </strong> </span></li>
							</s:iterator>
						</ul>
					</div>
				</div>

				<map name="Maptwo">
					<area shape="rect" coords="389,177,499,288"
						href="http://www.3j1688.com/goods/detail/940.html" target="_blank">
					<area shape="rect" coords="508,180,622,287"
						href="http://www.3j1688.com/goods/detail/941.html" target="_blank">
				</map>

				<map name="Mapthree">
					<area shape="rect" coords="324,209,481,256"
						href="javascript:goListView('手机','金星');" target="_blank">
					<area shape="rect" coords="321,262,482,310"
						href="http://www.3j1688.com/goods/detail/923.html" target="_blank">
				</map>

				<map name="MapFour">
					<area shape="rect" coords="347,216,484,295"
						href="http://www.3j1688.com/goods/detail/1245.html?s=banner"
						target="_blank">
					<area shape="rect" coords="496,218,653,297"
						href="http://www.3j1688.com/goods/detail/1255.html?s=banner"
						target="_blank">
				</map>

				<div id="main">
					<div class="focus">
						<div id="xmSlide" class="xmSlide">
							<c:set var="guoqi" value="<%=new Date().getTime()%>"></c:set>
							
							<a> <img src="images/sxnote7hs.png?<%=Math.random()%>" /> </a>
							
							<%-- <a href="http://www.3j1688.com/goods/detail/1416.html?s=banner">
							<img src="images/hmpro.png?<%=Math.random()%>" /> </a> --%>
							
							<a href="http://www.3j1688.com/goods/detail/1345.html?s=banner">
							<img src="images/hm3xhyj.png?<%=Math.random()%>" /> </a>
							
							<!-- 配件活动 -->
							<a href="http://www.3j1688.com/special_161009/index.html?s=banner">
							<img src="images/jppjdjkh.jpg?<%=Math.random()%>" /> </a>
							
							<!-- 小米5s -->
							<a href="http://www.3j1688.com/goods/detail/1450.html?s=banner">
							<img src="images/xm5s.png?<%=Math.random()%>" /> </a>
							
							<%-- <a href="http://www.3j1688.com/special_161001/index.html?s=banner">
							<img src="images/STLB.png?<%=Math.random()%>" /> </a> --%>
							

							<%-- <a href="http://www.3j1688.com/special_161001/index.html?s=banner"> <img src="images/GQFJTZLB.png?<%=Math.random()%>" /> </a> --%>
							
							<%-- 
							<a href="http://www.3j1688.com/special_161001/index.html?s=banner">
							<img src="images/beihuoleiji.png?<%=Math.random()%>" /> </a> --%>
							
							<%-- <a href="javascript:goListView('配件','','','')">
							<img src="images/gqqtl.png?<%=Math.random()%>" /> </a> --%>
							
							<a><img src="images/apple720.png" alt="" width="695" height="336" border="0" usemap="#Map11">
								<map name="Map11">
								  <area shape="rect" coords="370,224,483,268" href="javascript:goListView('手机','','','苹果7')" target="_blank">
								  <area shape="rect" coords="486,224,679,269" href="javascript:goListView('配件','','','苹果')" target="_blank">
								</map> 
							</a>  
								
							<%-- <a href="http://www.3j1688.com/goods/detail/873.html?s=banner"><img
								src="images/xljJD-T.png?<%=Math.random()%>" /> </a>  --%>
								
							<%-- <a href="http://www.3j1688.com/goods/detail/1435.html?s=banner"><img
								src="images/mlMAX.png?<%=Math.random()%>" /> </a>  --%>
								
							<!-- <a>	<img src="images/quansheng.png?" alt="" width="695" height="336" border="0" usemap="#Map">
										<map name="Map">
										  <area shape="rect" coords="22,215,147,303" href="http://www.3j1688.com/goods/detail/1416.html" target="_blank">
										  <area shape="rect" coords="149,214,273,304" href="javascript:goListView('手机','','','红米note3合约')" target="_blank">
										  <area shape="rect" coords="275,213,399,304" href="javascript:goListView('手机','','','小米5合约')" target="_blank">
										</map>
							</a> -->


						</div>
					</div>
				</div>
				<div class="home_main_01_ret">
					<div class="home_main_01_ret_top">
						<ul>
							<li><a id="admin_home_homeServe_1"
								onmouseover="showRetTop('1')" href="javascript:showRetTop('1');">服务特点</a>
							</li>
							<li id="home_main_01_ret_top"><a id="admin_home_homeServe_2"
								onmouseover="showRetTop('2')" href="javascript:showRetTop('2');">买家入门</a>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div ID="admin_home_homeFWXS_1" class="home_main_01_ret_txt">
						<table width="222" height="264" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao01.png" height="28" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">全品类供货</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao02.png" height="28" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">正品保证</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao03.png" height="28" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">天天低价</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao04.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">30天退货</a>
									</p></td>
							</tr>
							<tr>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao05.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">2年保修 </a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao06.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">限时送达</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao07.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">付款方便安全</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao08.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">货物在途保障</a>
									</p></td>
							</tr>
							<tr>


								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao09.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">免收运费</a>
									</p></td>
								<td width="74" height="88" align="center" valign="top"><p>
										<a href="serviceCommitment.html" target="_blank"><img
											src="images/bbao10.png" height="28" alt="" /> </a>
									</p>
									<p>
										<a href="serviceCommitment.html" target="_blank">360度服务</a>
									</p></td>
							</tr>
						</table>
					</div>
					<div id="admin_home_homeFWXS_2" class="xszqu_gai">
						<a href="findPassword.html" target="_blank">万一我的密码丢了怎么办？</a><br />
						<a href="howToBuy.html" target="_blank"> 如何在三际手机采购网下单进货？</a><br />
						<a href="returnedGoods.html" target="_blank">如果我采购的手机出现问题该怎么办？</a>
						<br /> <a href="integralRule.html" target="_blank">
							我要如何赚取更多的积分来兑换礼品？</a>
					</div>


				</div>
				<div class="clear"></div>

			</div>
			<!--======================推广开始============================-->
			<!--======================推广结束============================-->
			<div>
				<div class="home_main_02_top">
					<ul>
						<li><a id="admin_home_home_aHotday"
							href="javascript:selectHot('sku','day','5','admin_home_home_goodsHot');"
							onmouseover="selectHot('sku','day','5','admin_home_home_goodsHot');">今日热销</a>
						</li>
						<li><a id="admin_home_home_aHotweek"
							href="javascript:selectHot('sku','week','5','admin_home_home_goodsHot');"
							onmouseover="selectHot('sku','week','5','admin_home_home_goodsHot');">本周热销</a>
						</li>
						<li><a id="admin_home_home_aHotmonth"
							href="javascript:selectHot('sku','month','5','admin_home_home_goodsHot');"
							onmouseover="selectHot('sku','month','5','admin_home_home_goodsHot');">本月热销
						</a></li>
						<li id="home_main_04"><a href="goods/paihang.html">查看更多&gt;&gt;</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="home_main_03_txt">
					<ul id="admin_home_home_goodsHot"></ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="home_main_04">
			<a href="http://www.3j1688.com/special_160325/index.html"
				target="_blank"><img src="images/img_091.jpg" width="1190"
				height="60" alt="" /> </a>
		</div>
		<!---------------1楼------------------>
		<div>
			<div class="home_main_04">
				<span><a href="javascript:goListView('手机');">查看更多>></a> </span><img
					src="images/f1.jpg" width="51" height="35" alt="" align="left" />手机
			</div>
			<div>
				<div class="home_main_04_let">
					<div>
						<img src="images/images/hm2Agp.png" width="239" height="119"
							alt="" />
					</div>
					<div class="home_main_04_let_txt">
						<ul id="admin_home_homeFloorNews_1F">

						</ul>
					</div>
				</div>
				<div class="home_main_04_mid">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="220" height="135" valign="top">品牌<br /> <a
								href="javascript:goListView('手机','三星');">三星</a> <a
								href="javascript:goListView('手机','华为');">华为 </a> <a
								href="javascript:goListView('手机','荣耀');">荣耀</a> <a
								href="javascript:goListView('手机','中兴');">中兴</a> <a
								href="javascript:goListView('手机','努比亚');">努比亚</a><br /> <a
								href="javascript:goListView('手机','酷派');">酷派</a> <a
								href="javascript:goListView('手机','大神');">大神</a> <a
								href="javascript:goListView('手机','IVVI');">IVVI</a> <a
								href="javascript:goListView('手机','联想');">联想</a> <a
								href="javascript:goListView('手机','摩托罗拉');">摩托罗拉</a> <br /> <a
								href="javascript:goListView('手机','诺基亚');">诺基亚</a> <a
								href="javascript:goListView('手机','微软');">微软</a> <a
								href="javascript:goListView('手机','锤子');">锤子</a> <a
								href="javascript:goListView('手机','魅族');">魅族</a> <a
								href="javascript:goListView('手机','小米');">小米</a><br /> <a
								href="javascript:goListView('手机','HTC');">HTC</a> <a
								href="javascript:goListView('手机','索尼');">索尼</a> <a
								href="javascript:goListView('手机','苹果');">苹果</a><a
								href="javascript:goListView('手机','福中福');">福中福</a><a
								href="javascript:goListView('手机','大Q');">大Q</a><br /> <a
								href="javascript:goListView('手机','金星');">金星</a><br />
							</td>
							<td width="240" height="135" valign="top">价格<br /> <a
								href="javascript:goListView('手机','','0-500');">0-500</a> <a
								href="javascript:goListView('手机','','500-1000');">500-1000</a> <a
								href="javascript:goListView('手机','','1000-1500');">1000-1500</a><br />
								<a href="javascript:goListView('手机','','1500-2000');">1500-2000</a>
								<a href="javascript:goListView('手机','','2000-2500');">2000-2500</a>
								<a href="javascript:goListView('手机','','2500-3000');">2500-3000</a><br />
								<a href="javascript:goListView('手机','','3000-3500');">3000-3500</a>
								<a href="javascript:goListView('手机','','3500-4500');">3500-4500</a>
								<a href="javascript:goListView('手机','','4500');">4500以上</a></td>
						</tr>
					</table>
				</div>
				<div class="home_main_04_ret">
					<div class="myjQuery">
						<div class="myjQueryContent" id="HomeFloorContent1">
							<div>
								<a href="http://www.3j1688.com/goods/detail/949.html"
									target="_blank"><img src="images/images/hmNOTE3.png"
									width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="http://www.3j1688.com/goods/detail/803.html"
									target="_blank"><img src="images/images/ry4A.png"
									width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="http://www.3j1688.com/goods/detail/928.html"
									target="_blank"><img src="images/images/sxON5.png"
									width="254" height="271" /> </a>
							</div>
						</div>
						<ul class="myjQueryNav" id="HomeFloorNav1">
							<li class="current"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------------1楼------------------>
		<!---------------2楼------------------>
		<div>
			<div class="home_main_06">
				<span><a href="javascript:goListView('手环');">查看更多>></a> </span><img
					src="images/f2.jpg" align="left" />智能生活
			</div>
			<div>
				<div class="home_main_04_let">
					<div>
						<img src="images/images/znsh.png" />
					</div>
					<div class="home_main_04_let_txt">
						<ul id="admin_home_homeFloorNews_2F">

						</ul>
					</div>
					/
				</div>
				<div class="home_main_04_mid">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="220" height="135" valign="top">手环<br /> <a
								href="javascript:goListView('手环','小米');">小米</a> <a
								href="javascript:goListView('手环','荣耀');"> 荣耀</a> <a
								href="javascript:goListView('手机','乐心（手环）');"> 乐心</a><br /></td>
							<td width="220" height="135" valign="top">手表<br /> <a
								href="javascript:goListView('手机','U8（手表）');">U8</a> <a
								href="javascript:goListView('手机','掌航（手表）');">掌航</a> <a
								href="javascript:goListView('手机','摩托罗拉（手表）');">摩托罗拉</a>
							</td>
							<td width="220" height="135" valign="top">路由器<br /> <a
								href="javascript:goListView('手机','水星（路由器）');">水星</a> <a
								href="javascript:goListView('手机','TP（路由器）');">TP</a>
							</td>
						</tr>
					</table>

				</div>
				<div class="home_main_04_ret">
					<div class="myjQuery">
						<div class="myjQueryContent" id="HomeFloorContent2">
							<div>
								<a href="http://www.3j1688.com/goods/detail/1043.html"
									target="_blank"><img src="images/images/U8znsb.png"
									width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="http://www.3j1688.com/goods/detail/1008.html"
									target="_blank"><img src="images/images/xmznsh.png"
									width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="http://www.3j1688.com/goods/detail/1006.html"
									target="_blank"><img src="images/images/rysb.png"
									width="254" height="271" /> </a>
							</div>
						</div>
						<ul class="myjQueryNav" id="HomeFloorNav2">
							<li class="current"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------------2楼------------------>
		<!---------------3楼------------------>
		<div>
			<div class="home_main_05">
				<span><a href="javascript:goListView('手机壳');">查看更多>></a> </span><img
					src="images/f3.jpg" align="left" />保护类
			</div>
			<div>
				<div class="home_main_04_let">
					<div>
						<img src="images/f3_img.jpg" />
					</div>
					<div class="home_main_04_let_txt">
						<ul id="admin_home_homeFloorNews_3F">

						</ul>
					</div>
				</div>
				<div class="home_main_04_mid">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="220" height="135" valign="top">手机壳<br /> <a
								href="javascript:goListView('手机壳','艾伦顿');">艾伦顿</a> <a
								href="javascript:goListView('手机壳','莫凡');"> 莫凡 </a> <a
								href="javascript:goListView('手机壳','迪佐');">迪佐</a> <a
								href="javascript:goListView('手机壳','迪佑');">迪佑</a> <br /> <a
								href="javascript:goListView('手机壳','伊贝');">伊贝 </a></td>
							<td width="220" height="135" valign="top">手机套<br /> <a
								href="javascript:goListView('手机套','艾伦顿');">艾伦顿</a> <a
								href="javascript:goListView('手机套','莫凡');"> 莫凡 </a> <a
								href="javascript:goListView('手机套','迪佐');"> 迪佐</a> <a
								href="javascript:goListView('手机套','迪佑');">迪佑</a> <br /> <a
								href="javascript:goListView('手机套','伊贝');">伊贝 </a></td>
							<td width="220" height="135" valign="top">贴膜<br /> <a
								href="javascript:goListView('贴膜','艾伦顿');">艾伦顿</a></td>
						</tr>
					</table>

				</div>
				<div class="home_main_04_ret">
					<div class="myjQuery">
						<div class="myjQueryContent" id="HomeFloorContent3">
							<div>
								<a href="accessory/detail/AM051368.html" target="_blank"><img
									src="images/img_3f_01.jpg" width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="accessory/detail/AM051168.html" target="_blank"><img
									src="images/img_3f_02.jpg" width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="accessory/detail/AM030268.html" target="_blank"><img
									src="images/img_3f_03.jpg" width="254" height="271" /> </a>
							</div>
						</div>
						<ul class="myjQueryNav" id="HomeFloorNav3">
							<li class="current"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------------3楼------------------>
		<!---------------4楼------------------>
		<div>
			<div class="home_main_07">
				<span><a href="javascript:goListView('电池');">查看更多>></a> </span><img
					src="images/f4.jpg" align="left" />充电类
			</div>
			<div>
				<div class="home_main_04_let">
					<div>
						<img src="images/images/dccdl.png" />
					</div>
					<div class="home_main_04_let_txt">
						<ul id="admin_home_homeFloorNews_4F">

						</ul>
					</div>
				</div>
				<div class="home_main_04_mid">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="220" height="135" valign="top">电池<br /> <a
								href="javascript:goListView('电池','华毅');"> 华毅 </a> <a
								href="javascript:goListView('电池','至尊');"> 至尊 </a></td>
							<td width="220" height="135" valign="top">充电器<br /> <a
								href="javascript:goListView('充电器','风致');">风致</a></td>
							<td width="220" height="135" valign="top">充电宝<br /> <a
								href="javascript:goListView('充电宝','小米');"> 小米 </a> <a
								href="javascript:goListView('充电宝','倍斯特');"> 倍斯特 </a> <a
								href="javascript:goListView('充电宝','果果');"> 果果 </a><br /></td>
						</tr>
					</table>

				</div>
				<div class="home_main_04_ret">
					<div class="myjQuery">
						<div class="myjQueryContent" id="HomeFloorContent4">
							<div>
								<a href="accessory/detail/8080010301.html" target="_blank"><img
									src="images/img_4f_01.jpg" width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="accessory/detail/817001001.html" target="_blank"><img
									src="images/img_4f_02.jpg" width="254" height="271" /> </a>
							</div>
						</div>
						<ul class="myjQueryNav" id="HomeFloorNav4">
							<li class="current"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------------4楼------------------>
		<!---------------5楼------------------>
		<div>
			<div class="home_main_08">
				<span><a href="javascript:goListView('耳机');">查看更多>></a> </span><img
					src="images/f5.jpg" align="left" />视听类
			</div>
			<div>
				<div class="home_main_04_let">
					<div>
						<img src="images/f5_img.jpg" />
					</div>
					<div class="home_main_04_let_txt">
						<ul id="admin_home_homeFloorNews_5F">

						</ul>
					</div>
				</div>
				<div class="home_main_04_mid">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="220" height="135" valign="top">耳机<br /> <a
								href="javascript:goListView('耳机','小米');">小米</a> <a
								href="javascript:goListView('耳机','华为');"> 华为 </a></td>
						</tr>
					</table>

				</div>
				<div class="home_main_04_ret">
					<div class="myjQuery">
						<div class="myjQueryContent" id="HomeFloorContent5">
							<div>
								<a href="accessory/detail/500232.html" target="_blank"><img
									src="images/ep-21hd.jpg" width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="accessory/detail/500081.html" target="_blank"><img
									src="images/img_5f_02.jpg" width="254" height="271" /> </a>
							</div>
							<div class="smask">
								<a href="accessory/detail/500172.html" target="_blank"><img
									src="images/img_5f_03.jpg" width="254" height="271" /> </a>
							</div>
						</div>
						<ul class="myjQueryNav" id="HomeFloorNav5">
							<li class="current"></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------------6楼------------------>

		<div>
			<div class="home_main_new_top">
				<span></span>最新资讯
			</div>
			<div class="home_main_new">
				<ul id="admin_home_homeNewsHteml">

				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--弹窗-->
<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog "  style="width: 500px;  left:40%;top:25%;">
        <div class="modal-content">
            <div class="hello-header ">
                <button type="button" id="dialog_close" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <img src="images/shoprush/top1.png" alt="" style="margin-left: 35%; margin-top: 10px">
                </h4>
            </div>
            <div class="modal-body">
                <p style="margin-left:60px;margin-top: 30px "> <span class="text01">因系统升级，需</span> <span class="text02" sty>移动专卖店、加盟店、卖场客户</span></p>
                <p style="margin-left:60px;margin-top: 10px"><span class="text01">完善便利店渠道编码。</span></p>
                <p style="margin-left:140px;margin-top:20px"><span class="text03">立即完善 </span> &nbsp;  <a href="http://www.3j1688.com/member/toMyAccount.html"><span class="text04">渠道编码>></span></a></p>
                <p style="margin:20px 0px 20px 50px;"><span class="text05">亲！若您的信息已完善，或非移动便利店用户请当我“飘过”哦。</span></p>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>

</html>
