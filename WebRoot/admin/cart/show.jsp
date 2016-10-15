<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<link href="js/jquery-validation/1.11.1/validate.css" rel="stylesheet"
	type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script> 

<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript"
	src="js/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="js/jquery-validation/1.11.1/messages_bs_zh.js"></script>
<style>
.disabled {
	background-color: #999;
}
</style>

<script type="text/javascript">
	/**
	 * 添加或者移除收藏
	 * @param type 收藏的商品类型  accessories（配件商品）   sku（单品）
	 * @param targetId 要收藏的商品id
	 */
	function add_drop_collect(type, targetId) {
		$.ajax({
			type : "post",
			url : "collect/collect.html",
			dataType : 'json',
			data : {
				'type' : type,
				'targetId' : targetId
			},
			success : function(d) {
				d.msg = d.msg === "取消收藏" ? "点击收藏" : d.msg;
				$("#coll-" + targetId).text(d.msg);
			}
		});
	};
	var wenzi = function() {
		$('.g_img').slideToggle('fast');
	}
	wenzi();
	//更改数量
	function changeQuantity(itemid, quantity) {
		$.get("cart/updateCart.html", {
			cartId : itemid,
			amount : quantity
		}).done(updatePage);

	}
	//更新页面
	function updatePage() {
		//统计商品数量
		var totalQuantity = 0;
		$("input[name='quantity']").each(function() {
			totalQuantity += parseInt($(this).val());
		});
		$("#totalQuantity,.totalAmount").text(totalQuantity);
		//没有selected不能点击下一步
		if ($(":checkbox[id^='checkbox-']:checked").length) {
			$("#next").removeClass("disabled").attr("href",
					"javascript:next();");
		} else {
			$("#next").addClass("disabled").attr("href",
					"javascript:alert('请先勾选您要采购的商品。');");

		}
		updateSelected();
	}
	function updateSelected() {
		//统计选中
		var selectedQuantity = 0;
		var selectedPrice = 0.00;
		var jifen=0;
		$(":checkbox[id^='checkbox-']:checked").each(
				function() {
					$(this).closest('div').parent().find(
							"input[name='quantity']").each(function() {
						selectedQuantity += parseInt($(this).val());
					});
					
					$(this).closest('div').parent().find(".subTotalPrice")
							.each(function() {
								selectedPrice += parseFloat($(this).text());
							});
					$(this).closest('div').parent().find(".subTotalPonint").each(function() {
						jifen += parseFloat($(this).text());
					});
				});

		$(".selectedQuantity").text(selectedQuantity);
		$(".selectedJifen").text(jifen);
		$(".selectedPrice").text("¥" + selectedPrice.toFixed(2));
	}
	//减
	function jian(itemid) {
		var input = $("#quantity-" + itemid);
		var val = parseInt(input.val());
		if (val > 1) {
			//更改数量
			changeQuantity(itemid, val - 1);
		}
	}

	//加
	function jia(itemid) {
		var input = $("#quantity-" + itemid);
		var val = parseInt(input.val());
		//更改数量
		changeQuantity(itemid, val + 1);
	}
	//删除
	function remove(itemid) {
		$.get("cart/remove.html", {
			cartId : itemid
		});
		$("#item-" + itemid).slideUp(function() {
			$(this).remove();
			updatePage();
		});
	}
	function removeSelected() {
		$(":checkbox[id^='checkbox-']:checked").each(function() {
			var id = $(this).attr("id").split("-")[1];
			remove(id);
		});
	}

	$(
			function() {
				updatePage();
				//选择框
				$(":checkbox")
						.change(
								function(event) {
									var checked = $(event.target).attr(
											'checked');
									var checkboxs = $(event.target).closest(
											'div').parent().find(":checkbox");
									checkboxs
											.each(function(i, checkbox) {
												$(checkbox)
														.attr(
																'checked',
																$(event.target)
																		.attr(
																				'checked') || false);
											});
									updatePage();
								});
				//渲染收藏
				$("#sku_list a[id^='coll-']").each(function(i, a) {
					var targetId = $(a).attr("id").split("-")[1];
					var type = "sku";
					$.ajax({
						type : "post",
						url : "collect/collect.html",
						dataType : 'json',
						data : {
							'type' : type,
							'targetId' : targetId
						},
						success : function(d) {
							add_drop_collect('sku', targetId);
						}
					});
				});
				$("#pj_list a[id^='coll-']").each(function(i, a) {
					var targetId = $(a).attr("id").split("-")[1];
					var type = "accessories";
					$.ajax({
						type : "post",
						url : "collect/collect.html",
						dataType : 'json',
						data : {
							'type' : type,
							'targetId' : targetId
						},
						success : function(d) {
							add_drop_collect('accessories', targetId);
						}
					});
				});
			})
</script>
</head>

<body>

	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="#">返回</a></span>当前位置&gt;&gt;
			<h5>采购中心</h5>
		</div>
		<!---------已采购商品信息---------->
		<!---------第二块采购说明---------->
		<div class="caigou_top">
			<div class="caigou_top_let">
				进货单 <span> | 全部商品（<span id="totalQuantity">0</span>）件
				</span>
			</div>
			<div class="caigou_top_mid">
				<a href="javascript:wenzi();"><img src="images/tuwen.jpg"
					width="25" height="21" alt=""></a><a href="javascript:wenzi();"><img
					src="images/wenzi.jpg" width="23" height="21" alt=""></a>
			</div>
			<div class="caigou_top_ret">
				已选商品(不含运费)
				<h4 class="selectedPrice">：¥0.00</h4>
			</div>
			<div class="clear"></div>
		</div>
		<div class="caigou_top_01">
			<table width="1156" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td width="147"><input class="checkAll" type="checkbox"
							name="checkbox" id="checkbox" checked="checked"> <label for="checkbox">全选</label></td>
						<td width="543" align="center">商品信息</td>
						<td width="107" align="center">单价（元）</td>
						<td width="138" align="center">数量</td>
						<td width="113" align="center">金额（元）</td>

						<td width="108" align="center">操作</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="sku_list" class="caigou_main">
			<div class="caigou_main_top">
				<input type="checkbox" checked="checked">手机
			</div>
			<c:set var="totalQuantity" value="0" scope="page" />
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "GoodsSku" }'>
					<div id="item-${item.id}" class="caigou_main_01">
						<div>
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="115"><input id="checkbox-${item.id }"
											type="checkbox" name="checkbox2"  checked="checked"> <label
											for="checkbox2"></label> <img class="g_img"
											src="${item.goods.picSrc }" width="88" height="88"
											align="top" alt=""></td>
										<td width="371" valign="top"><p>${item.goods.goodsName }</p>
										</td>
										<td width="184" valign="top">网络类型：${item.goods.edition }<br>颜色：${item.goods.color.colorName }</td>
										<td width="108" valign="top" id="price-${item.id }">${item.singlePrice }</td>
										<td width="137" valign="top">
											<div id="xq_main_02_let_03">
												<div id="xq_main_02_let_03_let">
													<a href="javascript:jian('${item.id }');"><img
														src="images/jian.jpg" width="20" height="20"></a>
												</div>
												<div id="xq_main_02_let_03_ret">
													<input id="quantity-${item.id }" name="quantity"
														type="text" value="${item.quantity }"
														onmouseover="this.focus()"
														onmouseout="if(this.value=='')this.value='0';"
														onfocus="this.select()"
														onclick="if(this.value=='0')this.value=''">
												</div>
												<div id="xq_main_02_let_03_let">
													<a href="javascript:jia('${item.id }');"><img
														src="images/jia.jpg" width="20" height="20"></a>
												</div>
												<div class="clear"></div>
											</div>
										</td>
										<td width="112" valign="top"><h4
												id="subTotalPrice-${item.id }" class="subTotalPrice">${item.subTotalPrice }</h4></td>
										<td width="117" valign="top"><a
											id='coll-${item.goods.id }'
											href="javascript:add_drop_collect('sku','${item.goods.id }');">移入收藏夹</a><br>
											<a href="javascript:remove('${item.id }');">删除</a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<c:if test="${item.goods.gifts!=null }">
							<c:forEach var="gift" items="${item.goods.gifts }">
								<div>
									<table border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td width="115"><input type="checkbox" name="checkbox2"
													 checked="checked"> <label for="checkbox2"></label> <img
													class="g_img" src="${gift.defaultImg }" width="88"
													height="88" align="top" alt=""></td>
												<td width="371" valign="top"><p>${gift.name }</p></td>
												<td width="184" valign="top">颜色分类：${gift.colors[0].colorName }</td>
												<td width="108" valign="top" class="price">${item.goods.price }</td>
												<td width="137" valign="top">
													<div id="xq_main_02_let_03">
														<div id="xq_main_02_let_03_let">
															<a href="javascript:void(0);"><img
																src="images/jian.jpg" width="20" height="20"></a>
														</div>
														<div id="xq_main_02_let_03_ret">
															<input class="gift_quantity-${item.id }" name="quantity"
																type="text" value="${item.quantity }"
																onmouseover="this.focus()"
																onmouseout="if(this.value=='')this.value='0';"
																onfocus="this.select()"
																onclick="if(this.value=='0')this.value=''"
																readonly="readonly">
														</div>
														<div id="xq_main_02_let_03_let">
															<a href="javascript:void(0);"><img
																src="images/jia.jpg" width="20" height="20"></a>
														</div>
														<div class="clear"></div>
													</div>
												</td>
												<td width="112" valign="top"><h4
														id="gift_subTotalPrice-${item.id }" class="subTotalPrice">${item.quantity*gift.price }</h4></td>
												<td width="117" valign="top"><br></td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<div id="pj_list" class="caigou_main">
			<div class="caigou_main_top">
				<input type="checkbox" checked="checked">配件
			</div>
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "Accessory" }'>
					<div>
						<div id="item-${item.id}" class="caigou_main_02">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="115"><input id="checkbox-${item.id }"
											type="checkbox" name="checkbox2"  checked="checked"> <label
											for="checkbox2"></label> <img class="g_img"
											src="${item.goods.defaultImg }" width="88" height="88"
											align="top" alt=""></td>
										<td width="371" valign="top"><p>${item.goods.name }</p></td>
										<td width="184" valign="top">颜色分类：${item.goods.colors[0].colorName }</td>
										<td width="108" valign="top">${item.goods.price }</td>
										<td width="137" valign="top">
											<div id="xq_main_02_let_03">
												<div id="xq_main_02_let_03_let">
													<a href="javascript:jian('${item.id }');"><img
														src="images/jian.jpg" width="20" height="20"></a>
												</div>
												<div id="xq_main_02_let_03_ret">
													<input id="quantity-${item.id }" name="quantity"
														type="text" value="${item.quantity }"
														onmouseover="this.focus()"
														onmouseout="if(this.value=='')this.value='0';"
														onfocus="this.select()"
														onclick="if(this.value=='0')this.value=''">
												</div>
												<div id="xq_main_02_let_03_let">
													<a href="javascript:jia('${item.id }');"><img
														src="images/jia.jpg" width="20" height="20"></a>
												</div>
												<div class="clear"></div>
											</div>
										</td>
										<td width="112" valign="top"><h4 class="subTotalPrice">${item.subTotalPrice}</h4></td>
										<td width="117" valign="top"><a
											id='coll-${item.goods.id}'
											href="javascript:add_drop_collect('accessories','${item.goods.id }');">移入收藏夹</a><br>
											<a href="javascript:remove('${item.id }');">删除</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div id="jf_list" class="caigou_main">
			<div class="caigou_main_top">
				<input type="checkbox" checked="checked">积分商品
			</div>
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "PointGoods" }'>
					 <div>
						<div id="item-${item.id}" class="caigou_main_02">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="115"><input id="checkbox-${item.id }"
											type="checkbox" name="checkbox2"  checked="checked"> <label
											for="checkbox2"></label> <img class="g_img"
											src="${item.goods.pic }" width="88" height="88"
											align="top" alt=""></td>
										<td width="371" valign="top"><p>${item.goods.name }</p></td>
										<td width="184" valign="top"></td>
										<td width="108" valign="top">${item.goods.integral }</td>
										<td width="137" valign="top">
											<div id="xq_main_02_let_03">
												<div id="xq_main_02_let_03_let">
												</div>
												<div id="xq_main_02_let_03_ret">
													<input id="quantity-${item.id }" name="quantity" disabled="disabled"
														type="text" value="${item.quantity }"
														onmouseover="this.focus()"
														onmouseout="if(this.value=='')this.value='0';"
														onfocus="this.select()"
														onclick="if(this.value=='0')this.value=''">
												</div>
												<div id="xq_main_02_let_03_let">
												</div>
												<div class="clear"></div>
											</div>
										</td>
										<td width="112" valign="top"><h4 class="subTotalPonint">${item.quantity*item.goods.integral}（积分）</h4></td>
										<td width="117" valign="top"><br>
											<a href="javascript:remove('${item.id }');">删除</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> 
				</c:if>
			</c:forEach>

		</div>
		<div class="caigou_main_03">
			<table border="0" cellpadding="0" cellspacing="0" width="1180">
				<tbody>
					<tr>
						<td width="88"><input class="checkAll" type="checkbox"
							name="checkbox3" id="checkbox3" checked="checked"> 全选</td>
						<td width="38"><a href="javascript:removeSelected();">删除</a></td>
						<td width="100"></td>
						<td width="334">已选商品
							<h3 class="selectedQuantity">0</h3>件 合计(不含运费)：
							<h3 class="selectedPrice">¥0.00</h3>(<span class="selectedJifen"></span>积分)
						</td>
						<%-- <td width="151">本次获得积分<span>0</span>分 --%>
						</td>
						<td width="197" align="center" valign="middle"><div
								class="caigou_main_04" style="margin-right: 15px;">
								<a href="goods/lj/3/bjd.html">继续采购</a>
							</div></td>

						<td width="197" align="center" valign="middle"><div
								class="caigou_main_04">
								<a id="next" href="javascript:next();">去结算</a>
								<form id="myform" action="order/add.html" method="post">
									<input id="cartIdInput" type="hidden" name="cartIds" value="" />
								</form>
								<script type="text/javascript">
									function next() {
										var cartIds = [];
										
										$(".xq_mian input[type='checkbox']:checked[id^='checkbox-']").each(function() {
															var id = $(this)
																	.attr("id")
																	.split("-")[1];
															cartIds.push(id);
														});
										cartIds = cartIds.join(",");
										$("#cartIdInput").val(cartIds);
										$("#myform").submit();
										/* $("<form>",{action:"accessory/universal.html",method:"post"}).append($("<input>",{name:'cartIds'}).val(cartIds)).submit(); */
									}
								</script>
							</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
