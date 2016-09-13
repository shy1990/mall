package com.sanji.mall.model;

import java.math.BigDecimal;

public class PointGoods implements PriceAble{
    private String id;

    private String name;

    private String type;
    
    private BigDecimal price;

    private BigDecimal integral;

    private BigDecimal stock;

    private String isshelves;

    private String pic;
    
    private String integralCode;
    
    private String specCode;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getIsshelves() {
        return isshelves;
    }

    public void setIsshelves(String isshelves) {
        this.isshelves = isshelves == null ? null : isshelves.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getOriginalPrice() {
		return this.price;
	}

	public BigDecimal getTmallPrice() {
		return BigDecimal.ZERO;
	}

	/**
	 * @return the integralCode
	 */
	public String getIntegralCode() {
		return integralCode;
	}

	/**
	 * @param integralCode the integralCode to set
	 */
	public void setIntegralCode(String integralCode) {
		this.integralCode = integralCode;
	}

	/**
	 * @return the specCode
	 */
	public String getSpecCode() {
		return specCode;
	}

	/**
	 * @param specCode the specCode to set
	 */
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
    
}