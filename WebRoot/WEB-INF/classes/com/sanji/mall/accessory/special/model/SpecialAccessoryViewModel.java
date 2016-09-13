package com.sanji.mall.accessory.special.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sanji.mall.common.util.ResourceUtil;

/**
 * 
 * @ClassName: SpecialAccessoryViewModel
 * @Description: 专用配件页面视图模型
 * @author WuJiming wzslw_163_com
 * @date 2014年10月18日 上午10:02:14
 */
public class SpecialAccessoryViewModel {
	
	private String goodsId;
	private String goodsName;
	private List<SkuViewModel> skuList=new ArrayList<SkuViewModel>();// 单品列表
	
	private Map<String, List<AccessoryViewModel>> accessoryMap=new HashMap<String, List<AccessoryViewModel>>(4);// key 列表名称 value
																// 配件模型list
	private Map<String, AccessoryViewModel> giftMap=new HashMap<String, AccessoryViewModel>(4);// 赠品

	/**
	 * 
	 * @Title: getPrice
	 * @Description: 价格=单品订购数量*单品价格+root价格*root数量
	 * @return    设定文件 BigDecimal    返回类型
	 * @throws
	 */
	public BigDecimal getPrice() {
		BigDecimal skusPrice = BigDecimal.ZERO;
		BigDecimal rootsPrice = BigDecimal.ZERO;
		for (SkuViewModel skuViewModel : skuList) {
			skusPrice = skusPrice.add(skuViewModel.getPrice().multiply(
					BigDecimal.valueOf(skuViewModel.getQuantity())));
			rootsPrice = rootsPrice.add(BigDecimal.valueOf(
					ResourceUtil.getMobilePhoneRootPrice()).multiply(
					BigDecimal.valueOf(skuViewModel.getRootQuantity())));
		}
		return skusPrice.add(rootsPrice);
	}
	/**
	 * 
	* @Title: getOriginalPrice
	* @Description: 原价=单品订购数量*单品原价+root价格*root数量
	* @return    设定文件
	* BigDecimal    返回类型
	* @throws
	 */
	public BigDecimal getOriginalPrice() {
		BigDecimal skusPrice = BigDecimal.ZERO;
		BigDecimal rootsPrice = BigDecimal.ZERO;
		for (SkuViewModel skuViewModel : skuList) {
			skusPrice = skusPrice.add(skuViewModel.getOriginalPrice().multiply(
					BigDecimal.valueOf(skuViewModel.getQuantity())));
			rootsPrice = rootsPrice.add(BigDecimal.valueOf(
					ResourceUtil.getMobilePhoneRootPrice()).multiply(
					BigDecimal.valueOf(skuViewModel.getRootQuantity())));
		}
		return skusPrice.add(rootsPrice);
	}


	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<SkuViewModel> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<SkuViewModel> skuList) {
		this.skuList = skuList;
	}

	/**
	 * 
	 * @Title: getAccessoryMap
	 * @Description: 配件列表 key：选中的保护膜、选中的保护壳、选中的保护套、选中的电池，保护膜列表，保护套列表、保护壳列表、电池列表
	 * @return    设定文件 Map<String,List<AccessoryViewModel>>    返回类型
	 * @throws
	 */
	public Map<String, List<AccessoryViewModel>> getAccessoryMap() {
		return accessoryMap;
	}

	public void setAccessoryMap(
			Map<String, List<AccessoryViewModel>> accessoryMap) {
		this.accessoryMap = accessoryMap;
	}

	/**
	 * 
	 * @Title: getGiftMap
	 * @Description: 赠品;key:保护膜，保护壳，保护套，电池
	 * @return    设定文件 Map<String,AccessoryViewModel>    返回类型
	 * @throws
	 */
	public Map<String, AccessoryViewModel> getGiftMap() {
		return giftMap;
	}

	public void setGiftMap(Map<String, AccessoryViewModel> giftMap) {
		this.giftMap = giftMap;
	}
	/**
	* @Title: getQuantity
	* @Description: 数量
	* @return    设定文件
	* Integer    返回类型
	* @throws
	 */
	public Integer getQuantity() {
		Integer quantity=0;
		for (SkuViewModel skuViewModel : skuList) {
			quantity+=skuViewModel.getQuantity();
		}
		return quantity;
	}

}
