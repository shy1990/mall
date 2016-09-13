package com.sanji.mall.aftersale.model;

import java.util.Date;
import java.util.List;

public abstract class Form {
	private String id;
	private String remark;
	private String readUserId;
	private Date createTime = new Date();
	protected FormStatus status = FormStatus.INIT;
	private String abortReason;
	private List<FormItem> items;

	public String getAbortReason() {
		return abortReason;
	}

	public void setAbortReason(String abortReason) {
		this.abortReason = abortReason;
	}

	public abstract FormStatus getStatus();

	public String getId() {
		return id;
	}

	/**
	 * @Title: getReadUserId
	 * @Description: 处理人员id
	 * @return    设定文件 String    返回类型
	 * @throws
	 */
	public String getReadUserId() {
		return readUserId;
	}

	/**
	 * 
	 * @Title: setReadUserId
	 * @Description: 处理人员id
	 * @param readUserId
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void setReadUserId(String readUserId) {
		this.readUserId = readUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<FormItem> getItems() {
		return items;
	}

	public void setItems(List<FormItem> items) {
		this.items = items;
	}

}
