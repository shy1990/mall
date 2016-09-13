<%@page import="com.sanji.mall.common.util.ResourceUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	Random random = new Random();
	int fp0500 = random.nextInt(5) % (5 - 3 + 1) + 3;
	int fp500800 = random.nextInt(9) % (9 - 5 + 1) + 5;
	int fp8001000 = random.nextInt(10) % (10 - 6 + 1) + 6;
	int fp10001500 = random.nextInt(9) % (9 - 5 + 1) + 5;
	int fp15001800 = random.nextInt(5) % (5 - 3 + 1) + 3;
	int fp18002200 = random.nextInt(7) % (7 - 4 + 1) + 4;
	int fp22002500 = random.nextInt(8) % (8 - 6 + 1) + 6;
	int fp25002800 = random.nextInt(10) % (10 - 5 + 1) + 5;
	int fp280012000 = random.nextInt(11) % (11 - 7 + 1) + 7;
	
	request.setAttribute("fp0500", fp0500);
	request.setAttribute("fp500800", fp500800);
	request.setAttribute("fp8001000", fp8001000);
	request.setAttribute("fp10001500", fp10001500);
	request.setAttribute("fp15001800", fp15001800);
	request.setAttribute("fp18002200", fp18002200);
	request.setAttribute("fp22002500", fp22002500);
	request.setAttribute("fp25002800", fp25002800);
	request.setAttribute("fp280012000", fp280012000);
	
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>商品详情</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.json-2.4.min.js"></script>
	
	<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
	<script type="text/javascript">
		String.prototype.trim = function () {
			var str = this ,
			str = str.replace(/^\s /, '' );
			for ( var i = str.length - 1; i >= 0; i--) {
				if (/\S/.test(str.charAt(i))) {
					str = str.substring(0, i , 1);
					break ;
				}
			}
			return str;
		};
	var goodsInfoObj = ${goods.goodsJsonStr};
	//console.info(goodsInfoObj);
	var postagePrice = <%= ResourceUtil.getPostagePrice()%>;//邮费价格
	var mobilePhoneRootPrice = <%= ResourceUtil.getMobilePhoneRootPrice()%>;//手机root服务价格
	</script>  
	<script type="text/javascript" src="js/goodsDetail.js"></script>
</head>

<body>

<!-- 左边快捷按钮 -->
	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
<!--======================详情图片开始============================-->
<div class="xq_mian" id="xq_mian">
  <div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>商品详情</h5> </div>
  <!---------第一块基本信息---------->
  <div class="xq_main_01">
    <div class="xq_main_01_jage">
      <div class="xq_main_01_jage_let">
        <div class="xq_main_01_jage_let_01">${goods.brand.name }：${goods.name}</div>
        	<c:set var="minPrice" value="0" />
        	<c:set var="maxPrices" value="0" />
        	<c:set var="goodsColorStr" value="" /><!-- 去重复后不同颜色的单品 -->
        	<c:set var="goodsColorImgId" value="" /><!-- 去重复后不同颜色的单品id字符串 -->
        	<c:set var="goodsStorageStr" value="" /><!-- 去重复后所有单品的机身内存大小 -->
        	<c:set var="netSuitTypeStr" value="" />
        	
	        <c:if test="${!empty goods.goodsSkus}">
			    <c:forEach var="sku" items="${goods.goodsSkus }">
			    	<c:if test="${minPrice <= 0}">
			    		<c:set var="minPrice" value="${sku.price}"/>
			    	</c:if>
		            <c:if test="${minPrice > sku.price}">
		            	<c:set var="minPrice" value="${sku.price}" />
		            </c:if>
		            <c:if test="${maxPrices lt sku.price}">
		            	<c:set var="maxPrices" value="${sku.price}" />
		            </c:if>
		            <c:if test="${goodsColorStr==''}">
		            	<c:set var="goodsColorStr" value="${sku.color.colorName }" />
		            	<c:set var="goodsColorImgId" value="${sku.id }" />
		            </c:if>
		            <c:if test="${!fn:contains(goodsColorStr,sku.color.colorName)}">
		            	<c:set var="goodsColorStr" value="${goodsColorStr} / ${sku.color.colorName }"/>
		            	<c:set var="goodsColorImgId" value="${goodsColorImgId},${sku.id }"/>
		            </c:if>
		            
		            <c:if test="${goodsStorageStr==''}"><c:set var="goodsStorageStr" value="${sku.storage }" /></c:if>
		            <c:if test="${!fn:contains(goodsStorageStr,sku.storage)}"><c:set var="goodsStorageStr" value="${goodsStorageStr} / ${sku.storage }" /></c:if>
		            
		            <c:if test="${netSuitTypeStr==''}"><c:set var="netSuitTypeStr" value="${sku.netSuitType }" /></c:if>
		            <c:if test="${!fn:contains(netSuitTypeStr,sku.netSuitType)}"><c:set var="netSuitTypeStr" value="${netSuitTypeStr} ${sku.netSuitType }" /></c:if>
			    </c:forEach>
	   	    </c:if> 
        <div class="xq_main_01_jage_let_02">￥<fmt:formatNumber value="${minPrice}" type="number" maxFractionDigits="2" minFractionDigits="2"/>-<fmt:formatNumber value="${maxPrices}" type="number" maxFractionDigits="2" minFractionDigits="2"/></div>
        <div class="xq_main_01_jage_let_03"><span> 送积分：<script type="text/javascript">document.write(Math.floor("${minPrice/10}"))</script>起</span>已成交：${goodsDealCountNum }              </div>
      </div>
      <div class="xq_main_01_jage_mid">
        <div class="xq_main_01_jage_mid_01">适应网络：</div>
        <div>${netSuitTypeStr}</div>
      </div>
      <div class="xq_main_01_jage_ret">
      <ul>
        <li><span>屏幕尺寸：</span>${goods.screenSize}英寸</li>         
       <li><span>运行内存：</span>${goods.ram}</li> 
       <li><span>CPU核心数：</span>${goods.cpuNumber}</li> 
       <li id="xq_main_01_jage_ret"><span>前置摄像头：</span>${goods.frontCamera}</li> 
       </ul>
      </div>
      <div class="xq_main_01_jage_ret">
      <ul>
       <li><span>分辨率：</span>${goods.resolution}</li>         
       <li title="${goodsStorageStr}"><span>机身内存：</span>
       	   <c:if test="${fn:length(goodsStorageStr)>9}">
	       	  ${fn:substring(goodsStorageStr, 0, 9)}...
	       </c:if>
	       <c:if test="${fn:length(goodsStorageStr)<=9}">
	       	  ${goodsStorageStr}
	       </c:if> 
       	
       
       </li> 
       <li title="${goods.cpuRate}"><span>CPU频率：</span>
	       <c:if test="${fn:length(goods.cpuRate)>6}">
	       	  ${fn:substring(goods.cpuRate, 0, 6)}...
	       </c:if>
	       <c:if test="${fn:length(goods.cpuRate)<=6}">
	       	  ${goods.cpuRate}
	       </c:if> 
       </li> 
       <li id="xq_main_01_jage_ret"><span>后置摄像头：</span>${goods.postCamera }</li> 
       </ul>
      </div>
      <div class="clear"></div>
    </div>
    <div class="xq_main_01_mc"><span>机身颜色：</span>${goodsColorStr}</div>
    <div class="xq_main_01_ys">
      <ul>
      	<c:if test="${!empty goods.goodsSkus}">
		    <c:forEach var="sku" items="${goods.goodsSkus }">
	            <c:if test="${fn:contains(goodsColorImgId,sku.id)}">
	            	<li>
			          <div><img src="${sku.goodsPics[0].picSrc }" width="207" height="207" /></div>
			          <div>${sku.color.colorName }      <!-- 此颜色占采购比<span>71%</span> --></div>
			        </li>
	            	<c:set var="goodsColorImgId" value="${goodsColorImgId},${sku.id }"/>
	            </c:if>
		    </c:forEach>
   	    </c:if> 
        
      </ul>
      <div class="clear"></div>
    </div>
  </div>
  <!---------第二块采购说明---------->
  <div class="xq_main_02">
    <div class="xq_main_01_mc"><!-- <span>采购运费说明：</span>价低利薄，当天采购总量（不限型号）等于或超过3台免运费，3台以下加收10元运费  --></div>
    <div class="xq_main_02_gai"><!---------采购说明右侧开始---------->
      <div class="xq_main_02_let">
        <div class="xq_main_02_let_01">
          <table style="height: 45px; text-align: center; " border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="38" align="center" valign="middle"><!-- <img src="../images/xq_xihu.jpg" width="27" height="22" /> --></td>
              <td width="83">版本</td>
              <td width="97"> 制式</td>
              <td width="87">颜色</td>
              <td width="97">CPU</td>
              <td width="93">内存</td>
              <td width="141">单价/元</td>
              <td width="87">采购量</td>
              <!-- <td width="128">三际天猫官方价</td> -->
              <td width="80">库存</td>
            </tr>
          </table>
        </div>
        <div class="xq_main_02_let_03">
          <ul>
	          <c:if test="${!empty goods.goodsSkus}">
			    <c:forEach var="sku" items="${goods.goodsSkus }">
			      <li>
		            <div class="xq_main_02_let_02">
		              <table style="height: 32px; text-align: center;" border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td width="37" align="center">
		                  	<c:if test="${!empty sku.collectId}">
					  			<img class="xq_main_click_collect" src="../images/hart_01.jpg" width="20" height="14" align="middle" style="cursor: pointer;" state="1" targetId="${sku.id}" type="sku" />
					  		</c:if>
					  		<c:if test="${empty sku.collectId}">
					  			<img class="xq_main_click_collect" src="../images/hart.jpg" width="20" height="14" align="middle" style="cursor: pointer;" state="2" targetId="${sku.id}" type="sku" />
					  		</c:if>
		                  </td>
		                  <td width="82">${sku.edition}</td>
		                  <td width="98">${sku.standard}</td>
		                  <td width="89">${sku.color.colorName }</td>
		                  <td width="96">${goods.cpuNumber}</td> 
		                  <td width="92">${goods.ram} + ${sku.storage}</td>
		                  <td width="142"><h4>￥<span id="goods_change_price_span_${sku.skuNum}">
		                  <%-- 设置价格浮动 --%>
		                  	<c:if test="${sessionInfo.loginName == '德州市德城区黑马通讯'}">
				        		<c:if test="${sku.price>0 && sku.price<=500}">
				        			${sku.price+fp0500}
				        		</c:if>
				        		<c:if test="${sku.price>500 && sku.price<=800}">
				        			${sku.price+fp500800}
				        		</c:if>
				        		<c:if test="${sku.price>800 && sku.price<=1000}">
				        			${sku.price+fp8001000}
				        		</c:if>
				        		<c:if test="${sku.price>1000 && sku.price<=1500}">
				        			${sku.price+fp10001500}
				        		</c:if>
				        		<c:if test="${sku.price>1500 && sku.price<=1800}">
				        			${sku.price+fp15001800}
				        		</c:if>
				        		<c:if test="${sku.price>1800 && sku.price<=2200}">
				        			${sku.price+fp18002200}
				        		</c:if>
				        		<c:if test="${sku.price>2200 && sku.price<=2500}">
				        			${sku.price+fp22002500}
				        		</c:if>
				        		<c:if test="${sku.price>2500 && sku.price<=2800}">
				        			${sku.price+fp25002800}
				        		</c:if>
				        		<c:if test="${sku.price>2800 && sku.price<=12000}">
				        			${sku.price+fp280012000}
				        		</c:if>
				        	</c:if>
				        	<c:if test="${sessionInfo.loginName != '德州市德城区黑马通讯'}">
				        		${sku.price}
				        	</c:if>
				        	
		                  <%-- ++${sku.price} --%>
		                  
		                  </span><span id="goods_price_xieXian_${sku.skuNum}" style="display:none;">/</span><span id="goods_changed_price_span_${sku.skuNum}" style=" display:none;"><%-- ${sku.originalPrice} --%></span></h4></td>
		                  <td width="110"><div id="xq_main_02_let_03">
		                    <div id="xq_main_02_let_03_let" class="xq_main_02_let_03_let_del"><img src="../images/jian.jpg" width="20" height="20" /></a></div>
		                    <div id="xq_main_02_let_03_ret"><input class="${sku.skuNum}" data-stock="${sku.stock }" name="" type="text" value="0"  onMouseOver="this.focus()" onMouseOut="if(this.value=='')this.value='0';" onFocus="this.select()" onClick="if(this.value=='0')this.value=''"  /></div>
		                    <div id="xq_main_02_let_03_let" class="xq_main_02_let_03_let_add"><img src="../images/jia.jpg" width="20" height="20" /></div>
		                  </div></td>
		                  <%-- <td width="121" align="center"><a target="_Blank" href="${goods.tmallUrl}">￥${sku.tmallPrice}</a></td> --%>
		                  <td width="35" style="color:red;">
		                  	<c:if test="${sku.stock > 5}">有货</c:if>
		                  	<c:if test="${sku.stock <= 5 and sku.stock>0}">${sku.stock}</c:if>
		                  	<c:if test="${sku.stock <= 0}">缺货</c:if>
		                  </td>
		                </tr> 
		              </table>
		            </div>
		           </li>
			     </c:forEach>
	    	  </c:if>
          </ul>
        </div>
      </div><!---------采购说明右侧结束---------->
      <div class="xq_main_02_ret"><!---------采购说明左侧开始---------->
        <div class="xq_main_02_let_01">
          <table style="height: 45px;" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="183" align="center">版本/制式/颜色</td>
              <td width="117">采购量</td>
              <!-- <td width="108"><div class="xq_main_02_fd">
                <div class="xq_main_02_fd_01"><img src="../images/zhuan.jpg" width="20" height="19" /></div>
                <div class="xq_main_02_fd_02">10元/台</div>
Root服务</div></td> -->
            </tr>
          </table>
        </div>
        <div class="xq_main_02_let_03">
          <ul>
           
          </ul>
        </div>
        <div class="xq_main_02_ret_01">
          <p><!-- 非Root手机： -->共<span>0</span>部 采购金额<span>0.00</span>元</p>
          <!-- <p>Root手机&nbsp;&nbsp;&nbsp;：共<span>0</span>部 采购金额<span>0.00</span>元</p> -->
        </div>
        <div class="xq_main_02_ret_02">共<span>0</span>部<span>0.00</span>元</div>
        <div class="xq_main_02_ret_03">
        	<form id="go_choose_parts" method="post" action="accessory/special.html">
        	<input name="param" type="hidden" value="" />
        		<a href="javascript:chooseDedicatedParts();">下一步（选购专用配件）</a>
        	</form> 
        </div>
      </div><!---------采购说明左侧结束---------->
      <div class="clear"></div>
    </div>
  </div>
  <!---------第三块产品详情---------->
  <div class="xq_main_03">
    <div class="xq_main_03_top">
      <ul style="margin-left: 220px;" >
        <li class="selecter"><a href="javascript:void(0)">商品详情</a></li>
        <li id="" ><a href="javascript:void(0);">包装和参数</a></li>
        <!-- <li id="" style="display:none" ><a href="javascript:;">用户口碑</a></li> -->
         <li id="" ><a href="javaScript:;">累计评价（<span id="goods_pingjia_num_span"></span>）</a></li> 
         <li id="" ><a href="javascript:;">月成交记录（${goodsDealNum }）</a></li> 
        <li id="" ><a href="javascript:;">服务详情</a></li>
      </ul>
        <div class="clear"></div>
    </div>
  </div>
  <!-- 商品详情内容 -->
  <div class="xq_main_04 selecter goods_main_contents" id="" >
  	<div class="xq_main_03_txt"><%-- 品牌名称：<span>${goods.brand.name }</span> --%>
  		<!--收藏goods功能暂时取消
  		<c:if test="${!empty goods.collectId}">
  			<img class="xq_main_click_collect" src="../images/SHOUC_01.jpg" width="75" height="24" align="middle" style="cursor: pointer;" state="1" targetId="${goods.id}" type="goods" />
  		</c:if>
  		<c:if test="${empty goods.collectId}">
  			<img class="xq_main_click_collect" src="../images/SHOUC.jpg" width="75" height="24" align="middle" style="cursor: pointer;" state="2" targetId="${goods.id}" type="goods" />
  		</c:if>
  		-->
  	</div>
    <div class="xq_main_04_tp"><!-- <img src="../images/img_01.jpg" width="791" height="2627"  /> -->
    	${goods.goodsDetail}
    </div>
  </div>
  
  <!-- 包装和参数 -->
  <div class="xq_main_04 goods_main_contents" id="" style="display:none;">
  	<!-- 对比选择 -->
  	<div class="bz_mian_01"><span>参数仅为参考，产品以当地实际销售实物为准。</span>
      <input type="checkbox" name="checkbox" id="checkbox">
       <label for="checkbox"></label>
      	隐藏相同参数         
      <!--<input name="" type="checkbox" value="">       只显示我点击过的 -->
    </div>
    <!-- 对比参数 -->
    <div class="bz_mian_02">
      <div class="bz_mian_02_let">
      <div style="border-top: #e4e4e4 solid 1px;">
        <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_01">型号</div>
          <div class="bz_mian_02_let_biat_02">报价</div>
          <!-- <div class="bz_mian_02_let_biat_02">点评</div> -->
        </div>
        <div class="bz_mian_02_let_tupi" haveData="1">
          <div class="bz_mian_02_let_01">
            <div class="bz_mian_02_let_02"><div class="bz_mian_02_let_07">添加产品</div></div>
            <div class="bz_mian_02_let_03"></div>
			<div class="bz_mian_02_let_04">
				<div class="bz_mian_02_let_04_let"><a href="javascript:nowContrastLel('');">左移</a></div>
              	<div class="bz_mian_02_let_04_mid"><a href="javascript:nowContrastDel('');">删除</a></div>
              	<div class="bz_mian_02_let_04_ret"><a href="javascript:nowContrastRet('');">右移</a></div>
				<div class="clear"></div>
			</div> 
		  </div>
          <div class="bz_mian_02_let_05">
             [批发价]<h4></h4><br />
            <span></span>
          </div>
          <!-- <div class="bz_mian_02_let_06">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_02.jpg" width="12" height="14">4.3星<br />
            <span>178个批发商点评</span>
          </div> -->
        </div>
        <div class="bz_mian_02_let_tupi" haveData="1"><!-- 没有商品的时候的。。。 -->
          <div class="bz_mian_02_let_01">
            <div class="bz_mian_02_let_02"><div class="bz_mian_02_let_07">添加产品</div></div>
            <div class="bz_mian_02_let_03"></div>
            <div class="bz_mian_02_let_04">
              <div class="bz_mian_02_let_04_let"><a href="javascript:nowContrastLel('');">左移</a></div>
              <div class="bz_mian_02_let_04_mid"><a href="javascript:nowContrastDel('');">删除</a></div>
              <div class="bz_mian_02_let_04_ret"><a href="javascript:nowContrastRet('');">右移</a></div>
              <div class="clear"></div>
          </div>
          </div>
          <div class="bz_mian_02_let_05">
             [批发价]<h4></h4><br />
            <span></span>
          </div>
          <!-- <div class="bz_mian_02_let_06">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_02.jpg" width="12" height="14">4.3星<br />
            <span>178个批发商点评</span>
          </div> -->
        </div>
        <div class="bz_mian_02_let_tupi" haveData="1"><!-- 没有商品的时候的。。。 -->
          <div class="bz_mian_02_let_01">
            <div class="bz_mian_02_let_02"><div class="bz_mian_02_let_07">添加产品</div></div>
            <div class="bz_mian_02_let_03"></div>
            <div class="bz_mian_02_let_04">
              <div class="bz_mian_02_let_04_let"><a href="#">左移</a></div>
              <div class="bz_mian_02_let_04_mid"><a href="#">删除</a></div>
              <div class="bz_mian_02_let_04_ret"><a href="#">右移</a></div>
              <div class="clear"></div>
          </div>
          </div>
          <div class="bz_mian_02_let_05">
             [批发价]<h4></h4><br />
            <span></span>
          </div>
          <!-- <div class="bz_mian_02_let_06">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_02.jpg" width="12" height="14">4.3星<br />
            <span>178个批发商点评</span>
          </div> -->
        </div>
        <div class="bz_mian_02_let_tupi" haveData="1"><!-- 没有商品的时候的。。。 -->
          <div class="bz_mian_02_let_01">
            <div class="bz_mian_02_let_02"><div class="bz_mian_02_let_07">添加产品</div></div>
            <div class="bz_mian_02_let_03"></div>
            <div class="bz_mian_02_let_04">
              <div class="bz_mian_02_let_04_let"><a href="#">左移</a></div>
              <div class="bz_mian_02_let_04_mid"><a href="#">删除</a></div>
              <div class="bz_mian_02_let_04_ret"><a href="#">右移</a></div>
              <div class="clear"></div>
          </div>
          </div>
          <div class="bz_mian_02_let_05">
             [批发价]<h4></h4><br />
            <span></span>
          </div>
          <!-- <div class="bz_mian_02_let_06">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_01.jpg" width="14" height="14">
             <img src="images/stai_02.jpg" width="12" height="14">4.3星<br />
            <span>178个批发商点评</span>
          </div> -->
        </div>
      <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">基本参数</div>
      <div>
       <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">曝光日期</div>
          <div class="bz_mian_02_let_biat_02">手机类型</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">屏幕</div>
      <div>
        <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">触摸屏类型</div>
          <div class="bz_mian_02_let_biat_03">主屏尺寸 </div>
          <div class="bz_mian_02_let_biat_03">主屏幕分辨率</div>
          <div class="bz_mian_02_let_biat_03">屏幕像素密度</div>
          <div class="bz_mian_02_let_biat_03">屏幕技术</div>
          <div class="bz_mian_02_let_biat_03">窄边框</div>
          <div class="bz_mian_02_let_biat_04">屏幕占比</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="clear"></div>
      </div>
       <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">网络</div>
       <div> 
       <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">网络类型</div>
          <div class="bz_mian_02_let_biat_06">3G网络 </div>
          <div class="bz_mian_02_let_biat_05">支持频段</div>
          <div class="bz_mian_02_let_biat_06">理论速率</div>
          <div class="bz_mian_02_let_biat_03">WLAN功能</div>
          <div class="bz_mian_02_let_biat_06">导航</div>
          <div class="bz_mian_02_let_biat_06">连接与共享</div>
          <div class="bz_mian_02_let_biat_02">4G网络</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_06"></div>
       </div>
        <div class="clear"></div>
       </div>
       <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">硬件</div>
       <div>
        <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_06">操作系统</div>
          <div class="bz_mian_02_let_biat_03">核心数 </div>
          <div class="bz_mian_02_let_biat_06">CPU型号</div>
          <div class="bz_mian_02_let_biat_03">CPU频率</div>
          <div class="bz_mian_02_let_biat_03">GPU型号</div>
          <div class="bz_mian_02_let_biat_03">RAM容量</div>
          <div class="bz_mian_02_let_biat_03">ROM容量</div>
          <div class="bz_mian_02_let_biat_03">存储卡</div>
          <div class="bz_mian_02_let_biat_03">电池类型</div>
          <div class="bz_mian_02_let_biat_03">电池容量</div>
          <div class="bz_mian_02_let_biat_03">理论通话时间</div>
          <div class="bz_mian_02_let_biat_03">理论待机时间</div>
          <div class="bz_mian_02_let_biat_03">用户界面</div>
          <div class="bz_mian_02_let_biat_04">扩展容量</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_10"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">摄像头</div>
      <div>
        <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">摄像头</div>
          <div class="bz_mian_02_let_biat_03">摄像头类型 </div>
          <div class="bz_mian_02_let_biat_03">后置摄像头</div>
          <div class="bz_mian_02_let_biat_03">前置摄像头</div>
          <div class="bz_mian_02_let_biat_03">摄像头认证</div>
          <div class="bz_mian_02_let_biat_03">传感器类型</div>
          <div class="bz_mian_02_let_biat_03">闪光灯</div>
          <div class="bz_mian_02_let_biat_03">视频拍摄</div>
          <div class="bz_mian_02_let_biat_03">拍照功能</div>
          <div class="bz_mian_02_let_biat_03">光圈</div>
          <div class="bz_mian_02_let_biat_03">焦距/范围</div>
          <div class="bz_mian_02_let_biat_03">摄像头特色</div>
          <div class="bz_mian_02_let_biat_03">其他摄像头参数</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">外观</div>
      <div>
        <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">造型设计</div>
          <div class="bz_mian_02_let_biat_03">机身颜色</div>
          <div class="bz_mian_02_let_biat_03">手机尺寸</div>
          <div class="bz_mian_02_let_biat_03">手机重量</div>
          <div class="bz_mian_02_let_biat_03">机身特点</div>
          <div class="bz_mian_02_let_biat_03">操作类型</div>
          <div class="bz_mian_02_let_biat_05">感应器类型</div>
          <div class="bz_mian_02_let_biat_03">SIM卡类型</div>
          <div class="bz_mian_02_let_biat_06">机身接口</div>
          <div class="bz_mian_02_let_biat_03">机身材质</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_11"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left">服务与支持</div>
      <div>
       <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_06">音频支持</div>
          <div class="bz_mian_02_let_biat_06">视频支持</div>
          <div class="bz_mian_02_let_biat_03">图片支持</div>
          <div class="bz_mian_02_let_biat_06">常用功能</div>
          <div class="bz_mian_02_let_biat_03">商务功能</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div class="bz_mian_02_let_btxt" style=""><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left" />手机附件</div>
      <div >
       <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_05">包装清单</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_11"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_11"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_11"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_11"></div>
       </div>
       <div class="clear"></div>
      </div>
      <div style="display:none" class="bz_mian_02_let_btxt"><img state="1" src="images/shouqi.jpg" width="15" height="15" align="left" />保修信息</div>
      <div style="display:none" >
       <div class="bz_mian_02_let_biat">
          <div class="bz_mian_02_let_biat_03">保修政策</div>
          <div class="bz_mian_02_let_biat_03">质保时间</div>
          <div class="bz_mian_02_let_biat_06">质保备注</div>
          <div class="bz_mian_02_let_biat_03">客服电话</div>
          <div class="bz_mian_02_let_biat_06">电话备注</div>
          <div class="bz_mian_02_let_biat_07">详细内容</div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_013"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_013"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_013"></div>
       </div>
       <div class="bz_mian_02_let_tupi_04">
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_09"></div>
         <div class="bz_mian_02_let_012"></div>
         <div class="bz_mian_02_let_013"></div>
       </div>
       <div class="clear"></div>
      </div>
      </div>
      <div class="bz_mian_02_ret">
        <div class="bz_mian_02_ret_01">还想对比的同类产品</div>
        <div class="bz_mian_02_ret_02">
          <ul>
            
          </ul>
        </div>
        <div class="bz_mian_02_ret_03"><a href="javascript:packAndParam('more')">更多手机报价&gt;&gt;</a></div>
      </div>
     <div class="clear"></div>
    </div>
    
  </div>
  
  <!-- 用户口碑 -->
  <!-- <div class="xq_main_04 goods_main_contents" style="display:none">
    <div class="bz_mian_02">
      <div class="koub_top_02">
        <div class="koub_top_02_let"><img src="images/jiangz.jpg" width="59" height="54"></div>
        <div class="koub_top_02_ret"><span>诺基亚Lumia 1020</span><br />
          2014-12-12发表</div>
        <div class="clear"></div>
      </div>
      <div class="koub_img">
        <ul>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="koub_txt">
        <p>【最满意的一点】6.0英寸巨屏加全金属机身，霸气不解释；华为的通信技术完全不用担心有信号不好的情况出现；屏幕虽然只是1080P的，但是显示效果令人惊叹；emui3.0，简约而不简单；更多的个性化设定，人文气息更加突出；<br />
          指纹识别简单快捷，操作方便；相机表现出了十分优异的色彩表现力和细节表现，微距场景下丝毫毕现的细节展现堪称优异。夜拍总体的控噪能力不错，画面中的运动物体并非出现明显的拖影；925芯片首亮相，性能更加强劲</p>
        <p>【最不满意的一点】我买的白色联通版，也许是宣传太好了，期望很高，可拿到真机很失望，白色版的手机3个操作键和指示灯看起来做工很次，再有就是扬声器，所有的音量都调最大音还是很小，更别说放包里了，4的最大音量还没家里的m1， 2s，3音量一半响，不知道大家是不是都有这个问题。</p>
      </div>
      <div class="koub_nav"><a href="#">展开全部口碑</a></div>
      <div class="koub_pl">
        <div class="koub_pl_01">这条口碑对你有帮助吗？</div>
        <div class="koub_pl_02"><a href="#">有帮助</a></div>
        <div class="koub_pl_03">有<span>56</span>人支持该口碑</div>
        <div class="koub_pl_03">有<span>5546</span>人看过</div>
        <div class="koub_pl_04"><a href="#">评论（12）</a></div>
<div class="clear"></div>
      </div>
      <div class="koub_pl_05"><textarea name="" cols="" rows=""></textarea></div>
      <div class="koub_pl_08">
        <div class="koub_pl_06"><span>2014-12-12   11:12:11         <h4>1楼</h4></span><img src="images/touxiang.jpg" width="13" height="14" align="left">济南三鑫****通讯 </div>
        <div class="koub_pl_07">好详细啊！写的太好了！ </div>
      </div>
      <div class="koub_pl_08">
        <div class="koub_pl_06"><span>2014-12-12   11:12:11         <h4>1楼</h4></span><img src="images/touxiang.jpg" width="13" height="14" align="left">济南三鑫****通讯 </div>
        <div class="koub_pl_07">好详细啊！写的太好了！ </div>
      </div>
      <div class="koub_top_02">
        <div class="koub_top_02_let"><img src="images/jiangz.jpg" width="59" height="54"></div>
        <div class="koub_top_02_ret"><span>诺基亚Lumia 1020</span><br />
          2014-12-12发表</div>
        <div class="clear"></div>
      </div>
      <div class="koub_img">
        <ul>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
          <li><img src="images/img_03.jpg" width="126" height="126"></li>
        </ul>
        <div class="clear"></div>
      </div>
      <div class="koub_txt">
        <p>【最满意的一点】6.0英寸巨屏加全金属机身，霸气不解释；华为的通信技术完全不用担心有信号不好的情况出现；屏幕虽然只是1080P的，但是显示效果令人惊叹；emui3.0，简约而不简单；更多的个性化设定，人文气息更加突出；<br />
          指纹识别简单快捷，操作方便；相机表现出了十分优异的色彩表现力和细节表现，微距场景下丝毫毕现的细节展现堪称优异。夜拍总体的控噪能力不错，画面中的运动物体并非出现明显的拖影；925芯片首亮相，性能更加强劲</p>
        <p>【最不满意的一点】我买的白色联通版，也许是宣传太好了，期望很高，可拿到真机很失望，白色版的手机3个操作键和指示灯看起来做工很次，再有就是扬声器，所有的音量都调最大音还是很小，更别说放包里了，4的最大音量还没家里的m1， 2s，3音量一半响，不知道大家是不是都有这个问题。</p>
      </div>
      <div class="koub_nav"><a href="#">收起</a></div>
      <div class="koub_pl">
        <div class="koub_pl_01">这条口碑对你有帮助吗？</div>
        <div class="koub_pl_02"><a href="#">有帮助</a></div>
        <div class="koub_pl_03">有<span>56</span>人支持该口碑</div>
        <div class="koub_pl_03">有<span>5546</span>人看过</div>
        <div class="koub_pl_04"><a href="#">评论（12）</a></div>
        <div class="clear"></div>
      </div>
    </div>
  </div> -->
  
  
  <!-- 累计评价 -->
  <div class="xq_main_04 goods_main_contents" style="display:none">
  <div class="koub_top_01">
      <div class="pingj_top"> 
         <input type="checkbox" name="checkbox" id="checkbox">
        全部
        <input type="checkbox" name="checkbox" id="checkbox">
        有图片 
      </div>
      <div class="pingj_top_let"><a href="#">按时间v</a></div>
      <div class="clear"></div>
    </div>
    <div class="bz_mian_02">
    	<div class="jilu_top_07">
			<table border="0" cellspacing="0" cellpadding="0">
				<tbody >
					<tr style="text-align: center;">
						<td width="98">买家</td>
						<td width="523">款式/型号</td>
						<td width="112">数量</td>
						<td width="238">评价</td>
					</tr>
				</tbody>
			</table>
		</div>
      <div class="pingj_pingj">
        <ul>
          
        </ul> 
      </div>
    </div>
    <div class="fenye" id="goods_pingjia_fenye" style="margin-top: 20px;">
	    <ul id="goods_pingjia_page_ul" > 
	      <!-- <li><a href="#">上一页</a></li>
	      <li><a href="#">1</a></li>
	      <li><a href="#">2</a></li>
	      <li><a href="#">3</a></li>
	      <li>....</li>
	      <li><a href="#">下一页</a></li>
	      <li id="fenye">共20页</li>
	      <li id="fenyea">,到第</li>
	      <li id="fenyeb"><input name="" type="text"></li>
	      <li id="fenyea">页</li> -->
	     </ul>
	  </div>
  </div> 
  
  <!-- 月成交记录 --> 
   <div class="xq_main_04 goods_main_contents">
  	<div class="bz_mian_02">
		<div class="jilu_top_06">
			价 格：
			<h4>¥${minPrice} - ${maxPrices} </h4>拍下价格的不同可能是由于促销和打折引起的，详情可以咨询客服。
			<br /> 本商品累计售出<span>${goodsDealCountNum }</span>件 ，最近一个月成交<span>${goodsDealNum }</span>件
		</div>
		<div class="jilu_top_07">
			<table border="0" cellspacing="0" cellpadding="0">
				<tbody >
					<tr>
						<td width="198">买家</td>
						<td width="490">款式/型号</td>
						<td width="112">数量</td>
						<td width="170"></td>
						<td width="138">成交时间</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="cjjl">
			<ul id=goods_deal_ul>
				
			</ul>
		</div>
	</div>
	<div class="fenye" id="goods_deal_fenye" style="margin-top: 20px; margin-left: 440px;">
	    <ul id="goods_deal_page_ul">
		    
	    </ul>
	  </div>
  </div>
  
  
 
  <!-- 服务详情 -->
  <div class="xq_main_04 goods_main_contents" style="display:none">
  <div class="koub_top_01">
      <div class="fuwu">三际手机采购网服务承诺</div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany01.png" ></div>
        <div class="fuwu_01_ret">
          <title>全品类供货</title>
          <strong>全品类供货</strong>
          <p>致力于打造一站式采购平台，满足手机零售商全品类的采购需求。
            </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany02.png" ></div>
        <div class="fuwu_01_ret">
          <title>正品保证</title>
          <strong>正品保证</strong>
          <p>厂家一手直供货源，严格的入库验收管理，承诺假1罚10。</p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany03.png" ></div>
        <div class="fuwu_01_ret">
          <title>天天低价</title>
          <strong>天天低价</strong>
          <p>一手的采购货源能确保价格的优势，再通过全电商化运营，降低成本，出货价有显性优势。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany04.png" ></div>
        <div class="fuwu_01_ret">
          <title>30天退货</title>
          <strong>30天退货</strong>
          <p>30天内，只要产品不影响二次销售，可随时退货，退货价于退货日页面价格执行，运费需自己支付。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany05.png" ></div>
        <div class="fuwu_01_ret">
          <title>2年保修</title>
          <strong>2年保修</strong>
          <p>国家三包规定手机保修一年，三际承诺对零售店保修2年，人为损坏的不在保修范围。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany06.png" ></div>
        <div class="fuwu_01_ret">
          <title>限时送达</title>
          <strong>限时送达</strong>
          <p>除去恶劣天气，交通不畅等特殊原因，三际承诺当天下单，次日送达。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <div class="fuwu_01_let"><img src="images/miany07.png" ></div>
        <div class="fuwu_01_ret">
          <title>付款方便安全</title>
          <strong>付款方便安全</strong>
          <p>提供线上支付，货到pos收款两种安全便捷的方式，确保操作方便，安全放心
            </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01"> 
        <div class="fuwu_01_let"><img src="images/miany08.png" ></div>
        <div class="fuwu_01_ret">
          <title>货物在途保障</title>
          <strong>货物在途保障</strong>
          <p>只要在未签收前，所发生的一切破损、丢失，均由三际负责承担损失。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01"> 
        <div class="fuwu_01_let"><img src="images/miany09.png" ></div>
        <div class="fuwu_01_ret">
          <title>免收运费</title>
          <strong>免收运费</strong>
          <p>采购手机超过3台(含3台)，免收运费，3台以下每单收取6元。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01"> 
        <div class="fuwu_01_let"><img src="images/miany10.png" ></div>
        <div class="fuwu_01_ret">
          <title>360度服务</title>
          <strong>360度服务</strong>
          <p>三际对零售店提供全方位的贴身服务，你的任何一个好的建议及需求都会迅速得到反馈。
          </p>
        </div>
        <div class="clear"></div>
      </div>
    </div>
  </div>
	  
	</div> 
<!--======================bottom开始============================-->
<jsp:include page="/admin/common/footer.jsp"></jsp:include>
<script type="text/javascript">
var minPrice = ${minPrice};
var maxPrice = ${maxPrices};
</script>
</body>
</html>
