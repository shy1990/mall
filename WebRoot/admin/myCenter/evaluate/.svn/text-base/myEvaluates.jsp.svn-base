<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的评论列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
	<script type="text/javascript">
		var countNum = ${evalUateMap.pageCount};
		var page = ${evalUateMap.page};
	</script>
	
	<script type="text/javascript" src="js/myCenter/evaluate/myEvaluates.js"></script>

  </head>
  
  <body>
    <jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
	
	<!--======================详情图片开始============================-->
		<div class="xq_mian">
			<!-- <div class="wodz_left">
				<div class="wodz_left_top"><a href="#">我的账户</a>
				</div>
				<div class="wodz_left_txt">
					<dl>
						<dt>订单管理</dt>
						<dd><a href="#">我的订单</a>
						</dd>
						<dd><a href="#">我的购物车</a>
						</dd>
						<dd><a href="#">我的收藏/关注</a>
						</dd>
						<dt>账户管理</dt>
						<dd><a href="#">账户信息</a>
						</dd>
						<dd><a href="#">账户安全</a>
						</dd>
						<dd><a href="evaluate/myEvaluates.html">评价管理</a>
						</dd>
						<dd><a href="#">退换申请</a>
						</dd>
						<dd><a href="#">我的积分 </a>
						</dd>
						<dt id="wodz_left_txt"><a href="#">退出登录</a></dt>
					</dl>
				</div>
			</div> -->
			<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
			<div class="wodz_ret">
				<div class="wodz_ret_01_let_top">
					<div class="wodz_ret_01_let_top_let">评价管理
					</div>
					<div class="clear"></div>
				</div>
				<div class="ddgl_main">
					<div class="pjgl_01">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="246">买家累积信用积分：<span>130</span>分 </td>
								<td width="685">好评率：
									<h4>100.00% </h4>
								</td>
							</tr>
						</table>
					</div>
					<div class="pjgl_02">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="405">评价</td>
								<td width="193">评价店铺名</td>
								<td width="386">宝贝信息 </td>
							</tr>
						</table>
					</div>
					<div class="pjgl_03">
						<table border="0" cellspacing="0" cellpadding="0">
						<c:if test="${!empty evalUateMap.evaluates}">
							<c:forEach var="e" items="${evalUateMap.evaluates}">
							<c:if test="${!empty e.orderItemss}">
				    			<c:forEach var="o" items="${e.orderItemss}">
				    				<!-- 如果配件数据不为空那么就取配件数据 -->
				    				<c:if test="${!empty o.accessory }">
				    					<tr>
				    						<c:if test="${e.totality==1}">
				    							<td width="83" valign="middle">
													<img src="images/happy.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>好评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
													</p>
												</td>
				    						</c:if>
				    						<c:if test="${e.totality==2}">
				    							<td width="83" valign="middle">
													<img src="images/engry.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>中评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</p>
												</td>
				    						</c:if>
				    						<c:if test="${e.totality==3}">
				    							<td width="83" valign="middle">
													<img src="images/chaping.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>差评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</p>
												</td>
				    						</c:if>
											<td width="188">${e.members.username }</td>
											<td width="434">${o.accessory.brand.name} ${o.accessory.name }</td>
										</tr>
				    				</c:if>
				    				
				    				<!-- 如果goods数据不为空那么就取goods数据 -->
				    				<c:if test="${!empty o.goodsSku }">
				    					<tr>
											<c:if test="${e.totality==1}">
				    							<td width="83" valign="middle">
													<img src="images/happy.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>好评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
													</p>
												</td>
				    						</c:if>
				    						<c:if test="${e.totality==2}">
				    							<td width="83" valign="middle">
													<img src="images/engry.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>中评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</p>
												</td>
				    						</c:if>
				    						<c:if test="${e.totality==3}">
				    							<td width="83" valign="middle">
													<img src="images/chaping.jpg" width="25" height="28" alt="" />
												</td>
												<td width="305">
													<p>差评！</p>
													<p><span><fmt:formatDate value="${e.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</p>
												</td>
				    						</c:if>
											<td width="188">${e.members.username }</td>
											<td width="434">${o.goodsSku.goods.brand.name} ${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }</td>
										</tr>
				    				</c:if>
					    		</c:forEach>
					    	</c:if>
			    			</c:forEach>
				    	</c:if>
						</table>
					</div>
					<div class="phone_main_07">
						<div class="fenye">
							<ul id="mycenter_myEvaluates_page">
								<!-- <li><a href="#">上一页</a>
								</li>
								<li><a href="#">1</a>
								</li>
								<li><a href="#">2</a>
								</li>
								<li><a href="#">3</a>
								</li>
								<li>....</li> 
								<li><a href="#">下一页</a>
								</li>
								<li id="fenye">共20页</li>
								<li id="fenyea">,到第</li>
								<li id="fenyeb">
									<input name="" type="text" />
								</li>
								<li id="fenyea">页</li> -->
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	
	<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
