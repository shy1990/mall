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
				$(function() {
				    function conPosition() {
				        var oBackground = document.getElementById("background");
				        var dw = $(document).width();
				        var dh = $(document).height();
				        oBackground.style.width = dw+'px';
				        oBackground.style.height = dh+'px';
				        var oContent = document.getElementById("content");
				        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				        var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
				        var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 3) + scrollTop;
				        oContent.style.left = l + 'px';
				        oContent.style.top = t + 'px';
				    }
				    $("#btn").click(function() {
				        $("#background, #content").show();
				        conPosition();
				    });
				    
				    $("#ultlon_btn_cl").click(function() {$("#background, #content").hide();});
				    $("#background").click(function() {$("#background, #content").hide();});
				    //点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
				    $(window).resize(function() {conPosition();});
				    //$(window).scroll(function() {conPosition();});
				    //开启内容跟随垂直滚动条（水平滚动条需要处理的问题更多，暂时没有考虑）
				});
			</script>
			<style type="text/css">
				.ddgl_main {
					  min-height: 500px;
					  margin-top:60px;
				}
				.ultlon_as_select{
					  width: 240px;
					  height: 50px;
					  cursor: pointer;
					  outline: none;
					  text-align: center;
					  line-height: 50px;
					  float: left;
					  background: #ccc;
					  font-size: 18px;
					  margin-left: 30px;
				}
				.ultlon_as_select_frist{
					  background: red;
				}
				#background {position:absolute; z-index:998; top:0px; left:0px; background:rgb(50,50,50);background:rgba(0,0,0,0.5); display:none;}
				#content {position:absolute; width:380px; z-index:999; padding:10px; background:#fff; border-radius:5px; display:none;}
				.ultlon_content_body{text-align: center;}	 
				.ultlon_gather{margin-top:20px;font-size: 22px;}
				.ultlon_content_body_imei input{  width: 250px;height: 35px;}
				.ultlon_content_body_btn{border: 1px solid #ccc;
					  background: #F9F9F9;
					  width: 250px;
					  margin: 20px auto;
					  height: 30px;
					  line-height: 30px;
					  cursor: pointer;
					  font-size: 18px;
				}
				#ultlon_btn_cl{margin-top:0px;font-size: 16px;}
				.ultlon_as{margin:auto;color:white;width: 600px;}
			</style>
		</head>

		<body>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
			<div class="xq_mian">
				<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
				<div class="wodz_ret">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">我的售后</div>
						<div class="clear"></div> 
					</div>
					<div class="ddgl_main">
						<div class="ultlon_as">
							<div id="btn" class="ultlon_as_select ultlon_as_select_frist">发起售后</div>
							<a href="/ultlon/list/1.html"><div class="ultlon_as_select">查看历史</div></a>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>

			<div id="background"></div>
			<div id="content">
			    <div class="ultlon_content_body">
			    	<form action="ultlon/info.html" method="post">
				    	<dir class="ultlon_gather ultlon_content_body_title">请输入你想申请的手机串码</dir>
				    	<dir class="ultlon_gather ultlon_content_body_imei"><input type="text" name="imei"/> </dir>
				    	<input type="submit" class="ultlon_gather ultlon_content_body_btn" id="ultlon_btn_su" value="完成"/>
				    	<div class="ultlon_gather ultlon_content_body_btn" id="ultlon_btn_cl">取消</div>
			    	</form>
			    </div>
			</div>
			
		</body>

		</html>
