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
	<c:if test="${order != null }">
		<div class="zhifu">
			<div class="zhifu_01">
				<div class="zhifu_01_let">收货信息</div>
				<div class="zhifu_01_mid">
					<span>收件人：${order.shipName }<br> 电话：${order.shipTel } <br>
								邮编：${userInfo.zip }<br>
									${userInfo.pname}${userInfo.cname}${userInfo.aname}${userInfo.address}<br></span>
					您的订单号是：
					<h4>${order.orderNum }</h4>
					<!-- <a href="#">查看订单详情</a> -->
				</div>
				<div class="zhifu_01_ret">
					<h3>${order.totalCost }</h3>
					元 <a href="#">刷新</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="zhifu_02"></div>
		</div>
	</c:if>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
