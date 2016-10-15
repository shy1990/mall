package com.sanji.mall.model;

import java.util.Date;

public class MobileCode {
    private String id;

    private String magRecordId;

    private String toMobile;

    private Date createtime;

    private String verificationCode;
 

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMagRecordId() {
        return magRecordId;
    }

    public void setMagRecordId(String magRecordId) {
        this.magRecordId = magRecordId == null ? null : magRecordId.trim();
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile == null ? null : toMobile.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode == null ? null : verificationCode.trim();
    }
}