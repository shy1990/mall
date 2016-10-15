package com.sanji.mall.ultlon.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.ultlon.model.AfterSale;

@Namespace("/ultlon")
@Action(value = "asAction", results = { @Result(name = "list", location = "/admin/myCenter/ultlon/list.jsp"),
		@Result(name = "showInfo", location = "/admin/myCenter/ultlon/info.jsp"), @Result(name = "showInfo400", location = "/admin/myCenter/ultlon/info400.jsp"),
		@Result(name = "sure", location = "/admin/myCenter/ultlon/sure.jsp") })
public class AfterSaleAction extends BaseAction implements ModelDriven<AfterSale> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderService os;

	public AfterSale as;
	public static int page = 0;

	private static String baseUrl = "http://sh.3j168.cn/";

	//private static String baseUrl = "http://127.0.0.1:8080/";

	public AfterSale getModel() {
		if (null == as) {
			as = new AfterSale();
		}
		return as;
	}

	/**
	 * 获取当前登陆用户的用户信息
	 * 
	 * @Title: getUserId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String getUserId() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getLoginName().indexOf("400") >= 0) {
			return "0";
		} else {
			return sessionInfo.getUserId();
		}
	}

	/**
	 * 根据串号查询订单信息
	 * 
	 * @Title: showInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String getInfo() {
		try {
			// imei=8660190250045272&userId=b24f7de226b84ce48fce22c42bb8ef85

			// SessionInfo sessionInfo = (SessionInfo)
			// this.session.get(ResourceUtil.getSessionInfoName());
			// System.out.println("请求地址：" + baseUrl + "aftersale/" + getUserId()
			// + "/" + as.getImei());
			Document doc = Jsoup.connect(baseUrl + "aftersale/" + getUserId() + "/" + as.getImei()).header("Content-Type", "Mimetype=application/json").header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
					.ignoreContentType(true).get();
			// System.out.println(doc.body().text());

			JSONObject jsonObject = JSONObject.parseObject(doc.body().text());
			String userId = getUserId().equals("0") ? jsonObject.getString("username").trim() : getUserId();
			// 根据用户id订单号商品编码查询信息
			Map<String, Object> orderMap = os.selectByUidAndErpAndSkunum(userId, jsonObject.getString("orderNum"), jsonObject.getString("skuCode"));
			// System.out.println("=======================" + orderMap);
			request.setAttribute("order", orderMap);
			request.setAttribute("as", jsonObject);
			String url = "";
			String goodsName = jsonObject.containsKey("goodsName") ? jsonObject.getString("goodsName") : "";
			String skuCode = jsonObject.containsKey("skuCode") ? jsonObject.getString("skuCode").trim() : "";

			// System.out.println("getuserid:" + getUserId() + " username:" +
			// jsonObject.getString("username").trim());
			if (!getUserId().equals("0") && !jsonObject.getString("username").trim().equals(getUserId())) {// 当前登陆用户id和返回的订单用户id不相等，并且id不是0.那么就是非400用户申请别人的订单串码。只能让他维修
				goodsName = "";
				jsonObject.put("receiveTime", null);
			}

			if (jsonObject.containsKey("receiveTime") && null != jsonObject.getString("receiveTime")) {
				String receiveTime = jsonObject.getString("receiveTime");
				// System.out.println("签收时间：" + receiveTime);
				url = baseUrl + "aftersale/" + userId + "?receiveTime=" + receiveTime + "&goodsName=" + goodsName + "&skuCode=" + skuCode;
			} else {// 如果商品名称为空，那么就不是本站的商品。请求实际为很早的世界只可以维修。有名称就发送一个今天的时间
				url = baseUrl + "aftersale/" + userId + "?receiveTime=" + (goodsName.equals("") ? "" : new Date().getTime()) + "&goodsName=" + goodsName + "&skuCode=" + skuCode;
			}

			doc = Jsoup.connect(url).header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").ignoreContentType(true)
					.timeout(8000).get();

			// System.out.println(doc.body().text());

			request.setAttribute("service", doc.body().text());

		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("异常：" + e.getMessage());
		}
		if (getUserId().equals("0")) {
			return "showInfo400";
		} else {
			return "showInfo";
		}

	}

	// 根据用户id拉取用户申请的记录列表
	public String list() {
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			String url = "aftersale/" + toUtf8String(sessionInfo.getLoginName()) + "?size=10&page=" + page + "&sort=createdDate,desc";

			// System.out.println("请求的url:" + url);
			Document doc = Jsoup.connect(baseUrl + url).header("Content-Type", "application/json;charset=UTF-8").header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
					.ignoreContentType(true).get();
			// System.out.println(doc.body().text());

			JSONObject jsonObject = JSONObject.parseObject(doc.body().text());

			JSONArray jsonArray = jsonObject.getJSONArray("content");

			request.setAttribute("date", jsonArray);
			request.setAttribute("metadata", jsonObject.get("metadata"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 保存申请记录
	public String save() {
		try {

			String url = baseUrl + "aftersale/";
			 System.out.println("请求地址：" + url+"        "+as.getReceiveTime());
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			Document doc = Jsoup.connect(url).header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").data(objToMap(as))
					.data("receiveTimeStr", as.getReceiveTime()).timeout(5000).ignoreContentType(true).post();

			System.out.println("保存完返回的数据：" + doc.body().text());

			request.setAttribute("msg", doc.body().text());
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("异常：" + e.getMessage() +
			// "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		return "sure";
	}

	public Map<String, String> objToMap(AfterSale afterSale) {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		Map<String, String> data = new HashMap<String, String>();
		if (null != afterSale.getImei())
			data.put("imei", afterSale.getImei());
		if (null != afterSale.getOrderNum())
			data.put("orderNum", afterSale.getOrderNum());

		data.put("type", afterSale.getType());
		data.put("skuCode", afterSale.getSkuCode());
		data.put("goodsName", afterSale.getGoodsName());
		if (getUserId().equals("0")) {// 400代申请
			data.put("username", afterSale.getUsername());
		} else {
			data.put("username", sessionInfo.getLoginName());
		}

		data.put("result", afterSale.getResult());

		// System.out.println("传入的参数：" + JSON.toJSONString(data));
		return data;
	}

	/**
	 * 格式化时间戳字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String dateStrFormat(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(Long.parseLong(str)));
	}

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect(baseUrl + "aftersale/0/000001").header("Content-Type", "Mimetype=application/json").header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").timeout(5000)
					.ignoreContentType(true).get();
			System.out.println(doc.body().text());

			JSONObject jsonObject = JSONObject.parseObject(doc.body().text());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page - 1;
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = String.valueOf(c).getBytes("utf-8");
				} catch (Exception ex) {
					// System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

}
