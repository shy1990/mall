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
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="#">返回</a></span>当前位置&gt;&gt;
			<h5>订单确认</h5>
		</div>
		<div class="jiayxq_02">确认订单信息</div>
		<div class="jiayxq_03">
			<div class="jiayxq_04">
				<form id="myform" action="order/success.html" method="post">
					<table border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<c:choose>
									<c:when test="${userInfo.truename == null}">
										<td colspan="2">收货人：<input id="shipName" name="shipName" />（请输入收货人姓名）
										</td>
									</c:when>
									<c:otherwise>
										<input id="shipName" type="hidden" name="shipName"
											value="${userInfo.truename }" />
										<td colspan="2">收货人：${userInfo.truename }</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td colspan="2">收货人电话：${userInfo.mobile }</td>
							</tr>
							<tr>
								<td width="856">收货地址 ： ${userInfo.pname } ${userInfo.cname }
									${userInfo.aname }<c:choose>
										<c:when test="${userInfo.address == null}">
											<input id="address" name="address" />（请输入您的详细地址）
									</c:when>
										<c:otherwise>
											<input id="address" type="hidden" name="address"
												value="${userInfo.address }" />
										${userInfo.address }
									</c:otherwise>
									</c:choose>
								</td>
								<td width="272" align="right">提示：如需修改请 <a href="#">联系客服
								</a></td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="hbNo" id="form_hb_no" />
				</form>
			</div>
		</div>
		<div class="caigou_top_01">
			<table width="1173" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td width="641">商品信息</td>
						<td width="165">单价（元）</td>
						<td width="134">数量</td>
						<td width="150">金额（元）</td>
						<td width="83"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="sku_list" class="caigou_main">
			<div class="caigou_main_top">手机</div>
			<c:set var="totalQuantity" value="0" scope="page" />
			
			<c:if test="${hdOrder}">
			 <c:forEach var="item" items="${orderItems}">
			     <div id="item-${item.id}" class="caigou_main_01">
                        <div>
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                    <tr>
                                        <td width="426" valign="top"><p>${item.goodsSku.goodsName }</p>
                                        </td>
                                        <td width="195" valign="top">网络类型：${item.goodsSku.edition }</td>
                                        <td width="166" valign="top" id="price-${item.id }">${item.dealPrice }</td>
                                        <td width="130" valign="top">${item.nums }</td>
                                        <td width="151" valign="top"><h4
                                                id="subTotalPrice-${item.id }" class="subTotalPrice">￥${item.dealPrice }</h4></td>
                                        <td width="73" valign="top"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
			 </c:forEach>
			</c:if>
			
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "GoodsSku" }'>
					<div id="item-${item.id}" class="caigou_main_01">
						<div>
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="426" valign="top"><p>${item.goods.goodsName }</p>
										</td>
										<td width="195" valign="top">网络类型：${item.goods.edition }</td>
										<td width="166" valign="top" id="price-${item.id }">${item.singlePrice }</td>
										<td width="130" valign="top">${item.quantity }</td>
										<td width="151" valign="top"><h4
												id="subTotalPrice-${item.id }" class="subTotalPrice">￥${item.subTotalPrice }</h4></td>
										<td width="73" valign="top"></td>
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
												<td width="426" valign="top"><p>${gift.name }</p></td>
												<td width="195" valign="top">颜色分类：${gift.colors[0].colorName }</td>
												<td width="166" valign="top" class="price">${item.goods.price }</td>
												<td width="151" valign="top">${item.quantity }</td>
												<td width="112" valign="top"><h4
														id="gift_subTotalPrice-${item.id }" class="subTotalPrice">￥${item.quantity*gift.price }</h4></td>
												<td width="73" valign="top"></td>
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
			<div class="caigou_main_top">配件</div>
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "Accessory" }'>
					<div>
						<div id="item-${item.id}" class="caigou_main_02">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="426" valign="top"><p>${item.goods.name }</p></td>
										<td width="195" valign="top">颜色分类：${item.goods.colors[0].colorName }</td>
										<td width="166" valign="top">${item.goods.price }</td>
										<td width="151" valign="top">${item.quantity }</td>
										<td width="112" valign="top"><h4 class="subTotalPrice">￥${item.subTotalPrice}</h4></td>
										<td width="73" valign="top"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div id="jf_list" class="caigou_main">
			<div class="caigou_main_top">积分商品</div>
			<c:forEach var="item" items="${shoppingCart.items }">
				<c:if test='${item.type eq "PointGoods" }'>
					<div>
						<div id="item-${item.id}" class="caigou_main_02">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="426" valign="top"><p>${item.goods.name }</p></td>
										<td width="195" valign="top"></td>
										<td width="166" valign="top">${item.goods.integral }</td>
										<td width="151" valign="top">${item.quantity }</td>
										<td width="112" valign="top"><h4 class="subTotalPonint">￥${item.quantity*item.goods.integral}</h4></td>
										<td width="73" valign="top"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		
	  <!-- 使用红包包 -->
      <c:set var="areas" value="2184,2185,2186,2187,2188,2189,2190,3275" />
       <%-- <c:if test="${fn:indexOf(areas,sessionInfo.area) > -1}">  --%>
       	<div class="caigou_main hb_content">
			<div class="caigou_main_top">
				<span>使用红包：</span>
				<select class="hb_no" id="hb_no" >
				    <option>--请选择--</option>
			   </select>
			   <span id="hb_num_show" style="color:red;float:right;margin-right: 200px;">￥0</span>
			</div>
		</div>
      <%-- </c:if> --%>
		
		

		<div class="caigou_main">
			<div class="caigou_main_top">运费</div>
			<div>
				<div class="caigou_main_02"
					style="background-color: white; line-height: 30px;">
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="426" valign="top"><c:choose>
										<c:when test="${session.order.carriage !=0}">
						手机不满一台或者配件不满300元每单加6元运费
					</c:when>
										<c:otherwise>
						手机满一台或者配件已满300元免收运费
					</c:otherwise>
									</c:choose> <br />
								<span style="color: red;">晚上8点之前的订单当天发货</span></td>
								<td width="195" valign="top"></td>
								<td width="166" valign="top"></td>
								<td width="151" valign="top"></td>
								<td width="112" valign="top"><h4 class="subTotalPonint">
										￥${session.order.carriage }</h4></td>
								<td width="73" valign="top"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="ddqrxx_nav">
			<div class="ddqrxx_nav_let">
				实付款：<span id="total_cost_span" data-total="${session.order.totalCost }"> ${session.order.totalCost }</span>元
			</div>
			<div class="ddqrxx_nav_ret">
				<div class="ddqrxx_nav_ret_let">
					<a href="cart/main.html">返回购物车修改</a>
				</div>

				<div class="ddqrxx_nav_ret_ret">
					<a href="javascript:next();" style="font-size: 16px;">提交订单</a>
					<script type="text/javascript">
						function next() {
							var shipName = $("#shipName").val();
							var address = $("#address").val();
							var isHbOrder = "${hdOrder}";
							if (shipName && address) {
								
								//判断是不是活动商品订单//活动商品订单判断是否要提醒达到次日发货的提醒界限
								if(isHbOrder){//活动商品去检测
									$.ajax({
						                type: "get",
						                url : "order/hd/checkHdOrderMsg.html",
						                dataType:'json',
						                async:true,
						                success: function(d){
						                	if(d.success){
						                		$("#myform").submit();
						                	}else{
						                		//如果obj是false那么就是库存不足
						                		if(d.obj){
						                			if(confirm(d.msg)){
	                                                    $("#myform").submit();
	                                                }else{
	                                                	location.href="member/home.html";
	                                                    return;
	                                                }
						                		}else{//库存不足
						                			alert(d.msg);
						                			location.href="member/home.html";
						                		}
						                	}
						                }
						            });
								}else{
									$("#myform").submit();
								}
							} else {
								alert('请填写完整的订单信息。');
							}

						}
					</script>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<script type="text/javascript">
		$(function(){
			$("#hb_no").change(function(){
				var hbNum = $(this).find("option:selected").data("num");
				var total = parseInt($("#total_cost_span").data("total"));
				if(hbNum){
					$("#hb_num_show").text("￥-"+hbNum);
					$("#form_hb_no").val($(this).val());
					$("#total_cost_span").text(total-hbNum);
				}else{
					$("#hb_num_show").text("￥0");
					$("#form_hb_no").val("");
					$("#total_cost_span").text(total);
				}
			});
			
			//加载可使用红包
			$.ajax({
				type: "get",
				url : "hb/gethb.html",
				dataType:'json',
				success: function(d){
					//console.info(d);
					//console.info(d.obj);
					var total = parseInt($("#total_cost_span").data("total"));
					if(d&&d.obj&&d.obj.lessLimitAmount<=total){
						var option = "<option value='"+d.obj.id+"' data-num='"+d.obj.amount+"'  >"+d.obj.name+"</option>";
						$("#hb_no").append(option);
						//$(".hb_content").show();
					}
				}
			});
		});
	</script>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
