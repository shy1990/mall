package com.sanji.mall.qb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.common.util.Hmac;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.qb.service.QbService;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("qbService")
public class QbServiceImpl implements QbService {
  public static final String BASE_URL = ResourceUtil.get("member_server_url");//钱包服务的地址
  private static final String KEY = "doubi";

  public Json addPay(String tradeType, BigDecimal amount, String userName, String payPwd, String url, String orderNum) {
    Json j = new Json();
    try {

      RestTemplate restTemplate = new RestTemplate();
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("type", tradeType);
      param.put("description", "订单支付");
      param.put("amount", amount.negate());
      // param.put("username", userName);
      param.put("url", url);
      param.put("payPassword", payPwd);
      param.put("orderNum", orderNum);
      System.out.println(JSON.toJSONString(param));

      List messageConverters = new ArrayList();
      messageConverters.add(new SourceHttpMessageConverter());
      messageConverters.add(new FormHttpMessageConverter());
      messageConverters.add(new MappingJacksonHttpMessageConverter());
      restTemplate.setMessageConverters(messageConverters);

      HttpHeaders httpHeaders = new HttpHeaders(); // 设置HTTP请求的请求头信息
      // 设置相应内容，相应内容将被转换为json格式返回
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);

      // 设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数
      HttpEntity<Object> httpEntity = new HttpEntity<Object>(param, httpHeaders);

      ResponseEntity<JSONObject> obj = restTemplate.postForEntity(BASE_URL + "accounts/" + userName + "/tradings/", httpEntity, JSONObject.class);

      JSONObject o = obj.getBody();
      j.setMsg(o.getString("id"));
      j.setSuccess(true);
      System.out.println(JSON.toJSONString(obj));
      System.out.println(JSON.toJSONString(j));

    } catch (Exception e) {
      //MsgUtil.testSuccess("18453170418", "钱包支付异常：" + e.getMessage());
      e.printStackTrace();
      int stateCode = Integer.parseInt(e.getMessage().substring(0, 3));
      switch (stateCode) {
        case 401:
          j.setMsg("支付密码不正确");
          break;
        case 409:
          j.setMsg("余额不足");
          break;
        default:
          j.setMsg("系统异常请稍后重试");
          break;
      }
    }
    return j;
  }

  //次方法作废，调用传惨有问题好像。
  public Json editPay(String transNo, String state, String userName) throws Exception {
    Json j = new Json();
    // try {
    String url = BASE_URL + "/tradings/" + transNo + "/" + state;

    Connection con = Jsoup.connect(url).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
     .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
     .ignoreContentType(true);

    Response rs = con.execute();// 获取响应
    System.out.println("::::::::：" + rs.body());
    JSONObject obj = JSONObject.parseObject(rs.body());
    // j.setSuccess(true);
    /*
		 * } catch (HttpStatusException e) { e.printStackTrace();
		 * System.out.println(e.getStatusCode()); j.setMsg("钱包支付系统异常请稍后重试");
		 * j.setSuccess(false); }
		 */

    return j;
  }

  public Map<String, Object> getUserQbInfo(String userName) {
    Map<String, Object> qb = null;
    // 登陆获取用户cookie
    Map<String, String> cookie = loginQb(userName);
    try {

      String url = BASE_URL + "accounts/";
      // 获取当前用户钱包信息
      Connection con = Jsoup.connect(url).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
       .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
       .ignoreContentType(true);
      Response rs = con.execute();// 获取响应
      System.out.println("用户信息：" + rs.body());
      JSONObject qbInfo = JSONObject.parseObject(rs.body());
      qb = new HashMap<String, Object>();
      qb.put("balance", qbInfo.get("balance"));

    } catch (HttpStatusException e) {
      e.getStatusCode();
      return null;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
      return null;
    }
    return qb;
  }

  /**
   * 模拟登陆
   **/
  public Map<String, String> loginQb(String userName) {
    Map<String, String> cookies = null;
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("username", userName);
    String checkSum = Hmac.hmacMd5Hex(KEY, param);
    String url = BASE_URL + "hmacLogin?username=" + userName + "&hmac=mall:" + checkSum;
    // http://localhost:8081/hmacLogin?username=zhangsan&hmac=mall:7be94f5f53d05bd9efe7ccbdd0d6236a
    // http://localhost:8081/hmacLogin?username=zhangsan&hmac:mall=7be94f5f53d05bd9efe7ccbdd0d6236a
    try {
      Connection con = Jsoup.connect(url).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
       .header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3").header("Connection", " keep-alive")
       .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000);
      Response rs = con.execute();// 获取响应
      System.out.println("登陆钱包：" + rs.body());
      cookies = rs.cookies();
      System.out.println("cookie：" + cookies);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cookies;
  }

  public BigDecimal getBalance(String userName) {
    try {

      String url = BASE_URL + "accounts/" + userName;
      // 获取当前用户钱包信息
      Connection con = Jsoup.connect(url).header("Content-Type", "application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
       .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
       .ignoreContentType(true);
      Response rs = con.execute();// 获取响应
      System.out.println("用户信息：" + rs.body() + "   +++   " + rs.statusCode());
      JSONObject qbInfo = JSONObject.parseObject(rs.body());
      if (qbInfo != null && qbInfo.containsKey("balance")) {
        return new BigDecimal(qbInfo.getString("balance"));
      } else {
        return BigDecimal.ZERO;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return BigDecimal.ZERO;
  }

  public void qbAddEcerpNo(String qbId, String ecerpNo) {
    try {

      String url = BASE_URL + "tradings/" + qbId + "/" + ecerpNo;
      RestTemplate restTemplate = new RestTemplate();

      List messageConverters = new ArrayList();
      messageConverters.add(new SourceHttpMessageConverter());
      messageConverters.add(new FormHttpMessageConverter());
      messageConverters.add(new MappingJacksonHttpMessageConverter());
      restTemplate.setMessageConverters(messageConverters);

      HttpHeaders httpHeaders = new HttpHeaders(); // 设置HTTP请求的请求头信息
      // 设置相应内容，相应内容将被转换为json格式返回
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);

      // 设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数
      // HttpEntity<Object> httpEntity = new HttpEntity<Object>(param,
      // httpHeaders);
      System.out.println(url);
      restTemplate.put(url, JSONObject.class);

      System.out.println("++++++");
    } catch (Exception e) {
      MsgUtil.MsgConcelOrderToUser("", "18453170418", "通过钱包Id修改钱包记录dd号异常：" + ecerpNo + ":" + e.getMessage());
      e.printStackTrace();
    }
  }

  public void editDescription(String walletPayNo, String description) {
    try {
      String url = QbServiceImpl.BASE_URL + walletPayNo + "/description";
      RestTemplate restTemplate = new RestTemplate();
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("description", description);
      restTemplate.put(url, param);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // BigDecimal aa = new QbServiceImpl().getBalance("zhangsan");
    // System.out.println(aa);
    try {
      // BigDecimal aa = new QbServiceImpl().getBalance("zhangsan");
      // System.out.println(aa);
      //new QbServiceImpl().qbAddEcerpNo("JY52604612", "DD123456");
      //new QbServiceImpl().editDescription("JY15779285", "钱包和POS混合支付");
      new QbServiceImpl().addPay("ORDER_PAY_ALL", new BigDecimal(6.7), "菏泽市牡丹区佃户屯乡佃户屯村集十字路口东北新天地营业厅", "Dht789789", "http://localhost/order/detail.html?id=81ddfc67c4dc473caa484b9ae53ce68c", "20160602142458334");

      // Json j = new QbServiceImpl().addPay("ORDER_PAY_ALL", new
      // BigDecimal(-1000), "zhangsan", "123456");
      // System.out.println(JSON.toJSONString(j));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
