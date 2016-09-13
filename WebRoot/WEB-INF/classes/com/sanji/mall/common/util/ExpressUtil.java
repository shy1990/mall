package com.sanji.mall.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 快递相关工具类
 * 
 * @ClassName: ExpressUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-4 上午9:22:48
 * 
 */
public class ExpressUtil {

	/**
	 * 根据快递单号和制定快递公司代码获取快递信息
	 * 
	 * @Title: getExpressInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param com 要查询的快递公司代码，不支持中文
	 * @param @param nu 要查询的快递单号，请勿带特殊符号，不支持中文（大小写不敏感）
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static String getExpressInfo(String com, String nu) {
		try {
			// System.out.println("要查询的快递公司编号：" + com + "   快递单号：" + nu);
			// URL url = new
			// URL("http://api.kuaidi100.com/api?id=70b9f525d12a38cf&com=huitongkuaidi&nu=51001628604044&show=0&muti=1&order=desc");
			URL url = new URL("http://api.kuaidi100.com/api?id=70b9f525d12a38cf&com=" + com + "&nu=" + nu + "&show=0&muti=1&order=asc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0)
				return null;

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			// System.out.println("content:" + content);
			// Kuaidi kuaidi = (Kuaidi) JsonUtil.getObject4JsonString(content,
			// Kuaidi.class);
			// // System.out.println(kuaidi.getContext());
			urlStream.close();
			return content;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	};

	public static void main(String[] args) {
		getExpressInfo("yuantong", "100033892580");
	}

}
