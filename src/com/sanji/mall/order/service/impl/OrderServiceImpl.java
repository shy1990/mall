package com.sanji.mall.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.cart.dao.CartMapper;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.dao.OrderItemsMapper;
import com.sanji.mall.order.dao.OrderMapper;
import com.sanji.mall.order.service.OrderService;

/**
 * 订单相关接口实现类
 * 
 * @ClassName: OrderServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-1 上午10:10:33
 * 
 */
@Service("orderService")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderItemsMapper orderItemsMapper;
	@Resource
	private CartMapper cartMapper;
	@Resource
	private MembersMapper membersMaper;

	public List<Order> gainPageOrders(Map param) {

		List<Order> orders = orderMapper.gainPageOrders(param);

		// 遍历订单集合，判断签收时间是否超过7天。
		for (Order o : orders) {
			if (o.getSignForTime() != null) {// 如果签收时间不为空，那么就对比时间是否超过了7天
				long date1 = DateUtil.compareDate2(DateUtil.getStringByDate(new Date()), DateUtil.getStringByDate(o.getSignForTime()), 4);
				long date2 = 7 * 24 * 60 * 60;
				if (date1 > date2) {// 如果签收时间大于7天那么就不显示退款退货
					o.setShowQuit(2);
				} else {
					o.setShowQuit(1);
				}
			} else {// 如果数据库没有签收时间那么、显示退款退货
				o.setShowQuit(1);
			}
		}
		return orders;
	}

	public List<Order> gainDetail(Map param) {
		return orderMapper.gainDetail(param);
	}

	public String gainCountNum(Map param) {
		return orderMapper.gainCountNum(param);
	}

	public int upDisabledByUid(String userId, String ids) {
		// TODO Auto-generated method stub
		return orderMapper.upDisabledByUid(userId, ids);
	}

	public void save(Order order) {
		List<OrderItems> orderItemss = order.getOrderItemss();
		for (OrderItems orderItems : orderItemss) {
			orderItemsMapper.insertSelective(orderItems);
		}
		orderMapper.insertSelective(order);
		Members member = membersMaper.selectByPrimaryKey(order.getMemberId());
		member.setPoint(member.getPoint().add(order.getOrderPoints()));
	}

	
	public int updateByPrimaryKeySelective(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateByPrimaryKeySelective(order);
	}

	public Order getById(String id) {
		Map param = new HashMap();
		param.put("id", id);
		List<Order> orders = orderMapper.gainDetail(param);
		return orders != null ? orders.get(0) : null;
	}

	/*
	 * (非 Javadoc) <p>Title: gainOrderByID</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.order.service.OrderService#gainOrderByID(java.lang.String)
	 */

	public Order gainOrderByID(String id) {
		// TODO Auto-generated method stub
		return orderMapper.gainOrderByID(id);
	}

	/*
	 * (非 Javadoc) <p>Title: gainOrderALLByID</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.order.service.OrderService#gainOrderALLByID(java.lang.
	 * String)
	 */

	public Order gainOrderALLByID(String id) {
		// TODO Auto-generated method stub
		return orderMapper.gainOrderALLByID(id);
	}

	/**
	 * 根据订单编号查询订单信息
	 */
	public Order gainOrderByOrderNo(String orderNo) {
		return orderMapper.gainOrderByOrderNo(orderNo);
	}

	/**
	 * 根据订单编号修改订单发货状态
	 */
	public void updateShipStatusByOrderNo(Order order) {
		orderMapper.updateShipStatusByOrderNo(order);
	}

	/*
	 * (非 Javadoc) <p>Title: updateEcerpById</p> <p>Description: </p>
	 * 
	 * @param order
	 * 
	 * @see
	 * com.sanji.mall.order.service.OrderService#updateEcerpById(com.sanji.mall
	 * .model.Order)
	 */

	public void updateEcerpById(Order order) {
		// TODO Auto-generated method stub
		orderMapper.updateEcerpById(order);
	}

	/*
	 * (非 Javadoc) <p>Title: updateShipAdd</p> <p>Description: </p>
	 * 
	 * @param order
	 * 
	 * @see
	 * com.sanji.mall.order.service.OrderService#updateShipAdd(com.sanji.mall
	 * .model.Order)
	 */

	public void updateShipAdd(Order order) {
		Members members = new Members();
		members.setTruename(order.getShipName());
		members.setId(order.getMemberId());
		members.setMobile(order.getShipTel());
		members.setZip(order.getShipZip());
		members.setProvince(order.getProvince());
		members.setCity(order.getCity());
		members.setArea(order.getArea());
		members.setAddress(order.getAddress());
		membersMaper.updateByPrimaryKeySelective(members);
		orderMapper.updateShipAdd(order);

	}

	public void updatePrice(String id, BigDecimal price, String remark, BigDecimal walletNum, BigDecimal actualPayNum, String walletPayNo) {
		orderMapper.updatePrice(id, price, remark, walletNum, actualPayNum, walletPayNo);
	}

	/*
	 * (非 Javadoc) <p>Title: updateShipAdd</p> <p>Description: </p>
	 * 
	 * @param order
	 * 
	 * @see
	 * com.sanji.mall.order.service.OrderService#gainOrderInfoByOrderNum(String
	 * orderNum)
	 */
	public Order gainOrderInfoByOrderCode(String orderCode) {
		Order o = orderMapper.gainOrderInfoByOrderCode(orderCode);
		if (o != null) {
			return o;
		}
		return null;
	}

	public Map<String, Object> selectByUidAndErpAndSkunum(String userId, String ecerpNo, String skuNum) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = orderMapper.selectByUidAndErpAndSkunum(userId, ecerpNo, skuNum);
		if (list != null) {
			if (!list.isEmpty()) {
				return list.get(0);
			}

		}
		return null;
	}

	public int updatePriceByMid(String MemberId) {
		Map<String, Object> map = orderMapper.updatePriceByMid(MemberId);
		return Integer.parseInt(map.get("COUNT").toString());
	}

	public String selectErpnoById(String id) {

		return orderMapper.selectErpnoById(id);
	}

	public BigDecimal getNoCommentById(String memberId, String orderId) {
		List<Order> list = orderMapper.getNoCommentById(memberId);
		BigDecimal totalMoney = BigDecimal.ZERO;

		if (list != null && list.size() > 0) {
			if (list.size() == 1) {

				Order o = list.get(0);
				totalMoney = o.getTotalCost();
				BigDecimal point = BigDecimal.ZERO;// 初始化积分变量为0

				point = point.add(totalMoney.divide(BigDecimal.valueOf(10)));// 钱数chu积分换算系数=积分数量
				orderMapper.updateOrderPointById(point, o.getId());
			} else {
				for (Order o : list) {
					totalMoney = add(totalMoney, o.getTotalCost());
					BigDecimal point = BigDecimal.ZERO;// 初始化积分变量为0
					// System.out.println("ID:" + o.getId());
					point = point.add(o.getTotalCost().divide(BigDecimal.valueOf(10)));// 钱数chu积分换算系数=积分数量
					orderMapper.updateOrderPointById(point, o.getId());
				}
			}
			// System.out.println("money:" + list);
			// System.out.println("-----------" + totalMoney);
			return totalMoney;
		} else {
			return totalMoney;
		}

	}

	public static BigDecimal add(BigDecimal totalMoney, BigDecimal m) {
		return totalMoney.add(m);
	}

    @Override
    public int gainYetBuyNum(String goodsId, String memberId) {
        Integer num = orderMapper.gainYetBuyNum(goodsId, memberId);
        return num!=null?num.intValue():0;
    }

    @Override
    public boolean checkerOrderThereHdGoods(String userName, String orderId) {
        Integer num = orderMapper.checkerOrderThereHdGoods(userName, orderId);
        return num!=null&&num!=0?true:false;
    }

	@Override
	public boolean checkerOrderShipStatus(String orderNum) {
		Order order = orderMapper.gainShipStatusBuyOrderNum(orderNum);
		
			if ("3".equals(order.getShipStatus())
					&& "0".equals(order.getPayStatus())) {
				return true;
			}
		
		return false;
	}

	@Override
	public String findAdminMobileByOrderNo(String orderNum) {
		return orderMapper.gainAdminMobileByOrderNum(orderNum);
	}

	@Override
	public boolean checkOrderArea(String orderNum) {
		String area = membersMaper.gainMobileByOrderNum(orderNum);
		if ("2278".equals(area)
				|| "2323".equals(area)
				|| "2296".equals(area)
				|| "2307".equals(area)
				|| "2277".equals(area)
			//	|| "2185".equals(area)
				) {
			return true;
		}
		return false;
	}
	
	public boolean checkOrderCity(String id) {
		String area = membersMaper.gainCityByOrderId(id);
		if ("2286".equals(area)) {
			return true;
		}
		return false;
	}

}
