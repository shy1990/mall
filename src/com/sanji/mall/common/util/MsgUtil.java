/**
 * @Title: MsgUtil.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao
 * @date 2014-10-8 下午2:56:34
 * @version V1.0
 */
package com.sanji.mall.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bcloud.msg.http.HttpSender;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 短信方法类
 * @author ZhouZhangbao
 */
public class MsgUtil {
  private static String uri = "http://222.73.117.158/msg/";// 应用地址
  private static String account = "dianshang";// 账号
  private static String pswd = "Tch123456";// 密码
  private static boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
  // private static String product = "229611062";// 产品ID
  private static String product = "281249147";// 产品ID
  private static String extno = "168";// 扩展码
  private static String MSG_Signature = "";// 短信签名

  private static String MSGCODE_START = "您的验证码:";
  private static String MSGCODE_END = ",请在一分钟之内完成操作，请勿告诉别人!";
  private static String PAY_SUCCESS_START = "客官您好，您订单号为："; // 支付成功头部
  private static String PAY_SUCCESS_END = "的订单，已完成支付，我们会尽快安排发货，请耐心等待";// 支付成功尾部
  private static String HDFK_SUCCESS_START = "客官您好，您订单号为："; // 货到付款成功头部
  private static String HDFK_SUCCESS_END = "的订单，您选择的为货到付款！，我们会尽快安排发货，请耐心等待";// 货到付款成功尾部
  private static String REGISTER_SUCCESS = "客官您好，您已成功注册三际手机采购网，并获得5000积分，请牢记您的账号和密码。赶快登陆www.3j1688.com，享受一站式采购的极致体验吧！";// 注册成功
  private static String BINDING_SUCCESS_START = "客官您好，您的绑定手机已成功修改为：";// 绑定手机头部
  private static String BINDING_SUCCESS_END = "，请牢记。如有疑问，请致电0531-67860013";// 绑定手机尾部
  private static String SUBMITORDER_SUCCESS_START = "客官您好，您已成功提交订单，订单号为：";// 提交订单头部
  private static String SUBMITORDER_SUCCESS_END = "，您拍下的宝贝数量有限，请抓紧付款";// 提交订单尾部
  // ----------已发货
  private static String FHCG_SUCCESS_START = "客官您好，您采购的宝贝已发货，运单号为：";
  private static String FHCG_SUCCESS_END = "，请注意查收";
  // ---------已签收
  private static String SIGN_SUCCESS = "客官您好，您采购的宝贝已被签收，请确定为本人签收！如有疑问，请致电0531-67860013";
  // ---------申请售后
  private static String SJ_SQSH = "客官您好，我们已收到您的售后申请，售后人员会第一时间联系您，请耐心等到。如有疑问，请致电0531-67860013";
  // ---------货物发回
  private static String SJ_HWFH = "客官您好，您的售后商品已寄回，我们会尽快处理，请耐心等待。如有疑问，请致电0531-67860013";
  // ---------退款信息
  // ---------到货提醒
  private static String SJ_DHTX_START = "客官您好，之前您关注的商品:";
  private static String SJ_DHTX_END = "现已到货，赶紧去采购吧。";
  // 注册通知管理员信息
  private static String SJ_INFO_ADMIN_ZC_1 = "领导您好，";
  private static String SJ_INFO_ADMIN_ZC_2 = "于";
  private static String SJ_INFO_ADMIN_ZC_3 = "注册成功！";
  // 下单通知管理员信息
  private static String SJ_INFO_ADMIN_XD = "";
  // 向注册用户群发信息
  private static String SJ_DXQF_MEMBER_content = "尊敬的手机零售商店主：您已开通三际手机采购网会员权限，网址为WWW.3J1688.COM，可足不出户尊享全品类一站式手机采购服务，天天低价，限时送达，货到付款，两年延保，30天退换，当地三际服务站为您提供快速响应的无忧售后服务。您的登录名即为您的手机号码，默认密码为123456.请您务必及时登录及时修改密码以确保您的账户安全。如有问题请咨询当地移动公司渠道经理或三际服务站服务人员，也可拨打电话：400-937-1688。三际手机采购网竭诚为您服务。";

  private static Logger logger = Logger.getLogger(MsgUtil.class);
  
  private static String APPLIED_REGISTER_SUCCESS = "申请注册";// 申请注册成功

  public static void MsgSenderMembers(String mobile) {
    try {
      String content = MSG_Signature + SJ_DXQF_MEMBER_content;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 验证码发送接口
   * @param @param msg
   * @param @return 设定文件
   * @return MessageRecord 返回类型
   */
  public static MessageRecord MsgSender(MessageRecord msg) {
    String code = ToolsUtil.getRandomNumber(6);
    String content = MSG_Signature + MSGCODE_START + code + MSGCODE_END;
    try {
      // String returnString=sendMessage1(msg.getMobiles(), content,
      // "SMS");
      String returnString = HttpSender.send(uri, account, pswd, msg.getMobiles(), content, needstatus, product, extno);
      String[] str1 = returnString.split("\n");
      String[] str2 = str1[0].split(",");
      msg.setContent(content);
      msg.setResptime(str2[0]);
      msg.setResptatus(str2[1]);
      msg.setMsgid(str1[1]);
      msg.setCode(code);
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
    return msg;
  }

  public static MessageRecord MsgSenderCode(MessageRecord msg) {
    String code = ToolsUtil.getRandomNumber(6);
    String content = MSG_Signature + MSGCODE_START + code + MSGCODE_END;
    try {
      sendMessage(msg.getMobiles(), content, "SMS");
      msg.setContent(content);
      // msg.setResptime(str2[0]);
      // msg.setResptatus(str2[1]);
      // msg.setMsgid(str1[1]);
      msg.setCode(code);
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
    return msg;
  }

  /**
   * 向管理员发送错误代码信息
   * @param @param error 设定文件
   * @return void 返回类型
   */
  public static void MsgSenderAdminByError(String error) {

    try {
      String content = MSG_Signature + error;
      // HttpSender.send(uri, account, pswd,
      // ResourceUtil.getAdminMobile(), content, needstatus, product,
      // extno);
      //sendMessage(ResourceUtil.getAdminMobile(), content, "SMS");
      sendMessage("15965646543", content + error, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 支付通过短信通知接口
   * @param @param mobiles
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgPaySuccess(String mobiles, String orderNo) {
    try {
      String content = MSG_Signature + PAY_SUCCESS_START + orderNo + PAY_SUCCESS_END;
      // // System.out.println(HttpSender.send(uri, account, pswd,
      // mobiles,
      // content, needstatus, product, extno));
      sendMessage(mobiles, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 货到付款短信通知
   * @param @param mobiles
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgHDFKSuccess(String mobiles, String orderNo) {
    try {
      String content = MSG_Signature + HDFK_SUCCESS_START + orderNo + HDFK_SUCCESS_END;
      // HttpSender.send(uri, account, pswd, mobiles, content, needstatus,
      // product, extno);
      sendMessage(mobiles, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 积分通知
   * @param @param mobiles
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */

  public static void testSuccess(String mobiles, String str) {
    try {
      String content = "您好，您已支付成功，积分状态为：" + str;
      sendMessage(mobiles, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 注册成功短信
   * @param @param mobiles
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgRegisterSuccess(String mobiles) {
    try {
      String content = MSG_Signature + REGISTER_SUCCESS;
      // HttpSender.send(uri, account, pswd, mobiles, content,
      // needstatus,product, extno);
      sendMessage(mobiles, content, "SMS");
      // System.out.println(mobiles);
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 绑定手机成功通知
   * @param @param mobiles 设定文件
   * @return void 返回类型
   */
  public static void MsgBindingSuccess(String mobiles) {
    try {
      String content = MSG_Signature + BINDING_SUCCESS_START + mobiles + BINDING_SUCCESS_END;
      // HttpSender.send(uri, account, pswd, mobiles, content, needstatus,
      // product, extno);
      sendMessage(mobiles, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 订单提交成功短信通知
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgSubmitOrderSuccess(String mobile, String orderNo) {
    try {
      String content = MSG_Signature + SUBMITORDER_SUCCESS_START + orderNo + SUBMITORDER_SUCCESS_END;
      // HttpSender.send(uri, account, pswd, mobile, content,
      // needstatus,product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 已发货提醒
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgFHSuccess(String mobile, String orderNo) {
    try {
      String content = MSG_Signature + FHCG_SUCCESS_START + orderNo + FHCG_SUCCESS_END;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 签收提醒
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgQHSuccess(String mobile, String orderNo) {
    try {
      String content = MSG_Signature + SIGN_SUCCESS;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      // sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 申请售后
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgSQSH(String mobile, String orderNo) {
    try {
      String content = MSG_Signature + SJ_SQSH;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 货物发回
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgHWFH(String mobile, String orderNo) {
    try {
      String content = MSG_Signature + SJ_HWFH;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 到货提醒
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgDHTX(String mobile, String goodsName) {
    try {
      String content = MSG_Signature + SJ_DHTX_START + goodsName + SJ_DHTX_END;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  /**
   * 给管理员发送注册成功短信
   * @param @param mobiles 手机号, 多个用逗号间隔如果
   * “15550027177,15254188517,15253194849,18615696354,15275167120”
   * @param @param memberName 会员注册的名
   * @return void 返回类型
   */
  public static void MsgInfoAdminZC(String mobiles, String memberName) {
    try {
      String content = MSG_Signature + SJ_INFO_ADMIN_ZC_1 + memberName + SJ_INFO_ADMIN_ZC_2 + DateUtil.getStringByDate(new Date()) + SJ_INFO_ADMIN_ZC_3;
      // HttpSender.send(uri, account, pswd, mobiles, content, needstatus,
      // product, extno);
      sendMessage(mobiles, content, "SMS");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 会员下单通知管理员
   * @param @param mobiles
   * @param @param order 设定文件
   * @return void 返回类型
   */
  public static void MsgInfoAdminXD(String mobiles, Order order, Members members) {

    if (order != null && order.getOrderNum() != null && !"".equals(order.getOrderNum())) {
      String msg = "会员下单通知:【" + order.getOrderNum() + "】" + order.getPname() + order.getCname() + order.getAname() + members.getUsername() + "下单成功，订单商品:";
      for (OrderItems orderItems : order.getOrderItemss()) {
        String nums = orderItems.getNums() + "";
        String name = "";
        if (null != orderItems.getGoodsSku()) {
          name = orderItems.getGoodsSku().getEdition() == null ? "" : orderItems.getGoodsSku().getEdition() + orderItems.getGoodsSku().getGoods().getName();
          msg += name + "*" + nums + "台,";
        } else if (null != orderItems.getAccessory()) {
          name = orderItems.getAccessory().getName();
          msg += name + "*" + nums + "台,";

        } else if (null != orderItems.getPointGoods()) {
          name = orderItems.getPointGoods().getName();
          msg += name + "*" + nums + "台,";

        }
      }
      try {
        msg = msg.substring(0, msg.length() - 1);
        sendMessage(mobiles, msg, "WX");// 发送微信通知
        // HttpSender.send(uri, account, pswd, mobiles, MSG_Signature +
        // msg, needstatus, product, extno);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 会员下单通知业务(app)
   * @param @param mobiles
   * @param @param order 设定文件
   * @return void 返回类型
   */
  public static void MsgInfoXDApp(String mobiles, Order order, Members members) {
//		int count=(int)(Math.random()*100+1);
    if (order != null && order.getOrderNum() != null && !"".equals(order.getOrderNum())) {
      int nums = 0;
      int accNums = 0;
      for (OrderItems orderItems : order.getOrderItemss()) {
        if (null != orderItems.getGoodsSku()) {
          nums = add(nums, orderItems.getNums().intValue());
        } else if (null != orderItems.getAccessory()) {
          accNums = add(accNums, orderItems.getNums().intValue());
        }
      }
      String skuNum = nums + "";
      String accNum = accNums + "";
      String msg = "{\"orderNum\":\"" + order.getOrderNum() + "\",\"mobiles\":\"" + mobiles + "\",\"skuNum\":\"" + skuNum + "\",\"username\":\""
       + members.getUsername() + "\",\"amount\":\"" + order.getTotalCost() + "\",\"acutalPrice\":\"" + order.getActualPayNum() + "\",\"accNum\":\"" + accNum + "\",\"memberMobile\":\"" + members.getMobile() + "\"}";
      try {
        sendMessage1(msg);
        System.out.println("msg:" + msg);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  //累加
  public static int add(int v1, int v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.add(b2).intValue();
  }

  /**
   * 会员取消订单通知业务(app)
   * @param @param mobiles
   * @param @param orderNum
   * @return void 返回类型
   */
  public static void MsgConcelOrderToApp(String mobiles, Order order, String memberName, String memberMobile) {
    if (order != null && order.getOrderNum() != null && !"".equals(order.getOrderNum())) {
      int nums = 0;
      int accNums = 0;
      for (OrderItems orderItems : order.getOrderItemss()) {
        if (null != orderItems.getGoodsSku()) {
          nums = add(nums, orderItems.getNums().intValue());
        } else if (null != orderItems.getAccessory()) {
          accNums = add(accNums, orderItems.getNums().intValue());
        }
      }
      String skuNum = nums + "";
      String accNum = accNums + "";
      String msg = "{\"orderNum\":\"" + order.getOrderNum() + "\",\"mobiles\":\"" + mobiles + "\",\"username\":\"" + memberName + "\",\"accNum\":\"" + accNum + "\",\"skuNum\":\"" + skuNum + "\",\"memberMobile\":\"" + memberMobile + "\"}";
      try {
        cancelOrder(msg);
        System.out.println("msg:" + msg);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * sendMessage:发送SMS,WX,APP通知. <br/>
   * @since JDK 1.6
   */
  private static void sendMessage(String mobiles, String msg, String type) {
    String[] myMobiles = mobiles.split(",");
    for (String m : myMobiles) {
      Map<String, String> params = new HashMap<String, String>();
      params.put("mobile", m);
      params.put("msg", msg);
      params.put("type", type);
      HttpClientUtils.sendPostRequest("http://weixin.3j168.cn/sendByMobile", params, null, null);
//			 HttpClientUtils.sendPostRequest("http://localhost:8080/sendByMobile",
//			 params, null, null);
    }
  }

  private static void sendMessageToApp(JSONObject obj) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("msg", obj.toJSONString());
    HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/push/pushNewPosPayments",
     params, null, null);
//	          HttpClientUtils.sendPostRequest("http://192.168.2.153:8082/v1/push/pushNewPosPayments",
//	          params, null, null);
  }
  
  public static void sendMessageToApps(JSONObject obj) {
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("msg", obj.toJSONString());
 //	    HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/updateRd",
//	     params, null, null);
		HttpClientUtils.sendPostRequest("http://192.168.2.37:8082/v1/updateRd",
		          params, null, null);
	  }

  private static void sendMessage1(String msg) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("msg", msg);
   // HttpClientUtils.sendPostRequest("http://192.168.2.153:8082/v1/push/pushNewOrder",
   //  params, null, null);
    	String strResult = HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/push/pushNewOrder",
				 params, null, null);
  }
  
  /**
   * 调用业务后台WaterOrder接口
   */
  public static void sendToWaterOrder(String orderNum, BigDecimal actualPayNum,  Date createTime){
	  Map<String, String> params = new HashMap<String, String>();
	  params.put("payDate", createTime.getTime()+"");
	  params.put("payMoney", actualPayNum+"");
	  String strResult = HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/waterOrder/pay/"+orderNum,
				 params, null, null);
	  
  }
  
  

  private static void cancelOrder(String msg) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("msg", msg);
//		String strResult = HttpClientUtils.sendPostRequest("http://192.168.2.146:7777/v1/push/cancelOrder",
//				 params, null, null);
    HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/push/cancelOrder",
     params, null, null);
  }

  /**
   * 签收验证码发送接口
   * @param @param msg
   * @param @return 设定文件
   * @return MessageRecord 返回类型
   */
  public static MessageRecord GoodsReceiveSender(MessageRecord msg) {
    String code = ToolsUtil.getRandomNumber(6);
    String content = MSG_Signature + MSGCODE_START + code;
    try {
      // String returnString = sendMessage1(msg.getMobiles(), content,
      // "SMS");
      String returnString = HttpSender.send(uri, account, pswd, msg.getMobiles(), content, needstatus, product, extno);
      String[] str1 = returnString.split("\n");
      String[] str2 = str1[0].split(",");
      msg.setContent(content);
      msg.setResptime(str2[0]);
      msg.setResptatus(str2[1]);
      msg.setMsgid(str1[1]);
      msg.setCode(code);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return msg;
  }

  /**
   * 订单取消
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgConcelOrder(String orderNum) {
    try {
      // String mobile = "15965646543";
      String mobile = "15965646543";
      String content = MSG_Signature + "订单：" + orderNum + "申请退款退货（取消订单）";
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
  }

  /**
   * 订单取消通知客户
   * @param @param mobile
   * @param @param orderNo 设定文件
   * @return void 返回类型
   */
  public static void MsgConcelOrderToUser(String orderNum, String mobile, String content) {
    try {

      // String mobile = "15965646543";
      // String mobile = "18453170418";
      // String content = MSG_Signature + "订单：" + orderNum +
      // "申请退款退货（取消订单）";
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  public static void PosPaymentsSuc(String orderNo, String mobile) {
    String content = "订单已支付，请及时签收";
    JSONObject obj = new JSONObject();
    obj.put("orderNo", orderNo);
    obj.put("mobile", mobile);
    obj.put("content", content);
    sendMessageToApp(obj);
  }
  /**
   * 支付成功后推送到App
   */
  public static void updateOrderAfterPosPaymentsSuc(String orderNo,String payStatus) {
	    JSONObject obj = new JSONObject();
	    obj.put("orderNo", orderNo);
	    obj.put("payStatus", payStatus);
	    sendMessageToApp(obj);
	  }

  /**
   * 删除订单短信通知
   * @param @param orderNum 设定文件
   * @return void 返回类型
   */
  public static void delOrder(String orderNum, String userName) {
    try {
      String mobile = "15965646543";
      // String mobile = "18453170418";
      String content = MSG_Signature + "用户:" + userName + "，删除订单：" + orderNum;
      // HttpSender.send(uri, account, pswd, mobile, content, needstatus,
      // product, extno);
      sendMessage(mobile, content, "SMS");
    } catch (Exception e) {
      // TODO 处理异常
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // Map<String, String> params = new HashMap<String, String>();
    // params.put("mobile", "18615696354");
    // params.put("msg",
    // "会员下单通知:山东省济南市天桥区豹子的手机店铺下单成功，订单商品:畅玩版Lenovo/联想 A808t-i 黄金斗士A8*1台,");
    // HttpClientUtils.sendPostRequest("http://weixin.3j168.cn/sendByMobile",
    // params, null, null);
    // String returnString=sendMessage1(msg.getMobiles(), content,
    // "SMS");
    try {
      /*
			 * String returnString = HttpSender.send(uri, account, pswd,
			 * "14753170418", "测试返回值", needstatus, product, extno); //
			 * System.out.println(returnString); String returnString2 =
			 * sendMessage1("14753170418", "测试返回值", "SMS"); //
			 * System.out.println("++++++++++++++++++++++"); //
			 * System.out.println(returnString2);
			 */
      MsgConcelOrder("测试收到请回复一下相关人");
      MessageRecord m = new MessageRecord();
      m.setMobiles("18453170418");
      // m.setMobiles("18560132053");
      // System.out.println(JSON.toJSONString(MsgSenderCode(m)));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		/*
		 * try { String mobile = "15165009133"; //HttpSender.send(uri, account,
		 * pswd, mobile, MSG_Signature + "取消订单测试", needstatus, product, extno);
		 * // System.out.println("================"+HttpSender.send(uri,
		 * account, pswd, mobile, MSG_Signature + SJ_DXQF_MEMBER_content,
		 * needstatus, product, extno)); } catch (Exception e) { //
		 * System.out.println("信息发送异常：" + e.getMessage()); }
		 */
  }

  public static void main2(String[] args) {

    // MessageRecord m = new MessageRecord();
    // m.setMobiles("15550027177");
    // MsgSender(m);
    // // System.out.println(DateUtil.getStringByDate(new Date()));
    // MsgPaySuccess("15550027177,15254188517,15253194849,18615696354,15275167120",
    // "20150109182911233");
    try {
			/*
			 * MessageRecord m = new MessageRecord();
			 * m.setMobiles("18453170418"); MsgSender(m);
			 */
      // String msg =
      // "会员下单通知:山东省济南市天桥区豹子的手机店铺下单成功，订单商品:畅玩版Lenovo/联想 A808t-i 黄金斗士A8*1台,";
      // msg = msg.substring(0, msg.length() - 1);
      // HttpSender.send(uri, account, pswd, "15315000222",
      // MSG_Signature+msg, needstatus, product, extno);
      // HttpSender.send(uri, account, pswd, "15865261462", MSG_Signature
      // + msg, needstatus, product, extno);
      // sendMessage("15610696299", MSG_Signature + msg, "APP");
      // // System.out.println(msg);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 向指定 URL 发送POST方法的请求
   * @param url 发送请求的 URL
   * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
   * @return 所代表远程资源的响应结果
   */
  public static String sendPost(String url, String param) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      URLConnection conn = realUrl.openConnection();
      // 设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent",
       "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      conn.setConnectTimeout(5000);
      // 发送POST请求必须设置如下两行
      conn.setDoOutput(true);
      conn.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new PrintWriter(conn.getOutputStream());
      // 发送请求参数
      out.print(param);
      // flush输出流的缓冲
      out.flush();
      // 定义BufferedReader输入流来读取URL的响应
      in = new BufferedReader(
       new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println("发送 POST 请求出现异常！" + e);
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }
/**
 * 
 * @param mobiles 后台管理人员手机号
 * @param shopName 申请注册的手机店店名
 * @param truename 店主的名字
 * @param mobile 店主手机号
 */
public static void MsgAppliedRegisterSuccess(String mobiles,String shopName, String truename, String mobile,String address) {
	 try {
	      String content = MSG_Signature +address+" "+ shopName+" "+truename+" "+APPLIED_REGISTER_SUCCESS+"。  手机号  ："+mobile;
	      sendMessage(mobiles, content, "SMS");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
}

public static boolean CheckOrderFromBuzmgt(String orderNum) {
	System.out.println("=============判断app端订单是否存在");
	 Map<String, String> params = new HashMap<String, String>();
	    params.put("orderNum", orderNum);
			/*String strResult = HttpClientUtils.sendPostRequest("http://192.168.2.153:8082/v1/remind/checkOrder",
					 params, null, null);*/
	   String strResult =  HttpClientUtils.sendPostRequest("http://115.28.87.182:28503/v1/remind/checkOrder",
	     params, null, null);
	   if("true".equals(strResult)){
		   return true;
	   }
	return false;
}

}
