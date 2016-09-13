package junitTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsonUpTest {
	public static void main(String[] args) {
		String url = "http://detail.zol.com.cn/274/273715/param.shtml";
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

		Elements newTb = doc.select("#newTb table");
		String textVal = "";// 取到的值
		Elements span;// 查找最底层的span

		// 获取基本参数
		System.out.println("基本参数+++++++++++++++++++++++++");
		Elements table0 = newTb.eq(0);
		Elements li = table0.select(".category_param_list li");
		textVal = li.eq(0).select("span").eq(1).select("a").text();
		System.out.println("曝光日期：" + textVal);
		textVal = li.eq(1).select("span").eq(1).select("a").text();
		System.out.println("手机类型：" + textVal);

		// 获取屏幕
		System.out.println("屏幕参数+++++++++++++++++++++++++");
		Elements table1 = newTb.eq(1);
		li = table1.select(".category_param_list li");
		textVal = li.eq(0).select("span").eq(1).text();
		System.out.println("触摸屏类型：" + textVal);
		textVal = li.eq(1).select("span").eq(1).text();
		System.out.println("主屏尺寸：" + textVal);

		textVal = li.eq(2).select("span").eq(1).text();
		System.out.println("主屏材质：" + textVal);

		textVal = li.eq(3).select("span").eq(1).text();
		System.out.println("主屏分辨率：" + textVal);

		textVal = li.eq(4).select("span").eq(1).text();
		System.out.println("屏幕像素密度：" + textVal);

		textVal = li.eq(5).select("span").eq(1).text();
		System.out.println("窄边框：" + textVal);

		textVal = li.eq(6).select("span").eq(1).text();
		System.out.println("屏幕占比：" + textVal);

		// 获取网络
		System.out.println("网络参数+++++++++++++++++++++++++");
		Elements table2 = newTb.eq(2);
		li = table2.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("网络类型：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("4G网络：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("3G网络：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("支持频段：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("WLAN功能：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("导航：" + span.text());

		span = li.eq(6).select("span").eq(1);
		System.out.println("连接与共享：" + span.text());

		// 获取硬件
		System.out.println("硬件参数+++++++++++++++++++++++++");
		Elements table3 = newTb.eq(3);
		li = table3.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("操作系统：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("核心数：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("CPU型号：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("CPU频率：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("GPU型号：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("RAM容量：" + span.text());

		span = li.eq(6).select("span").eq(1);
		System.out.println("ROM容量：" + span.text());

		span = li.eq(7).select("span").eq(1);
		System.out.println("存储卡：" + span.text());

		span = li.eq(8).select("span").eq(1);
		System.out.println("电池类型：" + span.text());

		span = li.eq(9).select("span").eq(1);
		System.out.println("电池容量：" + span.text());

		span = li.eq(10).select("span").eq(1);
		System.out.println("理论通话时间：" + span.text());

		span = li.eq(11).select("span").eq(1);
		System.out.println("理论待机时间：" + span.text());

		span = li.eq(12).select("span").eq(1);
		System.out.println("其他硬件参数：" + span.text());

		// 获取摄像头
		System.out.println("摄像头参数+++++++++++++++++++++++++");
		Elements table4 = newTb.eq(4);
		li = table4.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("摄像头：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("摄像头类型：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("后置摄像头：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("前置摄像头：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("传感器类型：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("闪光灯：" + span.text());

		span = li.eq(6).select("span").eq(1);
		System.out.println("光圈：" + span.text());

		span = li.eq(7).select("span").eq(1);
		System.out.println("摄像头特色：" + span.text());

		span = li.eq(8).select("span").eq(1);
		System.out.println("视频拍摄：" + span.text());

		span = li.eq(9).select("span").eq(1);
		System.out.println("拍照功能：" + span.text());

		span = li.eq(10).select("span").eq(1);
		System.out.println("其他摄像头参数：" + span.text());

		// 获取外观
		System.out.println("外观参数+++++++++++++++++++++++++");
		Elements table5 = newTb.eq(5);
		li = table5.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("造型设计：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("机身颜色：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("手机尺寸：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("手机重量：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("操作类型：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("感应器类型：" + span.text());

		span = li.eq(6).select("span").eq(1);
		System.out.println("SIM卡类型：" + span.text());

		span = li.eq(7).select("span").eq(1);
		System.out.println("机身接口：" + span.text());

		// 获取服务与支持
		System.out.println("服务与支持参数+++++++++++++++++++++++++");
		Elements table6 = newTb.eq(6);
		li = table6.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("音频支持：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("视频支持：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("常用功能：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("商务功能：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("服务特色：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("其他功能参数：" + span.text());

		// 获取手机附件
		System.out.println("手机附件参数+++++++++++++++++++++++++");
		Elements table7 = newTb.eq(7);
		li = table7.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("包装清单：" + span.text());

		// 获取保修信息
		System.out.println("保修信息参数+++++++++++++++++++++++++");
		Elements table8 = newTb.eq(8);
		li = table8.select(".category_param_list li");

		span = li.eq(0).select("span").eq(1);
		System.out.println("保修政策：" + span.text());

		span = li.eq(1).select("span").eq(1);
		System.out.println("质保时间：" + span.text());

		span = li.eq(2).select("span").eq(1);
		System.out.println("质保备注：" + span.text());

		span = li.eq(3).select("span").eq(1);
		System.out.println("客服电话：" + span.text());

		span = li.eq(4).select("span").eq(1);
		System.out.println("电话备注：" + span.text());

		span = li.eq(5).select("span").eq(1);
		System.out.println("详细内容：" + span.text());
	}
}
