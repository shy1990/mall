package com.sanji.mall.aftersale.model;

public class QhForm extends Form{
	private String userName;
	private String address;
	private String expressNumber;

	/**
	* @Title: getUserName
	* @Description: 零售商姓名
	* @return    设定文件
	* String    返回类型
	* @throws
	 */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	* @Title: getAddress
	* @Description: 取货地址
	* @return    设定文件
	* String    返回类型
	* @throws
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 
	* @Title: getExpressNumber
	* @Description: 快递编号
	* @return    设定文件
	* String    返回类型
	* @throws
	 */
	public String getExpressNumber() {
		return expressNumber;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	@Override
	public FormStatus getStatus() {
		FormStatus status=this.status;
		if(this.getReadUserId()!=null){
			status=FormStatus.PICKUP;
		}else if (this.getAbortReason()!=null) {
			status=FormStatus.ABORT;
		}else if (this.getExpressNumber()!=null) {
			status=FormStatus.DOWNSENDED;
		}
		return status;
	}
}
