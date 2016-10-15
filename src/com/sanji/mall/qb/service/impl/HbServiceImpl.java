package com.sanji.mall.qb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.qb.service.HbService;

@Service("hbService")
public class HbServiceImpl implements HbService {

  public List<Map<String, Object>> getHb(String userId) {

    List<Map<String, Object>> hbList = new ArrayList<Map<String, Object>>();
    try {
      String url = QbServiceImpl.BASE_URL + "redenveloprecords?sc_EQ_status=UNUSED&sc_EQ_users.username=" + userId;
      System.out.println(url);
      /*RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<JSONObject> obj = restTemplate.getForEntity(url, JSONObject.class);
      JSONObject jsonObject = obj.getBody();*/
      Connection con = Jsoup.connect(url).header("Content-Type", "application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
              .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
              .ignoreContentType(true);
      Response rs = con.execute();// 获取响应
      System.out.println("用户信息：" + rs.body());
      JSONObject jsonObject = JSONObject.parseObject(rs.body());
      System.out.println(jsonObject);
      JSONArray content = jsonObject.getJSONArray("content");
      if (content != null && content.size() > 0) {
        for (Object jb : content) {
          JSONObject hbObj = ((JSONObject) jb).getJSONObject("belongRedEnvelop");
          Map<String, Object> hbmap = new HashMap<String, Object>();
          hbmap.put("id", ((JSONObject) jb).get("id"));
          hbmap.put("name", hbObj.getString("name"));
          hbmap.put("amount", hbObj.get("amount"));
          hbmap.put("lessLimitAmount", hbObj.get("lessLimitAmount"));
          hbList.add(hbmap);
        }
      }
      return hbList;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  public Json useHb(String hbId, String userId) {
    Json j = new Json();
    try {
      String url = QbServiceImpl.BASE_URL + "redenveloprecords/" + hbId + "/employ/";
      System.out.println("使用红包包:" + url);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<JSONObject> obj = restTemplate.postForEntity(url, null, JSONObject.class);
      System.out.println(obj.toString());
      j.setSuccess(true);
    } catch (Exception e) {
      j.setSuccess(false);
      int stateCode = Integer.parseInt(e.getMessage().substring(0, 3));
      switch (stateCode) {
      case 422:
        j.setMsg("红包不存在或者已使用");
        break;
      default:
        j.setMsg("系统异常请稍后重试");
        break;
      }
      e.printStackTrace();
    }
    return j;
  }


  public Json checkUserIfHasHb(String userId) {
    Json j = new Json();
    try {
      String url = QbServiceImpl.BASE_URL + "redenvelops/" + userId + "/useage";
      System.out.println(url);
      Connection con = Jsoup.connect(url).header("Content-Type", "application/json").header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate, sdch")
              .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
              .ignoreContentType(true);
      Response rs = con.execute();// 获取响应
      System.out.println("用户信息：" + rs.body());
      JSONObject jsonObject = JSONObject.parseObject(rs.body());
      System.out.println(jsonObject);
      if(null != jsonObject) {
        j.setObj("true");
      } 
      j.setSuccess(true);
      
      return j;
    } catch (Exception e) {
      j.setSuccess(false);
      return j;
    }
  }
      
}
