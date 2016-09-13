/**  
 * @Title: PayUtil.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-11-24 下午7:45:53
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.util.Date;

import com.sanji.mall.model.Order;
import com.sanji.mall.model.PayRefund;
import com.sanji.mall.pojo.YEEPayPojo;
import com.yeepay.DigestUtil;
import com.yeepay.PaymentForOnlineService;

/**
 * @ClassName: PayUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-11-24 下午7:45:53
 */
public class PayUtil {

	/**
	 * 根据订单、通道码生成支付POJO
	 * 
	 * @Title: getPayPOJO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order
	 * @param @param pd_FrpId
	 * @param @return 设定文件
	 * @return YEEPayPojo 返回类型
	 * @author ZhouZhangbao
	 */
	public static YEEPayPojo getPayPOJO(Order order, String pd_FrpId) {
		YEEPayPojo yeePayPojo = new YEEPayPojo();
		yeePayPojo.setP0_Cmd("Buy");
		yeePayPojo.setP1_MerId(ResourceUtil.getYEEPayMerId()); // 商户编号
		yeePayPojo.setP2_Order(order.getId());
		// yeePayPojo.setP3_Amt(order.getTotalCost()+"");
		yeePayPojo.setP3_Amt(order.getActualPayNum() + "");// 实际支付价格
		yeePayPojo.setP4_Cur("CNY");
		yeePayPojo.setP5_Pid("三际手机批发支付");
		yeePayPojo.setP8_Url(ResourceUtil.getYEEPayUrl());
		yeePayPojo.setPd_FrpId(pd_FrpId);

		yeePayPojo.setHmac(PaymentForOnlineService.getReqMd5HmacForOnlinePayment(formatString(yeePayPojo.getP0_Cmd()), formatString(yeePayPojo.getP1_MerId()),
				formatString(yeePayPojo.getP2_Order()), formatString(yeePayPojo.getP3_Amt()), formatString(yeePayPojo.getP4_Cur()), formatString(yeePayPojo.getP5_Pid()),
				formatString(yeePayPojo.getP6_Pcat()), formatString(yeePayPojo.getP7_Pdesc()), formatString(yeePayPojo.getP8_Url()), formatString(yeePayPojo.getP9_SAF()),
				formatString(yeePayPojo.getPa_MP()), formatString(yeePayPojo.getPd_FrpId()), formatString(yeePayPojo.getPr_NeedResponse()),
				formatString(ResourceUtil.getYEEPayKey())));
		return yeePayPojo;
	};

	/**
	 * 根据订单生产退款信息
	 * 
	 * @Title: getReFundPojo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param order 订单
	 * @param @param refundId 退单ID
	 * @param @param refundAmt 退款金额
	 * @param @param explain 退款说明
	 * @param @return 设定文件
	 * @return YEEPayPojo 返回类型
	 * @author ZhouZhangbao
	 */
	public static YEEPayPojo getReFundPojo(Order order, String refundId, String refundAmt, String explain) {
		YEEPayPojo yeePayPojo = new YEEPayPojo();
		yeePayPojo.setP0_Cmd("RefundOrd");
		yeePayPojo.setP1_MerId(ResourceUtil.getYEEPayMerId());
		yeePayPojo.setP2_Order(refundId);
		yeePayPojo.setPb_TrxId(order.getDealId());
		yeePayPojo.setP3_Amt(refundAmt);
		yeePayPojo.setP4_Cur("CNY");
		yeePayPojo.setP5_Desc(explain);
		// String hmac = DigestUtil.getHmac(new String[]
		// {yeePayPojo.getP0_Cmd(),yeePayPojo.getP1_MerId(),yeePayPojo.getPb_TrxId(),
		// yeePayPojo.getP3_Amt(),yeePayPojo.getP4_Cur(),yeePayPojo.getP5_Desc()},
		// ResourceUtil.getYEEPayKey());
		String aValue = formatString(yeePayPojo.getP0_Cmd()) + formatString(yeePayPojo.getP1_MerId()) + formatString(yeePayPojo.getPb_TrxId())
				+ formatString(yeePayPojo.getP3_Amt()) + formatString(yeePayPojo.getP4_Cur()) + formatString(yeePayPojo.getP5_Desc());
		String hmac = DigestUtil.hmacSign(aValue, ResourceUtil.getYEEPayKey());
		// // System.out.println("原来的HMAC:"+hmac);
		yeePayPojo.setHmac(hmac);
		return yeePayPojo;
	}

	/**
	 * 根据退款接口返回的字符串解析为实体类PayRefund
	 * 
	 * @Title: getRefundReturn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param returnStr 退款接口返回的字符串
	 * @param @param refundType 退款类别 gateway-网关退款，pos-POS退款
	 * @param @param refundProvider 退款商家: yeePay-易宝支付
	 * @param @return 设定文件
	 * @return PayRefund 返回类型
	 * @author ZhouZhangbao
	 */
	public static PayRefund getRefundReturn(String returnStr, String refundType, String refundProvider, String orderId) {
		PayRefund payRefund = new PayRefund();
		payRefund.setId(ToolsUtil.getUUID());
		payRefund.setRefundType(refundType);
		payRefund.setRefundProvider(refundProvider);
		payRefund.setOrderId(orderId);
		String[] returnStrs = returnStr.split("\n");
		for (int i = 0; i < returnStrs.length; i++) {
			String[] str = returnStrs[i].split("=");
			if ("r0_Cmd".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR0Cmd(str[1] + "");
				}
			} else if ("r1_Code".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR1Code(str[1] + "");
					if ("1".equals(str[1] + "")) {
						payRefund.setExceptionMsg("退款成功");
					} else if ("2".equals(str[1] + "")) {
						payRefund.setExceptionMsg("账户状态无效");
					} else if ("7".equals(str[1] + "")) {
						payRefund.setExceptionMsg("该订单不支持退款");
					} else if ("10".equals(str[1] + "")) {
						payRefund.setExceptionMsg("退款金额超限");
					} else if ("18".equals(str[1] + "")) {
						payRefund.setExceptionMsg("余额不足");
					} else if ("50".equals(str[1] + "")) {
						payRefund.setExceptionMsg("订单不存在");
					} else if ("55".equals(str[1] + "")) {
						payRefund.setExceptionMsg("历史退款未开通");
					} else if ("6801".equals(str[1] + "")) {
						payRefund.setExceptionMsg("IP限制");
					} else {
						payRefund.setExceptionMsg("未知的错误！");
					}
				}
			} else if ("r2_TrxId".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR2Trxid(str[1] + "");
				}
			} else if ("r3_Amt".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR3Amt(str[1] + "");
				}
			} else if ("r4_Cur".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR4Cur(str[1] + "");
				}
			} else if ("r5_Order".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setR5Order(str[1] + "");
				}
			} else if ("hmac".equals(str[0])) {
				if (str.length > 1) {
					payRefund.setHmac(str[1] + "");
				}
			}
		}
		String aValue = formatString(payRefund.getR0Cmd()) + formatString(payRefund.getR1Code()) + formatString(payRefund.getR2Trxid()) + formatString(payRefund.getR3Amt())
				+ formatString(payRefund.getR4Cur());
		String hmac = DigestUtil.hmacSign(aValue, ResourceUtil.getYEEPayKey());
		if (hmac.equals(payRefund.getHmac())) {
			payRefund.setIsException("false");// 没有异常
		} else {
			payRefund.setIsException("true");// 有异常
			MsgUtil.MsgSenderAdminByError("交易(退款)签名被篡改! 易宝支付交易流水号:" + payRefund.getR2Trxid());
		}
		payRefund.setCreateTime(new Date());
		return payRefund;
	}

	private static String formatString(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}
}
