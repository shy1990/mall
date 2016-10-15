<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta name="renderer" content="webkit">
    <base href="<%=basePath%>">
    
    <title>我的收藏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet"> -->
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
	
    <!-- <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script> -->
	
	<script type="text/javascript" >
	var skuCollectNums = ${collects.skuTatolNum};//单品收藏总数量
	var acsCollectNums = ${collects.acsTatolNum};//配件收藏总数量
	
	</script>
	<script type="text/javascript" src="js/myCenter/myCollect/collect.js"></script>
	<style type="text/css">
		.wodz_left{float:left; width:127px;}
		.wodz_left_top{ text-align:center; color:#FFFFFF; background-color:#FF1A1E; line-height:80px; font-size:18px;}
		.wodz_left_top a{color:#FFFFFF; display:block;}
		.wodz_left_txt{ padding:15px; background-color:#fcfcfc; line-height:35px; margin-bottom:20px;}
		.wodz_left_txt dd{ padding-left:5px; color:#939393;}
		.wodz_left_txt a{ color:#7B7B7B;}
		.wodz_left_txt a:hover{ color:#FF2700;}
		#wodz_left_txt a{ color:#000;}
		.wodz_ret{float:right; width:1040px;border:#e4e4e4 solid 1px; border-top:none;margin-bottom:20px; }
		.wodz_ret_01 {float: left;width: 100%;}
		.wodz_ret_01_let{float:left; width:100%;border-right:#e4e4e4 solid 1px;min-height:600px;height:auto;}
		.wodz_ret_01_ret{float:right; width:309px;}
		.wodz_ret_01_let_top{height:50px; padding:0 15px;}
		.wodz_ret_01_let_top_let{float:left; line-height:16px; border-left:#F40307 solid 3px; margin:17px 0 0; padding:0 0 0 5px; font-size:16px; color:#333;}
		.wodz_ret_01_let_top_mid{float:left;line-height:50px; padding-left:70px; color:#FF0004;}
		.wodz_ret_01_let_top_mid a{ color:#5A5A5A;}
		.wodz_ret_01_let_top_ret{float:left; padding-left:50px;line-height:50px; color:#388cea;}
		.wodz_ret_01_let_top_ret a{color:#388cea;}
		.wodz_ret_01_let_txt{ padding:0 15px 15px 15px;min-height:500px;height:auto;}
		.wodz_ret_01_let_txt li{ background-color:#fcfcfc; margin-top:10px; height:74px;}
		.wodz_ret_01_let_txt_01{float:left;}
		.wodz_ret_01_let_txt_02{float:left; width:410px; line-height:24px; padding:0 0 0 10px; color:#828282;}
		.wodz_ret_01_let_txt_02 span{ color:#191919;}
		#wodz_ret_01_let_txt_02 a{color:#388cea;}
		.wodz_ret_01_let_txt_03{ float:left; line-height:30px; width:80px; background-color:#FFFFFF; text-align:center;border:#e4e4e4 solid 1px; margin:17px 0 0 15px;}
		.wodz_ret_01_let_txt_03 a{ color:#4A4A4A; display:block;}
		.wodz_ret_01_let_txt_03 a:hover{ color:#31ACF9;}
		.wodz_ret_01_let_txt_04{ float:right;line-height:30px; width:80px; background-color:#FFFFFF; text-align:center;border:#e4e4e4 solid 1px; margin:17px 10px 0 0;}
		.wodz_ret_01_let_txt_04 a{ color:#4A4A4A; display:block;}
		.wodz_ret_01_let_txt_04 a:hover{ color:#31ACF9;}
		.wodz_ret_01_let_txt_06{padding:0 20px; line-height:40px; color:#939393;}
		.wodz_ret_01_let_txt_06 a{ padding-right:20px;color:#31ACF9;}
		.wodz_ret_01_let_txt_05, .wodz_ret_01_let_txt_07 {padding:10px 20px 0 20px;line-height:28px;color:#565656;}
		.wodz_ret_01_let_txt_07 span{ font-size:24px; color:#FF070B;}
		
		.nav-tabs {border-bottom: 1px solid #e4e4e4;}
		.nav {
			padding-left: 0;
			margin-bottom:0;
			list-style: none;
		}
		.nav a:hover{color:#f30;}
		.nav-tabs>li {
		float: left;
		margin-bottom: -1px;
		}
		.nav>li {
		position: relative;
		display: block;
		}
		.nav-tabs>li>a {
		margin-right: 2px;
		line-height: 1.428571429;
		
		border-radius: 4px 4px 0 0;
		}
		.nav>li>a {
		position: relative;
		display: block;
		padding: 10px 15px;
		font-size:16px; 
		}
		.tabSelect{border:#e4e4e4 solid 1px;border-top:none;}
	</style>
  </head>
  <body>
  
<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
		<div class="wodz_ret">
			<ul id="myTab" class="nav nav-tabs">
			   <li class="active" id="collect_tab_sku_li" style="width:519px;text-align: center;">
			      <a href="javascript:;" data-toggle="tab">单品收藏 </a>
			   </li>
			   <li style="width:519px;text-align: center;" class="tabSelect" id="collect_tab_acs_li">
			   	  <a href="javascript:;" data-toggle="tab">配件收藏</a>
			   </li>
			</ul>
			<div id="myTabContent" class="tab-content"> 
				<div class="wodz_ret_01 fade in active" id="sku">
					<div class="wodz_ret_01_let">
						<div class="wodz_ret_01_let_top">
							<div class="wodz_ret_01_let_top_let">单品收藏</div>
							<div class="clear"></div>
						</div>
						<div class="wodz_ret_01_let_txt">
							<ul class="sku_collect_list">
								<c:if test="${collects.skus != null }">
									<c:forEach var="cs" items="${collects.skus }">
										<li id="${cs.TARGET_ID}">
											<div class="wodz_ret_01_let_txt_01">
												<a href="goods/detail/${cs.GOODS_NUM }.html">
													<img src="${cs.PIC_SRC}" width="74" height="74" alt="" />
												</a>
											</div>
											<div class="wodz_ret_01_let_txt_02"><a href="goods/detail/${cs.GOODS_NUM }.html">${cs.BRANDNAME } ${cs.NAME } ${cs.EDITION } ${cs.STANDARD} ${cs.COLOR_NAME }</a>
												<br /> 收藏时间： ${cs.COLLECT_TIME }
												<br />
											</div>
											<div class="wodz_ret_01_let_txt_04">
												<a href="goods/detail/${cs.GOODS_NUM }.html">立即购买</a>
											</div>
											<div class="wodz_ret_01_let_txt_04">
												<a href="javascript:;" onclick="drop_collect(this,'sku','${cs.TARGET_ID}')">取消收藏</a>
											</div>
										</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
						<div class="fenye">
							<ul id="my_sku_collect_page">
							</ul>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="wodz_ret_01 fade" id="acs" style="display: none; ">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">配件收藏</div>
						<div class="clear"></div>
					</div>
					<div class="wodz_ret_01_let_txt">
						<ul class="acs_collect_list">
							<c:if test="${collects.acss != null }">
								<c:forEach var="as" items="${collects.acss}">
									<li id="${as.TARGET_ID}">
										<div class="wodz_ret_01_let_txt_01">
											<a href="accessory/detail/${as.ACCESSORIES_NUM }.html">
												<img src="${as.DEFAULT_IMG}" width="74" height="74" alt="" />
											</a>
										</div>
										<div class="wodz_ret_01_let_txt_02"><a href="accessory/detail/${as.ACCESSORIES_NUM }.html">${as.BRANDNAME }  ${as.NAME }  ${as.COLOR_NAME }</a>
											<br /> 收藏时间： ${as.COLLECT_TIME }
											<br />
										</div>
										<div class="wodz_ret_01_let_txt_04">
											<a href="accessory/detail/${as.ACCESSORIES_NUM }.html">立即购买</a>
										</div>
										<div class="wodz_ret_01_let_txt_04">
											<a href="javascript:;" onclick="drop_collect(this,'accessories','${as.TARGET_ID}')">取消收藏</a>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div> 
					<div class="fenye">
						<ul id="my_acs_collect_page">
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
  <jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
