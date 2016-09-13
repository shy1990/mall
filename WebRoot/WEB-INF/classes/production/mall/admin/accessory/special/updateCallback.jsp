<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="application/javascript; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

var input=$("input[name='skuId-${item.goods.id}']");
		<%--更新数量--%>
		input.val(${item.quantity }); 
		$(".skuGift-${item.goods.id}").each(function(){
			$(this).val(${item.quantity });
		});
		<%--更新单价--%>
		input.attr("data-price",${item.singlePrice });
		<%--通知标志--%>
		var notify=!input.attr("data-notify");
		<c:if test="${item.quantity>item.goods.changeNum }">
			if(notify){
				alert('为防止恶意炒货，您对本型号手机的采购数量已达到当天上限，如继续购买单价会统一上浮，敬请留意');
				<%--通知标志为已通知--%>
				input.attr("data-notify",true);
			}
		</c:if>
		<c:if test="${item.quantity<=item.goods.changeNum }">
			<%--通知标志为未通知--%>
				input.removeAttr("data-notify");
		</c:if>
		