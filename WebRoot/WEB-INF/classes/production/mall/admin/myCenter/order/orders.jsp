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
    
    <title>订单列表</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	
	<style type="text/css">
		.ddgl_main_top td{
			text-align: center;
		}
		table,.ddgl_main_04 table{table-layout:fixed;}
		table td,.ddgl_main_04 table td{word-wrap:break-word;}
		.ddgl_main_04 table td p{width:185px;word-break:break-all;}
		.zhe_die {cursor: pointer;float: right;}
		.caigou_main_top_price {margin-left: 320px;text-align: center;width: 100px;display:none;}
		.caigou_main_top_num {margin-left: 105px;text-align: center;width: 55px;display:none;}
	</style>
	
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" src="js/laydate.js"></script>
	<script type="text/javascript" >
		var countNum = ${orderMap.countNum};
		var pageCount = ${orderMap.pageCount};
		var page = ${orderMap.page};
		var selected = ${orderMap.selected};
	</script>
	<script type="text/javascript" src="js/myCenter/order/orders.js"></script>
  </head>
  
  <body>
  	<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
	<!--======================详情图片开始============================-->
		<div class="xq_mian">
			<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
			<div class="wodz_ret">
				<div class="wodz_ret_01_let_top">
					<div class="wodz_ret_01_let_top_let">订单管理</div>
					<div class="clear"></div>
				</div>
				<div class="ddgl_main">
					<div class="ddgl_main_nav_box">
						<div class="ddgl_main_nav">
							<ul>
								<li><a href="order/orders.html">全部订单</a></li>
								<li><a href="javascript:againData('payStatus',1,1);">已付款（<h4>${orderMap.paidNum }</h4>)</a></li>
								<li><a href="javascript:againData('payStatus',0,2);">待付款（<h4>${orderMap.obligationNum }</h4>)</a></li>
								<li><a href="javascript:againData('shipStatus',0,3);">待发货（<h4>${orderMap.staySendNum }</h4>)</a></li>
							</ul>
							<div class="clear"></div>
						</div> 
						<div class="ddgl_main_sousuo">
							<form id="orders_searchParam_form" action="order/orders.html" method="post" target="_self" >
								<div class="ddgl_main_sousuo_01">
									<input type="text" value="${orderMap.searchParam }" name="searchParam" id="orders_searchParam_input" />
								</div>
								<div class="ddgl_main_sousuo_02"><a href="javascript:searchOrderFun();">订单查询</a></div>
							</form>
							<div class="clear"></div>
						</div> 
						<div class="clear"></div>
					</div>
					<div class="ddgl_main_top">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="335">商品名称</td>
								<td width="85">单价(元)</td>
								<td width="65">数量</td>
								<td width="110">商品编号</td>
								<td width="95">商品操作</td>
								<td width="100">收货人</td>
								<td width="80">订单金额</td>
								<td width="95">订单状态</td>
								<td width="85">操作</td>
							</tr>
						</table>
					</div>
					<div class="ddgl_main_01"> 
						<table width="995" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="15">
									<input id="orders_select_all" type="checkbox" />全选
								</td>
								<td width="74">
									<!-- <a href="javascript:delOrder();">删除</a> -->
								</td>
								<td width="74">
									
								</td>
								<td width="88">
									<form id="search_order_by_time" action="order/orders.html" method="post">
									
										开始时间：<input name="searchStartTime" id="order_search_start_time" value="<fmt:formatDate value="${searchStartTime }" />" type="text" style="width:80px" readonly/>
										结束时间：<input name="searchEndTime" id="order_search_end_time" value="<fmt:formatDate value="${searchEndTime }" />" type="text" style="width:80px" readonly/>
										<input type="button" onclick="search_order_by_time()" value="搜索"/>
									</form>
								</td>
								<!-- <td width="69" align="center">
									<div class="ddgl_main_02"><a href="javascript:;">上一页</a>
									</div>
								</td>
								<td width="69" align="center">
									<div class="ddgl_main_02"><a href="javascript:;">下一页</a>
									</div>
								</td> -->
							</tr>
						</table>
					</div>
					
					<c:if test="${orderMap.orders != null }">
			    		<c:forEach var="order" items="${orderMap.orders }">
			    			<div class="caigou_main" id="${order.id }">
								<div class="caigou_main_top">
									<%-- <span class="caigou_main_top_del"><a href="javascript:delOrder('${order.id}');"><img src="images/lajitong.jpg" width="21" height="23" alt=""/></a></span> --%>
									<span class="caigou_main_top_no"><input type="checkbox" value="${order.id }" id="order_select_input" /><fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd"/> 订单号:${order.orderNum }</span>
									<span class="caigou_main_top_num">0</span>
									<span class="caigou_main_top_price">￥${order.totalCost }</span>
									<span class="zhe_die"><img class="order_zhe_die_img" width="15" height="15" align="left" data-state="1" src="images/shouqi.jpg" /></span>
								</div>
								<div class="ddgl_main_04">
									<table border="0" cellpadding="0" cellspacing="0">
										<c:set var="orderFrist" value="true"></c:set>
						    			<!-- 遍历订单里面的商品 -->
						    			<c:forEach var="o" items="${order.orderItemss }">
						    				<!-- 判断类型是手机还是配件 -->
							    			<c:if test="${!empty o.goodsSku}">
							    				<!-- 取单品数据 -->
								    			<tr> 
								    				<td width="111">
														<label for="checkbox2"></label>
														<a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html"><img src="${o.goodsSku.goods.defaultImg }" width="88" height="88" align="top" alt="" /></a>
													</td>
													<td width="200" valign="top">
														<a href="/goods/detail/${o.goodsSku.goods.goodsNum}.html"><p>${o.goodsSku.goods.brand.name}/${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }/${o.goodsSku.color.colorName }</p></a>
													</td>
													<!-- 追加 购买时价格 -->
													<td width="80" align="center" valign="top">${o.amount }</td>
													<td width="55" align="center" valign="top" class="order_detail_nums" >${o.nums }</td>
													<td width="106" valign="top">${o.goodsSku.skuNum} </td>
													
													<!-- 已经确认收货 -->
													<c:if test="${order.shipStatus == 3}">
														<!--   追加 商品操作， -->
														<c:if test="${empty o.status or o.status eq 'ABORT' or o.status eq 'COMPLETE'}"> 
															<!-- 状态是空或者已终止或者已完成可以申请售后 --> 
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 申请售后 </a> </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'INIT' or o.status eq 'EDITPROCESS'}">
															<!-- 状态已保存  等待卖家处理 -->
															<td width="110" style="text-align: center;" valign="top"> 等待卖家处理  </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'AUDITREJECT'}">
															<!-- 修改退款退货申请  修改申请 -->
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 修改申请 </a> </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'REJECTCLIENT'}"> 
															<!-- 同意退款退货请发货 -->
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 请发货 </a> </td>
														</c:if> 
														<c:if test="${!empty o.status and o.status eq 'CLIENTSEND'}">
															<!-- 状态等待商家处理 -->
															<td width="110" style="text-align: center;" valign="top"> 等待卖家处理  </td>
														</c:if>
													</c:if>
													
													<c:if test="${order.shipStatus != 3}"><td width="110" style="text-align: center;" valign="top">   </td></c:if>

													<!-- 只添加一次 -->
													<c:if test="${orderFrist }">
														<td width="88" rowspan="${fn:length(order.orderItemss)}" valign="top">&nbsp;</td>
														<td width="69" style="text-align: center;" rowspan="${fn:length(order.orderItemss)}" valign="top">
															<h4>￥${order.totalCost }</h4>
															<br />
															含运费<br/>
															(<h4 style="color:#ccc">
																<c:if test="${empty order.carriage}">￥0.00</c:if>
																<c:if test="${!empty order.carriage}">￥${order.carriage}</c:if>
															</h4>)
															
														</td>
														<td width="105" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top">
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
														</td>
														<td width="95" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top"><!-- 还剩7天 -->
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
															
															<br />
														</td>
														<c:set var="orderFrist" value="false"></c:set>
													</c:if>
												</tr>
							    			</c:if>
							    			<c:if test="${!empty o.accessory}">
							    				<!-- 取配件数据 -->
								    			<tr>
								    				<td width="111">
														<label for="checkbox2"></label>
														<a href="/accessory/detail/${o.accessory.specCode}.html"><img src="${o.accessory.defaultImg }" width="88" height="88" align="top" alt="" /></a>
													</td>
													<td width="200" valign="top">
														<a href="/accessory/detail/${o.accessory.specCode}.html"><p>${o.accessory.brand.name}/${o.accessory.name } <%-- ${o.accessory.colors.colorName } 要遍历取数据 --%></p></a>
													</td>
													
													<!-- 追加 购买时价格 -->
													<td width="80" align="center" valign="top">${o.amount}</td>
													<td width="55" align="center" valign="top" class="order_detail_nums">${o.nums }</td>
													<td width="106" valign="top">${o.accessory.accessoriesNum}</td>
													<!-- 已经确认收货  追加 商品操作， -->
													<!-- 已经确认收货 --> 
													<c:if test="${order.shipStatus == 3}">
														<!--   追加 商品操作， -->
														<c:if test="${empty o.status or o.status eq 'ABORT' or o.status eq 'COMPLETE' }">
															<!-- 状态是空或者已终止或者已完成可以申请售后 -->
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 申请售后 </a> </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'INIT' or o.status eq 'EDITPROCESS'}">
															<!-- 状态已保存  等待卖家处理 --> 
															<td width="110" style="text-align: center;" valign="top"> 等待卖家处理  </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'AUDITREJECT'}">
															<!-- 修改退款退货申请  修改申请 -->
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 修改申请 </a> </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'REJECTCLIENT'}">
															<!-- 同意退款退货请发货 -->
															<td width="110" style="text-align: center;" valign="top"><a href="javascript:;" onclick="concelOrder('${order.id}','','${o.id}')" > 请发货 </a> </td>
														</c:if>
														<c:if test="${!empty o.status and o.status eq 'CLIENTSEND'}">
															<!-- 状态等待商家处理 -->
															<td width="110" style="text-align: center;" valign="top"> 等待卖家处理  </td>
														</c:if>
													</c:if>
													
													<c:if test="${order.shipStatus != 3}"><td width="110" style="text-align: center;" valign="top">   </td></c:if>
													
													<!-- 只添加一次 -->
													<c:if test="${orderFrist }">
														<td width="88" rowspan="${fn:length(order.orderItemss)}" valign="top">${order.shipName }</td>
														<td width="69" style="text-align: center;" rowspan="${fn:length(order.orderItemss)}" valign="top">
															<h4>￥${order.totalCost }</h4> 
															<br />
															含运费<br/>
															(<h4 style="color:#ccc">
																<c:if test="${empty order.carriage}">￥0.00</c:if>
																<c:if test="${!empty order.carriage}">￥${order.carriage}</c:if>
															</h4>)
														</td>
														<td width="105" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top">
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
														</td>
														<td width="95" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top"><!-- 还剩7天 -->
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
															<br />
														</td>
														<c:set var="orderFrist" value="false"></c:set>
													</c:if>
												</tr>
							    			</c:if>
							    			<!-- 积分商品 -->
							    			<c:if test="${!empty o.pointGoods}">
							    				<!-- 取积分商品数据 -->
								    			<tr>
								    				<td width="111">
														<label for="checkbox2"></label>
														<img src="${o.pointGoods.pic }" width="88" height="88" align="top" alt="" />
													</td>
													<td width="200" valign="top"> 
														<p>${o.pointGoods.type}/${o.pointGoods.name }</p>
													</td>
													
													<!-- 追加 购买时价格 -->
													<td width="80" align="center" valign="top">0.00</td>
													<td width="55" align="center" valign="top" class="order_detail_nums">${o.nums }</td>
													<td width="106" valign="top"><%-- 积分商品没有编号 --%></td>
													<!-- 已经确认收货  追加 商品操作， -->
													<td width="110" style="text-align: center;" valign="top"> </td>
													
													<!-- 只添加一次 --> 
													<c:if test="${orderFrist }">
														<td width="88" rowspan="${fn:length(order.orderItemss)}" valign="top">${order.shipName }</td>
														<td width="69" style="text-align: center;" rowspan="${fn:length(order.orderItemss)}" valign="top">
															<h4>￥${order.totalCost }</h4> 
															<br />
															含运费<br/>
															(<h4 style="color:#ccc">
																<c:if test="${empty order.carriage}">￥0.00</c:if>
																<c:if test="${!empty order.carriage}">￥${order.carriage}</c:if>
															</h4>)
														</td>
														<td width="105" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top">
															<jsp:include page="/admin/myCenter/orderStatus.jsp"  >
																<jsp:param name="payMent" value="${order.payMent}"/> 
																<jsp:param name="status" value="${order.status}"/> 
																<jsp:param name="payStatus" value="${order.payStatus}"/> 
																<jsp:param name="shipStatus" value="${order.shipStatus}"/> 
																<jsp:param name="saleType" value="${order.saleType}"/> 
																<jsp:param name="id" value="${order.id}"/>  
																<jsp:param name="showQuit" value="${order.showQuit}"/> 
															</jsp:include>
														</td>
														<td width="95" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top"><!-- 还剩7天 -->
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
														</td>
														<c:set var="orderFrist" value="false"></c:set>
													</c:if>
												</tr>
							    			</c:if>
							    			
							    			<!-- 赠品商品信息 -->
							    			<c:if test="${!empty o.gift}"><!-- 如果赠品信息不为空 -->
								    			<tr>
								    				<td width="111">
														<label for="checkbox2"></label>
														<a href="/accessory/detail/${o.gift.accessory.specCode}.html"><img src="${o.gift.accessory.defaultImg }" width="88" height="88" align="top" alt="" /></a>
													</td>
													<td width="200" valign="top"> 
														<a href="/accessory/detail/${o.gift.accessory.specCode}.html"><p>${o.gift.accessory.brand.name}/${o.gift.accessory.name }</p></a>
													</td>
													
													<!-- 追加 购买时价格 -->
													<td width="80" align="center" valign="top">${o.amount }</td>
													<td width="55" align="center" valign="top" class="order_detail_nums">${o.nums }</td>
													<td width="106" valign="top">${o.gift.accessory.accessoriesNum}</td>
													<!-- 已经确认收货  追加 商品操作， -->
													<td width="110" style="text-align: center;" valign="top"></td>
													
													<!-- 只添加一次 -->
													<c:if test="${orderFrist }">
														<td width="88" rowspan="${fn:length(order.orderItemss)}" valign="top">${order.shipName }</td>
														<td width="69" style="text-align: center;" rowspan="${fn:length(order.orderItemss)}" valign="top">
															<h4>￥${order.totalCost }</h4> 
															<br />
															含运费<br/>
															(<h4 style="color:#ccc">
																<c:if test="${empty order.carriage}">￥0.00</c:if>
																<c:if test="${!empty order.carriage}">￥${order.carriage}</c:if>
															</h4>)
														</td>
														<td width="105" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top">
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
														</td>
														<td width="95" rowspan="${fn:length(order.orderItemss)}" align="center" valign="top"><!-- 还剩7天 -->
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
														</td>
														<c:set var="orderFrist" value="false"></c:set>
													</c:if>
												</tr>
							    			</c:if>
							    			
						    			</c:forEach>
					    			</table>
								</div>
							</div>
			    		</c:forEach>
			    	</c:if>
			    	
			    	<c:if test="${fn:length(orderMap.orders) == 0 }">
			    		暂无订单
			    	</c:if>
			    	
					<div class="fenye">
						<ul id="mycenter_orders_page">
						</ul>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
    	
    <jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
    <jsp:include page="/admin/myCenter/orderJs.jsp"></jsp:include>
    
     <script type="text/javascript">
        laydate({
            elem: '#order_search_start_time'
        });
		laydate({
            elem: '#order_search_end_time'
        });
    </script>
    
  </body>
</html>
