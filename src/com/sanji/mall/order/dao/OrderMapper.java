package com.sanji.mall.order.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Order;

/**
 * 订单映射类
 * 
 * @ClassName: OrderMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-31 下午2:22:10
 * 
 */
public interface OrderMapper {
	int deleteByPrimaryKey(String id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(String id);

	/**
	 * 根据订单Id和用户Id修改订单信息
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	/**
	 * 分页获取订单列表信息
	 * 
	 * @Title: gainPageOrders
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Order> gainPageOrders(Map param);

	/**
	 * 获取订单详情
	 * 
	 * @Title: gainDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Order> gainDetail(Map param);

	/**
	 * 统计数据总数量
	 * 
	 * @Title: gainCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainCountNum(Map param);

	/**
	 * 根据用户id和订单id集合批量修改订单状态为删除
	 * 
	 * @Title: upDisabledByUid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userId
	 * @param @param ids
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int upDisabledByUid(@Param("userId") String userId, @Param("ids") String ids);

	/**
	 * 根据订单的ID或者订单的编号查询订单信息<br>
	 * 只查询支付时所用的信息
	 * 
	 * @Title: gainOrderByID
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Order 返回类型
	 * @author ZhouZhangbao
	 */

	public Order gainOrderByID(String id);

	/**
	 * 查询订单的所有信息<br>
	 * 包括订单详情所对应的商品、赠品、配件
	 * 
	 * @Title: gainOrderALLByID
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Order 返回类型
	 * @author ZhouZhangbao
	 */
	public Order gainOrderALLByID(String id);

	/**
	 * 根据订单编号查询订单信息
	 * 
	 * @Title:gainOrderByOrderNo
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderNo
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @throws
	 * @param orderNo
	 * @return
	 */
	public Order gainOrderByOrderNo(String orderNo);

	/**
	 * 根据订单编号修改发货状态
	 * 
	 * @Title:updateShipStatusByOrderNo
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderNo 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param orderNo
	 */
	public void updateShipStatusByOrderNo(Order order);

	/**
	 * 根据订单的ID更新管易返回的数据<br>
	 * 只更新管易返回的三条数据
	 * 
	 * @Title: updateEcerpById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void updateEcerpById(Order order);

	/**
	 * 根据订单的ID更新支付返回的数据<br>
	 * 只更支付相关的数据
	 * 
	 * @Title: updatePayStatusById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void updatePayStatusById(Order order);

	/**
	 * 将原来为订单空的收货地址更新
	 * 
	 * @Title: updateShipAdd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */

	public void updateShipAdd(Order order);

	/**
	 * 自动执行取消未付款的订单
	 * 
	 * @Title: cancelNoPayOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int cancelNoPayOrder();

	/**
	 * 根据id取消订单
	 * 
	 * @Title: cancelNoPayOrderById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int cancelNoPayOrderById(String id);

	/**
	 * 查找状态正常，线上付款，已付款，未发货并且发货时间为空的订单信息
	 * 
	 * @Title: noShipGoods
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Order> noShipGoods();

	/**
	 * 获取未支付完成的订单
	 * 
	 * @Title: gainPayUnsuccessfulOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Order> gainPayUnsuccessfulOrder();

	/**
	 * 获取使用里钱包支付并且取消里订单的订单
	 * 
	 * @Title: gainPayUnsuccessfulOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Order> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Order> gainWalletUnsuccessfulOrder();
	
	/**
	 * 获取订单已经完结钱包还未完结的订单
	 * @return
	 */
	public List<Order> gainOrderEndWalletNotEnd();

	/**
	 * 跟新 状态正常，线上付款，已付款，已发货，并且发货时间超过N小时的订单状态为确认收货
	 * 
	 * @Title: autoAffirmReceiving
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void autoAffirmReceiving();

	/**
	 * 根据订单号查询订单信息
	 * 
	 * @Title: gainOrderInfoByOrderCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderCode
	 *            订单号或管易DD号
	 * @return order 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public Order gainOrderInfoByOrderCode(String orderCode);

	/**
	 * @param orderCode
	 *            查询订单状态
	 * @Title: gainShipStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return order 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public String gainShipStatus(String orderCode);

	/**
	 * 
	 * @Title: updatePrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id 订单id
	 * @param @param totalPrice 总价格
	 * @param @param remark 备注
	 * @param @param walletNum 钱包支付数量
	 * @param @param actualPayNum 实际支付价格
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	void updatePrice(@Param("id") String id, @Param("totalCost") BigDecimal totalPrice, @Param("remark") String remark, @Param("walletNum") BigDecimal walletNum,
			@Param("actualPayNum") BigDecimal actualPayNum, @Param("walletPayNo") String walletPayNo);

	public Map<String, Object> updatePriceByMid(String MemberId);

	/**
	 * 查询未发货的订单列表
	 * 
	 * @Title: getNoShipOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Map<String,Object>> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Map<String, Object>> getNoShipOrder();

	/**
	 * autoShip
	 * 
	 * @Title: autoShip
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param erpNo 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void autoShip(String erpNo);

	/**
	 * 根据用户名和订单管易编号和商品编号获取用户订单信息
	 * 
	 * @Title: selectByUidAndErpAndSkunum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userId
	 * @param @param ecerpNo
	 * @param @param skuNum
	 * @param @return 设定文件
	 * @return List<Map<String, Object>> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Map<String, Object>> selectByUidAndErpAndSkunum(@Param("userId") String userId, @Param("ecerpNo") String ecerpNo, @Param("skuNum") String skuNum);

	/**
	 * 已签收没有签收时间的订单自动修改成订单创建后2天自动签收时间
	 * 
	 * @Title: autoSingForTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int autoSingForTime();

	/**
	 * 根据订单编号查询dd号
	 * 
	 * @Title:selectErpnoById
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderId 设定文件
	 * @return String 返回类型
	 * @throws
	 * @param orderId
	 */
	public String selectErpnoById(String id);

	/**
	 * 通过用户id获取未评论的订单金额
	 * 
	 * @param id
	 * @return BigDecimal 返回类型
	 */
	public List<Order> getNoCommentById(String memberId);

	/**
	 * 更改用户订单积分
	 * 
	 * @param point
	 * @param orderId
	 */
	public void updateOrderPointById(@Param("point") BigDecimal point, @Param("orderId") String orderId);
	
	/**
	 * 批量推送订单时使用，慎用。修改mapper.xml 中对应的sql过滤订单
	 * @return
	 */
	public List<Order> getSomeOrder();
	
	/**
	 * 统计已经购买的活动商品数量
	 * @param goodsId
	 * @param memberId
	 * @return
	 */
	public Integer gainYetBuyNum(@Param("goodsId")String goodsId,@Param("memberId")String memberId);
	
	   /**
     * 查询订单是否有活动商品
     * @param goodsId
     * @param memberId
     * @return
     */
    public Integer checkerOrderThereHdGoods(@Param("userName")String userName,@Param("orderId")String orderId);

    /**
     * 根据订单号查询订单物流状态
     * @param orderNo
     * @return
     */
	public Order gainShipStatusBuyOrderNum(String orderNum);
    /**
     * 根据订单号查询业务员手机号
     * @param orderNum
     * @return
     */
	String gainAdminMobileByOrderNum(String orderNum);

}