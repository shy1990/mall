package com.sanji.mall.brand.service;

import java.util.List;

import com.sanji.mall.model.Brand;

/**
 * 品牌信息接口类
 * 
 * @ClassName: BrandService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-24 下午3:40:56
 * 
 */
public interface BrandService {
	/**
	 * 获得所有商品信息列表
	 * 
	 * @Title: gainAll
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Brand> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Brand> gainAll();

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
	
	List<Brand> getHotSaleBrand();
}
