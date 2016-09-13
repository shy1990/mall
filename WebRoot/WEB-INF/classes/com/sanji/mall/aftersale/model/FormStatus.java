package com.sanji.mall.aftersale.model;

/**
 * 
 * @ClassName: FormStatus
 * @Description: 处理状态
 * @author WuJiming wzslw_163_com
 * @date 2014年12月2日 下午2:44:35
 */
public enum FormStatus {
	INIT("已保存"), PROCESS("正在处理"), AUDITREJECT("修改退款退货申请"), EDITPROCESS("正在处理修改后的申请"), REJECTCLIENT("同意退款退货请发货"), CLIENTSEND("等待商家处理"), NOTIFIYED("已通知取货"), PICKUP("上门取货"), DOWNSENDED(
			"地包已发货"), UPSENDED("售后已发货"), UPRECEIVED("售后已收货"), DOWNRECEIVED("地包已收货"), ABORT("已终止"), COMPLETE("已完成");

	private String name;

	// auditReject售后审核拒绝, rejectClient审核通过通知客户发货 ， clientSend客户已发货
	private FormStatus(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
