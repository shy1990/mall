<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html>
  <head>
  
    <base href="<%=basePath%>">
    
    <title>手机列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript" >
		
		//var showType = ${paramMap.showType};
		var defaultCatIds = "${paramMap.defaultCatIds}";
		var defaultBrandIds = "${paramMap.defaultBrandIds}";
		var defaultPrices = "${paramMap.defaultPrices}";
		var searchParam =  "${paramMap.searchParam}";
		

		$(function(){
			/**松开键盘的时候进行模糊搜索匹配**/
			$("#phone_list_search_param").keyup(function(){
				//console.info("开始模糊匹配");
				likeSearch($(this).val(),$("#phone_like_search_show_box"),"phone_list_search_param");
			});
		});
		
		//console.info(defaultCatIds+" || "+defaultBrandIds+"  ;; "+defaultPrices+"  :  "+searchParam);
	</script>
    <script type="text/javascript" src="js/listView/phone_list.js"></script>
    <script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
    <script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
  </head>
  <body>
    <!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	
	<!-- 详情开始 -->
	<div class="xq_mian">
  		<div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>列表</h5></div>
  <!---------已采购商品信息---------->
	  <div class="phone_main">
	     <div class="phone_main_01">
	       <div class="phone_main_01_top">
	        <div class="phone_main_01_top_let">全部 &gt; </div>
	        <div class="phone_main_01_top_param">
	        </div>
	        <div class="phone_main_01_top_let">&gt;</div>
	         <div class="phone_main_01_top_fit">
	          <div class="phone_main_01_top_fit_let">
	          	<input onkeydown="keyDownSearch(event)" id="phone_list_search_param" name="" type="text" value="在当前条件下搜索" onmouseover="this.focus()" onmouseout="if(this.value=='')this.value='在当前条件下搜索';" onfocus="this.select()" onclick="if(this.value=='在当前条件下搜索')this.value=''" />
	          	<div id="phone_like_search_show_box"></div>
	          </div>
	          <div class="phone_main_01_top_fit_let"><a href="javascript:pSearchParam();"><img src="${pageContext.request.contextPath}/images/phone_input.jpg" width="20" height="19" alt=""/></a></div>
	          <div class="clear"></div> 
	         </div>
	         <div class="phone_main_01_top_ret">共 <h5>0</h5>件相关商品</div>
	        <div class="clear"></div>
	       </div> 
	       <div> 
	       	 <%-- <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">类型</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	              	 <c:if test="${paramMap.cats != null}">
	              		<c:forEach var="cat" items="${paramMap.cats}" >
	              			<li><a href="javascript:;" paramVal="${cat.id }" type="cat">${cat.name }</a></li>
	              		</c:forEach>
	              	</c:if> 
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <!-- <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="catIds">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div> --%>
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">品牌</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	              	<c:if test="${paramMap.brands!=null}">
	              		<c:forEach var="brand" items="${paramMap.brands }" >
	              			<li><a href="javascript:;" paramVal="${brand.id }">${brand.name }</a></li>
	              		</c:forEach>
	              	</c:if>
	               </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a>
	                <!-- <a href="#">更多v </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="brandIds">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">价格</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	              	 <li><a href="javascript:;" paramVal="0-500">0-500</a></li>
	                 <li><a href="javascript:;" paramVal="500-1000">500-1000</a></li>
	                 <li><a href="javascript:;" paramVal="1000-1500">1000-1500</a></li>
	                 <li><a href="javascript:;" paramVal="1500-2000">1500-2000</a></li>
	                 <li><a href="javascript:;" paramVal="2000-2500">2000-2500</a></li>
	                 <li><a href="javascript:;" paramVal="2500-3000">2500-3000</a></li>
	                 <li><a href="javascript:;" paramVal="3000-3500">3000-3500</a></li>
	                 <li><a href="javascript:;" paramVal="3500-4500">3500-4500</a></li>
	                 <li><a href="javascript:;" paramVal="4500">4500以上</a></li>
	                 <li>
	                 	<input type="text" class="phone_list_price" id="phone_list_price_01" title="最低价" style="width:40px;" value="" >
	                 	-
	                 	<input type="text" class="phone_list_price" id="phone_list_price_02" title="最高价" style="width:40px;" value="" >
	                 	<input type="button" id="phone_list_price_but" paramVal="" value="确定" style="cursor:pointer;" disabled="true" /> 
	                 	<!-- <a href="javascript:;" paramVal="4500">确定</a> -->
	                 </li>
	                 
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <!-- <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a> -->
	                <!-- <a href="#">更多v </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="prices">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">屏幕尺寸</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	                 <li><a href="javascript:;" paramVal="100-5.6">5.6英寸及以上</a></li>
	                 <li><a href="javascript:;" paramVal="5.5-5.0">5.5-5.0英寸</a></li>
	                 <li><a href="javascript:;" paramVal="4.9-1.1">4.9-4.1英寸</a></li>
	                 <li><a href="javascript:;" paramVal="4.0-3.1">4.0-3.1英寸</a></li>
	                 <li><a href="javascript:;" paramVal="3.0-0">3.0英寸及以下</a></li>
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	              <!-- <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="screenSize">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">网络类型</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	                 <li><a href="javascript:;">TD-LTE</a></li>
	                 <li><a href="javascript:;">FDD-LTE</a></li>
	                 <li><a href="javascript:;">TD-SCDMA</a></li>
	                 <li><a href="javascript:;">WCDMA</a></li>
	                 <li><a href="javascript:;">CDMA2000</a></li>
	                 <li><a href="javascript:;">GSM</a></li>
	                 <li><a href="javascript:;">CDMA</a></li> 
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a>
	                <!-- <a href="#">更多v </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="netSuitTypes">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">运行内存</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	                 <li><a href="javascript:;">3GB</a></li>
	                 <li><a href="javascript:;">2GB</a></li>
	                 <li><a href="javascript:;">1GB</a></li>
	                 <li><a href="javascript:;">768MB</a></li>
	                 <li><a href="javascript:;">512MB</a></li>
	                 <li><a href="javascript:;">256MB</a></li>
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a>
	                <!-- <a href="#">更多v </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="rams">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">分辨率</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	                 <li><a href="javascript:;">2K</a></li>
	                 <li><a href="javascript:;">1080p</a></li>
	                 <li><a href="javascript:;">720p</a></li>
	                 <li><a href="javascript:;">960x540像素</a></li>
	                 <li><a href="javascript:;">854x480像素</a></li>
	                 <li><a href="javascript:;">800x480像素</a></li>
	                 <li><a href="javascript:;">480x320像素</a></li>
	                 <li><a href="javascript:;">320x240像素</a></li>
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a>
	                <!-- <a href="#">更多v </a> -->
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="resolutions">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div> 
	            <div class="clear"></div>
	         </div>
	         <div class="phone_main_01_txt">
	            <div class="phone_main_01_txt_let">操作系统</div>
	            <div class="phone_main_01_txt_ret">
	              <ul class="phone_main_01_txt_ret_01">
	                 <li><a href="javascript:;">Android5.0</a></li>
	                 <li><a href="javascript:;">Android4.4</a></li>
	                 <li><a href="javascript:;">Android4.3</a></li>
	                 <li><a href="javascript:;">Android4.2</a></li>
	                 <li><a href="javascript:;">Android4.1</a></li>
	                 <li><a href="javascript:;">iOS8</a></li>
	                 <li><a href="javascript:;">iOS7</a></li>
	                 <li><a href="javascript:;">iOS6</a></li>
	                 <li><a href="javascript:;">Windows phone 8.1</a></li>
	              </ul>
	              <div class="phone_main_01_txt_ret_02">
	                <a class="phone_main_01_txt_ret_03" href="javascript:">+多选 </a>
	              </div>
	              <div class="clear"></div>
	              <div class="v-btns">
					 <a href="javascript:;" class="btn-red" key="operationSystems">确定</a>
					 <a href="javascript:;" class="btn-gray">取消</a>
				  </div>
	            </div>
	            <div class="clear"></div>
	         </div>
	         
	       </div>
	     </div>
	    </div>
    <!---------第三块产品详情---------->
    <div class="phone_main_02">
      <div class="ny_box_let_che_01">
          <div class="ny_box_let_che_01_let">
            <ul>
               <li class="sortSelected"><a style="color:#F30" href="javascript:;" sort="g.CREATE_TIME" order="desc" >综合</a></li>
              <!-- <li><a href="javascript:;" sort="g.CLICK_RATE" order="desc">人气</a></li> -->
              <li><a href="javascript:;" sort="g.CREATE_TIME" order="desc">新品</a></li>
              <!-- <li><a href="javascript:;" sort="buyNums" order="desc">销量</a></li>  -->
              <li><a href="javascript:;" sort="g.price" order="desc">价格</a></li>
              <!-- <div class="clear"></div> -->
            </ul>
          </div>
          <div class="ny_box_let_che_01_ret">
             <%-- <div class="ny_box_let_che_01_ret_01"><a href="#">1</a>/100</div>
             <div class="ny_box_let_che_01_ret_02">
               <span><a href="#"><img src="${pageContext.request.contextPath}/images/maiche_let.jpg" width="24" height="24" align="top"></a></span>
               <span><a href="#"><img src="${pageContext.request.contextPath}/images/maiche_ret.jpg" width="24" height="24" align="top"></a></span>
             </div> --%>
             <div class="clear"></div>
            </div>
          <div class="clear"></div>
        </div>
    </div>
    <!--产品列表-->
    <div class="phone_main_03">
      <ul>       
      </ul>
      <div class="clear"></div>
    </div>
    <div class="phone_main_07">
    <div class="fenye">
    <ul id="phone_list_page_ul">
      
    </ul>
  </div>
  </div>  </div>
	
	<jsp:include page="/admin/common/footer.jsp"></jsp:include>

  </body>
</html>
