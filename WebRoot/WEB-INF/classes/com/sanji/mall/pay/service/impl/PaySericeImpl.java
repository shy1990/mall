/**  
* @Title: PaySericeImpl.java
* @Package com.sanji.mall.pay.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-12-5 下午2:59:55
* @version V1.0  
*/
package com.sanji.mall.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.accessory.dao.AccessoryMapper;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.goodsSku.dao.GoodsSkuMapper;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.GoodsSku;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.model.PayRefund;
import com.sanji.mall.model.PointGoods;
import com.sanji.mall.order.dao.OrderItemsMapper;
import com.sanji.mall.order.dao.OrderMapper;
import com.sanji.mall.pay.dao.PayDealMapper;
import com.sanji.mall.pay.dao.PayRefundMapper;
import com.sanji.mall.pay.service.PayService;
import com.sanji.mall.point.dao.PointGoodsMapper;

/**
 * @ClassName: PaySericeImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-12-5 下午2:59:55
 */
@Service("paySerice")
@Transactional(rollbackFor = Exception.class)
public class PaySericeImpl implements PayService{
	@Resource
	private PayDealMapper payDealMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderItemsMapper orderItemsMapper;
	@Resource
	private GoodsSkuMapper goodsSkuMapper;
	@Resource
	private AccessoryMapper accessoryMapper;
	@Resource
	private PointGoodsMapper pointGoodsMapper;
	@Resource
	private PayRefundMapper payRefundMapper;

	/* (非 Javadoc)
	* <p>Title: gainDealByDeal</p>
	* <p>Description: </p>
	* @param dealId
	* @param dealType
	* @return
	* @see com.sanji.mall.pay.service.PayService#gainDealByDeal(java.lang.String, java.lang.String)
	*/
	
	public PayDeal gainDealByDeal(String dealId, String dealType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dealId", dealId);
		map.put("dealType", dealType);
		return payDealMapper.gainPayDealByDealID(map);
	}

	/* (非 Javadoc)
	* <p>Title: insetPayDeal</p>
	* <p>Description: </p>
	* @param payDeal
	* @see com.sanji.mall.pay.service.PayService#insetPayDeal(com.sanji.mall.model.PayDeal)
	*/
	
	public void insetPayDeal(PayDeal payDeal) {
		// TODO Auto-generated method stub
		payDealMapper.insertSelective(payDeal);
		Order order = new Order();
		order.setId(payDeal.getOrderId());
		order.setPayStatus("1");//已付款
		if (payDeal.getPayType().equals("pos")) {
			//order.setShipStatus("3");// 已签收
			//order.setSignForTime(new Date());//签收时间
			order.setPayMent("2");//pos支付
			MsgUtil.MsgQHSuccess(payDeal.getShipTel(), payDeal.getOrderNo());
		}else {
			order.setPayMent("0");//网上支付
		}
		order.setEbankMon(payDeal.getDealFee());
		order.setDealType(payDeal.getDealType());
		order.setDealId(payDeal.getDealId());
		order.setPayTime(payDeal.getCreateTime());
		orderMapper.updatePayStatusById(order);
	}

	/* (非 Javadoc)
	* <p>Title: insetPayDealStock</p>
	* <p>Description: </p>
	* @see com.sanji.mall.pay.service.PayService#insetPayDealStock()
	*/
	
	public void insetPayDealStock(PayDeal payDeal) {
		// TODO Auto-generated method stub
		this.updateInventory(payDeal);
		
		this.insetPayDeal(payDeal);
	}
	
	/**
	 * 只更新库存
	* @Title: updateInventory
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param payDeal    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void updateInventory(PayDeal payDeal){
		List<OrderItems> itemsList = orderItemsMapper.selectAllByOrderId(payDeal.getOrderId());
		for (OrderItems orderItems : itemsList) {
			if ("sku".equals(orderItems.getTargetType())) {//手机
				GoodsSku sku = goodsSkuMapper.selectById(orderItems.getTargetId());
				Integer stock = Integer.parseInt(sku.getStock()+"") - Integer.parseInt(orderItems.getNums()+"");
				sku.setStock(new BigDecimal(stock+""));
				if (stock <= 0) {
					sku.setShelvesTime(new Date());
				}
				goodsSkuMapper.updateStockById(sku);
			} else if("accessories".equals(orderItems.getTargetType())) {//配件
				Accessory accessory = accessoryMapper.selectByPrimaryKey(orderItems.getTargetId());
				Integer stock = Integer.parseInt(accessory.getStock()+"") - Integer.parseInt(orderItems.getNums()+"");
				accessory.setStock(new BigDecimal(stock+""));
				accessoryMapper.updateByPrimaryKeySelective(accessory);
			} else if ("gift".equals(orderItems.getTargetType())) {//赠品
				Accessory accessory = accessoryMapper.selectByGiftId(orderItems.getTargetId());
				if (null != accessory) {
					Integer stock = Integer.parseInt(accessory.getStock()+"") - Integer.parseInt(orderItems.getNums()+"");
					accessory.setStock(new BigDecimal(stock+""));
					accessoryMapper.updateByPrimaryKeySelective(accessory);
				} else {
					MsgUtil.MsgSenderAdminByError("客户支付之后0.1元赠品少了，订单ID："+payDeal.getOrderId());
				}
				
			} else if ("point".equals(orderItems.getTargetType())) {//积分商品
				PointGoods pointGoods = pointGoodsMapper.selectByPrimaryKey(orderItems.getTargetId());
				if (null != pointGoods) {
					Integer stock = Integer.parseInt(pointGoods.getStock()+"") - Integer.parseInt(orderItems.getNums()+"");
					pointGoods.setStock(new BigDecimal(stock+""));
					pointGoodsMapper.updateByPrimaryKeySelective(pointGoods);
				}else {
					MsgUtil.MsgSenderAdminByError("客户支付之后积分商品少了，订单ID："+payDeal.getOrderId());
				}
				
			}
		}
	}

	/* (非 Javadoc)
	* <p>Title: insetPayRefund</p>
	* <p>Description: </p>
	* @param payRefund
	* @see com.sanji.mall.pay.service.PayService#insetPayRefund(com.sanji.mall.model.PayRefund)
	*/
	
	public void insetPayRefund(PayRefund payRefund) {
		payRefundMapper.insertSelective(payRefund);
		
	}

	/* (非 Javadoc)
	* <p>Title: orderSign</p>
	* <p>Description: </p>
	* @param orderId
	* @param signSelfFlag
	* @param signName
	* @param signTel
	* @see com.sanji.mall.pay.service.PayService#orderSign(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	
	public void orderSign(String orderId, String signSelfFlag, String signName,
			String signTel,String terminalType) {
		Order order = new Order();
		order.setId(orderId);
		order.setShipStatus("3");// 已签收
		order.setSignForTime(new Date());//签收时间
		orderMapper.updatePayStatusById(order);
	}

	
}
