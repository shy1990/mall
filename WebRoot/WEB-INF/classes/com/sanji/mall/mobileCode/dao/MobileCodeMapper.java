package com.sanji.mall.mobileCode.dao;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.MobileCode;


public interface MobileCodeMapper {
	/**
	 *根据主键删除一条记录
	* @Title: deleteByPrimaryKey
	* @Description: TODO(根据主键删除一条记录)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int deleteByPrimaryKey(String id);
    /**
	 *增加一条记录
	* @Title: insert
	* @Description: TODO(增加一条记录)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insert(MobileCode record);
    /**
	 *有选则性的增加一条记录
	* @Title: insertSelective
	* @Description: TODO(有选则性的增加一条记录)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insertSelective(MobileCode record);
    /**
   	 *根据主键查询手机密码信息
   	* @Title: insertSelective
   	* @Description: TODO(根据主键查询手机密码信息)
   	* @param @param id
   	* @param @return    设定文件
   	* @return MobileCode    返回类型
   	* @author songbaozhen
   	 */
    MobileCode selectByPrimaryKey(String id);
    /**
   	 *有选择性的更新一条数据
   	* @Title: updateByPrimaryKeySelective
   	* @Description: TODO(有选择性的更新一条数据)
   	* @param @param record
   	* @param @return    设定文件
   	* @return int    返回类型
   	* @author songbaozhen
   	 */
    int updateByPrimaryKeySelective(MobileCode record);
    /**
   	 *更新一条数据
   	* @Title: updateByPrimaryKey
   	* @Description: TODO(更新一条数据)
   	* @param @param record
   	* @param @return    设定文件
   	* @return int    返回类型
   	* @author songbaozhen
   	 */
    int updateByPrimaryKey(MobileCode record);
    /**
   	 *更新一条数据
   	* @Title: gainCodeByMap
   	* @Description: TODO(更新一条数据)
   	* @param @param map
   	* @param @return    设定文件
   	* @return List<MobileCode>    返回类型
   	* @author songbaozhen
   	 */
	public List<MobileCode> gainCodeByMap(Map<String, String> map);
	
	/**
	 * 根据验证码查询一条记录
	* @Title: gainVerificationCode
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param String
	* @param @return    设定文件
	* @return String   返回类型
	* @author songbaozhen
	 */
	public Long gainVerificationCode(String code);
}