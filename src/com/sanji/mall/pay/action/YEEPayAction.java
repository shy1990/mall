/**  
 * @Title: YEEPayAction.java
 * @Package com.sanji.mall.pay.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-11-24 下午5:09:52
 * @version V1.0  
 */
package com.sanji.mall.pay.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.web.client.RestTemplate;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.EcErpUtil;
import com.sanji.mall.common.util.HttpClientUtils;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.PayUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.model.PayRefund;
import com.sanji.mall.model.Regions;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pay.service.PayService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.pojo.YEEPayPojo;
import com.sanji.mall.qb.service.QbService;
import com.sanji.mall.qb.service.impl.QbServiceImpl;
import com.yeepay.Configuration;
import com.yeepay.PaymentForOnlineService;

/**
 * @ClassName: YEEPayAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-11-24 下午5:09:52
 */
@Namespace("/pay")
@Action(value = "yeePayAction", results = { @Result(name = "toPay", location = "/admin/pay/toPay.jsp"), @Result(name = "payState", location = "/admin/pay/payState.jsp"),
		@Result(name = "reqPay", location = "/admin/pay/reqPay.jsp"), @Result(name = "payAgain", location = "/admin/pay/payAgain.jsp")
// @Result(name = "reqRefund", location = "/admin/pay/reqRefund.jsp")
})
public class YEEPayAction extends BaseAction implements ModelDriven<YEEPayPojo> {
	private static final Logger logger = Logger.getLogger(YEEPayAction.class);
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;
	private String orderId;
	private String reFundAmt;
	private String reFuntdId;
	private String explain;
	private String frpId;
	private Order order = new Order();
	private String userShipAdd;
	private YEEPayPojo yeePayPojo = new YEEPayPojo();
	// 添加支付密码字段，协助字段
	private String payPwd;

	private List<Regions> provinces;
	private List<Regions> cityList;
	private List<Regions> areaList;

	@Resource
	private MemberService memberService;
	@Resource
	private OrderService orderService;
	@Resource
	private PayService payService;
	// @Resource
	// private RegionsService regionsService;
	@Resource
	private AdminService adminService;
	@Resource
	private QbService qbService;

	// TODO 积分系数未设置
	private float pointCoefficient = 10;

	/**
	 * 根据订单ID转向支付页面
	 * 
	 * @Title: toPayByOrderId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String toPayByOrderId() {
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			String memberId = sessionInfo.getUserId();
			Json json = new Json();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", orderId);
			boolean isStock = false;
			String stockMsg = "";
			// List<OrderItems> items =
			// orderItemsService.gainSkuByOrderId(orderId);
			order = orderService.gainDetail(param).get(0);
			
			
			getRequest().setAttribute("hdGoods", orderService.checkerOrderThereHdGoods(sessionInfo.getLoginName(), order.getId()));
			
			//判断是否使用了红包
			if(order.getHbNo()!=null&&null!=order.getHbNum()){
				//如果使用了红包那么就在此处临时把总价格改为减去红包后的价格方便后边计算实际支付的价格
				order.setTotalCost(order.getTotalCost().subtract(order.getHbNum()));
			}
			
			List<OrderItems> items = order.getOrderItemss();
			for (OrderItems orderItems : items) {
				if (null != orderItems.getGoodsSku()) {
					if (orderItems.getNums().intValue() > orderItems.getGoodsSku().getStock().intValue()) {
						isStock = true;
						String name = orderItems.getGoodsSku().getEdition() == null ? "" : orderItems.getGoodsSku().getEdition() + orderItems.getGoodsSku().getGoods().getName();
						stockMsg = stockMsg + "  您购买的手机商品 ‘" + name + "’ 实际库存为：" + orderItems.getGoodsSku().getStock() + "请修改后再支付！";
					}
				} else if (null != orderItems.getAccessory()) {
					int gmNum = orderItems.getNums().intValue();// 当前配件的数量

					for (OrderItems orderItems2 : items) {
						if (null != orderItems2.getGift() && orderItems2.getGift().getAccessory().getId().equals(orderItems.getAccessory().getId())) {
							// 数量相加，
							gmNum += orderItems2.getNums().intValue();// 累积购买的配件数量
						}
					}
					// 判断是否超过库存
					if (gmNum > Integer.parseInt(orderItems.getAccessory().getStock() + "")) {
						isStock = true;
						stockMsg = stockMsg + "  您购买的配件商品 ‘" + orderItems.getAccessory().getName() + "’ 实际库存为：" + orderItems.getAccessory().getStock() + "请修改后再支付！";
					}
				} else if (null != orderItems.getPointGoods()) {
					if (orderItems.getNums().intValue() > orderItems.getPointGoods().getStock().intValue()) {
						isStock = true;
						stockMsg = stockMsg + "  您购买的积分商品 ‘" + orderItems.getPointGoods().getName() + "’ 实际库存为：" + orderItems.getPointGoods().getStock() + "请修改后再支付！";
					}
				}
			}

			if (isStock) {// 库存不足
				json.setMsg(stockMsg);
				json.setSuccess(false);
				request.setAttribute("json", json);
				return "payState";
			}

			if (null != order && order.getPayStatus().equals("0")) {
				int result = orderService.updatePriceByMid(memberId);

				String blackId = memberService.gainBlackMemberById(memberId);
				if (result == 0) {
					json.setSuccess(true);
					request.setAttribute("json", json);
					logger.info("json:" + json.getSuccess());
					// logger.info("json:"+orderService.updatePriceByMid(MemberId));
				} else {
					json.setSuccess(false);
					request.setAttribute("json", json);
					logger.info("json:" + json.getSuccess());
					// logger.info("json:"+orderService.updatePriceByMid(MemberId));
				}
				request.setAttribute("blackId", blackId);

				/*
				 * //通过用户id获取未评论的订单金额 BigDecimal
				 * money=orderService.getNoCommentById
				 * (sessionInfo.getUserId(),order.getId()); //
				 * System.out.println("----------"+money);
				 * 
				 * // 更新用户积分 editPoint(money, sessionInfo.getUserId());
				 */

				return "toPay";
			} else if (null != order && order.getPayStatus().equals("1")) {
				json.setMsg("您的订单已经支付，无需再次付款！");
				json.setSuccess(true);
				request.setAttribute("json", json);
				return "payState";
			} else {
				json.setMsg("您的订单已经不存在，或出现异常联系客服！");
				json.setSuccess(false);
				request.setAttribute("json", json);
				return "payState";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// System.out.println("转向支付页面异常：" + e.getMessage());
			return "";
		}

	}

	/**
	 * 根据订单号前往E宝网关界面支付
	 * 
	 * @Title: toYEEPayByOrderId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String toYEEPayByOrderId() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());

			// 根据订单编号或者id去查询该订单有没有dd号码
			// 如果有dd号码重定向到已存在页面
			// String ecerp_no=orderService.selectErpnoById(order.getId());
			// if(ecerp_no != null && !"".equals(ecerp_no)){
			// return "payAgain";
			// }

			order = orderService.gainOrderALLByID(order.getId());

			// 货到付款支付并且dd号不为空，那么就是已经付款了。不可重复支付
			if (order.getPayMent().trim().equals("1")) {
				json.setMsg("您已选择货到付款，无需再次付款！");
				json.setSuccess(true);
				request.setAttribute("json", json);
				return "payState";
			}

			String remark = order.getRemark();
			// System.out.println(order.getTotalCost());
			BigDecimal totalCost = order.getTotalCost();

			// 判断是不是货到付款
			if ("SJ-HDFK".equals(frpId)) {// 货到付款//调用钱包余额支付状态为使用中
				
				// 线上支付后的在货到付款的吧5块加回来。
				/*if (remark == null ? false : remark.contains("立减5元")) {
					totalCost = totalCost.add(BigDecimal.valueOf(5));
					order.setTotalCost(totalCost);
					// orderService.updatePrice(order.getId(),
					// order.getTotalCost(), "", BigDecimal.ZERO,
					// BigDecimal.ZERO);

					order.setRemark("");

				}*/
				json = balancePay();// 调用钱包支付//pos钱包混合暂时不启用
				
				 if (!json.getSuccess()) {// 钱包支付异常 // 
					 // json.setMsg("支付出现异常！");
					 // json.setSuccess(false); 
					  request.setAttribute("json", json); 
					  return "payState"; 
				  }
				
				 //短信通知相关人员//推送到管易把订单信息
				adminService.msgInfoAdminByRegionsAndType(order.getProvince(), order.getCity(), order.getArea(), "2", null, order);
				if (!"4dec69e6af0c4346beac65c6f332aa5d".equals(sessionInfo.getUserId())) {// 过滤掉测试账号，测试账号数据不推送到管易
					Members m = memberService.gainMembersDetailById(sessionInfo.getUserId());
					Admin admin = adminService.getAdminById(m.getAdminId());
					String proFile = ResourceUtil.get("proFile");
					Map<String, Object> map = new HashMap<String, Object>();
					/*
					 * 在config中添加proFile字段
					 * 读取是否为测试环境test，正式环境需要去掉。
					 */
					Date now = new Date();
					if("test".equals(proFile)){
						order.setEcerpNo("test-"+ now.getTime());
						order.setEcerpCreated(now.toString());
						//服务站经理存在就将此订单向APP推送
						if(admin != null && !"".equals(admin)){
							String mobile2 = admin.getMobilephone();
							MsgUtil.MsgInfoXDApp(mobile2, order, m);
						}
					}else{
						//管易推送
					  map = EcErpUtil.OrderAddNew(order, sessionInfo.getLoginName(), "", "", "", "", "1",admin!=null?admin.getTruename():"没有对应业务名称");
						if (null != map.get("tid") && !"".equals(map.get("tid"))) {
							order.setEcerpNo(map.get("tid") + "");
							order.setEcerpCreated(map.get("created") + "");
							//服务站经理存在就将此订单向APP推送
							if(admin != null && !"".equals(admin)){
								String mobile2 = admin.getMobilephone();
								MsgUtil.MsgInfoXDApp(mobile2, order, m);
							}
						}
					}
					order.setEcerpError(map.get("ERROR") + "");
					order.setPayMent("1");
					orderService.updateEcerpById(order);
				}

				MsgUtil.MsgHDFKSuccess(order.getShipTel(), order.getOrderNum());
				json.setMsg("客官您选择的为货到付款！我们会尽快安排发货，请耐心等待");
				List<OrderItems> orderItemss = order.getOrderItemss();
				int sumJF = 0;
				// 判断该订单是否有积分商品
				boolean isPoint = false;

				for (OrderItems oi : orderItemss) {

					if (oi.getTargetType().equals("point")) {
						int num = oi.getNums().intValue();
						int integral = oi.getPointGoods().getIntegral().intValue();
						int sum = num * integral;
						sumJF += sum;
						isPoint = true;
					}
				}
				// isPoint
				if (isPoint) {
					// 扣除用户对应的积分
					String mid = order.getMemberId();
					Members members = memberService.getMemberById(mid);
					int point = members.getPoint().intValue();
					int npoint = point - sumJF;
					// System.out.println(npoint);
					members.setPoint(new BigDecimal(Integer.toString(npoint)));
					memberService.updateByPrimaryKeySelective(members);
				}
				// 更库存
				PayDeal payDeal = new PayDeal();
				payDeal.setId(ToolsUtil.getUUID());
				payDeal.setOrderId(order.getId());
				payService.updateInventory(payDeal);

				json.setSuccess(true);
				request.setAttribute("json", json);

				return "payState";

			} else {// 线上支付

				// 总金额小于2000不减5元
				// 如果该用户今天没有线上支付并且订单金额大于2000元的订单那么就执行立减5元的代码
				String memberId = sessionInfo.getUserId();
				/*if (totalCost.compareTo(BigDecimal.valueOf(2000)) > 0 && !(remark == null ? false : remark.contains("立减5元")) && orderService.updatePriceByMid(memberId) == 0) {

					// 下面为立减5元的代码
					logger.info("线上支付立减五元。订单号:" + order.getOrderNum());
					totalCost = totalCost.subtract(BigDecimal.valueOf(5));
					if (totalCost.compareTo(BigDecimal.ZERO) < 0) {
						totalCost = BigDecimal.ZERO;
					}
					order.setTotalCost(totalCost);
					order.setRemark("线上支付立减5元");

				}*/
				
				//检查订单是否活动商品如果是，那么就添加备注活动商品
				

				// 如果钱包编号不为空，那么就不再调用钱包这部分功能
				if (null == order.getWalletPayNo() || "".equals(order.getWalletPayNo().trim())) {
					json = balancePay();// 调用钱包支付
					if (json.getSuccess()) {
						if (json.getObj() != null && json.getObj().equals("payState")) {
							json.setMsg("您的订单已经成功支付！");
							json.setSuccess(true);
							request.setAttribute("json", json);
							return json.getObj().toString();// 跳转到支付成功页面
						}
					} else {
						// json.setMsg("支付出现异常！");
						// json.setSuccess(false);
						request.setAttribute("json", json);
						return "payState";
					}
				}
				yeePayPojo = PayUtil.getPayPOJO(order, frpId);
				return "reqPay";
			}

		} catch (Exception e) {
			logger.error("前往易宝支付界面出现异常:" + e);
			e.printStackTrace();
			json.setMsg("支付出现异常！");
			json.setSuccess(false);
			request.setAttribute("json", json);
			return "payState";
		}

	}

	/**
	 * 修改订单价格
	 * 
	 * @Title: updatePrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	private void updatePrice() {

		// 修改订单价格
		orderService.updatePrice(order.getId(), order.getTotalCost(), order.getRemark(), order.getWalletNum(), order.getActualPayNum(), order.getWalletPayNo());
	}

	/**
	 * 易宝支付成功返回接受的Action
	 * 
	 * @Title: YEEPayReturnAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String doNotNeedSession_YEEPayReturn() {
		Json json = new Json();
		try {
			boolean isOK = false;
			// 校验返回数据包
			/*
			 * System.out.println("yeePay返回的通知:yeePayPojo.getHmac():" +
			 * yeePayPojo.getHmac() + ";" +
			 * "Configuration.getInstance().getValue(\"p1_MerId\"):" +
			 * Configuration.getInstance().getValue("p1_MerId") + ";" +
			 * "yeePayPojo.getR0_Cmd():" + yeePayPojo.getR0_Cmd() + ";" +
			 * "yeePayPojo.getR1_Code():" + yeePayPojo.getR1_Code() + ";" +
			 * "yeePayPojo.getR2_TrxId():" + yeePayPojo.getR2_TrxId() + ";" +
			 * "yeePayPojo.getR3_Amt():" + yeePayPojo.getR3_Amt() + ";" +
			 * "yeePayPojo.getR4_Cur():" + yeePayPojo.getR4_Cur() + ";" +
			 * "yeePayPojo.getR5_Pid():" + yeePayPojo.getR5_Pid() + ";" +
			 * "yeePayPojo.getR6_Order():" + yeePayPojo.getR6_Order() + ";" +
			 * "yeePayPojo.getR7_Uid():" + yeePayPojo.getR7_Uid() + ";" +
			 * "yeePayPojo.getR8_MP():" + yeePayPojo.getR8_MP() + ";" +
			 * "yeePayPojo.getR9_BType():" + yeePayPojo.getR9_BType() + ";" +
			 * "Configuration.getInstance().getValue(\"keyValue\"):" +
			 * Configuration.getInstance().getValue("keyValue") + ";"
			 * 
			 * );
			 */
			
			isOK = PaymentForOnlineService.verifyCallback(yeePayPojo.getHmac(), Configuration.getInstance().getValue("p1_MerId"), yeePayPojo.getR0_Cmd(), yeePayPojo.getR1_Code(),
					yeePayPojo.getR2_TrxId(), yeePayPojo.getR3_Amt(), yeePayPojo.getR4_Cur(), "三际手机批发支付", yeePayPojo.getR6_Order(), yeePayPojo.getR7_Uid(), yeePayPojo.getR8_MP(),
					yeePayPojo.getR9_BType(), Configuration.getInstance().getValue("keyValue"));
			if (isOK) {
				// 在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
				if (yeePayPojo.getR1_Code().equals("1")) {
					// 产品通用接口支付成功返回-浏览器重定向
					if (yeePayPojo.getR9_BType().equals("1")) {
						// System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
						// 产品通用接口支付成功返回-服务器点对点通讯
					} else if (yeePayPojo.getR9_BType().equals("2")) {
						// 如果在发起交易请求时 设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
						// System.out.println("SUCCESS");
						// 产品通用接口支付成功返回-电话支付返回
					}
					order = orderService.gainOrderALLByID(yeePayPojo.getR6_Order());
					// 下面页面输出是测试时观察结果使用
					// System.out.println("<br>交易成功!<br>商家订单号:" +
					// yeePayPojo.getR6_Order() + "<br>支付金额:" +
					// yeePayPojo.getR3_Amt() + "<br>易宝支付交易流水号:" +
					// yeePayPojo.getR2_TrxId());
					PayDeal oldPay = payService.gainDealByDeal(yeePayPojo.getR2_TrxId(), "yeePay");
					if (null == oldPay) {
						PayDeal payDeal = new PayDeal();
						payDeal.setId(ToolsUtil.getUUID());
						payDeal.setOrderId(yeePayPojo.getR6_Order());
						payDeal.setOrderAmount(order.getTotalCost());
						payDeal.setDealFee(new BigDecimal(yeePayPojo.getR3_Amt()));
						payDeal.setDealSigunre(yeePayPojo.getHmac());
						payDeal.setDealState(yeePayPojo.getR1_Code());
						payDeal.setDealId(yeePayPojo.getR2_TrxId());
						payDeal.setDealType("yeePay");
						payDeal.setPayType("gateway");
						payDeal.setCreateTime(DateUtil.noformatStringToDate(yeePayPojo.getRp_PayDate(), "yyyy-MM-dd hh:mm:ss"));
						payService.insetPayDealStock(payDeal);
						// 如果支付密码不为空//线上支付
						/*
						 * if (getPayPwd() != null && !"".equals(getPayPwd())) {
						 * SessionInfo sessionInfo = (SessionInfo)
						 * this.session.get(ResourceUtil.getSessionInfoName());
						 * 
						 * // 调用添加钱包交易，返回流水号 walletPayNo =
						 * qbService.addPay("ORDER_PAY_ONLINE",
						 * order.getWalletNum(), sessionInfo.getLoginName(),
						 * getPayPwd()); if (walletPayNo != null) {
						 * order.setWalletPayNo(walletPayNo); } // 修改订单余额支付流水号
						 * Order o = new Order(); o.setId(order.getId());
						 * o.setWalletPayNo(walletPayNo);
						 * orderService.updateByPrimaryKeySelective(o); }
						 */

						// 易宝支付成功，那么就修改该笔钱包交易的状态为成功
						if(order.getWalletPayNo()!=null&&!"".equals(order.getWalletPayNo())){
							updateState(order.getWalletPayNo());
						}
						
						sendMsgErpPoint(yeePayPojo.getR2_TrxId() + order.getWalletPayNo(), yeePayPojo.getR3_Amt(), yeePayPojo.getRp_PayDate(), "0");// 发送短信，推动到管易，增减积分

					} else {
						// System.out.println("该交易流水号已经处理!");
					}
					json.setMsg("您的订单已经支付！");
					json.setSuccess(true);
				}
			} else {
				// System.out.println("交易签名被篡改!");
				MsgUtil.MsgSenderAdminByError("交易(付款)签名被篡改! 商家订单号:" + yeePayPojo.getR6_Order() + " 易宝支付交易流水号:" + yeePayPojo.getR2_TrxId());
				json.setMsg("您的订单支付失败！代码：01125");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			MsgUtil.MsgSenderAdminByError("订单出现异常！订单ID：" + yeePayPojo.getR6_Order() + ";易宝支付交易流水号:" + yeePayPojo.getR2_TrxId());
			json.setMsg("非常抱歉，您的订单支出现异常！请联系我们的客服！");
			json.setSuccess(false);
		}

		request.setAttribute("json", json);
		return "payState";
	}
	/**
	 * 模拟银行回调
	 *
	 * @Title: YEEPayReturnAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String modelBankReturn() {
		Json json = new Json();
		try {
			boolean isOK = true;
			/**
			 * 需要的参数
			 * R1_Code：1
			 * R6_Order：
			 * R2_TrxId
			 * R3_Amt
			 * Hmac
			 */
			if (isOK) {
				// 在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
				if (yeePayPojo.getR1_Code().equals("1")) {
					// 产品通用接口支付成功返回-浏览器重定向
					if (yeePayPojo.getR9_BType().equals("1")) {
						// System.out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
						// 产品通用接口支付成功返回-服务器点对点通讯
					} else if (yeePayPojo.getR9_BType().equals("2")) {
						// 如果在发起交易请求时 设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
						// System.out.println("SUCCESS");
						// 产品通用接口支付成功返回-电话支付返回
					}
					order = orderService.gainOrderALLByID(yeePayPojo.getR6_Order());
					PayDeal oldPay = payService.gainDealByDeal(yeePayPojo.getR2_TrxId(), "yeePay");
					if (null == oldPay) {
						PayDeal payDeal = new PayDeal();
						payDeal.setId(ToolsUtil.getUUID());
						payDeal.setOrderId(yeePayPojo.getR6_Order());
						payDeal.setOrderAmount(order.getTotalCost());
						payDeal.setDealFee(new BigDecimal(yeePayPojo.getR3_Amt()));
						payDeal.setDealSigunre(yeePayPojo.getHmac());
						payDeal.setDealState(yeePayPojo.getR1_Code());
						payDeal.setDealId(yeePayPojo.getR2_TrxId());
						payDeal.setDealType("yeePay");
						payDeal.setPayType("gateway");
						payDeal.setCreateTime(DateUtil.noformatStringToDate(yeePayPojo.getRp_PayDate(), "yyyy-MM-dd hh:mm:ss"));
						payService.insetPayDealStock(payDeal);
						// 易宝支付成功，那么就修改该笔钱包交易的状态为成功
						if(order.getWalletPayNo()!=null&&!"".equals(order.getWalletPayNo())){
							updateState(order.getWalletPayNo());
						}

						sendMsgErpPoint(yeePayPojo.getR2_TrxId() + order.getWalletPayNo(), yeePayPojo.getR3_Amt(), yeePayPojo.getRp_PayDate(), "0");// 发送短信，推动到管易，增减积分

					} else {
						// System.out.println("该交易流水号已经处理!");
					}
					json.setMsg("您的订单已经支付！");
					json.setSuccess(true);
				}
			} else {
				// System.out.println("交易签名被篡改!");
				MsgUtil.MsgSenderAdminByError("交易(付款)签名被篡改! 商家订单号:" + yeePayPojo.getR6_Order() + " 易宝支付交易流水号:" + yeePayPojo.getR2_TrxId());
				json.setMsg("您的订单支付失败！代码：01125");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			MsgUtil.MsgSenderAdminByError("订单出现异常！订单ID：" + yeePayPojo.getR6_Order() + ";易宝支付交易流水号:" + yeePayPojo.getR2_TrxId());
			json.setMsg("非常抱歉，您的订单支出现异常！请联系我们的客服！");
			json.setSuccess(false);
		}

		request.setAttribute("json", json);
		return "payState";
	}


	public void updateState(String qbid) {
		try {

			String url = QbServiceImpl.BASE_URL + qbid + "/status";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("status", "SUCCESS");
			// System.out.println(url + "        " + param);
			restTemplate.put(url, param);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送短信，推动到管易，增减积分
	 * 
	 * @Title: sendMsgErpPoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param payId 支付序列号
	 * @param @param payNum 支付金额
	 * @param @param payTime 支付时间
	 * @param @param isCod 是否货到付款，0-非货到付款,1-货到付款
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	private void sendMsgErpPoint(String payId, String payNum, String payTime, String isCod) {
		Members m = memberService.gainMembersDetailById(order.getMemberId());
		adminService.msgInfoAdminByRegionsAndType(order.getProvince(), order.getCity(), order.getArea(), "2", null, order);
		if (!"4dec69e6af0c4346beac65c6f332aa5d".equals(m.getId())) {// 过滤掉测试账号，测试账号不推送到管易
			Admin admin = adminService.getAdminById(m.getAdminId());

			Map<String, Object> map = EcErpUtil.OrderAddNew(order, m.getUsername(), payId, "017", payNum, payTime, isCod,admin!=null?admin.getTruename():"没有对应业务名称");

			if (null != map.get("tid") && !"".equals(map.get("tid"))) {
				order.setEcerpNo(map.get("tid") + "");
				order.setEcerpCreated(map.get("created") + "");
				//服务站经理存在就将此订单向APP推送
				if(admin != null && !"".equals(admin)){
					String mobile2 = admin.getMobilephone();
					MsgUtil.MsgInfoXDApp(mobile2, order, m);
				}
			}
			order.setEcerpError(map.get("ERROR") + "");
			orderService.updateEcerpById(order);
			MsgUtil.MsgPaySuccess(order.getShipTel(), order.getOrderNum());

			// 判断钱包流水号是否存在如果存在那么就调用// 从这里调用修改钱包记录
			if (order.getWalletPayNo() != null && !"".equals(order.getWalletPayNo())) {
				qbService.qbAddEcerpNo(order.getWalletPayNo(), order.getEcerpNo());
			}
		}

		try {

			// 增减积分
			List<OrderItems> orderItemss = order.getOrderItemss();
			reducePoint(orderItemss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MsgUtil.testSuccess("15610696299", "积分异常" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 钱包支付
	 * 
	 * @Title: balancePay
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Json balancePay() {
		Json j = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			boolean isAllBalance = false;//是否全部钱吧支付

			//判断是否使用了红包
			if(order.getHbNo()!=null&&null!=order.getHbNum()){
				//如果使用了红包那么就在此处临时把总价格改为减去红包后的价格方便后边计算实际支付的价格
				order.setTotalCost(order.getTotalCost().subtract(order.getHbNum()));
			}
			
			
			if (getPayPwd() != null && !"".equals(getPayPwd().trim())&&request.getSession().getAttribute("balance")!=null) {// 如果支付密码不为空那么就是用钱包支付
				
				BigDecimal balance = new BigDecimal(request.getSession().getAttribute("balance").toString());//从session里面获取用户余额
				if (getPayPwd().trim().length() < 8) {
					j.setMsg("支付密码不正确");
					j.setSuccess(false);
					return j;
				}

				if (balance.doubleValue() >= order.getTotalCost().doubleValue()) {// 如果钱包余额大于等于需要支付的价格，那么就是全部钱包支付
					order.setWalletNum(order.getTotalCost());// 钱包支付价格
					isAllBalance = true;//全部钱包支付
				} else {// 否则就是支付钱包所有余额
					order.setWalletNum(balance);
				}

				// request.getSession().setAttribute("balance",
				// balance.subtract(order.getTotalCost()));//
				// 这里要修改，判断是否订单总金额大于钱包余额
				// 支付类型 ：默认钱包支付
				String tradeType = "ORDER_PAY_ALL";
				if (order.getTotalCost().doubleValue() > balance.doubleValue()) {// 总价格大于钱包价格
					BigDecimal actualPayNum = order.getTotalCost().subtract(balance);
					order.setActualPayNum(actualPayNum);// 实际支付价格
					if ("SJ-HDFK".equals(frpId)) {// 货到付款
						tradeType = "ORDER_PAY_POS";// 钱包和pos混合支付
					} else {
						tradeType = "ORDER_PAY_ONLINE";// 线上支付混合支付
					}
				} else {
					order.setActualPayNum(BigDecimal.ZERO);// 实际支付价格
				}
				// 线上支付混合支付等到回调的时候改变支付状态。。。
				j = qbService.addPay(tradeType, order.getWalletNum(), sessionInfo.getLoginName(), getPayPwd(), "http://" + request.getServerName() + "/order/detail.html?id="
						+ order.getId(), order.getOrderNum());
				if (j.getSuccess()) {//成功
					order.setWalletPayNo(j.getMsg());
				}else {
					return j;//钱包支付调用异常
				}
				// }
			} else {
				//TODO 判断当前订单里面钱包流水号不为空，并且此次请求是pos支付。那么就不要再改价格相关东西
				if(order.getWalletPayNo()!=null&&!"".equals(order.getWalletPayNo())&&"SJ-HDFK".equals(frpId)){
					//修改钱包交易备注为，货到付款和钱包混合
					qbService.editDescription(order.getWalletPayNo(), "钱包和POS混合支付");
				}else{
					order.setActualPayNum(order.getTotalCost());// 实际支付价格
					order.setWalletNum(BigDecimal.ZERO);// 钱包支付价格
				}
				j.setSuccess(true);
			}

			//如果使用了红包那么修改价格之前要把之前临时减去的加回来//保证存储在数据库的总价格不变
			if(order.getHbNo()!=null&&null!=order.getHbNum()){
				order.setTotalCost(order.getTotalCost().add(order.getHbNum()));
			}
			
			updatePrice();
			
			// 全部余额支付并且，线上支付//直接付款改订单状态跳转到付款成功页面
			if (isAllBalance && !"SJ-HDFK".equals(frpId)) {// 是全额钱包支付并且支付价格不为零
				// 判断支付是否成功
				if (j.getSuccess()) {
					// 修改订单状态
					Order o = new Order();
					o.setId(order.getId());
					o.setMemberId(order.getMemberId());
					o.setPayStatus("1");
					orderService.updateByPrimaryKeySelective(o);
					// 推送到管易
					sendMsgErpPoint(order.getWalletPayNo(), order.getWalletNum().toString(), new Date().toString(), "0");// 发送短信，推动到管易，增减积分
					j.setObj("payState");
				}
				// return "payState";
			}

		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.testSuccess("18453170418", "钱包支付异常：" + e.getMessage());
			j.setMsg("系统异常请稍后重试");
			j.setSuccess(false);
		}
		return j;
	}

	/**
	 * 增减用户积分
	 * 
	 * @Title: reducePoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderItemss
	 *            订单详情
	 * @return void 返回类型
	 * @author peter
	 * @throws
	 */
	public void reducePoint(List<OrderItems> orderItemss) {
		int sumJF = 0;
		// 判断该订单是否有积分商品
		boolean isPoint = false;

		for (OrderItems oi : orderItemss) {

			if (oi.getTargetType().equals("point")) {
				int num = oi.getNums().intValue();
				int integral = oi.getPointGoods().getIntegral().intValue();
				int sum = num * integral;
				sumJF += sum;
				isPoint = true;
			}
			try {
				// 增加用户积分
				editPoint(oi.getDealPrice(), order.getMemberId());
				// System.out.println("价格：" + oi.getDealPrice());
				isPoint = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MsgUtil.testSuccess("15610696299", "加积分异常" + oi.getDealPrice().toString() + e.getMessage());
				e.printStackTrace();
			}
		}

		// isPoint
		if (isPoint) {
			// 扣除用户对应的积分
			String mid = order.getMemberId();
			Members members = memberService.getMemberById(mid);
			int point = members.getPoint().intValue();
			int npoint = point - sumJF;
			// System.out.println(npoint);
			members.setPoint(new BigDecimal(Integer.toString(npoint)));
			memberService.updateByPrimaryKeySelective(members);
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

	public void doNotNeedSession_toEcp() {
		Order or = orderService.gainOrderALLByID("4ba95c79f391454294d440109e82a5a7");
		Map<String, Object> map = EcErpUtil.OrderAddNew(or, "济阳县孙耿镇星期六手机卖场", "", "", "", "20150209153010005", "1","");
		if (null != map.get("tid") && !"".equals(map.get("tid"))) {
			or.setEcerpNo(map.get("tid") + "");
			or.setEcerpCreated(map.get("created") + "");
		}
		orderService.updateEcerpById(or);
	}

	/**
	 * 申请退款
	 * 
	 * @Title: reFundByOrderId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public void doNotNeedSession_reFundByOrderId() {
		Json json = new Json();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", orderId);
		List<Order> orders = orderService.gainDetail(param);
		if (null != orders && orders.size() > 0) {
			order = orders.get(0);
			Double comparison = null;
			try {
				comparison = Double.parseDouble(order.getTotalCost() + "") - Double.parseDouble(reFundAmt);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (comparison >= 0.0) {
				if ("yeePay".equals(order.getDealType())) {// 易宝支付
					yeePayPojo = PayUtil.getReFundPojo(order, reFuntdId, reFundAmt, explain);
					Map<String, String> params = new HashMap<String, String>();
					params.put("p0_Cmd", yeePayPojo.getP0_Cmd());
					params.put("p1_MerId", yeePayPojo.getP1_MerId());
					params.put("pb_TrxId", yeePayPojo.getPb_TrxId());
					params.put("p3_Amt", yeePayPojo.getP3_Amt());
					params.put("p4_Cur", yeePayPojo.getP4_Cur());
					params.put("p5_Desc", yeePayPojo.getP5_Desc());
					params.put("hmac", yeePayPojo.getHmac());
					String xml = HttpClientUtils.sendPostSSLRequest(ResourceUtil.getYEEPayRefundURL(), params);
					PayRefund payRefund = PayUtil.getRefundReturn(xml, "gateway", "yeePay", orderId);
					payService.insetPayRefund(payRefund);
					if ("1".equals(payRefund.getR1Code())) {
						json.setMsg("退款成功！");
						json.setSuccess(true);
					} else {
						json.setMsg("退款失败！错误代码：" + payRefund.getR1Code() + "-" + payRefund.getExceptionMsg());
						json.setSuccess(false);
					}
				} else {
					String erMsg = order.getDealType() == null ? "null" : order.getDealType();
					json.setMsg("支付方式异常！支付代码：" + erMsg);
					json.setSuccess(false);
				}
			} else {
				json.setMsg("退款金额不能大于订单金额！");
				json.setSuccess(false);
			}
		} else {
			json.setMsg("订单不存在！");
			json.setSuccess(false);
		}

		super.writeJson(json);

	}

	/*
	 * (非 Javadoc) <p>Title: getModel</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */

	public YEEPayPojo getModel() {
		// TODO Auto-generated method stub
		return yeePayPojo;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the yeePayPojo
	 */
	public YEEPayPojo getYeePayPojo() {
		return yeePayPojo;
	}

	/**
	 * @param yeePayPojo
	 *            the yeePayPojo to set
	 */
	public void setYeePayPojo(YEEPayPojo yeePayPojo) {
		this.yeePayPojo = yeePayPojo;
	}

	/**
	 * @return the frpId
	 */
	public String getFrpId() {
		return frpId;
	}

	/**
	 * @param frpId
	 *            the frpId to set
	 */
	public void setFrpId(String frpId) {
		this.frpId = frpId;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the reFundAmt
	 */
	public String getReFundAmt() {
		return reFundAmt;
	}

	/**
	 * @param reFundAmt
	 *            the reFundAmt to set
	 */
	public void setReFundAmt(String reFundAmt) {
		this.reFundAmt = reFundAmt;
	}

	/**
	 * @return the reFuntdId
	 */
	public String getReFuntdId() {
		return reFuntdId;
	}

	/**
	 * @param reFuntdId
	 *            the reFuntdId to set
	 */
	public void setReFuntdId(String reFuntdId) {
		this.reFuntdId = reFuntdId;
	}

	/**
	 * @return the explain
	 */
	public String getExplain() {
		return explain;
	}

	/**
	 * @param explain
	 *            the explain to set
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * @return the provinces
	 */
	public List<Regions> getProvinces() {
		return provinces;
	}

	/**
	 * @param provinces
	 *            the provinces to set
	 */
	public void setProvinces(List<Regions> provinces) {
		this.provinces = provinces;
	}

	/**
	 * @return the cityList
	 */
	public List<Regions> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList
	 *            the cityList to set
	 */
	public void setCityList(List<Regions> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @return the areaList
	 */
	public List<Regions> getAreaList() {
		return areaList;
	}

	/**
	 * @param areaList
	 *            the areaList to set
	 */
	public void setAreaList(List<Regions> areaList) {
		this.areaList = areaList;
	}

	/**
	 * @return the userShipAdd
	 */
	public String getUserShipAdd() {
		return userShipAdd;
	}

	/**
	 * @param userShipAdd
	 *            the userShipAdd to set
	 */
	public void setUserShipAdd(String userShipAdd) {
		this.userShipAdd = userShipAdd;
	}

	public static void main(String[] args) {
		// Map<String, String> p = new HashMap<String, String>();
		// p.put("orderId", "82febd27b44547ae8555ee07a1ae6d9e");
		// p.put("reFundAmt", "496.20");
		// String xml =
		// HttpClientUtils.sendPostSSLRequest("http://www.3j1688.com/yeePay/toRefund.html",
		// p);
		// // System.out.println(xml);
		String xml = "r0_Cmd=RefundOrd\nr1_Code=10\nr2_TrxId=\nr4_Order=\nr3_Amt=\nrf_fee=\nr4_Cur=\nhmac=5493710a9639655f8f8ffe0c198625e0\n";
		// String[] xmls = xml.split("\n");
		// for (int i = 0; i < xmls.length; i++) {
		// // System.out.println(">>>>>"+i+"<<<<<<"+xmls[i]);
		// String[] str = xmls[i].split("=");
		// // System.out.println(">>0:"+str[0]);
		// if (str.length>1) {
		// // System.out.println(">>1:"+str[1]);
		// }else {
		// // System.out.println(">>1:空值");
		// }
		//
		// // // System.out.println(i+":"+xmls[i]);
		// }

		// // System.out.println(xml.substring(xml.lastIndexOf(ch,
		// fromIndex)("r0_Cmd=")+1, xml.indexOf("\n")));

		// System.out.println(xml);
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

}
