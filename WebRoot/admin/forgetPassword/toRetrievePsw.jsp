<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<base href="<%=basePath%>"/>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/forgetPassword/getMobileCode.js"></script> 
</head>
<body onkeydown="keydown(event)">
<form action="member/toRetrievePsw.html" method="post">
<!--======================top开始============================-->
<jsp:include page="/admin/common/follow.jsp"></jsp:include>
<jsp:include page="/admin/common/head.jsp"></jsp:include>
<!--======================详情图片开始============================-->
<div class="xq_mian">
  <div class="wjmm_ret" align="center">
        <div class="wodz_ret_01_let_top">
           <div class="wodz_ret_01_let_top_let">账户安全</div>
           <div class="clear"></div>
     </div>
      <div class="zhaq_main">
        <div class="xgmm_01">忘记密码</div>
         <div class="xgmm_diyi_01" >
           <div class="xgmm_03" >
             <div class="wjmm_03_let">您的手机号：</div>
             <div class="xgmm_03_mid"><input type="text" id="tel" name="toMobile" /></div>
             <div class="xgmm_diyi_01_ret" style="margin-left: 10px;" ><span id="timer" style="float:left; display: none;"><input type="button" id="btn" style="border: 0px; width:110px; height: 34px;font-size: 12px;"/></span><a id="show"  href="javascript:getvalidatedCode()">获取验证码</a></div>
             <div class="xgmm_03_ret_01" id="existTel"></div>
             <div class="clear"> </div>
           </div>
           <div class="xgmm_03">
             <div class="wjmm_03_let">输入验证码：</div>
             <div class="xgmm_03_mid"><input type="text" id="code" name="verificationCode"/></div>
             <div class="xgmm_03_ret_01" id="rexistMessage"  style="display: none"><span>*${message}</span></div>
             <div class="clear"> </div>
           </div>
           <div class="wjmm_04" style="margin-left: 40px"><a href="javascript:existCode()">确认提交</a></div>
           <div class="wjmm_diyi_02" style="margin-right: 378px">尊敬的用户，你好！若手机号码更换或丢失，请<a href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a></div>
      </div>
     </div>
  </div>
  <div class="clear"></div>
</div>
<!--======================bottom开始============================-->
<div>
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>
</form>
</body>
</html>
