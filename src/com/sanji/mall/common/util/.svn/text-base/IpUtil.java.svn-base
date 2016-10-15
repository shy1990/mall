/**  
 * @Title: IpUtil.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-7-23 下午1:53:52
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * IP 工具类
 * 
 * @ClassName: IpUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-23 下午1:53:52
 */
public class IpUtil {
	/**
	 * 获取登录用户IP地址
	 * 
	 * @Title: getIpAddr
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf("0:") != -1) {
			ip = "本地";
		}
		return ip;
	}

	/**
	 * 通过IP获取地址
	 * 
	 * @Title: getIpInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param ip
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String getIpInfo(String ip) {
		String info = "";
		try {
			URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
			htpcon.setRequestMethod("GET");
			htpcon.setDoOutput(true);
			htpcon.setDoInput(true);
			htpcon.setUseCaches(false);

			InputStream in = htpcon.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			JSONObject obj = (JSONObject) JSON.parse(temp.toString());
			if (obj.getIntValue("code") == 0) {
				JSONObject data = obj.getJSONObject("data");
				info += data.getString("country") + " ";
				info += data.getString("region") + " ";
				info += data.getString("city") + " ";
				info += data.getString("county") + " ";
				info += data.getString("isp");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 通过IP获取地理信息地址
	 * 
	 * @Title: getIpInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param ip
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	/*
	 * public static Map<String, String> getAddressInfoByIp(String ip) {
	 * Map<String, String> map = new HashMap<String, String>(); String infos =
	 * ""; try { URL url = new
	 * URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
	 * HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
	 * htpcon.setRequestMethod("GET"); htpcon.setDoOutput(true);
	 * htpcon.setDoInput(true); htpcon.setUseCaches(false);
	 * 
	 * InputStream in = htpcon.getInputStream(); BufferedReader bufferedReader =
	 * new BufferedReader(new InputStreamReader(in)); StringBuffer temp = new
	 * StringBuffer(); String line = bufferedReader.readLine(); while (line !=
	 * null) { temp.append(line).append("\r\n"); line =
	 * bufferedReader.readLine(); } bufferedReader.close(); JSONObject obj =
	 * (JSONObject) JSON.parse(temp.toString()); if (obj.getIntValue("code") ==
	 * 0) { JSONObject data = obj.getJSONObject("data"); map.put("region",
	 * data.getString("region")); map.put("city", data.getString("city"));
	 * map.put("county", data.getString("county")); } } catch
	 * (MalformedURLException e) { e.printStackTrace(); } catch
	 * (ProtocolException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } return map; }
	 */

	public static Map<String, String> getIpInfo2(String ip) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (!"127.0.0.1".equals(ip) && !ip.equals("本地")) {
			try {
				String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
				Document doc = Jsoup.connect(url).get();
				String json = doc.text();
				JSONObject jb = (JSONObject) JSONObject.parse(json);
				JSONObject data = jb.getJSONObject("data");
				resultMap.put("country", data.getString("country"));// 国家
				resultMap.put("region", data.getString("region"));// 省
				resultMap.put("city", data.getString("city"));// 市
				resultMap.put("county", data.getString("county"));// 区
				// System.out.println(resultMap);

			} catch (Exception e) {
				// TODO: handle exception
				// System.err.println(e.getMessage());
			}
		} else {
			resultMap.put("region", "山东省");
		}
		return resultMap;
	}

	public static void main(String[] args) {
		// System.out.println(JSON.toJSONString(getIpInfo2("219.72.203.11")));
	}

}
