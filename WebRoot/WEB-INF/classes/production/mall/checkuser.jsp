<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<% 
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0);
%>

<c:if test="${exsit}">
	<img src="images/huiyuan_dui.png" width="15" height="14" align="left" />店铺名重复
</c:if>
<c:if test="${!exsit}">
	<img src="images/huiyuan_sha.png"align="left" />
</c:if>

 