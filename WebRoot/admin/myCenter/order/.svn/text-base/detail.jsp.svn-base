<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta name="renderer" content="webkit">
    <base href="<%=basePath%>">

			<title>订单详情</title> 

			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			<link href="css/css.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
			<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
			<script type="text/javascript">
			</script>
		</head>

		<body>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
			<div class="xq_mian">
				<div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<a href="/order/orders.html">订单列表</a>&gt;&gt;<h5>订单详情</h5>
				</div>
				<div class="jiayxq_01">
					<p><span>当前订单状态：
						<c:if test="${orderMap.order.payStatus == 0}">
							待付款  <%-- <a href="yeePay/${orderMap.order.id}/toPay.html" target="_self">去付款</a> --%>
						</c:if>
						<c:if test="${orderMap.order.payStatus == 1}">
							<c:if test="${orderMap.order.shipStatus == 0}">
								待发货
							</c:if>
							<c:if test="${orderMap.order.shipStatus == 1}">
								已发货
							</c:if>
							<c:if test="${orderMap.order.shipStatus == 2}">
								已收货
							</c:if>
							<c:if test="${orderMap.order.shipStatus == 3}">
								待退货
							</c:if>
							<c:if test="${orderMap.order.shipStatus == 4}">
								已退货
							</c:if>
							<c:if test="${orderMap.order.shipStatus == 5}">
								卖家拒绝退货
							</c:if>
						</c:if>
						<c:if test="${orderMap.order.payStatus == 2}">
							待退款
						</c:if> 
						<c:if test="${orderMap.order.payStatus == 3}">
							已退款
						</c:if> 
						<c:if test="${orderMap.order.payStatus == 2}">
							卖家拒绝退款
						</c:if> 
						</span> 
						<br/>如果没有收到货，或收到货后出现问题，您可以
						<a href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a>
					</p>
					<span>物流信息</span>
					<c:if test="${!empty orderMap.express}">
						<br/>发货方式：快递
						<br/>物流公司：${orderMap.express.expressName }
						<br/>运单号码：${orderMap.express.nu }
						<br/>物流跟踪：
						<c:forEach var="e" varStatus="i" items="${orderMap.express.data }">
							<c:if test="${i.index != fn:length(orderMap.express.data)-1}">
								<br/>${e.time } ${e.context} 
							</c:if>
							<c:if test="${i.index == fn:length(orderMap.express.data)-1}">
								<br>
								<h5>${e.time } ${e.context} </h5>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty orderMap.express}">
						<br/>暂无快递信息
					</c:if>
	
	
				</div>
				<div class="jiayxq_02">
					订单信息
					<c:if test="${!empty order.walletPayNo}">
						<div style="font-size: 14px; float:right; margin-right:50%;">
						&nbsp;&nbsp;&nbsp;&nbsp;钱包已支付<span style="color:red">${order.walletNum}</span>元，还需支付<span  style="color:red">${order.actualPayNum}</span>元， 请于00:59前完成剩余支付
						</div>
					</c:if>
				</div>
				<div class="jiayxq_03">
					<div class="jiayxq_04">
						<table border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="321">订单编号：${orderMap.order.orderNum}</td>
									<td width="459">下单时间：<fmt:formatDate value="${orderMap.order.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>发货时间：<fmt:formatDate value="${orderMap.order.shipTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
								<tr>
									<td>付款时间：<fmt:formatDate value="${orderMap.order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>收货电话：${orderMap.order.shipTel }</td>
									<td>收货人：${orderMap.order.shipName }</td>
								</tr>
								<tr>
									<td>付款方式：
										<c:if test="${orderMap.order.payMent==1}">货到付款</c:if>
										<c:if test="${orderMap.order.payMent==0}">网上支付</c:if>
									</td>
									<td colspan="2">收货地址 ：${orderMap.order.pname } ${orderMap.order.cname } ${orderMap.order.aname } ${orderMap.order.address }  ${orderMap.order.shipZip }</td>
								</tr>
							</tbody> 
						</table>
					</div>
					<div class="jiayxq_05">
						<table border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="459">商品名称</td>
									<td width="163">数量</td>
									<td width="158">价格</td>
									<td width="143">状态</td>
									<td width="124">积分</td>
									<td width="94">物流费</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="jiayxq_06">
						<table border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<c:set var="orderFrist" value="true"></c:set>
				    			<!-- 遍历订单里面的商品 -->
				    			<c:forEach var="o" items="${orderMap.order.orderItemss }">
				    				<!-- 判断类型是手机还是配件 -->
					    			<c:if test="${!empty o.goodsSku}">
					    				<!-- 取单品数据 -->
						    			<tr> 
											<td width="439">
												<label for="checkbox2"></label>
												<img src="${o.goodsSku.goods.defaultImg }" width="56" height="55" align="left" alt="">${o.goodsSku.goods.brand.name}/${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }</td>
											<td width="132" align="center" valign="top">
												<p>${o.nums}</p>
											</td>
											<!-- 只添加一次 -->
											<c:if test="${orderFrist }">
											
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="172" align="center" valign="top">
													<h4>￥${orderMap.order.totalCost }</h4>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="155" align="center" valign="top">
												<!-- 根据付款状态显示：付款，确认付款  支付状态：0 未付款，1 已付款， 2待退款， 3 已退款,4 卖家拒绝退款-->
													<c:if test="${orderMap.order.payStatus==0 }">
														<a href="javascript:;">去付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==1 }">
														<a href="javascript:;">确认付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==2 }">
														<a href="javascript:;">待付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==3 }">
														<a href="javascript:;">已付款</a>
													</c:if>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="128" align="center" valign="top">
													<h2>+${orderMap.order.orderPoints }</h2>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="131" align="center" valign="top">
													${orderMap.order.carriage }
													<br>
													<span>（快递）</span>
												</td>
												<c:set var="orderFrist" value="false"></c:set>
											</c:if>
										</tr>
					    			</c:if>
					    			<c:if test="${!empty o.accessory}">
					    				<!-- 取配件数据 -->
						    			<tr>
						    				<td width="439">
												<label for="checkbox2"></label>
												<img src="${o.accessory.defaultImg }" width="56" height="55" align="left" alt="">${o.accessory.brand.name}/${o.accessory.name }</td>
											<td width="132" align="center" valign="top">
												<p>${o.nums}</p>
											</td>
											<!-- 只添加一次 -->
											<c:if test="${orderFrist }">
											
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="172" align="center" valign="top">
													<h4>￥${orderMap.order.totalCost }</h4>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="155" align="center" valign="top">
												<!-- 根据付款状态显示：付款，确认付款  支付状态：0 未付款，1 已付款， 2待退款， 3 已退款,4 卖家拒绝退款-->
													<c:if test="${orderMap.order.payStatus==0 }">
														<a href="javascript:;">去付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==1 }">
														<a href="javascript:;">确认付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==2 }">
														<a href="javascript:;">待付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==3 }">
														<a href="javascript:;">已付款</a>
													</c:if>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="128" align="center" valign="top">
													<h2>+${orderMap.order.orderPoints }</h2>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="131" align="center" valign="top">
													${orderMap.order.carriage }
													<br>
													<span>（快递）</span>
												</td>
												<c:set var="orderFrist" value="false"></c:set>
											</c:if>
										</tr>
					    			</c:if>
					    			<!-- 积分商品信息 -->
					    			<c:if test="${!empty o.pointGoods}">
					    				<!-- 取积分商品信息数据 -->
						    			<tr>
						    				<td width="439">
												<label for="checkbox2"></label>
												<img src="${o.pointGoods.pic }" width="56" height="55" align="left" alt="">${o.pointGoods.type}/${o.pointGoods.name }</td>
											<td width="132" align="center" valign="top">
												<p>${o.nums}</p>
											</td>
											<!-- 只添加一次 -->
											<c:if test="${orderFrist }">
											
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="172" align="center" valign="top">
													<h4>￥${orderMap.order.totalCost }</h4>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="155" align="center" valign="top">
												<!-- 根据付款状态显示：付款，确认付款  支付状态：0 未付款，1 已付款， 2待退款， 3 已退款,4 卖家拒绝退款-->
													<c:if test="${orderMap.order.payStatus==0 }">
														<a href="javascript:;">去付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==1 }">
														<a href="javascript:;">确认付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==2 }">
														<a href="javascript:;">待付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==3 }">
														<a href="javascript:;">已付款</a>
													</c:if>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="128" align="center" valign="top">
													<h2>+${orderMap.order.orderPoints }</h2>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="131" align="center" valign="top">
													${orderMap.order.carriage }
													<br>
													<span>（快递）</span>
												</td>
												<c:set var="orderFrist" value="false"></c:set>
											</c:if>
										</tr>
					    			</c:if>
					    			
					    			<!-- 赠品信息 -->
					    			<c:if test="${!empty o.gift}">
						    			<tr>
						    				<td width="439">
												<label for="checkbox2"></label>
												<img src="${o.gift.accessory.defaultImg }" width="56" height="55" align="left" alt="">${o.gift.accessory.brand.name}/${o.gift.accessory.name }</td>
											<td width="132" align="center" valign="top">
												<p>${o.nums}</p>
											</td>
											<!-- 只添加一次 -->
											<c:if test="${orderFrist }">
											
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="172" align="center" valign="top">
													<h4>￥${orderMap.order.totalCost }</h4>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="155" align="center" valign="top">
												<!-- 根据付款状态显示：付款，确认付款  支付状态：0 未付款，1 已付款， 2待退款， 3 已退款,4 卖家拒绝退款-->
													<c:if test="${orderMap.order.payStatus==0 }">
														<a href="javascript:;">去付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==1 }">
														<a href="javascript:;">确认付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==2 }">
														<a href="javascript:;">待付款</a>
													</c:if>
													<c:if test="${orderMap.order.payStatus==3 }">
														<a href="javascript:;">已付款</a>
													</c:if>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="128" align="center" valign="top">
													<h2>+${orderMap.order.orderPoints }</h2>
												</td>
												<td rowspan="${fn:length(orderMap.order.orderItemss)}" width="131" align="center" valign="top">
													${orderMap.order.carriage }
													<br>
													<span>（快递）</span>
												</td>
												<c:set var="orderFrist" value="false"></c:set>
											</c:if>
										</tr>
					    			</c:if>
					    			
				    			</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
		</body>

		</html>
