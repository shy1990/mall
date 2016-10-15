<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />


</head>

<body>
<form>
<!--======================登陆top开始============================-->
<div class="dl_top">
  <div class="dl_top_box">
    <div class="dl_top_box_let"></div>
    <div class="dl_top_box_ret">
      <ul>
        <li><a href="#">公司介绍 </a></li>
        <li><a href="#">帮助中心 </a></li>
        <li><a href="#">联系客服</a></li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="dl_top_box_mid">客服：<span>0531-67860006</span></div>
    <div class="clear"></div>
  </div>
</div>
<!--======================注册框开始============================-->
<div class="about_mian">
  <jsp:include page="../common/aboutNav.jsp"></jsp:include>
  <div class="about_mian_ret">
    <div class="about_mian_ret_01">
      <p> 交易进行中买家申请退货退款操作流程</p>
      <p> <span>交易中买家申请退款并需要退货的流程，共计三个步骤（不包含未收到货/不需要退货的）:</span></p>
    </div>
    <div class="about_mian_ret_02">
      <p>1、进入“已买到的宝贝”，点击相应交易中的“退货/退款”，按照页面提示填写内容并提交。</p>
      <p>2、提交成功之后您需要等待卖家在5天内做出响应，在此期间您可点击“退款处理中”查看退款的详细信息</p>
      <p>1、买家需要在卖家同意退款申请的7天内完成退货，否则会导致退款关闭。请您在“已买到的宝贝”页面点击“请退货”，根据卖家在页面上提供的退货地址完成退货（除非卖家同意，否则不可使用平邮和到付）。 </p>
      <p>2、退货后，请务必到退款详情页面“填写退货信息”（即物流公司的名称和运单号码）</p>
      <p>提醒：退货信息一旦提交将无法修改，若您填写错误可能导致运费险无法理赔！</p>
      <p><h4>温馨提示：</h4></p>
      <p>1、如果您遇到未收到货、邮费问题，请先联系卖家协商；卖家联系不上，建议您进入我的淘宝—“已买到的宝贝”页面点击【退货退款】。</p>
      <p>2、如果您申请了【退货/退款】或者【申请售后】，卖家不予处理的，系统会根据页面倒计时间自动达成协议；</p>
      <p>如果卖家不提供退货地址、拒绝退款协议且双方无法协商一致时，您可以根据页面提示点击申请【淘宝客服介入处理】。</p>
    </div>
  </div>
  <div class="clear"></div>
</div>

<!--======================bottom开始============================-->
<div class="bottom">
  <div class="bottom_box">
    <div class="bottom_box_01">
      <div class="bottom_box_02">
         <ul>
           <li>
                <div><img src="images/mian.jpg" /></div>
                <div><a href="#">退货免费</a></div>
           </li>
           <li>
                <div><img src="images/xiu.jpg" width="34" height="36" /></div>
                <div><a href="#">保修两年</a></div>
           </li>
           <li>
                <div><img src="images/hoc.jpg" width="38" height="36" /></div>
                <div><a href="#">货到付款</a></div>
           </li>
           <li>
                <div><img src="images/qian.jpg" width="35" height="36" />`</div>
                <div><a href="#">免费配送 </a></div>
           </li>
           <li>
                <div><img src="images/huan.jpg" width="34" height="36" /></div>
                <div><a href="#">七天无理由退换货 </a></div>
           </li>
           <li>
                <div><img src="images/shijan.jpg" width="35" height="36" /></div>
                <div><a href="#">30天换货</a></div>
           </li>
           <li>
                <div><img src="images/bao.jpg" width="31" height="34" /></div>
                <div><a href="#">品质保障 </a></div>
           </li>
         </ul>
      </div>
      <div class="bottom_box_04">
         <ul>
           <li>
                <div class="bottom_box_05">客户服务 </div>
                <div><a href="#">客服中心</a>  / <a href="#"> 申请售后</a> <br />
               <a href="#"> 售后流程</a> / <a href="#"> 调换货说明</a></div>
           </li>
           <li>
                <div class="bottom_box_05">新手指南</div>
                <div><a href="#">新手专区</a>  /<a href="#">  购物下单</a><br />
                <a href="#">支付货款</a> /  <a href="#"> 会员协议</a></div>
           </li>
           <li>
                <div class="bottom_box_05">支付方式</div>
                <div><a href="#">在线支付</a>  /<a href="#">  货到付款</a><br />
                <a href="#"> 关于发票</a> /<a href="#">  支付帮助</a></div>
           </li>
           <li>
                <div class="bottom_box_05">积分商城</div>
                <div><a href="#">积分规则</a>  / <a href="#"> 礼品兑换</a></div>
           </li>
           <li>
                <div class="bottom_box_05">公司介绍</div>
                <div><a href="#">了解三际</a>  /<a href="#">  平台优势</a></div>
           </li>
         </ul>
      </div>
      <div class="bottom_box_03"> <a href="#">三际公告</a> |   <a href="#">招纳贤士 </a> |   <a href="#">联系我们</a> |  客服热线：0531-6666666<br />
        Copyright © 2006 - 2014 三际版权所有   鲁ICP备06024200号   南市公安局宝山分局备案编号：3101130646</div>
    </div>
  </div>
</div>
<div>此处显示新 Div 标签的内容</div>
</form>
</body>
</html>
  