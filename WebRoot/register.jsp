<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<link href="<%=basePath%>css/index/css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register.js"></script>
</head>

<body onkeydown="keydown(event)">
<form action="member/register.html" name="form1" method="post">
<!--======================登陆top开始============================-->
<div class="dl_top">
  <div class="dl_top_box">
    <div class="dl_top_box_let"><a href="/"><img src="images/register_logo.jpg" alt="logo" /></a></div>
    <div class="dl_top_box_ret">
      <ul>
        <!-- <li><a href="companyIntroduction.html">公司介绍 </a></li> -->
        <li><a href="groupIntroduction.html">帮助中心 </a></li>
        <li><a href="tencent://message/?uin=480537383&Site=企业网站&Menu=yes">联系客服</a></li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="dl_top_box_mid">客服：<span>400-937-1688</span></div>
    <div class="clear"></div>
  </div>
</div>
<!--======================注册框开始============================-->
<div class="zc_box">
  <div class="zc_box_01">
    <div class="zc_box_02">
      <div class="zc_box_03">
        
        <div class="zc_box_07">
        <div class="zc_box_04">验证</div>
        <div class="zc_box_06">
        <div>
          <div class="zc_box_05">业务经理手机号码：</div>
          <div class="zc_box_mid"><input name="toMobile" id="mobile" type="text" onkeyup="checkMobile()"/></div>
          <div class="zc_box_right"><span id="timer" style="float:left; display: none"  ><input type="button" id="btn" value="获取验证码" style="border: 0px;width:110px; height: 33px;"/>  </span><a id="show" href="javascript:getExistPsw();" >获取验证码</a></div>
             <div class="clear"></div> 
         </div>
        </div>
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05">手机验证码：</div>
          <div class="zc_box_mid_01"><input name="verificationCode" type="text" id="code" onkeyup="checkCode()" /></div>
          <div class="clear"></div>
          
         </div>
        </div>
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05"></div>
         
	            <div id="message" style="margin-left: 210px;font-size: 14px;color: red;" >
	             <c:if test="${!empty error}">
	               <span>${error}</span>
	             </c:if>
	            </div>
          
          <div id="existCode" style="display: none">
          	<img src="images/huiyuan_dui.png" align="left" />手机验证码不能为空
          </div>
          <div class="zc_box_09">提示：如需注册，请拨打客服电话4009371688，由我方业务主管实地考察验证</div>
          <div class="clear"></div>
         </div>
        </div>
        </div>
        <div  id="existMobile"  style="display:none;float: left;position:absolute;margin-top: 120px;margin-left: 675px;">
        	<img src="images/huiyuan_dui.png" align="left" />手机格式错误
        </div>
        <div  id="existCodeRight"  style="display:none;float: left;position:absolute; margin-top: 180px;margin-left: 675px;">
        	<img src="images/huiyuan_dui.png" align="left" />手机验证码不能为空
        </div>
        <div class="zc_box_xp" id="showEmployeeImg"  style="display:none;">
        	<img id="emplyeeImg" src="" width="131" height="181" align="left" />
        	<br /><br />姓名：<span id="name"></span><br />
         		 职务：<span id="zhiwei"></span><br />
          		工牌号：<span id="gongpaihao"></span>
          		<input type="hidden" id="adminId" name="adminId" />
          		<input type="hidden" id="name" name="name" />
        </div>
<div class="clear"></div>
      </div>
      <div class="zc_box_03">
        <div class="zc_box_04">注册</div>
        <div class="zc_box_06">
        <div>
          <div class="zc_box_05">商铺名称：</div>
          <div class="zc_box_mid_01"><input type="text" name="username" id="username" onkeyup="checkUsername()" value="请勿填写地址信息!" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999"/></div>
          <div class="zc_box_tis">
              <span id="checkResult"></span>
          </div>
           <div class="clear"></div>
         </div>
         
        </div>
        
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05">手机号码：</div>
          <div class="zc_box_mid_01"><input type="text" id="regMobile" name="mobile" id="mobile" /></div>
          <div class="zc_box_tis">
            <span id="showInfoBox"></span>
          </div>
           <div class="clear"></div>
         </div>
        </div>
        <div class="zc_box_08">
             <div  class="zc_box_05"> 所在地区：</div>
             <div class="zc_box_mid_01" >
             <c:if test="${requestScope.member.province == null }">
               <div class="zhxinxi_04_cz">
                	<select name="province" id="province" onchange="queryCity()" >
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.province != null }">
               <div class="zhxinxi_04_cz" style="margin-top: 10px;">
               		<input name="province" id="province" type="text" value="${requestScope.member.pname}" style="border: 0px;" readonly="readonly"/>
               </div></c:if>
               <c:if test="${requestScope.member.city == null }">
               <div class="zhxinxi_04_cz">
                	<select name="city" id="city" onchange="queryArea()" >
                	   <option value="">----------请选择城市----------</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.city != null }">
               <div class="zhxinxi_04_cz" style="margin-top: 10px;margin-left: -30px">
               		<input name="city" id="city" type="text" value="${requestScope.member.cname}" style="border: 0px;" readonly="readonly"/>
               </div></c:if>
               <c:if test="${requestScope.member.area == null }">
               <div class="zhxinxi_04_cz">
                	<select name="area" id="area" onchange="queryTowns()" >
                		<option value="">----------请选择地区----------</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.area != null }">
               <div class="zhxinxi_04_cz" style="margin-top: 10px;margin-left: -30px;">
                    <input type="text" name="area" id="area" value="${requestScope.member.aname}" style="border: 0px;" readonly="readonly"/>
               </div></c:if>
                
               <c:if test="${requestScope.member.town == null }">
               <div class="zhxinxi_04_cz">
                	<select name="town" id="town" onchange="checkArea()" >
                		<option value="">----------请选择乡镇----------</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.town != null }">
               <div class="zhxinxi_04_cz" style="margin-top: 10px;margin-left: -30px;">
                    <input type="text" name="town" id="town" value="${requestScope.member.tname}" style="border: 0px;" readonly="readonly"/>
               </div></c:if>
              
               <div class="clear"> </div>
             </div>
            <div class="zc_box_tis" id="checkArea"></div>
            <div class="clear"> </div>
          </div>
          <div style="padding-top:25px;"></div>
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05">密码：</div>
          <div class="zc_box_mid_01"><input type="password" name="password" id="password" onkeyup="checkPassword()" ></div>
          <div class="zc_box_tis">
            <span id="showimgps"></span>
          </div>
           <div class="clear"></div>
         </div>
        </div>
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05">确认密码：</div>
          <div class="zc_box_mid_01"><input type="password" name="repassword" id="repassword" onkeyup="checkrepsw()"></div>
          <div class="zc_box_tis">
          
            <span id="showimgreps">
            
             
            </span>
          </div>
           <div class="clear"></div>
         </div>
        </div>
        <div class="zc_box_08">
        <div>
          <div class="zc_box_05"></div>
          <div class="zc_box_mid_anniu"><a href="javascript:toSubmit();">同意以下协议并注册</a></div>
          <div class="clear"></div>
         </div>
        </div>
      </div>
      <div class="zc_box_xieyi">
        <input type="checkbox" name="checkbox" id="checkbox" />
        查看<a href="memberAgreet.html" target="_blank">《服务协议》</a></div>
    </div>
  </div>
</div>

<!--======================bottom开始============================-->
<div>
<jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>
<div>此处显示新 Div 标签的内容</div>
</form>

</body>
</html>
