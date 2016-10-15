package com.sanji.mall.aftersale.model;

import org.apache.commons.lang3.StringUtils;

import com.sanji.mall.model.Order;

/**
 * 
 * @ClassName: ThForm
 * @Description: 退货单
 * @author WuJiming wzslw_163_com
 * @date 2014年12月2日 上午11:35:22
 */
public class ThForm extends Form {
	private Order order;
	private QhForm qhForm;
	private String expressNumber;
	private double money;

	/**
	 * @Title: getMoney
	 * @Description:退款金额
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * 
	 * @Title: getExpressNumber
	 * @Description: 售后绝后退货的物流号
	 * @return    设定文件 String    返回类型
	 * @throws
	 */
	public String getExpressNumber() {
		return expressNumber;
	}

	/**
	 * 
	 * @Title: setExpressNumber
	 * @Description: 售后绝后退货的物流号
	 * @param expressNumber
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public QhForm getQhForm() {
		return qhForm;
	}

	public void setQhForm(QhForm qhForm) {
		this.qhForm = qhForm;
	}

	/*
	 * @Override public FormStatus getStatus() { FormStatus status =
	 * this.status; if (!StringUtils.isBlank(this.getReadUserId())) { status =
	 * FormStatus.PROCESS; } else if
	 * (!StringUtils.isBlank(this.getAbortReason())) { status =
	 * FormStatus.ABORT; } else if (this.getQhForm() != null) { status =
	 * FormStatus.NOTIFIYED; } else if (this.getQhForm() != null &&
	 * this.getQhForm().getReadUserId() != null) { status = FormStatus.PICKUP; }
	 * if (this.getQhForm() != null && this.getQhForm().status ==
	 * FormStatus.DOWNSENDED) { status = FormStatus.DOWNSENDED; } if
	 * (!StringUtils.isBlank(this.getExpressNumber())) { status =
	 * FormStatus.UPSENDED; } return status; }
	 */

	@Override
	public FormStatus getStatus() {
		FormStatus status = this.status;
		if (!StringUtils.isBlank(this.getReadUserId()) && this.status == FormStatus.INIT) {// 如果处理人Id不为空,当前订单状态为已保存
			status = FormStatus.PROCESS;// 正在处理
		} else if (!StringUtils.isBlank(this.getReadUserId()) && this.status == FormStatus.PROCESS) {// 处理人不为空，并且处理状态是正在处理
			status = FormStatus.AUDITREJECT;// 等待客户修改退款退货申请
		} else if (!StringUtils.isBlank(this.getReadUserId()) && this.status == FormStatus.AUDITREJECT) {// 处理人不为空，并且处理状态是，等待客户修改退款退货申请
			status = FormStatus.EDITPROCESS;// 正在处理修改后的申请
		}
		// 如果售后同意退款退货申请，那么就产生一个只有id的物流记录
		else if (!StringUtils.isBlank(this.getReadUserId()) && this.status == FormStatus.EDITPROCESS) {
			// 如果当前状态为EDITPROCESS正在处理修改后的申请
			// 如果对应的取货记录为空，那么状态就改为AUDITREJECT 等待客户修改退款退货申请
			if (this.getQhForm() == null) {
				status = FormStatus.AUDITREJECT;// 等待客户修改退款退货申请
			}
			// 如果对应的取货记录不为空，那么状态就改为REJECTCLIENT("同意退款退货请发货")
			if (this.getQhForm() != null) {
				status = FormStatus.REJECTCLIENT;// 同意退款退货请发货
			}
		}

		// 如果取货记录，操作人员不为空，那么就把状态改为CLIENTSEND("等待商家处理")
		else if (this.getQhForm() != null && this.getQhForm().getReadUserId() != null) {
			if (this.status == FormStatus.REJECTCLIENT) {
				// 如果当前状态是REJECTCLIENT;
				// 同意退款退货请发货。那么就改为FormStatus.CLIENTSEND;//
				// 等待商家处理
				status = FormStatus.CLIENTSEND;// 等待商家处理
			} else if (this.status == FormStatus.CLIENTSEND) {
				// 如果当前状态是FormStatus.CLIENTSEND;等待商家处理。改为COMPLETE("已完成")
				status = FormStatus.COMPLETE;// 已完成
			}
		}

		else if (!StringUtils.isBlank(this.getAbortReason())) {// 终止原因不为空
			status = FormStatus.ABORT;// 已终止
		} else if (!StringUtils.isBlank(this.getExpressNumber())) {
			status = FormStatus.UPSENDED;
		}
		return status;
	}

}
