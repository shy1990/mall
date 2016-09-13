package com.sanji.mall.loginLog.dao;

import com.sanji.mall.model.LoginLog;


public interface LoginLogMapper {
	/**
	 * 根据主键删除一条数据
	* @Title: deleteByPrimaryKey
	* @Description: TODO(根据主键删除一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 增加一条记录
	* @Title: insert
	* @Description: TODO(增加一条记录)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insert(LoginLog record);
    /**
	 * 有选择性的增加一条记录
	* @Title: insertSelective
	* @Description: TODO(有选择性的增加一条记录)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insertSelective(LoginLog record);
    /**
	 * 根据主键查询信息
	* @Title: selectByPrimaryKey
	* @Description: TODO(根据主键查询信息)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    LoginLog selectByPrimaryKey(String id);
    /**
   	 * 有选择性的更新一条数据
   	* @Title: updateByPrimaryKeySelective
   	* @Description: TODO(有选择性的更新一条数据)
   	* @param @param record
   	* @param @return    设定文件
   	* @return int    返回类型
   	* @author songbaozhen
   	 */
    int updateByPrimaryKeySelective(LoginLog record);
    /**
   	 * 更新一条数据
   	* @Title: updateByPrimaryKey
   	* @Description: TODO(更新一条数据)
   	* @param @param record
   	* @param @return    设定文件
   	* @return int    返回类型
   	* @author songbaozhen
   	 */
    int updateByPrimaryKey(LoginLog record);

}