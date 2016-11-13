<!-- 订单操作 -->
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!-- 确认收货  评价 -->
<!-- 线上支付 -->
<c:if test="${param.payMent==0}">
	<!-- 订单状态0正常 -->
	<c:if test="${param.status==0}">
		<!-- 支付状态0未付款 -->
		<c:if test="${param.payStatus==0}"> 
			<%-- <a href="javascript:toPay('${param.id}');" >去付款</a><br> --%>
			<%-- <a href="order/${param.id}/success.html" target="_self">去付款</a><br> --%>
			<a href="yeePay/${param.id}/toPay.html" target="_self">
				<!-- 判断是否有钱包支付流水号，有的话还是未付款就继续支付，没有的话就立即付款 -->
				<c:if test="${empty param.walletPayNo}">
					${param.walletPayNo}
					<!-- 去付款 --><img src="/images/lijifk.png" />
				</c:if>
				<c:if test="${!empty param.walletPayNo}">
					<!-- 继续支付 --><img src="/images/jixuzhifu.png" />
					<%-- <img title="钱包已支付${param.walletNum}元，还需支付${param.actualPayNum}元， 请于00:59前完成剩余支付" src="/images/wen.png" /> --%>
				</c:if>
				</a><br>
				<%-- <c:if test="${!empty param.walletPayNo}">
					<a href="javascript:orderDetail('${param.id}');">详细>></a><br>
				</c:if> --%>
			<c:if test="${empty param.walletPayNo}">
				<a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">取消订单</a><br/> 
			</c:if>
		</c:if>
		<!-- 支付状态 1已付款-->
		<c:if test="${param.payStatus==1}">
			<!-- 发货状态 0未发货-->
			<c:if test="${param.shipStatus==0}">
				<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">退款退货</a><br/> --%>
			</c:if>
			<!-- 发货状态 1已发货-->
			<c:if test="${param.shipStatus==1}">
			<%-- 	<a href="javascript:;" onclick="affirmGoods(this,'${param.id}')"><img src="/images/quersh.png" /><!-- 确认收货 --></a><br /> --%>
				<!-- 判断是否超过七天，是否可以申请退款退货 -->
				<c:if test="${param.showQuit==1}">
					<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">退款退货</a><br/> --%>
				</c:if>
			</c:if>
			<!-- 发货状态 2已送达--> 
			<%-- <c:if test="${param.shipStatus==2}">
				<a href="javascript:;" onclick="affirmGoods(this,'${param.id}')"><img src="/images/quersh.png" /></a><br />
				<!-- 判断是否超过七天，是否可以申请退款退货 -->
				<c:if test="${param.showQuit==1}">
					<a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">退款退货</a><br/>
				</c:if>
			</c:if> --%>
			<!-- 发货状态 3已收货-->
			<c:if test="${param.shipStatus==3}">
				<!-- 评论状态 0未评论-->
				<c:if test="${param.saleType==0}">
					<a href="javascript:goEvaluate('${param.id }');">添加评价</a><br />
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">退款退货</a><br/> --%>
					</c:if>
				</c:if> 
				<!-- 评论状态 1评论列表-->
				<c:if test="${param.saleType==1}">
					<a href="/evaluate/myEvaluates.html">评价列表</a><br/>
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">退款退货</a><br/> --%>
					</c:if>
				</c:if>
			</c:if>
		</c:if>
		<!-- 已退款未发货 -->
		<c:if test="${param.payStatus==3 && param.shipStatus==0}">
		
		</c:if>
	</c:if>
	
	<!-- 订单状态1 申请退货退款中 -->
	 <c:if test="${param.status==1 and param.shipStatus!=3}">
		<%-- <a href="javascript:thDetail('${param.id}','${param.orderNum }');">退货退款进度详情</a><br/> --%>
	</c:if> 
	
	<!-- 订单状态2 申请退货退款已完成-->
	<c:if test="${param.status==2}">
	
		<%-- <!-- 货物状态 4待退货 5已退货-->
		<c:if test="${param.shipStatus == 4 || param.shipStatus == 5 }">
			<!-- 支付状态 1已付款 2待退款 -->
			<c:if test="${param.payStatus==1 || param.payStatus==2}">
				<a href="javascript:;">修改退货退款申请</a><br/>
			</c:if>
			
			<!-- 支付状态 3已退款 -->
			<c:if test="${param.payStatus==3}">
				<!-- 再次购买 -->
			</c:if>
		</c:if> --%>
	</c:if>
	
	<!-- 订单状态3取消 -->
	<c:if test="${param.status==3}">
		
	</c:if>
</c:if>

<!-- 货到付款-->
<c:if test="${param.payMent==1}">
	<!-- 订单状态0正常 -->
	<c:if test="${param.status==0}">
		<!-- 发货状态 0未发货-->
		<c:if test="${param.shipStatus==0}">
			<a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">取消订单</a><br/>
		</c:if>
		<!-- 发货状态 1已发货-->
		<%-- <c:if test="${param.shipStatus==1 and param.payStatus==1}">
			<a href="javascript:;" onclick="affirmGoods(this,'${param.id}')"><img src="/images/quersh.png" /></a><br />
			<!-- 判断是否超过七天，是否可以申请退款退货 -->
			<c:if test="${param.showQuit==1}">
				<a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/>
			</c:if>
		</c:if> --%>
		<!-- 发货状态 2已送达-->
		<%-- <c:if test="${param.shipStatus==2}">
			<a href="javascript:;" onclick="affirmGoods(this,'${param.id}')"><img src="/images/quersh.png" /></a><br />
			<!-- 判断是否超过七天，是否可以申请退款退货 -->
			<c:if test="${param.showQuit==1}">
				<a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/>
			</c:if>
		</c:if> --%>
		
		<!-- 发货状态 3已收货-->
		<c:if test="${param.shipStatus==3}">
			<!-- 支付状态1yi付款 -->
			<c:if test="${param.payStatus==1}">
				<!-- 评论状态 0未评论-->
				<c:if test="${param.saleType==0}">
					<a href="javascript:goEvaluate('${param.id }');">添加评价</a><br />
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/> --%>
					</c:if> 
				</c:if>
				<!-- 评论状态 1评论列表--> 
				<c:if test="${param.saleType==1}">
					<a href="/evaluate/myEvaluates.html">评价列表</a><br />
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/> --%>
					</c:if>
				</c:if>
			</c:if>
		</c:if> 
		
		<!-- 订单状态1 申请退货退款中 收货状态不等于已签收-->
		<c:if test="${param.status==1 and param.shipStatus!=3}">
			<%-- <a href="javascript:thDetail('${param.id}','${param.orderNum }');">退货退款进度详情</a><br/> --%>
		</c:if>
		
		<!-- 订单状态2 退货申请已审核 -->
		<c:if test="${param.status==2}">
			<%-- <!-- 货物状态 1已发货 2已送达(地包) 3已收货-->
			<c:if test="${param.shipStatus == 1 || param.shipStatus == 2 || param.shipStatus == 3 }">
				<!-- 支付状态 1已付款 2待退款 -->
				<c:if test="${param.payStatus==0 || param.payStatus==1}">
					<a href="javascript:;">查看退货退款申请</a><br/>
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
					<a href="javascript:;">查看退货退款申请</a><br/>
				</c:if>
				<!-- 3已退款 -->
				<c:if test="${param.payStatus==3}"></c:if>
			</c:if> --%>
		</c:if>
		
		<!-- 订单状态3取消 -->
		<c:if test="${param.status==3}">
			
		</c:if>
	</c:if>
</c:if>

<!-- pos支付方式 -->
<c:if test="${param.payMent==2}">
	<!-- 订单状态0正常 -->
	<c:if test="${param.status==0}">
		<!-- 发货状态 3已收货-->
		<c:if test="${param.shipStatus==3}">
			<!-- 支付状态1yi付款 -->
			<c:if test="${param.payStatus==1}">
				<!-- 评论状态 0未评论-->
				<c:if test="${param.saleType==0}">
					<a href="javascript:goEvaluate('${param.id }');">添加评价</a><br />
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/> --%>
					</c:if> 
				</c:if>
				<!-- 评论状态 1评论列表--> 
				<c:if test="${param.saleType==1}">
					<a href="/evaluate/myEvaluates.html">评价列表</a><br />
					<!-- 判断是否超过七天，是否可以申请退款退货 -->
					<c:if test="${param.showQuit==1}">
						<%-- <a href="javascript:concelOrder('${param.id}','${param.orderNum }','');">申请退款退货</a><br/> --%>
					</c:if>
				</c:if>
			</c:if>
		</c:if> 
	</c:if>
</c:if> 


<!-- 跳转到商品详情页，无论什么状态都有再次购买所以放到最下面 
<c:if test="${!empty param.detail}"></c:if>-->
<!-- 默认先所有跳到手机列表页 -->
	<a href="javascript:goListView('手机');">再次购买</a>
