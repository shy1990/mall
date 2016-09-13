package com.sanji.mall.model;

import java.util.Date;

import com.sanji.mall.pojo.Base;

public class Collect extends Base {

	private String id;

	private String targetId;

	private Date collectTime;

	private String collectIp;

	private String memberType;

	private String type;

	private String memberId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId == null ? null : targetId.trim();
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public String getCollectIp() {
		return collectIp;
	}

	public void setCollectIp(String collectIp) {
		this.collectIp = collectIp == null ? null : collectIp.trim();
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType == null ? null : memberType.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId == null ? null : memberId.trim();
	}
}