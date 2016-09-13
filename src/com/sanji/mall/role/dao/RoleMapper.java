package com.sanji.mall.role.dao;

import com.sanji.mall.model.Role;



public interface RoleMapper {
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
	* @Title: insert
	* @Description: TODO(增加一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insert(Role record);
    /**
	 *有选择的增加一条数据
	* @Title: insertSelective
	* @Description: TODO(有选择的增加一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int insertSelective(Role record);
    /**
	 *根据主键查询数据
	* @Title: selectByPrimaryKey
	* @Description: TODO(根据主键查询数据)
	* @param @param record
	* @param @return    设定文件
	* @return Role    返回类型
	* @author songbaozhen
	 */
    Role selectByPrimaryKey(String id);
    /**
	 *有选择性的更新一条数据
	* @Title: selectByPrimaryKey
	* @Description: TODO(有选择性的更新一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int updateByPrimaryKeySelective(Role record);
    /**
	 *更新一条数据
	* @Title: updateByPrimaryKey
	* @Description: TODO(更新一条数据)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
    int updateByPrimaryKey(Role record);
    /**
	 *根据主键查询一条数据
	* @Title: updateByPrimaryKey
	* @Description: TODO(根据主键查询一条数据)
	* @param @param aid
	* @param @return    设定文件
	* @return String    返回类型
	* @author songbaozhen
	 */
    public	String gainByAid(String aid);
}