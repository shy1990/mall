package junitTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.brand.service.BrandService;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.EcErpUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Brand;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.order.dao.OrderMapper;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pay.service.PayService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class JunitTest {
	@Autowired
	private OrderService orderService;
	@Resource
	private OrderMapper orderMapper;
	@Autowired
	private PayService payService;
	
	@Resource
	private AdminService adminService;
	
	@Autowired
	private BrandService b;
	
	@Autowired
	private MemberService m;

	@Test
	public void test() {
		List<Brand> gainAll = b.gainAll();
		System.out.println(gainAll);
	}


	public static class chekAgencyIpThread extends Thread {
		private Order order;
		private OrderService orderService;
		private String name;
		private PayService payService;

		public chekAgencyIpThread(Order order, OrderService orderService, String name, PayService payService) {
			super();
			this.order = order;
			this.orderService = orderService;
			this.name = name;
			this.payService = payService;
		}

		@Override
		public void run() {
			order = orderService.gainOrderALLByID(order.getId());

			Map<String, Object> map = EcErpUtil.OrderAddNew(order, name, "", "", "", "", order.getPayMent(),"");
			System.out.println(Thread.currentThread().getId()+"-- 推送结果：" + map);
			/*if (map.get("ERROR") != null) {
				if (map.get("ERROR").toString().contains("订单已存在")
						|| map.get("ERROR").toString().contains("uni_idx_dd_UniqueLYDH")) {
					return;
				}
			}*/
			/*if (null != map.get("tid") && !"".equals(map.get("tid"))) {
				order.setEcerpNo(map.get("tid") + "");
				order.setEcerpCreated(map.get("created") + "");
			}*/
			/*order.setEcerpError(map.get("ERROR") + "");
			order.setPayMent("1");
			orderService.updateEcerpById(order);*/
			// 更库存
			/*PayDeal payDeal = new PayDeal();
			payDeal.setId(ToolsUtil.getUUID());
			payDeal.setOrderId(order.getId());
			payService.updateInventory(payDeal);*/

			/*System.out.println(
					Thread.currentThread().getId() + "-- " + order.getId() + " : " + name + " " + map.get("tid"));*/
		}
	}

	@Test
	public void startMutil() {
		List<Order> orders = getOrders();

		// 启动多线程检测代理ip是否可用
		ExecutorService pool = Executors.newFixedThreadPool(2);// 创建一个固定大小为3的线程池

		for (int i = 0; i < orders.size(); i++) {
			pool.submit(new chekAgencyIpThread(orders.get(i), orderService, getShopName(orders.get(i).getMemberId()),
					payService));

		}

		System.out.println("所有线程运行完毕++++++++++++++++++++++++++++++++++++++++");
		pool.shutdown();

		while (true) {
			if (pool.isTerminated()) {
				System.out.println("结束了！===========================================");
				break;// 终止循环查询
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testAdd() {
		List<String> noDD = new ArrayList<String>();
		List<Order> orders = getOrders();

		for (Order order : orders) {
			order = orderService.gainOrderALLByID(order.getId());

			String name = getShopName(order.getMemberId());
			Map<String, Object> map = EcErpUtil.OrderAddNew(order, name, "", "", "", "", order.getPayMent(),"");
			System.out.println("erp：" + map);
			if (map.get("ERROR") != null) {
				if (map.get("ERROR").toString().contains("订单已存在")
						|| map.get("ERROR").toString().contains("uni_idx_dd_UniqueLYDH")) {
					continue;
				}
			}
			if (null != map.get("tid") && !"".equals(map.get("tid"))) {
				order.setEcerpNo(map.get("tid") + "");
				order.setEcerpCreated(map.get("created") + "");
			}
			order.setEcerpError(map.get("ERROR") + "");
//			order.setPayMent("1");
			orderService.updateEcerpById(order);
			// 更库存
			PayDeal payDeal = new PayDeal();
			payDeal.setId(ToolsUtil.getUUID());
			payDeal.setOrderId(order.getId());
			payService.updateInventory(payDeal);

			System.out.println(order.getId() + " : " + name + " " + map.get("tid"));
			if (StringUtils.isBlank(map.get("tid").toString())) {
				noDD.add(order.getId());
			}

			for (String id : noDD) {
				System.out.println("为成功：" + id);
			}
		}
		System.out.println("ok 完成");
	}

	private String getShopName(String memberId) {
		Members member = m.getMemberById(memberId);
		return member.getUsername();
	}

	private List<Order> getOrders() {
		List<Order> orders = orderMapper.getSomeOrder();
		return orders;
	}

	public void sendMessage() {
		Members members = new Members();
		members.setPage(1);
		members.setRows(1000);
		List<Members> gainMembers = m.gainMembers(members);
	}
	
	
	
	
	@Test
	public void sendOneOrder(){
		try {
			/*"20160816092534179",,*/
//			20160410174953086      20160410173925755 20160519194338877   20160519194412344

			String[] orders = {"20161017114921673"};
			for(String s : orders){
				System.out.println("开始推送订单："+s);
				Order	order=orderService.gainOrderByOrderNo(s);
				order = orderService.gainOrderALLByID(order.getId());
				Members members = m.gainMembersDetailById(order.getMemberId());
				Admin admin = adminService.getAdminById(members.getAdminId());
				
				Map<String, Object> map = EcErpUtil.OrderAddNew2(order, getShopName(order.getMemberId()), "", "", "", "", order.getPayMent(),admin!=null?admin.getTruename():"没有对应业务名称");
			}
			
			/*Order	order=orderService.gainOrderByOrderNo("20160130172816696");
				order = orderService.gainOrderALLByID(order.getId());

			Map<String, Object> map = EcErpUtil.OrderAddNew2(order, getShopName(order.getMemberId()), "", "", "", "", order.getPayMent())*/;
			//System.out.println(Thread.currentThread().getId()+"-- erp：" + map);
			//System.out.println(map.toString());
			/*if (map.get("ERROR") != null) {
				if (map.get("ERROR").toString().contains("订单已存在")
						|| map.get("ERROR").toString().contains("uni_idx_dd_UniqueLYDH")) {
					return;
				}
			}
			if (null != map.get("tid") && !"".equals(map.get("tid"))) {
				order.setEcerpNo(map.get("tid") + "");
				order.setEcerpCreated(map.get("created") + "");
			}
			order.setEcerpError(map.get("ERROR") + "");
			order.setPayMent("1");
			orderService.updateEcerpById(order);
			// 更库存
			PayDeal payDeal = new PayDeal();
			payDeal.setId(ToolsUtil.getUUID());
			payDeal.setOrderId(order.getId());
			payService.updateInventory(payDeal);*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	   @Test
	    public void getOrderDetail(){
	        try {
	            /*20151230113404019 1800
	            20151230082535482   2553
	            20151230193728647*/
	            //"20160220110230892","20160220111533562","20160220105027492","20160220093954029","20160220091302993","20160220113638836","20160220124852218","20160220130130616","20160220141920615","20160220153043097","20160220153117942",
	            //"20160220016004875",
	            String[] orders = {"20160510171858487"};
	            for(String s : orders){
	               // System.out.println("开始推送订单："+s);
	                Order   order=orderService.gainOrderByOrderNo(s);
	                order = orderService.gainOrderALLByID(order.getId());
	               // Map<String, Object> map = EcErpUtil.OrderAddNew2(order, getShopName(order.getMemberId()), "", "", "", "", order.getPayMent());
	                
	                String userName =  getShopName(order.getMemberId());
	                
	                Map<String, Object> message = new HashMap<String, Object>();
	                    //params.put("v", "1.0");
	                    //params.put("sign", "");
	                    message.put("订单编号：", order.getOrderNum());
	                   
	                   // message.put("ShopCode", "B2B");
	                    message.put("ShopName", userName);
	                    //message.put("PayDate",  DateUtil.getStringByDate(new Date()) );
	                    //message.put("buyer_nick", order.getShipName());
	                    //message.put("buyer_nick", userName);
	                    message.put("PayAmount", order.getTotalCost());
	                    message.put("MemberCode", "b2b");
	                    message.put("MemberName", userName);
	                   // message.put("Quantity", 0);//数量传什么
	                    message.put("Consignee", order.getShipName());
	                    message.put("ConsigneeMobile", order.getShipTel());
	                    message.put("ConsigneeAddress", order.getPname()+" "+order.getCname()+" "+order.getAname()+" "+order.getAddress());
	                   // message.put("ConsigneeProvinceName", order.getPname());
	                   // message.put("ConsigneeCityName", order.getCname());
	                   // message.put("ConsigneeCountyName", order.getAname());
	                    //message.put("TradeType", );
	                    List<Map<String, String>> ProductDetails = new ArrayList<Map<String, String>>();
	                    for(OrderItems items : order.getOrderItemss()){//订单详情集合
	                        Map<String, String> ProductDetail = new HashMap<String, String>();
	                        if ("sku".equals(items.getTargetType())) {
	                            ProductDetail.put("ProductCodeOldPlatform", items.getGoodsSku().getSkuCode());//平台商品ID，匹配铺货关系 
	                            ProductDetail.put("ProductSkuCodePlatform", items.getGoodsSku().getSkuNum());//平台规格ID，匹配铺货关系
	                            
	                           // ProductDetail.put("ProductCodeOldPlatform", items.getGoodsSku().getGoods().getId());//
	                           // ProductDetail.put("ProductSkuCodePlatform", items.getGoodsSku().getId());//
	                            
	                            //ProductDetail.put("ProductCode", items.getGoodsSku().getSkuCode());//商品编号
	                            ProductDetail.put("ProductName", items.getGoodsSku().getGoods().getName());//产品名称
	                            //ProductDetail.put("SkuCode", items.getGoodsSku().getSkuNum());//规格代码
	                            ProductDetail.put("SkuName", items.getGoodsSku().getGoods().getName() + items.getGoodsSku().getColor().getColorName());//单品名称
	                        } else if ("accessories".equals(items.getTargetType())) {
	                            ProductDetail.put("ProductCodeOldPlatform",  items.getAccessory().getAccessoriesNum());//平台商品ID，匹配铺货关系 
	                            ProductDetail.put("ProductSkuCodePlatform",  items.getAccessory().getSpecCode());//平台规格ID，匹配铺货关系
	                            
	                           // ProductDetail.put("ProductCodeOldPlatform",  items.getAccessory().getId());//平台商品ID，匹配铺货关系 
	                           // ProductDetail.put("ProductSkuCodePlatform",  items.getAccessory().getId());//平台规格ID，匹配铺货关系
	                             
	                            //ProductDetail.put("ProductCode", items.getAccessory().getAccessoriesNum());//商品编号
	                            ProductDetail.put("ProductName", items.getAccessory().getName());//产品名称
	                            //ProductDetail.put("SkuCode", items.getAccessory().getSpecCode());//规格代码
	                            ProductDetail.put("SkuName", items.getAccessory().getName());//单品名称
	                        } else if ("gift".equals(items.getTargetType())) {
	                            ProductDetail.put("ProductCodeOldPlatform", items.getGift().getAccessory().getAccessoriesNum());//平台商品ID，匹配铺货关系 
	                            ProductDetail.put("ProductSkuCodePlatform", items.getGift().getAccessory().getSpecCode());//平台规格ID，匹配铺货关系
	                           /* ProductDetail.put("ProductCodeOldPlatform", items.getGift().getAccessory().getId());//平台商品ID，匹配铺货关系 
	                            ProductDetail.put("ProductSkuCodePlatform", items.getGift().getAccessory().getId());//平台规格ID，匹配铺货关系
*/	                            
	                            //ProductDetail.put("ProductCode", items.getGift().getAccessory().getAccessoriesNum());//商品编号
	                            ProductDetail.put("ProductName", items.getGift().getAccessory().getName());//产品名称
	                            //ProductDetail.put("SkuCode", items.getGift().getAccessory().getSpecCode());//规格代码
	                            ProductDetail.put("SkuName", items.getGift().getAccessory().getName());//单品名称
	                        } else if ("point".equals(items.getTargetType())) {
	                            ProductDetail.put("ProductCodeOldPlatform", items.getPointGoods().getIntegralCode());//平台商品ID，匹配铺货关系 
	                            ProductDetail.put("ProductSkuCodePlatform",  items.getPointGoods().getSpecCode());//平台规格ID，匹配铺货关系
	                           // ProductDetail.put("ProductCodeOldPlatform", items.getPointGoods().getId());//平台商品ID，匹配铺货关系  
	                           // ProductDetail.put("ProductSkuCodePlatform",  items.getPointGoods().getId());//平台规格ID，匹配铺货关系
	                            
	                            //ProductDetail.put("ProductCode", items.getPointGoods().getIntegralCode());//商品编号
	                            ProductDetail.put("ProductName", items.getPointGoods().getName());//产品名称
	                            //ProductDetail.put("SkuCode", items.getPointGoods().getSpecCode());//规格代码
	                            ProductDetail.put("SkuName", items.getPointGoods().getName());//单品名称
	                        }
	                        ProductDetail.put("数量", items.getNums().toString());//数量
	                        ProductDetail.put("PriceSelling", items.getDealPrice().toString());//价格
	                        ProductDetails.add(ProductDetail);
	                    }
	                    message.put("ProductDetail", ProductDetails);
	                    
	                    //支付方式
	                    List<Map<String, String>> PayDetails = new ArrayList<Map<String, String>>();
	                    Map<String, String> PayDetail = new HashMap<String, String>();
	                    if ("1".equals(order.getPayMent())) {// 运输方式 货到付款
	                       // message.put("TradeType", "cod");
	                       // System.out.println("aaaaaaaaaa");
	                        //PayDetail.put("TenderCode", "CodPay");
	                        PayDetail.put("TenderName", "货到付款");
	                    } else {
	                       // PayDetail.put("TenderCode", "cash");
	                        PayDetail.put("TenderName", "线上支付");
	                    }
	                    //PayDetail.put("PayTime",  DateUtil.getStringByDate(new Date()) );
	                   // PayDetail.put("Amount", order.getTotalCost().toString());
	                    PayDetails.add(PayDetail);
	                    message.put("PayDetail", PayDetails);
	                
	                System.out.println(JSON.toJSONString(message));
	                System.out.println();
	                System.out.println();
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	
}
