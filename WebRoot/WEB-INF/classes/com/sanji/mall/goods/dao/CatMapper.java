package com.sanji.mall.goods.dao;

import java.util.List;

import com.sanji.mall.model.Cat;

/**
 * 分类信息类
 * 
 * @ClassName: CatMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-24 下午3:18:50
 * 
 */
public interface CatMapper {
	/**
	 * 获取所有分类信息列表
	 * 
	 * @Title: gainAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Cat> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Cat> gainAll();

	/**
	 * 根据分类名称集合获取对应的类型id集合
	 * 
	 * @Title: gainIds
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param names
	 * @param @return 设定文件
	 * @return List<String> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<String> gainIds(List<String> names);

}