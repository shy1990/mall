package com.sanji.mall.ultlon.model;

import java.util.Date;

import com.sanji.mall.common.util.DateUtil;

import oracle.sql.DATE;

public class AfterSale {

	private String imei;
	private String orderNum;// 订单号
	private String type;
	private String receiveTime;
	private String skuCode;
	private String goodsName;
	private String result = "处理中";// 处理结果
	private String remark;
	private String username;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReceiveTime() {
		if(this.receiveTime==null){
			receiveTime = (new Date().getTime())+"";
		}
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
