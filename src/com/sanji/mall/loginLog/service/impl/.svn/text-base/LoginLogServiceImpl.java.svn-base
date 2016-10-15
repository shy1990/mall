package com.sanji.mall.loginLog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.loginLog.dao.LandingExceptionMapper;
import com.sanji.mall.loginLog.dao.LoginLogMapper;
import com.sanji.mall.loginLog.service.LoginLogService;
import com.sanji.mall.model.LandingException;
import com.sanji.mall.model.LoginLog;
/**
 * @ClassName: LoginLogServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-10-8 下午3:48:58
 */
@Service("loginLogService")
@Transactional(rollbackFor = Exception.class)
public class LoginLogServiceImpl implements LoginLogService {
	@Resource
	private LoginLogMapper loginLogMapper;
	@Resource
	private LandingExceptionMapper landingExceptionMapper;
	/**
	 * 添加一条用户登录的信息
	* @Title: MgsCodeSender
	* @Description: TODO(添加一条用户登录的信息)
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	 */
	public void addLoginLog(LoginLog loginLog) {
		// TODO Auto-generated method stub
		loginLogMapper.insertSelective(loginLog);
	}
	public void addLandLog(LandingException record) {
		// TODO Auto-generated method stub
		landingExceptionMapper.insertLand(record);
		
	}
	
}
