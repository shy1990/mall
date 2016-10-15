package com.sanji.mall.accessory.special.model;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: SkuViewModel
 * @Description: 专用配件单品试视图模型
 * @author WuJiming wzslw_163_com
 * @date 2014年10月18日 上午9:28:48
 */
public class SkuViewModel {
	private String id;
	private String color;
	private String edition;
	private Integer quantity;
	private Integer rootQuantity;
	private BigDecimal price;
	private BigDecimal originalPrice;
	private BigDecimal rootPrice;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @Title: getRootQuantity
	 * @Description: root数量不高于订购数量
	 * @return    设定文件 Integer    返回类型
	 * @throws
	 */
	public Integer getRootQuantity() {
		return rootQuantity > quantity ? quantity : rootQuantity;
	}

	public void setRootQuantity(Integer rootQuantity) {
		this.rootQuantity = rootQuantity;
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

	public BigDecimal getRootPrice() {
		return rootPrice;
	}

	public void setRootPrice(BigDecimal rootPrice) {
		this.rootPrice = rootPrice;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "SkuViewModel [id=" + id + ", color=" + color + ", edition="
				+ edition + ", quantity=" + quantity + ", rootQuantity="
				+ rootQuantity + ", price=" + price + ", originalPrice="
				+ originalPrice + ", rootPrice=" + rootPrice + "]";
	}

	
}
