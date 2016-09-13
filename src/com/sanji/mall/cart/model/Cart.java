package com.sanji.mall.cart.model;

import java.util.Date;

public class Cart {
	private Object goods;
	private String id;

	private String targetId;

	private String type;

	private Integer orderNum;

	private Integer goodsRootNum=0;

	private String memberId;

	private Date createTime=new Date();

	private String createIp;

	private String memberType;

	
	
	public Object getGoods() {
		return goods;
	}

	public void setGoods(Object goods) {
		this.goods = goods;
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

	public String getType() {
		return type;
	}
	/**
	 * 
	* @Title: setType
	* @Description: 购物车项类型：手机，配件，赠品
	* @param type    设定文件
	* void    返回类型
	* @throws
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 
	 * @Title: getGoodsRootNum
	 * @Description: root数量不高于订购数量
	 * @return    设定文件 Integer    返回类型
	 * @throws
	 */
	public Integer getGoodsRootNum() {

		return goodsRootNum > this.orderNum ? orderNum : goodsRootNum;
	}

	public void setGoodsRootNum(Integer goodsRootNum) {
		this.goodsRootNum = goodsRootNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp == null ? null : createIp.trim();
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType == null ? null : memberType.trim();
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", targetId=" + targetId + ", type=" + type
				+ ", orderNum=" + orderNum + ", goodsRootNum=" + goodsRootNum
				+ ", memberId=" + memberId + ", createTime=" + createTime
				+ ", createIp=" + createIp + ", memberType=" + memberType + "]";
	}
	
	
}