<%-- <%@page import="com.sanji.mall.common.util.ResourceUtil"%> --%>
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
<meta charset="utf-8">
	<base href="<%=basePath%>" />
</head>
<title>专题页</title>

<style>
*{
margin:0px;
padding:0px;
border:0px;
}
 body{
background-color:rgb(205,28,74);
} 

.rowone{
width:100%;
heigth:auto;
display:block;
}
.rowtwo{
width:100%;
}
.rowthree{
width:100%;
}
.rowfour{
width:100%;
}
.rowtwo>.one{
width:30.5%;
heigth:492px;	
float:left;
}
.rowtwo>.two{
 width:45%;
 float:left;
}
.rowtwo>.three{
width:24%;
heigth:auto;
float:left;
}
.rowtwo>.four{
float:left;
margin-top:0px;
}
.rowthree>.two{
margin-left:25%;
width:25.5%;
}
.rowthree>.three{
width:26%;
}
.rowfour>.one{
margin-left:25%;
width:25%;
}
.rowfour>.two{
width:26%;
}
.rowfive>.one{
width:100%;
}
</style>
<body>
<div class="container-fuild">
	<div class="rowone">
		<img style="width:100%" src="images/zhuantiyetwo/zhuantiye1.jpg"/>
	</div>
	<div class="rowtwo">
		<img class="one" src="images/zhuantiyetwo/zhuantiye2.jpg"/>
		<img class="two" src="images/zhuantiyetwo/zhuantiye3.jpg" onclick="window.open('goods/detail/849.html')"/>
		<img class="three" src="images/zhuantiyetwo/zhuantiye4.jpg" onclick="window.open('goods/detail/854.html')"/>
	</div>
	<div class="rowthree">
			<img class="two" src="images/zhuantiyetwo/zhuantiye6.jpg" onclick="window.open('goods/detail/854.html')" />
			<img class="three" src="images/zhuantiyetwo/zhuantiye7.jpg" onclick="window.open('goods/detail/818.html')"/>
	</div>
	 <div class="rowfour">
		<img class="one" src="images/zhuantiyetwo/zhuantiye9.jpg"  onclick="window.open('goods/detail/544.html')"/>
		<img class="two" src="images/zhuantiyetwo/zhuantiye10.jpg"  onclick="window.open('goods/detail/554.html')"/>
	</div> 
	<div class="rowfive">
		<a href="#"><img class="one" src="images/zhuantiyetwo/zhuantiye12.jpg"/></a>
	</div>
	</div>
</body>
</html>