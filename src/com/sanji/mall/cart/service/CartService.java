package com.sanji.mall.cart.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.cart.model.Cart;
import com.sanji.mall.cart.model.CartItem;
import com.sanji.mall.cart.model.ShoppingCart;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.GoodsSku;

/**
 * 
 * @ClassName: CartService
 * @Description: 购物车服务接口
 * @author WuJiming wzslw_163_com
 * @date 2014年10月17日 上午9:09:56
 */
public interface CartService {
	/**
	 * 
	 * @Title: getCartById
	 * @Description: 通过id获取购物车
	 * @param id
	 * @return    设定文件 Cart    返回类型
	 * @throws
	 */
	public Cart getCartById(String id);
	public List<Cart> getCartsByIds(String[] ids);

	/**
	 * 
	 * @Title: saveCart
	 * @Description: 保存购物车
	 * @param cart
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void saveCart(Cart cart);

	/**
	 * 
	 * @Title: updateCart
	 * @Description: 更新购物车
	 * @param cart
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void updateCart(Cart cart);

	/**
	 * 
	 * @Title: deleteCartById
	 * @Description: 删除一条购物车记录
	 * @param id
	 *                设定文件 void    返回类型
	 * @throws
	 */
	public void deleteCartById(String id);

	/**
	 * @Title: getSj
	 * @Description: 获取用户购物车中的手机
	 * @param cartId
	 * @return    设定文件 GoodsSku    返回类型
	 * @throws
	 */
	public List<GoodsSku> getSj(String cartId);

	/**
	 * 
	 * @Title: getCartsByUserId
	 * @Description: 查询用户所有购物记录
	 * @param userId
	 * @return    设定文件 List<Cart>    返回类型
	 * @throws
	 */
	public List<Cart> getCartsByUserId(String userId);

	/**
	 * 
	 * @Title: getPj
	 * @Description: 获取配件
	 * @param cartId
	 * @return    设定文件 Accessory    返回类型
	 * @throws
	 */
	/*public Accessory getPj(String cartId);*/

	/**
	 * 
	 * @Title: getBhmGift
	 * @Description: 获取购物车保护膜赠品
	 * @param id
	 * @return    设定文件 Accessory    返回类型
	 * @throws
	 */
	public Accessory getGift(String id);

	/**
	 * 根据用户id和商品id查找指定类型的商品是否已存在
	 * 
	 * @Title: checkExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param targetId
	 * @param @param userId
	 * @param @param type
	 * @param @return 设定文件
	 * @return List<Cart> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Cart> checkExist(@Param("userId") String userId, @Param("targetId") String targetId, @Param("type") String type);
	
	int getCount(String userId);
	
	int getintegral(String id);
	
	public ShoppingCart getShoppingCart(String userId);
}
