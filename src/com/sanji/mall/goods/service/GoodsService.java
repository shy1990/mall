package com.sanji.mall.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Goods;

/**
 * 商品基础信息类
 * 
 * @ClassName: GoodsService
 * @Description: TODO(商品信息类)
 * @author 田超强
 * @date 2014-10-11 下午3:46:00
 * 
 */
public interface GoodsService {
	public List<Map<String, Object>> getBjd(String brandName, String userId,String machineType);

	/**
	 * 无条件获取所有商品信息
	 * 
	 * @Title: select
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Goods> 返回类型
	 * @author 田超强
	 */
	public List<Goods> select();

	/**
	 * 根据主键id获得商品信息
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(根据主键id获得商品信息)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Goods 返回类型
	 * @author 田超强
	 */
	public Goods selectByPrimaryKey(String id);

	/**
	 * 根据编号查询商品所有信息
	 * 
	 * @Title: gainAllGoodsInfoByGoodsNum
	 * @Description: TODO(根据id查询商品所有信息)
	 * @param @param id
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

	public List<Goods> getHotSale(int page, int size, String brandName);

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
	List<Map<String, String>> gainNameBylike(String s);

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
	List<Goods> gainAllGoodsInfo2(String memberId, String goodsNum, String area,String userName);
	
	
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
  List<Goods> gainAllShoppingRush(@Param("memberId") String memberId, @Param("goodsNum") String goodsNum, @Param("area") String area,@Param("userName")String userName);

	List<Goods> gainAllShoppingRush(String memberId, String userName);

}
