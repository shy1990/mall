package com.sanji.mall.model;

import java.util.Date;

public class MessageRecord {
    private String id;

    private String mobiles;

    private String content;

    private Date cratetime;

    private String resptime;

    private String resptatus;

    private String msgid;
    
    //------一下为辅助字段------
    private String code; //验证码  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? null : mobiles.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public String getResptime() {
        return resptime;
    }

    public void setResptime(String resptime) {
        this.resptime = resptime == null ? null : resptime.trim();
    }

    public String getResptatus() {
        return resptatus;
    }

    public void setResptatus(String resptatus) {
        this.resptatus = resptatus == null ? null : resptatus.trim();
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid == null ? null : msgid.trim();
    }

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
    
    
}