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
    
    <title>提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
  </head>
  
  <body>
  	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	
	<!--======================详情图片开始============================-->
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="javascript:history.go(-1);">返回</a></span>
			当前位置&gt;&gt;<h5>售后申请</h5>
		</div>
		<div class="zfcg_01">
			<div class="zfcg_02">
				<c:if test="${msg.success}">
					<img src="images/xiaolian.jpg" width="58" height="54" align="top"/>
				</c:if>
				<c:if test="${msg.success == false}">
					<img src="images/kulian.jpg" width="58" height="54" align="top"/>
				</c:if>
				{msg.msg}
			</div>
			<div class="zfcg_04">
				<a href="/order/orders.html">返回订单列表</a>
			</div>
		</div>
	</div>
   	 <!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
