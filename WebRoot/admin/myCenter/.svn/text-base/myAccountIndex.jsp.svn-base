<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="renderer" content="webkit"/>
    <base href="<%=basePath%>"/>
    <title>我的账户</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" src="js/myCenter/myAccountIndex.js"></script>
  </head>
  
  <body>
  <jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
    <div class="xq_mian">
	  <jsp:include page="/admin/myCenter/left.jsp"></jsp:include>
	  		<div class="wodz_ret_gaig">
			<div class="wodz_ret_01">
				<div class="wodz_ret_01_let">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">我的订单</div>
						<div class="wodz_ret_01_let_top_mid"> 
							<a href="javascript:againData('payStatus',1,1);">已付款</a>（${orderMap.paidNum }） 
				           	<a href="javascript:againData('payStatus',0,2);">待付款</a>（${orderMap.obligationNum }）  
				           	<a href="javascript:againData('shipStatus',0,3);"> 待发货</a>（${orderMap.staySendNum }） </div>
				        <div class="wodz_ret_01_let_top_ret"><a href="/order/orders.html">查看全部</a></div>
						<div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="wodz_ret_01_let_txt">
						<ul>
							<c:if test="${orderMap.orders != null }">
					    		<c:forEach var="order" items="${orderMap.orders }">
					    			<li>
										<c:set var="orderFrist" value="true"></c:set><!-- 用户判断是否第一次遍历订单信息 -->
						    			<!-- 遍历订单里面的商品 -->
						    			<c:forEach var="o" items="${order.orderItemss }">
						    				<!-- 判断类型是手机还是配件 -->
							    			<c:if test="${!empty o.goodsSku}">
							    				<!-- 取单品数据 -->
							    				<c:if test="${!orderFrist}">
									    			<div class="wodz_ret_01_let_txt_gai">
														<div class="wodz_ret_01_let_txt_01">
															<a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html"><img src="${o.goodsSku.goods.defaultImg }" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html">${o.goodsSku.goods.brand.name}/${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }</a>
															<br/> <!-- 已签收 2014-09-03 17:40:40 -->
															<br/>
														</div>
														<div class="clear"></div>
													</div>
												</c:if>
												<!-- 只添加一次 -->
												<c:if test="${orderFrist}">
													<div>
														<div class="wodz_ret_01_let_txt_01">
															<a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html"><img src="${o.goodsSku.goods.defaultImg }" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html">${o.goodsSku.goods.brand.name}/${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }</a>
															<br/> <fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
															<br/>
															<span>订单编号：${order.orderNum }</span> 
														</div>
														<div class="wodz_ret_01_let_txt_03">
															<!-- <a href="javascript:;">退换申请</a> -->
															<jsp:include page="/admin/myCenter/orderStatus.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>  
																<jsp:param name="showQuit" value="${order.showQuit}"/>
																<jsp:param name="detail" value="/goods/detail/${o.goodsSku.goods.goodsNum}.html"/>  
															</jsp:include>
														</div>
														<div class="wodz_ret_01_let_txt_04">
															<jsp:include page="/admin/myCenter/orderOperation.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/> 
																<jsp:param name="orderNum" value="${order.orderNum}"/>
																<jsp:param name="showQuit" value="${order.showQuit}"/>
																<jsp:param name="walletPayNo" value="${order.walletPayNo}"/>
																<jsp:param name="walletNum" value="${order.walletNum}"/> 
																<jsp:param name="actualPayNum" value="${order.actualPayNum}"/> 
																<jsp:param name="detail" value="/goods/detail/${o.goodsSku.goods.goodsNum}.html"/>  
															</jsp:include>
														</div>
														<div class="clear"></div>
													</div>
													<c:set var="orderFrist" value="false"></c:set>
												</c:if>
							    			</c:if>
							    			<c:if test="${!empty o.accessory}">
							    				<!-- 取配件数据 -->
												<c:if test="${!orderFrist}">
									    			<div class="wodz_ret_01_let_txt_gai">
														<div class="wodz_ret_01_let_txt_01">
															<a href="/accessory/detail/${o.accessory.specCode}.html"><img src="${o.accessory.defaultImg }" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="/accessory/detail/${o.accessory.specCode}.html">${o.accessory.brand.name}/${o.accessory.name }</a>
															<br/> <!-- 已签收 2014-09-03 17:40:40 -->
															<br/>
														</div>
														<div class="clear"></div>
													</div>
												</c:if>
												<!-- 只添加一次 -->
												<c:if test="${orderFrist }">
													<div>
														<div class="wodz_ret_01_let_txt_01">
															<a href="/accessory/detail/${o.accessory.specCode}.html"><img src="${o.accessory.defaultImg }" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="/accessory/detail/${o.accessory.specCode}.html">${o.accessory.brand.name}/${o.accessory.name }</a>
															<br/> <fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
															<br/>
															<span>订单编号：${order.orderNum }</span> <span id="wodz_ret_01_let_txt_02"><%-- <a href="javascript:orderDetail('${order.id }');">查看物流明细</a> --%></span>
														</div>
														<div class="wodz_ret_01_let_txt_03">
															<!-- <a href="javascript:;">退换申请</a> -->
															<jsp:include page="/admin/myCenter/orderStatus.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>  
																<jsp:param name="showQuit" value="${order.showQuit}"/>
																<jsp:param name="detail" value="/accessory/detail/${o.accessory.specCode}.html"/>  
															</jsp:include>
														</div>
														<div class="wodz_ret_01_let_txt_04">
															<jsp:include page="/admin/myCenter/orderOperation.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>  
																<jsp:param name="orderNum" value="${order.orderNum}"/> 
																<jsp:param name="showQuit" value="${order.showQuit}"/>
																<jsp:param name="walletPayNo" value="${order.walletPayNo}"/>
																<jsp:param name="walletNum" value="${order.walletNum}"/> 
																<jsp:param name="actualPayNum" value="${order.actualPayNum}"/> 
																<jsp:param name="detail" value="/accessory/detail/${o.accessory.specCode}.html"/>  
															</jsp:include>
														</div>
														<div class="clear"></div>
													</div>
													<c:set var="orderFrist" value="false"></c:set>
												</c:if>
												
							    			</c:if>
							    			<!-- 积分商品信息 -->
							    			<c:if test="${!empty o.pointGoods}">
												<c:if test="${!orderFrist}">
									    			<div class="wodz_ret_01_let_txt_gai">
														<div class="wodz_ret_01_let_txt_01">
															<a href="javascript:orderDetail('${order.id }');"><img src="${o.pointGoods.pic}" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="javascript:orderDetail('${order.id }');">${o.pointGoods.type}/${o.pointGoods.name}</a>
															<br/> <!-- 已签收 2014-09-03 17:40:40 -->
															<br/> 
														</div>
														<div class="clear"></div>
													</div>
												</c:if>
												<!-- 只添加一次 -->
												<c:if test="${orderFrist }">
													<div>
														<div class="wodz_ret_01_let_txt_01">
															<a href="javascript:orderDetail('${order.id }');"><img src="${o.pointGoods.pic}" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="javascript:orderDetail('${order.id }');">${o.pointGoods.type}/${o.pointGoods.name}</a>
															<br/> <fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
															<br/>
															<span>订单编号：${order.orderNum }</span> <span id="wodz_ret_01_let_txt_02"><%-- <a href="javascript:orderDetail('${order.id }');">查看物流明细</a> --%></span>
														</div>
														<div class="wodz_ret_01_let_txt_03">
															<!-- <a href="javascript:;">退换申请</a> -->
															<jsp:include page="/admin/myCenter/orderStatus.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>  
																<jsp:param name="showQuit" value="${order.showQuit}"/>
															</jsp:include>
														</div>
														<div class="wodz_ret_01_let_txt_04">
															<jsp:include page="/admin/myCenter/orderOperation.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/> 
																<jsp:param name="orderNum" value="${order.orderNum}"/>
																<jsp:param name="showQuit" value="${order.showQuit}"/> 
																<jsp:param name="walletPayNo" value="${order.walletPayNo}"/>
																<jsp:param name="walletNum" value="${order.walletNum}"/> 
																<jsp:param name="actualPayNum" value="${order.actualPayNum}"/> 
															</jsp:include>
														</div>
														<div class="clear"></div>
													</div>
													<c:set var="orderFrist" value="false"></c:set>
												</c:if>
							    			</c:if>
							    			
							    			<%-- 开始判断有没有赠品信息：${!empty o.gift} --%>
							    			<!-- 赠品商品信息 -->
							    			<c:if test="${!empty o.gift}">
												<c:if test="${!orderFrist}">
									    			<div class="wodz_ret_01_let_txt_gai">
														<div class="wodz_ret_01_let_txt_01">
															<a href="/accessory/detail/${o.gift.accessory.specCode}.html"><img src="${o.gift.accessory.defaultImg }" width="74" height="74" alt=""/></a>
														</div> 
														<div class="wodz_ret_01_let_txt_02"><a href="/accessory/detail/${o.gift.accessory.specCode}.html">${o.gift.accessory.brand.name}/${o.gift.accessory.name }</a>
															<br/> <!-- 已签收 2014-09-03 17:40:40 -->
															<br/> 
														</div>
														<div class="clear"></div>
													</div>
												</c:if>
												<!-- 只添加一次 -->
												<c:if test="${orderFrist }">
													<div>
														<div class="wodz_ret_01_let_txt_01">
															<a href="/accessory/detail/${o.gift.accessory.specCode}.html"><img src="${o.gift.accessory.defaultImg }" width="74" height="74" alt=""/></a>
														</div>
														<div class="wodz_ret_01_let_txt_02"><a href="/accessory/detail/${o.gift.accessory.specCode}.html">${o.gift.accessory.brand.name}/${o.gift.accessory.name }</a>
															<br/> <fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
															<br/>
															<span>订单编号：${order.orderNum }</span> <span id="wodz_ret_01_let_txt_02"><%-- <a href="javascript:orderDetail('${order.id }');">查看物流明细</a> --%></span>
														</div>
														<div class="wodz_ret_01_let_txt_03">
															<!-- <a href="javascript:;">退换申请</a> -->
															<jsp:include page="/admin/myCenter/orderStatus.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/> 
																<jsp:param name="showQuit" value="${order.showQuit}"/> 
																<jsp:param name="detail" value="/goods/detail/${o.goodsSku.goods.skuNum}.html"/>  
															</jsp:include>
														</div>
														<div class="wodz_ret_01_let_txt_04">
															<jsp:include page="/admin/myCenter/orderOperation.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>
																<jsp:param name="orderNum" value="${order.orderNum}"/>   
																<jsp:param name="showQuit" value="${order.showQuit}"/> 
																<jsp:param name="walletPayNo" value="${order.walletPayNo}"/>
																<jsp:param name="walletNum" value="${order.walletNum}"/> 
																<jsp:param name="actualPayNum" value="${order.actualPayNum}"/> 
															</jsp:include>
														</div>
														<div class="clear"></div>
													</div>
													<c:set var="orderFrist" value="false"></c:set>
												</c:if>
							    			</c:if>
							    			 
							    			
						    			</c:forEach>
					    			</li>
					    		</c:forEach>
					    	</c:if>
					    	
					    	<c:if test="${fn:length(orderMap.orders) == 0 }">
					    		暂无订单信息
					    	</c:if>
							
						</ul>
					</div>
				</div>
				<div class="wodz_ret_01_let_gai">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">我的收藏</div>
						<div class="wodz_ret_01_let_top_ret"><a href="/collect/myCollects.html">查看全部</a></div>
						<div>
						</div>
						<div class="clear"></div>
					</div>
					<%-- <div class="wodz_ret_01_let_txt">
						<ul>
							<c:if test="${orderMap.collects != null }">
					    		<c:forEach var="cs" items="${orderMap.collects }">
					    			<li>
										<div>
											<div class="wodz_ret_01_let_txt_01">
												<a href="goods/detail/${cs.GOODS_NUM }.html"><img src="${cs.PIC_SRC }" width="74" height="74" alt=""/></a>
											</div>
											<div class="wodz_ret_01_let_txt_02"><a href="goods/detail/${cs.GOODS_NUM }.html">${cs.BRANDNAME } ${cs.NAME } ${cs.EDITION } ${cs.STANDARD} ${cs.COLOR_NAME }</a>
												<br/> 收藏时间： ${cs.COLLECT_TIME }
												<br/>
											</div>
											<div class="wodz_ret_01_let_txt_04"><a href="goods/detail/${cs.GOODS_NUM }.html">立即下单</a>
											</div>
											<div class="clear"></div>
										</div>
									</li>
					    		</c:forEach>
					    	</c:if>
						</ul>
					</div> --%>
				</div>
			</div>
			<div class="wodz_ret_02">
				<div class="wodz_ret_01_ret">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">账户管理</div>
						<div class="clear"></div>
					</div>
					<div class="wodz_ret_01_let_txt_05">
						${orderMap.userInfo.username}
						<br/> 收货地址：${orderMap.userInfo.pname}${orderMap.userInfo.cname}${orderMap.userInfo.aname}${orderMap.userInfo.address}
						<br/> 收件人：${orderMap.userInfo.truename}
						<br/> 邮编：${orderMap.userInfo.zip}
						<br/> 收件人电话：${orderMap.userInfo.mobile}
					</div>
					<div class="wodz_ret_01_let_txt_06"><a href="/member/toAccountSecurity.html">修改密码</a> <a href="/member/toAccountSecurity.html">修改绑定手机号码</a>
					</div>
				</div>
				<div class="wodz_ret_01_ret_gai">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">我的积分</div>
						<div class="clear"></div>
					</div>
					<div class="wodz_ret_01_let_txt_07">当前积分<span>${orderMap.userInfo.point}</span>分</div>
					<div>
						<!--<div class="wodz_ret_01_let_txt_03"> <a href="#">退换申请</a> </div>
						<div class="wodz_ret_01_let_txt_03"><!-- <a href="#">退换申请</a> </div>-->
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="clear"></div> 
		</div>
		
	  <div class="clear"></div>
	</div>
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
	<jsp:include page="/admin/myCenter/orderJs.jsp"></jsp:include>
  </body>
</html>
