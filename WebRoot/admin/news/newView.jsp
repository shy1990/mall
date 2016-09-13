<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<base href="<%=basePath%>"/> 
	<title>三际手机采购网</title>
	
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="${news.keywords }" />
	<meta http-equiv="description" content="${news.description}" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form>
  
	<jsp:include page="../common/head.jsp"></jsp:include>
	<!--======================详情图片开始============================-->
	<div class="xq_mian">
	  <div class="xq_mian_top"><!-- span><a href="#">返回</a></span-->当前位置&gt;&gt;<a href="#">手机资讯</a>&gt;&gt;</div>
	  <div class="new_main">
	    <div class="new_let">
	      <div class="new_let_top">
	        <p>${news.firstTitle }</p>
	        <p><span>文章来源：${news.resources }</span>&nbsp;<span>发布时间：<s:date name="news.createtime" />
					</span><span>发布人：${news.author }</span><span>浏览次数：${news.viewnum }</span></p>
			
	      </div>
	      	
	      <div class="new_let_txt">
		   <div class="new_let_txt_01"><strong>内容摘要：</strong><br>
				<p><span>${news.secondTitle }</span>
			</p></div>
	        ${news.content }
	      </div>
	    </div>
	    <div class="new_ret">
	      <div class="wodz_ret_01_let_top">
	           <div class="wodz_ret_01_let_top_let">热点新闻</div>
	           <div class="clear"></div>
	     </div>
	     <div class="new_ret_01">
	       <ul >
	         <s:iterator value="#request.newsList" var="new" status="status">
	            <li style="width: 200px;white-space: nowrap;overflow: hidden;text-overflow:ellipsis;"><a href="nwes/view/${new.id}.html">${new.firstTitle}</a></li>
	         </s:iterator>
	       </ul>
	     </div>
	    </div>
	    <div class="clear"></div>
	  </div>
	</div>
	<!--======================bottomå¼å§============================-->
	<jsp:include page="../common/footer.jsp"></jsp:include>
</form>
</body>
</html>
