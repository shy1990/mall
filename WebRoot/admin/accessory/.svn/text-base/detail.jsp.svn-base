<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>配件详情</title>
    
	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
 	<script type="text/javascript" src="js/ajaxPage/jqPaginator.js"></script>
 	<script type="text/javascript" >
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
 		var acsId = "${accessorie.id}";
 	</script>
 	<script type="text/javascript" src="js/accessory/detail.js"></script>
 </head>
  <body>
  	<jsp:include page="/admin/common/follow.jsp"></jsp:include>
	<jsp:include page="/admin/common/head.jsp"></jsp:include>
	
	<div class="xq_mian">
	  <div class="xq_mian_top"><span><a href="javascript:history.go(-1);">返回</a></span>当前位置&gt;&gt;<h5>配件详情</h5></div>
	  <!---------第一块基本信息----------><!---------第二块采购说明---------->
	  <div class="xq_main_02">
	    <div>
	    <div class="sanji_clxx_box_left">
	        <div class="sanji_clxx_box_left_images"><img src="${accessorie.defaultImg}" width="380" height="390" alt=""/></img>
	        </div>
	        
	    </div> 
	    <div class="sanji_clxx_box_right">
	      <div class="sanji_clxx_box_right_02">
	         ${accessorie.brand.name} ${accessorie.name}
	      </div>
	       <div class="sanji_clxx_box_right_03"> 
	          <p>批发价：<span class="price">￥${accessorie.price}元</span></p>
	          <p>销量：${goodsDealCountNum } 件	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	库存：<span id="accessory_stock">${accessorie.stock} <c:if test="${empty accessorie.stock}">0</c:if> </span>件</p>
			    <%-- 运费：<img src="${pageContext.request.contextPath}/images/T19jvMXh4jXXXUKY6f-41-14.png" width="41" height="14" alt=""/> 快递 ¥10.00 --%>
	       </div> 
	
	        <div class="sanji_clxx_box_right_04"> 
	         <ul>
	           <li>颜色分类：</li>
	           <c:if test="${accessorie.colors != null }">
		    		<c:forEach var="c" items="${accessorie.colors }">
		    			<li><a href="javascript:;" class="selected">${c.colorName}</a></li>
		    		</c:forEach>
		    	</c:if>
		    	<c:if test="${accessories != null }">
		    		<c:forEach var="a" items="${accessories }">
		    			<c:if test="${a.COLORNAME != null }">
				    		<li><a href="accessory/detail/${a.APECCODE}.html">${a.COLORNAME}</a></li>
				    	</c:if>
		    		</c:forEach>
		    	</c:if>
		    	
	         </ul>
	         <div class="clear"></div>
	        </div>
	        <form id="go_add_cart" onkeydown="if(event.keyCode==13) return false;" target="_blank" method="post" action="cart/addAccessory_cart.html">
		        <div class="sanji_clxx_box_right_05">
		         <div class="xgzy_top_03" style="line-height: 2;">购买数量：</div>
		         <div id="xq_main_02_let_03_let" style="width: 30px;"><a href="javascript:statsBuyNum(-1)"><img src="images/jian.jpg" width="30" height="30" /></a></div>
		         <div id="xq_main_02_let_03_ret" style="width: 47px;"><input id="gou_mai_num" data-stock="${accessorie.stock}<c:if test="${empty accessorie.stock}">0</c:if>" style="width: 47px;height: 30px;" name="amount" type="text" value="0"  onMouseOver="this.focus()" onMouseOut="if(this.value=='')this.value='0';" onFocus="this.select()" onClick="if(this.value=='0')this.value=''"  /></div>
		         <div id="xq_main_02_let_03_let" style="width: 30px;"><a href="javascript:statsBuyNum(1)"><img src="images/jia.jpg" width="30" height="30" /></a></div>
		         <div class="clear"></div>
		        </div>
		        <div class="sanji_clxx_box_right_06">
		        	<input type="hidden" name="targetId" value="${accessorie.id}" />
		        	<a href="javascript:add_cart_show();" >加入采购车</a> 
		        </div>
	        </form>
	    </div>
	      <div class="clear"></div>
	  </div>
	  </div>
	  <!---------第三块产品详情---------->
	  <div class="xq_main_03">
	    <div class="xq_main_03_top">
	      <ul>
	        <li class="selecter"><a href="javascript:;">商品详情</a></li>
	        <li id="" ><a href="javascript:;">包装和参数</a></li>
	        <li id="" ><a href="javascript:;">用户口碑</a></li>
	        <li id="" ><a href="javaScript:;">累计评价（<span id="goods_pingjia_num_span"></span>）</a></li>
	        <li id="" ><a href="javascript:;">月成交记录（${goodsDealNum }）</a></li>
	        <li id="" ><a href="javascript:;">服务详情</a></li>
	      </ul>
	        <div class="clear"></div> 
	    </div>
	  </div>
	    <!-- 商品详情内容 -->
  	  <div class="xq_main_04 selecter goods_main_contents" id="" >
	    <div class="xq_main_03_txt">品牌名称：<span>${accessorie.brand.name}</span>
	    	<a targetId="${accessorie.id}" class="acs_detail_collect" href="javascript:;">
	    		<!-- <img src="images/SHOUC.jpg" width="75" height="24" align="middle" /> -->
	    		<c:if test="${collect}">
		  			<img class="xq_main_click_collect" src="../images/SHOUC_01.jpg" width="75" height="24" align="middle" style="cursor: pointer;" state="1" targetId="${accessorie.id}" type="accessories" />
		  		</c:if>
		  		<c:if test="${!collect}">
		  			<img class="xq_main_click_collect" src="../images/SHOUC.jpg" width="75" height="24" align="middle" style="cursor: pointer;" state="2" targetId="${accessorie.id}" type="accessories" />
		  		</c:if>
	    	</a>
	    </div>
	    
	    <div class="xq_main_04">
		  <div class="xq_main_04_tp">
		  	${accessorie.accessoriesDetail}
		  </div>
		</div> 
	  </div>
	  
	  
	  <!-- 包装和参数 -->
	  <div class="xq_main_04 goods_main_contents" id="" style="display:none;">
	  	<!-- 对比选择 -->
	  	<div class="bz_mian_01"><span>参数仅为参考，产品以当地实际销售实物为准。</span>
	      <!-- <input type="checkbox" name="checkbox" id="checkbox">
	       <label for="checkbox"></label> -->
	      	<!-- 隐藏相同参数  -->        
	      <!--<input name="" type="checkbox" value="">       只显示我点击过的 -->
	      <div class="sanji_clxx_box_right_07">产品参数:</div>
		    <div class="sanji_clxx_box_right_08">
		      <table border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="384">品牌: ${accessorie.brand.name}</td>
		          <td width="369">型号: ${accessorie.accessoriesVersion }</td>
		          <td width="387">颜色分类: <c:if test="${accessorie.colors != null }">
			    		<c:forEach var="c" items="${accessorie.colors }"> 
			    			${c.colorName}&nbsp;&nbsp;
			    		</c:forEach> 
			    	</c:if></td>
		        </tr>
		        <tr>
		          <td>适用型号: ${accessorie.goods.name }</td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
		        </tr>
		      </table>
		    </div>
	    </div>
	  </div>
    
	  
	    <!-- 用户口碑 -->
		  <div class="xq_main_04 goods_main_contents" style="display:none">
		    <div class="bz_mian_02">
		      <div class="koub_top_02">
		        <div class="koub_top_02_let"><img src="images/jiangz.jpg" width="59" height="54"></div>
		        <div class="koub_top_02_ret"><span>诺基亚Lumia 1020</span><br>
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
		        <p>【最满意的一点】6.0英寸巨屏加全金属机身，霸气不解释；华为的通信技术完全不用担心有信号不好的情况出现；屏幕虽然只是1080P的，但是显示效果令人惊叹；emui3.0，简约而不简单；更多的个性化设定，人文气息更加突出；<br>
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
		        <div class="koub_top_02_ret"><span>诺基亚Lumia 1020</span><br>
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
		        <p>【最满意的一点】6.0英寸巨屏加全金属机身，霸气不解释；华为的通信技术完全不用担心有信号不好的情况出现；屏幕虽然只是1080P的，但是显示效果令人惊叹；emui3.0，简约而不简单；更多的个性化设定，人文气息更加突出；<br>
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
		  </div>
		  
		  
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
	</div><!-- 累计评价end -->
	
	  <!-- 月成交记录 --> 
  <div class="xq_main_04 goods_main_contents" style="display:none">
  	<div class="bz_mian_02">
		<div class="jilu_top_06">
			价 格：
			<h4>¥${minPrice} - ${maxPrices} </h4>拍下价格的不同可能是由于促销和打折引起的，详情可以咨询客服。
			<br> 本商品累计售出<span>${goodsDealCountNum }</span>件 ，最近一个月成交<span>${goodsDealNum }</span>件
		</div>
		<div class="jilu_top_07">
			<table border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td width="198">买家</td>
						<td width="523">款式/型号</td>
						<td width="112">数量</td>
						<td width="150">拍下价格</td>
						<td width="138">成交时间</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="pingj_pingj">
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
      <div class="fuwu">三际在线服务承诺</div>
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
        <div class="fuwu_01_let"><img src="images/miany.jpg" width="45" height="39" ></div>
        <div class="fuwu_01_ret">
          <title>退货免费</title>
          <strong>退货免费</strong>
          <p>放心购买，退货无忧，承担运费，为商家在退货时提供省心便捷的上门取货服务，如因三际产品本身原因导致的退货，三际为上家提供上门退货服务并承担运费<br>
            服务优势<br>
            <span>1.退货物流信息透明，让你实时掌握进度，物流跟踪状态实时更新<br>
            2.退货时进行基础检验，保障，退货商品符合您的退货需求</span></p> 
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
        <!-- <div class="fuwu_01_let"><img src="images/car.jpg" width="45" height="27"></div> -->
        <div class="fuwu_01_let"><img src="images/miany04.png" ></div>
        <div class="fuwu_01_ret">
        <!--   <title>货到付款</title>
          <p>商家在三际网上通讯城购买的手机可享受货到付款的服务，商家可在检验货物完好，符合商家要求后刷卡或现金支付<br> -->
          <title>30天退货</title>
          <strong>30天退货</strong>
          <p>30天内，只要产品不影响二次销售，可随时退货，退货价于退货日页面价格执行，运费需自己支付。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <!-- <div class="fuwu_01_let"><img src="images/mianq.jpg" width="45" height="34"></div> -->
        <div class="fuwu_01_let"><img src="images/miany05.png" ></div>
        <div class="fuwu_01_ret">
          <!-- <title>免费配送</title>
          <p>商家在三际网上通讯城一个订单购买三台以上手机（含三台）运费由三际承担<br> -->
          <title>2年保修</title>
          <strong>2年保修</strong>
          <p>国家三包规定手机保修一年，三际承诺对零售店保修2年，人为损坏的不在保修范围。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
        <!-- <div class="fuwu_01_let"><img src="images/shijianh.jpg" width="43" height="34"></div> -->
        <div class="fuwu_01_let"><img src="images/miany06.png" ></div>
        <div class="fuwu_01_ret">
         <!--  <title>七天无理由退换货</title>
          <p>因质量问题产生的退换货，往返运费由三际承担。退换货要求具备商品收到时要有完整的外包装，配件，保卡，商品无破损，残缺。非商品质量问题的退换货由商家承担往返运费<br> -->
          <title>限时送达</title>
          <strong>限时送达</strong>
          <p>除去恶劣天气，交通不畅等特殊原因，三际承诺当天下单，次日送达。
          </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01">
      <!--   <div class="fuwu_01_let"><img src="images/huanh.jpg" width="45" height="34"></div> -->
        <div class="fuwu_01_let"><img src="images/miany07.png" ></div>
        <div class="fuwu_01_ret">
        <!--   <title>30天换货</title>
          <p>为保障商家能够及时的周转，合理的控制库存及有利的竞争优势是，三际承诺商家在三际购买的手机可享受30天内调换；补交差价，即换货按现价计算<br>
            细则：<br>
            <span>1）同款不同颜色调换 2）<br>
            不同型号，款式需补交差价，核算价格为三际收到<br>
            货的当天销售价格 <br>
            3）30天以商家签收值日次日起开始计算日期<br>
4）退货可联系当地区域主管或指定物流公司</span></p> -->
          <title>付款方便安全</title>
          <strong>付款方便安全</strong>
          <p>提供线上支付，货到pos收款两种安全便捷的方式，确保操作方便，安全放心
            </p>
        </div>
        <div class="clear"></div>
      </div>
      <div class="fuwu_01"> 
       <!--  <div class="fuwu_01_let"><img src="images/dunp.jpg" width="43" height="34"></div> -->
        <div class="fuwu_01_let"><img src="images/miany08.png" ></div>
        <div class="fuwu_01_ret">
          <!-- <title>品质保障</title>
          <p>为保障商家能够及时的周转，合理的控制库存及有利的竞争优势是，三际承诺商家在三际购买的手机可享受30天内调换；补交差价，即换货按现价计算<br>
 -->
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
	
	
	
	
 	<%--  配件信息：<br>
    ${accessorie.id}<br>
    ${accessorie.name}<br>
    ${accessorie.cat.name}<br>
    ${accessorie.brand.name}<br> --%>
    <jsp:include page="/admin/common/footer.jsp"></jsp:include>
  </body>
</html>
