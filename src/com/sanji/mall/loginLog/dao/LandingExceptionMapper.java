package com.sanji.mall.loginLog.dao;

import com.sanji.mall.model.LandingException;
//import com.sanji.mall.model.LoginLog;


public interface LandingExceptionMapper {
	
	/**
	 * 增加一条记录
	* @Title: insert
	* @Description: TODO(有选择性的增加一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author shihongyue
	 */
    int insertLand(LandingException record);


}