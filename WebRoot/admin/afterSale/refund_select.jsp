<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>售后选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	
	
	
	<style type="text/css">
		.xq_mian{text-align: center; height: 400px;border:1px #e4e4e4 solid; margin-top:20px;margin-bottom:20px;}
		.refund_select_box .select_box{ color: #FFF; line-height: 100px;cursor: pointer; font-size: 18px;background-color:#fc3637;  width: 200px;height: 100px; float:left; margin:30px; margin-top:120px;}
		.refund_select_box a:hover .select_box{background-color:#ff1314;}
	</style>
	
	<script type="text/javascript">
		//取消订单
		function concelOrder(action){
			$("#refund_select_form").attr("action",action);
		    document.getElementById("refund_select_form").submit();
		}
	</script>
	
	
  </head>
  
  <body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	<div class="xq_mian_top" style="margin-left:auto; margin-right:auto;padding-top: 15px;width: 1190px;">
		<span><a href="javascript:history.go(-1);">返回</a></span>
		当前位置&gt;&gt;<h5>选择售后</h5>
	</div>
	<div class="xq_mian"> 
		
		<div class="refund_select_box">
			<form id="refund_select_form" action="" method="post">
				<input type="hidden" name="order.id" value="${order.id}" />
				<input type="hidden" name="order.itemsId" value="${order.itemsId}" />
				<input type="hidden" name="orderItems.id" value="${order.itemsId}" />
				<c:if test="${showSelect==3}">
					<a href="javascript:concelOrder('th/add.html');"><div class="select_box">退货退款</div></a>
				</c:if>
				<c:if test="${showSelect == 2 or showSelect==3}">
					<a href="javascript:concelOrder('th/add.html');"><div class="select_box">换货</div></a>
				</c:if>
				<a href="javascript:concelOrder('wx/add.html');"><div class="select_box">维修</div></a>
			</form>
		</div>
	</div>
	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
   
  <script type="text/javascript">
		$(function(){
			var num = $(".select_box").length;
			var left = (1190-200*num-60*num)/2;
			console.info(left);
			$(".refund_select_box").css("margin-left",left);
		});
	</script>
  
</html>
