package com.sanji.mall.admin.dao;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.Admin;


public interface AdminMapper {
	/**
	 *根据主键删除一条数据
	* @Title: deleteByPrimaryKey
	* @Description: TODO(根据主键删除一条数据)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int deleteByPrimaryKey(String id);
    /**
	 *增加一条数据
	* @Title: deleteByPrimaryKey
	* @Description: TODO(增加一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insert(Admin record);
    /**
	 *有选择性的增加一条数据
	* @Title: insertSelective
	* @Description: TODO(有选择性的增加一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insertSelective(Admin record);
    /**
	 *根据主键查询一条数据
	* @Title: selectByPrimaryKey
	* @Description: TODO(根据主键查询一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return Admin    返回类型
	* @author songbaozhen
	 */
    Admin selectByPrimaryKey(String id);
    /**
	 *有选择性的更新一条数据
	* @Title: selectByPrimaryKey
	* @Description: TODO(有选择性的更新一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int updateByPrimaryKeySelective(Admin record);
    /**
	 *更新一条数据
	* @Title: selectByPrimaryKey
	* @Description: TODO(更新一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int updateByPrimaryKey(Admin record);
    /**
	 *根据手机号码查询一条数据
	* @Title: gainByMoble
	* @Description: TODO(根据手机号码查询一条数据)
	* @param @param mobile
	* @param @return    设定文件
	* @return Admin    返回类型
	* @author songbaozhen
	 */
	public Admin gainByMoble(String mobile);
	
	/**
	 * 用于POS端的签到
	* @Title: gainAdminBylogin
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param Admin 
	* @param @return    设定文件
	* @return Admin    返回类型
	* @author ZhouZhangbao
	*/
	public Admin gainAdminBylogin(Admin admin);
	
	/**
	 * 根据省市区以及授权类别获取授权的电话号码
	* @Title: gainMsgInfoAdminByRegionsAndType
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param province
	* @param @param city
	* @param @param area
	* @param @param type
	* @param @return    设定文件
	* @return List<String>    返回类型
	* @author ZhouZhangbao
	 */
	public String gainMsgInfoAdminByRegionsAndType(Map<String, String> map);
}