package com.sanji.mall.model;

import java.math.BigDecimal;

public class Regions {
    private String id;

    private String pid;

    private String ptree;

    private BigDecimal grade;

    private String name;

    private String disabled;

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

    public String getPtree() {
        return ptree;
    }

    public void setPtree(String ptree) {
        this.ptree = ptree == null ? null : ptree.trim();
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }
}