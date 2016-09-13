package com.sanji.mall.accessory.special.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.Accessory;

/**
 * 
 * @ClassName: SpecialPartsService
 * @Description: 专用配件Service
 * @author WuJiming wzslw_163_com
 * @date 2014年10月15日 下午5:24:09
 * 
 */
public interface SpecialAccessoryService {
	/**
	 * 
	 * @Title: getGift
	 * @Description: 获取赠品
	 * @param goodsId
	 * @param type
	 * @param start
	 * @param size
	 * @return    设定文件 List<Accessory>    返回类型
	 * @throws
	 */
	List<Accessory> getGift(String goodsId, String type, int start, int size);

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
	 * @Title: getPj
	 * @Description: 获取配件
	 * @param goodsId
	 * @param type
	 * @param page
	 * @param size
	 * @return    设定文件 List<Accessory>    返回类型
	 * @throws
	 */
	List<Accessory> getPj(String goodsId, String type, int page, int size);

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
