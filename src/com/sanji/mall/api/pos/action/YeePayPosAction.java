/**  
 * @Title: SandPayAction.java
 * @Package com.sanji.mall.api.pos.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-12-4 下午3:02:05
 * @version V1.0  
 */
package com.sanji.mall.api.pos.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.common.util.YeePayPosUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pay.service.PayService;
import com.sanji.mall.pojo.YeepayPosPojo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @ClassName: SandPayAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-12-4 下午3:02:05
 */
@Namespace("/pos")
@Action(value = "yeePayPosAction", results = { @Result(name = "succeed", location = "/goods/goods.jsp") })
public class YeePayPosAction extends BaseAction {

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
	private YeepayPosPojo sendPos = new YeepayPosPojo();
	private YeepayPosPojo resultPos = new YeepayPosPojo();
	private YeepayPosPojo sessionHead = new YeepayPosPojo();
	private YeepayPosPojo sessionBody = new YeepayPosPojo();
	private YeepayPosPojo headExtendAtt = new YeepayPosPojo();
	private YeepayPosPojo bodyExtendAtt = new YeepayPosPojo();
	private String result = "";

	@Resource
	private MemberService memberService;

	private Order order = new Order();
	// TODO 积分系数未设置
	private float pointCoefficient = 10;

	/**
	 * POS登录验证
	 * 
	 * @Title: inspectionUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	private YeepayPosPojo inspectionUser(YeepayPosPojo sendPos) {
		try {

			sessionHead = sendPos.getSessionHead();
			Admin admin = adminService.gainAdminByLogin(sendPos.getSessionBody().getEmployee_ID(), sendPos.getSessionBody().getPassword());
			if (null != admin) {
				// head 设置
				sessionHead.setResult_code("2");
				sessionHead.setResult_msg("成功");
				sessionHead.setResp_time(ToolsUtil.formatDateToyyyyMMddHHmmss(new Date()));
				headExtendAtt.setEmployee_ID(admin.getUserNum());
				sessionHead.setExtendAtt(headExtendAtt);
				// body 设置
				bodyExtendAtt.setEmployee_Name(admin.getTruename());
				bodyExtendAtt.setCompany_Tel(admin.getMobilephone());
				bodyExtendAtt.setCompany_Name("三际采购网");
				sessionBody.setExtendAtt(bodyExtendAtt);

			} else {
				sessionHead.setResult_code("10");
				sessionHead.setResult_msg("用户名或密码错误");
			}

			resultPos.setSessionHead(sessionHead);
			resultPos.setSessionBody(sessionBody);
			return resultPos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * POS端查询订单
	 * 
	 * @Title: gainOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sendPos
	 * @param @return 设定文件
	 * @return YeepayPosPojo 返回类型
	 * @author ZhouZhangbao
	 */
	public YeepayPosPojo gainOrder(YeepayPosPojo sendPos) {
		sessionHead = sendPos.getSessionHead();
		Order order = orderService.gainOrderByID(sendPos.getSessionBody().getOrder_No());

		// head 设置
		sessionHead.setResult_code("2");
		sessionHead.setResult_msg("成功接收");
		sessionHead.setResp_time(ToolsUtil.formatDateToyyyyMMddHHmmss(new Date()));
		headExtendAtt.setOrder_No(sendPos.getSessionBody().getOrder_No());
		sessionHead.setExtendAtt(headExtendAtt);

		if (null != order) {
			// 设置body
			bodyExtendAtt.setSignStandard("");
			bodyExtendAtt.setReceiver_Name(order.getShipName());
			bodyExtendAtt.setReceiver_Addr(order.getPname() + order.getCname() + order.getAname() + order.getAddress());
			bodyExtendAtt.setReceiver_Tel(order.getShipTel());
			bodyExtendAtt.setReceiver_OrderNo(order.getOrderNum());
			bodyExtendAtt.setOrder_AMT(order.getTotalCost() + "");
			bodyExtendAtt.setBiz_Name("三际采购网");
			bodyExtendAtt.setAMT(order.getTotalCost() + "");
			bodyExtendAtt.setSub_Station_Name("");
			String statu = "";
			String statu_Msg = "";
			if ("3".equals(order.getShipStatus())) {// 已签收
				statu = "21";
				statu_Msg = "订单已签收";
			} else if ("1".equals(order.getPayStatus())) {
				statu = "22";
				statu_Msg = "已收款，未签收";
			} else if ("0".equals(order.getPayStatus())) {
				statu = "23";
				statu_Msg = "未收款，未签收";
			}
			bodyExtendAtt.setOrder_Status(statu);
			bodyExtendAtt.setOrder_Status_Msg(statu_Msg);

		} else {
			bodyExtendAtt.setOrder_Status("20");
			bodyExtendAtt.setOrder_Status_Msg("查无此单");
		}
		sessionBody.setExtendAtt(bodyExtendAtt);
		resultPos.setSessionHead(sessionHead);
		resultPos.setSessionBody(sessionBody);
		return resultPos;
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
	private YeepayPosPojo payInfo(YeepayPosPojo sendPos) {
		sessionHead = sendPos.getSessionHead();
		sessionHead.setResp_time(ToolsUtil.formatDateToyyyyMMddHHmmss(new Date()));
		headExtendAtt.setOrder_No(sendPos.getSessionBody().getExtendAtt().getOrder_No());

		try {
			if (null != sendPos.getSessionHead().getExtendAtt().getEmployee_ID() && null != sendPos.getSessionBody().getExtendAtt().getOrder_No()
					&& null != sendPos.getSessionBody().getExtendAtt().getYeepay_OrderId()) {// 工号和订单号是否为空
				order = orderService.gainOrderByID(sendPos.getSessionBody().getExtendAtt().getOrder_No());
				if (null != order) {
					Double u = Double.valueOf(order.getTotalCost() + "") - Double.valueOf(sendPos.getSessionBody().getExtendAtt().getAMT() + "");
					if (u == 0.0) {// 比较订单价格是否相同
						PayDeal deal = payService.gainDealByDeal(sendPos.getSessionBody().getExtendAtt().getYeepay_OrderId(), sendPos.getSessionHead().getExtendAtt()
								.getCompany_Code());
						if (null == deal) {
							deal = new PayDeal();
							deal.setId(ToolsUtil.getUUID());
							deal.setCreateTime(new Date());
							deal.setOrderAmount(order.getTotalCost());
							deal.setDealFee(new BigDecimal(sendPos.getSessionBody().getExtendAtt().getAMT()));
							deal.setDealState("SUCCESS");
							deal.setDealSigunre(sendPos.getSessionHead().getHMAC());
							deal.setDealId(sendPos.getSessionBody().getExtendAtt().getYeepay_OrderId());
							deal.setDealType(sendPos.getSessionHead().getExtendAtt().getCompany_Code());
							deal.setPayType("pos");
							deal.setPayee(sendPos.getSessionHead().getExtendAtt().getEmployee_Name());
							deal.setPayeeNo(sendPos.getSessionHead().getExtendAtt().getEmployee_ID());
							deal.setOrderId(order.getId());
							deal.setShipTel(order.getShipTel());
							deal.setOrderNo(order.getOrderNum());
							deal.setBankCardNo(sendPos.getSessionBody().getExtendAtt().getBankCardNo());
							deal.setBankCardName(sendPos.getSessionBody().getExtendAtt().getPay_Type());
							payService.insetPayDeal(deal);

							// 更新支付状态
							order.setPayMent("1");
							orderService.updateEcerpById(order);

							order = orderService.gainOrderALLByID(sendPos.getSessionBody().getExtendAtt().getOrder_No());
							try {
								// 增减积分
								List<OrderItems> orderItemss = order.getOrderItemss();
								addPoint(orderItemss);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								MsgUtil.testSuccess("15610696299", "积分异常" + e.getMessage());
								e.printStackTrace();
							}

							sessionHead.setResult_code("2");
							sessionHead.setResult_msg("接收成功");
						} else {
							sessionHead.setResult_code("3");
							sessionHead.setResult_msg("接收失败;支重复提交，不给于处理");
						}
					} else {
						sessionHead.setResult_code("3");
						sessionHead.setResult_msg("接收失败;支付金额和订单金额不一致，不给于处理");
					}
				} else {
					sessionHead.setResult_code("3");
					sessionHead.setResult_msg("接收失败;无效的运单单号");
				}

			} else {
				sessionHead.setResult_code("3");
				sessionHead.setResult_msg("接收失败;工号、订单号或支付流水号不能为空");
			}

		} catch (NumberFormatException e) {
			log.equals("易宝POS支付通知接口YeePayPosAction出现异常：" + e);
			sessionHead.setResult_code("3");
			sessionHead.setResult_msg("接收失败;未知错误信息");
		}
		sessionHead.setExtendAtt(headExtendAtt);
		resultPos.setSessionHead(sessionHead);
		return resultPos;
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
	 * POS端签收通知
	 * 
	 * @Title: orderSign
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param sendPojo
	 * @param @return 设定文件
	 * @return YeepayPosPojo 返回类型
	 * @author ZhouZhangbao
	 */
	private YeepayPosPojo orderSign(YeepayPosPojo sendPos) {
		sessionHead = sendPos.getSessionHead();
		sessionHead.setResp_time(ToolsUtil.formatDateToyyyyMMddHHmmss(new Date()));
		headExtendAtt.setOrder_No(sendPos.getSessionBody().getExtendAtt().getOrder_No());
		try {
			payService.orderSign(sendPos.getSessionBody().getExtendAtt().getOrder_No(), sendPos.getSessionBody().getExtendAtt().getEmployee_ID(), sendPos.getSessionBody()
					.getExtendAtt().getEmployee_Name(), sendPos.getSessionBody().getExtendAtt().getSign_Tel(), "pos");
			sessionHead.setResult_code("2");
			sessionHead.setResult_msg("接收成功");
		} catch (Exception e) {
			log.equals("易宝POS签收通知接口YeePayPosAction出现异常：" + e);
			sessionHead.setResult_code("3");
			sessionHead.setResult_msg("接收失败;未知错误信息");
		}

		sessionHead.setExtendAtt(headExtendAtt);
		resultPos.setSessionHead(sessionHead);
		return resultPos;
	}

	public static void main(String[] args) {
		// System.out.println(MD5.encrypt("<SessionHead><Version>V1.0</Version><ServiceCode>COD202</ServiceCode><TransactionID>TIMECOD202201503137433274734</TransactionID><SrcSysID>yeepay</SrcSysID><DstSysID>TIME</DstSysID><ReqTime>20150313145222</ReqTime><ExtendAtt><Order_No>20150313121210620</Order_No></ExtendAtt><Result_code>2</Result_code><Result_msg>成功接收</Result_msg><Resp_time>20150313145223</Resp_time></SessionHead><SessionBody><ExtendAtt><SignStandard></SignStandard><Receiver_Name>王吉勇</Receiver_Name><Receiver_Addr>山东省济南市商河县山东省商河县玉皇庙街道办事处璐瑶手机大卖场</Receiver_Addr><Receiver_Tel>13853159057</Receiver_Tel><Order_AMT>275</Order_AMT><Biz_Name>三际采购网</Biz_Name><AMT>275</AMT><Sub_Station_Name></Sub_Station_Name><Order_Status>22</Order_Status><Order_Status_Msg>已收款，未签收</Order_Status_Msg></ExtendAtt></SessionBody>30wJY7ljzJH5f5a69jKSQhE7Vr8r0FG6Rq59y6d08600LbD022v9xP5y8Q47"));
	}

	public void doNotNeedSession_yeePayPosInterface() {
		try {
			ServletInputStream inStream = request.getInputStream();
			// int length = 0;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len = -1;
			byte[] buffer = new byte[1024];
			try {
				while ((len = inStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			String send = new String(outputStream.toByteArray(), "UTF-8");//
			// 易宝发送给商户的报文
			// String send =
			// "<?xml version=\"1.0\" encoding=\"UTF-8\"?><COD-MS><SessionHead><Version>V1.0</Version><ServiceCode>COD203</ServiceCode><TransactionID>SJCOD203201503202255482974</TransactionID><SrcSysID>yeepay</SrcSysID><DstSysID>SJ</DstSysID><ReqTime>20150320145041</ReqTime><ExtendAtt></ExtendAtt><HMAC>11fcfd33696c26fb45f698aa28b0f549</HMAC></SessionHead><SessionBody><ExtendAtt><Employee_ID>1000</Employee_ID><Employee_Name>周张豹</Employee_Name><Company_Code>yeepay</Company_Code><Company_Name>易宝支付</Company_Name><Pay_Type>8</Pay_Type><Order_No>20150103132213032</Order_No><Sign_Self_Flag>1</Sign_Self_Flag><Sign_Name>zhngsan</Sign_Name><Sign_Tel>15555555555</Sign_Tel></ExtendAtt></SessionBody></COD-MS>";
			// System.out.println("易宝发送给商户的报文");
			// System.out.println(send);
			XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
			xstream.autodetectAnnotations(true);
			// 商户 自己的处理逻辑  根据service_code   走不同的业务逻辑
			sendPos = YeePayPosUtil.getYeePayPosPojo(send);
			if (YeePayPosUtil.signatureVerification(send)) {

				if ("COD201".equals(sendPos.getSessionHead().getServiceCode())) {
					resultPos = inspectionUser(sendPos);
				} else if ("COD202".equals(sendPos.getSessionHead().getServiceCode())) {
					resultPos = gainOrder(sendPos);
				} else if ("COD203".equals(sendPos.getSessionHead().getServiceCode())) {
					resultPos = orderSign(sendPos);
				} else if ("COD204".equals(sendPos.getSessionHead().getServiceCode())) {
					resultPos = payInfo(sendPos);
				}
				result = xstream.toXML(resultPos);// 商户返回给易宝的报文

				result = YeePayPosUtil.signatureEncryption(result);
				// System.err.println("加密后");
				// System.err.println(result);
			} else {
				sessionHead = sendPos.getSessionHead();
				sessionBody = sendPos.getSessionBody();
				sessionHead.setResult_code("4");
				sessionHead.setResult_msg("报文验证失败");
				resultPos.setSessionHead(sessionHead);
				resultPos.setSessionBody(sessionBody);
				result = xstream.toXML(resultPos);// 商户返回给易宝的报文
			}
			response.setContentType("text/xml; charset=utf-8");
			response.setCharacterEncoding("utf-8");

			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
