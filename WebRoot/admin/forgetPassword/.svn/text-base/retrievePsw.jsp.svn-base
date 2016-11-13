<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/forgetPassword/fixPassword.js"></script>
</head>

<body onkeydown="keydown(event)">
<form action="member/retrievePsw.html" method="post">
<!--======================top开始============================-->
<jsp:include page="/admin/common/follow.jsp"></jsp:include>
<jsp:include page="/admin/common/head.jsp"></jsp:include>
<!--======================详情图片开始============================-->
<div class="xq_mian">
  <div class="wjmm_ret">
        <div class="wodz_ret_01_let_top">
           <div class="wodz_ret_01_let_top_let">账户安全</div>
           <div class="clear"></div>
     </div>
      <div class="zhaq_main">
        <div class="xgmm_01">忘记密码</div>
         <div class="xgmm_02">
           <div class="xgmm_03">
             <div class="wjmm_03_let">新密码：</div><input type="hidden" id="tel" name="mobile" value="${mobile}"/>
             <div class="xgmm_03_mid"><input type="password" id="newpassword" name="newpassword" onkeyup="checNewPsw()"/></div>
             <div class="xgmm_03_ret" id="existNewPsw"></div>
             <div class="clear"> </div>
           </div>
           <div class="xgmm_03">
             <div class="wjmm_03_let">确认密码：</div>
             <div class="xgmm_03_mid"><input type="password" id="repassword" name="repassword" onkeyup="checkRePsw()"/></div>
             <div class="xgmm_03_ret_01" id="existRePsw"></div>
             <div class="clear"> </div>
           </div>
           <div class="wjmm_04"><a href="javascript:toSubmit()">确认提交</a></div>
      </div>
     </div>
  </div>
  <div class="clear"></div>
</div>
<!--======================bottom开始============================-->
<div id="footer">
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>
</form>
</body>
</html>
