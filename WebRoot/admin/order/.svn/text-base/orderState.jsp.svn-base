<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>下单失败</title>


<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<!--======================详情图片开始============================-->
	<div class="xq_mian">
		<div class="xq_mian_top">
			当前位置&gt;&gt;下单状态
		</div>
		<div class="zfcg_01">
			<div class="zfcg_02">
				<c:if test="${json.success == false}">
					<img src="images/kulian.jpg" width="58" height="54" align="top"/>
				</c:if>
				${json.msg }
			</div>
			
			<div class="zfcg_04">
				<a href="cart/main.html">重新下单</a>
			</div>
		</div>
	</div>
	<!--======================bottom开始============================-->
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
