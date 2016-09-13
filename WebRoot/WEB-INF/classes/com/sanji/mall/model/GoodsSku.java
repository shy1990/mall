package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sanji.mall.pojo.Base;

public class GoodsSku extends Base implements PriceAble{
	private static final long serialVersionUID = 1L;

	private String id;

	private String skuNum;

	private BigDecimal price;

	private BigDecimal originalPrice;

	private BigDecimal tmallPrice;

	private String edition;

	private String standard;

	private String storage;

	private String goodsId;

	private BigDecimal stock;

	private String shelves;

	private String networkThree;

	private String networkFour;

	private String supportChannel;

	private String netSuitType;

	private String colorId;

	private String collectId;
	
	private String skuCode;
	
	private Date shelvesTime;

	private String countPage;// 总页数

	private String goodsName;// 辅助字段 商品名称
	private String goodsNum;// 辅助字段 商品名称
	private String picSrc;// 辅助字段 图片地址
	private String colorName;// 辅助字段 颜色
	private Long collectNum;// 收藏总数

	private List<Accessory> gifts;// 辅助字段赠品

	private String thenBuyNum;// 该用户此单品今天已购买的数量

	private String changeNum=String.valueOf(Integer.MAX_VALUE);

	private String changePrice="0";

	public String getThenBuyNum() {
		
		return thenBuyNum;
	}

	public void setThenBuyNum(String thenBuyNum) {
		this.thenBuyNum = thenBuyNum;
	}

	public String getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(String changeNum) {
		this.changeNum = changeNum;
	}

	public String getChangePrice() {
		return changePrice;
	}

	public void setChangePrice(String changePrice) {
		this.changePrice = changePrice;
	}

	

	public List<Accessory> getGifts() {
		return gifts;
	}

	public void setGifts(List<Accessory> gifts) {
		this.gifts = gifts;
	}

	public String getCountPage() {
		return countPage;
	}

	public void setCountPage(String countPage) {
		this.countPage = countPage;
	}

	/**
	 * 单品颜色
	 */
	private Color color;

	/**
	 * 此单品的基本信息
	 */
	private Goods goods;

	/**
	 * 此单品相关图片
	 */
	private List<GoodsPic> goodsPics;

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<GoodsPic> getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(List<GoodsPic> goodsPics) {
		this.goodsPics = goodsPics;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getSkuNum() {
		return skuNum;
	}

	public void setSkuNum(String skuNum) {
		this.skuNum = skuNum == null ? null : skuNum.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getTmallPrice() {
		return tmallPrice;
	}

	public void setTmallPrice(BigDecimal tmallPrice) {
		this.tmallPrice = tmallPrice;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition == null ? null : edition.trim();
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard == null ? null : standard.trim();
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage == null ? null : storage.trim();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId == null ? null : goodsId.trim();
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public String getShelves() {
		return shelves;
	}

	public void setShelves(String shelves) {
		this.shelves = shelves == null ? null : shelves.trim();
	}

	public String getNetworkThree() {
		return networkThree;
	}

	public void setNetworkThree(String networkThree) {
		this.networkThree = networkThree;
	}

	public String getNetworkFour() {
		return networkFour;
	}

	public void setNetworkFour(String networkFour) {
		this.networkFour = networkFour;
	}

	public String getSupportChannel() {
		return supportChannel;
	}

	public void setSupportChannel(String supportChannel) {
		this.supportChannel = supportChannel;
	}

	public String getNetSuitType() {
		return netSuitType;
	}

	public void setNetSuitType(String netSuitType) {
		this.netSuitType = netSuitType;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the picSrc
	 */
	public String getPicSrc() {
		return picSrc;
	}

	/**
	 * @param picSrc
	 *            the picSrc to set
	 */
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	/**
	 * @return the colorName
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * @param colorName
	 *            the colorName to set
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	/**
	 * @return the goodsNum
	 */
	public String getGoodsNum() {
		return goodsNum;
	}

	/**
	 * @param goodsNum
	 *            the goodsNum to set
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * @return the collectNum
	 */
	public Long getCollectNum() {
		return collectNum;
	}

	/**
	 * @param collectNum
	 *            the collectNum to set
	 */
	public void setCollectNum(Long collectNum) {
		this.collectNum = collectNum;
	}

	/**
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * @param skuCode the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * @return the shelvesTime
	 */
	public Date getShelvesTime() {
		return shelvesTime;
	}

	/**
	 * @param shelvesTime the shelvesTime to set
	 */
	public void setShelvesTime(Date shelvesTime) {
		this.shelvesTime = shelvesTime;
	}

	
}