package com.sanji.mall.cart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: ShoppingCart
* @Description: 新式购物车
* @author WuJiming wzslw_163_com
* @date 2014年11月22日 上午10:57:57
 */
public class ShoppingCart {
	private List<CartItem> items=new ArrayList<CartItem>();
	
	/**
	* @Title: getTotalQuantity
	* @Description: 获取数量
	* @return    设定文件
	* int    返回类型
	* @throws
	 */
	public int getTotalQuantity(){
		int totalQuantity=0;
		for (CartItem cartItem : items) {
			totalQuantity+=cartItem.getQuantity();
		}
		return totalQuantity;
	}
	/**
	* @Title: getTotalPrice
	* @Description: 获取总价格
	* @return    设定文件
	* double    返回类型
	* @throws
	 */
	public double getTotalPrice(){
		double totalPrice=.0;
		for (CartItem cartItem : items) {
			totalPrice+=cartItem.getSubTotalPrice();
		}
		return totalPrice;
	}
	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> item) {
		this.items = item;
	}
	
	
}
