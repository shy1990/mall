/**  
 * @Title: SandPayAction.java
 * @Package com.sanji.mall.api.pos.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-12-4 下午3:02:05
 * @version V1.0  
 */
package com.sanji.mall.api.pos.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pay.service.PayService;
import com.sanji.mall.pojo.SandPayPojo;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.qb.service.QbService;
import com.sanji.mall.qb.service.impl.QbServiceImpl;

/**
 * 中信pos机
 * 
 * @ClassName: SandPayAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-12-4 下午3:02:05
 */
@Namespace("/pos")
@Action(value = "sandPayAction", results = { @Result(name = "succeed", location = "/goods/goods.jsp") })
public class SandPayAction extends BaseAction implements ModelDriven<SandPayPojo> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private AdminService adminService;
	@Resource
	private OrderService orderService;
	@Resource
	private PayService payService;
	@Resource
	private QbService qbService;

	private SandPayPojo sandPayPojo = new SandPayPojo();
	private SandPayPojo resultPojo;

	@Resource
	private MemberService memberService;

	private Order order = new Order();
	// TODO 积分系数未设置
	private float pointCoefficient = 10;
	
	//private static String YD_BASE_URL = "http://localhost:8080/";
	//private static String YD_BASE_URL = "http://yd.3j1688.com/";
	private static String YD_BASE_URL = "http://yd.3j168.cn/";

	/**
	 * POS登录验证
	 * 
	 * @Title: inspectionUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void doNotNeedSession_inspectionUser() {
System.out.println(JSON.toJSONString(sandPayPojo));
		resultPojo = new SandPayPojo();
		try {
			if (getLoginHmac(sandPayPojo).equals(sandPayPojo.getHmac())) {// 验证签名数据
				if (null != sandPayPojo.getEmployeeId() && null != sandPayPojo.getPassword()) {// 用户密码是否为空
					Admin admin = adminService.gainAdminByLogin(sandPayPojo.getEmployeeId(), sandPayPojo.getPassword());
					if (null != admin) {
						resultPojo.setEmployeeId(admin.getUserNum());
						resultPojo.setEmployeeName(admin.getTruename());
						resultPojo.setEmployeeTel(admin.getMobilephone());
						resultPojo.setResult_code("1");
						resultPojo.setResult_type("成功");
					} else {
						resultPojo.setResult_code("4");
						resultPojo.setResult_type("无效的登录名或者密码");
					}
				} else {
					resultPojo.setResult_code("3");
					resultPojo.setResult_type("登录名或者密码不能为空");
				}
			} else {
				resultPojo.setResult_code("2");// 签名数据无效
				resultPojo.setResult_type("无效的签名数据");
			}
		} catch (Exception e) {
			log.equals("杉德POS登录验证SandPayAction出现异常：" + e);
			resultPojo.setResult_code("9");// 签名数据无效
			resultPojo.setResult_type("未知错误，请联系相关技术人员！");
		}
		writeJson(resultPojo);

	}

	/**
	 * POS端订单查询
	 * 
	 * @Title: gainOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	/*
	 * public void doNotNeedSession_gainOrder() { resultPojo = new
	 * SandPayPojo(); try { if
	 * (getOrderHmac(sandPayPojo).equals(sandPayPojo.getHmac())) {// 验证签名数据 if
	 * (null != sandPayPojo.getEmployeeId() && null != sandPayPojo.getOrderNo())
	 * {// 工号和订单号是否为空 Integer
	 * ordernum=Integer.parseInt(sandPayPojo.getOrderNo()); if
	 * ((100-ordernum)>=0) {
	 * resultPojo.setEmployeeId(sandPayPojo.getEmployeeId());
	 * resultPojo.setOrderId(sandPayPojo.getOrderNo());
	 * resultPojo.setOrderNo(sandPayPojo.getOrderNo());
	 * resultPojo.setShipName("测试"+sandPayPojo.getOrderNo());
	 * resultPojo.setShipAdd("山东省济南市天桥区凤凰山商贸城 互联网部");
	 * resultPojo.setShipTel("1888888888"); resultPojo.setOrderAmount(0.01 +
	 * ""); resultPojo.setOrderStatus("0"); resultPojo.setResult_code("1");
	 * resultPojo.setResult_type("成功"); } else { resultPojo.setResult_code("6");
	 * resultPojo.setResult_type("无效的订单号"); } } else {
	 * resultPojo.setResult_code("5"); resultPojo.setResult_type("工号或订单号不能为空");
	 * } } else { resultPojo.setResult_code("2");// 签名数据无效
	 * resultPojo.setResult_type("无效的签名数据"); } } catch (Exception e) {
	 * log.equals("POS端查询订单SandPayAction出现异常：" + e);
	 * resultPojo.setResult_code("9");// 签名数据无效
	 * resultPojo.setResult_type("未知错误，请联系相关技术人员！"); } writeJson(resultPojo); }
	 */
	public void doNotNeedSession_gainOrder() {
		resultPojo = new SandPayPojo();
		try {
			if (getOrderHmac(sandPayPojo).equals(sandPayPojo.getHmac())) {// 验证签名数据
				if (null != sandPayPojo.getEmployeeId() && null != sandPayPojo.getOrderNo()) {// 工号和订单号是否为空
					String orderNum = sandPayPojo.getOrderNo();
					  if(MsgUtil.CheckOrderFromBuzmgt(orderNum)){
						  if(orderService.checkOrderArea(orderNum)){//判断订单是否为指定的区域，如果是就判断订单是否签收，如果不是直接返回订单信息
						    	if(orderService.checkerOrderShipStatus(orderNum)){//判断订单是否签收
						    		resultPojo = messageToPos(resultPojo);//返回订单信息
								}else{
									resultPojo.setResult_code("6");
									resultPojo.setResult_type("未获取到订单信息，请先签收");
								}
						    }else{
						    	 resultPojo = messageToPos(resultPojo);//返回订单信息
						    }
					  }else{
						  resultPojo = messageToPos(resultPojo);//返回订单信息
						  }
					 
						
				} else {
					resultPojo.setResult_code("5");
					resultPojo.setResult_type("工号或订单号不能为空");
				}
			} else {
				resultPojo.setResult_code("2");// 签名数据无效
				resultPojo.setResult_type("无效的签名数据");
			}
		} catch (Exception e) {
			log.equals("杉德POS端查询订单SandPayAction出现异常：" + e);
			resultPojo.setResult_code("9");// 签名数据无效
			resultPojo.setResult_type("未知错误，请联系相关技术人员！");
		}
		writeJson(resultPojo);
	}
    
	/**
	 * 
	 * 将订单信息返回给pos终端
	 */
	private SandPayPojo messageToPos(SandPayPojo resultPojo) {
		// 判断是哪里的订单yd开头的订单就调用远程接口查询订单
		if (sandPayPojo.getOrderNo().toLowerCase()
				.startsWith("yd")) {
			// 调用远程方法
			getYdmallOrder(sandPayPojo.getOrderNo()
					.toLowerCase());
		} else {
			// MsgUtil.MsgConcelOrderToUser(null, "18453170418",
			// "调用pos查询laoshagnch订单:"+sandPayPojo.getEmployeeId()+"   订单号："+sandPayPojo.getOrderNo().toLowerCase());
			Order order = orderService
					.gainOrderByID(sandPayPojo.getOrderNo());
			if (null != order) {
				resultPojo.setEmployeeId(sandPayPojo
						.getEmployeeId());
				resultPojo.setOrderId(order.getId());
				resultPojo.setOrderNo(order.getOrderNum());
				resultPojo.setShipName(order.getShipName());
				resultPojo.setShipAdd(order.getPname()
						+ order.getCname() + order.getAname()
						+ order.getAddress());
				resultPojo.setShipTel(order.getShipTel());
				// resultPojo.setOrderAmount(order.getTotalCost()
				// + "");
				//if(orderService.checkOrderCity(order.getId())){
					
				if("LD".equals(order.getOrderNum().substring(0, 2))){
					resultPojo.setOrderAmount(new BigDecimal(order.getTotalCost()+"").add(new BigDecimal("20"))+"");
				}else if("DL".equals(order.getOrderNum().substring(0, 2))){
					resultPojo.setOrderAmount(order.getTotalCost()+"");
				}else{
					resultPojo.setOrderAmount(new BigDecimal(mul(order.getActualPayNum()))+"");
				}
				
				/*}else{
					resultPojo.setOrderAmount(order.getActualPayNum()+"");
				}*/
				resultPojo.setOrderStatus(order.getPayStatus());
				resultPojo.setResult_code("1");
				resultPojo.setResult_type("成功");
			} else {
				resultPojo.setResult_code("6");
				resultPojo.setResult_type("无效的订单号");
			}
		}
		
		return resultPojo;
	}
	
	/**  
	* 提供精确的乘法运算。  
	* @param ActualPayNum 实付金额
	* @param rate ：0.55% (费率)
	* @return 两个参数的积  
	*/  
	
	private static Double mul(BigDecimal ActualPayNum){   
	BigDecimal rate = new BigDecimal("1.0055"); //费率  
	
	
	return ActualPayNum.add(new BigDecimal("14.5")).multiply(rate).doubleValue();   
	}   
	
	public SandPayPojo getYdmallOrder(String orderNo){
		//String url =  "http://ydmall.3j1688.com/order/" + orderNo ;
		String url =  YD_BASE_URL+"orders/pos/"+orderNo ;
		try {
				Connection con = Jsoup.connect(url).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
						.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
						.ignoreContentType(true);
				Response rs = con.execute();// 获取响应
				System.out.println("::::::::：" + rs.body());
				JSONObject obj = JSONObject.parseObject(rs.body());
				
				//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商城接口查询订单："+obj.getString("id")+"  :"+obj.getJSONObject("createBy").getString("shipName"));
				
				if (null != obj && obj.containsKey("id")) {//返回值不为空，并且有id字段
					//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商城接口成功返回信息");
					String orderNum = obj.getString("id");
					//if(orderService.checkerOrderShipStatus(orderNum)){
						
					//	String adminMobile = orderService.findAdminMobileByOrderNo(orderNum);
					//	MsgUtil.PosPaymentsSuc(obj.getString("id"),adminMobile);//付款成功后向app推送消息
					
					resultPojo.setEmployeeId(sandPayPojo.getEmployeeId());
					resultPojo.setOrderId(obj.getString("id"));//订单id
					resultPojo.setOrderNo(obj.getString("id"));//订单编号
					resultPojo.setShipName(obj.getJSONObject("createBy").getString("shipName"));//收货人名称
					resultPojo.setShipAdd(obj.getJSONObject("createBy").getString("address"));//收货人地址
					resultPojo.setShipTel(obj.getJSONObject("createBy").getString("shipTel"));//收货人电话
					resultPojo.setOrderAmount(obj.getString("amount"));//订单总额
					if(obj.getString("status").equals("PROC")){
						resultPojo.setOrderStatus("0");//订单支付状态
					}else {
						resultPojo.setOrderStatus("1");//订单支付状态
					}
					resultPojo.setResult_code("1");
					resultPojo.setResult_type("成功");
					//}
				} else {
					resultPojo.setResult_code("6");
					resultPojo.setResult_type("无效的订单号");
				}
			
		} catch (Exception e) {
			//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商城接口异常："+e.getMessage());
			e.printStackTrace();
		}
		return resultPojo;
	}
	
	

	/**
	 * POS支付通知
	 * 
	 * @Title: payInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	/*
	 * public void doNotNeedSession_payInfo() { resultPojo = new SandPayPojo();
	 * try { if (getPayInfoHmac(sandPayPojo).equals(sandPayPojo.getHmac())) {//
	 * 验证签名数据 if (null != sandPayPojo.getEmployeeId() && null !=
	 * sandPayPojo.getOrderId() && null != sandPayPojo.getPayNO()) {//
	 * 工号和订单号是否为空 Integer ordernum=Integer.parseInt(sandPayPojo.getOrderId());
	 * if ((100-ordernum)>=0) { Double u = Double.valueOf(0.01 + "") -
	 * Double.valueOf(sandPayPojo.getPayAmount() + ""); if (u == 0.0) {//
	 * 比较订单价格是否相同 resultPojo.setOrderId(sandPayPojo.getOrderId());
	 * resultPojo.setOrderNo(sandPayPojo.getOrderId());
	 * resultPojo.setPayNO(sandPayPojo.getPayNO());
	 * resultPojo.setEmployeeId(sandPayPojo .getEmployeeId());
	 * resultPojo.setResult_code("1"); resultPojo.setResult_type("成功"); } else {
	 * resultPojo.setResult_code("7");
	 * resultPojo.setResult_type("支付金额和订单金额不一致，不给于处理"); } } else {
	 * resultPojo.setResult_code("6"); resultPojo.setResult_type("无效的订单号"); }
	 * 
	 * } else { resultPojo.setResult_code("5");
	 * resultPojo.setResult_type("工号、订单号或支付流水号不能为空"); } } else {
	 * resultPojo.setResult_code("2");// 签名数据无效
	 * resultPojo.setResult_type("无效的签名数据"); } } catch (NumberFormatException e)
	 * { log.equals("通用POS支付通知接口SandPayAction出现异常：" + e);
	 * resultPojo.setResult_code("9");// 签名数据无效
	 * resultPojo.setResult_type("未知错误，请联系相关技术人员！"); } writeJson(resultPojo); }
	 */
	public void doNotNeedSession_payInfo() {
		
		resultPojo = new SandPayPojo();
		try {
			if (getPayInfoHmac(sandPayPojo).equals(sandPayPojo.getHmac())) {// 验证签名数据
				if (null != sandPayPojo.getEmployeeId() && null != sandPayPojo.getOrderId() && null != sandPayPojo.getPayNO()) {// 工号和订单号是否为空
					//判断订单号是否是移动商城的，如果是移动商城的那么就通知移动商城完结改订单
					if(sandPayPojo.getOrderId().toLowerCase().startsWith("yd")){
						//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "pos回调成功移动商城订单号:"+sandPayPojo.getOrderId().toLowerCase());
						//调用远程方法
						updateYdmallOrder(sandPayPojo.getOrderId().toLowerCase());
						 
					}else { 
						//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "pos回调成功老商城订单:"+sandPayPojo.getEmployeeId()+"   订单号："+sandPayPojo.getOrderId());
						order = orderService.gainOrderByID(sandPayPojo.getOrderId());
						if (null != order) {
							double f1 = 0;
							if("LD".equals(order.getOrderNum().substring(0, 2))){
								 f1 = Double.parseDouble(String.format("%.2f", new BigDecimal(order.getTotalCost()+"").add(new BigDecimal("20")).doubleValue()));
							}else if("DL".equals(order.getOrderNum().substring(0, 2))){
								 f1 = Double.parseDouble(String.format("%.2f", order.getTotalCost().doubleValue()));
							}else{
								 f1 = Double.parseDouble(String.format("%.2f", mul(order.getActualPayNum())));
							}
							
							
							Double   u =  f1 - Double.valueOf(sandPayPojo.getPayAmount() + "") ;
							
							if (u == 0.0) {// 比较订单价格是否相同
								PayDeal deal = payService.gainDealByDeal(sandPayPojo.getPayNO(), sandPayPojo.getCompanyName());
								if (null == deal) {
									deal = new PayDeal();
									deal.setId(ToolsUtil.getUUID());
									deal.setCreateTime(new Date());
									if("LD".equals(order.getOrderNum().substring(0, 2))){
									 deal.setOrderAmount(new BigDecimal(order.getTotalCost()+"").add(new BigDecimal("20")));
									}else if("DL".equals(order.getOrderNum().substring(0, 2))){
										 deal.setOrderAmount(order.getTotalCost());
									}else{
										 deal.setOrderAmount(new BigDecimal(mul(order.getActualPayNum())));
									}
									
									deal.setDealFee(new BigDecimal(sandPayPojo.getPayAmount()));
									deal.setDealState("SUCCESS");
									deal.setDealSigunre(sandPayPojo.getHmac());
									deal.setDealId(sandPayPojo.getPayNO());
									deal.setDealType("pos");
									deal.setPayType("pos");
									deal.setPayee(sandPayPojo.getEmployeeName());
									deal.setPayeeNo(sandPayPojo.getEmployeeId());
									deal.setOrderId(sandPayPojo.getOrderId());
									deal.setOrderNo(order.getOrderNum());
									deal.setBankCardNo(sandPayPojo.getBankCardNo());
									deal.setBankCardName(sandPayPojo.getBankCardName());
									payService.insetPayDeal(deal);
									
									resultPojo.setOrderId(order.getId());
									resultPojo.setOrderNo(order.getOrderNum());
									resultPojo.setPayNO(sandPayPojo.getPayNO());
									resultPojo.setEmployeeId(sandPayPojo.getEmployeeId());
									resultPojo.setResult_code("1");
									resultPojo.setResult_type("成功");
	
									order = orderService.gainOrderALLByID(sandPayPojo.getOrderId());
	
									try {
										// 增积分
										List<OrderItems> orderItemss = order.getOrderItemss();
										addPoint(orderItemss);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										MsgUtil.testSuccess("15610696299", "积分异常" + e.getMessage());
										e.printStackTrace();
									}
	
									editBalancePay();// 如果是钱包支付或者钱包混合支付，修改支付状态
	                                //调用业务后台waterOrder
									MsgUtil.sendToWaterOrder(order.getOrderNum(),order.getActualPayNum(),order.getCreatetime());
								} else {
									resultPojo.setResult_code("8");
									resultPojo.setResult_type("重复提交，不给于处理");
								}
							} else {
								resultPojo.setResult_code("7");
								resultPojo.setResult_type("支付金额和订单金额不一致，不给于处理");
							}
						} else {
							resultPojo.setResult_code("6");
							resultPojo.setResult_type("无效的订单号");
						}
					}
				} else {
					resultPojo.setResult_code("5");
					resultPojo.setResult_type("工号、订单号或支付流水号不能为空");
				}
			} else {
				resultPojo.setResult_code("2");// 签名数据无效
				resultPojo.setResult_type("无效的签名数据");
			}
		} catch (NumberFormatException e) {
			log.equals("通用POS支付通知接口SandPayAction出现异常：" + e);
			resultPojo.setResult_code("9");// 签名数据无效
			resultPojo.setResult_type("未知错误，请联系相关技术人员！");
		}catch(Exception e){
			 e.printStackTrace();
			//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "pos回调异常："+e.getMessage()); 
			resultPojo.setResult_code("9");// 签名数据无效
			resultPojo.setResult_type("未知错误，请联系相关技术人员！");
		}
		writeJson(resultPojo);
	}

	/**
	 * 远程调用订单支付成功
	 * @param orderNo
	 */
	private SandPayPojo updateYdmallOrder(String orderNo) {
		String url =  YD_BASE_URL+"orders/pos/"+orderNo+"/payed"  ;
		try {
			Connection con = Jsoup.connect(url).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
					.ignoreContentType(true); 
			Response rs = con.execute();// 获取响应
			System.out.println("::::::::：" + rs.body());
			JSONObject obj = JSONObject.parseObject(rs.body());
			//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商接口成功");
			//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商接口成功1："+obj.toJSONString());
			if(obj!=null&&obj.containsKey("order")){
				//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商接口成功2");
				if(obj.getInteger("code")==2){//订单已支付，无需重复支付！
					resultPojo.setResult_code("8");
					resultPojo.setResult_type("重复提交，不给于处理");
				}else {//支付状态更改成功
					resultPojo.setOrderId(obj.getJSONObject("order").getString("id"));
					resultPojo.setOrderNo(obj.getJSONObject("order").getString("id"));
					resultPojo.setPayNO(sandPayPojo.getPayNO());
					resultPojo.setEmployeeId(sandPayPojo.getEmployeeId());
					resultPojo.setResult_code("1");
					resultPojo.setResult_type("成功");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//MsgUtil.MsgConcelOrderToUser(null, "18453170418", "调用移动商接口异常:"+e.getMessage());
		}
		return resultPojo;
	}

	/**
	 * 线上钱包支付
	 * 
	 * @Title: balancePay
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void editBalancePay() {
		try {
			// 如果订单钱包交易单号不为空
			if (order.getWalletPayNo()!= null && !"".equals(order.getWalletPayNo())) {
				//SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
				// 调用添加钱包交易，返回流水号
				 //qbService.editPay(order.getWalletPayNo(), "SUCCESS", null);//废弃
				String url = QbServiceImpl.BASE_URL + order.getWalletPayNo() + "/status";
				RestTemplate restTemplate = new RestTemplate();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("status", "SUCCESS");
				// System.out.println(url + "        " + param);
				restTemplate.put(url, param);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.testSuccess("18453170418", "钱包支付pos回调修改状态异常：" + e.getMessage());
		}
	}

	/**
	 * 增用户积分
	 * 
	 * @Title: reducePoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderItemss
	 *            订单详情
	 * @return void 返回类型
	 * @author peter
	 * @throws
	 */
	public void addPoint(List<OrderItems> orderItemss) {

		for (OrderItems oi : orderItemss) {

			if (!oi.getTargetType().equals("point")) {
				try {
					// 增加用户积分
					editPoint(oi.getDealPrice(), order.getMemberId());
					// System.out.println("价格："+oi.getDealPrice());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					MsgUtil.testSuccess("15610696299", "加积分异常" + oi.getDealPrice().toString() + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新用户积分信息
	 * 
	 * @Title: editPoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param money
	 *            退款金额数量
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public boolean editPoint(BigDecimal money, String userId) {
		Members member = memberService.getMemberById(userId);
		BigDecimal point = BigDecimal.ZERO;// 初始化积分变量为0

		point = point.add(money.divide(BigDecimal.valueOf(pointCoefficient)));// 钱数chu积分换算系数=积分数量

		point = BigDecimal.valueOf(Math.floor(point.doubleValue()));// 对计算出的积分取整
		// 消费积分
		point = member.getPoint().add(point);// 用户已有积分加上本次的积分

		// 更新积分
		// pointGoodsMaper.updatePoint(member.getId(), point);
		member.setPoint(point);
		memberService.updateByPrimaryKey(member);
		return true;
	}

	/**
	 * @return the sandPayPojo
	 */
	public SandPayPojo getSandPayPojo() {
		return sandPayPojo;
	}

	/**
	 * @param sandPayPojo
	 *            the sandPayPojo to set
	 */
	public void setSandPayPojo(SandPayPojo sandPayPojo) {
		this.sandPayPojo = sandPayPojo;
	}

	/*
	 * (非 Javadoc) <p>Title: getModel</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */

	public SandPayPojo getModel() {
		// TODO Auto-generated method stub
		return sandPayPojo;
	}

	/**
	 * 签到签名数据
	 * 
	 * @Title: getLoginHmac
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param pojo
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	private String getLoginHmac(SandPayPojo pojo) {
		System.out.println(MD5.encrypt(pojo.getEmployeeId() + pojo.getPassword() + ResourceUtil.getSandPayKey()));
		return MD5.encrypt(pojo.getEmployeeId() + pojo.getPassword() + ResourceUtil.getSandPayKey());

	}

	public static void main(String[] args) {
		String hmack = MD5.encrypt("2016052708" + "e10adc3949ba59abbe56e057f20f883e" + "85D581431CF15B22A08073C442E281B7");
		 System.out.println(hmack);
		//new SandPayAction().getYdmallOrder(null);
		//new SandPayAction().updateYdmallOrder("yd123");
		//String encrypt = MD5.encrypt(pojo.getEmployeeId() + pojo.getOrderId() + pojo.getPayAmount() + pojo.getPayNO() + pojo.getBankCardNo() + ResourceUtil.getSandPayKey());
		//employeeId=12345678&orderId=18e2ceba8d7f491089b70747508bb367&payAmount=7.00
		 
		//String hmack = MD5.encrypt("00001"+"a206e475763146958e54b5e6bbd28e2a"+"7"+"183526755320" +"621226********5904"+"85D581431CF15B22A08073C442E281B7");
		//String hmack = MD5.encrypt("12345678"+"18e2ceba8d7f491089b70747508bb367"+"7.00"+"105251839160" +"622848********3978"+"85D581431CF15B22A08073C442E281B7");
		//System.out.println(hmack);
	}

	/**
	 * 订单查询签名
	 * 
	 * @Title: getOrderHmac
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param pojo
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	private String getOrderHmac(SandPayPojo pojo) {
		return MD5.encrypt(pojo.getEmployeeId() + pojo.getOrderNo() + ResourceUtil.getSandPayKey());
	}

	/**
	 * 支付通知签名
	 * 
	 * @Title: getPayInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param pojo
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String getPayInfoHmac(SandPayPojo pojo) {
		// System.out.println(pojo.getEmployeeId() + pojo.getOrderId() +
		// pojo.getPayAmount() + pojo.getPayNO() + pojo.getBankCardNo() +
		// ResourceUtil.getSandPayKey());
		String encrypt = MD5.encrypt(pojo.getEmployeeId() + pojo.getOrderId() + pojo.getPayAmount() + pojo.getPayNO() + pojo.getBankCardNo() + ResourceUtil.getSandPayKey());
		// System.out.println(encrypt);
		return encrypt;
	}

	/*
	 * public static void main(String[] args) throws JDOMException, IOException
	 * { String loginXml =
	 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><COD-MS><SessionHead><Version>V1.0</Version><ServiceCode>COD201</ServiceCode><TransactionID>NEPCOD201201106211108869768</TransactionID><SrcSysID>yeepay</SrcSysID><DstSysID>NEP</DstSysID><ReqTime>20110621162649</ReqTime><ExtendAtt></ExtendAtt><HMAC>D3C357E25D61155835F028A9C21D737B</HMAC></SessionHead><SessionBody><Employee_ID>888801</Employee_ID><Password>E10ADC3949BA59ABBE56E057F20F883E</Password></SessionBody></COD-MS>"
	 * ; String loginXml2 =
	 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><COD-MS><SessionHead><Version>V1.0</Version><ServiceCode>COD201</ServiceCode><TransactionID>NEPCOD201201106211108869768</TransactionID><SrcSysID>yeepay</SrcSysID><DstSysID>NEP</DstSysID><ReqTime>20110621162649</ReqTime><ExtendAtt></ExtendAtt><HMAC>D3C357E25D61155835F028A9C21D737B</HMAC></SessionHead><SessionBody><Employee_ID>888801</Employee_ID><Password>E10ADC3949BA59ABBE56E057F20F883E</Password></SessionBody></COD-MS>"
	 * ; // String loginXml =
	 * "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>"
	 * ;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	/**
	 * 使用套接字进行数据提交
	 * 
	 * @param requestUrl
	 *            目标地址
	 * @return string
	 * @throws Exception
	 */
	public String submit(String requestUrl, String content) throws Exception {
		// content = URLEncoder.encode(content, "utf-8");
		URL url = new URL(requestUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestProperty("content-type", "text/xml");
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("contentType", "utf-8");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setConnectTimeout(5000);
		httpURLConnection.setReadTimeout(5000);
		httpURLConnection.connect();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
		out.write(content);
		out.flush();
		// 接收服务器的返回：
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
		StringBuilder buffer = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
