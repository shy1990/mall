package com.sanji.mall.cart.dao;



import java.util.Map;

import com.sanji.mall.cart.model.IntegralGoods;

public interface IGoodMapper {

	/**
	 * 
	* @Title: selectCountByMemberId
	* @Description: 根据积分商品id查价格
	* @param userId
	* @return    设定文件
	* int    返回类型
	* @throws
	 */
	Map<String, Object> selectById(String id);
}