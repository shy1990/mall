<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>已评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
    <jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div style="text-algin:center; width:100%;font-size: 38px; padding-left: 30%;min-height: 500px;color: #666;">
			<div style="margin-top:250px;"><img src="images/xiaolian.jpg" width="58" height="54" align="top"/>已评论！！！</div>
		</div>
	</div>
	<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
