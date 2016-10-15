<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="js/jquery-validation/1.11.1/validate.css" rel="stylesheet"
	type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript"
	src="js/jquery-validation/1.11.1/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="js/jquery-validation/1.11.1/messages_bs_zh.js"></script>
<script type="text/javascript">
	$(function() {
		$("#th_form").validate();
	})
</script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="#">返回</a></span>当前位置&gt;&gt;<a href="#">退货退款</a>&gt;&gt;
			<h5>选购专用配件</h5>
		</div>
		<!---------已采购商品信息---------->
		退货了亲 <br/>
		订单号:${order.orderNum }
		<s:form id="th_form" action="th/save.html">
			<s:hidden name="order.id" value="%{order.id}" />
			<s:textfield label="退款金额" name="money" cssClass="{number:true}" />
			<s:textfield label="原因" name="remark" cssClass="{required:true}" />
			<s:submit value="提交"></s:submit>
		</s:form>
		<!---------第二块采购说明---------->
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
