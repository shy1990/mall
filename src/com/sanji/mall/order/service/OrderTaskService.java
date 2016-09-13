package com.sanji.mall.order.service;

/**
 * 订单自动定时任务
 * 
 * @ClassName: OrderTask
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2015-1-23 下午5:25:37
 * 
 */
public interface OrderTaskService {

	/**
	 * 定时任务每天凌晨取消未付款订单订单
	 * 
	 * @Title: cancelNoPayOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void cancelNoPayOrder();

	/**
	 * 取消未完成的订单
	 * 
	 * @Title: cancelUnSuccessOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void cancelUnSuccessOrder();

	/**
	 * 定时确认，确定收货
	 * 
	 * @Title: orderConfirm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void orderConfirm();

	/**
	 * 轮询是否已经发货。。。。
	 * 
	 * @Title: sendOut
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void sendOut();

}
