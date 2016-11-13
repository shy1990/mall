<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>三际手机采购网</title>
<base href="<%=basePath%>"/>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.scroll-follow.js"></script>
<script type="text/javascript" src="js/myCenter/accountSecurity/editBankInfo.js"></script>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
</head>

<body onkeydown="keydown(event)">
<form action="member/updateMember.html" method="post">
<!--======================top开始============================-- >
<jsp:include page="/admin/common/follow.jsp"></jsp:include>
<jsp:include page="/admin/common/head.jsp"></jsp:include>

<!--======================详情图片开始============================-->
<div class="xq_mian">
   <jsp:include page="/admin/myCenter/left.jsp"></jsp:include>
  
  <div class="wodz_ret">
        <div class="wodz_ret_01_let_top">
           <div class="wodz_ret_01_let_top_let">编辑银行账户信息</div>
           <div class="clear"></div>
     </div>
      <div class="zhaq_main">
        <!-- <div class="zhxinxi_01">
           <div class="zhxinxi_01_let">资料完整度：</div>
           <div class="zhxinxi_01_mid">
             <div class="zhxinxi_01_mid_lv"></div>
          </div>
           <div class="clear"></div>
        </div> 
        <div class="zhxinxi_02"><img  style="margin-left:50px;" src="images/zhxx_sj.jpg" width="15" height="10" alt=""/></div>
        <div class="zhxinxi_03" style="line-height:18px; font-size:14px; color:#3D3D3D; margin-left:360px;width:45px;background-color:#e7fac3; padding-left:10px;">100%</div>-->
        <div class="zhxinxi_04">
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行开户行：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankDeposit"  value="${requestScope.member.bankDeposit}" onkeyup="javascript:void(0)"/></div>
             <div class="xgmm_03_ret_01" id="checkBankDeposit"></div>
             <div class="clear"> </div>
           </div>
           
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡号：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankCardNo"  value="${requestScope.member.bankCardNo}" onkeyup="javascript:void(0)"/></div>
             <div class="xgmm_03_ret_01" id="checkBankCardNo"></div>
             <div class="clear"> </div>
           </div>
           
           <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 银行卡户名：</div>
             <div class="zhxinxi_04_mid"><input type="text" name="bankAccount"  value="${requestScope.member.bankAccount}" onkeyup="javascript:void(0)"/></div>
             <div class="xgmm_03_ret_01" id="checkBankAccount"></div>
             <div class="clear"> </div>
           </div>
          <%-- <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 店铺名：</div><input type="hidden" id="pro" value="${requestScope.member.province}">
             <div class="zhxinxi_04_mid"><input type="text" name="username" style="border: 0px; float: right;" value="${requestScope.member.username}" readonly="readonly" /></div>
             <div class="xgmm_03_ret_01" id="checkUsername"></div>
             <div class="clear"> </div>
           </div>
        	
           <div class="xgmm_03">
             <div  class="zhxinxi_04_let"> 所在地区：</div>
             <div class="zhxinxi_04_cz_01" >
               <div class="zhxinxi_04_cz">
                    
                	<select name="province" id="province" onchange="queryCity()">
                	 <s:iterator value="#request.province" var="p">
					     <option value="<s:property value="#p.id"/>" <s:property value="hasSelected(#p.id,#request.selectedRegions)"/>><s:property value="#p.name"/></option> 
				     </s:iterator>
                	</select>
               </div>
               <div class="zhxinxi_04_cz">
                	<select name="city" id="city" onchange="queryArea()">
                	   <s:iterator value="#request.city" var="c">
					    <option value="<s:property value="#c.id"/>" <s:property value="hasSelected(#c.id,#request.selectedRegions)"/>><s:property value="#c.name"/></option> 
				     </s:iterator>
                	</select>
               </div>
               <div class="zhxinxi_04_cz">
                	<select name="area" id="area" >
                	<s:iterator value="#request.area" var="a">
					   <option value="<s:property value="#a.id"/>" <s:property value="hasSelected(#a.id,#request.selectedRegions)"/>><s:property value="#a.name"/></option>
				     </s:iterator>
                	</select>
               </div>
               <div class="clear"> </div>
             </div>
            <div class="xgmm_03_ret_01" id="checkArea"></div>
             <div class="clear"> </div>
          </div>
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 详细地址：</div>
             <div class="zhxinxi_04_mid"><input id="address" name="address" type="text" value="${requestScope.member.address }" style="border: 0px" readonly="readonly" /></div>
             <div class="xgmm_03_ret_01" id="checkAddress">*修改地址（半年修改一次，如需更改请联系客服人员）</div>
             <div class="clear"> </div>
          </div>
          
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人：</div>
             <div class="zhxinxi_04_mid"><input id="truename" name="truename" type="text" style="border: 0px" value="${requestScope.member.truename }" readonly="readonly" /></div>
             <div class="xgmm_03_ret" id="checkResult" style="color:red">*请认真填写，填写后不能修改 </div>
             <div class="clear"> </div>
          </div>
          <div class="xgmm_03">
             <div class="zhxinxi_04_let"> 收件人电话：</div>
            <div class="zhxinxi_04_mid"><input id="mobile" name="mobile" type="text" value="${requestScope.member.mobile }" style="border: 0px;" readonly="readonly"/></div>
             <div class="zhxinxi_04_mid_a" style="width:110px;border: 0px;" ></div>
             <div class="xgmm_03_ret" id="existMobile"></div>
             <div class="clear"> </div>
          </div> --%>
          <div class="zhxinxi_04_cz_02"><a href="javascript:forSubmit();">确认保存</a></div>
      </div>
      </div></div></div>
  <div class="clear"></div>
<!--======================bottom开始============================-->
<div id="footer">
 <jsp:include page="/admin/common/footer.jsp"></jsp:include>
</div>
</form>
</body>
</html>
