package com.sanji.mall.collect.service;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.Collect;

/**
 * 商品收藏接口类
 * 
 * @ClassName: CollectService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-21 下午9:41:53
 * 
 */
public interface CollectService {
	/**
	 * 根据用户id和商品id获取一条收藏记录
	 * 
	 * @Title: gainByTidMid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return Collect 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Collect> gainByTidMid(Map param);

	/**
	 * 根据商品id和用户id物理删除收藏记录
	 * 
	 * @Title: dropById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int dropById(String id);

	/**
	 * 添加一个商品收藏记录
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int add(Collect record);

	/**
	 * 根据用户id获取用户单品收藏列表
	 * 
	 * @Title: gainByMidSku
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Map> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Map> gainByMidSku(Map param);

	/**
	 * 根据用户id获取用户配件收藏列表
	 * 
	 * @Title: gainByMidAcs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Map> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Map> gainByMidAcs(Map param);

	/**
	 * 获取单品总数量
	 * 
	 * @Title: gainByMidSkuTatolNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainByMidSkuTatolNum(Map param);

	/**
	 * 获取配件总数量
	 * 
	 * @Title: gainByMidAcsTatolNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainByMidAcsTatolNum(Map param);
}
