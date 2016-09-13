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
		      <p><strong>如何购买</strong></p>
		    </div>
		    <div class="about_mian_ret_02">
		       
		       <div style="width: 100%;height:380px;">
				 <p><strong>第一步：注册</strong></p>
		          <p style="text-indent: 2em">若要在三际采购商品，首先需要注册为三际会员</p>
		          <div align="center">
		            <img src="images/zhuce_01.jpg" align="middle" >
		          </div> 		       
		       </div>
		       
		        <div style="width: 100%;height:520px;">
		          <p><strong>第二步： 验证</strong></p>
		          <p style="text-indent: 2em">如需注册为三际采购网会员用户，需我司业务主管上门考察验证</p>
		          <div align="center">
		            <img src="images/zhuce_02.jpg" align="middle"/>
		          </div>
		        </div>
		        <div style="width: 100%;height:480px;">
		          <p><strong>第三步： 登陆</strong></p>
				  <p style="text-indent: 2em">输入用户名<br/>用户密码  登陆三际采购网</p>
				  <div align="center">
					<img src="images/denglu_01.jpg" align="middle"/>
				  </div>
		        </div>
		        <div style="width: 100%;height:850px;">
				  <P><strong>第四步： 挑选商品，进入详情页了解商品 </strong></P>                     
		          <div align="center">
		            <img src="images/spxiangqing.jpg" align="middle"/>
		          </div>
		          <div align="center">
		           <img src="images/txshangpin.jpg" align="middle">
		          </div>
		        </div>
		        <div style="width: 100%;height:700px;">
		         <p><strong>第五步：</strong></p>
		           <p>挑选专用配件然后添加进购物车，在购物车界面，您可以查看商品单价、
		                                                    商品总价，更改商品的购买数量（在购物车商品所在栏目后的商品数量框中输入购买数量），
		                                                    删除加入购物车的商品，或者直接去结算；您还可以选择“继续购物”，将其他想购买的商品加入购物车，
		                                                    然后再一起去结算。</p>
		          <div align="center">
		           <img src="images/zypeijian.jpg" align="middle">
		          </div>
		        </div>
		        <div style="width: 100%;height:630px">
		           <p><strong>第六步： 挑选通用配件</strong></p>
		           <div align="center">
		             <img src="images/typeijian.jpg" align="middle">
		           </div>
		        </div>
		        <div style="width: 100%;height:680px " >
		          <p><strong>第七步： 挑选积分产品</strong></p>
		          <div align="center">
		           <img src="images/txjfchanpin.jpg" align="middle">
		          </div>
		        </div>
		        <div style="width: 100%;height:455px; ">
		          <p><strong>第八步： 付款结算；  </strong></p>
		          <p>1：网银支付<br/>2：货到付款 </p>
		          <div align="center">
		            <img src="images/fkjiesuan.jpg" align="middle">
		          </div>
		        </div>
		        <div>
		          <p><strong>第九步 查看订单状态</strong></p>
		          <p>订单成功提交之后，您可以到“我的订单”页面查看订单的处理进度信息。</p>
		        </div>
		        <div>
		          <p><strong>第十步 收货与评价:商品签收后，您可以进入个人中心，点击商品评价中“我要评论”即可完成评价 </strong></p>
		          <p>商品签收后，您可以进入个人中心，点击商品评价中“我要评论”即可完成评价</p>
		          <div align="center">
		            <img src="images/sppingjia.jpg" align="middle">
		          </div>
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
