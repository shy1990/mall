package com.sanji.mall.model;


public class NewsCat {
    private String id;

    private String name;

    private String pid;

    private String disabled;

    private String remark;

    private String show;

    private Double porder;

    private String typeId;

    private Double grade;

    private String ptree;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show == null ? null : show.trim();
    }

    public Double getPorder() {
        return porder;
    }

    public void setPorder(Double porder) {
        this.porder = porder;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getPtree() {
        return ptree;
    }

    public void setPtree(String ptree) {
        this.ptree = ptree == null ? null : ptree.trim();
    }
}