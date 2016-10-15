package com.sanji.mall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Order;

/**
 * 订单信息service接口类
 * 
 * @ClassName: OrderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-1 上午10:09:08
 * 
 */
public interface OrderService {
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
	 * 
	 * @Title: save
	 * @Description: 保存订单
	 * @param order
	 *                设定文件 void    返回类型
	 * @throws
	 */
	void save(Order order);

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

	/**
	 * 根据订单的ID或者订单的编号查询订单信息<br>
	 * 只查询支付时所用的信息<br>
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
	 * 通过订单Id获得订单详情
	 * 
	 * @Title: getById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Order 返回类型
	 * @author 田超强
	 * @throws
	 */
	Order getById(String id);

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
	 * 根据订单编号修改订单发货状态
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
	 * 将原来为订单空的收货地址更新<br>
	 * 同时更新会员的地址
	 * 
	 * @Title: updateShipAdd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void updateShipAdd(Order order);

	/**
	 * 将原来为订单空的收货地址更新<br>
	 * 同时更新会员的地址
	 * 
	 * @Title: gainOrderInfoByOrderNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderCode 设定文件 订单号或管易DD号
	 * @return Order 返回类型
	 * @author songbaozhen
	 */
	public Order gainOrderInfoByOrderCode(String orderCode);

	/**
	 * 更改订单价格，（for 线上支付立减五元） updatePrice:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author ydhlw001
	 * @param id
	 * @param price
	 * @since JDK 1.6
	 */

	public void updatePrice(String id, BigDecimal totalCost, String string, BigDecimal walletNum, BigDecimal actualPayNum, String walletPayNo);

	public int updatePriceByMid(String MemberId);

	/**
	 * 根据用户名和订单管易编号和商品编号获取用户订单信息
	 * 
	 * @Title: selectByUidAndErpAndSkunum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param userId
	 * @param @param ecerpNo
	 * @param @param skuNum
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Map<String, Object> selectByUidAndErpAndSkunum(String userId, String ecerpNo, String skuNum);

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
	 * @return
	 */
	public BigDecimal getNoCommentById(String memberId, String orderId);
	
	/**
     * 统计已经购买的活动商品数量
     * @param goodsId
     * @param memberId
     * @return
     */
    public int gainYetBuyNum(@Param("goodsId")String goodsId,@Param("memberId")String memberId);

    /**
  * 查询订单是否有活动商品
  * @param goodsId
  * @param memberId
  * @return
  */
 public boolean checkerOrderThereHdGoods(@Param("userName")String userName,@Param("orderId")String orderId);
 /**
  * 查询订单物流状态是否已签收
  * @param orderNum
  * @return
  */
	boolean checkerOrderShipStatus(String orderNum);
/**
 * 根据订单号查询业务员手机号
 * @param orderNum
 * @return
 */
String findAdminMobileByOrderNo(String orderNum);

}
