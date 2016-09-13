<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>订单支付</title>


<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/newstyle.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/regions.js"></script>

<style type="text/css">
	#background {position:absolute; z-index:9998; top:0px; left:0px; background:rgb(50,50,50);background:rgba(0,0,0,0.5); display:none;}
	#content {position:absolute; width:590px; z-index:9999; background:#fff; border-radius:5px; display:none;}
	.up-box {width: 590px;position: absolute;border-radius: 5px;top: 0px;left: 0px;	z-index: 20000;}
	.up-box-hd {background: #D9534F;height: 37px;line-height: 35px;	position: relative;	background-position: 0px -350px;
		border-radius: 5px 5px 0px 0px;	padding: 0px 20px;	font-size: 16px;color: #E5F8FF;	}
	.up-box-bd {background: #FFF none repeat scroll 0% 0%;border-radius: 0px 0px 5px 5px;padding: 20px 60px;line-height: 25px;}
	.up-box-bd .icon {float: left;margin-right: 25px;}
	.up-con {height: 66px;}
	.up-con .clearfix {display: block;}
	.up-box-bd .r-txt {width: 360px;margin-top: 10px;float: left;color: #666;}
	.ft14 {	font-size: 14px;padding: 0;margin: 0;}
	.up-box-bd .lay-btn {padding: 20px 0px 0px;}
	.lay-btn {padding: 10px 0px;text-align: center;	}
	.lay-btn a {margin: 0px 10px;outline: 0px none;	text-decoration: none;}
	.btn92 {
		width: 90px;
		display: inline-block;
		text-align: center;
		color: #333;
		border-radius: 4px;
		height: 28px;
		line-height: 28px;
		border: 1px solid #CCC;
		background-position: 0px -100px;
		font-size: 12px;
	}
</style>

</head>

<body>
	<!-- 左边快捷按钮 -->
	<jsp:include page="../common/follow.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<!-- <form id="admin_pay_yeePayForm" method="post" action="yeePay/toYEEPayByOrderId.html" target="_blank"> -->
	<form id="admin_pay_yeePayForm" method="post" action="yeePay/toYEEPayByOrderId.html" target="_blank">
		<input type="hidden" name="order.id" value="${order.id }" />
		<!--======================详情图片开始============================-->
		<div class="xq_mian">
			<div class="xq_mian_top">当前位置&gt;&gt;订单支付&gt;&gt;</div>
			<div class="zhifu">

				<div class="zhifu_02">
						<div class="boreder-red">
							<div class="zhifus_01" id="balance_box_div" style="display:none;">
								<div class="zhifus_02">
									<div class="tx-1 ">
										<input type="checkbox" name="" id="checkbox-1" value="" />
										<label for="checkbox-1"> &nbsp;钱包余额支付</label>
										<p>钱包余额：<span class="text-red" id="balance_span">0</span>元</p>
									</div>
									<div class="tx-2 hidden">
										<div class="blk">钱包支付：<span id="balance_span2">0</span>元
											<%-- <span class="margin-left-20">钱包余额不足</span> --%>
											<span class="margin-left-10">还需要支付</span>
											<span class="text-red shiji_zhifu_num">0</span>元
											<%-- <div class="float-right">
												钱包流水号：<span class="text-green">00005540</span>
											</div> --%>
											<div class="clear"></div>
										</div>
										<p>钱包余额：<span class="text-red qian_bao_yu_e">00.00</span>元</p>
										<!-- <p><input type="button" id="cancel_balance_pay" value="取消使用钱包"/></p> -->
									</div>
									<div class="tx-3 hidden">
										<p>
											支付密码：
											<input type="password" style="display:none" />
											<input class="input-border balance_pay_pwd_input" name="payPwd" type="password" maxlength="20" autocomplete=off />
											<div class="btn-sub">
												<a href="javascript:pwdSuccess();">确定</a>
											</div>
										</p>
									</div>
								</div>
							</div>
							<script>
								var balanceNum = 0;
								var total ;
								$('#checkbox-1').click(function() {
									if ($('#checkbox-1')[0].checked) {
										$('.tx-3,.qu_fu_kuan').show();
										$(".pay_type_box,.zhifu_09").hide();
										$(".balance_pay_pwd_input").val("");
									} else {
										$('.tx-3').hide();
										$(".pay_type_box,.zhifu_09").show();
										$(".balance_pay_pwd_input").val("");
									}
								});
								
								//确定支付密码
								function pwdSuccess() {
									var pwd = $('.input-border').val();
									if(pwd==""||pwd==null){
										alert("不能为空！");
										return false;
									}
									//alert(pwd + "成功");
									$('.tx-1').hide();
									$('.tx-2').hide();
									$('.tx-3').hide();
									$('.tx-2').show();
									total = parseInt($("#jg").text());//现在总价格
									//赋值
									
									if(total<balanceNum){//实际金额小于钱包余额
										$(".shiji_zhifu_num,#jg").text(0);
										$("#balance_span2").text(total);//钱包支付
										$(".qian_bao_yu_e").text(balanceNum-total);//钱包余额
										$(".pay_type_box").hide();
										//默认让他为线上支付
										$("input[name=frpId]:eq(0)").attr("checked",'checked');
										
										$("#admin_pay_yeePayForm").submit();//提交表单
										//开始弹窗
										$("#background, #content").show();
									    conPosition();
									}else{//余额小于实际支付的金额。全部用钱包余额支付
										$(".shiji_zhifu_num,#jg").text(total-balanceNum);//实际支付XX元
										if ($("input[name='frpId']:checked").val() === "SJ-HDFK") {
											//$("#jg").data("jg",total-balanceNum-5);
											$("#jg").data("jg",total-balanceNum);
										}else{
											$("#jg").data("jg",total-balanceNum);
										}
										
										$("#balance_span2").text(balanceNum);//钱包支付
										$(".qian_bao_yu_e").text(0);//钱包余额
										$(".pay_type_box,.qu_fu_kuan").show();
										//$(".huo_dao_fu_kuan.zhifu_07").hide();//隐藏货到付款
										
									}
								}
								
								function subPay() {//提交整个页面
									$("#admin_pay_yeePayForm").submit();
									//开始弹窗
									$("#background, #content").show();
								    conPosition();
								}
								
								$(function(){
									$(".balance_pay_pwd_input").val("");//加载完页面清空支付密码//防止操蛋火狐浏览器
									if("${order.walletPayNo}"==""){
										$.ajax({
											type: "get",
											url : "qb/balance.html",
											dataType:'json',
											success: function(d){
												 if(d>0){
													balanceNum = d;
													$("#balance_span").text(d);
													$("#balance_box_div").show();//关闭钱包
												} 
											}
										});
									}
									
									
								    
								    $("#pay_success").click(function() {window.location.href="order/detail.html?id=${order.id}";});
								    $("#pay_error").click(function() {window.location.href="myCenter/index.html";});
								    //点击黑色背景隐藏弹出层，当然可以绑定在任意一个按钮上
								    //$(window).resize(function() {conPosition();});
									
									
								});
								
								//弹窗
								function conPosition() {
							        var oBackground = document.getElementById("background");
							        var dw = $(document).width();
							        var dh = $(document).height();
							        oBackground.style.width = dw+'px';
							        oBackground.style.height = dh+'px';
							        var oContent = document.getElementById("content");
							        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
							        var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
							        var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 3) + scrollTop;
							        oContent.style.left = l + 'px';
							        oContent.style.top = t + 'px';
							    }
								   
								
								//弹框询问是否退出
								function logout(){
							        if(confirm("支付是否完成？")){
						               location.href="http://www.3j1688.com/order/detail.html?id=${order.id}";
							        }else{
							           location.href="http://www.3j1688.com/myCenter/index.html";
							        }
								}
								
							</script>
						</div>
					</div>	
				<div class="zhifu_02 pay_type_box">
					<div class="pay_type_box">
						<div class="zhifu_03">网上支付</div>
						<div class="zhifu_04">
							<%-- <s:if test="%{order.totalCost>2000 }">
									<span style="float: left; font-size: 18px; padding-left: 10px;">线上支付，立减五元(每天限一单)。</span>
							</s:if> --%>
							<a href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a>
						</div>
						<div class="clear"></div>
					</div>
					<jsp:include page="yeePay.jsp"></jsp:include>

					<div class="zhifu_08 pay_type_box">
						
						<!-- 判断是否使用了钱包支付 -->
						<c:if test="${!empty order.walletPayNo}">
							<span>钱包已支付：</span>
							<h3><span>${order.walletNum}</span></h3>元
						</c:if>
						<!-- 判断是否使用了红包支付 -->
						<c:if test="${!empty order.hbNo}">
							<span>红包已支付：</span>
							<h3><span>${order.hbNum}</span></h3>元
						</c:if>
						
						<c:if test="${!empty order.walletPayNo || !empty order.hbNo }">
							&nbsp;&nbsp;&nbsp;还需
						</c:if>
							支付：
						<c:set var="totalNum" value="${order.totalCost}"></c:set><!-- 订单总价格 -->
						
						<%-- <c:if test="${ !empty order.hbNo }"><!-- 如果使用了红包那么就总金额减去红包的钱数 -->
							<c:set var="totalNum" value="${order.totalCost - order.hbNum}"></c:set>
						</c:if> --%>
						
						<c:if test="${!empty order.actualPayNum && 0 != order.actualPayNum}"><!-- 实际支付不等于空并且实际支付不等于零 -->
							<c:set var="totalNum" value="${order.actualPayNum}"></c:set>
						</c:if> 
						
						<h3>
							<%-- <s:if test="order.totalCost>2000 ">
							
							<c:if test="${json.success == true }">
								<s:if test='%{"线上支付立减5元" eq order.remark}'>
									<span id="jg">${totalNum}</span>
									<span id="j5y">(已优惠5元)</span>
								</s:if>
								<s:if test="%{'线上支付立减5元' != order.remark}">
									<span id="jg">${totalNum-5}</span>
									<span id="j5y">(已优惠5元)</span>
								</s:if>
							</c:if>
							<c:if test="${json.success==false}">
								<span id="jg">${totalNum}</span>
							</c:if>
							</s:if>
							<s:if test="%{order.totalCost<2000 }"> --%>
								<span id="jg">${totalNum}</span>
							<%-- </s:if> --%>
						</h3>
						元
					</div>
<%-- 
					<s:if test="order.totalCost>2000 ">
					
						<script type="text/javascript">
							/* 线上支付立减5元hdfk */
							$("input[name='frpId']").change(function() {	
								var s="${json.success==true}";//是否可以减
								var value = $(this).val();
								var $jg = $("#jg");//当前需要支付的价格

								var jg = $jg.data('jg');
								
								if (!jg) {
									
									if(s==="true"){	
										jg = parseFloat($jg.text());
										$jg.data("jg", jg);
									}
									
								}
								
								if (value === "SJ-HDFK") {
									if(s==="true"){
										$("#j5y").text("");
										$("#jg,.shiji_zhifu_num").text(jg+5); 
										
									}else{
										$("#j5y").text("");
										$("#jg,.shiji_zhifu_num").text(jg);
									}
								} else {
									$("#j5y").text("(已优惠5元)");  
									$("#jg,.shiji_zhifu_num").text(jg);
								}
							});
						</script>
					</s:if> --%>

					<%-- <script type="text/javascript">
						if("${order.walletPayNo}"!=""){
							$(".huo_dao_fu_kuan").hide();
						}
					</script> --%>

					<div class="zhifu_09 qu_fu_kuan">
						<a href="javascript:subPay();">付款</a>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!---------第三块产品详情---------->
		</div>
		
		<!--======================bottom开始============================-->
		<jsp:include page="../common/footer.jsp"></jsp:include>
		
		
		<!-- 弹窗询问是否完成支付 -->
		<div id="background"></div>
		<div id="content">
		
			<div dhxbox="1" class="up-box w600" style="position: fixed; left: 35%; top: 30%;" id="content">
				<div class="up-box-hd">网上支付提示</div>
				<div class="up-box-bd">
					<div class="up-con">
						<span class="icon">
							<img src="/images/working.gif" />
						</span>
						<div class="r-txt">
							<p class="ft14">支付完成前，请不要关闭此支付验证窗口。
								<br clear="none" /> 支付完成后，请根据您支付的情况点击下面按钮。
							</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="lay-btn">
						<a href="javascript:" class="btn92" id="pay_error" shape="rect">支付遇到问题</a>
						<a href="javascript:" class="btn92" id="pay_success" shape="rect">支付完成</a>
					</div>
				</div>
			</div>
		
		    <!-- <div style="text-align:center;margin-bottom:20px;">
		    	<dir style="margin-bottom:20px;"><h2>是否支付完成</h2></dir>
		    	<input type="button" id="pay_success" value="支付成功" />&nbsp;&nbsp;<input type="button" id="pay_error" value="支付失败" />
		    </div> -->
		</div>
		
	</form>
	<!-- 判断有没有某个只能线上支付的商品 -->
	<%-- <c:forEach var="item" items="${order.orderItemss}">
		<c:if test="${item.targetId eq '22a2d45faf4644aa98b2a3e721b941f9'}"><!-- 如果订单里面有只支持线上付款的商品那么就把货到付款注释掉 -->
			<script type="text/javascript">
				$(".huo_dao_fu_kuan").hide();
			</script>
		</c:if>
	</c:forEach>
	--%>
	<%-- <c:if test="${hdGoods}"><!-- 如果订单里面有活动商品就关闭货到付款选项 -->
        <script type="text/javascript">
            $(".huo_dao_fu_kuan").remove();
        </script>
    </c:if> --%>
    
    <!-- 关闭线上支付 -->
    <script type="text/javascript">
           // $(".huo_dao_fu_kuan_22").hide();
          //  $("#hdfk").attr("checked","checked");
        </script>
</body>
</html>
