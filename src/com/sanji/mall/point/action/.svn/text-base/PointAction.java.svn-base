package com.sanji.mall.point.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sanji.mall.accessory.service.AccessoryService;
import com.sanji.mall.cart.model.Cart;
import com.sanji.mall.cart.model.PriceCalculator;
import com.sanji.mall.cart.service.CartService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.model.PointGoods;
import com.sanji.mall.model.PriceAble;
import com.sanji.mall.point.service.PointService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 
* @ClassName: PointAction
* @Description: 积分action
* @author WuJiming wzslw_163_com
* @date 2014年10月28日 上午10:49:31
 */
@Namespace("/point")
@Action(value = "pointAction", results = { @Result(name = "show", location = "/admin/point/show.jsp") })
public class PointAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(PointAction.class);
	
	@Resource
	private PointService pointService;
	@Resource
	private CartService cartService;
	@Resource
	private GoodsSkuService skuService;
	@Resource
	private AccessoryService acessoryService;

	// TODO 积分系数未设置
	private float pointCoefficient = 1;
	
	public String show(){
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil
				.getSessionInfoName());
		Integer point=pointService.getPoint(sessionInfo.getUserId());
		Double thisPoint=.0;
		String cartIdsStr = getRequest().getParameter("cartIds");
		if (cartIdsStr!=null) {
			String[] ids = cartIdsStr.split(",");
			List<Cart> Carts = cartService.getCartsByIds(ids);
			for (Cart cart : Carts) {
				if("积分商品".equals(cart.getType())){
					continue;
				}
				thisPoint+=PriceCalculator.getInstance().calcPoint((PriceAble)cart.getGoods(), cart.getOrderNum());
			}
		}
		String cartIds = getRequest().getParameter("cartIds");
		getRequest().setAttribute("cartIds", cartIds);
		getRequest().setAttribute("point", point.toString()+","+thisPoint);
		return "show";
	}
	
	public void getPoint(){
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil
				.getSessionInfoName());
		Integer point=pointService.getPoint(sessionInfo.getUserId());
		writeJson(point);
	}
	
	public void getPointGoods(){
		String type=getRequest().getParameter("type");
		type=type==null?"":type;
		List<PointGoods> pointGoods = pointService.getPointGoods(type, 1, 100);
		Json json=new Json();
		json.setSuccess(true);
		json.setObj(pointGoods);
		writeJson(json);
	}

	public float getPointCoefficient() {
		return pointCoefficient;
	}

	public void setPointCoefficient(float pointCoefficient) {
		this.pointCoefficient = pointCoefficient;
	}
	
}
