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

<style type="text/css">
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
	width: 50%;
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
				           魅族 : [],
				           乐视: [],
					小米 : [],
					百立丰 : [],
					金星 : [],
					荣耀 : [],
					华为 : [],
					苹果 : [],
					三星 : [],					
					大神 : [],
					多美达 : [],
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
					青橙 : [],
					ivvi : [],
					台电 : [],
					睿米车载 : [],
					夏新 : [],
					酷比 : [],
					亚马逊 : [],
					海尔 : [],
					小辣椒 : [],
					奇酷 : [],
					
					"U8（手表）" : [],
					"掌航（手表）" : [],
					"小米（血压计）" : [],
					"乐心（手环）" : [],
					"TP（路由器）" : [],
					"三星（手表）" : [],
					"小米（手环）" : [],
					"华为（手表）" : [],
					"摩托罗拉（手表）" : [],
					"暴风（3D魔镜）" : [],
					"水星（路由器）" : [],
					"大疆（无人飞机）" : []
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
															delete showg[i];
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
		<%-- <c:if test="${jx=='ydhy'}">
			<img src="images/lj-b_4.jpg" alt="" width="1192" height="197"
				border="0" usemap="#Map"
				href="http://www.3j1688.com/goods/detail/901.html"> <map
					name="Map">
					<area shape="rect" coords="2,4,436,212"
						href="http://www.3j1688.com/goods/detail/915.html" target="_blank">
						<area shape="rect" coords="436,1,818,286"
							href="http://www.3j1688.com/goods/detail/537.html"
							target="_blank">
							<area shape="rect" coords="816,7,1327,266"
								href="http://www.3j1688.com/goods/detail/901.html"
								target="_blank">
				</map>
		</c:if> --%>

		<%--  <c:if test="${jx=='dxhy'}">
			<a href="http://www.3j1688.com/goods/detail/989.html"><img src="images/lj-b_2.jpg" /> 
			</a>
		</c:if>  --%>
		<c:if test="${jx=='dxhy'}">
			<a href="http://www.3j1688.com/goods/detail/1041.html?s=bjd2"><img
				src="images/ry6plus hf.png?<%=Math.random()%>" /> </a>
		</c:if>

		<c:if test="${jx=='dz'}">
			<img src="images/hyjyp.png" alt="" width="1192" height="197"
				border="0" usemap="#Map">
			<map name="Map">
				<area shape="rect" coords="1,1,277,260"
					href="http://www.3j1688.com/goods/detail/1416.html" target="_blank">
					<area shape="rect" coords="380,101,484,204"
						href="http://www.3j1688.com/goods/detail/1415.html"
						target="_blank">
						<area shape="rect" coords="485,103,591,207"
							href="http://www.3j1688.com/goods/detail/1414.html"
							target="_blank">
							<area shape="rect" coords="591,0,836,219"
								href="http://www.3j1688.com/goods/detail/1345.html"
								target="_blank">
								<area shape="rect" coords="977,94,1068,202"
									href="http://www.3j1688.com/goods/detail/1413.html"
									target="_blank">
									<area shape="rect" coords="1077,97,1175,197"
										href="http://www.3j1688.com/goods/detail/1412.html"
										target="_blank">
			</map>

		</c:if>
		<%--  <c:if test="${jx=='ydhy'}">
            <a href="http://www.3j1688.com/goods/ydhy/2/bjd.html"><img src="images/banner1_0002.jpg?<%=Math.random() %>" />
			</a>
		</c:if> --%>
		<%-- 	<c:if test="${jx=='pb'}">
			<a href="http://www.3j1688.com/goods/detail/1373.html?s=bjd2"><img
				src="images/tdpb.png?<%=Math.random()%>" /> </a>
		</c:if> --%>

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
							<td width="164"><div class="baojiadan_top_04">
									<a href="javascript:void(0);" ms-click="filteGoods">确定</a>
								</div></td>
						</tr>

						<tr>
							<td width="47">品牌：</td>
							<td width="20" colspan="10">
								<ul id="filter_brand">
									<li ms-repeat="allBrand"><input type="checkbox"
										ms-attr-value="el" ms-duplex="brandRange" />{{el}}</li>
								</ul>
							</td>
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
								ms-class-3="right:$index%2==1" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="21" align="center" ms-class-1="icon"
											ms-class-2="icon-gray:!(goods.isNew||goods.isHot)"
											ms-text="goods.isNew?'新':goods.isHot?'热':''">热</td>
										<td width="278" class="xinghao"><a target="_blank"
											ms-attr-title="{{goods.goodsName}}"
											ms-href="goods/detail/{{goods.goodsNum}}.html"
											ms-text="goods.goodsName | lastWord |truncate(30,'...')">S7572</a>
										</td>
										<td width="300" class="price"><a target="_blank"
											ms-href="goods/detail/{{goods.goodsNum}}.html"
											ms-text="goods.price|currency">￥510</a></td>

										<!-- <td width="54" ms-class="collected:goods.isCollected"><img
											ms-if="goods.isCollected" src="images/hongxin.gif" />{{goods.isCollected?'收藏':''}}</td> -->
									</tr>
								</tbody>
							</table>
						</li>
					</ul>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>

		<!--产品列表-->
	</div>

	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</body>
</html>
