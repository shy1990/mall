/**  
* @Title: SendPayPojo.java
* @Package com.sanji.mall.pojo
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-12-4 下午3:27:34
* @version V1.0  
*/
package com.sanji.mall.pojo;

/**
 * @ClassName: SendPayPojo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-12-4 下午3:27:34
 */
public class SandPayPojo {
	private String employeeId;//派送员工号
	private String password;//密码
	private String employeeName;//派送员姓名
	private String employeeTel;//派送员电话
	private String orderId;//订单唯一码
	private String orderNo;//订单编号
	private String shipName;//收件人姓名
	private String shipAdd;//收件人地址
	private String shipTel;//收件人电话
	private String orderAmount;//订单总金额
	private String orderStatus;//订单状态
	private String payAmount;//支付金额
	private String payNO;//支付序列号
	private String bankCardNo;//支付卡号
	private String bankCardName;//发卡行
	private String result_code;//返回状态码
	private String result_type;//返回状态类型
	private String hmac;//传递过来的签名数据
	
	private String companyName;//支付公司名称
	
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeTel
	 */
	public String getEmployeeTel() {
		return employeeTel;
	}
	/**
	 * @param employeeTel the employeeTel to set
	 */
	public void setEmployeeTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the shipName
	 */
	public String getShipName() {
		return shipName;
	}
	/**
	 * @param shipName the shipName to set
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	/**
	 * @return the shipAdd
	 */
	public String getShipAdd() {
		return shipAdd;
	}
	/**
	 * @param shipAdd the shipAdd to set
	 */
	public void setShipAdd(String shipAdd) {
		this.shipAdd = shipAdd;
	}
	/**
	 * @return the shipTel
	 */
	public String getShipTel() {
		return shipTel;
	}
	/**
	 * @param shipTel the shipTel to set
	 */
	public void setShipTel(String shipTel) {
		this.shipTel = shipTel;
	}
	/**
	 * @return the orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}
	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	
	/**
	 * @return the payAmount
	 */
	public String getPayAmount() {
		return payAmount;
	}
	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * @return the payNO
	 */
	public String getPayNO() {
		return payNO;
	}
	/**
	 * @param payNO the payNO to set
	 */
	public void setPayNO(String payNO) {
		this.payNO = payNO;
	}
	/**
	 * @return the bankCardNo
	 */
	public String getBankCardNo() {
		return bankCardNo;
	}
	/**
	 * @param bankCardNo the bankCardNo to set
	 */
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	/**
	 * @return the bankCardName
	 */
	public String getBankCardName() {
		return bankCardName;
	}
	/**
	 * @param bankCardName the bankCardName to set
	 */
	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}
	/**
	 * @return the result_code
	 */
	public String getResult_code() {
		return result_code;
	}
	/**
	 * @param result_code the result_code to set
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	/**
	 * @return the hmac
	 */
	public String getHmac() {
		return hmac;
	}
	/**
	 * @param hmac the hmac to set
	 */
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	/**
	 * @return the result_type
	 */
	public String getResult_type() {
		return result_type;
	}
	/**
	 * @param result_type the result_type to set
	 */
	public void setResult_type(String result_type) {
		this.result_type = result_type;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
