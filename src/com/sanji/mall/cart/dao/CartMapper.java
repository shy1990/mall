package com.sanji.mall.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.cart.model.Cart;

public interface CartMapper {
	int deleteByPrimaryKey(String id);

	int insert(Cart record);

	int insertSelective(Cart record);
	
	Cart selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Cart record);

	int updateByPrimaryKey(Cart record);

	String selectTargetIdPrimaryKey(@Param("id") String id);

	List<Cart> selectByMemberId(String userId);

	/**
	 * 根据用户id和商品id查找指定类型的商品是否已存在
	 * 
	 * @Title: checkExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param targetId
	 * @param @param userId
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Cart> checkExist(@Param("userId") String userId, @Param("targetId") String targetId, @Param("type") String type);
	/**
	 * 
	* @Title: clearnByMemberId
	* @Description: 清空购物车
	* @param memberId    设定文件
	* void    返回类型
	* @throws
	 */
	void clearnByMemberId(String memberId);
	/**
	 * 
	* @Title: selectCountByMemberId
	* @Description: 查询购物车数量
	* @param userId
	* @return    设定文件
	* int    返回类型
	* @throws
	 */
	int selectCountByMemberId(String userId);
}