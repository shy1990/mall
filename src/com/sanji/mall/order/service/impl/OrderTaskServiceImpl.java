package com.sanji.mall.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.EcErpUtil;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.model.Order;
import com.sanji.mall.order.dao.OrderMapper;
import com.sanji.mall.order.service.OrderTaskService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.qb.service.impl.QbServiceImpl;

@Component
public class OrderTaskServiceImpl implements OrderTaskService {

	@Autowired
	private OrderMapper orderMapper;

	@Scheduled(cron = "0 59 23 * * ? ")
	public void cancelNoPayOrder() {
		// TODO Auto-generated method stub
		// System.out.println("定时取消未付款订单任务执行");
		// //
		// System.out.println(orderMapper.selectByPrimaryKey("52e8801d89d34d5085b6e561af8c3586"));
		orderMapper.cancelNoPayOrder();
	}

	/**
	 * 定时确认，确定收货
	 * 
	 * @Title: orderConfirm
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	//@Scheduled(cron = "0 59 23 * * ? ")
	public void orderConfirm() {
		try {
			// 查找未发货的订单
			List<Order> noShipOrders = orderMapper.noShipGoods();
			for (Order o : noShipOrders) {// 遍历一遍未发货的订单，管易里面是否已发货，如果已发货就更新状态到订单表
				Json js = EcErpUtil.gyShipStatus(o.getOrderNum());
				if (js.getSuccess()) {
					o.setShipStatus("1");
					o.setShipTime(DateUtil.getDateStrToDate(js.getObj().toString()));
					orderMapper.updateByPrimaryKeySelective(o);
				}
			}
			// 把订单状态正常，线上支付，已付款，已发货，并且发货时间超过2天的订单状态改为已收货
			orderMapper.autoAffirmReceiving();
		} catch (Exception e) {
		}
	}

	/**
	 * 每小时执行一次,这里应该是轮寻是否有已发货订单。老关以的可以关掉里。
	 */
	//@Scheduled(cron = "0 0/59 * * * ?")
	public void sendOut() {
		try {
			List<Map<String, Object>> noShipOrder = orderMapper.getNoShipOrder();
			// System.out.println("查询出的未发货的列表" +
			// JSON.toJSONString(noShipOrder));
			if (null != noShipOrder && noShipOrder.size() > 0) {
				for (Map<String, Object> map : noShipOrder) {
					if (null != map && map.containsKey("ID") && map.containsKey("ECERPNO")) {
						Json j = EcErpUtil.gyShipStatus(map.get("ORDERNUM").toString());
						// System.out.println("管易查询结果" + JSON.toJSONString(j));
						if (j.getSuccess()) {
							orderMapper.autoShip(map.get("ECERPNO").toString());
						}
					}
				}
			}

		} catch (Exception e) {
			// System.out.println("定时执行发货状态异常：" + e.getMessage());
		}
	}

	// 每天凌晨一点钟触发
	//@Scheduled(cron = "0 38 15 ? * *")//15点38执行
	@Scheduled(cron = "0 0 01 * * ? ")
	public void cancelUnSuccessOrder() {
		// System.out.println("执行销毁订单+++++++++++++++++++++++++++++++++");
		List<Order> orders = orderMapper.gainPayUnsuccessfulOrder();
		if (null != orders && orders.size() > 0) {
			for (Order o : orders) {
				updateState(o.getWalletPayNo(), o.getId());
			}
		}
		List<Order> orders2 =orderMapper.gainWalletUnsuccessfulOrder();
		if (null != orders2 && orders2.size() > 0) {
			for (Order o : orders2) {
				updateState(o.getWalletPayNo(), o.getId());
			}
		}
	}

	public void updateState(String qbid, String oid) {
		try {
			String url = QbServiceImpl.BASE_URL + qbid + "/status";
			// System.out.println("执行销毁订单：请求：" + url + "   " + qbid);
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "FAILURE");
			// System.out.println(url + "        " + param);
			restTemplate.put(url, param);
			// 修改该订单状态
			orderMapper.cancelNoPayOrderById(oid);
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.MsgConcelOrderToUser("", "18453170418", "批量取消钱包混合支付异常：" + e.getMessage() + ":" + oid);
		}
	}

	//修改钱包状态为失败，然后把钱返回给用户
	  public static void main2(String[] args) { 
		  String url = QbServiceImpl.BASE_URL + "JY23377818" + "/status";
			// System.out.println("执行销毁订单：请求：" + url + "   " + qbid);
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "FAILURE");
			// System.out.println(url + "        " + param);
			restTemplate.put(url, param);
			System.out.println("ok");
		  }
	  
	  //修改钱包状态为成功，把用户的钱扣掉
	  public static void main(String[] args) {
		  String url = QbServiceImpl.BASE_URL + "JY65149729"+ "/status";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "SUCCESS");
			// System.out.println(url + "        " + param);
			restTemplate.put(url, param);
			System.out.println("ok");
	}
	 

}
