package com.sanji.mall.goodsSku.service;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.GoodsSku;

/**
 * 商品单品信息类
 * 
 * @ClassName: GoodsSkuService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-16 下午7:53:47
 * 
 */
public interface GoodsSkuService {
	/**
	 * 根据价格获得一个区间的单品列表
	 * 
	 * @Title: gainByPrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param map
	 * @param @return 设定文件
	 * @return List<GoodsSku> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<GoodsSku> gainByPrice(Map map);
	
	/**
	* @Title: getById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return    设定文件
	* GoodsSku    返回类型
	* @throws
	 */
	List<GoodsSku> getById(String id,String area);
	
}
