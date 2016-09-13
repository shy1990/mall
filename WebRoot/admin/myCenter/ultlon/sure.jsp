<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <meta name="renderer" content="webkit">
    <base href="<%=basePath%>">

			<title>售后申请</title> 

			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			<link href="css/css.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
			<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
			<script type="text/javascript">
			
			</script>
			<style type="text/css">
				.ddgl_main {
					  min-height: 500px;
					}
			</style>
		</head>

		<body>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
			<div class="xq_mian">
				<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
				<div class="wodz_ret">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">售后信息</div>
						<div class="clear"></div>
					</div>
					<div class="ddgl_main">
						<div style="color:red;font-size:30px;text-align:center;  margin-top: 8%;">
							<!-- 您的售后申请已火速提交并通知当地的业务人员，请耐心等候.... -->
							${msg }
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
		</body>

		</html>
