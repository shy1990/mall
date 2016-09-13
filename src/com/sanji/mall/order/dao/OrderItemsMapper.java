package com.sanji.mall.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.OrderItems;

/**
 * 订单详情映射类
 * 
 * @ClassName: OrderItemsMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-31 下午2:22:27
 * 
 */
public interface OrderItemsMapper {
	int deleteByPrimaryKey(String id);

	int insert(OrderItems record);

	int insertSelective(OrderItems record);

	int updateByPrimaryKeySelective(OrderItems record);

	int updateByPrimaryKey(OrderItems record);

	/**
	 * 统计单品（商品）或者单品销售数量
	 * 
	 * @Title: gainTargetCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param targetId
	 * @param @param targetType
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int gainTargetCountNum(@Param("targetId") String targetId, @Param("targetType") String targetType);

	/**
	 * 统计指定时间成交记录
	 * 
	 * @Title: gainDeal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Map> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Map> gainDeal(Map param);

	/**
	 * 统计指定时间成交记录数量
	 * 
	 * @Title: gainDealNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainDealNum(Map param);

	/**
	 * 统计指定时间成交记录总数量用于分页
	 * 
	 * @Title: gainDealNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainDealCountNum(Map param);

	/**
	 * 查询及时生成的订单
	 * 
	 * @Title: gainOrderItemAllSku
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return list 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	List<OrderItems> gainOrderItemAllSku(OrderItems orderItems);

	/**
	 * 根据id获取订单详情记录
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id 订单详情Id
	 * @param @return 设定文件
	 * @return OrderItems 返回类型
	 * @author 田超强
	 * @throws
	 */
	OrderItems selectByPrimaryKey(String id);
	
	/**
	 * 根据订单ID查询订单的详情信息
	* @Title: selectAllByOrderId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param orderId
	* @param @return    设定文件
	* @return List<OrderItems>    返回类型
	* @author ZhouZhangbao
	 */
	public List<OrderItems> selectAllByOrderId(String orderId);
	//public String selectTargetTypeByOrderId(String orderId);

}