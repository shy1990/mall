<%@page import="com.sanji.mall.common.util.ResourceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<title>报价单</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/avalon.min.js"></script>
<script type="text/javascript"
	src="js/shoprush/sanji-timer-count-down-.js"></script>
<style type="text/css">
.ultlon_as_select {
	width: 240px;
	height: 50px;
	cursor: pointer;
	outline: none;
	text-align: center;
	line-height: 50px;
	float: left;
	background: #ccc;
	font-size: 18px;
	margin-left: 30px;
}

.ultlon_as_select_frist {
	background: red;
}

#background {
	position: absolute;
	z-index: 998;
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

.ultlon_gather {
	margin-top: 20px;
	font-size: 22px;
}

.ultlon_content_body_imei input {
	width: 250px;
	height: 35px;
}

.ultlon_content_body_btn {
	border: 1px solid #ccc;
	width: 250px;
	margin: 20px auto;
	height: 30px;
	line-height: 30px;
	cursor: pointer;
	font-size: 18px;
}

#ultlon_btn_cl {
	margin-top: 0px;
	font-size: 16px;
}

.ultlon_as {
	margin: auto;
	color: white;
	width: 600px;
}

.wrap {
	width: 1190px;
	border: #e4e4e4 solid 1px;
}

.wrap>.head {
	border-bottom: #e4e4e4 solid 1px;
	line-height: 30px;
	background-color: #f8f8f8;
}

.wrap>.head>h5 {
	margin: 0 0 0 15px;
	padding-left: 10px;
	float: left;
	background: url(images/baojiad.jpg) no-repeat center left;
}

.wrap>.head>a.toggle {
	margin-right: 15px;
	float: right;
	font-size: 12px;
	text-decoration: none;
	color: #7F7F7F;
}

.wrap>.content {
	margin-left: -15px;
}

.wrap>.content>ul {
	margin: 0;
	padding: 0;
}

.wrap>.content>ul>li {
	box-sizing: border-box;
	padding-left: 15px;
	list-style: none;
	float: left;
	line-height: 30px;
	width: 33.33333333%;
	overflow: hidden;
}

.wrap>.content>ul>li>table {
	border: #e4e4e4 solid 1px;
	border-top: none;
}

.wrap>.content>ul>li>table.left {
	border-left: none;
}

.wrap>.content>ul>li>table.right {
	width: 390px;
	border-right: none;
}

.wrap>.content>ul>li>table .xinghao {
	padding-left: 15px;
}

.wrap>.content>ul>li>table .xinghao a {
	text-decoration: none;
	color: #525252;
}

.wrap>.content>ul>li>table .price a {
	color: #0862FF;
	font-size: 12px;
}

.wrap>.content>ul>li>table .ordered {
	color: #683405;
}

.wrap>.content>ul>li>table .collected { /*padding-left: 16px;
       background: url(images/hongxin.gif) no-repeat left center;*/
	font-size: 12px;
}

.wrap>.content>ul>li>table.hot {
	background-color: #FFF8E5;
}

.wrap>.content>ul>li>table.hot .icon {
	background-color: #FF393C;
	color: #FFFE93;
}

.wrap>.content>ul>li>table.new {
	background-color: #F9FFF2;
}

.wrap>.content>ul>li>table.new .icon {
	background-color: #4AE50B;
	color: #FFFE93;
}

.icon-gray {
	background-color: #f8f8f8;
}

#filter_brand>li {
	float: left;
	margin-right: 10px;
}

/* 倒计时样式 */
.down-time {
	position: absolute;
	top: 40px;
	right: 30px;
	background-color: #E90000;
	color: #FFF;
	font-size: 14px;
	font-weight: bold;
	border: 2px solid #FDFEBF;
	-moz-border-radius: 5px; /* Gecko browsers */
	-webkit-border-radius: 5px; /* Webkit browsers */
	border-radius: 5px;
	padding: 0px 10px 6px;
	text-align: center;
}

.down-time span {
	position: relative;
	top: 3px;
	background-color: #FFF;
	color: #000;
	border: 1px solid #000;
	font-size: 22px;
	margin: 0 2px;
	padding: 0 3px;
	-moz-border-radius: 3px; /* Gecko browsers */
	-webkit-border-radius: 3px; /* Webkit browsers */
	border-radius: 3px;
}
</style>
<script type="text/javascript">
	avalon
			.ready(function() {
				avalon.filters.lastWord = function(str) {
					var strs = str.match(avalon.rword);
					strs.shift();
					return strs.join(" ");
				}
				var bjd = avalon
						.define({
							$id : "bjd",
							$allGoods : {},
							allBrand : [],
							showGoods : {},
							priceRange : [],
							brandRange : [],
							onlyShowCollected : false,
							customMin : '',
							customMax : '',
							toggleShow : function(e) {
								var a = $(this);
								var target = a.closest(".wrap")
										.find(".content");
								target.toggle("slow", function() {
									var text = a.text();
									a.text(text === "展开v" ? "收起^" : "展开v");
								});
							},
							filteGoods : function() {
								var goodss = {};
								for (b in bjd.$allGoods) {
									if (!testBrand(b)) {
										continue;
									}
									var arr = $
											.grep(
													bjd.$allGoods[b],
													function(g, i) {
														var collected = testCollected(g);
														var inPriceRange = testPriceRange(g);
														return inPriceRange
																&& collected;
													});
									if (arr.length > 0) {
										goodss[b] = arr;
									}
								}
								bjd.showGoods = goodss;

								function testBrand(b) {
									if (bjd.brandRange.length === 0) {
										return true;
									} else {
										var index = bjd.brandRange.indexOf(b);
										return index >= 0;
									}
								}

								function testCustomPriceRange(g) {
									if (bjd.customMin < bjd.customMax) {
										return bjd.customMin < g.price
												&& g.price <= bjd.customMax;
									} else if (bjd.customMin === void 0
											|| bjd.customMax === void 0) {
										return true;
									} else {
										return false;
									}
								}

								function testCollected(g) {
									if (bjd.onlyShowCollected) {
										return g.isCollected;
									} else {
										return true;
									}

								}

								function testPriceRange(g) {
									var inRange = false;
									if (bjd.priceRange.length > 0) {
										for ( var i = 0; i < bjd.priceRange.length; i++) {
											var range = bjd.priceRange[i]
													.split(",");
											var min = parseInt(range[0]);
											var max = parseInt(range[1]);
											inRange = min <= g.price
													&& g.price <= max;
											if (inRange) {
												break;
											} else {
												inRange = testCustomPriceRange(g);
											}
										}
									} else {
										inRange = true;
									}
									return inRange;
								}
							}
						});

				bjd.priceRange.$watch('length', function(n, o) {
					bjd.filteGoods();
				});
				//只显示收藏
				bjd.$watch('onlyShowCollected', function(n, o) {
					bjd.filteGoods();
				});
				bjd.brandRange.$watch("length", function(n, o) {
					bjd.filteGoods();
				});

				var sg = {
					小米 : [],
					天语 : [],
					京凯达 : [],
					优思 : [],
					多美达 : [],
					福中福 : [],
					乐视 : [],
					苹果 : [],
					华为 : [],
					荣耀 : [],
					//百立丰 : [],
					三星 : [],
					魅族 : [],
					大神 : [],
					酷派 : [],
					诺基亚 : [],
					联想 : [],
					中兴 : [],
					米多 : [],
					Q2 : [],
					纽曼 : [],
					世纪天元 : [],
					爱我 : [],
					飞利浦 : [],
					HTC : [],
					TCL : [],
					倍斯特 : [],
					努比亚 : [],
					美图 : [],
					索尼 : [],
					微软 : [],
					JIMI大可乐 : [],
					果果 : [],
					洪洋伟业 : [],
					摩托罗拉 : [],
					神舟 : [],
					大Q : [],
					优它 : [],
					锤子 : [],
					青橙 : [],
					奇酷 : [],
					金星 : [],
					台电 : [],
					先科 : [],
					夏新 : [],
					U8 : [],
					掌航 : [],
					"21克" : [],
					小辣椒 : [],
					一加 : []
				};
				var showg = sg;

				$
						.each(
								sg,
								function(i, b) {
									$
											.ajax({
												url : "goods/${jx}/bjdjsonByBrand.html",
												contentType : "application/x-www-form-urlencoded; charset=utf-8",
												dataType : "json",
												type : "POST",
												data : {
													"brandName" : i
												},
												success : function(data) {
													if (data.success) {
														if (!data.data) {
															delete sg[i];
														} else {
															if (data.data) {
																sg[i] = data.data
																bjd.$allGoods = sg;
																showg[i] = data.data;
																bjd.showGoods = showg;
																bjd.allBrand
																		.push(i);
															}
														}
													}
												}
											});

								});

				$("#loadmask").remove();
				$("#mainbox").slideToggle(1000);
				avalon.scan(document.body);
			});
</script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	<!--======================详情图片开始============================-->
	<div class="xq_mian" id="xq_mian" ms-controller="bjd">
		<c:if test="${jx=='gnj'}">

			<img src="images/xin1.png" alt="" width="1192" height="197"
				border="0" usemap="#Map1">

			<map name="Map1">
				<area shape="rect" coords="18,1,410,202"
					href="http://www.3j1688.com/goods/detail/1236.html?s=bjd3">
				<area shape="rect" coords="419,1,779,202"
					href="http://www.3j1688.com/goods/detail/1346.html?s=bjd3">
				<area shape="rect" coords="788,4,1178,207"
					href="http://www.3j1688.com/goods/detail/1381.html?s=bjd3">
			</map>
		</c:if>
		<c:if test="${jx=='bxjx'}">
			<a href="javascript:goListView('手机','','','TP')"><img
				src="images/banner_luyou.jpg" /> </a>
		</c:if>
		<c:if test="${jx=='lj'}">
			<img src="images/heyueji4.png" alt="" width="1192" height="197"
				border="0" usemap="#Map">
			<map name="Map">
  <area shape="rect" coords="9,4,299,194" href="http://www.3j1688.com/goods/detail/1345.html" target="_blank">
  <area shape="rect" coords="412,96,512,188" href="http://www.3j1688.com/goods/detail/1433.html" target="_blank">
  <area shape="rect" coords="513,96,611,187" href="http://www.3j1688.com/goods/detail/1434.html" target="_blank">
  <area shape="rect" coords="611,4,899,222" href="http://www.3j1688.com/goods/detail/1440.html" target="_blank">
  <area shape="rect" coords="904,5,1186,194" href="http://www.3j1688.com/goods/detail/1278.html" target="_blank">
            </map>

			<!--  <map name="Map">
        <area shape="rect" coords="1,1,600,206"
              href="javascript:goListView('手机','','','激活返利')" target="_blank">
        <area shape="rect" coords="609,4,1195,201"
              href="http://www.3j1688.com/goods/detail/1354.html?s=bjd"
              target="_blank">
      </map> -->
			</a>
			<!-- <div class="down-time">
    <span id="day_show" class="">0</span>天
    <span id="hour_show" class="">0</span>时
    <span id="minute_show" class="">0</span>分
    <span id="second_show" class="">0</span>秒
    </div>
    </div> -->
		</c:if>
		<div class="xq_mian_top">
			<span><a href="javascript:history.go(-1);">返回</a> </span>当前位置&gt;&gt;
			<h5>报价单</h5>
		</div>
		<!---------已采购商品信息---------->
		<div class="baojiadan_main_01">
			<div class="baojiadan_top">
				<div class="baojiadan_top_01">
					<div class="baojiadan_top_01_let">全部</div>
					<div class="baojiadan_top_01_let">
						<input type="checkbox" name="checkbox" id="checkbox"
							ms-duplex-checked="onlyShowCollected" /> <label for="checkbox">只显示收藏
						</label>
					</div>
					<div class="clear"></div>
				</div>

				<div class="baojiadan_top_02">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="47">价格：</td>
							<td width="111"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="0,300" id="checkbox3" /> <label
								for="checkbox3"><span>300元以下</span> </label></td>
							<td width="103"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="300,500" id="checkbox3" /> <label
								for="checkbox3"><span>300~500</span> </label></td>
							<td width="113"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="500,1000" id="checkbox3" /> <label
								for="checkbox3"><span>500~1000</span> </label></td>
							<td width="115"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="1000,1500" id="checkbox3" /> <label
								for="checkbox3"><span> 1000~1500</span> </label></td>
							<td width="120"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="1500,2000" id="checkbox3" /> <label
								for="checkbox3"><span>1500~2000</span> </label></td>
							<td width="122"><input type="checkbox" name="checkbox3"
								ms-duplex="priceRange" value="2000,99999" id="checkbox3" /> <label
								for="checkbox3"><span>2000元以上</span> </label></td>
							<td class="baojiadan_top_03" width="80"><input type="text"
								ms-duplex="customMin" name="textfield" id="textfield" /> —</td>
							<td class="baojiadan_top_03" width="84"><input type="text"
								ms-duplex="customMax" name="textfield" id="textfield" /> 元</td>
							<td width="164">
								<div class="baojiadan_top_04">
									<a href="javascript:void(0);" ms-click="filteGoods">确定</a>
								</div></td>
						</tr>

						<tr>
							<td width="47">品牌：</td>
							<td width="20" colspan="10">
								<ul id="filter_brand">
									<li ms-repeat="allBrand"><input type="checkbox"
										ms-attr-value="el" ms-duplex="brandRange" />{{el}}</li>
								</ul></td>
						</tr>
					</table>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------第三块产品详情---------->
		<div id="loadmask" class="baojidan_box" style="text-align: center;">
			<img src="images/loading.gif" />
		</div>
		<div id="mainbox" class="baojidan_box" style="display: none">
			<div class="wrap" ms-repeat="showGoods">
				<div class="head" ms-if="$val.length>0">
					<h5 ms-text="$key">三星</h5>
					<a href="javascript:void(0);" class="toggle" ms-click="toggleShow">收起^</a>
					<div style="clear: both;"></div>
				</div>
				<div class="content">
					<ul>
						<li ms-repeat-goods="$val">
							<table ms-class-1="hot:goods.isHot" ms-class-2="new:goods.isNew"
								ms-class-3="left:$index%3==0" ms-class-4="right:$index%3==2"
								cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="21" align="center" ms-class-1="icon"
											ms-class-2="icon-gray:!(goods.isNew||goods.isHot)"
											ms-text="goods.isNew?'新':goods.isHot?'热':''">热</td>
										<td width="278" class="xinghao"><a target="_blank"
											ms-attr-title="{{goods.goodsName}}"
											ms-href="goods/detail/{{goods.goodsNum}}.html?s=bjd"
											ms-text="goods.goodsName | lastWord |truncate(30,'...')">S7572</a>
										</td>
										<td width="30" class="price"><a target="_blank"
											ms-href="goods/detail/{{goods.goodsNum}}.html?s=bjd"
											ms-text="goods.price|currency">￥510</a></td>

										<!-- <td width="54" ms-class="collected:goods.isCollected"><img
                  ms-if="goods.isCollected" src="images/hongxin.gif" />{{goods.isCollected?'收藏':''}}</td> -->
									</tr>
								</tbody>
							</table></li>
					</ul>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
		<!--产品列表-->
		<div id="background"></div>
		<!-- <div id="content">
    <div class="ultlon_content_body">
       <i id="dialog_close"
       style="position:absolute;width:32px;height:32px;right:150px;background-image:url('/images/shoprush/dialog_close.png')"></i>
      <a href="/goods/toShopRush.html?s=menu"><img height="360px" width="734px"src="images/shoprush/xmzhuangong.png"/> </a> 
    </div>
  </div> -->
	</div>

	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(function() {
			var endTime = "2016-04-14 10:00:00";
			$(".down-time").sanjiTimerCountDown(
					new Date(Date.parse(endTime.replace(/-/g, "/"))).getTime());

			$("#dialog_close").on("click", function() {
				$("#background, #content").hide();
			});

			/*     function conPosition() {
			 var oBackground = document.getElementById("background");
			 var dw = $(document).width();
			 var dh = $(document).height();
			 oBackground.style.width = dw + 'px';
			 oBackground.style.height = dh + 'px';
			 var oContent = document.getElementById("content");
			 var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
			 var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
			 var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 3) + scrollTop;
			 //oContent.style.left = l + 'px';
			 oContent.style.top = t + 'px';
			 }

			 $("#background, #content").show();
			 conPosition();

			 $("#ultlon_btn_cl").click(function () {
			 $("#background, #content").hide();
			 });
			 $("#background").click(function () {
			 $("#background, #content").hide();
			 });
			 //点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
			 $(window).resize(function () {
			 conPosition();
			 }); */
		})
	</script>
</body>
</html>
