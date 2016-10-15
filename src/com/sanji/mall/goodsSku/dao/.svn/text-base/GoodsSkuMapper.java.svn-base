package com.sanji.mall.goodsSku.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.GoodsHot;
import com.sanji.mall.model.GoodsSku;

/**
 * 商品单品信息映射类
 * 
 * @ClassName: GoodsSkuMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-16 下午7:50:49
 * 
 */
public interface GoodsSkuMapper {

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
	 * 查询热销排行榜商品-SKU
	 * 
	 * @Title: gainHotByType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param goodsHot
	 * @param @return 设定文件
	 * @return List<GoodsSku> 返回类型
	 * @author ZhouZhangbao
	 */
	public List<GoodsSku> gainHotByType(GoodsHot goodsHot);

	/**
	 * 
	 * @param id
	 * @param regionId
	 *            可以为空
	 * @return
	 */
	public List<GoodsSku> selectByPrimaryKey(@Param("id") String id,
			@Param("regionId") String regionId);

	/**
	 * 根据主键ID查询一条信息<br>
	 * 基本查询，不进行任何表关联
	 * 
	 * @Title: selectById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return GoodsSku 返回类型
	 * @author ZhouZhangbao
	 */
	public GoodsSku selectById(String id);

	/**
	 * 更新库存<br>
	 * 只能更新库存
	 * 
	 * @Title: updateStockById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param goodsSku 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void updateStockById(GoodsSku goodsSku);

	public List<GoodsSku> gainHotByBrandName(@Param("start") int start,
			@Param("end") int end, @Param("brandName") String brandName);

	/**
	 * 获得商品不同地域价格浮动列表信息
	 * 
	 * @Title: getFloatPrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param skuIds 单品id字符串
	 * @param @param regionsId 地域编号
	 * @param @return 设定文件
	 * @return List<GoodsSku> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<GoodsSku> getFloatPrice(@Param("skuIds") String skuIds,
			@Param("regionsId") String regionsId);

}