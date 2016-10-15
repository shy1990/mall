<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.xgzy_main_01 {
	height: 298px;
}

.xgzy_main_01 li {
	border-left: #e4e4e4 solid 1px;
	border-right: none;
	width: 236px;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<link rel="stylesheet" href="js/sj/ui/block-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/simple-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/data-view-list/css.css" />
<title>三际在线商城-选购通用配件</title>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../../common/follow.jsp"></jsp:include>
	<jsp:include page="../../common/head.jsp"></jsp:include>

	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;
			<h5>选购通用配件</h5>
		</div>
		<!---------已采购商品信息---------->
		<!---------第二块采购说明---------->
		<div class="xgzy_top_05">通用配件</div>
		<div class="xgzy_main">
			<div id="showList">
				<div>
					<div class="xgzy_main_top">
						<span><a href="#">更多</a></span>耳机
					</div>
					<div id="erji" class="xgzy_main_01"></div>
				</div>
				<div class="xgzy_main_top">数据线</div>
				<div id="shujuxian" class="xgzy_main_01"></div>
				<div class="xgzy_main_top">
					<span><a href="#">更多</a></span>充电宝
				</div>
				<div id="dianyuan" class="xgzy_main_01"></div>
				<div class="xgzy_main_top">
					<span><a href="#">更多</a></span>音箱
				</div>
				<div id="yinxiang" class="xgzy_main_01"></div>
			</div>
			<div>
				<div class="xgzy_main_07">
					<div class="xgzy_main_07_top">
						<table border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="82">品类</td>
									<td width="95">品牌</td>
									<td width="149">名称</td>
									<td width="196">规格</td>
									<td width="109">价格</td>
									<td width="193">购量</td>
									<td width="87">天猫价</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="detailList" class="xgzy_main_09"></div>
				</div>
				<div class="xgzy_main_08">
					<div class="xgzy_main_let_01">
						<div class="xgzy_main_let_02">
							<img src="images/huax.png" width="60" height="10">
						</div>
						<span>原价<font class='totalOriginalPrice'>0.00</font>元
						</span><br>共
						<h3 class='totalNumber'>0</h3>
						件
						<h3 class="totalPrice">0.00</h3>
						元
					</div>
					<div class="xgzy_main_let_03">
						已省<span class="spare">0.00</span>元
					</div>
					<form id="myform" action="point/main.html" method="post">
						<input id="mycartIds" type="hidden" name="cartIds" value="${cartIds}"/>
					</form>
					<div class="xgzy_main_let_04">
						<script type="text/javascript">
							function add2Cart() {
								var datas = $("#detailList").data(
										'sjDataViewList').items;
								var params = {};
								$.each(datas, function(i, data) {
									params[data.id] = parseInt(data.number);
								});
								
								$.post('cart/addPj2Cart.html', {
									param : $.toJSON(params)
								},function(r){
									if(r!="null"){
										var cardids= $("#mycartIds").val();
										$("#mycartIds").val(cardids+","+r.replace(/\"/g,''));
									}
								}).done(function(){
									$("#myform").submit();
								});
								
							}
						</script>
						<a href="javascript:add2Cart();">下一步（免费兑换积分商品）</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------第三块产品详情---------->
	</div>

	<jsp:include page="../../common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/sj/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="js/sj/ui/simple-data-view/simple-data-view.js"></script>
	<script type="text/javascript"
		src="js/sj/ui/block-data-view/block-data-view.js"></script>
	<script type="text/javascript"
		src="js/sj/ui/data-view-list/data-view-list.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/sj/cart.js"></script>
	<script>
		$(function() {
			var cart = new Cart({
				key : 'cart'
			});
			$.cookie('cart',null,{path: "/"});
			//离开页面事保存购物车
			$(window).bind('beforeunload', function(e) {
				//cart.save2cookie("cart");
			});
			var updatePrice = function() {
				var totalNumber = cart.getTotalQuantity();
				var totalPrice = cart.getTotalPrice();
				var totalOriginalPrice = cart.getTotalOriginalPrice();

				$(".totalOriginalPrice").text(totalOriginalPrice.toFixed(2));
				$(".totalPrice").text(totalPrice.toFixed(2));
				$(".totalNumber").text(totalNumber);
				$(".spare").text((totalOriginalPrice - totalPrice).toFixed(2));
			};
			cart.items.on('afteradd afterremove', updatePrice);
			updatePrice();

			function updateList(sel, obj) {
				var datas = [];
				$.each(obj, function(i, data) {
					datas.push({
						"id" : data.id,
						"specCode":data.specCode,
						"type" : data.cat.name,
						"goodsName" : data.brand.name,
						"specification" : data.batteryCapacity,
						"name" : data.name,
						"imgSrc" : data.defaultImg,
						"value1" : data.price,
						"value2" : data.originalPrice,
						"value3" : data.tmallPrice,
						"tmallUrl" : data.tmallUrl,
						"description" : data.name,
						"number" : 0,
						"colorName":data.colors[0].colorName
					});
				});
				$(sel).dataViewList({
					viewType : 'block',
					datas : datas,
					itemChange : onListItemnChange1
				});
			}
			$.post("universal/accessory.html", {
				type : '耳机'
			}, function(result) {
				result = $.parseJSON(result);
				if (result.success) {
					updateList("#erji", result.obj);
				}
			});
			$.post("universal/accessory.html", {
				type : '数据线'
			}, function(result) {
				result = $.parseJSON(result);
				if (result.success) {
					updateList("#shujuxian", result.obj);
				}
			});
			$.post("universal/accessory.html", {
				type : '充电宝'
			}, function(result) {
				result = $.parseJSON(result);
				if (result.success) {
					updateList("#dianyuan", result.obj);
				}
			});
			$.post("universal/accessory.html", {
				type : '音响'
			}, function(result) {
				result = $.parseJSON(result);
				if (result.success) {
					updateList("#yinxiang", result.obj);
				}
			});
			var detail = $("#detailList").dataViewList({
				itemChange : onListItemnChange2
			}).dataViewList('instance');

			function onListItemnChange1(event, item) {
				var data = item.data;
				data.from = event.target;
				if (parseInt(data.number)) {
					detail.appendDatas([ data ]);
				} else {
					detail.remove(data);
				}
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '2',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.add(item);
			}

			function onListItemnChange2(event, item) {
				var data = item.data;
				var list = $(data.from).data('sjDataViewList');
				if (parseInt(data.number)) {
					list.appendDatas([ data ]);
				} else {
					list.appendDatas([ data ]);
					detail.remove(data);
				}
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '2',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.add(item);
			}
		});
	</script>
</body>
</html>
