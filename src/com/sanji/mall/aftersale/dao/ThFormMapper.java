package com.sanji.mall.aftersale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.ThForm;

public interface ThFormMapper {
	int deleteByPrimaryKey(String id);

	int insertSelective(ThForm record);

	ThForm selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(ThForm record);

	int insertItemSelective(FormItem item);

	/**
	 * 根据订单Id获取退款退货详情信息
	 * 
	 * @Title: selectByOid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param oid
	 * @param @return 设定文件
	 * @return ThForm 返回类型
	 * @author 田超强
	 * @throws
	 */
	ThForm selectByOid(String orderId);

	/**
	 * 根据订单Id和 订单详情id 获得所有退款退货记录
	 * 
	 * @Title: selectByOidAdOitemId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param oid 订单Id
	 * @param @param oItemsId 订单详情Id
	 * @param @return 设定文件
	 * @return ThForm 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<ThForm> selectByOidAdOitemId(@Param("oid") String oid, @Param("oItemsId") String oItemsId);

	/**
	 * 根据订单Id和 订单详情id 获得未完成的退款退货记录
	 * 
	 * @Title: selectUnfinished
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param oid 订单Id
	 * @param @param oItemsId 订单详情Id
	 * @param @return 设定文件
	 * @return ThForm 返回类型
	 * @author 田超强
	 * @throws
	 */
	ThForm selectUnfinished(@Param("oid") String oid, @Param("oItemsId") String oItemsId);
}