package com.sanji.mall.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sanji.mall.pojo.SessionInfo;

public class TractLogFilter implements Filter {
	private static final Logger tracking = Logger.getLogger("tracking");

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest) request;
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		String userName = "";
		if (sessionInfo != null) {
			userName = sessionInfo.getLoginName();
		}

		tracking.info("," + IpUtil.getIpAddr(hsRequest) + "," + userName + "," + hsRequest.getHeader("Referer") + "," + hsRequest.getRequestURL());

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
