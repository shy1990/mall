/**  
* @Title: YeepayPosPojo.java
* @Package com.sanji.mall.pojo
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2015-3-10 下午4:17:48
* @version V1.0  
*/
package com.sanji.mall.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @ClassName: YeepayPosPojo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2015-3-10 下午4:17:48
 */
@XStreamAlias("COD-MS")
public class YeepayPosPojo {

	private YeepayPosPojo SessionHead;
	private YeepayPosPojo SessionBody;
	
	private String Version;
	private String ServiceCode;
	private String TransactionID;
	private String SrcSysID;
	private String DstSysID;
	private String ReqTime;
	private YeepayPosPojo ExtendAtt;
	private String HMAC;
	
	
	private String Employee_ID;
	private String Password;
	
	private String Employee_Name;
	private String Company_Code;
	private String Company_Tel;
	private String Company_Name;
	
	private String Result_code;
	private String Result_msg;
	private String Resp_time;
	
	private String Order_No;
	private String SignStandard;
	private String Receiver_Name;
	private String Receiver_Addr;
	private String Receiver_Tel;
	private String Receiver_OrderNo;
	private String Order_AMT;
	private String Biz_Name;
	private String AMT;
	private String Sub_Station_Name;
	private String Sub_Station_Code;
	private String Serial_NO;
	private String Sorting_Name;
	private String Weight;
	private String Order_Status;
	private String Order_Status_Msg;
	private String BankCardNo;
	private String BankOrderNo;
	private String Pay_Type;
	private String Bank_OrderId;
	private String Yeepay_OrderId;
	private String ChequeNo;
	private String Sign_Self_Flag;
	private String Sign_Name;
	private String Sign_Tel;
	
	
	/**
	 * @return the sessionHead
	 */
	public YeepayPosPojo getSessionHead() {
		return SessionHead;
	}
	/**
	 * @param sessionHead the sessionHead to set
	 */
	public void setSessionHead(YeepayPosPojo sessionHead) {
		SessionHead = sessionHead;
	}
	/**
	 * @return the sessionBody
	 */
	public YeepayPosPojo getSessionBody() {
		return SessionBody;
	}
	/**
	 * @param sessionBody the sessionBody to set
	 */
	public void setSessionBody(YeepayPosPojo sessionBody) {
		SessionBody = sessionBody;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return Version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		Version = version;
	}
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return ServiceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		ServiceCode = serviceCode;
	}
	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return TransactionID;
	}
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	/**
	 * @return the srcSysID
	 */
	public String getSrcSysID() {
		return SrcSysID;
	}
	/**
	 * @param srcSysID the srcSysID to set
	 */
	public void setSrcSysID(String srcSysID) {
		SrcSysID = srcSysID;
	}
	/**
	 * @return the dstSysID
	 */
	public String getDstSysID() {
		return DstSysID;
	}
	/**
	 * @param dstSysID the dstSysID to set
	 */
	public void setDstSysID(String dstSysID) {
		DstSysID = dstSysID;
	}
	/**
	 * @return the reqTime
	 */
	public String getReqTime() {
		return ReqTime;
	}
	/**
	 * @param reqTime the reqTime to set
	 */
	public void setReqTime(String reqTime) {
		ReqTime = reqTime;
	}
	/**
	 * @return the extendAtt
	 */
	public YeepayPosPojo getExtendAtt() {
		return ExtendAtt;
	}
	/**
	 * @param extendAtt the extendAtt to set
	 */
	public void setExtendAtt(YeepayPosPojo extendAtt) {
		ExtendAtt = extendAtt;
	}
	/**
	 * @return the hMAC
	 */
	public String getHMAC() {
		return HMAC;
	}
	/**
	 * @param hMAC the hMAC to set
	 */
	public void setHMAC(String hMAC) {
		HMAC = hMAC;
	}
	/**
	 * @return the employee_ID
	 */
	public String getEmployee_ID() {
		return Employee_ID;
	}
	/**
	 * @param employee_ID the employee_ID to set
	 */
	public void setEmployee_ID(String employee_ID) {
		Employee_ID = employee_ID;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the employee_Name
	 */
	public String getEmployee_Name() {
		return Employee_Name;
	}
	/**
	 * @param employee_Name the employee_Name to set
	 */
	public void setEmployee_Name(String employee_Name) {
		Employee_Name = employee_Name;
	}
	/**
	 * @return the company_Code
	 */
	public String getCompany_Code() {
		return Company_Code;
	}
	/**
	 * @param company_Code the company_Code to set
	 */
	public void setCompany_Code(String company_Code) {
		Company_Code = company_Code;
	}
	/**
	 * @return the company_Tel
	 */
	public String getCompany_Tel() {
		return Company_Tel;
	}
	/**
	 * @param company_Tel the company_Tel to set
	 */
	public void setCompany_Tel(String company_Tel) {
		Company_Tel = company_Tel;
	}
	/**
	 * @return the company_Name
	 */
	public String getCompany_Name() {
		return Company_Name;
	}
	/**
	 * @param company_Name the company_Name to set
	 */
	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
	}
	/**
	 * @return the result_code
	 */
	public String getResult_code() {
		return Result_code;
	}
	/**
	 * @param result_code the result_code to set
	 */
	public void setResult_code(String result_code) {
		Result_code = result_code;
	}
	/**
	 * @return the result_msg
	 */
	public String getResult_msg() {
		return Result_msg;
	}
	/**
	 * @param result_msg the result_msg to set
	 */
	public void setResult_msg(String result_msg) {
		Result_msg = result_msg;
	}
	/**
	 * @return the resp_time
	 */
	public String getResp_time() {
		return Resp_time;
	}
	/**
	 * @param resp_time the resp_time to set
	 */
	public void setResp_time(String resp_time) {
		Resp_time = resp_time;
	}
	/**
	 * @return the order_No
	 */
	public String getOrder_No() {
		return Order_No;
	}
	/**
	 * @param order_No the order_No to set
	 */
	public void setOrder_No(String order_No) {
		Order_No = order_No;
	}
	/**
	 * @return the receiver_Name
	 */
	public String getReceiver_Name() {
		return Receiver_Name;
	}
	/**
	 * @param receiver_Name the receiver_Name to set
	 */
	public void setReceiver_Name(String receiver_Name) {
		Receiver_Name = receiver_Name;
	}
	/**
	 * @return the receiver_Addr
	 */
	public String getReceiver_Addr() {
		return Receiver_Addr;
	}
	/**
	 * @param receiver_Addr the receiver_Addr to set
	 */
	public void setReceiver_Addr(String receiver_Addr) {
		Receiver_Addr = receiver_Addr;
	}
	/**
	 * @return the receiver_Tel
	 */
	public String getReceiver_Tel() {
		return Receiver_Tel;
	}
	/**
	 * @param receiver_Tel the receiver_Tel to set
	 */
	public void setReceiver_Tel(String receiver_Tel) {
		Receiver_Tel = receiver_Tel;
	}
	/**
	 * @return the receiver_OrderNo
	 */
	public String getReceiver_OrderNo() {
		return Receiver_OrderNo;
	}
	/**
	 * @param receiver_OrderNo the receiver_OrderNo to set
	 */
	public void setReceiver_OrderNo(String receiver_OrderNo) {
		Receiver_OrderNo = receiver_OrderNo;
	}
	/**
	 * @return the order_AMT
	 */
	public String getOrder_AMT() {
		return Order_AMT;
	}
	/**
	 * @param order_AMT the order_AMT to set
	 */
	public void setOrder_AMT(String order_AMT) {
		Order_AMT = order_AMT;
	}
	/**
	 * @return the biz_Name
	 */
	public String getBiz_Name() {
		return Biz_Name;
	}
	/**
	 * @param biz_Name the biz_Name to set
	 */
	public void setBiz_Name(String biz_Name) {
		Biz_Name = biz_Name;
	}
	/**
	 * @return the aMT
	 */
	public String getAMT() {
		return AMT;
	}
	/**
	 * @param aMT the aMT to set
	 */
	public void setAMT(String aMT) {
		AMT = aMT;
	}
	/**
	 * @return the sub_Station_Name
	 */
	public String getSub_Station_Name() {
		return Sub_Station_Name;
	}
	/**
	 * @param sub_Station_Name the sub_Station_Name to set
	 */
	public void setSub_Station_Name(String sub_Station_Name) {
		Sub_Station_Name = sub_Station_Name;
	}
	/**
	 * @return the sub_Station_Code
	 */
	public String getSub_Station_Code() {
		return Sub_Station_Code;
	}
	/**
	 * @param sub_Station_Code the sub_Station_Code to set
	 */
	public void setSub_Station_Code(String sub_Station_Code) {
		Sub_Station_Code = sub_Station_Code;
	}
	/**
	 * @return the serial_NO
	 */
	public String getSerial_NO() {
		return Serial_NO;
	}
	/**
	 * @param serial_NO the serial_NO to set
	 */
	public void setSerial_NO(String serial_NO) {
		Serial_NO = serial_NO;
	}
	/**
	 * @return the sorting_Name
	 */
	public String getSorting_Name() {
		return Sorting_Name;
	}
	/**
	 * @param sorting_Name the sorting_Name to set
	 */
	public void setSorting_Name(String sorting_Name) {
		Sorting_Name = sorting_Name;
	}
	/**
	 * @return the weight
	 */
	public String getWeight() {
		return Weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		Weight = weight;
	}
	/**
	 * @return the order_Status
	 */
	public String getOrder_Status() {
		return Order_Status;
	}
	/**
	 * @param order_Status the order_Status to set
	 */
	public void setOrder_Status(String order_Status) {
		Order_Status = order_Status;
	}
	/**
	 * @return the order_Status_Msg
	 */
	public String getOrder_Status_Msg() {
		return Order_Status_Msg;
	}
	/**
	 * @param order_Status_Msg the order_Status_Msg to set
	 */
	public void setOrder_Status_Msg(String order_Status_Msg) {
		Order_Status_Msg = order_Status_Msg;
	}
	/**
	 * @return the signStandard
	 */
	public String getSignStandard() {
		return SignStandard;
	}
	/**
	 * @param signStandard the signStandard to set
	 */
	public void setSignStandard(String signStandard) {
		SignStandard = signStandard;
	}
	/**
	 * @return the bankCardNo
	 */
	public String getBankCardNo() {
		return BankCardNo;
	}
	/**
	 * @param bankCardNo the bankCardNo to set
	 */
	public void setBankCardNo(String bankCardNo) {
		BankCardNo = bankCardNo;
	}
	/**
	 * @return the bankOrderNo
	 */
	public String getBankOrderNo() {
		return BankOrderNo;
	}
	/**
	 * @param bankOrderNo the bankOrderNo to set
	 */
	public void setBankOrderNo(String bankOrderNo) {
		BankOrderNo = bankOrderNo;
	}
	/**
	 * @return the pay_Type
	 */
	public String getPay_Type() {
		return Pay_Type;
	}
	/**
	 * @param pay_Type the pay_Type to set
	 */
	public void setPay_Type(String pay_Type) {
		Pay_Type = pay_Type;
	}
	/**
	 * @return the bank_OrderId
	 */
	public String getBank_OrderId() {
		return Bank_OrderId;
	}
	/**
	 * @param bank_OrderId the bank_OrderId to set
	 */
	public void setBank_OrderId(String bank_OrderId) {
		Bank_OrderId = bank_OrderId;
	}
	/**
	 * @return the yeepay_OrderId
	 */
	public String getYeepay_OrderId() {
		return Yeepay_OrderId;
	}
	/**
	 * @param yeepay_OrderId the yeepay_OrderId to set
	 */
	public void setYeepay_OrderId(String yeepay_OrderId) {
		Yeepay_OrderId = yeepay_OrderId;
	}
	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return ChequeNo;
	}
	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		ChequeNo = chequeNo;
	}
	/**
	 * @return the sign_Self_Flag
	 */
	public String getSign_Self_Flag() {
		return Sign_Self_Flag;
	}
	/**
	 * @param sign_Self_Flag the sign_Self_Flag to set
	 */
	public void setSign_Self_Flag(String sign_Self_Flag) {
		Sign_Self_Flag = sign_Self_Flag;
	}
	/**
	 * @return the sign_Name
	 */
	public String getSign_Name() {
		return Sign_Name;
	}
	/**
	 * @param sign_Name the sign_Name to set
	 */
	public void setSign_Name(String sign_Name) {
		Sign_Name = sign_Name;
	}
	/**
	 * @return the sign_Tel
	 */
	public String getSign_Tel() {
		return Sign_Tel;
	}
	/**
	 * @param sign_Tel the sign_Tel to set
	 */
	public void setSign_Tel(String sign_Tel) {
		Sign_Tel = sign_Tel;
	}
	
	
	
	
}
