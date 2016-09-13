package com.sanji.mall.loginLog.service;

import com.sanji.mall.model.LandingException;
import com.sanji.mall.model.LoginLog;
/**
 * @ClassName: LoginLogService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-10-8 下午3:42:36
 */
public interface LoginLogService {
	/**
	 * 记录用户登陆信息
	* @Title: selectCompanyByID
	* @Description: TODO(记录用户登陆信息)
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	* @throws
	 */

	public void addLoginLog(LoginLog loginLog);
	public void addLandLog(LandingException landingException);

}
