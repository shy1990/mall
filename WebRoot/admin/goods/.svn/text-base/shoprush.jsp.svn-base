<%--
  Created by IntelliJ IDEA.
  User: barton
  Date: 16-3-4
  Time: 下午1:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <title>热销专区首页</title>
    <link rel="stylesheet" type="text/css" href="css/shoprush/shopping.css"/>
    <link rel="stylesheet" type="text/css"
          href="js/shoprush/Scrollbar/jquery.mCustomScrollbar.min.css"/>
    <style type="text/css">
        #background {
            position: absolute;
            z-index: 9998;
            top: 0px;
            left: 0px;
            background: rgb(50, 50, 50);
            background: rgba(0, 0, 0, 0.5);
            display: none;
        }

        #content {
            position: absolute;
            width: 590px;
            z-index: 9999;
            background: #fff;
            border-radius: 5px;
            display: none;
        }

        .up-box {
            width: 590px;
            position: absolute;
            border-radius: 5px;
            top: 0px;
            left: 0px;
            z-index: 20000;
        }

        .up-box-hd {
            background: #D9534F;
            height: 37px;
            line-height: 35px;
            position: relative;
            background-position: 0px -350px;
            border-radius: 5px 5px 0px 0px;
            padding: 0px 20px;
            font-size: 16px;
            color: #E5F8FF;
        }

        .up-box-bd {
            background: #FFF none repeat scroll 0% 0%;
            border-radius: 0px 0px 5px 5px;
            padding: 20px 60px;
            line-height: 25px;
        }

        .up-box-bd .icon {
            float: left;
            margin-right: 25px;
        }

        .up-con {
            height: 66px;
        }

        .up-con .clearfix {
            display: block;
        }

        .up-box-bd .r-txt {
            width: 360px;
            margin-top: 10px;
            float: left;
            color: #666;
        }

        .ft14 {
            font-size: 14px;
            padding: 0;
            margin: 0;
        }

        .up-box-bd .lay-btn {
            padding: 20px 0px 0px;
        }

        .lay-btn {
            padding: 10px 0px;
            text-align: center;
        }

        .lay-btn a {
            margin: 0px 10px;
            outline: 0px none;
            text-decoration: none;
        }

        .btn92 {
            width: 90px;
            display: inline-block;
            text-align: center;
            color: #333;
            border-radius: 4px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #CCC;
            background-position: 0px -100px;
            font-size: 12px;
        }
    </style>
</head>
<body class="placement">
<input type="hidden" id="skuId" value=""/>
<div class="top-box">
    <div class="container">
        <a id="top" href="http://www.3j1688.com/member/home.html"><img
                src="images/shoprush/logo.png"/></a>
    </div>
</div>
<div class="container">
    <!--种类分栏-->
    <div class="column" id="goodColumn">
        <ul class="nav">

        </ul>
    </div>
    <!--种类分栏-->
    <div class="content">
        <div class="phone-box">
            <img id="goodPic" class="phone-img" src=""/>
        </div>
        <!---->
        <!--手机信息-->
        <div class="phonetinfo">
            <h1 id="goodName"></h1>
            <h2 id="goodPrice"></h2>
            <!--版本-->
            <h3>1.选择版本</h3>
            <div class="box-1">
                <ul class="navber" id="goodVersion">

                </ul>
            </div>
            <!--版本-->
            <!--颜色-->
            <h3>2.选择颜色</h3>
            <div class="box-1">
                <ul class="navber" id="goodColor">
                </ul>
            </div>
            <!--颜色-->
            <h3>3.选择数量</h3>
            <div class="count-box">
                <div class="amount-box">
                    <span class="btn J_minus"><i class="icon icon-minus-out"></i></span>
                    <input type="text" id="" value="1" readonly="readonly"
                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                           onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span class="btn J_plus"><i class="icon icon-plus "></i></span>
                </div>
                <span>部</span>
                <span class="total" style="display:none;">库存 <span id="goodStock" class="text-red">
                </span> 部
                </span>
                <span class="max-count">限购 <span id="canByNum" class="text-red">  </span> 部
                    </span>
            </div>
            <!--加入购物车-->
            <div class="btn btn-cart">加入购物车</div>
            <div style="float:right;margin:0px 0px 30px 0px;color: #FF3040;">温馨提示:不同型号、不同颜色请分开下单
            </div>
            <div style="clear:both"></div>
            <%--<div id="timer-count-down" class="btn btn-cart2 disabled">
                <strong><span id="day_show">0</span>天</strong>
                <strong><span id="hour_show">00</span>时</strong>
                <strong><span id="minute_show">00</span>分</strong>
                <strong><span id="second_show">00</span>秒</strong>
            </div>--%>
            <!--加入购物车-->
        </div>
        <!--手机信息-->
    </div>
</div>
<hr/>
<div id="goodDetail" class="goodDetail">

</div>
<!--左侧购物显示 浮动-->
<div class="cart-show ">
    <div class="show-box">
        <!--go-cart-->
        <div class="go-cart active">
            <span class="tag tag-cart"></span> 购物车
            <%--<span class="tag tag-num">0</span>--%>
        </div>
        <!--去结算-->
        <div class="go-balance ">
            去结算
            <span class="tag tag-goright"></span>
        </div>
        <!--去结算-->
    </div>
    <!--go top
    <a href="#top"><span class="icon icon-go-top"></span></a>-->
    <!--go top-->
    <!--结算按钮-->
    <div class="settlement">
        <div class="settlement-top">
            已选：<span id="totalNum" class="total-num-phone">0 </span> 部手机
            <span class="pull-right">￥<span id="totalPrice" class="totalprice">0</span></span>
        </div>
        <a href="javascript:;" class="J_Settlement disabled">结 算 <span
                class="tag tag-goright"></span></a>
    </div>
    <!--结算按钮-->

    <!--购物车-->
    <div class="shopping-cart mCustomScrollbar light" data-mcs-theme="minimal-dark">
        <!--购物车内容-->
        <div class="cart-content">
            <!--全选-->
            <div class="check-all">
                <label>
                    <input type="checkbox" id="checkAll" value=""/>全选
                </label>
            </div>
            <!--全选-->
            <div id="contents" class="all">
                <p class="nothing-text">呜呜，来都来了，不买点东西吗？</p>
                <img class="nothing-img" src="images/shoprush/nothing.png"/>
            </div>
        </div>
        <!--购物车内容-->
    </div>

</div>
<!--左侧购物显示-->

<form id="payForm" action="order/hd/orders.html" method="post" target="_blank">
    <input type="hidden" id="hdSkus" name="hdSkus" value=""/>
</form>
<!-- 弹窗询问是否完成支付 -->
<div id="background"></div>
<div id="dialog" style="display: none">
    <div dhxbox="1" class="up-box w600" style="position: fixed; left: 35%; top: 30%;">
        <div class="up-box-hd">网上支付提示
            <div class="" style="float: right;" onclick="logout();">关闭</div>
        </div>
        <div style="clear:both;"></div>
        <div class="up-box-bd">
            <div class="up-con">
						<span class="icon">
							<img src="images/working.gif"/>
						</span>
                <div class="r-txt">
                    <p class="ft14">支付完成前，请不要关闭此支付验证窗口。
                        <br clear="none"/> 支付完成后，请根据您支付的情况点击下面按钮。
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="lay-btn">
                <a href="javascript:" class="btn92" id="pay_error" shape="rect">支付遇到问题</a>
                <a href="javascript:" class="btn92" id="pay_success" shape="rect">支付完成</a>
            </div>
        </div>
    </div>

    <!-- <div style="text-align:center;margin-bottom:20px;">
        <dir style="margin-bottom:20px;"><h2>是否支付完成</h2></dir>
        <input type="button" id="pay_success" value="支付成功" />&nbsp;&nbsp;<input type="button" id="pay_error" value="支付失败" />
    </div> -->
</div>

<script type="text/javascript">
    //弹窗
    function conPosition() {
        var oBackground = document.getElementById("background");
        var dw = $(document).width();
        var dh = $(document).height();
        oBackground.style.width = dw + 'px';
        oBackground.style.height = dh + 'px';
        var oContent = document.getElementById("dialog");
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
        var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 3) + scrollTop;
        oContent.style.left = l + 'px';
        oContent.style.top = t + 'px';
    }

    //弹框询问是否退出
    function logout() {
        location.href = "http://www.3j1688.com/myCenter/index.html";
        $("#background, #dialog").hide();
    }
</script>

<script type="text/javascript" src="js/jquery-1.8.0.min.js" charset="utf-8"></script>
<!--[if lt IE 9]>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript" src="js/shoprush/Scrollbar/jquery-ui-1.10.4.min.js"
        charset="utf-8"></script>
<script src="js/shoprush/Scrollbar/jquery.mousewheel.min.js" type="text/javascript"
        charset="utf-8"></script>
<script src="js/shoprush/Scrollbar/jquery.mCustomScrollbar.min.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript" src="js/dateutil.js" charset="utf-8"></script>
<script type="text/javascript" src="js/shoprush/screeningofgoods.js" charset="utf-8"></script>
<script type="text/javascript" src="js/shoprush/shoppingcartevent.js" charset="utf-8"></script>
<script type="text/javascript" src="js/shoprush/shoppingcart.js" charset="utf-8"></script>
<script type="text/javascript" src="js/shoprush/shoprush.js" charset="utf-8"></script>

<script type="text/javascript" src="js/shoprush/sanji-timer-count-down.js" charset="utf-8"></script>

</body>
</html>
