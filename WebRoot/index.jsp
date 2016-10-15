<%@page import="com.sanji.mall.common.util.ResourceUtil"%>
<%@page import="com.sanji.mall.pojo.SessionInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- <%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit" />
<title>三际数码三际手机采购网</title>
<link href="<%=basePath%>css/index/css.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/login/base64.js"></script>
<script type="text/javascript" src="js/login/login.js"></script>
<script type='text/javascript' src='js/login/jquery.placeholder.js'></script>

</head>

<body onkeydown="keydown(event)" style="background-color: #fff;">

	<c:if test="${sessionInfo !=null}">
		<c:redirect url="member/home.html" />
	</c:if>
	<form action="member/index.html" method="post" name="form1">
		<!--======================登陆top开始============================-->
		<div class="dl_top">
			<div class="dl_top_box">
				<div class="dl_top_box_let">
					<a href="/"><img src="images/register_logo.jpg" alt="logo" /></a>
				</div>
				<div class="dl_top_box_ret">
					<ul>
					    <!-- <li><a href="groupIntroduction.html">公司介绍 </a></li> -->
						<li><a href="groupIntroduction.html">帮助中心 </a></li>
						<li><!-- <a href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a> -->
						 <a target="_blank" href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4009371688&aty=0&a=0&curl=&ty=1">联系客服</a>
						</li>
						<li style="float: right; width: 100px;">
						<a><img src="images/index/two-dimension-code.png" alt="" />
						</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="dl_top_box_mid">
					客服：<span>400-937-1688</span>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!--======================登陆框开始============================-->
		<div class="dl_banner">
			<div class="dl_banner_box">
				<div class="dl_banner_01">
					<div class="dl_banner_02">
						<div class="dl_dlk">登陆</div>
						<c:if test="${!empty msg}">
							<div style="color: red; margin-left: 40px;">${msg}</div>
						</c:if>
						<div class="dl_dlk_01">
							<input type="text" id="username" name="username" value="店铺名/手机号"
								onMouseOver="this.focus()"
								onMouseOut="if(this.value=='')this.value='店铺名/手机号';"
								onFocus="this.select()"
								onClick="if(this.value=='店铺名/手机号')this.value=''"
								class="main_logo_input_zi" />
						</div>
						<div id="existUs" style="color: red; margin-left: 40px;"></div>
						<div class="dl_dlk_03" id="box">
							<input type="text" id="password" name="password" value="密码"
								class="password main_logo_input_zi" />
						</div>
						<div id="existPs" style="color: red; margin-left: 40px;"></div>
						<div class="dl_dlk_02">
							<label for="checkbox"></label> <span id="click"><input
								type="checkbox" name="checkbox" align="left"
								onclick="javascript:showps();" />显示密码</span>
							<div class="clear"></div>
						</div>

						<div class="dl_dlk_04">
							<a href="javascript:toSubmit();">登陆</a>
						</div>
						<div class="dl_dlk_05">
							<ul>
								<li><input type="checkbox" id="checkbox" />记住密码</li>
								<li id="dl_dlk_05"><a href="member/toGetPhoneCode.html">忘记密码</a>
								</li>
								<li id="dl_dlk_05"><a href="member/regMemberUI.html">免费注册</a>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!--======================bottom开始============================-->
		<div>
			<jsp:include page="/admin/common/footer.jsp"></jsp:include>
		</div>
	</form>
	<div class="dl_ts" id="showFa" style="display: none;">
		<div class="dl_ts_01">是否将网站设为主页?</div>
		<div class="dl_ts_02">
			<a href="javascript:void(0)" onclick="Se	tHome(this,window.location)"><div
					class="dl_ts_02_let">是</div> </a> <a href="javascript:toSubmit()"><div
					class="dl_ts_02_ret">否</div> </a>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>
