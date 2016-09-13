package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sanji.mall.pojo.Base;

public class Evaluate extends Base {
	private String id;

	private String targetId;

	private String targetType;

	private String orderItemsId;

	private String memberId;

	private BigDecimal totality;

	private BigDecimal descMatch;

	private BigDecimal logistics;

	private BigDecimal clerkManner;

	private BigDecimal sendManner;

	private BigDecimal sincerity;

	private String content;

	private Date createtime;

	private String orderId;

	private Members members;

	// 订单详情列表
	private List<OrderItems> orderItemss;

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public List<OrderItems> getOrderItemss() {
		return orderItemss;
	}

	public void setOrderItemss(List<OrderItems> orderItemss) {
		this.orderItemss = orderItemss;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId == null ? null : targetId.trim();
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType == null ? null : targetType.trim();
	}

	public String getOrderItemsId() {
		return orderItemsId;
	}

	public void setOrderItemsId(String orderItemsId) {
		this.orderItemsId = orderItemsId == null ? null : orderItemsId.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public BigDecimal getTotality() {
		return totality;
	}

	public void setTotality(BigDecimal totality) {
		this.totality = totality;
	}

	public BigDecimal getDescMatch() {
		return descMatch;
	}

	public void setDescMatch(BigDecimal descMatch) {
		this.descMatch = descMatch;
	}

	public BigDecimal getLogistics() {
		return logistics;
	}

	public void setLogistics(BigDecimal logistics) {
		this.logistics = logistics;
	}

	public BigDecimal getClerkManner() {
		return clerkManner;
	}

	public void setClerkManner(BigDecimal clerkManner) {
		this.clerkManner = clerkManner;
	}

	public BigDecimal getSendManner() {
		return sendManner;
	}

	public void setSendManner(BigDecimal sendManner) {
		this.sendManner = sendManner;
	}

	public BigDecimal getSincerity() {
		return sincerity;
	}

	public void setSincerity(BigDecimal sincerity) {
		this.sincerity = sincerity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}
}