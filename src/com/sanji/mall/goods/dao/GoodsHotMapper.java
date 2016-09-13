package com.sanji.mall.goods.dao;

import java.util.List;

import com.sanji.mall.model.GoodsHot;


public interface GoodsHotMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsHot record);

    int insertSelective(GoodsHot record);

    GoodsHot selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsHot record);

    int updateByPrimaryKey(GoodsHot record);
    /**
	 * 根据热销类型周获取热销排行
	 * 
	 * @Title: gainGoodHotTopByPhone
	 * @Description: TODO(根据热销类型周获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopByPhone(GoodsHot goodsHot);
	/**
	 * 根据热销类型品牌获取热销排行
	 * 
	 * @Title: gainGoodHotTopByBrand
	 * @Description: TODO(根据热销类型品牌获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopByBrand(GoodsHot goodsHot);
	/**
	 * 根据热销类型获取热销排行
	 * 
	 * @Title: gainGoodHotTopBy4G
	 * @Description: TODO(根据热销类型获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopBy4G(GoodsHot goodsHot);
	/**
	 * 根据热销类型获取热销排行
	 * 
	 * @Title: gainGoodHotTopByLt3G
	 * @Description: TODO(根据热销类型获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopByLt3G(GoodsHot goodsHot);
	/**
	 * 根据热销类型获取热销排行
	 * 
	 * @Title: gainGoodHotTopByYd3G
	 * @Description: TODO(根据热销类型获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopByYd3G(GoodsHot goodsHot);
	/**
	 * 根据热销类型获取热销排行
	 * 
	 * @Title: gainGoodHotTopByDx3G
	 * @Description: TODO(根据热销类型获取热销排行)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public List<GoodsHot> gainGoodHotTopByDx3G(GoodsHot goodsHot);

	/**
	* @Title: gainHotByType
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param goodsHot
	* @param @return    设定文件
	* @return List<GoodsHot>    返回类型
	* @author ZhouZhangbao
	*/
	
	public List<GoodsHot> gainHotByType(GoodsHot goodsHot);
}