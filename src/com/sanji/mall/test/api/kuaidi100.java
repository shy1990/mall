package com.sanji.mall.test.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.sanji.mall.common.util.JsonUtil;
import com.sanji.mall.pojo.Kuaidi;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class kuaidi100 {

	public static void main(String[] agrs) {

		try {
			URL url = new URL(
					"http://api.kuaidi100.com/api?id=70b9f525d12a38cf&com=huitongkuaidi&nu=51001628604044&show=0&muti=1&order=desc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0)
				return;

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
			System.out.println("content:" + content);
//			 Kuaidi kuaidi = (Kuaidi)JsonUtil.getObject4JsonString(content,
//			 Kuaidi.class);
//			 System.out.println(kuaidi.getContext());
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
