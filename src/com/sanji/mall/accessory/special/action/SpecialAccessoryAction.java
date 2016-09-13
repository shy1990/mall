/**  
 * @Title: SpecialAccessoryAction.java
 * @Package com.sanji.mall.specialparts.action
 * @author WuJiming wzslw_163_com  
 * @date 2014年10月15日 下午4:49:08
 * @version V1.0  
 */
package com.sanji.mall.accessory.special.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.accessory.special.model.SpecialAccessoryViewModel;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.cart.model.CartItem;
import com.sanji.mall.cart.service.CartService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.GoodsSku;
import com.sanji.mall.model.Members;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

/**
 * @ClassName: SpecialAccessoryAction
 * @Description: 专用配件action
 * @author WuJiming wzslw_163_com
 * @date 2014年10月15日 下午4:49:08
 * 
 */
@Namespace("/accessory")
@Action(value = "specialAccessoryAction", results = {
		@Result(name = "show", location = "/admin/accessory/special/show.jsp"),
		@Result(name = "cart", location = "/admin/cart/show.jsp"),
		@Result(name = "updateCallback", location = "/admin/accessory/special/updateCallback.jsp") })
public class SpecialAccessoryAction extends BaseAction implements
		ModelDriven<SpecialAccessoryViewModel>, ParameterAware {
	private static final long serialVersionUID = 1L;

	private SpecialAccessoryViewModel viewModel;
	private Double rootPrice = ResourceUtil.getMobilePhoneRootPrice();

	@Resource
	private SpecialAccessoryService specialAccessoryService;
	@Resource
	private CartService cartService;
	@Resource
	private MemberService memberService;
	@Resource
	private GoodsSkuService goodsSkuService;

	/**
	 * @Title: showSpecialAccessory
	 * @Description: 显示相关手机专用配件
	 * @return String    返回类型
	 */
	public String showSpecialAccessory() {

		String goodsId = this.viewModel.getGoodsId();

		// 获取赠品
		List<Accessory> bhmGift = specialAccessoryService.getGift(goodsId,
				"保护膜", 1, 2);
		List<Accessory> bhkGift = specialAccessoryService.getGift(goodsId,
				"保护壳", 1, 2);
		List<Accessory> bhm;
		List<Accessory> bhk;
		if (!bhmGift.isEmpty()) {
			request.setAttribute("bhmGift", bhmGift.get(0));
			// 获取专用配件
			bhm = specialAccessoryService.getPj(goodsId, "保护膜", 1, 5);
		} else {
			// 获取专用配件
			bhm = specialAccessoryService.getPj(goodsId, "保护膜", 1, 6);
		}
		if (!bhkGift.isEmpty()) {
			request.setAttribute("bhkGift", bhkGift.get(0));
			bhk = specialAccessoryService.getPj(goodsId, "保护壳", 1, 5);
		} else {
			bhk = specialAccessoryService.getPj(goodsId, "保护壳", 1, 6);
		}

		List<Accessory> bht = specialAccessoryService.getPj(goodsId, "保护套", 1,
				6);
		List<Accessory> dc = specialAccessoryService.getPj(goodsId, "电池", 1, 6);
		
		request.setAttribute("bhm", bhm);
		request.setAttribute("bhk", bhk);
		request.setAttribute("bht", bht);
		request.setAttribute("dc", dc);

		return "show";
	}

	public String updateSku() {
		String skuId = (String) request.getParameter("skuId");
		Integer quantity = Integer.valueOf(request.getParameter("quantity"));
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		List<GoodsSku> sku = goodsSkuService.getById(skuId,sessionInfo.getUserId());
		CartItem cartItem = new CartItem(sku.get(0), quantity);
		request.setAttribute("item", cartItem);
		return "updateCallback";
	}

	/**
	 * 
	 * @Title: getGift
	 * @Description: 获取赠品    设定文件 void    返回类型
	 * @throws
	 */
	public void getGift() {
		String goodsId = getRequest().getParameter("goodsId");
		String type = getRequest().getParameter("type");
		List<Accessory> gifts = specialAccessoryService.getGift(goodsId, type,
				1, 2);
		Json json = new Json();

		if (!gifts.isEmpty()) {
			json.setSuccess(true);
			json.setObj(gifts.get(0));
			writeJson(json);
		} else {
			json.setSuccess(false);
			writeJson(json);
		}

	}

	/**
	 * 
	 * @Title: getPj
	 * @Description: 获取配件    设定文件 void    返回类型
	 * @throws
	 */
	public void getPj() {
		String goodsId = getRequest().getParameter("goodsId");
		String type = getRequest().getParameter("type");
		List<Accessory> pjs = specialAccessoryService
				.getPj(goodsId, type, 1, 5);
		Json json = new Json();
		json.setSuccess(true);
		json.setObj(pjs);
		writeJson(json);
	}

	// ////////////////////////////////////////////////////////////////////////////
	public SpecialAccessoryViewModel getModel() {
		if (viewModel == null) {
			viewModel = new SpecialAccessoryViewModel();
		}
		return viewModel;
	}

	/**
	 * 参数解析
	 */
	public void setParameters(Map<String, String[]> params) {
		String[] strs = params.get("param");
		String param;
		if (strs != null && strs.length > 0) {
			param = strs[0];
			BeanUtils.copyProperties(
					JSON.parseObject(param, SpecialAccessoryViewModel.class),
					this.getModel());
		}
	}

	public Double getRootPrice() {
		return rootPrice;
	}

	public void setRootPrice(Double rootPrice) {
		this.rootPrice = rootPrice;
	}

}
