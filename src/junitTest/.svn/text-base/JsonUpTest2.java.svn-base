package junitTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsonUpTest2 {
	public static void main(String[] args) {
		String url = "http://sjsm.tmall.com/category-168446487.htm?spm=a220o.1000855.w5002-3242259877.5.kf4ods&mid=w-3242259921-0&viewType=grid&catId=168446487&lowPrice=0&highPrice=200&search=y&orderType=hotsell_desc";
		long start = System.currentTimeMillis();
		Document doc = null;
		try {
			// timeout(5000)//有些网站连接比较慢，防止连接超时报错
			doc = Jsoup.connect(url).timeout(5000).get();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("连接用时:" + (System.currentTimeMillis() - start) + "ms");
		}

		Elements newTb = doc.select(".c-price");
		System.out.println("ok");
		System.out.println(newTb);
		System.out.println(newTb.text());

	}
}
