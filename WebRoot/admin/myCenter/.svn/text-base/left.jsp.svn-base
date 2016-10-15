<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/>
 <c:set var="guanbi" value="20160203200000"></c:set>
<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="d" />

<div class="wodz_left">
    <div class="wodz_left_top"><a href="/myCenter/index.html">我的账户</a></div>
    <div class="wodz_left_txt">
      <dl> 
        <dt>订单管理</dt>
        <dd><a class="selected" href="/order/orders.html">我的订单</a></dd>
        <dd><a href="cart/main.html">我的购物车</a></dd>
        <!-- <dd><a href="collect/myCollects.html">我的收藏/关注</a></dd> -->
        <dd><a href="ultlon/agterSale.html">我的售后</a></dd>
        <dt>账户管理</dt>
        <dd><a href="member/toMyAccount.html">账户信息</a></dd>
        <dd><a href="member/toAccountSecurity.html">账户安全</a></dd> 
      <!--   <dd><a href="evaluate/myEvaluates.html">评价管理</a></dd> -->
<!--         <dd><a>我的钱包</a></dd> -->
        <!-- <dd><a href="/admin/myCenter/shdcl.jsp">退换申请</a></dd>
         <dd><a href="wx/add.html">维修申请</a></dd> -->
        <!-- <dt id="wodz_left_txt"><a href="javascript:;">退出登录</a></dt> -->
        <!-- <dt>我的钱包</dt>
        <dd><a>我的余额</a></dd>
        <dd><a>交易记录</a></dd> -->
      </dl> 
      
      <%-- <c:if test="${sessionInfo.loginName == 'zhangsan'}">
      	<!--  --><dt><a href="qb/gohome.html">我的钱包</a></dt>
      </c:if>
      <c:if test="${sessionInfo.loginName == 'lisi'}">
      	<!--  --><dt><a href="qb/gohome.html">我的钱包</a></dt>
      </c:if> --%>
     <%--  <c:set var="areas" value="2184,2185,2186,2187,2188,2189,2190,2191,2192,2193,3275" />
       <c:if test="${fn:indexOf(areas,sessionInfo.area) > -1}"> 
      	     <dt><!-- <a href="/qb.html">我的钱包</a> --><a href="qb/gohome.html">我的钱包</a></dt> 
      </c:if> --%>
      
       <dt><a href="qb/gohome.html">我的钱包</a></dt> 
      
    </div> 
</div>
  

<script type="text/javascript">
	var thisUrl = window.location.pathname;
	console.info("当前地址栏地址："+thisUrl+" a数量："+$(".wodz_left a").length);
	$(".wodz_left a").each(function(){
		console.info("遍历所有a标签："+$(this).attr("href"));
		if($(this).attr("href")==thisUrl){
			$(this).addClass("selected"); 
		}else{ 
			$(this).removeClass("selected");
		}
	}); 
	//console.info("遍历完毕");
</script>
 