<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/>

<div class="zhifu_05 pay_type_box">
	<div class="zhifu_06 huo_dao_fu_kuan_22">
		<table width="1130" height="92" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="ICBC-NET-B2C" checked="checked" /></td>
				<td width="10"><img src="images/yeePayLogo/acbc.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="CMBCHINA-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/cmbc.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="ABC-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/abc.gif" width="137"
					height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="CCB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/cbc.gif" width="137"
					height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="BCCB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/beijing.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="BOCO-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/jiaotong.gif"
					width="137" height="30" alt="" /></td>
			</tr>
			<tr>


				<td width="10"><input type="radio" name="frpId" id="radio"
					value="CIB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/xingye.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="CMBC-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/cmsb.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="CEB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/guangda.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="BOC-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/bc.gif" width="137"
					height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="PINGANBANK-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/pingan.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="ECITIC-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/zhongxin.gif"
					width="137" height="30" alt="" /></td>
			</tr>

			<tr>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="SDB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/shenfa.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="GDB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/guangfa.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="SHB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/shanghaibank.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="SPDB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/shangpufa.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="POST-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/youzheng.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="BJRCB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/nongcunshangye.gif"
					width="137" height="30" alt="" /></td>
			</tr>
			<tr>

				<td width="10"><input type="radio" name="frpId" id="radio"
					value="HXB-NET-B2C" /></td>
				<td width="10"><img src="images/yeePayLogo/huaxia.gif"
					width="137" height="30" alt="" /></td>
				<td width="10"><input type="radio" name="frpId" id="radio"
					value="1000000-NET" /></td>
				<td width="10"><img src="images/yeePayLogo/hykj.jpg"
					width="137" height="30" alt="" /></td>
			</tr>
		</table>
	</div>
	
	<c:if test='${sessionInfo.userId ne requestScope.blackId }'>
	       <div class="zhifu_07 huo_dao_fu_kuan">
				<input type="radio" name="frpId" id="hdfk" value="SJ-HDFK" /> <label
					for="radio2">货到付款</label> 
			</div>
	</c:if>
	
</div>

