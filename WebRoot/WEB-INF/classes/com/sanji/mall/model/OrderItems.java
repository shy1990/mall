package com.sanji.mall.model;

import java.math.BigDecimal;

import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.pojo.Base;

public class OrderItems extends Base {

	private String id;

	private String orderId;

	private String targetId;

	private String targetType;

	private BigDecimal marketbalePrice = BigDecimal.ZERO;

	private BigDecimal dealPrice = BigDecimal.ZERO;

	private BigDecimal amount = BigDecimal.ZERO;

	private BigDecimal nums = BigDecimal.ZERO;

	private String saleActiveId;

	private BigDecimal saleActiveReduceMoney;

	private BigDecimal memberlvRedceMoney;

	private BigDecimal memberlvDiscount;

	private String appraise;

	private String isgift;

	private BigDecimal rootNum;

	private Goods goods;

	private Accessory accessory;

	private GoodsSku goodsSku;

	private PointGoods pointGoods;

	private Gift gift;

	// 关联字段
	private String name;

	private String goodsNum;

	// 一下为辅助字段
	private String goodsSns;
	private String skuSns;

	private FormItem formItem;

	private String status;// 退货退款申请状态

	private String thStatus;

	public String getThStatus() {
		return thStatus;
	}

	public void setThStatus(String thStatus) {
		this.thStatus = thStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FormItem getFormItem() {
		return formItem;
	}

	public void setFormItem(FormItem formItem) {
		this.formItem = formItem;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	private String storage;

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public PointGoods getPointGoods() {
		return pointGoods;
	}

	public void setPointGoods(PointGoods pointGoods) {
		this.pointGoods = pointGoods;
	}

	public GoodsSku getGoodsSku() {
		return goodsSku;
	}

	public void setGoodsSku(GoodsSku goodsSku) {
		this.goodsSku = goodsSku;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
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

	public BigDecimal getMarketbalePrice() {
		return marketbalePrice;
	}

	public void setMarketbalePrice(BigDecimal marketbalePrice) {
		this.marketbalePrice = marketbalePrice;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getNums() {
		return nums;
	}

	public void setNums(BigDecimal nums) {
		this.nums = nums;
	}

	public String getSaleActiveId() {
		return saleActiveId;
	}

	public void setSaleActiveId(String saleActiveId) {
		this.saleActiveId = saleActiveId == null ? null : saleActiveId.trim();
	}

	public BigDecimal getSaleActiveReduceMoney() {
		return saleActiveReduceMoney;
	}

	public void setSaleActiveReduceMoney(BigDecimal saleActiveReduceMoney) {
		this.saleActiveReduceMoney = saleActiveReduceMoney;
	}

	public BigDecimal getMemberlvRedceMoney() {
		return memberlvRedceMoney;
	}

	public void setMemberlvRedceMoney(BigDecimal memberlvRedceMoney) {
		this.memberlvRedceMoney = memberlvRedceMoney;
	}

	public BigDecimal getMemberlvDiscount() {
		return memberlvDiscount;
	}

	public void setMemberlvDiscount(BigDecimal memberlvDiscount) {
		this.memberlvDiscount = memberlvDiscount;
	}

	public String getAppraise() {
		return appraise;
	}

	public void setAppraise(String appraise) {
		this.appraise = appraise == null ? null : appraise.trim();
	}

	public String getIsgift() {
		return isgift;
	}

	public void setIsgift(String isgift) {
		this.isgift = isgift == null ? null : isgift.trim();
	}

	public BigDecimal getRootNum() {
		return rootNum;
	}

	public void setRootNum(BigDecimal rootNum) {
		this.rootNum = rootNum;
	}

	/**
	 * @return the goodsSns
	 */
	public String getGoodsSns() {
		return goodsSns;
	}

	/**
	 * @param goodsSns
	 *            the goodsSns to set
	 */
	public void setGoodsSns(String goodsSns) {
		this.goodsSns = goodsSns;
	}

	/**
	 * @return the skuSns
	 */
	public String getSkuSns() {
		return skuSns;
	}

	/**
	 * @param skuSns
	 *            the skuSns to set
	 */
	public void setSkuSns(String skuSns) {
		this.skuSns = skuSns;
	}

}