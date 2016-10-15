package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.List;

public class Brand {
    private String id;

    private String pid;

    private String name;

    private String pic;

    private String remark;

    private String disabled;

    private BigDecimal pOrder;

    private BigDecimal grade;

    private String ptree;
    
    //商品列表信息
    private List<Goods> goodss;

    public List<Goods> getGoodss() {
		return goodss;
	}

	public void setGoodss(List<Goods> goodss) {
		this.goodss = goodss;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public BigDecimal getpOrder() {
        return pOrder;
    }

    public void setpOrder(BigDecimal pOrder) {
        this.pOrder = pOrder;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getPtree() {
        return ptree;
    }

    public void setPtree(String ptree) {
        this.ptree = ptree == null ? null : ptree.trim();
    }

	@Override
	public String toString() {
		return "\""+name+"\"";
	}
    
    
}