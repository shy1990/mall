package com.sanji.mall.model;

import java.math.BigDecimal;

public class Gift {
	private String id;

	private String goodsId;

	private String giftId;

	private BigDecimal giftPrice;

	private Accessory accessory;

	public Accessory getAccessory() {
		return accessory;
	}

	public void setAccessory(Accessory accessory) {
		this.accessory = accessory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId == null ? null : goodsId.trim();
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId == null ? null : giftId.trim();
	}

	public BigDecimal getGiftPrice() {
		return giftPrice;
	}

	public void setGiftPrice(BigDecimal giftPrice) {
		this.giftPrice = giftPrice;
	}
}