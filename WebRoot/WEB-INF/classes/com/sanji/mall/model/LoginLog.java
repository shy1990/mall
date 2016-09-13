package com.sanji.mall.model;

import java.math.BigDecimal;
import java.util.Date;

public class LoginLog {
    private String id;

    private String loginIp;

    private Date loginTime;

    private String loginAddr;

    private String userId;

    private BigDecimal userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAddr() {
        return loginAddr;
    }

    public void setLoginAddr(String loginAddr) {
        this.loginAddr = loginAddr == null ? null : loginAddr.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getUserType() {
        return userType;
    }

    public void setUserType(BigDecimal userType) {
        this.userType = userType;
    }
}