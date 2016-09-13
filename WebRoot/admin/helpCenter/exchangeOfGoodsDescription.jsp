<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>三际手机采购网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/staticLink.css">
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="js/helpCenter/helpCenter.js"></script>
  </head>
  
  <body>
    <div id="container">

	<div id="header">
	<h1></h1>
	 <jsp:include page="../common/head.jsp"></jsp:include>
	</div>
	
	<div id="content">
		<div class="about_mian">
		  <jsp:include page="../common/aboutNav.jsp"></jsp:include>
		  <div class="about_mian_ret">
		    <div class="about_mian_ret_01">
		      <p><strong>手机寄修服务</strong></p>
		      <p> <span></span></p>
		    </div>
		    <div class="about_mian_ret_02">
		      
		      <strong>一、 三际采购网寄修服务说明</strong>
		      <p>
				1.三际采购网寄修服务旨在为用户提供一条便捷、简单的产品维修通道，丰富用户的维修方式选择，让三际采购网的售后维修更加方便、快捷；<br/>
				2.用户购买三际采购网超过30日（不含30日，以开具发票或接收产品之日起计算）发现质量问题，用户拨打三际售后服务热线 400-937-1688提交寄修服务申请，获取业务受理短信后，用户根据指导寄送故障机至指定售后维修中心，完成维修后三际将修复的机器寄回至用户；<br/>
				3.对于超过保修期或发生因人为损坏引起的故障，三际维修中心将会联系用户进行报价维修或者联系业务员办理寄回流程；<br/>
				4.运费规则：<br>
			 </p>
			 <div align="center"><img src="images/yfguize.jpg" align="middle"></div>
			 <strong>一、 服务承诺</strong>
			<p style="text-indent: 2em">三际收到故障机并确认产品故障属于保修范围内（以国家三包法为准），在两个自然日内完成维修，期间往返物流周期参照各快递公司发布信息。<br/>
			（因异常问题沟通引起的维修、返还时间延迟不在此限制范围，视实际情况而定。）</p>
			<strong>三、 三际手机采购网寄修服务流程</strong>
			<div align="center"><img src="images/fwliucheng.jpg" align="middle"></div>
			<strong>四、 温馨提示</strong>
			<p>
			    1.寄修服务仅限三际手机和平板产品；<br/>
				2.用户需拨打热线提交寄修服务申请，如用户未提交寄修申请而直接寄送故障机至三际维修中心，维修中心有权拒收、退回用户寄送的故障机；<br/>
				3.用户通过热线提交寄修服务申请后，请根据提示在两天内寄出故障机；<br/>
				4.如果您产品或故障不在保修范围内，我们会根据实际情况进行收费维修；<br/>
				5.物流责任：因物流原因造成的产品丢失、损坏等问题的责任由物流公司承担；<br/>
				6.为了保护您的个人信息，请您在邮寄前提前备份存储在产品、SD卡、SIM卡内数据和资料，三际授权服务中心无权对您的产品信息进行备份和保存。<br/>
				7.如果您预设了开机密码，请您送修前清除密码。<br/>
				8.如有疑问请您拨打三际客户服务热线（0531-67860315）或联系业务人员咨询。
			</p>
		    </div>
		  </div>
		<div class="clear"></div>
</div>
			
	</div>
	
	
		<jsp:include page="../common/footer.jsp"></jsp:include>
	
	
	</div>
  </body>
</html>
