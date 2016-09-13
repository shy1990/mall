package com.sanji.mall.aftersale.service;

import java.util.List;

import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.ThForm;

/**
 * 退货退款service类
 * 
 * @ClassName: ThFormService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-12-3 上午10:15:54
 * 
 */
public interface ThFormService {
	/**
	 * 添加退货退款记录
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int addThForm(ThForm thForm) throws Exception;

	/**
	 * 添加单个订单详情退货退款记录
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int addThForm(ThForm thForm, String oItemsId) throws Exception;

	/**
	 * 添加退货退款详情记录
	 * 
	 * @Title: addItem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int addItem(FormItem item) throws Exception;

	/**
	 * 根据订单Id获取退款退货申请详情
	 * 
	 * @Title: gainThFormByOid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param oid 订单Id
	 * @param @return 设定文件
	 * @return ThForm 返回类型
	 * @author 田超强
	 * @throws
	 */
	public ThForm gainThFormByOid(String oid) throws Exception;

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
	public List<ThForm> selectByOidAdOitemId(String oid, String oItemsId);

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
	ThForm selectUnfinished(String oid, String oItemsId);

	/**
	 * 修改退货记录
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int updateByPrimaryKeySelective(ThForm record);

	/**
	 * 通过退货记录获得记录详情
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ThForm 返回类型
	 * @author 田超强
	 * @throws
	 */
	ThForm selectByPrimaryKey(String id);

}
