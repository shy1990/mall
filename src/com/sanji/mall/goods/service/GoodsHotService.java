/**  
* @Title: GoodsHotService.java
* @Package com.sanji.mall.goods.service
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-23 下午3:01:22
* @version V1.0  
*/
package com.sanji.mall.goods.service;

import java.util.List;

import com.sanji.mall.model.GoodsHot;
import com.sanji.mall.model.GoodsSku;

/**
 * @ClassName: GoodsHotService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-23 下午3:01:22
 */
public interface GoodsHotService {
	/**
	 * 根据热销分类获取相应的热销商品
	 * 
	 * @Title: gainGoodsHotByType
	 * @Description: TODO(根据热销分类获取相应的热销商品)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsSku> gainGoodsHotByType(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title: gainGoodsHotByPhone
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotByPhone(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title: gainGoodsHotByBrand
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotByBrand(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title: gainGoodsHotBy4G
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotBy4G(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title:gainGoodsHotByLt3G
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotByLt3G(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title: gainGoodsHotByDx3G
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotByDx3G(GoodsHot goodsHot);
	/**
	 * 根据热销排行分类获取相应的热销排行
	 * 
	 * @Title: gainGoodsHotByYd3G
	 * @Description: TODO(根据热销排行分类获取相应的热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodsHotByYd3G(GoodsHot goodsHot);
}
