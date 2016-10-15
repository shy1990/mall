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

			<title>售后申请</title> 

			<meta http-equiv="pragma" content="no-cache"/>
			<meta http-equiv="cache-control" content="no-cache"/>
			<meta http-equiv="expires" content="0"/>
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
			<meta http-equiv="description" content="This is my page"/>
			<link href="css/css.css" rel="stylesheet" type="text/css" />
			<link href="css/ultlon/ultlon_list.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
			<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
			<script type="text/javascript">
				$(function() {
				    function conPosition() {
				        var oBackground = document.getElementById("background");
				        var dw = $(document).width();
				        var dh = $(document).height();
				        oBackground.style.width = dw+'px';
				        oBackground.style.height = dh+'px';
				        var oContent = document.getElementById("content");
				        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				        var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
				        var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 4) + scrollTop;
				        oContent.style.left = l + 'px';
				        oContent.style.top = t + 'px';
				    }
				    $(".sel_service_btn").click(function() {
				        $("#background, #content").show();
				        conPosition();
				        $("#ultlon_apply_type").val($(this).data("service"));
				    });
				    
				    $(".open_service_btn").click(function() {
				        $("#ultlon_apply_type").val($(this).data("service")); 
				        document.getElementById("save").submit();
				    });
				    
				    $("#background,#ultlon_btn_cl").click(function() {$("#background, #content").hide();});
				    //点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
				    $(window).resize(function() {conPosition();});
				    //$(window).scroll(function() {conPosition();});
				    //开启内容跟随垂直滚动条（水平滚动条需要处理的问题更多，暂时没有考虑）
				});
			</script>
			<style type="text/css">
				.ddgl_main {
					  min-height: 500px;
					}
				#background {position:absolute; z-index:998; top:0px; left:0px; background:rgb(50,50,50);background:rgba(0,0,0,0.5); display:none;}
				#content {position:absolute; width:580px; z-index:999; padding:10px; background:#fff; border-radius:5px; display:none;}
				.ultlon_content_body{text-align: center;}	 
				.ultlon_gather{margin-top:25px;font-size: 22px;}
				.ultlon_content_warming{  color:red;font-size: 36px;}
				.ultlon_content_body_btn{border: 1px solid #ccc;
					  background: #F9F9F9;
					  width: 250px;
					  margin: 20px auto;
					  height: 30px;
					  line-height: 30px;
					  cursor: pointer;
					  font-size: 18px;
				}
				#ultlon_btn_cl{margin-top:0px;font-size: 16px;}
				.ultlon_as{margin:auto;color:white;width: 600px;}
				.gather_price_msg_body{width:580px;}
				.gather_price_msg_left{width: 50%; text-align: right; float: left;}
				.gather_price_msg_right{width: 50%; text-align: left; float: left;}
				.clear{clear:both}
			</style>
		</head>

		<body>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
			<div class="xq_mian">
				<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
				<div class="wodz_ret">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">订单信息</div>
						<div class="clear"></div>
					</div>
					<div class="ddgl_main">
						<%-- <br><br><br><br><br>
						订单信息：${order}
						<br><br><br><br><br>
						串号记录信息：${as}
						<br><br><br><br><br>
						服务信息：${service}
						<br><br><br><br><br>
						-------------------------------------------------------------------
						<c:if test="${fn:contains(service,'WX')}">
							有维修
						</c:if> --%>
						
						<table width="auto" class="ultlon_table1">
							<tr>
								<table width="auto" class="ultlon_order_info">
									<tr>
										<td class="td_text_right">订单编号：</td>
										<td class="td_text_left">${order.ORDERNUM}</td>
										<td class="td_text_right">商品编号：</td>
										<td class="td_text_left">${order.SKUNUM}</td>
										<td class="td_text_right">下单时间：</td>
										<td class="td_text_left">${order.CREATETIME}</td>
									</tr>
									<tr>
										<td class="td_text_right">收货电话：</td>
										<td class="td_text_left">${order.SHIPTEL}</td>
										<td class="td_text_right">收货人：</td>
										<td class="td_text_left">${order.SHIPNAME}</td>
										<td class="td_text_right">签收时间：</td>
										<td class="td_text_left">${order.SIGNFORTIME}</td>
									</tr>
									<tr>
										<td class="td_text_right">购买人：</td>
										<td class="td_text_left">${order.USERNAME}</td>
										<td class="td_text_right">收货地址：</td>
										<td class="td_text_left" colspan="3">${order.PNAME}${order.CNAME}${order.ANAME}${order.ADDRESS}</td>
									</tr>
								</table>
							</tr>
							<tr>
								<table class="ultlon_sku_info" width="auto">
									<tr>
										<th colspan="2">商品名称</th>
										<th>数量</th>
										<th>价格</th>
									</tr>
									<tr>
										<td style="text-align:left;width:120px;height:120px;"><img src="${order.PICSRC}" width="120" height="120"></td>
										<td style="text-align:left;">${order.GOODSNAME} ${order.COLORNAME} ${order.EDITION} </td>
										<td>${order.NUMS}</td>
										<td>￥${order.DEALPRICE}</td>
									</tr>
								</table>
							</tr>
							<tr>
							  <table>
							  	<tr style="height:60px; font-size:24px;">
								  <td colspan="6">现价格：<span class="ultlon_td7">${order.SKUPRICE}</span>元</td>
							    </tr>
							  </table>
							</tr>
							<table class="ultlon_service">
								<tr class="ultlon_divT <c:if test="${!fn:contains(service,'KXS')}">no_service</c:if> ">
									<td colspan="2">
										<div class="ultlon_ldiv <c:if test="${!fn:contains(service,'KXS')}">ultlon_div</c:if>">开箱损</div>
									</td>
									<td colspan="2">
										签收5小时内产品开箱验机外观破损瑕疵、缺漏附件等
									</td>
									<td colspan="2" style="width:5%;">
										<div class="circle <c:if test="${fn:contains(service,'KXS')}"> sel_service_btn</c:if>" <c:if test="${fn:contains(service,'KXS')}">data-service="KXS"</c:if>>申请售后</div>
									</td>
								</tr>
								<tr class="ultlon_divT <c:if test="${!fn:contains(service,'THH30')}">no_service</c:if>">
									<td colspan="2">
										<div class="ultlon_ldiv <c:if test="${!fn:contains(service,'THH30')}">ultlon_div</c:if>">30天退换货</div>
									</td>
									<td colspan="2">
										收货日起30天内，商品完好的（不影响二次销售）情况下，可无理由退换货
									</td>
									<td colspan="2" style="width:5%;">
										<div class="circle <c:if test="${fn:contains(service,'THH30')}"> sel_service_btn</c:if>" <c:if test="${fn:contains(service,'THH30')}">data-service="THH30"</c:if>>申请售后</div>
									</td>
								</tr>
								<tr class="ultlon_divT <c:if test="${!fn:contains(service,'WX')}">no_service</c:if>">
									<td colspan="2">
										<div class="ultlon_ldiv <c:if test="${!fn:contains(service,'WX')}">ultlon_div</c:if>">维修</div>
									</td>
									<td colspan="2">
										收货日起两年内，商品无人为损坏，可享受维修服务
									</td>
									<td colspan="2" style="width:5%;">
										<div class="circle <c:if test="${fn:contains(service,'WX')}"> open_service_btn</c:if>" <c:if test="${fn:contains(service,'WX')}">data-service="WX"</c:if>>申请售后</div>
									</td>
								</tr>
								<tr class="ultlon_divT <c:if test="${!fn:contains(service,'DMDHX100')}">no_service</c:if>">
									<td colspan="2">
										<div class="ultlon_ldiv <c:if test="${!fn:contains(service,'DMDHX100')}">ultlon_div</c:if>">多美达百日换新</div>
									</td>
									<td colspan="2" >
										收货日起百日内 商品有质量问题。只要无人为损坏，均可支持换新
									</td>
									<td colspan="2" style="width:5%;">
										<div class="circle <c:if test="${fn:contains(service,'DMDHX100')}"> sel_service_btn</c:if>" <c:if test="${fn:contains(service,'DMDHX100')}">data-service="DMDHX100" </c:if>>申请售后</div>
									</td>
								</tr>
							</table>
						</table> 
					</div><!-- ddgl_main  end -->
				</div>
				<div class="clear"></div>
			</div>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
			
			<!-- 弹窗 -->
			<div id="background"></div>
			<div id="content">
			    <div class="ultlon_content_body">
			    	<form action="ultlon/save.html" method="post" id="save">
				    	<%-- <dir class="ultlon_gather ultlon_content_body_title">
				    		<div><div class="gather_price_msg_left">购买价：</div><div class="gather_price_msg_right">${order.DEALPRICE}元</div></div>
				    		<div><div class="gather_price_msg_left">现价：</div><div class="gather_price_msg_right">${order.SKUPRICE}元</div></div>
				    		<c:if test="${order.DEALPRICE > order.SKUPRICE }">
								<div><div class="gather_price_msg_left">可退款：</div><div class="gather_price_msg_right"><span style="color:red;">${order.SKUPRICE}</span>元</div></div>
								<input type="hidden" name="retreatPrice" value="${order.SKUPRICE}">
							</c:if>
							<c:if test="${order.DEALPRICE < order.SKUPRICE }">
								<div><div class="gather_price_msg_left">可退款：</div><div class="gather_price_msg_right"><span style="color:red;">${order.DEALPRICE}</span>元</div></div>
								<input type="hidden" name="retreatPrice" value="${order.DEALPRICE}">
							</c:if> 
				    		<div class="clear"></div>
				    	</dir> --%>
				    	<dir class="ultlon_gather ultlon_content_warming">
				    		<div class="circle1">!</div>
				    		<div>退货价按到货日页面最低价执行</div>
				    		<div class="clear"></div>
				    	</dir>
				    	
				    	<input type="hidden" name="imei" value="${as.imei}"/>
				    	<input type="hidden" name="orderNum" value="${as.orderNum}"/>
				    	<input type="hidden" name="receiveTime" value="${as.receiveTime}"/>
				    	<input type="hidden" name="type" value="" id="ultlon_apply_type"/>
				    	<input type="hidden" name="skuCode" value="${as.skuCode}"/>
				    	<input type="hidden" name="goodsName" value="${as.goodsName}"/>
				    	<input type="hidden" name="result" value="${as.result}"/>
				    	<input type="hidden" name="username" value="${order.USERNAME}"/>
				    	
				    	<input type="submit" class="ultlon_gather ultlon_content_body_btn" style="background:red;" id="ultlon_btn_su" value="确定"/>
				    	<div class="ultlon_gather ultlon_content_body_btn" id="ultlon_btn_cl">取消</div>
			    	</form>
			    </div>
			</div>
			
		</body>

		</html>
