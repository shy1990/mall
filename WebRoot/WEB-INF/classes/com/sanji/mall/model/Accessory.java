package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Accessory implements PriceAble {
	private String id;

	private String name;

	private String accessoriesNum;

	private String type;

	private String disabled;

	private Date createTime;

	private Date modifyTime;

	private String userId;

	private BigDecimal price;

	private BigDecimal originalPrice;

	private String brandId;

	private String catId;

	private String tmallUrl;

	private BigDecimal tmallPrice;

	private String defaultImg;

	private String batteryCapacity;

	private String accessoriesVersion;

	private String filmType;

	private String material;

	private String styles;

	private String colorId;

	private String accessoriesStyle;

	private String isoriginal;

	private String wearType;

	private String iswire;
	private BigDecimal stock;

	private BigDecimal clickRate;

	private String accessoriesDetail;

	private String specCode;

	// 配件所属品牌信息
	private Brand brand;

	// 配件类别信息
	private Cat cat;

	private List<Color> colors;

	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// 用于记录配件使用的机型
	private Goods goods;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getClickRate() {
		return clickRate;
	}

	public void setClickRate(BigDecimal clickRate) {
		this.clickRate = clickRate;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAccessoriesNum() {
		return accessoriesNum;
	}

	public void setAccessoriesNum(String accessoriesNum) {
		this.accessoriesNum = accessoriesNum == null ? null : accessoriesNum.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled == null ? null : disabled.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId == null ? null : brandId.trim();
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId == null ? null : catId.trim();
	}

	public String getTmallUrl() {
		return tmallUrl;
	}

	public void setTmallUrl(String tmallUrl) {
		this.tmallUrl = tmallUrl == null ? null : tmallUrl.trim();
	}

	public BigDecimal getTmallPrice() {
		return tmallPrice;
	}

	public void setTmallPrice(BigDecimal tmallPrice) {
		this.tmallPrice = tmallPrice;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg == null ? null : defaultImg.trim();
	}

	public String getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity == null ? null : batteryCapacity.trim();
	}

	public String getAccessoriesVersion() {
		return accessoriesVersion;
	}

	public void setAccessoriesVersion(String accessoriesVersion) {
		this.accessoriesVersion = accessoriesVersion == null ? null : accessoriesVersion.trim();
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType == null ? null : filmType.trim();
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material == null ? null : material.trim();
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles == null ? null : styles.trim();
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId == null ? null : colorId.trim();
	}

	public String getAccessoriesStyle() {
		return accessoriesStyle;
	}

	public void setAccessoriesStyle(String accessoriesStyle) {
		this.accessoriesStyle = accessoriesStyle == null ? null : accessoriesStyle.trim();
	}

	public String getIsoriginal() {
		return isoriginal;
	}

	public void setIsoriginal(String isoriginal) {
		this.isoriginal = isoriginal == null ? null : isoriginal.trim();
	}

	public String getWearType() {
		return wearType;
	}

	public void setWearType(String wearType) {
		this.wearType = wearType == null ? null : wearType.trim();
	}

	public String getIswire() {
		return iswire;
	}

	public void setIswire(String iswire) {
		this.iswire = iswire == null ? null : iswire.trim();
	}

	public String getAccessoriesDetail() {
		return accessoriesDetail;
	}

	public void setAccessoriesDetail(String accessoriesDetail) {
		this.accessoriesDetail = accessoriesDetail == null ? null : accessoriesDetail.trim();
	}

	/**
	 * @return the specCode
	 */
	public String getSpecCode() {
		return specCode;
	}

	/**
	 * @param specCode
	 *            the specCode to set
	 */
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

}