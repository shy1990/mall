package com.sanji.mall.common.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
// 这里是package的name
@Namespace("/")
// 命名空间
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6230751116897773145L;
	protected Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	// 将对象写成json扔到前台
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			// System.err.println(json);
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转换成JSON字符串，过滤不想返回的字段
	 * 
	 * @Title: writeJsonByExcludesProperties
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param object 需要转换的对象
	 * @param @param excludesProperties 不需要转换的属性
	 * @return String 转换过后的json字符串
	 * @author 田超强
	 * @throws
	 */
	public String writeJsonByExcludesProperties(Object object, String[] excludesProperties) {
		try {
			FastjsonFilter filter = new FastjsonFilter();
			if (excludesProperties != null && excludesProperties.length > 0) {
				filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
			}
			String json;
			String User_Agent = getRequest().getHeader("User-Agent");
			if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
				// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect,
						SerializerFeature.BrowserCompatible);
			} else {
				// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd
				// hh24:mi:ss
				// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
				json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
			}
			// // System.out.println("转换后的JSON字符串：" + json);
			return json;
			// ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			// ServletActionContext.getResponse().getWriter().write(json);
			// ServletActionContext.getResponse().getWriter().flush();
			// ServletActionContext.getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResopnse() {
		return response;
	}

}
