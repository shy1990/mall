package junitTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.order.service.OrderService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class RestTest {
	// @Autowired
	// @Qualifier("clientRestTemplate")

	private RestTemplate restTemplate;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderservice;
	
	

	@Test
	public void sda() {
		/*Order order = orderservice
				.gainOrderByID("d5a352d642e845e7a093ff19e5441335");

		Members m = memberService
				.gainMembersDetailById("4a6451f565e5489391c23f79183b099a");

		MsgUtil.MsgInfoXDApp("15562282883", order, m);
		System.out.println("!!!!!!!!!");*/
		Order order = orderservice.gainOrderALLByID("675da2eabf1544fb9d691afba5219180");
		Members m = memberService.gainMembersDetailById(order.getMemberId());
	//	Admin admin = adminService.getAdminById(m.getAdminId());
		MsgUtil.MsgInfoXDApp("15753766321", order, m);


	}

	@Test
	public void test() {
		try {
			restTemplate = new RestTemplate();

			List messageConverters = new ArrayList();
			messageConverters.add(new SourceHttpMessageConverter());
			messageConverters.add(new FormHttpMessageConverter());
			messageConverters.add(new MappingJacksonHttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);

			// restTemplate.
			Map<String, String> param = new HashMap<String, String>();
			param.put("type", "ORDER_PAY_ALL");
			param.put("description", "订单支付");
			param.put("amount", "-100");
			param.put("username", "zhangsan");
			param.put("url", "http://www.3j1688.com/xxx");
			param.put("payPassword", "123456");

			ResponseEntity<JSONObject> obj = restTemplate.postForEntity(
					"http://192.168.2.247:58080/v2/tradings/", param,
					JSONObject.class);

			System.out.println(JSON.toJSONString(obj));
		} catch (Exception e) {
			System.out.println("异常LLLLLLLLLLLL：" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate2 = new RestTemplate();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", "ORDER_PAY_ALL");
			param.put("description", "订单支付");
			param.put("amount", -100);
			param.put("username", "zhangsan");
			param.put("url", "http://www.3j1688.com/xxx");
			param.put("payPassword", "123456");
			List messageConverters = new ArrayList();
			messageConverters.add(new SourceHttpMessageConverter());
			messageConverters.add(new FormHttpMessageConverter());
			messageConverters.add(new MappingJacksonHttpMessageConverter());
			restTemplate2.setMessageConverters(messageConverters);

			HttpHeaders httpHeaders = new HttpHeaders(); // 设置HTTP请求的请求头信息
			// httpHeaders.setContentType(MediaType.parseMediaType("application/json"));
			// // 设置相应内容，相应内容将被转换为json格式返回
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			// httpHeaders.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));
			// httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// 设置HttpEntity的Body类型为String，调用StringHttpMessageConverter转换报文体参数
			HttpEntity<Map<String, Object>> httpEntity = new HttpEntity(param,
					httpHeaders);

			ResponseEntity<JSONObject> obj = restTemplate2.postForEntity(
					"http://192.168.2.247:58080/v2/tradings/", httpEntity,
					JSONObject.class);

			System.out.println(JSON.toJSONString(obj));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
