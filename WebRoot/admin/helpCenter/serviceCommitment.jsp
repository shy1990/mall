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
		      <p><strong>服务承诺</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		      <p><strong>三际手机采购网服务承诺</strong></p>
		      <div>
		        <table style="font-family: 微软雅黑;font-size: 14px;height: 800px;">
		          <tr>
		           <td style="width: 100px;"><img src="images/miany01.png"/></td>
		           <td  style="width: 800px;"><strong>全品类供货</strong><br/>
致力于打造一站式采购平台，满足手机零售商全品类的采购需求。
		           </td>
		          </tr>
		          
		          <tr>
		            <td><img src="images/miany02.png"/></td>
		            <td><strong>正品保证</strong><br/>
厂家一手直供货源，严格的入库验收管理，承诺假1罚10。
		            </td>
		          </tr>
		          <tr>
		            <td><img src="images/miany03.png"/></td>
		            <td><strong>天天低价</strong><br/>
一手的采购货源能确保价格的优势，再通过全电商化运营，降低成本，出货价有显性优势。
		            </td>
		          </tr>
		          <tr>
		            <td><img src="images/miany04.png"/></td>
		            <td><strong>30天退货</strong><br/>
30天内，只要产品不影响二次销售，可随时退货，退货价于退货日页面价格执行，运费需自己支付。
		            </td>
		          </tr>
		          <tr>
		           <td><img src="images/miany05.png"/></td>
		           <td><strong>2年保修</strong><br/>
国家三包规定手机保修一年，三际承诺对零售店保修2年，人为损坏的不在保修范围。
		           </td>
		          </tr>
		          <tr>
		           <td><img src="images/miany06.png"/></td>
		           <td><strong>限时送达</strong><br/>
除去恶劣天气，交通不畅等特殊原因，三际承诺当天下单，次日送达。
		           </td>
		          </tr>
		          <tr>
		           <td><img src="images/miany07.png"/></td>
		           <td><strong>付款方便安全</strong><br/>
提供线上支付，货到pos收款两种安全便捷的方式，确保操作方便，安全放心</td>
		          </tr>
		          <tr>
		           <td><img src="images/miany08.png"/></td>
		           <td><strong>货物在途保障</strong><br/>
只要在未签收前，所发生的一切破损、丢失，均由三际负责承担损失。
		           </td>
		          </tr>
		          <tr>
		           <td><img src="images/miany09.png"/></td>
		           <td><strong>免收运费</strong><br/>
手机一台包邮。
		           </td>
		          </tr>
		          <tr>
		           <td><img src="images/miany10.png"/></td>
		           <td><strong>360度服务</strong><br/>
三际对零售店提供全方位的贴身服务，你的任何一个好的建议及需求都会迅速得到反馈。
		           </td>
		          </tr>
		        </table>
		      </div>
		    </div>
		  </div>
		<div class="clear"></div>
</div>
			
	</div>
	
	<div id="footer">
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
	
	</div>
  </body>
</html>
