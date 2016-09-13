<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
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
			    <td width="103" align="center"><img src="images/tjsq.jpg" width="53" height="60" alt=""></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""></td>
			    <td width="103" align="center"><img src="images/wenjj.jpg" width="55" height="60" alt=""></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""></td>
			    <td width="103" align="center"><img src="images/huojian.jpg" width="55" height="60" alt=""></td>
			    <td width="103" align="center"><img src="images/jiantou.jpg" width="30" height="20" alt=""></td>
			    <td width="103" align="center"><img src="images/jcg.jpg" width="58" height="60" alt=""></td>
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
	  
	  <div class="tijsq_main_03">
	      <div class="tijsq_main_06">
	      	订单：${orderNum} <%-- <%=request.getParameter("orderNum") %> --%><br>
	      	物流公司名：申通快递<br>
	                      物流单号：<h4 id="wuliudanhao"></h4>
	        <div id="express_context"></div>
	                      
	        <script type="text/javascript">
		        $(function(){ 
		        	var express = ${express};
		        	$("#wuliudanhao").html(express.nu);
		        	var data = express.data;
		        	//console.info(data);
		        	if(data){
			        	$.each(data,function(i,d){
			        		//console.info($(this));
			        		//console.info(d);
			        		 $("#express_context").append(d.time+" : "+d.context+"<br>");
			        	});
		        	}else{
		        		if(express.status==2){
		        			$("#express_context").append("快递公司网络异常，请稍后查询");
		        		};
		        	};
		        	//console.info(express);
		        });
	        </script>
	      </div>
	  </div>
	</div>
	<!--======================bottom开始============================-->
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
