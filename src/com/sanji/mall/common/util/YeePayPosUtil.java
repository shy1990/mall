/**  
 * @Title: YeePayPosUtil.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2015-3-7 上午9:45:20
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.io.IOException;
import java.util.Map;

import org.jdom.JDOMException;

import com.sanji.mall.pojo.YeepayPosPojo;

/**
 * @ClassName: YeePayPosUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2015-3-7 上午9:45:20
 */
public class YeePayPosUtil {

	/**
	 * 易宝支付POS端数据签名验证
	 * 
	 * @Title: signatureVerification
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param xml
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @author ZhouZhangbao
	 */
	public static boolean signatureVerification(String xml) {
		try {
			int i = xml.indexOf("<COD-MS>");
			int y = xml.indexOf("</COD-MS>");
			xml = xml.substring(i + 8, y);
			int start = xml.indexOf("<HMAC>");
			int end = xml.indexOf("</HMAC>") + 7;
			String hmac = xml.substring(start, end);
			xml = xml.replace(hmac, "");
			hmac = hmac.replace("<HMAC>", "").replace("</HMAC>", "");
			String newHmac = MD5.encrypt(xml + ResourceUtil.getYeePayPosKEY());
			if (hmac.equals(newHmac)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 加密操作
	 * 
	 * @Title: signatureEncryption
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param xml
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String signatureEncryption(String xml) {
		xml = xml.replaceAll(" ", "").replace("\n", "");// 去除空格
		int i = xml.indexOf("<COD-MS>");
		int y = xml.indexOf("</COD-MS>");
		xml = xml.substring(i + 8, y);// 去掉头部
		int start = xml.indexOf("<HMAC>");
		int end = xml.indexOf("</HMAC>") + 7;
		// 得到旧的加密数据含格式
		String hmac = xml.substring(start, end);
		// 去除旧的加密数据
		String xmlNotHmac = xml.replace(hmac, "");
		// 得到旧的加密数据无格式
		hmac = hmac.replace("<HMAC>", "").replace("</HMAC>", "");
		// 需要加密的数据
		String xx = xmlNotHmac + ResourceUtil.getYeePayPosKEY();
		// System.out.println(xx);
		// 得到新的加密数据
		String newHmac = MD5.encrypt(xx);

		xml = xml.replace(hmac, newHmac);

		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><COD-MS>" + xml + "</COD-MS>";
		return xml;
	};

	/**
	 * 根据易宝返回的XML解析成实体类
	 * 
	 * @Title: getYeePayPosPojo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param xml
	 * @param @return 设定文件
	 * @return YeepayPosPojo 返回类型
	 * @author ZhouZhangbao
	 */
	public static YeepayPosPojo getYeePayPosPojo(String xml) {
		YeepayPosPojo pojo = new YeepayPosPojo();
		YeepayPosPojo sessionHead = new YeepayPosPojo();
		YeepayPosPojo sessionBody = new YeepayPosPojo();
		YeepayPosPojo headExtendAtt = new YeepayPosPojo();
		YeepayPosPojo bodyExtendAtt = new YeepayPosPojo();
		try {
			Map<String, String> map = XmlUtil.xml2Map(xml);
			// Head
			sessionHead.setVersion(map.get("COD-MS.SessionHead.Version"));
			sessionHead.setServiceCode(map.get("COD-MS.SessionHead.ServiceCode"));
			sessionHead.setTransactionID(map.get("COD-MS.SessionHead.TransactionID"));
			sessionHead.setSrcSysID(map.get("COD-MS.SessionHead.SrcSysID"));
			sessionHead.setDstSysID(map.get("COD-MS.SessionHead.DstSysID"));
			sessionHead.setReqTime(map.get("COD-MS.SessionHead.ReqTime"));
			sessionHead.setHMAC(map.get("COD-MS.SessionHead.HMAC"));
			// head 中的 ExtendAtt
			headExtendAtt.setEmployee_ID(map.get("COD-MS.SessionHead.ExtendAtt.Employee_ID"));
			headExtendAtt.setEmployee_Name(map.get("COD-MS.SessionHead.ExtendAtt.Employee_Name"));
			headExtendAtt.setCompany_Code(map.get("COD-MS.SessionHead.ExtendAtt.Company_Code"));
			headExtendAtt.setCompany_Name(map.get("COD-MS.SessionHead.ExtendAtt.Company_Name"));
			// Body
			sessionBody.setEmployee_ID(map.get("COD-MS.SessionBody.Employee_ID"));
			sessionBody.setPassword(map.get("COD-MS.SessionBody.Password"));
			sessionBody.setOrder_No(map.get("COD-MS.SessionBody.Order_No"));
			// body 中的 extendAtt
			String orderId = map.get("COD-MS.SessionBody.ExtendAtt.OrderNo") == null ? map.get("COD-MS.SessionBody.ExtendAtt.Order_No") : map
					.get("COD-MS.SessionBody.ExtendAtt.OrderNo");
			bodyExtendAtt.setOrder_No(orderId);
			bodyExtendAtt.setBankCardNo(map.get("COD-MS.SessionBody.ExtendAtt.BankCardNo"));
			bodyExtendAtt.setBankOrderNo(map.get("COD-MS.SessionBody.ExtendAtt.BankOrderNo"));
			bodyExtendAtt.setAMT(map.get("COD-MS.SessionBody.ExtendAtt.AMT"));
			bodyExtendAtt.setPay_Type(map.get("COD-MS.SessionBody.ExtendAtt.Pay_Type"));
			bodyExtendAtt.setBank_OrderId(map.get("COD-MS.SessionBody.ExtendAtt.Bank_OrderId"));
			bodyExtendAtt.setYeepay_OrderId(map.get("COD-MS.SessionBody.ExtendAtt.Yeepay_OrderId"));
			bodyExtendAtt.setChequeNo(map.get("COD-MS.SessionBody.ExtendAtt.ChequeNo"));
			bodyExtendAtt.setEmployee_ID(map.get("COD-MS.SessionBody.ExtendAtt.Employee_ID"));
			bodyExtendAtt.setEmployee_Name(map.get("COD-MS.SessionBody.ExtendAtt.Employee_Name"));
			bodyExtendAtt.setCompany_Code(map.get("COD-MS.SessionBody.ExtendAtt.Company_Code"));
			bodyExtendAtt.setCompany_Name(map.get("COD-MS.SessionBody.ExtendAtt.Company_Name"));
			bodyExtendAtt.setSign_Self_Flag(map.get("COD-MS.SessionBody.ExtendAtt.Sign_Self_Flag"));
			bodyExtendAtt.setSign_Name(map.get("COD-MS.SessionBody.ExtendAtt.Sign_Name"));
			bodyExtendAtt.setSign_Tel(map.get("COD-MS.SessionBody.ExtendAtt.Sign_Tel"));

			sessionHead.setExtendAtt(headExtendAtt);
			sessionBody.setExtendAtt(bodyExtendAtt);
			pojo.setSessionHead(sessionHead);
			pojo.setSessionBody(sessionBody);

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pojo;
	}

}
