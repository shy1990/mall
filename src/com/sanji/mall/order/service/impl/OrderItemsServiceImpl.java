package com.sanji.mall.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.dao.OrderItemsMapper;
import com.sanji.mall.order.service.OrderItemsService;

@Service("orderItemsService")
@Transactional(rollbackFor = Exception.class)
public class OrderItemsServiceImpl implements OrderItemsService {

	@Resource
	private OrderItemsMapper orderItemsMapper;

	public int gainTargetCountNum(String targetId, String targetType) {
		// TODO Auto-generated method stub
		return orderItemsMapper.gainTargetCountNum(targetId, targetType);
	}

	public List<Map> gainDeal(Map param) {
		// TODO Auto-generated method stub
		return orderItemsMapper.gainDeal(param);
	}

	public String gainDealNum(Map param) {
		// TODO Auto-generated method stub
		return orderItemsMapper.gainDealNum(param);
	}

	public String gainDealCountNum(Map param) {
		// TODO Auto-generated method stub
		return orderItemsMapper.gainDealCountNum(param);
	}

	public List<OrderItems> gainOrderItemAllSku(OrderItems orderItems) {
		List<OrderItems> list = orderItemsMapper.gainOrderItemAllSku(orderItems);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	public OrderItems selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderItemsMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(OrderItems record) {
		// TODO Auto-generated method stub
		return orderItemsMapper.updateByPrimaryKeySelective(record);
	}

//	@Override
//	public String getTargetTypeByOrderId(String orderId) {
//		// TODO Auto-generated method stub
//		return orderItemsMapper.selectTargetTypeByOrderId(orderId);
//	}

}
