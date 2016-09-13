package com.sanji.mall.model;

import java.util.Date;

/**
 * 用户访问商品记录
 * 
 * @ClassName: UserSkuRecord
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2015-8-12 下午8:03:18
 * 
 */
public class UserGoodsRecord {
	private String id;

	private String userId;

	private String goodsNum;

	private Date createTime;
	
	private String source;//记录来源
	
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum == null ? null : goodsNum.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
