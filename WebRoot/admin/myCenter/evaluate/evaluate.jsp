<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>评论</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.raty.js"></script>
	 <script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript"> 	
		$(function(){
			 $('#evaluate_desc').raty({
		        starOff: 'images/happy_01.jpg',
		        starOn : 'images/happy.jpg',
		        width  : '300',
		        target : '#targetKeep-desc',
        		targetKeep: true,
		        hints     : ['<h4>1分</h4> <span>-失望</span>', '<h4>2分</h4> <span>-不满</span>', '<h4>3分</h4> <span>-一般</span>', '<h4>4分</h4> <span>-惊喜</span>', '<h4> 5分</h4> <span>-质量非常好，价格便宜，与描述相符</span>'],
		        scoreName : 'descMatch'
		      });
		      
		      $('#evaluate_logistics').raty({
		        starOff: 'images/happy_01.jpg',
		        starOn : 'images/happy.jpg',
		        width  : '300',
		        target : '#targetKeep-logistics',
        		targetKeep: true,
		        hints     : ['<h4>1分</h4> <span>-失望</span>', '<h4>2分</h4> <span>-不满</span>', '<h4>3分</h4> <span>-一般</span>', '<h4>4分</h4> <span>-惊喜</span>', '<h4> 5分</h4> <span>-质量非常好，价格便宜，与描述相符</span>'],
		        scoreName : 'logistics'
		      });
		      
		      $('#evaluate_clerkManner').raty({
		        starOff: 'images/happy_01.jpg',
		        starOn : 'images/happy.jpg',
		        width  : '300',
		        target : '#targetKeep-clerkManner',
        		targetKeep: true,
		        hints     : ['<h4>1分</h4> <span>-失望</span>', '<h4>2分</h4> <span>-不满</span>', '<h4>3分</h4> <span>-一般</span>', '<h4>4分</h4> <span>-惊喜</span>', '<h4> 5分</h4> <span>-质量非常好，价格便宜，与描述相符</span>'],
		        scoreName : 'clerkManner'
		      });
		      
		      $('#evaluate_sendManner').raty({
		        starOff: 'images/happy_01.jpg',
		        starOn : 'images/happy.jpg',
		        width  : '300',
		        target : '#targetKeep-sendManner',
        		targetKeep: true,
		        hints     : ['<h4>1分</h4> <span>-失望</span>', '<h4>2分</h4> <span>-不满</span>', '<h4>3分</h4> <span>-一般</span>', '<h4>4分</h4> <span>-惊喜</span>', '<h4> 5分</h4> <span>-质量非常好，价格便宜，与描述相符</span>'],
		        scoreName : 'sendManner'
		      });
		      
		      //点击好评按钮取值
		      $(".bbpj_01_ret_top input[name='totality']").live("click",function(){
		      	$("#evaluate_totality").val($(this).val());
		      });
		      
		      //检测评价内容同步到隐藏域
		      $("#evaluate_textarea_contend").live("keyup",function(){
		      	$("#evaluate_content").val($(this).val());
		      });
		      
		});
		
		//添加评论
		function goEvaluateAdd(){
			if(!$("#evaluate_totality").val()){
				alert("请填写完整的评论信息");
				return false;
			}
		 	if(!$("#evaluate_content").val()){
				alert("请填写完整的评论信息");
				return false;
			} 
			/* 	alert($("#saleType").val());
				return false; */
			var sub = true;
			
			$(".bbpj_03 input").each(function(i,input){
				
				if(!$(input).val()){
					alert("请选择星号评价信息");
					sub = false;
					return false;
				};
				//console.info(i);
			});
			
			//提交数据之前检测需要的数据是否完整不完整返回false
			if(sub){
				$("#evaluate_add").submit(); 
			}
		    
		}
		
	</script>

  </head>
  
  <body>
    <jsp:include page="${pageContext.request.contextPath}/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="${pageContext.request.contextPath}/admin/common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top"><span><a href="#">返回</a></span>当前位置&gt;&gt;<a href="#">小米手机</a>&gt;&gt;<a href="#">商品详情</a>&gt;&gt;
			<h5>选购专用配件</h5>
		</div>
		<div class="tijsq_main_box">
			<div class="wodz_ret_01_let_top">
				<div class="wodz_ret_01_let_top_let">订单评价</div>
				<div class="clear"></div>
			</div>
			<div class="bbpj_01">
				<div class="bbpj_01_let">
					<div class="bbpj_01_let_txt">
						<ul>
							<c:forEach var="o" items="${orderMap.order.orderItemss }">
								<li>
				    				<!-- 判断类型是手机还是配件 -->
					    			<c:if test="${o.targetType == 'sku' }">
					    				<!-- 取单品数据 -->
										<img src="${o.goodsSku.goods.defaultImg }" width="68" height="65" align="left"/>${o.goodsSku.goods.brand.name}/${o.goodsSku.goods.name} ${o.goodsSku.edition } ${o.goodsSku.standard }
					    			</c:if>
					    			<c:if test="${o.targetType == 'accessories' }">
					    				<!-- 取配件数据 -->
										<img src="${o.accessory.defaultImg }" width="68" height="65" align="left"/>${o.accessory.brand.name}/${o.accessory.name }
					    			</c:if> 
					    			<!-- 积分商品 -->
							    	<c:if test="${!empty o.pointGoods}">
							    		<img src="${o.pointGoods.pic }" width="68" height="65" align="left"/>${o.pointGoods.type}/${o.pointGoods.name }
							    	</c:if>
							    	<!-- 赠品商品信息 -->
					    			<c:if test="${!empty o.gift}">
					    				<img src="${o.gift.accessory.defaultImg }" width="68" height="65" align="left"/>${o.gift.accessory.brand.name}/${o.gift.accessory.name }
					    			</c:if>
				    			</li>
			    			</c:forEach> 
						</ul>
						<div class="clear"></div>
					</div>
				</div>
				<div class="bbpj_01_ret">
					<div class="bbpj_01_ret_top">
						<ul>
							<li>
								<div class="bbpj_01_ret_top_01">
									<input type="radio" name="totality" value="1"/>
								</div>
							</li>
							 <li>
								<img src="images/happy.jpg" width="25" height="28" alt=""/>
							</li>
							<!--<li>
								<div class="bbpj_01_ret_top_02">可获18分</div>
							</li> -->
							<li>
								<div class="bbpj_01_ret_top_01">
									<input type="radio" name="totality" value="2"/>
								</div>
							</li>
							<li>
								<img src="images/engry.jpg" width="25" height="28" alt=""/>
							</li>
							<li>
								<div class="bbpj_01_ret_top_01">
									<input type="radio" name="totality" value="3"/>
								</div>
							</li>
							<li>
								<img src="images/chaping.jpg" width="28" height="31" alt=""/>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="bbpj_01_ret_txt">
						<textarea id="evaluate_textarea_contend"></textarea>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div>
				<div class="bbpj_01_let">
					<div class="bbpj_02">动态评分</div>
					<div class="clear"></div>
				</div>
				<form action="evaluate/add.html" id="evaluate_add" method="post">
				<div class="bbpj_01_ret">
					<div class="bbpj_03">
						<ul>
							<li>
								<div id="evaluate_desc" style="float: left;">与描述相符：</div>
								<div id="targetKeep-desc" style="float:left;width:300px;"></div>
								<div class="clear"></div>
							</li>
							<li>
								<div id="evaluate_logistics" style="float: left;">物流的速度：</div>
								<div id="targetKeep-logistics" style="float:left;width:300px;"></div>
								<div class="clear"></div>
							</li>
							<li>
								<div id="evaluate_clerkManner" style="float: left;">业务员态度：</div>
								<div id="targetKeep-clerkManner" style="float:left;width:300px;"></div>
								<div class="clear"></div>
							</li>
							<li>
								<div id="evaluate_sendManner" style="float: left;">送件人态度：</div>
								<div id="targetKeep-sendManner" style="float:left;width:300px;"></div>
								<div class="clear"></div>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="bbpj_04"><a href="javascript:goEvaluateAdd();">提交评价</a>
					</div>
				</div>
				<input type="hidden" value="" name="totality" id="evaluate_totality" />
				<input type="hidden" value="" name="content" id="evaluate_content" />
				 <input type="hidden" name="saleType" id="saleType" value="${orderMap.order.saleType}"/> 
				<!-- 后台可以根据订单号码查询，订单详情 -->
				<input type="hidden" value="${orderMap.order.id}" name="orderId" />
				</form>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	
	<jsp:include page="${pageContext.request.contextPath}/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
