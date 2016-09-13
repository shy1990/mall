<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<base href="<%=basePath%>"/>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/myCenter/accountSecurity/userInfo.js"></script>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
</head>

<body onkeydown="keydown(event)">
<form action="member/updateMember.html" method="post">
<!--======================top开始============================-->
<jsp:include page="/admin/common/follow.jsp"></jsp:include>
<jsp:include page="/admin/common/head.jsp"></jsp:include>

<!--======================详情图片开始============================-->
<div class="xq_mian">
   <jsp:include page="/admin/myCenter/left.jsp"></jsp:include>
  
  <div class="wodz_ret">
        <div class="wodz_ret_01_let_top">
           <div class="wodz_ret_01_let_top_let">账户信息</div>
           <div class="clear"></div>
     </div>
      <div class="zhaq_main">
       <c:if test="${requestScope.member.truename == null || requestScope.member.mobile == null || requestScope.member.ditchEncode == null ||  requestScope.member.bankDeposit == null || requestScope.member.bankCardNo == null || requestScope.member.bankAccount == null || requestScope.member.comPhone == null}">
        <div class="zhxinxi_01">
           <div class="zhxinxi_01_let">资料完整度：</div>
           <div class="zhxinxi_01_mid">
             <div class="zhxinxi_01_mid_lv"></div>
          </div>
           <div class="clear"></div>
        </div>
         
        <div class="zhxinxi_02"><img src="images/zhxx_sj.jpg" width="15" height="10" alt=""/></div>
        <div class="zhxinxi_03">80%，完成手机绑定+20% </div></c:if>
      <c:if test="${requestScope.member.truename != null && requestScope.member.mobile != null && requestScope.member.ditchEncode != null && requestScope.member.bankDeposit != null && requestScope.member.bankCardNo != null && requestScope.member.bankAccount != null && requestScope.member.comPhone != null}">
       <div class="zhxinxi_01">
           <div class="zhxinxi_01_let">资料完整度：</div>
           <div class="zhxinxi_01_mid">
             <div class="zhxinxi_01_mid_lv" style="width:100%; background-color:#a1eb0f;height:10px;"></div>
          </div>
           <div class="clear"></div>
        </div>
        
        <div class="zhxinxi_02"><img  style="margin-left:50px;" src="images/zhxx_sj.jpg" width="15" height="10" alt=""/></div>
        <div class="zhxinxi_03" style="line-height:18px; font-size:14px; color:#3D3D3D; margin-left:360px;width:45px;background-color:#e7fac3; padding-left:10px;">100%</div></c:if>
        <!-- <a href="member/modifyUserInfo.html"><input type="button" value="编辑"/></a> 备用-->
        <div class="zhxinxi_04">
           
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 店铺名：</div><input type="hidden" id="pro" value="${requestScope.member.province}"/>
             <div class="zhxinxi_04_mid"><input type="text" name="username" style="border: 0px; float: right;" value="${requestScope.member.username}" readonly="readonly" /></div>
             <div class="xgmm_03_ret_01" id="checkUsername"></div>
             <div class="clear"> </div>
           </div>
        	
           <div class="xgmm_03">
             <div  class="zhxinxi_04_let"> 所在地区：</div>
             <div class="zhxinxi_04_cz_01" >
             <c:if test="${requestScope.member.province == null }">
               <div class="zhxinxi_04_cz">
                	<select name="province" id="province" onchange="queryCity()">
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.province != null }">
               <div class="zhxinxi_04_cz" style="margin-top:1px;width: 82px">
               		 <input type="text" value="${requestScope.member.pname}" style="border: 0px;height: 32px" readonly="readonly"/>
               		  <input type="hidden" name="province" id="province" value="${requestScope.member.province}" style="border: 0px;" readonly="readonly"/>  
               </div></c:if>
               <c:if test="${requestScope.member.city == null }">
               <div class="zhxinxi_04_cz">
                	<select name="city" id="city" onchange="queryArea()">
                	   <option value="">--请选择--</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.city != null }">
               <div class="zhxinxi_04_cz" style="margin-top:1px;margin-left: -30px;width: 82px">
               		 <input type="text" value="${requestScope.member.cname}" style="border: 0px;height: 32px" readonly="readonly"/> 
               		  <input type="hidden" name="city" id="city"  value="${requestScope.member.city}" style="border: 0px;" readonly="readonly"/> 
               </div></c:if>
               <c:if test="${requestScope.member.area == null }">
               <div class="zhxinxi_04_cz">
                	<select name="area" id="area" >
                		<option value="">--请选择--</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.area != null }">
               <div class="zhxinxi_04_cz" style="margin-top:1px; margin-left: -30px;width: 82px">
                    <input type="text" value="${requestScope.member.aname}" style="border: 0px;height: 32px" readonly="readonly" /> 
                    <input type="hidden" name="area" id="area" value="${requestScope.member.area}" style="border: 0px;" readonly="readonly" /> 
               </div></c:if>
               
                <c:if test="${requestScope.member.town == null }">
               <div class="zhxinxi_04_cz">
                	<select name="town" id="town" >
                		<option value="">--请选择乡镇--</option>
                	</select>
               </div></c:if>
               <c:if test="${requestScope.member.town != null }">
               <div class="zhxinxi_04_cz" style="margin-top:1px; margin-left: -30px;width: 82px">
                    <input type="text" value="${requestScope.member.tname}" style="border: 0px;height: 32px" readonly="readonly" /> 
                    <input type="hidden" name="town" id="town" value="${requestScope.member.town}" style="border: 0px;" readonly="readonly" /> 
               </div></c:if>
               <div class="clear"> </div>
             </div>
            <div class="xgmm_03_ret_01" id="checkArea"></div>
             <div class="clear"> </div>
          </div>
          <c:if test="${requestScope.member.address == null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 详细地址：</div>
             <div class="zhxinxi_04_mid"><input name="address" type="text" onkeyup="checkAddress()" style="border: 0px;"/></div>
             <div class="xgmm_03_ret_01" id="checkAddress">（半年修改一次，如需更改请联系客服人员）</div>
             <div class="clear"> </div>
          </div></c:if>
          <c:if test="${requestScope.member.address != null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 详细地址：</div>
             <div class="zhxinxi_04_mid"><input id="address" name="address" type="text" value="${requestScope.member.address }" style="border: 0px" readonly="readonly" /></div>
             <div class="xgmm_03_ret_01" id="checkAddress" style="color:black">（半年修改一次，如需更改请联系客服人员）</div>
             <div class="clear"> </div>
          </div></c:if>
          
          <c:if test="${requestScope.member.truename != null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人：</div>
             <div class="zhxinxi_04_mid"><input id="truename" name="truename" type="text" style="border: 0px" value="${requestScope.member.truename }" readonly="readonly" /></div>
             <div class="xgmm_03_ret" id="checkResult" style="color:black">（请认真填写，填写后不能修改） </div>
             <div class="clear"> </div>
          </div></c:if>
          
          <c:if test="${requestScope.member.truename == null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人：</div>
             <div class="zhxinxi_04_mid"><input id="truename" name="truename" type="text"  value="${requestScope.member.truename }" style="border: 0px;" /></div>
             <div class="xgmm_03_ret" id="checkResult" style="color:black">（请认真填写，填写后不能修改 ）</div>
             <div class="clear"> </div>
          </div> </c:if>
          <c:if test="${requestScope.member.mobile != null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人电话：</div>
            <div class="zhxinxi_04_mid"><input id="mobile" name="mobile" type="text" value="${requestScope.member.mobile }" style="border: 0px;" readonly="readonly"/></div>
             <!-- <div class="zhxinxi_04_mid_a" style="width:110px;border: 0px;" ></div> -->
             <div class="xgmm_03_ret" id="existMobile" style="color:red;">*以上为必填项</div>
             <div class="clear"> </div>
          </div>
          </c:if>
          <c:if test="${requestScope.member.mobile == null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人电话：</div>
            <div class="zhxinxi_04_mid"><input id="mobile" name="mobile" type="text" value="${requestScope.member.mobile }" style="border: 0px;" /></div>
             <!-- <div class="zhxinxi_04_mid_a" style="width:110px;border: 0px;" ></div> -->
             <div class="xgmm_03_ret" id="existMobile" style="color:red;">*以上为必填项</div>
             <div class="clear"> </div>
          </div>
          </c:if>
            <c:if test="${requestScope.member.ditchEncode == null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 渠道编码：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="ditchEncode" style="border: 0px;" value="${requestScope.member.ditchEncode}" readonly="readonly" onkeyup="checkDitchEncode()"/></div>
             <div class="xgmm_03_ret_01" id="checkDitchEncode"></div>
             <div class="clear"> </div>
           </div>
           </c:if>
           <c:if test="${requestScope.member.ditchEncode != null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 渠道编码：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="ditchEncode" style="border: 0px; float: right;" value="${requestScope.member.ditchEncode}" readonly="readonly"/></div>
             <div class="clear"> </div>
           </div>
           </c:if>
           
           <c:if test="${requestScope.member.bankDeposit == null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行开户行：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankDeposit" style="border:0px;color: gray;"   value="${requestScope.member.bankDeposit}" readonly="readonly" onfocus="cleanBankDeposit()" onkeyup="checkBankDeposit()"/></div>
             <div class="xgmm_03_ret_01" id="checkBankDeposit"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;（请精确到支行名称）</div>
             <div class="clear"> </div>
           </div>
           </c:if>
           <c:if test="${requestScope.member.bankDeposit != null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行开户行：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankDeposit" style="border:0px; float: right;" value="${requestScope.member.bankDeposit}" readonly="readonly"/></div>
             <div class="xgmm_03_ret_01" id="checkBankDeposit"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;（请精确到支行名称）</div>
             <div class="clear"> </div>
           </div>
           </c:if>
           
           <c:if test="${requestScope.member.bankCardNo == null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡号：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankCardNo" style="border:0px;color: gray;"   value="${requestScope.member.bankCardNo}" readonly="readonly" onfocus="cleanBankCardNo()" onkeyup="checkBankCardNo()"/></div>
             <div class="xgmm_03_ret_01" id="checkBankCardNo"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;（此卡号为退款卡号。为保证账号安全，请勿泄露账号密码。）</div>
             <div class="clear"> </div>
           </div>
           </c:if>
           <c:if test="${requestScope.member.bankCardNo != null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡号：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankCardNo" style="border:0px;float: right;" value="${requestScope.member.bankCardNo}" readonly="readonly"/></div>
             <div class="xgmm_03_ret_01" id="checkBankCardNo"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;（此卡号为退款卡号。为保证账号安全，请勿泄露账号密码。）</div>
             <div class="clear"> </div>
           </div>
           </c:if>
           
           <c:if test="${requestScope.member.bankAccount == null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡户名：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankAccount" style="border:0px;color: gray;"   value="${requestScope.member.bankAccount}" readonly="readonly" onfocus="cleanBankAccount()" onkeyup="checkBankAccount()"/></div>
             <div class="xgmm_03_ret_01" id="checkBankAccount"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;</div>
             <div class="clear"> </div>
           </div>
           </c:if>
           <c:if test="${requestScope.member.bankAccount != null }">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡户名：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankAccount" style="border:0px;float: right;" value="${requestScope.member.bankAccount}" readonly="readonly"/>
             <div class="xgmm_03_ret_01" id="checkBankAccount"></div><div style="margin-left: -500px;color:black;">&nbsp;&nbsp;&nbsp;</div>
             <div class="clear"> </div>
           
           </div>
             
           </div>
           </c:if>
           <c:if test="${requestScope.member.comPhone == null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 常用手机号：</div>
             <div class="zhxinxi_04_mid"><input name="comPhone" type="text" onkeyup="checkComPhone()" readonly="readonly" style="border: 0px"/></div>
             <div class="xgmm_03_ret_01" id="checkComPhone"></div>
             <div class="clear"> </div>
          </div></c:if>
          <c:if test="${requestScope.member.comPhone != null }">
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 常用手机号：</div>
             <div class="zhxinxi_04_mid"><input id="comPhone" name="comPhone" type="text" value="${requestScope.member.comPhone }" style="border: 0px" readonly="readonly" /></div>
             <div class="xgmm_03_ret_01" id="checkComPhone"></div>
             <div class="clear"> </div>
          </div></c:if>
         
          <c:if test="${requestScope.member.truename == null || requestScope.member.address == null}">
             <div class="zhxinxi_04_cz_02"><a href="javascript:forSubmit()">确认保存</a></div>
          </c:if>
           <c:if test="${requestScope.member.truename != null || requestScope.member.address != null}">
             <div class="zhxinxi_04_cz_02"><a href="member/modifyUserInfo.html">完善编辑</a></div>
          </c:if>
      </div>
      </div>
  </div>
  <div class="clear"></div>
</div>
<!--======================bottom开始============================-->
<div id="footer">
 <jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>
</form>
</body>
</html>
