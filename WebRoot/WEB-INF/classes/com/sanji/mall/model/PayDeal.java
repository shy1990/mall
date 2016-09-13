package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayDeal {
    private String id;

    private BigDecimal orderAmount;

    private BigDecimal dealFee;

    private String dealState;

    private String dealSigunre;

    private String dealId;

    private String dealType;

    private String payType;

    private Date createTime;

    private String payee;

    private String payeeNo;
    
    private String orderId;
    private String bankCardNo;
    private String bankCardName;
    
    
    private String shipTel;
    private String orderNo;
    
    

   
    
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

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getDealFee() {
        return dealFee;
    }

    public void setDealFee(BigDecimal dealFee) {
        this.dealFee = dealFee;
    }

    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState == null ? null : dealState.trim();
    }

    public String getDealSigunre() {
        return dealSigunre;
    }

    public void setDealSigunre(String dealSigunre) {
        this.dealSigunre = dealSigunre == null ? null : dealSigunre.trim();
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId == null ? null : dealId.trim();
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getPayeeNo() {
        return payeeNo;
    }

    public void setPayeeNo(String payeeNo) {
        this.payeeNo = payeeNo == null ? null : payeeNo.trim();
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
    
	
}