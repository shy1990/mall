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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
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
<link rel="stylesheet" href="js/sj/ui/block-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/simple-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/data-view-list/css.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
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
				<table id="skudetail" border="0" cellspacing="0" cellpadding="0"
					width="100%">
					<tbody>
						<tr>
							<td width="198" align="center"><p>
									<span>${goodsName }</span>
								</p>
								<p>
									<a href="javascript:history.back();">【返回修改】</a>
								</p></td>
							<%-- <td width="198" align="center"><p>
									<span>ROOT版</span>
								</p>
								<p>
									<a href="javascript:history.go(-1);">【返回修改】</a>
								</p></td> --%>
							<td width="198" align="center">保护膜</td>
							<td width="198" align="center">保护壳</td>
						</tr>
						<s:set var='rootP' value="%{rootPrice }" />
						<s:iterator value="skuList">
							<tr>
								<td>
									<div id="xq_main_02_let_03">
										<div class="xgzy_top_03 xgzy_name">${color}${edition }</div>
										<div id="xq_main_02_let_03_let">
											<a href="javascript:subSku('${id }');"><img
												src="images/jian.jpg" width="20" height="20"></a>
										</div>
										<div id="xq_main_02_let_03_ret">
											<input id="sku-${id }" name="skuId-${id }"
												data-price='${price }'
												data-originalPrice='${originalPrice }' type="text"
												value="${quantity }" onmouseover="this.focus()"
												onmouseout="if(this.value=='')this.value='0';"
												onfocus="this.select()"
												onclick="if(this.value=='0')this.value=''"
												readonly="readonly">
										</div>
										<div id="xq_main_02_let_03_let">
											<a href="javascript:addSku('${id }');"><img
												src="images/jia.jpg" width="20" height="20"></a>
										</div>
										<div class="clear"></div>
									</div>
								</td>
								<%-- <td>
									<div id="xq_main_02_let_03">
										<div class="xgzy_top_03">${color}${edition }</div>
										<div id="xq_main_02_let_03_let">
											<a href="javascript:subRoot('${id }');"><img
												src="images/jian.jpg" width="20" height="20"></a>
										</div>
										<div id="xq_main_02_let_03_ret">
											<input id="skuRoot-${id }" name="skuRoot-${id }"
												data-price='${rootP }' type="text" value="${rootQuantity }"
												onmouseover="this.focus()"
												onmouseout="if(this.value=='')this.value='0';"
												onfocus="this.select()"
												onclick="if(this.value=='0')this.value=''"
												readonly="readonly">
										</div>
										<div id="xq_main_02_let_03_let">
											<a href="javascript:addRoot('${id }');"><img
												src="images/jia.jpg" width="20" height="20"></a>
										</div>
										<div class="clear"></div>
									</div>
								</td> --%>
								<c:choose>
									<c:when test="${bhmGift != null }">
										<td>
											<div id="xq_main_02_let_03">
												<div class="xgzy_top_03 giftbhmname">${bhmGift.name }</div>
												<div id="xq_main_02_let_03_ret">
													<input class="skuGift-${id }" name="bhmGift-${bhmGift.id }"
														type="text" value="${quantity }"
														data-price="${bhmGift.price }"
														data-originalPrice="${bhmGift.originalPrice }"
														onmouseover="this.focus()"
														onmouseout="if(this.value=='')this.value='0';"
														onfocus="this.select()"
														onclick="if(this.value=='0')this.value=''">
												</div>
												<div class="clear"></div>
											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td align="center">----</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${bhkGift != null }">
										<td>
											<div id="xq_main_02_let_03">
												<div class="xgzy_top_03 giftbhmname">${bhkGift.name }</div>
												<div id="xq_main_02_let_03_ret">
													<input class="skuGift-${id }" name="bhkGift-${bhkGift.id }"
														type="text" value="${quantity }"
														data-price="${bhkGift.price }"
														data-originalPrice="${bhkGift.originalPrice }"
														onmouseover="this.focus()"
														onmouseout="if(this.value=='')this.value='0';"
														onfocus="this.select()"
														onclick="if(this.value=='0')this.value=''">
												</div>
												<div class="clear"></div>
											</div>
										</td>
									</c:when>
									<c:otherwise>
										<td align="center">----</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</s:iterator>
					</tbody>
				</table>

			</div>
			<div class="xgzy_top_04">
				共计 <span>￥<font class="totalPrice">0.00</font></span> 节省<font
					class='spare'>0.00</font>元
			</div>
			<script>
				//单品加
				function addSku(skuId) {
					var val = parseInt($("#sku-" + skuId).val()) + 1;
					$.post("accessory/updateCount.html", {
						skuId : skuId,
						quantity : val
					}).done(function() {
						updateGiftViewNumber();
						updateTotal();
					});
				};
				//单品减
				function subSku(skuId) {
					var val = parseInt($("#sku-" + skuId).val()) - 1;
					var quantity = 0;
					$("#skudetail input[id^='sku-']").each(function() {
						quantity += parseInt($(this).val());
					});
					if (quantity > 1) {
						if (val == 0) {
							$("#sku-" + skuId).closest("tr").remove();
							updateGiftViewNumber();
							updateTotal();
							return;
						}
						$.post("accessory/updateCount.html", {
							skuId : skuId,
							quantity : val
						}).done(function() {
							updateGiftViewNumber();
							updateTotal();
						});
					} else {
						alert("再减就没了。");
					}
				}
				//root加
				function addRoot(skuId) {
					var skuQuantity = parseInt($("#sku-" + skuId).val());
					var rootQuantity = parseInt($("#skuRoot-" + skuId).val()) + 1;
					if (rootQuantity > skuQuantity) {
						alert("没有可以root的手机了。");
					} else {
						$("#skuRoot-" + skuId).val(rootQuantity);
						updateTotal();
					}
				}
				//root减
				function subRoot(skuId) {

				}
			</script>
		</div>
		<!---------第二块采购说明---------->
		<div class="xgzy_top_05">${goodsName }专用配件</div>
		<div class="xgzy_main">
			<div>
				<div class="xgzy_main_top">
					<span></span>保护膜
				</div>
				<div id='pjbhm' class="xgzy_main_01">
					<c:if test="${bhmGift != null }">
						<div id="giftbhm">
							<ul class="ui-sj-data-view-list">
								<li><div class="ui-sj-block-data-view" style="position: relative;">
								<img src="images/img_05.png" style="position: absolute; width:100px; left:140px;">
										<div class="img">
										<a href="accessory/detail/${bhmGift.specCode }.html" target="_blank">
											<img
												alt="${bhmGift.name }" src="${bhmGift.defaultImg }"></a>
										</div>
										<div class="content">
											<p>
												<span class="value1">￥${bhmGift.price }</span>&nbsp;&nbsp;&nbsp;<span
													class="value2 ">￥${bhmGift.originalPrice }</span>
											</p>
											<p class="description">${bhmGift.name }</p>
											<p>购买数量</p>
											<a class="button sub" href="javascript:void(0);"></a><input
												type="text" name="giftview-${bhmGift.id }"><a
												class="button add" href="javascript:void(0);"></a>
										</div>
									</div></li>
							</ul>
						</div>
					</c:if>
					<ul class="ui-sj-data-view-list">
						<c:forEach var="item" items="${bhm }">
							<li><div class="ui-sj-block-data-view">
									<div class="img">
									<a href="accessory/detail/${item.specCode }.html" target="_blank">
										<img alt="${item.name }" src="${item.defaultImg }">
										</a>
									</div>
									<div class="content">
										<p>
											<span class="value1">￥${item.price }</span>&nbsp;&nbsp;&nbsp;<span
												class="value2 ">￥${item.originalPrice }</span>
										</p>
										<p class="description">${item.name }</p>
										<p>购买数量</p>
										<a class="button sub"
											href="javascript:subPj('${item.id }','bhm');"></a><input
											id="bhm-${item.id }" data-brand='${item.brand.name }'
											data-cat='${item.cat.name }' data-name='${item.name }'
											data-price='${item.price }'
											data-originalPrice='${item.originalPrice }' type="text"
											value="0"><a class="button add"
											href="javascript:addPj('${item.id }','bhm');"></a>
									</div>
								</div></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="xgzy_main_top">
				<span></span>保护壳
			</div>
			<div id='pjbhk' class="xgzy_main_01">
				<c:if test="${bhkGift != null }">
					<div id="giftbhk">
						<ul class="ui-sj-data-view-list">
							<li><div class="ui-sj-block-data-view" style="position: relative;">
								<img src="images/img_05.png" style="position: absolute; width:100px; left:140px;">
									<div class="img">
									<a href="accessory/detail/${bhkGift.specCode }.html" target="_blank">
										<img alt="${bhkGift.name }" src="${bhkGift.defaultImg }">
									</a>
									</div>
									<div class="content">
										<p>
											<span class="value1">￥${bhkGift.price }</span>&nbsp;&nbsp;&nbsp;<span
												class="value2 ">￥${bhkGift.originalPrice }</span>
										</p>
										<p class="description">${bhkGift.name }</p>
										<p>购买数量</p>
										<a class="button sub" href="javascript:void(0);"></a><input
											type="text" name="giftview-${bhkGift.id }"><a
											class="button add" href="javascript:void(0);"></a>
									</div>
								</div></li>
						</ul>
					</div>
				</c:if>
				<script>
					function updateGiftViewNumber() {
						//跟新保护膜赠品数量
						var number = 0;
						var id;
						$("input[name^='bhmGift-']").each(function() {
							number += parseInt($(this).val());
							id = $(this).attr("name").split("-")[1];
						});

						$("input[name='giftview-" + id + "']").val(number);
						//更新保护壳赠品数量
						number = 0;
						$("input[name^='bhkGift-']").each(function() {
							number += parseInt($(this).val());
							id = $(this).attr("name").split("-")[1];
						});
						if (number)
							$("input[name='giftview-" + id + "']").val(number);

					};
					updateGiftViewNumber();
				</script>
				<ul class="ui-sj-data-view-list">
					<c:forEach var="item" items="${bhk }">
						<li><div class="ui-sj-block-data-view">
								<div class="img">
								<a href="accessory/detail/${item.specCode }.html" target="_blank">
									<img alt="${item.name }" src="${item.defaultImg }"></a>
								</div>
								<div class="content">
									<p>
										<span class="value1">￥${item.price }</span>&nbsp;&nbsp;&nbsp;<span
											class="value2 ">￥${item.originalPrice }</span>
									</p>
									<p class="description">${item.name }</p>
									<p>购买数量</p>
									<a class="button sub"
										href="javascript:subPj('${item.id }','bhk');"></a><input
										id="bhk-${item.id }" data-brand='${item.brand.name }'
										data-cat='${item.cat.name }' data-name='${item.name }'
										data-price='${item.price }'
										data-originalPrice='${item.originalPrice }' type="text"
										value="0"><a class="button add"
										href="javascript:addPj('${item.id }','bhk');"></a>
								</div>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<div class="xgzy_main_top">
				<span></span>保护套
			</div>
			<div id="pjbht" class="xgzy_main_01">
				<ul class="ui-sj-data-view-list">
					<c:forEach var="item" items="${bht}">
						<li><div class="ui-sj-block-data-view">
								<div class="img">
								<a href="accessory/detail/${item.specCode }.html" target="_blank">
									<img alt="${item.name }" src="${item.defaultImg }"></a>
								</div>
								<div class="content">
									<p>
										<span class="value1">￥${item.price }</span>&nbsp;&nbsp;&nbsp;<span
											class="value2 ">￥${item.originalPrice }</span>
									</p>
									<p class="description">${item.name }</p>
									<p>购买数量</p>
									<a class="button sub"
										href="javascript:subPj('${item.id }','bht');"></a><input
										id="bht-${item.id }" data-brand='${item.brand.name }'
										data-cat='${item.cat.name }' data-name='${item.name }'
										data-price='${item.price }'
										data-originalPrice='${item.originalPrice }' type="text"
										value="0"><a class="button add"
										href="javascript:addPj('${item.id }','bht');"></a>
								</div>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<div class="xgzy_main_top" style="display: none;">
				<span></span>电池
			</div>
			<div id="pjdc" class="xgzy_main_01" style="display: none;">
				<ul class="ui-sj-data-view-list">
					<c:forEach var="item" items="${dc }">
						<li><div class="ui-sj-block-data-view">
								<div class="img">
								<a href="accessory/detail/${item.specCode }.html" target="_blank">
									<img alt="${item.name }" src="${item.defaultImg }"></a>
								</div>
								<div class="content">
									<p>
										<span class="value1">￥${item.price }</span>&nbsp;&nbsp;&nbsp;<span
											class="value2 ">￥${item.originalPrice }</span>
									</p>
									<p class="description">${item.name }</p>
									<p>购买数量</p>
									<a class="button sub"
										href="javascript:subPj('${itme.id }','dc');"></a><input
										id="dc-${item.id }" data-brand='${item.brand.name }'
										data-cat='${item.cat.name }' data-name='${item.name }'
										data-price='${item.price }'
										data-originalPrice='${item.originalPrice }' type="text"
										value="0"><a class="button add"
										href="javascript:addPj('${item.id }','dc');"></a>
								</div>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<div class="xgzy_main_07">
					<div class="xgzy_main_07_top">
						<table border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="82">品类</td>
									<td width="95">品牌</td>
									<td width="200">名称</td>
									<td width="148">规格</td>
									<td width="109">价格</td>
									<td width="193">购量</td>
									<td width="87">天猫价</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="detailList" class="xgzy_main_09">
						<ul class="ui-sj-data-view-list">
						</ul>
						<script>
							function addPj(pjId, type) {
								var view = $("#" + type + "-" + pjId);
								var detail = $("#detailList #" + pjId + "-"
										+ type);
								var num = (parseInt(detail.val()) || 0) + 1;
								if (!detail.length) {
									var html = "<table class=\"ui-sj-simple-data-view\"><tbody><tr><td style=\"width:92px\">"
											+ view.attr("data-brand")
											+ "</td><td style=\"width:93px\">"
											+ view.attr("data-cat")
											+ "</td><td style=\"width:200px\">"
											+ view.attr("data-name")
											+ "</td><td style=\"width:148px\"></td><td class=\"value1\" style=\"width:109px\">￥"
											+ view.attr("data-price")
											+ "</td><td style=\"width:192px\"><a class=\"button sub\" href=\"javascript:subPj('"
											+ pjId
											+ "','"
											+ type
											+ "');\"></a><input id=\""+pjId+"-"+type+"\" value=\""+num+"\" type=\"text\"><a class=\"button add\" href=\"javascript:addPj('"
											+ pjId
											+ "','"
											+ type
											+ "');\"></a></td><td class=\"value3\" style=\"width:87px\"><a href=\"\" target=\"_blank\">￥"
											+ view.attr('data-originalPrice')
											+ "</a></td></tr></tbody></table>";
									$("<li>").append(html).appendTo(
											$("#detailList ul"));

								} else {
									detail.val(num);
								}
								view.val(num);
								updateTotal();
							}
							function subPj(pjId, type) {
								var view = $("#" + type + "-" + pjId);
								var detail = $("#detailList #" + pjId + "-"
										+ type);
								var num = (parseInt(view.val()) || 0) - 1;
								if (num <= 0) {
									num = 0;
									detail.closest("li").remove();
								}
								detail.val(num);
								view.val(num);
								updateTotal();
							}
						</script>
					</div>
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
							//计算价格
							function updateTotal() {
								var totalOra = 0;
								var totalPrice = 0;
								var totalQuantity = 0;
								$("#skudetail input")
										.each(
												function() {
													var quantity = parseInt($(
															this).val());
													var op = parseFloat($(this)
															.attr(
																	"data-originalPrice")) || 0;
													var p = parseFloat(
															$(this)
																	.attr(
																			"data-price"),
															2) || 0;
													totalOra += quantity * op;
													totalPrice += quantity * p;
													totalQuantity += quantity;
												});

								$("#detailList input")
										.each(
												function() {
													var id = $(this).attr("id")
															.split("-");
													id = id[1] + "-" + id[0];
													var view = $("#" + id);
													var quantity = parseInt(view
															.val());
													var op = parseFloat(view
															.attr("data-originalPrice")) || 0;
													var p = parseFloat(
															view
																	.attr("data-price"),
															2) || 0;
													totalOra += quantity * op;
													totalPrice += quantity * p;
													totalQuantity += quantity;
												});
								$(".totalPrice").text(totalPrice.toFixed(2));
								$(".totalOriginalPrice").text(
										totalOra.toFixed(2));
								$(".spare").text(
										(totalOra - totalPrice).toFixed(2));
								$(".totalNumber").text(totalQuantity);
							}
							updateTotal();
							////////////////
							function add2Cart() {
								var skuMap = {};
								var pjMap = {};

								$("#skudetail input[id^='sku-']")
										.each(
												function() {
													var id = $(this).attr("id")
															.split("-")[1];
													skuMap[id] = parseInt($(
															this).val());
												});

								$("#detailList input").each(function() {
									var id = $(this).attr("id").split("-")[0];
									pjMap[id] = parseInt($(this).val());
								});
								$
										.post('cart/addSku2Cart.html', {
											param : $.toJSON(skuMap)
										})
										.done(
												function() {
													$
															.post(
																	'cart/addPj2Cart.html',
																	{
																		param : $
																				.toJSON(pjMap)
																	})
															.done(
																	function() {
																		window.location.href = '<%=basePath%>cart/main.html';
																	});
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
