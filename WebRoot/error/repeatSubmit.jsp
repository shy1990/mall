<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>404页面</title>

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
	<!-- 左边快捷按钮 -->
	<jsp:include page="../admin/common/follow.jsp"></jsp:include>
	<jsp:include page="../admin/common/head.jsp"></jsp:include>
	<!--======================详情图片开始============================-->
	<div class="xq_mian">
		<div class="cuowu">
			<span>杯具了！你找的页面被外星人带走了</span>~<br /> 别着急，点<a href="/">这里</a>找出路
		</div>
	</div>
	<jsp:include page="../admin/common/footer.jsp"></jsp:include>
</body>
</html>