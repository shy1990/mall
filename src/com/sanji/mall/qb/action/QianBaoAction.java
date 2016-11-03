package com.sanji.mall.qb.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.Hmac;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.qb.service.HbService;
import com.sanji.mall.qb.service.QbService;

@Namespace("/qb")
@Action(value = "qbAction", results = { @Result(name = "success", location = "/admin/myCenter/qb/index.jsp") })
public class QianBaoAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QianBaoAction.class);

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;
	private static final String KEY = "doubi";
	// private static final String BASE_URL = "http://localhost:8082/";
	//private static final String BASE_URL = "http://115.28.92.73:58081/";//跳转到钱包的地址
	private static final String BASE_URL = "http://qb.3j1688.com/";//跳转到钱包的地址

	@Autowired
	private QbService qbService;
	
	@Autowired 
	private HbService hbService;

	/**
	 * 加密相关信息跳转到钱包
	 * 
	 * @Title: toQianBao
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void toQianBao() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		if (null != sessionInfo) {
			try {
				// http://localhost:8081/hmacLogin?username=zhangsan&hmac=7be94f5f53d05bd9efe7ccbdd0d6236a
				// Long validDate = DateUtil.getAnotherDate2(new Date(),
				// 30).getTime();// 获取30秒以后的时间字符串
				String url = BASE_URL + "hmacLogin?phone=" + sessionInfo.getMoblie() + "&username=" + toUtf8String(sessionInfo.getLoginName());// +
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("username", sessionInfo.getLoginName());
				param.put("phone", sessionInfo.getMoblie());
				String checkSum = Hmac.hmacMd5Hex(KEY, param);
				System.out.println("url:"+url + "&hmac=mall:" + checkSum);
				response.sendRedirect(url + "&hmac=mall:" + checkSum);
			} catch (Exception e) {
				MsgUtil.testSuccess("18453170418", "你大爷的你大爷异常：：：" + e.getMessage());
				// // System.out.println("跳转异常：" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// 跳转到支付页面首先要判断是否可用钱包功能，可用钱包余额
	public void getBalance() {
		// 获取用户当前余额，如果余额为0那么就不显示选择钱包支付功能
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		BigDecimal balance = qbService.getBalance(sessionInfo.getLoginName());
		// BigDecimal balance = new BigDecimal("20");
		request.getSession().setAttribute("balance", balance);
		writeJson(balance);
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
	
	   public static String toIsoString(String s) {
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            if (c >= 0 && c <= 255) {
	                sb.append(c);
	            } else {
	                byte[] b;
	                try {
	                    b = String.valueOf(c).getBytes("ISO-8859-1");
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

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//红包包相关操作
	public void getHb(){
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			List<Map<String, Object>> hblist = hbService.getHb(toUtf8String(sessionInfo.getLoginName()));
			if (hblist!=null&&hblist.size()>0) {
				request.getSession().setAttribute(hblist.get(0).get("id").toString(), hblist.get(0));//把当前可使用红包放入session里面
				json.setObj(hblist.get(0));
			}
		} catch (Exception e) {
			json.setMsg("系统异常请稍后重试");
			e.printStackTrace();
		}
		writeJson(json);
	}
	
	public void checkUserIfHasHb(){
	  Json json = new Json();
    try {
      SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
      json =  hbService.checkUserIfHasHb(toUtf8String(sessionInfo.getLoginName()));
    } catch (Exception e) {
      json.setMsg("系统异常请稍后重试");
      e.printStackTrace();
    }
    writeJson(json);
	}
	
	
	// 提交到去支付只需要提交是否使用钱包后台自动判断，是否

	// 是只使用钱包还是钱包组合应用

	public static void main(String[] args) {
		/*
		 * String validDate =
		 * DateUtil.getStringByDate(DateUtil.getAnotherDate2(new Date(), 30));//
		 * 获取30秒以后的时间字符串 // // System.out.println(validDate);
		 */

		BigDecimal a = new BigDecimal(20);

		a.abs();

	}

}
