<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    
    <title>物流信息</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" >
	
		function submit(){
			document.getElementById("refund_sales_kd_form").submit();
		}
		$(function(){
			$("#wl_bianhao").change(function(){
				console.info($(this).val());
				var value = $(this).val()+"-"+$("#wl_danhao").val();
				$("#qhForm_express_number").val(value);
			});
			
			$("#wl_danhao").keyup(function(){
				var value = $("#wl_bianhao").val()+"-"+$(this).val();
				$("#qhForm_express_number").val(value);
			});
		});
	</script>
</head>

<body>
<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	
	<div class="xq_mian">
	  <div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>物流信息</h5></div>
	  
	  <div class="tijsq_main_01">
	  	<table border="0" cellspacing="0" cellpadding="0">
		   <tbody>
			  <tr>
			    <td width="103" align="center"><img src="images/tjsq.jpg" width="53" height="60" alt=""/></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""/></td>
			    <td width="103" align="center"><img src="images/wenjj.jpg" width="55" height="60" alt=""/></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""/></td>
			    <td width="103" align="center"><img src="images/huojian.jpg" width="55" height="60" alt=""/></td>
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
		   </tbody>
		</table> 
	  </div>
	  
	    
	    <div class="tijsq_main_02">
	      <div class="tijsq_main_04">订单：${order.orderNum} ${msg.obj.items[0].name}  </div>
	      <div class="tijsq_main_05"><h4>提醒：</h4>
	      货物请邮至 ：<span> 山东省济南市天桥区凤凰山路3号电商产业园2楼A区 三际数码 </span>      收件人 ：<span> 苏西  </span>     电话 ： <span>0531-67860013</span>   邮编 ： <span>250000</span></div>
	    </div>
	    <div class="tijsq_main_02">
	      <div class="tijsq_main_05_let">物流公司名：</div>
	      <div class="tijsq_main_05_ret">
	         <select id="wl_bianhao">
	         	<c:if test="${!empty dlyCorps}">
	         		<c:forEach var="d" items="${dlyCorps}"> 
	         			<option value ="${d.type}">${d.name}</option>
	         		</c:forEach>
	         	</c:if>
	         </select>
	      </div>
	      <div class="tijsq_main_05_ret">
	        <h4>*请选择</h4></div>
	      <div class="clear"></div>
	    </div> 
	    <div class="tijsq_main_03">
	      <div class="tijsq_main_05_let">物流单号：</div>
	      <div class="tijsq_main_05_ret">
	        <input type="text" id="wl_danhao" />
	      </div>
	      <!-- <div class="tijsq_main_05_ret"> 
	        <h4>&nbsp;</h4>
	        <h4 style="display:none">*填写错误</h4>
	      </div> --> 
	      <div class="clear"></div>
	    </div>
	    
	    <div class="tijsq_main_dibu">
	    	<form id="refund_sales_kd_form" action="th/editQh.html" method="post">
	    		<input type="hidden" name="order.id" value="${order.id}" />
		    	<input type="hidden" name="id" value="${msg.obj.id }" /><!-- 退货记录Id -->
		    	<input type="hidden" name="qhForm.id" value="${msg.obj.qhForm.id }" /><!-- 取货记录id -->
		    	<input type="hidden" id="qhForm_express_number" name="qhForm.expressNumber" value="${msg.obj.qhForm.id }" />
			    <div class="tijsq_main_dibu_nav"><a  href="javascript:submit();">确认提交</a></div>
		    </form>
		</div>
	  </div>
	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
