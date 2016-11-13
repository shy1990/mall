/**  
 * @Title: UserAction.java
 * @Package com.sanji.mall.test.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-7-15 下午3:31:41
 * @version V1.0  
 */
package com.sanji.mall.members.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.IpUtil;
import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.loginLog.service.LoginLogService;
import com.sanji.mall.members.service.LoginService;
import com.sanji.mall.model.LandingException;
import com.sanji.mall.model.LoginLog;
import com.sanji.mall.model.Members;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.whiteList.service.WhiteListService;

/**
 * 测试获取用户信息的ACTION
 * 
 * @ClassName: LoginAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author SongBaoZhen
 * @date 2014-7-15 下午3:31:41
 */
@Namespace("/member")
@Action(value = "loginAction", results = { @Result(name = "succeed", type = "redirect", location = "/member/home.html"), @Result(name = "noSession", location = "/index.jsp") })
public class LongAction extends BaseAction implements ModelDriven<Members> {
	private static final Logger logger = Logger.getLogger(LongAction.class);

	private static final long serialVersionUID = 1L;
	private Members members;
	private LoginLog loginLog = new LoginLog();
	private LandingException landingException = new LandingException();

	@Resource
	private LoginService loginService;
	@Resource
	private LoginLogService loginLogService;
	@Resource
	private WhiteListService whiteListService;
	// @Resource
	// private LandingException landingException1;

	/**
	 * 登录
	 * 
	 * @Title: doNotNeedSession_login
	 * @Description: TODO(用户登录)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	private static final Logger tracking = Logger.getLogger("tracking");

	public String doNotNeedSession_login() {
		try {
			SessionInfo si = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
			String un = si == null ? null : si.getLoginName();
			String pw = si == null ? null : si.getLoginPassword();
			String ar = si == null ? null : si.getArea();
			members = loginService.gainMembersByUsernameAndPassword(members.getUsername() == null ? un : members.getUsername().trim(),
					members.getPassword() == null ? pw : MD5.encrypt(members.getPassword().trim()));
 
			if (members != null) {
				/*
				 * if
				 * (("山东省".equals(IpUtil.getIpInfo2(IpUtil.getIpAddr(request))
				 * .get("region"))) ||
				 * (whiteListService.gainWhiteListByIp(IpUtil
				 * .getIpAddr(request))) ||
				 * members.getMobile().equals("13600006313") ||
				 * members.getUsername().trim().equals("zhangsan")) {//
				 * 13600006313
				 */
				
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo.setUserId(members.getId());
				sessionInfo.setLoginName(members.getUsername());
				sessionInfo.setLoginPassword(members.getPassword());
				sessionInfo.setLoginNickName(members.getNickname());
				sessionInfo.setMoblie(members.getMobile());
				sessionInfo.setIp(IpUtil.getIpAddr(request));
				sessionInfo.setArea(members.getArea());
				sessionInfo.setCity(IpUtil.getIpInfo2(IpUtil.getIpAddr(request))
						  .get("city"));
				loginLog = reglogin();// 用户登录时记录登录信息
				loginLogService.addLoginLog(loginLog);
				session.put(ResourceUtil.getSessionInfoName(), sessionInfo);

        // 将sessionInfo推送到redis
        request.getSession().setAttribute("sessionInfo",sessionInfo);

				return "succeed";
				/*
				 * } else {
				 * 
				 * landingException = landingException();// 用户异常登录时记录登录信息
				 * 
				 * loginLogService.addLandLog(landingException); //
				 * 记录登陆异常的用户名，IP，登陆时间， request.setAttribute("msg",
				 * "您的账户IP登录有异常！"); return "noSession"; }
				 */

			} else {
				request.setAttribute("msg", "用户名或密码错误！");
				return "noSession";
			}
		} catch (Exception e) {

			e.printStackTrace();
			logger.info("异常+++++++++++++++++++++：" + e.getMessage());
			return "err";
		}
	}

	private LoginLog reglogin() {

		loginLog.setId(ToolsUtil.getUUID());
		loginLog.setLoginAddr(members.getAddress());
		loginLog.setLoginIp(IpUtil.getIpAddr(request));
		loginLog.setLoginTime(new Date());
		loginLog.setUserId(members.getId());
		loginLog.setUserType(new BigDecimal(2));

		return loginLog;
	}

	private LandingException landingException() {

		landingException.setId(ToolsUtil.getUUID());
		landingException.setLoginAddr(members.getAddress());
		landingException.setLoginIp(IpUtil.getIpAddr(request));
		landingException.setLoginTime(new Date());
		landingException.setUserId(members.getId());
		// landingException.setUserType(new BigDecimal(2));

		return landingException;
	}

	/**
	 * 退出登录
	 * 
	 * @Title: logout
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String logout() {
		session.clear();
		return "noSession";
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
	}

	public Members getModel() {
		if (members == null) {
			members = new Members();
		}
		return members;
	}

	public static void main(String[] args) {
		System.out.println(MD5.encrypt("Dht789789"));
	}
	
}
