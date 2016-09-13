<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sanji.mall.pojo.SessionInfo"%>
<%@ page import="com.sanji.mall.common.util.ResourceUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute(ResourceUtil.getSessionInfoName());
	String memberName = sessionInfo == null ? "" : sessionInfo
			.getLoginName();

	if (memberName != null && !"".equals(memberName)) {
%>
<!--======================top开始============================-->


<style>
.fix_top {
	position: fixed;
	margin-top: 0;
	top: 0;
	z-index: 9999;
	width: 100%
}

.caret {
	display: inline-block;
	width: 0;
	height: 0;
	margin-left: 2px;
	vertical-align: middle;
	border-top: 4px solid;
	border-right: 4px solid transparent;
	border-left: 4px solid transparent;
}

.index_daohang_let .dropdown {
	position: relative;
	display: block;
}

.index_daohang_let .dropdown ul {
	display: none;
	position: absolute;
	top: 100%;
	z-index: 1000;
}

.index_daohang_let .dropdown ul li {
	background-color: #f02424;
}

.index_daohang_let .dropdown ul li a {
	font-size: 14px;
}

.index_top_ret_let {
	position: relative;
}

.dropdown-menu {
	position: absolute;
	top: 100%;
	left: 0;
	z-index: 99999;
	float: left;
	width: 98%;
	margin-top: -2px;
	font-size: 14px;
	text-align: left;
	list-style: none;
	background-color: #FFF;
	background-clip: padding-box;
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .15);
	border-top: none;
	border-radius: 4px;
	-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
	box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
	clear: left;
}

.dropdown-menu li {
	cursor: pointer;
	display: list-item;
	color: #000;
	font: 14px arial;
	line-height: 25px;
	position: relative;
	background-color: #FFF;
}

.dropdown-menu li:hover {
	background-color: #ccc;
}

.icon-new {
	position: relative;
	padding: 2px 5px;
	border: 1px solid #ffffff;
	border-radius: 5px;
	/*background: url(images/red-packet/new.gif) no-repeat;*/
}
</style>

<script type="text/javascript">
	/* window.prototype.//console = function(){};
	 */

	/*String.prototype.trim = $.trim;  function () {
	 var str = this ,
	 str = str.replace(/^\s /, '' );
	 for ( var i = str.length - 1; i >= 0; i--) {
	 if (/\S/.test(str.charAt(i))) {
	 str = str.substring(0, i , 1);
	 break ;
	 }
	 }
	 return str;
	 }; */

	/**
	 * 跳转到列表页
	 * @param catNames 要查询的类别名称
	 */
	function goListView(catNames, brandNames, defaultPrices, searchParam) {
		var form = $('<form id="js_submit_form" action="listView/list.html" method="post" target="_self" style="display:none"></form>');
		var catNamesInput = '<input type="text" name="catNames" value="' + catNames + '"/>';
		var brandNamesInput = '<input type="text" name="brandNames" value="' + brandNames + '"/>';
		var pricesInput = '<input type="text" name="defaultPrices" value="' + defaultPrices + '"/>';
		var searchParamInput = '<input type="text" name="searchParam" value="' + searchParam + '"/>';
		var sub = '<input type="submit" />';
		form.append(catNamesInput + brandNamesInput + pricesInput
				+ searchParamInput);
		form.append(sub);
		$("body").append(form);
		//form.submit().click();
		document.getElementById("js_submit_form").submit();
	}

	function searchfn(searchType) {
		var searchVal = document.getElementById("head_searchParam").value;
		//console.info("要搜索的内容："+searchVal);
		//if (searchVal != undefined && searchVal != "") {
		//goListView('手机', '', '', searchVal);
		goListView(searchType, '', '', searchVal);
		//}
	}
	function keyDown(e) {
		var ev = window.event || e;

		//13是键盘上面固定的回车键
		if (ev.keyCode == 13) {
			//你要执行的方法
			searchfn('手机');
		}
	}

	$(function() {

		setNav();

		//鼠标移入的时候显示
		$(".index_daohang_let .dropdown").live("mouseover", function() {
			$(this).children("ul").show();
		});

		//鼠标离开的时候隐藏
		$(".index_daohang_let .dropdown").live("mouseout", function() {
			$(this).children("ul").hide();
		});

		/**松开键盘的时候进行模糊搜索匹配**/
		$("#head_searchParam").keyup(
				function() {
					//console.info("开始模糊匹配");
					likeSearch($(this).val(), $("#head_like_search_show_box"),
							"head_searchParam");
				});

		/**红包浮层关闭按钮 */
		$('.J_close').on('click', function() {
			$(this).parents('.red-packet-modal').css('display', 'none');
			$('.modal').css("display", "none");
		});
		// 检查该用户是否有红包
		checkUserIfHasHb();

		shopRush();
	});
	//模糊搜索
	var likeSearch = function(s, box, searchInput) {
		//console.info((!s.trim()));
		if (!$.trim(s)) {
			box.html("");
			$(".home_main_01 #main").css("z-index", "1");
			return false;
		}

		$.ajax({
			url : "goods/search.html",
			type : "post",
			dataType : "json",
			data : {
				s : s
			},
			success : function(json) {
				//var json = $.parseJSON(j);
				if (json && json.success) {
					if (json.obj.length) {
						var ul = '<ul class="dropdown-menu">';
						$.each(json.obj, function(i, obj) {
							//console.info(obj);
							//console.info(obj.NAME);
							ul += '<li onclick="lickSearchVal(\'' + searchInput
									+ '\',\'' + obj.NAME + '\')">' + obj.NAME
									+ '</li>';
						});
						ul += '</ul>';
						box.html(ul);
						//主页的图片z-index设为-1
						$(".home_main_01 #main").css("z-index", "-1");
					} else {
						box.html("");
						$(".home_main_01 #main").css("z-index", "1");
					}
				} else {
					box.html("");
					$(".home_main_01 #main").css("z-index", "1");
				}
			}
		});
	};

	//把当前点击的值赋值给对应的搜索框
	var lickSearchVal = function(searchInput, val) {
		$("#" + searchInput).val(val);
		searchfn('手机');
	};

	//动态设置导航栏居中
	var setNav = function() {
		var width = $(window).width();
		var divWidth = $(".index_daohang_let").width();
		var wid = (width - divWidth) / 2;
		$(".index_daohang_box").css("margin-left", wid);
	}
	function checkUserIfHasHb() {
		$.ajax({
			type : "get",
			url : "hb/checkuserifhashb.html",
			dataType : 'json',
			success : function(d) {
				var data = d.obj;
				// data = "abc";
				if (data) {
					$("#hbView").show();
					$(".J_modal").show();

				} else {
					$("#hbView").hide();
					$(".J_modal").hide();
				}
			}
		});

	}

	/**
	 * 检查该用户是否可以看到限购活动的页面
	 */
	function shopRush() {
		$.ajax({
			url : "goods/shoprush.html",
			type : "GET",
			success : function(data) {
				var d = $.parseJSON(data);
				if (null != d && d["obj"] && d["obj"].length > 0) {
					$("#shopRush").show();
				}
			},
			error : function(data) {
			}
		});
	}
</script>
<style>
.red-packet {
	display: inline-block;
	position: absolute;
	top: 0;
	right: -75px;
	display: inline-block;
}

.red-packet>img {
	padding: 0;
	margin: 0;
	display: inline-block;
	position: relative;
	width: 165px;
}

.index_logo_let {
	position: relative;
}
</style>

<div class="index_top">
	<div class="index_top_box">
		<div class="index_top_let">
			您好！<span><%=memberName%> </span><a href="member/out.html">[退出登录]</a>
		</div>
		<div class="index_top_ret">
			<div class="index_top_ret_let">
				<!-- member/toMyAccount.html -->
				<a href="myCenter/index.html"><img src="images/wodehznaghu.jpg"
					width="15" height="14" align="top" />我的账户</a> <a
					href="groupIntroduction.html"><img src="images/bangzhu.jpg"
					width="14" height="14" align="top" />帮助中心</a> <a href="cart/main.html"><img
					src="images/gouwu.jpg" width="17" height="14" align="top" />我的购物车</a>（<span
					id="count" class="totalAmount">00</span>）
				<script type="text/javascript">
					$.post('cart/count.html', function(result) {
						$("#count").html(result);
					});
				</script>
				<!--    <a href="/collect/myCollects.html"><img
                        src="images/shoucang.jpg" width="18" height="14" align="top"/>我的收藏</a> -->
				<a target="_blank"
					href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4009371688&aty=0&a=0&curl=&ty=1">
					<img src="images/qq.png" width="16" height="14" align="top" />QQ客服</a>
			</div>
			<div class="index_top_ret_ret">
				<img src="images/kefu.jpg" width="15" height="14" align="top" />客服:<span>400-937-1688</span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>
<!--======================logo开始============================-->
<div class="index_logo">
	<div class="index_logo_let">
		<a href="member/home.html"><img alt="logo"
			src="images/index_logo_jfh.jpg" /> </a> <a class="red-packet"
			href="qb/gohome.html" id="hbView" style="display:none;"> <img
			src="images/index/hongbao5.gif" alt="" /> </a>
	</div>
	<div></div>
	<!--======================搜索开始============================-->
	<div class="index_logo_ret">
		<div>
			<div class="index_logo_ret_sou">

				<%--        <c:if test="${searchParam != null and searchParam!=''}"><input name="" id="head_searchParam" type="text" value="${searchParam}"  onMouseOver="this.focus()" onMouseOut="if(this.value=='')this.value='红米';" onFocus="this.select()" onClick="if(this.value=='红米')this.value=''" class="main_logo_input_zi" /></c:if>
      <c:if test="${searchParam == null or searchParam!='' }"><input name="" id="head_searchParam" type="text" value="红米"  onMouseOver="this.focus()" onMouseOut="if(this.value=='')this.value='红米';" onFocus="this.select()" onClick="if(this.value=='红米')this.value=''" class="main_logo_input_zi" /></c:if>--%>
				<input name="" id="head_searchParam" onkeydown="keyDown(event)"
					type="text" value="红米" onMouseOver="this.focus()"
					onMouseOut="if(this.value=='')this.value='红米';"
					onFocus="this.select()" onClick="if(this.value=='红米')this.value=''"
					class="main_logo_input_zi" />
				<div id="head_like_search_show_box" style="position: relative;">
					<!-- <ul class="dropdown-menu">
                      <li>111111111111</li>
                      <li>222222222222</li>
                      <li>333333333333</li>
                      <li>4444444</li>
                      <li>4444444</li>
                      <li>4444444</li>
                    </ul> -->
				</div>
			</div>
			<div class="index_logo_ret_sou_ret">
				<a href="javascript:searchfn('手机');" class="search-box"> 搜手机 </a>
			</div>
			<div class="index_logo_ret_sou_ret">
				<a href="javascript:searchfn('配件');" class="search-box">搜配件 </a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="index_logo_ret_hot">
			<div class="index_logo_ret_hot_let">
				<a href="goods/paihang.html">热卖排行榜</a> |
			</div>
			<div class="index_logo_ret_hot_ret">
				热门搜索：<a href="javascript:goListView('手机','小米');">小米</a> <a
					href="javascript:goListView('手机','三星');">三星</a> <a
					href="javascript:goListView('手机','华为');">华为</a> <a
					href="javascript:goListView('手机','魅族');">魅族</a> <a
					href="javascript:goListView('手机','中兴');">中兴</a> <a
					href="javascript:goListView('手机','华为');">华为</a> <a
					href="javascript:goListView('手机','荣耀');">荣耀</a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>
<!--======================导航开始============================-->
<div class="index_daohang">
	<div class="index_daohang_box">
		<div class="index_daohang_let">
			<ul>
				<li><a href="member/home.html">首页</a></li>
				<li id="shopRush" style="display: none;"><a
					href="goods/toShopRush.html?s=menu" target="_blank"> <span
						class="icon-new">小米抢购</span> </a></li>
				<li><a href="goods/lj/3/bjd.html">智能机</a></li>
				<li><a href="goods/dz/2/bjd.html">合约机</a></li>
				<!-- <li><a href="http://www.3j1688.com/special_160701_/index.html">移动订货会</a></li> -->
				 <!-- <li><a href="goods/gnj/3/bjd.html">功能机</a></li>  -->

				<c:set var="areas"
					value="2184,2185,2186,2187,2188,2189,2190,2191,2192,2193,3275" />
				<%-- <c:if test="${fn:indexOf(areas,sessionInfo.area) < 0}">  --%>
				<%-- </c:if> --%>

				<%-- <c:if test="${sessionInfo.LoginName != '测试帐号'}">

				</c:if> --%>

				<!-- <li class="dropdown"><a href="goods/gnj/3/bjd.html"
					class="dropdown-toggle" data-toggle="dropdown">功能机 </a>
				</li> -->
				<li><a href="goods/pb/2/bjd.html">平板</a></li>
				<%--<li id="shopRush"
                    style="display: none;"><a href="goods/toShopRush.html?s=menu"
                                              target="_blank">
                    <span class="icon-new">小米抢购</span>
                </a></li>--%>
				<!--<li><a href="goods/ydhy/2/bjd.html">移动定制机</a></li>-->
				<!-- <li><a href="goods/lthy/2/bjd.html">联通定制机</a></li> -->
				<!-- <li><a href="goods/dxhy/2/bjd.html">电信定制机</a></li> -->
				<!-- <li><a href="goods/hotsale.html">热销商品</a></li> -->
				<li><a href="goods/bxjx/2/bjd.html">智能生活</a></li>
				<li class="dropdown"><a href="javascript:goListView('配件');"
					class="dropdown-toggle" data-toggle="dropdown">配件 <!-- <span class="caret"></span> -->
				</a> <!-- <ul class="dropdown-menu">
	          <li><a href="javascript:goListView('手环');">手环</a></li>
	          <li><a href="javascript:goListView('保护壳');">保护壳</a></li>
	          <li><a href="javascript:goListView('保护套');">保护套</a></li> 
	          <li><a href="javascript:goListView('保护膜');">保护膜</a></li>
	          <li><a href="javascript:goListView('电池');">电池</a></li>
	          <li><a href="javascript:goListView('充电器');">充电器</a></li>
	          <li><a href="javascript:goListView('充电宝');">充电宝</a></li>
	          <li><a href="javascript:goListView('耳机');">耳机</a></li>
	          <li><a href="javascript:goListView('数据线');">数据线</a></li>
	       </ul> --></li>
				<li><a href="javascript:goListView('手机');">综合查找</a></li>
				<li><a href="point/main.html">积分商城</a></li>
				<li><a href="cart/main.html">采购中心</a></li>
				<li><a target="_Blank"
					href="http://sjsm.tmall.com/search.htm?search=y&search=y&orderType=hotsell_desc&tsearch=y">天猫旗舰店</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<script type="text/javascript">
	$(window).scroll(
			function(e) {
				var winScroll = document.documentElement.scrollTop
						|| document.body.scrollTop;
				if (winScroll >= 200) {
					$(".index_daohang").addClass("fix_top");
				} else {
					$(".index_daohang").removeClass("fix_top");
				}
			});
</script>

<%
	} else {
%>
<!--======================登陆top开始============================-->
<div class="dl_top">
	<div class="dl_top_box">
		<div class="dl_top_box_let">
			<a href="/"><img src="images/register_logo.jpg" alt="logo" /> </a>
		</div>
		<div class="dl_top_box_ret">
			<ul>
				<!-- <li><a href="companyIntroduction.html">公司介绍 </a></li> -->
				<li><a href="groupIntroduction.html">帮助中心 </a></li>
				<li><a
					href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="dl_top_box_mid">
			客服：<span>400-937-1688</span>
		</div>
		<div class="clear"></div>
	</div>
</div>
<%
	}
%>
