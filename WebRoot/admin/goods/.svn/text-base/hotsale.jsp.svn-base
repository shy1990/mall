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
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<title>热销产品</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>

<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<c:forEach var="b" items="${goodss }">
			<div class="xgzy_main_top">
				<span></span>${b.key }
			</div>
			<c:forEach var="goods" items="${b.value }">
				<div class="phone_main_03">
					<ul>
						<li title="${goods.name }"><div>
								<a target="_blank" href="goods/detail/${goods.goodsNum }.html"><img
									src="${goods.defaultImg }"
									width="228" height="228"></a>
							</div>
							<div class="phone_main_04">
								<a target="_blank" href="goods/detail/${goods.goodsNum }.html">${goods.name } </a><br>
							</div>
							<div class="phone_main_05">
								<span>库存：${goods.stock gt 0 ?"有货":"无货"}</span>￥${goods.price }
							</div></li>
					</ul>
				</div>
			</c:forEach>
			<div class="clear"></div>
		</c:forEach>
	</div>
	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</body>
</html>
