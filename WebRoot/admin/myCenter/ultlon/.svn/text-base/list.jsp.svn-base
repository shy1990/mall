<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <meta name="renderer" content="webkit">
    <base href="<%=basePath%>">

			<title>售后申请</title> 

			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			<link href="css/css.css" rel="stylesheet" type="text/css" />
			<link href="css/ultlon/ultlon_lslist.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
			<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
			<script type="text/javascript">
				$(function(){
					var number = ${metadata.number};
					var totalPages = ${metadata.totalPages}-1;
					for(var i = 0 ; i<=totalPages; i++){
						console.info(i);
						var href = "/ultlon/list/"+(i+1)+".html";
						var li = $('<li class="page"><a href="'+href+'">'+(i+1)+'</a></li>');
						
						if(i==number){
							//当前页，颜色
							li.addClass("active");
						};
						if(i==0){
							//第一页
							href = "/ultlon/list/"+(number)+".html";
						    if(number==0){href="JavaScript:;"; }
						    var li1 = '<li class="page "><a href="'+href+'">上一页</a></li>';
						    
						    $("#mycenter_orders_page").append(li1);
						};
						
						$("#mycenter_orders_page").append(li);
						
						if(i==totalPages){
							//最后一页
							href = "/ultlon/list/"+(number+1+1)+".html";
						    if(number==totalPages){href="JavaScript:;"; }
						    var li1 = '<li class="page "><a href="'+href+'">下一页</a></li>';
						    
						    $("#mycenter_orders_page").append(li1);
						};
						
					};
					
					//初始化调整分页位置
					$(".fenye").each(function(i,fenye){
						var liNum = $(fenye).find("li").length; 
						//如果页数小于8那就重新定位页码的位置，使其居中
						if(liNum<9){
							var width = $(fenye).width();
							//$(fenye).css('margin-left',(width-liNum*60)/2+"px");
							$(fenye).css('margin-left',(width-liNum*50)+"px");
						} 
					});
					
				});
			</script>
			<style type="text/css">
				.ddgl_main {
					  min-height: 500px;
					}
			</style>
		</head>

		<body>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
			<div class="xq_mian">
				<jsp:include page="${pageContext.request.contextPath}/admin/myCenter/left.jsp"></jsp:include>
				<div class="wodz_ret">
					<div class="wodz_ret_01_let_top">
						<div class="wodz_ret_01_let_top_let">售后列表</div>
						<div class="clear"></div>
					</div>
					<div class="ddgl_main">
						<%--  ${date }
						<br><br/>
						<br><br/>
						${metadata }  --%>
						
						<table border="0" cellpadding="0" cellspacing="0" class="ultlon_XXX">
							<tbody><tr class="ultlon_top">
								<th>售后单号</th>
								<th>订单号</th>
								<th>串码</th>
								<th>类型</th>
								<th>价格</th>
								<th class="ultlon_th6">状态</th>
								<th>申请时间</th>
							</tr>
							<c:forEach var="d" items="${date}">
								<tr>
									<td>${d.id}</td>
									<td>${d.orderNum}</td>
									<td>${d.imei}</td>
									<td>
										<c:if test="${d.type=='KXS'}">开箱损</c:if>
										<c:if test="${d.type == 'THH30'}">30天退换货</c:if>
										<c:if test="${d.type == 'WX'}">维修</c:if>
										<c:if test="${d.type == 'DMDHX100'}">多美达百日换新</c:if>
									</td>
									<td><c:if test="${!empty d.price}">￥</c:if> ${d.price}</td>
									<td class="ultlon_td6">
										${d.result} 
									</td> 
									<td><%-- ${d.content.receiveTime} --%>
										${d.createTime} 
									</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					
					 <div class="fenye">
						<ul id="mycenter_orders_page">
							<!-- <li class="prev disabled" jp-role="prev" jp-data="0"><a href="javascript:;">上一页</a></li>
							<li class="page active" jp-role="page" jp-data="1"><a href="javascript:;">1</a></li>
							<li class="page" jp-role="page" jp-data="2"><a href="javascript:;">2</a></li>
							<li class="page" jp-role="page" jp-data="3"><a href="javascript:;">3</a></li>
							<li class="page" jp-role="page" jp-data="4"><a href="javascript:;">4</a></li>
							<li class="page" jp-role="page" jp-data="5"><a href="javascript:;">5</a></li>
							<li class="page" jp-role="page" jp-data="6"><a href="javascript:;">6</a></li>
							<li class="page" jp-role="page" jp-data="7"><a href="javascript:;">7</a></li>
							<li class="page" jp-role="page" jp-data="8"><a href="javascript:;">8</a></li>
							<li class="next" jp-role="next" jp-data="2"><a href="javascript:;">下一页</a></li> -->
						</ul>
					</div>
					
				</div>
				<div class="clear"></div>
			</div>
			<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
		</body>

		</html>
