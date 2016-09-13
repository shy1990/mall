<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>退货申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
  </head>
  
  <body>
  <jsp:include page="/admin/common/follow.jsp"></jsp:include>
  <jsp:include page="/admin/common/head.jsp"></jsp:include>
    <div class="xq_mian">
  <div class="xq_mian_top"><span><a href="#">返回</a></span>当前位置&gt;&gt;<a href="#">小米手机</a>&gt;&gt;<a href="#">商品详情</a>&gt;&gt;
    <h5>选购专用配件</h5></div>
  <div class="tijsq_main">
    <div class="tijsq_main_01"><table border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td width="103" align="center"><img src="${pageContext.request.contextPath}/images/tjsq.jpg" width="53" height="60" alt=""></td>
    <td width="103" align="center"><img src="/images/jiantou.jpg" width="30" height="20" alt=""></td>
    <td width="103" align="center"><img src="/images/wenjj.jpg" width="55" height="60" alt=""></td>
    <td width="103" align="center"><img src="/images/jiantou.jpg" width="30" height="20" alt=""></td>
    <td width="103" align="center"><img src="/images/huojian_01.jpg" width="55" height="60" alt=""></td>
    <td width="103" align="center"><img src="/images/jiantou.jpg" width="30" height="20" alt=""></td>
    <td width="103" align="center"><img src="/images/jcg.jpg" width="58" height="60" alt=""></td>
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
</tbody></table>
</div>
    <div class="tijsq_main_02">
      <div class="tijsq_main_05_let">串号：</div>
      <div class="tijsq_main_05_ret">124557487878787</div>
      <div class="clear"></div>
  </div>
  <div class="tijsq_main_02">
      <div class="tijsq_main_05_let">选择类型：</div>
      <div class="tijsq_main_05_ret_01">
        <input type="radio" name="radio" id="radio" value="radio">
        <label for="radio">返修 </label>
        <input type="radio" name="radio" id="radio" value="radio">（售出一年内可返修）       
        <input type="radio" name="radio" id="radio" value="radio">   退货              
        <input type="radio" name="radio" id="radio" value="radio">换货</div>
      <div class="clear"></div>
  </div>
  <div class="tijsq_main_02">
      <div class="tijsq_main_05_let">选择商品：</div>
      <div class="tijsq_main_05_ret_02"><img src="/images/chanpintu.jpg" width="49" height="44" align="left">速发 【送皮套+耳机】MIUI/小米 红米Note 4G<br>
        增强版移动官网版手机 </div>
      <div class="clear"></div>
  </div>
  <div class="tijsq_main_02">
      <div class="tijsq_main_05_let">返修/退货原因：</div>
      <div class="tijsq_main_05_ret">
         <select></select>
      </div>
      <div class="tijsq_main_05_ret">
        <h4>*请选择</h4></div>
      <div class="clear"></div>
  </div>
    <div class="tijsq_main_03">
      <div class="tijsq_main_05_let">备注和描述：</div>
      <div class="tijsq_main_05_ret">
        <textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <div class="tijsq_main_dibu">
    <div class="tijsq_main_dibu_01">系统正在处理，请耐心等待......</div>
  </div>
</div>
<jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
