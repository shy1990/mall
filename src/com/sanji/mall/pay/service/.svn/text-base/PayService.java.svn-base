/**  
* @Title: PayService.java
* @Package com.sanji.mall.pay.service
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-12-5 下午2:56:58
* @version V1.0  
*/
package com.sanji.mall.pay.service;

import java.util.Map;

import com.sanji.mall.model.PayDeal;
import com.sanji.mall.model.PayRefund;

/**
 * 由于支付
 * @ClassName: PayService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-12-5 下午2:56:58
 */
public interface PayService {
	
	/**
	 * 根据序列号，类型查询支付返回信息
	* @Title: gainDealByDeal
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param dealId  支付序列号
	* @param @param dealType 返回类型：sandPay-杉德
	* @param @return    设定文件
	* @return PayDeal    返回类型
	* @author ZhouZhangbao
	 */
	public PayDeal gainDealByDeal(String dealId,String dealType);
	
	/**
	 * 新增一条支付记录<br>同时修改订单为已支付状态，若付款方式为POS则订单改为已签收
	* @Title: insetPayDeal
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param payDeal    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void insetPayDeal(PayDeal payDeal);
	
	/**
	 * 新增一条支付记录<br>同时修改订单为已支付状态和减去订单想要库存，若付款方式为POS则订单改为已签收
	* @Title: insetPayDealStock
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void insetPayDealStock(PayDeal payDeal);
	/**
	 * 只更新库存
	* @Title: updateInventory
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param payDeal    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void updateInventory(PayDeal payDeal);
	
	/**
	 * 新增一条退款返回信息
	* @Title: insetPayRefund
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param payRefund    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	*/
	
	public void insetPayRefund(PayRefund payRefund);
	
	/**
	 * 订单签收，根据订单ID或者订单编号
	* @Title: orderSign
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param orderId 订单ID或者订单编号
	* @param @param signSelfFlag 签收方式 是否本人签收（1为是，0为否）
	* @param @param signName 当非本人签收时，终端输入签收人姓名
	* @param @param signTel    签收人电话
	* @param @param terminalType  签收终端类型  APP   pos
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void orderSign(String orderId,String signSelfFlag,String signName,String signTel,String terminalType);
	
}
