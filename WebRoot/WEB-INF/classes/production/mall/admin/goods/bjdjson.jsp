<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="application/javascript; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

{"success":true,"data":{
<c:forEach var="item" varStatus="status" items="${bjd }">
	<c:forEach var="map"  items="${item }">
		"${map.key}":[
			<c:forEach varStatus="s" var="i" items="${map.value }">
					{
					 "goodsNum":"${i['GOODS_NUM']}",
					 "goodsName":"${i['GOODS_NAME']}",
					 "price":${i['PRICE']},
					 "isNew":${i['IS_NEW']},
					 "isHot":${i['IS_QUOTATION_HOT']},
					 "isCollected":${i['COLLECTED']}
					}<c:if test="${!s.last}">,</c:if>
		</c:forEach>
		]
	</c:forEach>
	<c:if test="${!status.last}">,</c:if>
</c:forEach>
}}
