<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ceshi</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>

<script type="text/javascript">

	$(function(){
	
		console.info("我累个去");
		alert("执行了console");
		
	});

	function test(obj){
		
		//console.info("我累个去");
		//alert("执行了console");
	
		console.info(obj);
		console.info($(obj));
		console.info($(obj).text());
		console.info($(obj).parent().attr("id"));
		//$(obj).parent().text("黑油");
		console.info($(obj).parent().next().text());
	}
</script>

<style type="text/css">
	.modal-backdrop {
		position: fixed;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		z-index: 1040;
		background-color: #000000;
	}
	
	.modal-backdrop, .modal-backdrop.fade.in {
		opacity: 0.8;
		filter: alpha(opacity=80);
	}
	
	.modal.fade.in {
		top: 50%;
	}
	.fade.in {
		opacity: 1;
	}
	.modal {
		position: fixed;
		top: 50%;
		left: 50%;
		z-index: 1050;
		max-height: 500px;
		overflow: auto;
		width: 560px;
		margin: -250px 0 0 -280px;
		background-color: #ffffff;
		border: 1px solid #999;
		border: 1px solid rgba(0, 0, 0, 0.3);
		-webkit-border-radius: 6px;
		-moz-border-radius: 6px;
		border-radius: 6px;
		-webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		-moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
		-webkit-background-clip: padding-box;
		-moz-background-clip: padding-box;
		background-clip: padding-box;
	}
	
	.modal-header {
		padding: 9px 15px;
		border-bottom: 1px solid #eee;
	}
	
	.modal-header .close {margin-top: 2px;}
	
	.close {
		float: right;
		font-size: 20px;
		font-weight: bold;
		line-height: 18px;
		color: #000000;
		text-shadow: 0 1px 0 #ffffff;
		opacity: 0.2;
		filter: alpha(opacity=20);
	}
	
	h3 {
		line-height: 27px;
		font-size: 18px;
	}
	
	h1, h2, h3, h4, h5, h6 {
		margin: 0;
		font-weight: bold;
		color: #333333;
		text-rendering: optimizelegibility;
	}
	.modal-body {padding: 15px;}
	
	.modal-footer {
		padding: 14px 15px 28px;
		margin-bottom: 0;
		background-color: #f5f5f5;
		border-top: 1px solid #ddd;
		-webkit-border-radius: 0 0 6px 6px;
		-moz-border-radius: 0 0 6px 6px;
		border-radius: 0 0 6px 6px;
		-webkit-box-shadow: inset 0 1px 0 #ffffff;
		-moz-box-shadow: inset 0 1px 0 #ffffff;
		box-shadow: inset 0 1px 0 #ffffff;
	}
	
	.modal-footer .btn {
		float: right;
		margin-left: 5px;
		margin-bottom: 0;
	}
	
	.btn-success {
		background-color: #5bb75b;
		background-image: -moz-linear-gradient(top, #62c462, #51a351);
		background-image: -ms-linear-gradient(top, #62c462, #51a351);
		background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#62c462), to(#51a351));
		background-image: -webkit-linear-gradient(top, #62c462, #51a351);
		background-image: -o-linear-gradient(top, #62c462, #51a351);
		background-image: linear-gradient(top, #62c462, #51a351);
		background-repeat: repeat-x;
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#62c462', endColorstr='#51a351', GradientType=0);
		border-color: #51a351 #51a351 #387038;
		border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
		filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
	}
	
	.modal-footer .btn {
		float: right;
		margin-left: 5px;
		margin-top: -6px;
	}
	
	.btn {
		display: inline-block;
		padding: 4px 10px 4px;
		margin-bottom: 0;
		font-size: 13px;
		line-height: 18px;
		color: #333333;
		text-align: center;
		text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
		vertical-align: middle;
		background-color: #f5f5f5;
		background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
		background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);
		background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
		background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
		background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
		background-image: linear-gradient(top, #ffffff, #e6e6e6);
		background-repeat: repeat-x;
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffff', endColorstr='#e6e6e6', GradientType=0);
		border-color: #e6e6e6 #e6e6e6 #bfbfbf;
		border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
		filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
		border: 1px solid #ccc;
		border-bottom-color: #bbb;
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		border-radius: 4px;
		-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
		cursor: pointer;
		filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
	}
	
</style>

  </head>
  
  <body>
  <div id="aa">
	    我累个去
	  <a href="javascript:test(this)">点击</a>
	  <a href="javascript:;" onclick="test(this)">dianji</a>
  </div>
  <div>二货</div>
  
<!--   <div class="modal-backdrop fade in"></div>
  
  <div id="example" class="modal hide fade in" style="display: block;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3>确认收货</h3>
		</div>
		<div class="modal-body">
			<h4>已经收到货请点击确认！确认收货  订单就变成交易已完成了</h4>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-success">确定</a>
			<a href="#" class="btn" data-dismiss="modal">取消</a>
		</div>
   </div> -->
  
  </body>
</html>
