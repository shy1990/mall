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
		$("#wx_form").validate();
		
		
		
	});
	
	function submit(){
		if(!$("#remark").val()){
			alert('备注不能为空。');
			$("#remark").focus();
		}else{
			$("#wx_form").submit();
		}
	}
</script>
</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
<div class="xq_mian">
	  <div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>维修申请</h5></div>
	  <div class="tijsq_main">
	    <div class="tijsq_main_01">
		    <table border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td width="103" align="center"><img src="images/tjsq.jpg" width="53" height="60" alt=""/></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""/></td>
			    <td width="103" align="center"><img src="images/wenjj_01.jpg" width="55" height="60" alt=""/></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""/></td>
			    <td width="103" align="center"><img src="images/huojian_01.jpg" width="55" height="60" alt=""/></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""/></td>
			    <td width="103" align="center"><img src="images/jcg.jpg" width="58" height="60" alt=""/></td>
			  </tr>
			  <tr>
			    <td width="103" align="center">提交申请</td>
			    <td width="103">&nbsp;</td>
			    <td width="103" align="center">审核待处理</td>
			    <td width="103">&nbsp;</td>
			    <td width="103" align="center">寄还货物</td>
			    <td width="103">&nbsp;</td>
			    <td width="103" align="center">维修成功</td>
			  </tr>
			</table>
		</div>
		<s:form id="wx_form" theme="simple" action="wx/save.html">
			<div class="tijsq_main_02">
			    <div class="tijsq_main_05_let">手机：</div>
			    <div class="tijsq_main_05_ret">
			    	<div><input type="text" name="items.name"/><input type="button" value="添加串号" style="width:70px; margin-left:30px;cursor: pointer;" /></div>
			       <%-- <s:textarea name="items[0].name" /> --%>
			    </div>
			    <div class="tijsq_main_05_ret">
			      <h4></h4></div>
			    <div class="clear"></div>
			</div>
		    <div class="tijsq_main_03">
		      <div class="tijsq_main_05_let">备注：</div>
		      <div class="tijsq_main_05_ret">
		        <s:textfield id="remark" class="input required"   name="items[0].remark" />
		      </div>
		      <div class="clear"></div>
		    </div>
	    </s:form>
	  </div>
	  <div class="tijsq_main_dibu">
	    <div  class="tijsq_main_dibu_nav"><a href="javascript:submit();">确认提交</a></div>
	  </div>
	</div>
		<!---------第二块采购说明---------->
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
