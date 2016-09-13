package com.sanji.mall.model;

import java.util.Date;

public class PayRefund {
    private String id;

    private String r0Cmd;

    private String r1Code;

    private String r2Trxid;

    private String r3Amt;

    private String r4Cur;

    private String r5Order;

    private String hmac;

    private String refundType;

    private String refundProvider;

    private String isException;

    private Date createTime;
    
    private String ExceptionMsg;
    
    private Date returnTime;
    
    private String orderId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getR0Cmd() {
        return r0Cmd;
    }

    public void setR0Cmd(String r0Cmd) {
        this.r0Cmd = r0Cmd == null ? null : r0Cmd.trim();
    }

    public String getR1Code() {
        return r1Code;
    }

    public void setR1Code(String r1Code) {
        this.r1Code = r1Code == null ? null : r1Code.trim();
    }

    public String getR2Trxid() {
        return r2Trxid;
    }

    public void setR2Trxid(String r2Trxid) {
        this.r2Trxid = r2Trxid == null ? null : r2Trxid.trim();
    }

    public String getR3Amt() {
        return r3Amt;
    }

    public void setR3Amt(String r3Amt) {
        this.r3Amt = r3Amt == null ? null : r3Amt.trim();
    }

    public String getR4Cur() {
        return r4Cur;
    }

    public void setR4Cur(String r4Cur) {
        this.r4Cur = r4Cur == null ? null : r4Cur.trim();
    }

    public String getR5Order() {
        return r5Order;
    }

    public void setR5Order(String r5Order) {
        this.r5Order = r5Order == null ? null : r5Order.trim();
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac == null ? null : hmac.trim();
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
    }

    public String getRefundProvider() {
        return refundProvider;
    }

    public void setRefundProvider(String refundProvider) {
        this.refundProvider = refundProvider == null ? null : refundProvider.trim();
    }

    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException == null ? null : isException.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * @return the exceptionMsg
	 */
	public String getExceptionMsg() {
		return ExceptionMsg;
	}

	/**
	 * @param exceptionMsg the exceptionMsg to set
	 */
	public void setExceptionMsg(String exceptionMsg) {
		ExceptionMsg = exceptionMsg;
	}

	/**
	 * @return the returnTime
	 */
	public Date getReturnTime() {
		return returnTime;
	}

	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
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
    
    
}