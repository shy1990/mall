<%@page import="com.sanji.mall.common.util.ResourceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>" />
<title>移动定制机专题页</title>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script>
	$("#dingshang").click(function() {
		$('body,html').animate({
			scrollTop : 0
		}, 1000);
		return false;
	});
</script>

<style>
#d1 {
	text-align: center;
	padding: 0px;
	margin: 0px;
}
</style>
</head>

<body>
	<div id="d1" class="container-fluid">
		<div>
			<img src="images/zhuanti01.jpg" />
		</div>
		<div>
			<img
				src="images/zhuanti02.jpg" />
		</div>
		<div>
				<img src="images/zhuanti03.jpg" alt="" width="1920" height="628" border="0" usemap="#Map">
				<map name="Map">
				  <area shape="rect" coords="486,29,777,565" href="goods/detail/844.html" target="_blank">
				  <area shape="rect" coords="819,31,1106,565" href="goods/detail/537.html" target="_blank">
				  <area shape="rect" coords="1155,30,1432,564" href="goods/detail/901.html" target="_blank">
				</map>
		</div>
		
		<div>
			<a id="dingshang" href="#" target="_blank"><img src="images/zhuanti04.jpg" /> </a>
		</div>
	</div>
</body>
</html>