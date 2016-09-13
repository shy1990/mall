<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="application/javascript; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach var="item" items="${shoppingCart.items }">
	<%--手机--%>
	<c:if test='${item.type eq "GoodsSku" and item.id == cartId }'>
		var input=$("#quantity-${cartId}");
		<%--更新数量--%>
		input.val(${item.quantity }); 
		
		<%--更新单价--%>
		$("#price-${cartId }").html("${item.singlePrice }");
		<%--更新赠品信息--%>
		$(".gift_quantity-${cartId}").val(${item.quantity });
		$(".gift_subTotalPrice-${item.id }").html((${item.quantity}*0.1).toFixed(2));
		<%--通知标志--%>
		var notify=!input.attr("data-notify");
		<%-- <c:if test="${item.quantity>item.goods.changeNum }">
			if(notify){
				alert('为防止恶意炒货，您对本型号手机的采购数量已达到当天上限，如继续购买单价会统一上浮，敬请留意');
				通知标志为已通知
				input.attr("data-notify",true);
			}
		</c:if>
		<c:if test="${item.quantity<=item.goods.changeNum }">
			通知标志为未通知
				input.removeAttr("data-notify");
		</c:if> --%>
	</c:if>
	
	<%--配件--%>
	<c:if test='${item.type eq "Accessory" and item.id == cartId }'>
		var input=$("#quantity-${cartId}");
		<%--更新数量--%>
		input.val(${item.quantity }); 
	</c:if>
	<%--更新小计--%>
	$("#subTotalPrice-${item.id }").html(${item.subTotalPrice});
	
</c:forEach>
