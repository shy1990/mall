<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:if test="${ empty skuList}">
	<script>
		history.back();
	</script>
</c:if>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="js/sj/ui/block-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/simple-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/data-view-list/css.css" />
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
	var cart = new Cart();
	cart.items.on('afteradd afterremove', function() {
		var totalNumber = cart.getTotalQuantity();
		var totalPrice = cart.getTotalPrice();
		var totalOriginalPrice = cart.getTotalOriginalPrice();

		$(".totalOriginalPrice").text(totalOriginalPrice.toFixed(2));
		$(".totalPrice").text(totalPrice.toFixed(2));
		$(".totalNumber").text(totalNumber);
		$(".spare").text((totalOriginalPrice - totalPrice).toFixed(2));
	});
	//离开页面事保存购物车
	$(window).bind('beforeunload', function(e) {
		//cart.save2cookie("cart");
	});

	//初始化购物车
	function initCart() {
		$.cookie('cart',null,{path:'/'});
		//cart.loadFromCookie("cart");

		//加入单品  0手机 1赠品 2配件 3root
		$("#skudetail tr:gt(0)").each(function(i, tr) {
			var input = $(tr).find("td:first input");
			var skuId = input.attr("name").split("-")[1];
			var quantity = input.val();
			var price = input.attr('data-price');
			var originalPrice = input.attr('data-originalPrice');
			var item = new CartItem({
				goodsId : skuId,
				quantity : quantity,
				type : '0',
				price : price,
				originalPrice : originalPrice
			});
			cart.add(item);
		});

		//加入root
		$("#skudetail tr:gt(0)").each(function(i, tr) {
			var input = $(tr).find("td:eq(1) input");
			var skuId = input.attr("name").split("-")[1];
			var quantity = input.val();
			var price = input.attr('data-price');
			var originalPrice = input.attr('data-originalPrice');
			var item = new CartItem({
				goodsId : skuId,
				quantity : quantity,
				type : '3',
				price : price,
				originalPrice : originalPrice
			});
			cart.add(item);
		});
	}

	$(function() {
		$("a[href='#']").attr('href', "javascript:void(0);");
		//计算单品数量
		var getSkuNumber = function() {
			var number = 0;
			$("#skudetail tr:gt(0)").each(function(i, tr) {
				var n = $(tr).find('td:eq(0) input').val();
				number += parseInt(n);
			});
			return number;
		}

		//加载赠品
		$.post("accessory/gift.html", {
			goodsId : "${goodsId}",
			type : '保护膜'
		}).done(function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				var data = result.obj;
				var datas = [];
				$(".giftbhmname").text(data.name);
				datas.push({
					"id" : data.id,
					"type" : "保护膜",
					"goodsName" : data.brand.name,
					"specification" : data.batteryCapacity,
					"name" : data.name,
					"imgSrc" : data.defaultImg,
					"value1" : data.price,
					"value2" : data.originalPrice,
					"value3" : data.tmallPrice,
					"tmallUrl" : data.tmallUrl,
					"description" : data.name,
					"number" : getSkuNumber()
				});
				$("#giftbhm").dataViewList({
					viewType : 'block',
					datas : datas,
					itemChange : function(event, item) {
						alert('想多要赠品就要多买手机哦亲~');
						$(event.target).find("input").val(getSkuNumber());
					}
				});
				//加入购物车 0手机 1赠品 2配件 3root fdfdd
				var item = new CartItem({
					goodsId : data.id,
					quantity : getSkuNumber(),
					type : '1',
					price : data.price,
					originalPrice : data.originalPrice
				});
				cart.add(item);
			}else{
				$(".giftbhmname").text('--');
			}
			
		});
		//绑定单品规则
		$("#skudetail tr:gt(0)").each(function(i, tr) {
			//单品减
			var giftInput = $(tr).find("td:eq(2) input");
			var skuSubButton = $(tr).find('td:eq(0) a:first');
			var skuInput = $(tr).find("td:eq(0) input");
			var skuAddButton = $(tr).find('td:eq(0) a:eq(1)');
			var rootSubButton = $(tr).find('td:eq(1) a:first');
			var rootInput = $(tr).find("td:eq(1) input");
			var rootAddButton = $(tr).find('td:eq(1) a:eq(1)');
			//单品减 
			skuSubButton.click(function(event) {
				var val = parseInt(skuInput.val());
				var rootVal = parseInt(rootInput.val());
				if (val > 1) {
					skuInput.val(--val);
					giftInput.val(val);
					var skuId = skuInput.attr("name").split("-")[1];
					var price = skuInput.attr('data-price');
					var originalPrice = skuInput.attr('data-originalPrice');
					var item = new CartItem({
						goodsId : skuId,
						quantity : val,
						type : '0',
						price : price,
						originalPrice : originalPrice
					});
					cart.add(item);
					if (rootVal > val) {
						rootInput.val(val);
					}
				}
			});
			//单品加
			skuAddButton.click(function(event) {
				var val = parseInt(skuInput.val());
				var rootVal = parseInt(rootInput.val());
				skuInput.val(++val);
				giftInput.val(val);
				var skuId = skuInput.attr("name").split("-")[1];
				var price = skuInput.attr('data-price');
				var originalPrice = skuInput.attr('data-originalPrice');
				var item = new CartItem({
					goodsId : skuId,
					quantity : val,
					type : '0',
					price : price,
					originalPrice : originalPrice
				});
				cart.add(item);
			});
			//root减
			rootSubButton.click(function(event) {
				var val = parseInt(rootInput.val());
				if (val > 0) {
					rootInput.val(--val);
					var skuId = rootInput.attr("name").split("-")[1];
					var price = rootInput.attr('data-price');
					var originalPrice = 0;
					var item = new CartItem({
						goodsId : skuId,
						quantity : val,
						type : '0',
						price : price,
						originalPrice : originalPrice
					});
					cart.add(item);
				}
			});
			//root加
			rootAddButton.click(function(event) {
				var val = parseInt(skuInput.val());
				var rootVal = parseInt(rootInput.val());
				if (rootVal < val) {
					rootInput.val(++rootVal);
					var skuId = rootInput.attr("name").split("-")[1];
					var price = rootInput.attr('data-price');
					var originalPrice = skuInput.attr('data-originalPrice');
					var item = new CartItem({
						goodsId : skuId,
						quantity : val,
						type : '0',
						price : price,
						originalPrice : originalPrice
					});
					cart.add(item);
				} else {
					alert("~亲，您没有可以root的手机了。");
				}
			});
		});

		//更新配件
		function updatePj(type, objs) {
			var datas = [];
			$.each(objs, function(i, data) {
				datas.push({
					"id" : data.id,
					"type" : "保护膜",
					"goodsName" : data.brand.name,
					"specification" : data.batteryCapacity,
					"name" : data.name,
					"imgSrc" : data.defaultImg,
					"value1" : data.price,
					"value2" : data.originalPrice,
					"value3" : data.tmallPrice,
					"tmallUrl" : data.tmallUrl,
					"description" : data.name,
					"number" : 0
				});
			});

			$("#pj" + type).dataViewList({
				viewType : 'block',
				datas : datas,
				itemChange : onListItemnChange1
			}).dataViewList('instance');
		}

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
		var detail = $("#detailList").dataViewList({
			itemChange : onListItemnChange2
		}).dataViewList('instance');

		//加载配件
		$.post("special/accessory.html", {
			'goodsId' : "${goodsId}",
			'type' : '保护膜'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				updatePj('bhm', result.obj);
			}
		});
		$.post("special/accessory.html", {
			'goodsId' : "${goodsId}",
			'type' : '保护壳'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				updatePj('bhk', result.obj);
			}
		});
		$.post("special/accessory.html", {
			'goodsId' : "${goodsId}",
			'type' : '保护套'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				updatePj('bht', result.obj);
			}
		});
		$.post("special/accessory.html", {
			'goodsId' : "${goodsId}",
			'type' : '电池'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				updatePj('dc', result.obj);
			}
		});

		initCart();
	});
</script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../../common/follow.jsp"></jsp:include>
	<jsp:include page="../../common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<a
				href="javascript:history.go(-1);">${goodsName }</a>&gt;&gt;
			<h5>选购专用配件</h5>
		</div>
		<!---------已采购商品信息---------->
		<div class="xgzy_top">
			<div class="xgzy_top_01">已采购商品汇总</div>
			<div class="xgzy_top_02">
				<table id="skudetail" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="198" align="center"><p>
									<span>${goodsName }</span>
								</p>
								<p>
									<a href="javascript:history.back();">【返回修改】</a>
								</p></td>
							<td width="198" align="center"><p>
									<span>ROOT版</span>
								</p>
								<p>
									<a href="javascript:history.go(-1);">【返回修改】</a>
								</p></td>
							<td width="198" align="center">保护膜</td>
							<td width="198" align="center">保护壳</td>
							<td width="198" align="center">保护套</td>
							<td width="198" align="center" id="xgzy_top_02">电池</td>
						</tr>
						<s:set var='rootP' value="%{rootPrice }" />
						<s:iterator value="skuList">
							<tr>
								<td>
									<div id="xq_main_02_let_03">
										<div class="xgzy_top_03 xgzy_name">${color}${edition }</div>
										<div id="xq_main_02_let_03_let">
											<a href="#"><img src="images/jian.jpg" width="20"
												height="20"></a>
										</div>
										<div id="xq_main_02_let_03_ret">
											<input name="skuId-${id }" data-price='${price }'
												data-originalPrice='${originalPrice }' type="text"
												value="${quantity }" onmouseover="this.focus()"
												onmouseout="if(this.value=='')this.value='0';"
												onfocus="this.select()"
												onclick="if(this.value=='0')this.value=''"
												readonly="readonly">
										</div>
										<div id="xq_main_02_let_03_let">
											<a href="#"><img src="images/jia.jpg" width="20"
												height="20"></a>
										</div>
										<div class="clear"></div>
									</div>
								</td>
								<td>
									<div id="xq_main_02_let_03">
										<div class="xgzy_top_03">
											 ${color}${edition } 
										</div>
										<div id="xq_main_02_let_03_let">
											<a href="#"><img src="images/jian.jpg" width="20"
												height="20"></a>
										</div>
										<div id="xq_main_02_let_03_ret">
											<input name="skuId-${id }" data-price='${rootP }' type="text"
												value="${rootQuantity }" onmouseover="this.focus()"
												onmouseout="if(this.value=='')this.value='0';"
												onfocus="this.select()"
												onclick="if(this.value=='0')this.value=''"
												readonly="readonly">
										</div>
										<div id="xq_main_02_let_03_let">
											<a href="#"><img src="images/jia.jpg" width="20"
												height="20"></a>
										</div>
										<div class="clear"></div>
									</div>
								</td>
								<td>
									<div id="xq_main_02_let_03">
										<div class="xgzy_top_03 giftbhmname">正品保护膜啊亲</div>
										<div id="xq_main_02_let_03_ret">
											<input name="" type="text" value="${quantity }"
												onmouseover="this.focus()"
												onmouseout="if(this.value=='')this.value='0';"
												onfocus="this.select()"
												onclick="if(this.value=='0')this.value=''">
										</div>
										<div class="clear"></div>
									</div>
								</td>
								<td align="center">----</td>
								<td align="center">----</td>
								<td align="center" id="xgzy_top_02">----</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>

			</div>
			<div class="xgzy_top_04">
				共计 <span>￥<font class="totalPrice">0.00</font></span> 节省<font
					class='spare'>0.00</font>元
			</div>
		</div>
		<!---------第二块采购说明---------->
		<div class="xgzy_top_05">${goodsName }专用配件</div>
		<div class="xgzy_main">
			<div>
				<div class="xgzy_main_top">
					<span></span>保护膜
				</div>
				<div id='pjbhm' class="xgzy_main_01">
					<div id='giftbhm'></div>
				</div>
			</div>
			<div class="xgzy_main_top">
				<span></span>保护壳
			</div>
			<div id='pjbhk' class="xgzy_main_01"></div>
			<div class="xgzy_main_top">
				<span></span>保护套
			</div>
			<div id="pjbht" class="xgzy_main_01"></div>
			<div class="xgzy_main_top">
				<span></span>电池
			</div>
			<div id="pjdc" class="xgzy_main_01"></div>
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
						<span>原价<font class="totalOriginalPrice">0.00</font>元
						</span><br>共
						<h3 class="totalNumber">0</h3>
						件
						<h3 class="totalPrice">0.00</h3>
						元
					</div>
					<div class="xgzy_main_let_03">
						已省<span class="spare">0.00</span>元
					</div>
					<div class="xgzy_main_let_04">
						<script>
							function add2Cart() {
								var skuMap = {};
								var pjMap = {};
								var items = cart.items.data();
								$.each(items, function(i, item) {
										switch (item.type) {
										case '0'://手机
											if(item.quantity>0)
												skuMap[item.goodsId]=item.quantity
											break;
										case '2'://配件
											if(item.quantity>0)
											pjMap[item.goodsId]=item.quantity
											break;
										case '3'://root
											skuMap[item.goodsId]=skuMap[item.goodsId]+","+item.quantity
											break;
										}
								});
								$.getJSON('cart/addPj2Cart.html', {
									param : $.toJSON(pjMap)
								});
								$.getJSON('cart/addSku2Cart.html', {
									param : $.toJSON(skuMap)
								}).done(function(){
									window.location.href = 'cart/main.html';
								});
							}
						</script>
						<a href="javascript:add2Cart();">加入采购车</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------第三块产品详情---------->
	</div>
	<jsp:include page="../../common/footer.jsp"></jsp:include>
</body>

</html>
