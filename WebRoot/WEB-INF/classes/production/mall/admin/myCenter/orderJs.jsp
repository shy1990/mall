<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 订单通用js方法 -->
<script type="text/javascript" charset="utf-8">
function toPay(orderId){
	 if (confirm("确定退款退货？")) {
     	var form = $('<form id="js_submit_form" action="yeePay/toPay.html" method="post" target="_self" style="display:none"></form>');  
	    var orderIdInput = '<input type="text" name="orderId" value="'+orderId+'"/>';
	    var sub = '<input type="submit" />';
	    form.append(orderIdInput+sub);
	    $("body").append(form);
	    //form.submit().click();
	    document.getElementById("js_submit_form").submit();
     }
};

//取消订单
function concelOrder(orderId,orderNum,oItemsId){
	//if (confirm("确定退款退货？")) {
		var form = $('<form id="js_submit_form2" action="th/skip.html" method="post" target="_self" style="display:none"></form>');  
	    var orderIdInput = '<input type="text" name="order.id" value="'+orderId+'"/>';
	    var orderNumInput = '<input type="text" name="orderNum" value="'+orderNum+'"/>';
	    var oItemsIdInput = '<input type="text" name="order.itemsId" value="'+oItemsId+'"/>';
	    var sub = '<input type="submit" />';
	    form.append(orderIdInput+orderNumInput+oItemsIdInput+sub); 
	    $("body").append(form);
	    document.getElementById("js_submit_form2").submit();
    //}
}

//取消订单详情 
function thDetail(orderId,orderNum){
	var form = $('<form id="js_submit_form2" action="th/detail.html" method="post" target="_self" style="display:none"></form>');  
    var orderIdInput = '<input type="text" name="order.id" value="'+orderId+'"/>';
    var orderNumInput = '<input type="text" name="order.orderNum" value="'+orderNum+'"/>';
    var sub = '<input type="submit" />';
    form.append(orderIdInput+orderNumInput+sub);
    $("body").append(form);
    document.getElementById("js_submit_form2").submit();
}

//查看发货状态
function checkShipStatus(obj,orderId){
	$.ajax({
		type: "post",
		url : "order/checkShipStatus.html",
		dataType:'json', 
		data: {'id':orderId}, 
		success: function(d){
		
			//console.info(d);
		
			if(d.obj==0){//系统异常
				alert(d.msg);
			}
			
			if(d.obj==1){//未发货
				$(obj).removeAttr("onclick");
				$(obj).html(d.msg).css("color","#666");
				//alert(d.msg);
			}
			
			if(d.obj==2){//卖家已发货
				var htmlstr = '卖家已发货<br><a href="javascript:orderDetail("'+orderId+'");">查看物流</a><br><a href="javascript:orderDetail("'+orderId+'");">订单详情</a>';
				
				$(obj).parent().next().prepend('<a href="javascript:;">确认收货</a><br/>');
				//提示文字改为  卖家已发货
				$(obj).parent().html(htmlstr); 
				
			}
		}
	});
}


//确认收货
function affirmGoods(obj,orderId){
	$.ajax({
		type: "post",
		url : "order/affirmGoods.html",
		dataType:'json', 
		data: {'id':orderId}, 
		success: function(d){
			//console.info(d);
			if(d.obj==0){//系统异常
				alert(d.msg);
			} 
			if(d.obj==1){//未发货
				location.reload(); 
				/* var payType = $(obj).parent().prev().find("#payType").html();
				$(obj).parent().prev().html("");
				$(obj).parent().prev().prepend(' 交易成功<br><a href="javascript:orderDetail("'+orderId+'");">订单详情</a><br/><span id="payType">'+payType+'</span>');
				//提示文字改为  卖家已发货
				
				$(obj).attr("href","javascript:goEvaluate('"+orderId+"');");
				$(obj).text("添加评论");
				$(obj).parent().find("a").eq(1).remove();
				$(obj).parent().find("br").eq(1).remove(); */
			}
		}
	});
}

</script>