<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.xgzy_main_01 li {
	border-left: #e4e4e4 solid 1px;
	border-right: none;
	width: 236px;
}

.jifen_main {
	width: 1190px;
}

.fix_top {
	position: fixed;
	margin-top:0;
	top: 0;
	z-index: 9999;
}

.jifen_main1 {
	margin-top: 260px;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<link rel="stylesheet" href="js/sj/ui/block-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/simple-data-view/css.css" />
<link rel="stylesheet" href="js/sj/ui/data-view-list/css.css" />
<script type="text/javascript" src="js/sj/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/sj/cart.js"></script>
<script type="text/javascript"
	src="js/sj/ui/simple-data-view/simple-data-view.js"></script>
<script type="text/javascript"
	src="js/sj/ui/block-data-view/block-data-view.js"></script>
<script type="text/javascript"
	src="js/sj/ui/data-view-list/data-view-list.js"></script>
<script type="text/javascript" src="js/jquery.pin.js"></script>
<script type="text/javascript">
	$(function() {
		$(window).scroll(
				function(e) {
					var winScroll = document.documentElement.scrollTop
							|| document.body.scrollTop;
					if (winScroll >= 250) {
						var nextTop = $(".jifen_top").next().addClass(
								"jifen_main1");
						$(".jifen_top").addClass("fix_top");
					} else {
						var nextTop = $(".jifen_top").next().removeClass(
								"jifen_main1");
						$(".jifen_top").removeClass("fix_top");
					}
				});
		var cart = new Cart({
			key : 'cart'
		});
		var coefficient = ${pointCoefficient};//积分系数
		var point = parseInt('${point}'.split(",")[0]);
 
		var updatePrice = function() {
			var totalNumber = 0;
			var totalPrice = 0;//积分
			var spare=0.00;
			var totalOriginalPrice = 0.00;//总价格
			$.each(detail.items, function(i, item) {
				totalNumber+=parseInt(item.number);
				totalPrice+=item.value1*item.number;
				totalOriginalPrice+=item.value2*item.number;
				spare+=parseInt(item.value2)*item.number;
			});

			$(".totalOriginalPrice").text(totalOriginalPrice.toFixed(2));
			$(".totalPrice").text(totalPrice);
			$(".totalNumber").text(totalNumber);
			$(".spare").text((spare).toFixed(2));
			//积分计算
			var pointCoefficient = '${pointCoefficient}';
			var thispoint =parseInt('${point}'.split(",")[1]);
			$(".thispoint").text(thispoint);
			$(".totalpoint").text(point - totalPrice);
			$(".ljpoint").text(thispoint+point-totalPrice);

		};
		cart.items.on('afteradd afterremove', updatePrice);
		
		var costPoint = 0;
		var beforeNumberChange = function(data,type) {
			costPoint = 0;
			$.each(detail.items, function(i, item) {
				costPoint += item.value1 * item.number;
			});
			if(type==="add"){
				if (costPoint + data.value1 > point) {
					alert("~积分不够。");
					return false;
				} else {
					costPoint += data.value1;
					$(".point").text(point - costPoint);
					$(".costpoint").text(costPoint);
					return true;
				}
			}else{
				costPoint -= data.value1;
				$(".point").text(point+costPoint);
				$(".costpoint").text(costPoint);
				return true;
			}
		}
		// 去除空连接跳转
		$("a[href='#']").attr('href', 'javascript:void(0);');
		$.post("point/pointGoods.html", function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				var datas = [];
				$.each(result.obj, function(i, data) {
					datas.push({
						"id" : data.id,
						"type" : data.type,
						"goodsName" : '---',
						"specification" : "---",
						"name" : data.name,
						"imgSrc" : data.pic,
						"value1" : data.integral,
						"value2" : data.price,
						"value3" : data.price,
						"tmallUrl" : "javascript:void(0);",
						"description" : data.name,
						"number" : 0
					});
				});
				$("#qbsp").dataViewList({
					viewType : 'block',
					value1Prefix : '',
					datas : datas,
					itemChange : onListItemnChange1,
					beforeNumberChange : beforeNumberChange
				});
				$(".img a").attr("href","javascript:");
			}
		});
		$.post("point/pointGoods.html", {
			type : '手机相关'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				var datas = [];
				$.each(result.obj, function(i, data) {
					datas.push({
						"id" : data.id,
						"type" : data.type,
						"goodsName" : data.name,
						"specification" : data.name,
						"name" : data.name,
						"imgSrc" : data.pic,
						"value1" : data.integral,
						"value2" : data.price,
						"value3" : data.price,
						"tmallUrl" : "javascript:void(0);",
						"description" : data.name,
						"number" : 0
					});
				});
				$("#sjxg").dataViewList({
					viewType : 'block',
					value1Prefix : '',
					datas : datas,
					itemChange : onListItemnChange1,
					beforeNumberChange : beforeNumberChange
				});
				$(".img a").attr("href","javascript:");
			}
		});
		$.post("point/pointGoods.html", {
			type : '生活'
		}, function(result) {
			result = $.parseJSON(result);
			if (result.success) {
				var datas = [];
				$.each(result.obj, function(i, data) {
					datas.push({
						"id" : data.id,
						"type" : data.type,
						"goodsName" : data.name,
						"specification" : data.name,
						"name" : data.name,
						"imgSrc" : data.pic,
						"value1" : data.integral,
						"value2" : data.price,
						"value3" : data.price,
						"tmallUrl" : "javascript:void(0);",
						"description" : data.name,
						"number" : 0
					});
				});
				$("#shyp").dataViewList({
					viewType : 'block',
					value1Prefix : '',
					datas : datas,
					itemChange : onListItemnChange1,
					beforeNumberChange : beforeNumberChange
				});
				$(".img a").attr("href","javascript:");
			}
		});
		var detail = $("#detailList").dataViewList({
			value1Prefix : "",
			itemChange : onListItemnChange2,
			beforeNumberChange : beforeNumberChange
		}).dataViewList('instance');

		function onListItemnChange1(event, item) {
			var data = item.data;
			data.from = event.target;
			if (parseInt(data.number)) {
				detail.appendDatas([ data ]);
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '0',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.add(item);
			} else {
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '0',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.remove(item);
				detail.remove(data);
			}
		}
		
		function onListItemnChange2(event, item) {
			var data = item.data;
			var list = $(data.from).data('sjDataViewList');
			if (parseInt(data.number)) {
				list.appendDatas([ data ]);
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '0',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.add(item);
			} else {
				list.appendDatas([ data ]);
				detail.remove(data);
				var item = new CartItem({
					goodsId : data.id,
					quantity : data.number,
					type : '0',
					price : data.value1,
					originalPrice : data.value2
				});
				cart.add(item);
			}
		}
		updatePrice();
	});
</script>
<title>三际手机采购网-选购通用配件</title>
</head>

<body>

	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<div class="xq_mian">
		<div class="xq_mian_top">
			<span><a href="javascript:history.back();">返回</a></span>当前位置&gt;&gt;
			<h5>积分商城</h5>
		</div>
		<!---------已采购商品信息---------->
		<!---------第二块采购说明---------->
		<div class="jifen_top">
			<div class="jifen_top_let">
				<p>剩余积分</p>
				<p>
					<span class="totalpoint">0</span>
				</p>
			</div>
			<%-- <div class="jifen_top_let">
				<p>本次获得积分</p>
				<p>
					<span class="thispoint">0</span>
				</p>
			</div> --%>
			<div class="jifen_top_let">
				<p>累计积分</p>
				<p>
					<span class="costpoint">0</span>
				</p>
			</div>
			<div class="jifen_top_ret">
				已选商品：
				<h3 class='totalNumber'>0</h3>
				件 <br> 消费积分：
				<h3 class="costpoint">0</h3>
				分 <br> 可用积分： <span class="totalpoint">0</span> 分
			</div>
			<div class="clear"></div>
		</div>
		<div class="jifen_main">
			<div class="jifen_main_01">
			
				<script type="text/javascript">
					function turnTo(sel) {
						if ($(sel).is(':hidden')) {
							$("#showList").children("div:not('" + sel + "')")
									.slideUp();
							$(sel).fadeIn();
						}
					}
				</script>
				<ul>
					<li>积分兑换</li>
					<li><a href="javascript:void(0);"
						onmousemove="turnTo('#qbsp');">全部商品</a></li>
					<li><a href="javascript:void(0);"
						onmousemove="turnTo('#sjxg');">手机相关</a></li>
					<li><a href="javascript:void(0);"
						onmousemove="turnTo('#shyp');">生活用品</a></li>
				</ul>

				<div class="clear"></div>
			</div>
			<div id="showList">
				<div id='qbsp'></div>

				<div id='sjxg' style="display: none;"></div>

				<div id='shyp' style="display: none;"></div>
			</div>
			<div class="xq_main_02_gaia" style="border-top: #e4e4e4 1px solid;">
				<div class="xgzy_main_07">
					<div class="xgzy_main_07_top">
						<table border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="82">品类</td>
									<td width="95">品牌</td>
									<td width="149">名称</td>
									<td width="196">规格</td>
									<td width="109">积分</td>
									<td width="193">购量</td>
									<td width="87">天猫价</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="detailList" class="xgzy_main_09 "></div>
				</div>
				<div class="xgzy_main_08">
					<div class="xgzy_main_let_01">
						<div class="xgzy_main_let_02">
							<img src="images/huax.png" width="60" height="10">
						</div>
						<span>原价<font class='totalOriginalPrice'>0.00</font>元
						</span><br>共
						<h3 class='totalNumber'>0</h3>
						件
						<h3 class="costpoint">0.00</h3>
						积分
					</div>
					<div class="xgzy_main_let_03">
						已省<span class="spare">0.00</span>元
					</div>
					<div class="xgzy_main_let_04">
					
				<c:if test="${!empty msg}">
							<div style="color: red; margin-left: 40px;">${msg}</div>
						</c:if>
					<%-- <form id="myform" action="order/add.html" method="post">
						<input id="mycartIds" type="hidden" name="cartIds" value="${cartIds}"/>
					</form> --%>
						<script type="text/javascript">
					
					
							function add2Cart() {
								var datas = $("#detailList").data(
										'sjDataViewList').items;
								var params = {};
								$.each(datas, function(i, data) {
									params[data.id] = data.number;
								});
								
								$.post('cart/addJfSp2Cart.html', {
									param : $.toJSON(params)
								},function(rs){
								
									if(rs!="null"){
										var cardids= $("#mycartIds").val();
										$("#mycartIds").val(cardids+","+rs.replace(/\"/g,''));
										
									};
								
								}).done(function(r) {
									//$("#myform").submit();
									if(r=="false"){
										alert("您剩余的积分不足！！！！！");
									}else{
										location.href ="cart/main.html";
									}
								});
							};
						</script>
						<a href="javascript:add2Cart();">加入购物车</a>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!---------第三块产品详情---------->
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
