package com.sanji.mall.cart.model;

import java.math.BigDecimal;
import java.util.Date;

public class IntegralGoods {
	
	private String id;

	private String name;

	private BigDecimal price = BigDecimal.ZERO;

	private Integer integral;

	private Integer stock;

	private String isshelves;

	private String pic;

	private String type;

	private String integralCode;
	
	private String specCode;
    
	private Date shelvesTimeDate = new Date();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getIsshelves() {
		return isshelves;
	}

	public void setIsshelves(String isshelves) {
		this.isshelves = isshelves;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntegralCode() {
		return integralCode;
	}

	public void setIntegralCode(String integralCode) {
		this.integralCode = integralCode;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public Date getShelvesTimeDate() {
		return shelvesTimeDate;
	}

	public void setShelvesTimeDate(Date shelvesTimeDate) {
		this.shelvesTimeDate = shelvesTimeDate;
	}

	@Override
	public String toString() {
		return "IntegralGoods [id=" + id + ", name=" + name + ", price="
				+ price + ", integral=" + integral + ", stock=" + stock
				+ ", isshelves=" + isshelves + ", pic=" + pic + ", type="
				+ type + ", integralCode=" + integralCode + ", specCode="
				+ specCode + ", shelvesTimeDate=" + shelvesTimeDate + "]";
	}
	
	
	
	
}