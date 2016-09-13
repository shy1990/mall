/**  
 * @Title: EcRrpUtil.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-11-26 下午2:26:53
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.pojo.Json;

/**
 * 关于管易ERP接口通用类
 * 
 * @ClassName: EcRrpUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-11-26 下午2:26:53
 */
public class EcErpUtil {

	private static final String ERP_API_URL = "http://121.41.164.77:30014/sjdzcgerp/data.dpk?method=ecerp.shangpin.get&page_size=10&page_no=1";// 备份
																																				// http://121.41.164.77:30014/sjdzcgerp
	private static final String ERP_API_APPKEY = "9B7988EF20264F7CBDC077925869842C";

	/**
	 * 推送的新订单
	 * 
	 * @Title: OrderAddNew
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 订单信息
	 * @param @param userName 买家账号
	 * @param @param dealId 支付序列号
	 * @param @param dealType 支付代码
	 * @param @param amt 支付金额
	 * @param @param payTime 支付时间
	 * @param @param isCod 是否货到付款，0-非货到付款,1-货到付款
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author ZhouZhangbao
	 */
	public static Map<String, Object> OrderAddNew(Order order, String userName, String dealId, String dealType, String amt, String payTime, String isCod,String adminName) {
		//调用新管易推送订单
	    Map<String, Object> resultMap = OrderAddNew2( order,  userName,  dealId,  dealType,  amt,  payTime,  isCod,adminName);
			return resultMap;//返回新管易推送结果
	}
	
	
/*	public static Map<String, Object> oldOrderAddNew(Order order, String userName, String dealId, String dealType, String amt, String payTime, String isCod) {
        //调用新管易推送订单
        //Map<String, Object> resultMap = OrderAddNew2( order,  userName,  dealId,  dealType,  amt,  payTime,  isCod);
         
        Map<String, Object> retMap = new HashMap<String, Object>();
            Map<String, String> params = new HashMap<String, String>();
            try {
            String itemsns = "";
            String prices = "";
            String skusns = "";
            String nums = "";
            for (OrderItems items : order.getOrderItemss()) {
                if (itemsns.equals("")) {
                    if ("sku".equals(items.getTargetType())) {
                        itemsns = items.getGoodsSku().getSkuCode();// 商品代码
                        skusns = items.getGoodsSku().getSkuNum();// 规格代码
                    } else if ("accessories".equals(items.getTargetType())) {
                        itemsns = items.getAccessory().getAccessoriesNum();// 商品代码
                        skusns = items.getAccessory().getSpecCode();// 规格代码
                    } else if ("gift".equals(items.getTargetType())) {
                        itemsns = items.getGift().getAccessory().getAccessoriesNum();// 商品代码
                        skusns = items.getGift().getAccessory().getSpecCode();// 规格代码
                    } else if ("point".equals(items.getTargetType())) {
                        itemsns = items.getPointGoods().getIntegralCode();// 商品代码
                        skusns = items.getPointGoods().getSpecCode();// 规格代码
                    }
                    prices = items.getDealPrice() + "";
                    nums = items.getNums() + "";
                } else {
                    if ("sku".equals(items.getTargetType())) {
                        itemsns = itemsns + "," + items.getGoodsSku().getSkuCode();// 商品代码
                        skusns = skusns + "," + items.getGoodsSku().getSkuNum();// 规格代码
                    } else if ("accessories".equals(items.getTargetType())) {
                        itemsns = itemsns + "," + items.getAccessory().getAccessoriesNum();// 商品代码
                        skusns = skusns + "," + items.getAccessory().getSpecCode();// 规格代码
                    } else if ("gift".equals(items.getTargetType())) {
                        itemsns = itemsns + "," + items.getGift().getAccessory().getAccessoriesNum();// 商品代码
                        skusns = skusns + "," + items.getGift().getAccessory().getSpecCode();// 规格代码
                    } else if ("point".equals(items.getTargetType())) {
                        itemsns = itemsns + "," + items.getPointGoods().getIntegralCode();// 商品代码
                        skusns = skusns + "," + items.getPointGoods().getSpecCode();// 规格代码
                    }
                    prices = prices + "," + items.getDealPrice();
                    nums = nums + "," + items.getNums();
                }
            }
            params.put("mail", userName);// 买家账号
            params.put("itemsns", itemsns);// 商品编码列表：以半角逗号(,)分隔。
            params.put("prices", prices);// 商品价格列表：以半角逗号(,)分隔。商品价格个数必须与商品编码个数一致
            params.put("skusns", skusns);// 商品规格
            params.put("nums", nums);// 商品数量：以半角逗号(,)分隔。商品数量个数必须与商品编码个数一致。
            params.put("receiver_name", order.getShipName());// String 是 张三 收货人
            params.put("receiver_address", order.getAddress());// 收货地址
            params.put("receiver_state", order.getPname());// 省
            params.put("receiver_city", order.getCname());// 市
            params.put("receiver_district", order.getAname());// 区
            if ("1".equals(isCod)) {
                params.put("logistics_type", "B2BZTWLD");// 运输方式 货到付款
            } else {
                params.put("logistics_type", "B2BZTWLD");// 运输方式 费货到付款

            }

            params.put("outer_tid", order.getOrderNum());// 外部订单号
            params.put("outer_shop_code", "三际在线采购网");// 卖家账号
            params.put("receiver_phone", order.getShipTel());// 电话
            params.put("receiver_mobile", order.getShipTel());// 手机
            params.put("outer_ddly", "6");// 订单来源： 0-系统网站, 1-淘宝网站, 2-其他, 3-淘宝分销,
                                            // 4-拍拍网站, 5-京东商城, 6-当当网站, 7-E链通,
                                            // 8-商派网站, 9-POS门店, 10-商派分销王,
                                            // 11-一号店, 12-凡客商城, 99-商派独立网店
            // params.put("buyer_message", value);//买家留言
            params.put("store_code", "001");// 仓库代码
            params.put("receiver_zip", order.getShipZip());// receiver_zip邮编
            params.put("logistics_fee", order.getCarriage() == null ? "0" : order.getCarriage() + "");// 运输费用
            params.put("FPTT", order.getBillHead());// 发票抬头
            // params.put("SYFP", value);//是否发票：0 - 无发票
            params.put("LXDM", order.getBillType());// 发票类型代码
            params.put("ticket_no", dealId);// 交易单号
            params.put("pay_codes", dealType);// 支付代码
            params.put("pay_moneys", amt);// 支付金额
            params.put("pay_datatimes", "2015-01-13");// 支付时间
            // params.put("autosplit", value);//自动拆分组合商品
            params.put("trade_memo", order.getRemark());// 卖家备注
            params.put("is_cod", isCod);// 是否货到付款。(
                                        // 0-非货到付款,1-货到付款,如是货到付款toverify为0
                                        // )
            // params.put("YGDM", value);//员工代码
            params.put("invoice_content", order.getBillContent());// 发票内容
            // params.put("invoice_amount", value);//发票金额
            // params.put("invoice_number", value);//发票号
            // params.put("invoice_date", value);//发票日期
            // params.put("YFRQ", value);//预计发货日期
            // params.put("TB_BZ", value);//淘宝备注
            // params.put("cod_fee", value);//到付服务费
            // params.put("total_discount_fee", value);//让利金额
             params.put("pay_trade_ids", "11");//交易号数组
             params.put("pay_accounts", "11");//账号数组
             params.put("pay_memos", "11");//支付明细备注数组
            // params.put("add_district", value);//自动增加地区信息 ( 0-否,1-是 )
            params.put("appkey", ERP_API_APPKEY);
            params.put("method", "ecerp.trade.add_order_new");
            String xml = HttpClientUtils.sendPostSSLRequest(ERP_API_URL, params);
            Map<String, String> xmlMap = XmlUtil.xml2Map(xml);

            retMap.put("created", xmlMap.get("TradeOrdersNew.trade_orders_response.trade.created"));
            retMap.put("tid", xmlMap.get("TradeOrdersNew.trade_orders_response.trade.tid"));
            retMap.put("ERROR", xmlMap.get("TradeOrdersNew.ERROR"));
            if (null != retMap.get("ERROR") && !"".equals(retMap.get("ERROR"))) {
                MsgUtil.MsgSenderAdminByError("订单号：" + order.getOrderNum() + ";老管易推送订单失败！" + retMap.get("ERROR"));
            }
            
            
        } catch (Exception e) {
            MsgUtil.MsgSenderAdminByError("订单号：" + order.getOrderNum() + ";老管易推送订单失败！" + retMap.get("ERROR"));
            e.printStackTrace();
        }
        return retMap;
    }*/

	/**
	 * 查询订单发货状态
	 * 
	 * @Title: getOrderShipType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderNo
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author ZhouZhangbao
	 */
	public static Map<String, Object> getOrderShipType(String orderNo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("method", "ecerp.tradestate.get");
			params.put("appkey", ERP_API_APPKEY);
			params.put("condition", "LYDH ='" + orderNo + "'");
			String xml = HttpClientUtils.sendPostSSLRequest(ERP_API_URL, params);
			Map<String, String> xmlMap = XmlUtil.xml2Map(xml);
			retMap.put("GUID", xmlMap.get("tradestate_get_response.tradestates.tradestate.guid"));// 订单GUID
			retMap.put("DJBH", xmlMap.get("tradestate_get_response.tradestates.tradestate.djbh"));// 单据编号
			retMap.put("LYDH", xmlMap.get("tradestate_get_response.tradestates.tradestate.lydh"));// 来源单号
			retMap.put("ZDRQ", xmlMap.get("tradestate_get_response.tradestates.tradestate.zdrq"));// 制单日期
			retMap.put("SHENHE", xmlMap.get("tradestate_get_response.tradestates.tradestate.shenhe"));// 是否客审
																										// (
																										// 0-否,1-是
																										// )
			retMap.put("SHENHERQ", xmlMap.get("tradestate_get_response.tradestates.tradestate.shenherq"));// 客审
																											// 日期
			retMap.put("CWSH", xmlMap.get("tradestate_get_response.tradestates.tradestate.cwsh"));// 是否财审
																									// (
																									// 0-否,1-是
																									// )
			retMap.put("CWSHRQ", xmlMap.get("tradestate_get_response.tradestates.tradestate.cwshrq"));// 财神日期
			retMap.put("SM", xmlMap.get("tradestate_get_response.tradestates.tradestate.sm"));// 是否扫描
																								// (
																								// 0-否,1-是
																								// )
			retMap.put("FH", xmlMap.get("tradestate_get_response.tradestates.tradestate.fh"));// 是否发货
																								// (
																								// 0-否,1-是
																								// )
			retMap.put("FHRQ", xmlMap.get("tradestate_get_response.tradestates.tradestate.fhrq"));// 发货日期
			retMap.put("HH", xmlMap.get("tradestate_get_response.tradestates.tradestate.hh"));// 是否换货
																								// (
																								// 0-否,1-是
																								// )
			retMap.put("Refund_Status", xmlMap.get("tradestate_get_response.tradestates.tradestate.refund_status"));// 是否退款(
																													// 大于0表示有退款
																													// )
			retMap.put("IsBad", xmlMap.get("tradestate_get_response.tradestates.tradestate.isbad"));// 是否问题单
																									// (
																									// 0-否,1-是
																									// )
			retMap.put("ZF", xmlMap.get("tradestate_get_response.tradestates.tradestate.zf"));// 是否作废
																								// (
																								// 0-否,1-是
																								// )
			retMap.put("ZFRQ", xmlMap.get("tradestate_get_response.tradestates.tradestate.zfrq"));// 作废日期
			retMap.put("hbguid", xmlMap.get("tradestate_get_response.tradestates.tradestate.hbguid"));// 合并订单GUID
			retMap.put("is_payed", xmlMap.get("tradestate_get_response.tradestates.tradestate.is_payed"));// 是否支付
																											// (
																											// 0-否,1-是
																											// )
			retMap.put("shopcode", xmlMap.get("tradestate_get_response.tradestates.tradestate.shopcode"));// 卖家帐号
			retMap.put("modify_time", xmlMap.get("tradestate_get_response.tradestates.tradestate.modify_time"));// 修改时间
			retMap.put("wlgsdm", xmlMap.get("tradestate_get_response.tradestates.tradestate.wlgsdm"));// 物流公司代码
			retMap.put("wlgsmc", xmlMap.get("tradestate_get_response.tradestates.tradestate.wlgsmc"));// 物流公司名称
			retMap.put("wldh", xmlMap.get("tradestate_get_response.tradestates.tradestate.wldh"));// 物流单号
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 作废管易订单
	 * 
	 * @Title: cancelOderById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderNo
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author ZhouZhangbao
	 */
	public static Map<String, Object> cancelOderById(String orderNo) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("method", "ecerp.trade.cancel");
			params.put("appkey", ERP_API_APPKEY);
			params.put("outer_tid", orderNo);
			String xml = HttpClientUtils.sendPostSSLRequest(ERP_API_URL, params);
			Map<String, String> xmlMap = XmlUtil.xml2Map(xml);
			retMap.put("created", xmlMap.get("TradeCancel.cancel_order_response.trade.created"));// 作废时间
			retMap.put("orderNo", xmlMap.get("TradeCancel.cancel_order_response.trade.tid"));// 订单号
			retMap.put("ERROR", xmlMap.get("TradeOrders.ERROR"));// 出现异常时，所提示的信息
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 查询管易订单发货状态
	 * 
	 * @Title: gyShipStatus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param orderNo
	 * @param @return 设定文件
	 * @return boolean false-未发货 ，true-已发货
	 * @author 田超强
	 * @throws
	 */
	public static Json gyShipStatus(String orderNo) {
		Json j = new Json();
		Map<String, Object> map = getOrderShipType(orderNo);
		if (null != map) {
			if (map.containsKey("FH") && null != map.get("FH")) {
				// System.out.println("管易发货状态：" + map.get("FH"));
				// 0-否,1-是
				if ("0".equals(map.get("FH"))) {
					j.setSuccess(false);
				} else {
					j.setSuccess(true);
					j.setObj(map.get("FHRQ"));
				}
			}
		}
		return j;
	}
	
	
	/**
	 * 推送的新订单
	 * 
	 * @Title: OrderAddNew
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 订单信息
	 * @param @param userName 买家账号
	 * @param @param dealId 支付序列号
	 * @param @param dealType 支付代码
	 * @param @param amt 支付金额
	 * @param @param payTime 支付时间
	 * @param @param isCod 是否货到付款，0-非货到付款,1-货到付款
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @author ZhouZhangbao
	 */
	public static Map<String, Object> OrderAddNew2(Order order, String userName, String dealId, String dealType, String amt, String payTime, String isCod,String adminName) {
			Map<String, Object> retMap = new HashMap<String, Object>();
			Map<String, String> params = new HashMap<String, String>();
			Map<String, Object> message = new HashMap<String, Object>();
			try {
				//params.put("v", "1.0");
				//params.put("sign", "");
				message.put("TradeId", order.getOrderNum());
				message.put("Consignee", order.getShipName());
				message.put("ShopCode", "B2B");
				message.put("ShopName", userName);
				message.put("PayDate",  DateUtil.getStringByDate(new Date()) );
				//message.put("buyer_nick", order.getShipName());
				message.put("buyer_nick", userName);
				message.put("PayAmount", order.getTotalCost());
				message.put("MemberCode", "b2b");
				message.put("MemberName", userName);
				message.put("Quantity", 0);//数量传什么
				message.put("ConsigneeMobile", order.getShipTel());
				message.put("ConsigneeAddress", order.getPname()+" "+order.getCname()+" "+order.getAname()+" "+order.getAddress());
				message.put("ConsigneeProvinceName", order.getPname());
				message.put("ConsigneeCityName", order.getCname());
				message.put("ConsigneeCountyName", order.getAname());
				//message.put("TradeType", );
				List<Map<String, String>> ProductDetails = new ArrayList<Map<String, String>>();
				for(OrderItems items : order.getOrderItemss()){//订单详情集合
					Map<String, String> ProductDetail = new HashMap<String, String>();
					if ("sku".equals(items.getTargetType())) {
						//ProductDetail.put("ProductCodeOldPlatform", items.getGoodsSku().getSkuCode());//平台商品ID，匹配铺货关系 
						//ProductDetail.put("ProductSkuCodePlatform", items.getGoodsSku().getSkuNum());//平台规格ID，匹配铺货关系
						
						ProductDetail.put("ProductCodeOldPlatform", items.getGoodsSku().getGoods().getId());//
						ProductDetail.put("ProductSkuCodePlatform", items.getGoodsSku().getId());//
						
						//ProductDetail.put("ProductCode", items.getGoodsSku().getSkuCode());//商品编号
						ProductDetail.put("ProductName", items.getGoodsSku().getGoods().getName());//产品名称
						//ProductDetail.put("SkuCode", items.getGoodsSku().getSkuNum());//规格代码
						ProductDetail.put("SkuName", items.getGoodsSku().getGoods().getName() + items.getGoodsSku().getColor().getColorName());//单品名称
					} else if ("accessories".equals(items.getTargetType())) {
						//ProductDetail.put("ProductCodeOldPlatform",  items.getAccessory().getAccessoriesNum());//平台商品ID，匹配铺货关系 
						//ProductDetail.put("ProductSkuCodePlatform",  items.getAccessory().getSpecCode());//平台规格ID，匹配铺货关系
						
						ProductDetail.put("ProductCodeOldPlatform",  items.getAccessory().getId());//平台商品ID，匹配铺货关系 
						ProductDetail.put("ProductSkuCodePlatform",  items.getAccessory().getId());//平台规格ID，匹配铺货关系
						 
						//ProductDetail.put("ProductCode", items.getAccessory().getAccessoriesNum());//商品编号
						ProductDetail.put("ProductName", items.getAccessory().getName());//产品名称
						//ProductDetail.put("SkuCode", items.getAccessory().getSpecCode());//规格代码
						ProductDetail.put("SkuName", items.getAccessory().getName());//单品名称
					} else if ("gift".equals(items.getTargetType())) {
						//ProductDetail.put("ProductCodeOldPlatform", items.getGift().getAccessory().getAccessoriesNum());//平台商品ID，匹配铺货关系 
						//ProductDetail.put("ProductSkuCodePlatform", items.getGift().getAccessory().getSpecCode());//平台规格ID，匹配铺货关系
					    if(null!=items.getGift()&&null!= items.getGift().getAccessory()){
    						ProductDetail.put("ProductCodeOldPlatform", items.getGift().getAccessory().getId());//平台商品ID，匹配铺货关系 
    						ProductDetail.put("ProductSkuCodePlatform", items.getGift().getAccessory().getId());//平台规格ID，匹配铺货关系
    						
    						//ProductDetail.put("ProductCode", items.getGift().getAccessory().getAccessoriesNum());//商品编号
    						ProductDetail.put("ProductName", items.getGift().getAccessory().getName());//产品名称
    						//ProductDetail.put("SkuCode", items.getGift().getAccessory().getSpecCode());//规格代码
    						ProductDetail.put("SkuName", items.getGift().getAccessory().getName());//单品名称
					    }
					} else if ("point".equals(items.getTargetType())) {
						//ProductDetail.put("ProductCodeOldPlatform", items.getPointGoods().getIntegralCode());//平台商品ID，匹配铺货关系 
						//ProductDetail.put("ProductSkuCodePlatform",  items.getPointGoods().getSpecCode());//平台规格ID，匹配铺货关系
						ProductDetail.put("ProductCodeOldPlatform", items.getPointGoods().getId());//平台商品ID，匹配铺货关系  
						ProductDetail.put("ProductSkuCodePlatform",  items.getPointGoods().getId());//平台规格ID，匹配铺货关系
						
						//ProductDetail.put("ProductCode", items.getPointGoods().getIntegralCode());//商品编号
						ProductDetail.put("ProductName", items.getPointGoods().getName());//产品名称
						//ProductDetail.put("SkuCode", items.getPointGoods().getSpecCode());//规格代码
						ProductDetail.put("SkuName", items.getPointGoods().getName());//单品名称
					}
					ProductDetail.put("Quantity", items.getNums().toString());//数量
					ProductDetail.put("PriceSelling", items.getDealPrice().toString());//价格
					ProductDetails.add(ProductDetail);
				}
				message.put("ProductDetail", ProductDetails);
				
				//支付方式
				List<Map<String, String>> PayDetails = new ArrayList<Map<String, String>>();
				Map<String, String> PayDetail = new HashMap<String, String>();
				if ("1".equals(isCod)) {// 运输方式 货到付款
					message.put("TradeType", "cod");
					System.out.println("aaaaaaaaaa");
					PayDetail.put("TenderCode", "CodPay");
					PayDetail.put("TenderName", "货到付款");
				} else {
					PayDetail.put("TenderCode", "cash");
					PayDetail.put("TenderName", "线上支付");
				}
				PayDetail.put("PayTime",  DateUtil.getStringByDate(new Date()) );
				PayDetail.put("Amount", order.getTotalCost().toString());
				PayDetails.add(PayDetail);
				message.put("PayDetail", PayDetails);
				
				message.put("SellerMemo", userName.equals("zhangsan")?"测试不要发货":(order.getRemark()!=null?order.getRemark():""));
				message.put("BuyerMemo", adminName);
				message.put("NeedInvoice", false);
				
				List<Map<String, Object>> messages = new ArrayList<Map<String, Object>>();
				messages.add(message);
				params.put("message",messages.toString());
				System.out.println("请求参数："+JSON.toJSONString(messages));
//			String xml = HttpClientUtils.sendPostSSLRequest("http://121.43.123.30:30006/PubService.svc/AddSalesOrderOffline?v=1.0&sign=", params);
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost("http://121.43.123.30:30006/PubService.svc/AddSalesOrderOffline");
				httpPost.setEntity(new StringEntity("v=1.0&sign= &message="+JSON.toJSONString(messages),Charset.forName("utf8")));
				CloseableHttpResponse response2 = httpclient.execute(httpPost);
				 String reString = null;
				try {
				    System.out.println(response2.getStatusLine());
				    HttpEntity entity2 = response2.getEntity();
//				    EntityUtils.consume(entity2);
				    reString = EntityUtils.toString(entity2, Charset.forName("utf8"));
				    System.out.println(reString);
				} finally {
				    response2.close();
				}
				
			
			JSONObject xmlMap = JSONObject.parseObject(reString);
			
			System.out.println("推送返回值："+xmlMap);
			
			if (xmlMap.getBooleanValue("result")) {//成功
				JSONObject successMap = (JSONObject) xmlMap.getJSONObject("message").getJSONArray("successList").get(0);
				retMap.put("created", new Date());
				retMap.put("tid", successMap.get("tradeid"));
				retMap.put("ERROR", "");
			}else {
				JSONObject failMap = (JSONObject) xmlMap.getJSONObject("message").getJSONArray("failList").get(0);
				retMap.put("created", new Date());
				retMap.put("tid", "");
				retMap.put("ERROR", failMap.get("errMessage"));
				MsgUtil.MsgSenderAdminByError("订单号：" + order.getOrderNum() + ";新管易推送订单失败！" +failMap.get("errMessage"));
			}
			
		} catch (Exception e) {
			MsgUtil.MsgSenderAdminByError("订单号：" + order.getOrderNum() + ";新管易推送订单失败！" + retMap.get("ERROR")+e.getMessage());
			e.printStackTrace();
		}
		return retMap;
	}
	

	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(getOrderShipType("20151120085319751")));
		System.out.println(JSON.toJSONString(gyShipStatus("20151120085319751")));

	}

}
