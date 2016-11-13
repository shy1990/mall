<!-- 订单状态 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 线上支付 -->
<c:if test="${param.payMent==0}">
	<!-- 订单状态0正常 -->
	<c:if test="${param.status==0}">
		<!-- 支付状态0未付款 -->
		<c:if test="${param.payStatus==0}">
			未付款<br>
			<a href="javascript:orderDetail('${param.id }');">订单详情</a>
		</c:if>
		<!-- 支付状态 1已付款-->
		<c:if test="${param.payStatus==1}">
			<!-- 发货状态 0未发货-->
			<c:if test="${param.shipStatus==0}">
				店主已付款<br>
				<a href="javascript:;" onclick="checkShipStatus(this,'${param.id}')">查看是否已发货</a><br>
				<a href="javascript:orderDetail('${param.id }');">订单详情</a>
			</c:if>
			<!-- 发货状态 1已发货-->
			<c:if test="${param.shipStatus==1}">
				卖家已发货<br>
				<a href="javascript:orderDetail('${param.id }');">查看物流</a><br>
				<a href="javascript:orderDetail('${param.id }');">订单详情</a>
			</c:if>
			<!-- 发货状态 2已送达-->
			<c:if test="${param.shipStatus==2}">
				卖家已发货<br>
				<a href="javascript:orderDetail('${param.id }');">查看物流</a><br>
				<a href="javascript:orderDetail('${param.id }');">订单详情</a>
			</c:if>
			<!-- 发货状态 3已收货-->
			<c:if test="${param.shipStatus==3}">
				<!-- 评论状态 0未评论-->
				<c:if test="${param.saleType==0}">
					交易成功<br/>
					<%-- <a href="javascript:orderDetail('${param.id }');">查看物流</a><br> --%>
					<a href="javascript:orderDetail('${param.id }');">订单详情</a>
				</c:if>
				<!-- 评论状态 1评论列表-->
				<c:if test="${param.saleType==1}">
					交易成功<br/>
					<%-- <a href="javascript:orderDetail('${param.id }');">查看物流</a><br> --%>
					<a href="javascript:orderDetail('${param.id }');">订单详情</a>
				</c:if>
			</c:if>
		</c:if>
		<!-- 已退款未发货 -->
		<c:if test="${param.payStatus==3 && param.shipStatus==0}">
			交易完成<br/>
		</c:if>
	</c:if>
	
	<!-- 订单状态1 申请退货退款中 -->
	<c:if test="${param.status==1 and param.shipStatus!=3}">
		待退货退款<br/>
		<a href="javascript:orderDetail('${param.id }');">订单详情</a>
	</c:if>
	
	<!-- 订单状态2 申请退货退款已完成-->
	<c:if test="${param.status==2}">
		退款成功<br/>
		<%-- <!-- 货物状态 4待退货 5已退货-->
		<c:if test="${param.shipStatus == 4 || param.shipStatus == 5 }">
			<!-- 支付状态 1已付款 2待退款 -->
			<c:if test="${param.payStatus==1 || param.payStatus==2}">
				待退货退款<br/>
			</c:if>
			
			<!-- 支付状态 3已退款 -->
			<c:if test="${param.payStatus==3}">
				<!-- 再次购买 -->
			</c:if>
		</c:if> --%>
	</c:if>
	
	<!-- 订单状态3取消 -->
	<c:if test="${param.status==3}">
		交易关闭<br/>
		<a href="javascript:orderDetail('${param.id }');">订单详情</a>
	</c:if>
	<br/>
	<span id="payType">线上支付</span>
</c:if>

<!-- 货到付款-->
<c:if test="${param.payMent==1}">
	<!-- 订单状态0正常 -->
	<c:if test="${param.status==0}">
		<!-- 发货状态 0未发货-->
		<c:if test="${param.shipStatus==0}">
			<a href="javascript:;" onclick="checkShipStatus(this,'${param.id}')">查看是否已发货</a><br>
			<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
		</c:if>
		<!-- 发货状态 1已发货-->
		<c:if test="${param.shipStatus==1}">
			卖家已发货<br>
			<a href="javascript:orderDetail('${param.id }');">查看物流</a><br>
			<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
		</c:if>
		<!-- 发货状态 2已送达-->
		<c:if test="${param.shipStatus==2}">
			卖家已发货<br>
			<a href="javascript:orderDetail('${param.id }');">查看物流</a><br>
			<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
		</c:if>
		
		<!-- 发货状态 3已收货-->
		<c:if test="${param.shipStatus==3}">
			<!-- 支付状态1yi付款 -->
			<c:if test="${param.payStatus==1}">
				<!-- 评论状态 0未评论-->
				<c:if test="${param.saleType==0}">
					交易成功<br/>
					<%-- <a href="javascript:orderDetail('${param.id }');">查看物流</a><br> --%>
					<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
				</c:if>
				<!-- 评论状态 1评论列表-->
				<c:if test="${param.saleType==1}">
					交易成功<br/>
					<%-- <a href="javascript:orderDetail('${param.id }');">查看物流</a><br> --%>
					<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
				</c:if> 
			</c:if>
		</c:if>
		
		<%-- <!-- 订单状态1 退货申请未审核 -->
		<c:if test="${param.status==1}">
			待退货退款<br/>
		</c:if>
		
		<!-- 订单状态2 退货申请已审核 -->
		<c:if test="${param.status==2}">
			<!-- 货物状态 1已发货 2已送达(地包) 3已收货-->
			<c:if test="${param.shipStatus == 1 || param.shipStatus == 2 || param.shipStatus == 3 }">
				<!-- 支付状态 1已付款 2待退款 -->
				<c:if test="${param.payStatus==0 || param.payStatus==1}">
					待退货退款<br/>
				</c:if>
				<!-- 支付状态 3已退款 -->
				<c:if test="${param.payStatus==3}">
					<!-- 再次购买 -->
				</c:if>
			</c:if>
			
			<!-- 货物状态 5已退货 -->
			<c:if test="${param.shipStatus==5}">
				<!-- 2待退款 -->
				<c:if test="${param.payStatus==2 }">
					待退货退款<br/>
				</c:if>
				<!-- 3已退款 -->
				<c:if test="${param.payStatus==3}">
					交易完成<br/>
				</c:if>
			</c:if>
		</c:if> --%>
		<!-- 订单状态1 申请退货退款中 -->
	<c:if test="${param.status==1 and param.shipStatus!=3}">
		待退货退款<br/>
		<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
	</c:if>
	
	<!-- 订单状态2 申请退货退款已完成-->
	<c:if test="${param.status==2}">
		退款成功<br/>
	</c:if>
		
		<!-- 订单状态3取消 -->
		<c:if test="${param.status==3}">
			交易关闭<br/>
			<a href="javascript:orderDetail('${param.id }');">订单详情</a><br/>
		</c:if>
	</c:if>
	<span id="payType">货到付款</span>
</c:if>
