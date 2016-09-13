<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>退款退货申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/jquery-validation/1.11.1/validate.css" rel="stylesheet"type="text/css" />
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript"	src="js/jquery-validation/1.11.1/jquery.validate.min.js"></script>
	<script type="text/javascript"	src="js/jquery-validation/1.11.1/messages_bs_zh.js"></script>
	<script type="text/javascript">
		function submit(){
			//$("#th_form").validate();
			document.getElementById("refund_sales_form").submit();
		}
		var thPrice = ${thPrice};
		$(function(){
			$("#thPrice").keyup(function(){
				var thisPrice = parseInt($(this).val());
				console.info("当前输入的价格："+thisPrice);
				if(thisPrice>thPrice){
					$(this).val(thPrice);
				}
			});
		});
	</script>  
</head>

<body>
<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	
	<div class="xq_mian">
	  <div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>退款退货申请</h5></div>
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
			    <td width="103" align="center">返修退货成功</td>
			  </tr>
			</table>
		</div>
		<form id="refund_sales_form" action="th/save.html" method="post">
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.itemsId" value="${order.itemsId}" />
			<div class="tijsq_main_02">
			    <div class="tijsq_main_05_let">退款金额:</div>
			    <div class="tijsq_main_05_ret">
			       <input id="thPrice" type="text" name="money" value="" /><span>最多可退款：${thPrice}元</span>
			    </div>
			    <div class="tijsq_main_05_ret">
			      <h4></h4></div>
			    <div class="clear"></div>
			</div>
		    <div class="tijsq_main_03">
		      <div class="tijsq_main_05_let">返修/退货原因：</div>
		      <div class="tijsq_main_05_ret">
		        <textarea id="textarea" name="remark" cols="45" rows="5"></textarea>
		      </div>
		      <div class="clear"></div>
		    </div>
	    </form>
	  </div>
	  <div class="tijsq_main_dibu">
	    <div  class="tijsq_main_dibu_nav"><a href="javascript:submit();">确认提交</a></div>
	  </div>
	</div>
	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
	
  </body>
</html>
