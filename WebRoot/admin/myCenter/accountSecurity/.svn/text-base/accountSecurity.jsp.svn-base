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
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />

</head>
<body>
<form>
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
        <div class="zhaq_main_01">修改密码</div>
         <div class="zhaq_main_03">
         <div class="zhaq_main_02">
         <c:if test="${requestScope.phone == null}">
         <a href="member/toBindMobileUI/.html">绑定</a><span>绑定手机：</span>为保障账户和资金安全，请尽快绑定手机号<br />
         </c:if>
          <c:if test="${requestScope.phone != null}">
         <a href="member/toExistMobileCode.html">修改</a><span>绑定手机：</span>已绑定 ：${requestScope.phone}<br />
         </c:if>
       <a href="member/toMobileCodeUI.html">修改</a> <span>登陆密码：</span>如需修改密码，请先绑定手机号码</div></div>
       <div class="zhaq_main_04" style="display: none;">
         <div class="zhaq_main_04_let">帮助：</div>
         <div class="zhaq_main_04_ret"><a href="#">如何修改密码？</a><br />
           <a href="#">如何绑定手机号？</a><br />
          <a href="#">如何修改绑定的手机号？</a></div>
         <div class="clear"></div>
       </div>
      </div>
     </div>
  </div>
  <div class="clear"></div>

<!--======================bottom开始============================-->
<div id="footer">
		<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>

</form>
</body>
</html>
