package com.sanji.mall.accessory.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Accessory;

public interface AccessoryMapper {
	int deleteByPrimaryKey(String id);

	int insert(Accessory record);

	int insertSelective(Accessory record);

	Accessory selectByPrimaryKey(@Param("id") String id);

	int updateByPrimaryKeySelective(Accessory record);

	int updateByPrimaryKeyWithBLOBs(Accessory record);

	int updateByPrimaryKey(Accessory record);

	List<Accessory> selectGiftByGoodsIdAndType(@Param("goodsId") String goodsId, @Param("type") String type, @Param("start") int start, @Param("end") int end);

	List<Accessory> selectByGoodsIdAndType(@Param("goodsId") String goodsId, @Param("type") String type, @Param("start") int start, @Param("end") int end);

	/**
	 * 根据配件编号获取配件详情
	 * 
	 * @Title: gainDetailByNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param accessoriesNum
	 * @param @return 设定文件
	 * @return List<Accessory> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Accessory> gainDetailByNum(String accessoriesNum);

	/**
	 * 根据配件规格代码获取配件详情
	 * 
	 * @Title: gainDetailBySpecCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param specCode
	 * @param @return 设定文件
	 * @return List<Accessory> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Accessory> gainDetailBySpecCode(String specCode);

	Accessory selectByGiftPrimaryKey(@Param("giftId") String giftId);

	/**
	 * 根据配件id更改配件点击数
	 * 
	 * @Title: upClickRate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int upClickRate(String id);

	/**
	 * 
	 * @Title: selectComm
	 * @Description: 查询通用配件
	 * @param type
	 * @param start
	 * @param end
	 * @return    设定文件 List<Accessory>    返回类型
	 * @throws
	 */
	List<Accessory> selectComm(@Param("type") String type, @Param("start") int start, @Param("end") int end);

	/**
	 * 根据赠品的ID查询配件信息
	 * 
	 * @Title: selectByGiftId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Accessory 返回类型
	 * @author ZhouZhangbao
	 */
	public Accessory selectByGiftId(String id);

	/**
	 * 根据 配件编号查找同一系列的 配件 排除不需要查的那个
	 * 
	 * @Title: gainByNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param num
	 * @param @param id
	 * @param @return 设定文件
	 * @return Map 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Map> gainByNum(@Param("num") String num, @Param("id") String id);

}