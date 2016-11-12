package com.sanji.mall.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Goods;

/**
 * 
 * @ClassName: GoodsMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-11 下午3:39:56
 * 
 */
public interface GoodsMapper {

	/**
	 * 根据主键获取一条数据
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(根据主键获取一条数据)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Goods 返回类型
	 * @author 田超强
	 */
	Goods selectByPrimaryKey(String id);

	/**
	 * 获取指定条数的数据
	 * 
	 * @Title: select
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Goods> 返回类型
	 * @author 田超强
	 */
	public List<Goods> select();

	/**
	 * 根据编号查询商品所有信息
	 * 
	 * @Title: gainAllGoodsInfoByGoodsNum
	 * @Description: TODO(根据id查询商品所有信息)
	 * @param @param goodsNum
	 * @param @return 设定文件
	 * @return Goods 返回类型
	 * @author 田超强
	 */
	List<Goods> gainAllGoodsInfoByGoodsNum(String goodsNum);

	/**
	 * 用户登录状态下传入用户id和商品编号。查询商品所有相关信息，可查询是否已收藏
	 * 
	 * @Title: gainAllGoodsInfoByMap
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Goods> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Goods> gainAllGoodsInfoByMap(Map param);

	/**
	 * 根据用户Id和商品编号获取商品详细信息，动态计算价格
	 * 
	 * @Title: gainAllGoodsInfo2
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param memberId
	 * @param @param goodsNum
	 * @param @return 设定文件
	 * @return List<Goods> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Goods> gainAllGoodsInfo2(@Param("memberId") String memberId, @Param("goodsNum") String goodsNum, @Param("area") String area,@Param("userName")String userName);
	
	   /**
     * 查询是否有对此用户的热销商品 
     * 
     * @Title: gainAllShoppingRush
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param memberId
     * @param @param goodsNum
     * @param @return 设定文件
     * @return List<Goods> 返回类型
     * @author 田超强
     * @throws
     */
    List<Goods> gainAllShoppingRush(@Param("memberId") String memberId, @Param("userName") String
			userName);

	/**
	 * 更新商品点击数
	 * 
	 * @Title: upClickRate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param goodsNum
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int upClickRate(String goodsNum);

	/**
	 * 有条件的分页查找商品列表信息
	 * 
	 * @Title: gainByPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Map> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Goods> gainByPage(Map param);

	/**
	 * 分页获取商品列表信息的总条数
	 * 
	 * @Title: gainCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainCountNum(Map param);

	/**
	 * 有条件的分页查找配件列表信息
	 * 
	 * @Title: gainAccessoriesPage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Map> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Goods> gainAccessoriesPage(Map param);

	/**
	 * 分页获取配件列表信息的总条数
	 * 
	 * @Title: gainAccessoriesCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainAccessoriesCountNum(Map param);

	List<Goods> gainHotByBrandName(@Param("start") int start, @Param("end") int end, @Param("brandName") String brandName);

	List<Map<String, Object>> selectBjd(@Param("brandName") String brandName, @Param("userId") String userId,@Param("regionsId") String regionsId,@Param("machineType") String machineType, @Param("userName") String
            userName);

	/**
	 * 模糊匹配商品名称
	 * 
	 * @Title: gainNameBylike
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param s
	 * @param @return 设定文件
	 * @return List<Map<String,String>> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Map<String, String>> gainNameBylike(@Param(value = "s") String s);

	
	/**
	 * 统计指定时间成交记录数量
	 * 
	 * @Title: gainDealNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainDealNum(String goodsId);
	
}