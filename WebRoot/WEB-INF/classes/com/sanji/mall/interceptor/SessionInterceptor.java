package com.sanji.mall.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.pojo.SessionInfo;

/**
 * session拦截�?
 * 
 * @ClassName: SessionInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:24:15
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;

	// private static final Logger logger =
	// Logger.getLogger(SessionInterceptor.class);
	// private static final Logger tracking = Logger.getLogger("tracking");

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录！");
			return "noSession";
		} else {
			// // System.out.println("跟踪用户" + sessionInfo.getIp());
			/*
			 * tracking.info("用户:" +
			 * IpUtil.getIpAddr(ServletActionContext.getRequest()) + " ip:" +
			 * sessionInfo.getIp() + "来源地址：" +
			 * ServletActionContext.getRequest().getHeader("Referer") + "请求地址："
			 * + ServletActionContext.getRequest().get + "时间：" +
			 * (DateUtil.getStringByDate(new Date())));
			 */
		}
		return actionInvocation.invoke();
	}
}
