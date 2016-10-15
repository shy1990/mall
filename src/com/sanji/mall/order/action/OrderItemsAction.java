package com.sanji.mall.order.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.service.OrderItemsService;

/**
 * 订单详情相关类
 * 
 * @ClassName: OrderItemsAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-13 上午10:47:00
 * 
 */
@Namespace("/orderItems")
@Action(value = "orderItemsAction", results = { @Result(name = "orders", location = "/admin/myCenter/order/orders.jsp") })
public class OrderItemsAction extends BaseAction implements ModelDriven<OrderItems> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OrderItemsAction.class);

	private OrderItems orderItems;

	public OrderItems getModel() {
		// TODO Auto-generated method stub
		if (orderItems == null)
			orderItems = new OrderItems();
		return orderItems;
	}

	@Resource
	private OrderItemsService orderItemsService;

	/**
	 * 统计指定时间成交记录
	 * 
	 * @Title: gainDeal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void gainDeal() {
		try {
			// // System.out.println("开始查询月成交记录信息：" + orderItems.getTargetType()
			// +
			// "  " + orderItems.getTargetId());
			Map param = new HashMap();
			param.put("minTime", DateUtil.getDateStrToDate(DateUtil.currentMonFirstDay().toString(), "yyyy-MM-dd"));
			param.put("maxTime", DateUtil.getDateStrToDate(DateUtil.currentMonLastDay().toString(), "yyyy-MM-dd"));
			param.put("page", orderItems.getPage());
			param.put("rows", orderItems.getRows());
			param.put("targetType", orderItems.getTargetType());
			param.put("targetId", orderItems.getTargetId());
			Map resultMap = new HashMap();
			resultMap.put("deals", orderItemsService.gainDeal(param));
			String countNum = orderItemsService.gainDealCountNum(param).toString();
			int pageCount = (Integer.parseInt(countNum) - 1) / orderItems.getRows() + 1;
			resultMap.put("pageCount", pageCount);

			// logger.info("月成交记录查询数据：" + JSON.toJSONString(resultMap));

			writeJson(resultMap);

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
	}
}
