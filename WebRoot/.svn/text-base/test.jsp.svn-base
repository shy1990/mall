<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.vticker-min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".roll").each(function() {
			$(this).vTicker({
				showItems : 18, //显示滚动行数 
				pause : 3000, //滚动间歇 
				speed : 500, //滚动速度 
				animation : "fade",
				mousePause : false,
				direction : "up", //滚动方向 
			});
		});
		$.ajax({
			url : 'http://localhost:8090/goods-web-client/brand.json',
			dataType : "jsonp",
			jsonp : "callback",
			success : function(data) {
				console.log(data);
			}
		});

	});
</script>
<body>
	<div class="conn">
		<table>
			<tr>
			<div id="shadow_dom"></div>
				<th style="width: 50px;">序号</th>
				<th style="width: 150px;">名称</th>
				<th style="width: 80px;">季度完成量</th>
				<th style="width: 50px;">昨日</th>
				<th style="width: 70px;">完成进度</th>
				<th style="width: 80px;">基本指标</th>
				<th style="width: 70px;">挑战指标</th>
			</tr>
			<tr>
				<td colspan="7">
					<div class="roll">
						<ul>
							<c:forEach items="${statislist}" var="s" varStatus="i">
								<c:set var="cs" value=""></c:set>
								<c:set var="index" value=""></c:set>
								<c:if test="${i.index < 9 }">
									<c:set var="index" value="0"></c:set>
								</c:if>
								<c:if test="${i.index % 2==0}">
									<c:set var="cs" value="background:#ebf6fd;"></c:set>
								</c:if>
								<li style="height:30px;width:550px;line-height:30px;${cs}">
									<div style="width: 50px; text-align: center; float: left;">${index}
										${i.count}</div>
									<div style="width: 150px; text-align: center; float: left;">${s.name}</div>
									<div style="width: 80px; text-align: center; float: left;">${s.number}</div>
									<div style="width: 50px; text-align: center; float: left;">${s.zuori}</div>
									<div style="width: 70px; text-align: center; float: left;">${s.wcjb}</div>
									<div style="width: 80px; text-align: center; float: left;">${s.jbzb}</div>
									<div style="width: 70px; text-align: center; float: left;">${s.tzzb}</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
</html>
