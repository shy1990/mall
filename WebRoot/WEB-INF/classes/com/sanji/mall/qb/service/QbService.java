package com.sanji.mall.qb.service;

import java.math.BigDecimal;
import java.util.Map;

import com.sanji.mall.pojo.Json;

public interface QbService {

	/**
	 * 添加支付
	 * 
	 * @Title: addPay
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tradeType 交易类型
	 * @param @param amount 交易金额
	 * @param @param orderNum 订单编号
	 * @param @param userName 会员编码
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Json addPay(String tradeType, BigDecimal amount, String userName, String payPwd, String url, String orderNum) throws Exception;

	/**
	 * 更改钱包支付状态
	 * 
	 * @Title: editPay
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param transNo 钱包流水号id
	 * @param @param state 状态SUCCESS 交易成功 FAILURE 交易失败 PENDING 交易进行中 EXAMINE
	 *        交易待审查
	 * 
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Json editPay(String transNo, String state, String userName) throws Exception;

	/***
	 * 获取用户钱包信息
	 * 
	 * @Title: getUserQbInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userName
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Map<String, Object> getUserQbInfo(String userName);

	/**
	 * 获取用户余额
	 * 
	 * @Title: getBalance
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userName
	 * @param @return 设定文件
	 * @return BigDecimal 返回类型
	 * @author 田超强
	 * @throws
	 */
	public BigDecimal getBalance(String userName);

	/**
	 * 根据钱包id修改记录管易dd号
	 * 
	 * @Title: qbAddEcerpNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param qbId
	 * @param @param ecerpNo 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void qbAddEcerpNo(String qbId, String ecerpNo);

	/**
	 * 修改钱包记录的备注信息
	 * @param walletPayNo
	 * @param description
	 */
	public void editDescription(String walletPayNo,String description) ;
}
