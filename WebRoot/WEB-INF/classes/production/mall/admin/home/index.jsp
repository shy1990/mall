<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />

</head>

<body>

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/jquery.vticker-min.js"></script>
<script type="text/javascript" src="js/home/home.js"></script>
<script type="text/javascript">
$(function(){
	GetMemberNum("admin_home_homeMemberNum");//获取商家数量
	selectHot('sku','day','5','admin_home_home_goodsHot');//获取热销手机
	showRetTop("1");// 买家服务和入门鼠标效果
	loadHomeNews();//加载资讯
	DealRoll();//成交状态循环
	homeFloorNews();
	ImgContent("HomeFloorContent1","HomeFloorNav1");
	ImgContent("HomeFloorContent2","HomeFloorNav2");
	ImgContent("HomeFloorContent3","HomeFloorNav3");
	ImgContent("HomeFloorContent4","HomeFloorNav4");
	ImgContent("HomeFloorContent5","HomeFloorNav5");
	ImgContent("HomeFloorContent6","HomeFloorNav6");
});
</script>
<!-- 左边快捷按钮 -->
<jsp:include page="../common/follow.jsp"></jsp:include>


<!--======================首页banner开始============================-->
<div class="home_main">
  <div class="home_main_banner">
    <div class="home_main_01">
      <div class="home_main_01_let">
        <div class="home_main_01_let_top"><span>在线买家数: <h5 id="admin_home_homeMemberNum"></h5><img src="images/home_xias.jpg" width="11" height="14" alt=""/></span>实时采购</div>
        <div class="home_main_01_let_txt">
          <ul>
            <li><a href="#">苹果手机 iPhone4S (8GB) </a>
              <h5>10台</h5></li>
            <li><a href="#">苹果手机 iPhonS (8GB)</a>
              <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPh4S (8GB)</a>
              <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPhone4S (8GB)</a>
            <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPhone (8GB) </a>
              <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPh8GB)</a>
              <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPhone4S (8GB)</a>
            <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPho8GB)</a>
              <h5> 10台</h5></li>
            <li><a href="#">苹果手机 iPhone4S (8GB)</a>
            <h5> 10台</h5></li>
          </ul>
        </div>
      </div>
     <div id="main">
	<div class="box">
		<img style="opacity:1;filter:alpha(opacity=100);" src="images/intro1.jpg" />
		<img src="images/intro2.jpg" /> <img src="images/intro3.jpg" /> </div>
	<div class="page">
		<a class="active" href="javascript:void(0);">1</a>
		<a href="javascript:void(0);">2</a> <a href="javascript:void(0);">3</a>
	</div>
</div>
      <div class="home_main_01_ret">
         <div class="home_main_01_ret_top">
           <ul>
             <li ><a id="admin_home_homeServe_1" onmouseover="showRetTop('1')"  href="javascript:showRetTop('1');">服务特点</a></li>
             <li id="home_main_01_ret_top"><a id="admin_home_homeServe_2" onmouseover="showRetTop('2')"  href="javascript:showRetTop('2');">买家入门</a></li>
           </ul>
           <div class="clear"></div>
         </div>
         <div class="home_main_01_ret_txt">
           <table width="222" height="264" border="0" cellpadding="0" cellspacing="0">
             <tr>
               <td width="74" height="88" align="center" valign="top"><p><a href="#"><img src="images/bqian.jpg"/></a></p>
                <p><a href="#">退货免费</a></p></td>
               <td width="74" height="88" align="center" valign="top"><p><a href="#"><img src="images/bxiu.jpg" /></a></p>
                <p><a href="#">保修两年</a></p></td>
               <td width="74" height="88" align="center" valign="top"><p><a href="#"><img src="images/bhoc.jpg" /></a></p>
                <p><a href="#">退货免费</a></p></td>
             </tr>
             <tr>
               <td width="74" height="88" align="center" valign="top"><p><img src="images/bmian.jpg" width="26" height="28" alt=""/></p>
                <p>货到付款 </p></td>
               <td width="74" height="88" align="center" valign="top"><p><img src="images/bhuan.jpg" width="25" height="28" alt=""/></p>
                <p>七天无理<br />
由退换货 </p></td>
               <td width="74" height="88" align="center" valign="top"><p><img src="images/bshijan.jpg" width="29" height="28" alt=""/></p>
                <p>退货免费</p></td>
             </tr>
             <tr>
               <td width="74" height="88" align="center" valign="top"><p><img src="images/bbao.jpg" width="23" height="25" alt=""/></p>
                <p>退货免费</p></td>
                <td width="74" height="88" align="center" valign="top"></td>
                <td width="74" height="88" align="center" valign="top"></td>
             </tr>
           </table>
         </div>
      </div>
      <div class="clear"></div>
    </div>
    <div>
       <div class="home_main_02_top">
          <ul>
           <li><a id="admin_home_home_aHotday" href="javascript:selectHot('sku','day','5','admin_home_home_goodsHot');"
           			 onmouseover="selectHot('sku','day','5','admin_home_home_goodsHot');">今日热销</a></li>
           <li><a id="admin_home_home_aHotweek" href="javascript:selectHot('sku','week','5','admin_home_home_goodsHot');"
           			onmouseover="selectHot('sku','week','5','admin_home_home_goodsHot');">本周热销</a></li>
           <li><a id="admin_home_home_aHotmonth" href="javascript:selectHot('sku','month','5','admin_home_home_goodsHot');"
           			onmouseover="selectHot('sku','month','5','admin_home_home_goodsHot');">本月热销 </a></li>
           <li id="home_main_04"><a href="goods/paihang.html">查看更多&gt;&gt;</a></li>
         </ul>
         <div class="clear"></div>
       </div>
       <div class="home_main_03_txt">
         <ul id="admin_home_home_goodsHot"></ul>
         <div class="clear"></div>
       </div>
    </div>
  </div>
  <div class="home_main_03"><img src="images/img_08.jpg" width="1190" height="70" alt=""/></div>
  <!---------------1楼------------------>
  <div>
    <div class="home_main_04"><span><a href="javascript:goListView('手机');">查看更多>></a></span><img src="images/f1.jpg" width="51" height="35" alt="" align="left"/>手机</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f1_img.jpg" width="239" height="119" alt=""/></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_1F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="220" height="135" valign="top">品牌<br />
      <a href="javascript:goListView('手机','三星');">三星</a>
      <a href="javascript:goListView('手机','诺基亚');">诺基亚 </a>
      <a href="javascript:goListView('手机','索尼');">索尼</a>
      <a href="javascript:goListView('手机','苹果');">苹果</a> <br />
      
      <a href="javascript:goListView('手机','努比亚');">努比亚</a>
      <a href="javascript:goListView('手机','htc');">htc</a>
      <a href="javascript:goListView('手机','金立');">金立</a>
      <a href="javascript:goListView('手机','艾尤尼');">艾尤尼</a> <br />
      
      <a href="javascript:goListView('手机','魅族');">魅族</a>
      <a href="javascript:goListView('手机','中兴');">中兴</a>
      <a href="javascript:goListView('手机','一加');">一加</a>
      <a href="javascript:goListView('手机','锤子');">锤子</a><br/>
      
      <a href="javascript:goListView('手机','小米');">小米</a></td>
      <td width="220" height="135" valign="top">价格<br />
      <a href="javascript:goListView('手机','','0-500');">0-500</a>
      <a href="javascript:goListView('手机','','500-1000');">500-1000</a>
      <a href="javascript:goListView('手机','','1000-1500');">1000-1500</a><br />
      <a href="javascript:goListView('手机','','1500-2000');">1500-2000</a>
      <a href="javascript:goListView('手机','','2000-2500');">2000-2500</a>
      <a href="javascript:goListView('手机','','2500-3000');">2500-3000</a><br />
      <a href="javascript:goListView('手机','','3000-3500');">3000-3500</a>
      <a href="javascript:goListView('手机','','3500-4500');">3500-4500</a>
      <a href="javascript:goListView('手机','','4500');">4500以上</a> </td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent1">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav1">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------1楼------------------>
  <!---------------2楼------------------>
  <div>
    <div class="home_main_06"><span><a href="javascript:goListView('手环');">查看更多>></a></span><img src="images/f2.jpg" align="left"/>智能生活</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f2_img.jpg" /></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_2F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="220" height="135" valign="top">手环<br />
      <a href="javascript:goListView('手环','小米');">小米</a>
      <a href="javascript:goListView('手环','inwatch');"> inwatch </a>
      <a href="javascript:goListView('手环','荣耀');"> 荣耀</a>     <br />
   </td>
    <td width="220" height="135" valign="top">价格<br />
      <a href="javascript:goListView('手环','','0-500');">0-500</a>
      <a href="javascript:goListView('手环','','500-1000');">500-1000</a>
      <a href="javascript:goListView('手环','','1000-1500');">1000-1500</a><br />
      <a href="javascript:goListView('手环','','1500-2000');">1500-2000</a>
      <a href="javascript:goListView('手环','','2000-2500');">2000-2500</a>
      <a href="javascript:goListView('手环','','2500-3000');">2500-3000</a><br />
      <a href="javascript:goListView('手环','','3000-3500');">3000-3500</a>
      <a href="javascript:goListView('手环','','3500-4500');">3500-4500</a>
      <a href="javascript:goListView('手环','','4500');">4500以上</a> </td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent2">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav2">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------2楼------------------>
  <!---------------3楼------------------>
  <div>
    <div class="home_main_05"><span><a href="javascript:goListView('手机壳');">查看更多>></a></span><img src="images/f3.jpg" align="left"/>保护类</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f3_img.jpg" /></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_3F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
   <td width="220" height="135" valign="top">手机壳<br />
      <a href="javascript:goListView('手机壳','艾伦顿');">艾伦顿</a>
      <a href="javascript:goListView('手机壳','莫凡');"> 莫凡 </a>
      <a href="javascript:goListView('手机壳','迪佐');">迪佐</a>
	  <a href="javascript:goListView('手机壳','迪佑');">迪佑</a> <br />
      <a href="javascript:goListView('手机壳','伊贝');">伊贝   </a> 
    </td>
    <td width="220" height="135" valign="top">手机套<br />
      <a href="javascript:goListView('手机套','艾伦顿');">艾伦顿</a>
      <a href="javascript:goListView('手机套','莫凡');"> 莫凡 </a>
      <a href="javascript:goListView('手机套','迪佐');">  迪佐</a>
	  <a href="javascript:goListView('手机套','迪佑');">迪佑</a> <br />
      <a href="javascript:goListView('手机套','伊贝');">伊贝   </a> 
    </td>
    <td width="220" height="135" valign="top">贴膜<br />
      <a href="javascript:goListView('贴膜','艾伦顿');">艾伦顿</a>
    </td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent3">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav3">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------3楼------------------>
  <!---------------4楼------------------>
  <div>
    <div class="home_main_07"><span><a href="javascript:goListView('电池');">查看更多>></a></span><img src="images/f4.jpg" align="left"/>充电类</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f4_img.jpg"/></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_4F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="220" height="135" valign="top">电池<br />
      <a href="javascript:goListView('电池','华毅');"> 华毅  </a>
      <a href="javascript:goListView('电池','至尊');"> 至尊  </a>
    </td>
    <td width="220" height="135" valign="top">充电器<br />
      <a href="javascript:goListView('充电器','风致');">风致</a>
	</td>
    <td width="220" height="135" valign="top">充电宝<br />
      <a href="javascript:goListView('充电宝','小米');"> 小米  </a>
      <a href="javascript:goListView('充电宝','倍斯特');"> 倍斯特  </a>
      <a href="javascript:goListView('充电宝','果果');">  果果   </a><br />
	</td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent4">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav4">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------4楼------------------>
  <!---------------5楼------------------>
  <div>
    <div class="home_main_08"><span><a href="javascript:goListView('耳机');">查看更多>></a></span><img src="images/f5.jpg" align="left"/>视听类</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f5_img.jpg"/></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_5F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
     <td width="220" height="135" valign="top">耳机<br />
      <a href="javascript:goListView('耳机','小米');">小米</a>
      <a href="javascript:goListView('耳机','华为');"> 华为  </a>
	</td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent5">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav5">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------5楼------------------>
  <!---------------6楼------------------>
  <div>
    <div class="home_main_09"><span><a href="javascript:goListView('移动卡');">查看更多>></a></span><img src="images/f6.jpg" align="left"/>号卡类</div>
    <div>
      <div class="home_main_04_let">
        <div><img src="images/f6_img.jpg"/></div>
        <div class="home_main_04_let_txt">
          <ul id="admin_home_homeFloorNews_6F">
            
          </ul>
        </div>
      </div>
      <div class="home_main_04_mid">
        <table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="220" height="135" valign="top">移动<br />
   	</td>
    <td width="220" height="135" valign="top">联通<br />
    </td>
    <td width="220" height="135" valign="top">电信<br />
    </td>
  </tr>
</table>

      </div>
      <div class="home_main_04_ret">
      	<div class="myjQuery">
		    <div class="myjQueryContent" id="HomeFloorContent6">
		      <div><a href="#"><img src="images/img_10.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_11.jpg" width="254" height="271"/></a></div>
		      <div class="smask"><a href="#" ><img src="images/img_12.jpg" width="254" height="271"/></a></div>
		    </div>
		    <ul class="myjQueryNav" id="HomeFloorNav6">
		      <li class="current"></li>
		      <li></li>
		      <li></li>
		    </ul>
		  </div>
      </div>
      <div class="clear"></div>
    </div>
  </div><!---------------6楼------------------>
  <div class="home_main_03"><img src="images/img_09.jpg" width="1191" height="119" alt=""/></div>
  <div>
    <div class="home_main_new_top"><span><a href="#">查看更多>></a></span>最新资讯  </div>
    <div class="home_main_new">
      <ul id="admin_home_homeNewsHteml">
        
      </ul>
      <div class="clear"></div>
    </div>
  </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>


</body>
</html>
