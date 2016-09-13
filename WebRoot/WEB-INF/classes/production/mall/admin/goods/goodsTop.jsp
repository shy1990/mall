
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三级在线商城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
    <form>
  <!--======================top开始============================-->
  <jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
<!--======================详情图片开始============================-->
<div class="xq_mian">
  <div class="xq_mian_top"><span><a href="member/home.html">返回</a></span>当前位置&gt;&gt;<a href="goods/paihang.html"><h5>热卖排行榜</h5></a></div>
    <div class="phb_main">
      <div class="phb_main_01">
        <div class="phb_main_01_top">热门手机排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="34">排名</td>
    <td width="118">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
   <s:iterator value="#request.phoneTopList" var="phoneTop" status="status">
    <li>
    
    <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26">
    <div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div>
    </td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#phoneTop.getName()"/> </a></td>
    <td width="215"><a href="javascript:void(0)">￥<s:property value="#phoneTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}% </span></td>
  </tr>
</table>
  </li>
   </s:iterator>
  </ul>
</div>
      </div>
      <div class="phb_main_01">
        <div class="phb_main_01_top_01">热门品牌排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="34">排名</td>
    <td width="118">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
   <s:iterator value="#request.brandTopList" var="brandTop" status="status">
    <li>
   
    <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26"><div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div></td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#brandTop.getName()"/></a></td>
    <td width="215"><a href="javascript:void(0)">￥<s:property value="#brandTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}%</span></td>
  </tr>
</table>

</li>
</s:iterator>
  </ul>
</div>
      </div>
      <div class="phb_main_02">
        <div class="phb_main_01_top_02">热门4G手机排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="33">排名</td>
    <td width="119">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
    <s:iterator value="#request.fourGTopList" var="fourGTop" status="status">
    <li>
  
    <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26"><div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div></td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#fourGTop.getName()"/></a></td>
    <td width="215"><a href="javascript:void(0)">￥<s:property value="#fourGTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}%</span></td>
  </tr>
</table>

</li>
</s:iterator>
  </ul>
</div>
      </div>
      <div class="clear"></div>
    </div>
    

<!-- 
<div class="phb_main_03">
      <div class="phb_main_01">
        <div class="phb_main_01_top_03">热门联通3G手机排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="43">排名</td>
    <td width="109">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
  <s:iterator value="#request.ltThreeGTopList" var="ltThreeGTop" status="status">
    <li><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26"><div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div></td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#ltThreeGTop.getName()"/> </a></td>
    <td width="215"><a href="javascript:void(0)">￥<s:property value="#ltThreeGTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}%</span></td>
  </tr>
</table>
</li>
</s:iterator>
  </ul>
</div>
      </div>
      <div class="phb_main_01">
        <div class="phb_main_01_top_04">热门移动3G手机排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="43">排名</td>
    <td width="109">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
    <s:iterator value="#request.ydThreeGTopList" var="ydThreeGTop" status="status">
    <li><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26"><div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div></td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#ltThreeGTop.getName()"/></a></td>
    <td width="215"><a href="javascript:void(0)">￥<s:property value="#ltThreeGTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}%</span></td>
  </tr>
</table>
</li></s:iterator>

  </ul>
</div>
      </div>
      <div class="phb_main_02">
        <div class="phb_main_01_top_01">热门电信3G手机排行榜</div>
        <div class="phb_main_01_txt"><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="43">排名</td>
    <td width="109">型号</td>
    <td width="141">价格</td>
    <td width="65">热度</td>
  </tr>
</table>
</div>
<div class="phb_main_01_bot">
  <ul>
   <s:iterator value="#request.dxThreeGTopList" var="dxThreeGTop" status="status">
    <li><table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="26"><div class="phb_main_01_bot_0<s:property value='#status.count'/>"><s:property value='#status.count'/></div></td>
    <td width="198"><a href="goods/detail/<s:property value="#phoneTop.getGoodsNum()"/>.html"><s:property value="#dxThreeGTop.getName()"/> </a></td>
    <td width="215"><a href="#">￥<s:property value="#dxThreeGTop.getPrice()"/></a></td>
    <td width="94"><span><img src="images/shangjian.jpg" width="9" height="10" alt=""/>${(10-status.index)*10}%</span></td>
  </tr>
</table>
</li>
</s:iterator>
  </ul>
</div>
      </div>


 
      <div class="clear"></div>
    </div>
</div>--></div>
<!--======================bottom开始============================-->
<div>
 <jsp:include page="../common/footer.jsp"></jsp:include>
</div>
</form>
  </body>
</html>
