package com.sanji.mall.order.dao;

import java.util.List;

import com.sanji.mall.model.DlyCorp;

/**
 * 快递公司信息记录映射类
 * 
 * @ClassName: DlyCorpMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-4 上午9:46:24
 * 
 */
public interface DlyCorpMapper {
	int deleteByPrimaryKey(String id);

	int insert(DlyCorp record);

	int insertSelective(DlyCorp record);

	/**
	 * 根据id获取快递公司记录信息
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return DlyCorp 返回类型
	 * @author 田超强
	 * @throws
	 */
	DlyCorp selectByPrimaryKey(String id);

	/**
	 * 获得所有快递公司信息
	 * 
	 * @Title: gainAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<DlyCorp> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<DlyCorp> gainAll();

	int updateByPrimaryKeySelective(DlyCorp record);

	int updateByPrimaryKey(DlyCorp record);
}