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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myCenter/accountSecurity/bindMobile.js"></script>

</head>

<body onkeydown="keydown(event)">
<form action="member/modifyMobile.html" method="post">
<!--======================top开始============================-->
<jsp:include page="/admin/common/follow.jsp"></jsp:include>
<jsp:include page="/admin/common/head.jsp"></jsp:include>
<!--======================详情图片开始============================-->
<div class="xq_mian">
  <jsp:include page="/admin/myCenter/left.jsp"></jsp:include>
  <div class="wodz_ret">
        <div class="wodz_ret_01_let_top">
           <div class="wodz_ret_01_let_top_let">账户安全</div>
           <div class="clear"></div>
     </div>
      <div class="zhaq_main">
        <div class="xgmm_01">修改手机</div>
         <div class="xgmm_02">
           <div class="xgmm_03">
             <div class="xgmm_03_let">手机号：</div>
             <div class="xgmm_03_mid"><input type="text" id="mobile" name="mobile" onkeyup="checkMobile()"/></div>
             <div class="xgmm_03_ret" id="existMobile"></div>
             <div class="clear"> </div>
           </div>
           <div class="xgmm_04"><a href="javascript:toSubmit()">确认提交</a></div>
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
