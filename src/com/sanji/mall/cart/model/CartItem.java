package com.sanji.mall.cart.model;

import com.sanji.mall.model.PriceAble;

/**
 * 
* @ClassName: CartItem
* @Description: 购物项目
* @author WuJiming wzslw_163_com
* @date 2014年11月22日 上午10:58:37
 */
public class CartItem {
	private String id;
	private PriceAble goods;
	private int quantity;
	/**
	* @Title: subTotalPrice
	* @Description: 小计
	* @return    设定文件
	* double    返回类型
	* @throws
	 */
	public double getSubTotalPrice(){
		return PriceCalculator.getInstance().calcTotalPrice(goods, quantity);
	}
	
	public CartItem(PriceAble goods, int quantity) {
		super();
		this.goods = goods;
		this.quantity = quantity;
	}
	public PriceAble getGoods() {
		return goods;
	}
	public void setGoods(PriceAble goods) {
		this.goods = goods;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getSinglePrice(){
		 return PriceCalculator.getInstance().calcSinglePrice(goods, quantity);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getType(){
		return this.goods.getClass().getSimpleName();
	}
}
