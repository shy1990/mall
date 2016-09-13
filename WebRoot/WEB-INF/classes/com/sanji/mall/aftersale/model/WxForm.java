package com.sanji.mall.aftersale.model;

import org.apache.commons.lang3.StringUtils;

import com.sanji.mall.model.OrderItems;

/**
 * 
 * @ClassName: WxForm
 * @Description: 维修单
 * @author WuJiming wzslw_163_com
 * @date 2014年12月2日 上午11:35:22
 */
public class WxForm extends Form {
	private QhForm qhForm;
	private String expressNumber;
	private String userId;
	private double money;

	private OrderItems orderItems;

	public OrderItems getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItems orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @Title: getMoney
	 * @Description:维修金额
	 * @return    设定文件 double    返回类型
	 * @throws
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * @Title: setMoney
	 * @Description: 退款金额
	 * @param money
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public QhForm getQhForm() {
		return qhForm;
	}

	public void setQhForm(QhForm qhForm) {
		this.qhForm = qhForm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public FormStatus getStatus() {
		FormStatus status = this.status;
		if (!StringUtils.isBlank(this.getReadUserId())) {
			status = FormStatus.PROCESS;
		} else if (!StringUtils.isBlank(this.getAbortReason())) {
			status = FormStatus.ABORT;
		} else if (this.getQhForm() != null) {
			status = FormStatus.NOTIFIYED;
		} else if (this.getQhForm() != null && this.getQhForm().getReadUserId() != null) {
			status = FormStatus.PICKUP;
		}
		if (this.getQhForm() != null && this.getQhForm().status == FormStatus.DOWNSENDED) {
			status = FormStatus.DOWNSENDED;
		}
		if (!StringUtils.isBlank(this.getExpressNumber())) {
			status = FormStatus.UPSENDED;
		}
		return status;
	}
}
