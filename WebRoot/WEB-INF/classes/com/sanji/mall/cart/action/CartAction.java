package com.sanji.mall.cart.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.accessory.service.AccessoryService;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.cart.model.Cart;
import com.sanji.mall.cart.model.CartItem;
import com.sanji.mall.cart.model.ShoppingCart;
import com.sanji.mall.cart.service.CartService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.point.service.PointService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 
 * @ClassName: CartAction
 * @Description: 购物车action
 * @author WuJiming wzslw_163_com
 * @date 2014年10月24日 上午11:14:33
 */

@Namespace("/cart")
@Action(value = "cartAction", results = { @Result(name = "cartShow", type = "redirect", location = "/cart/main.html"), @Result(name = "show", location = "/admin/cart/show.jsp"),
		@Result(name = "updateCallback", location = "/admin/cart/updateCallback.jsp") })
public class CartAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CartAction.class);
	private String cartId;// 购物车id
	private Integer amount;// 数量
	private String targetId;// 收藏的目标id
	private String param;// 接受json字符串
	// TODO 积分系数未设置
	private float pointCoefficient = 1;
	@Resource
	private CartService cartService;

	@Resource
	private PointService pointService;
	@Resource
	private GoodsSkuService skuService;
	@Resource
	private AccessoryService acessoryService;
	@Resource
	private SpecialAccessoryService specialAccessoryService;

	/**
	 * 
	 * @Title: show
	 * @Description: 显示购物中心
	 * @return    设定文件 String    返回类型
	 * @throws
	 */
	public String show() {
		ShoppingCart shooppingCart = getCart();
		getRequest().setAttribute("shoppingCart", shooppingCart);
		return "show";
	}

	/**
	 * 
	 * @Title: remove
	 * @Description: 删除购物车 void    返回类型
	 * @throws
	 */
	public void remove() {
		cartService.deleteCartById(getCartId());
		Json json = new Json();
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 
	 * @Title: addSku2Cart
	 * @Description: 添加单品到购物车
	 * @return    设定文件
	 * @throws
	 */
	public void addSku2Cart() {
		Map<String, Integer> dataMap = JSON.parseObject(param, Map.class);
		logger.debug(dataMap);
		for (String id : dataMap.keySet()) {
			this.targetId = id;
			this.amount = dataMap.get(id);
			// int rootNumber = Integer.parseInt(dataMap.get(id).split(",")[1]);
			int rootNumber = 0;
			this.add_cart("手机", rootNumber);
		}
		writeJson("{success:true}");
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * @Title: addJfSpCart
	 * @Description: 添加积分商品到购物车
	 * @return    设定文件
	 * @throws
	 */
	public void addJfSp2Cart() throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, String> dataMap = JSON.parseObject(param, Map.class);
		logger.debug(dataMap);
		String cartId = null;
		boolean first = true;
		SessionInfo sessionInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		Integer point = pointService.getPoint(sessionInfo.getUserId());
		ShoppingCart shooppingCart = getCart();
		int sumCart = 0;

		for (CartItem ci : shooppingCart.getItems()) {

			JSONObject jsonObject = new JSONObject().parseObject(JSON.toJSONString(ci.getGoods()));
			if (jsonObject.containsKey("name")) {
				int integral = Integer.parseInt(jsonObject.get("integral").toString());
				int num = ci.getQuantity();
				sumCart = sumCart + integral * num;
			}

		}

		int sumJF = 0;
		for (String id : dataMap.keySet()) {
			this.targetId = id;
			this.amount = Integer.parseInt(dataMap.get(id));
			int num = this.amount;
			int integral = cartService.getintegral(this.targetId);
			sumJF = sumJF + integral * num;
		}
		// // System.out.println(sumJF);
		// // System.out.println(sumCart);
		if (sumJF + sumCart <= point) {
			for (String id : dataMap.keySet()) {
				this.targetId = id;
				this.amount = Integer.parseInt(dataMap.get(id));
				this.targetId = id;
				this.amount = Integer.parseInt(dataMap.get(id));
				String addId = this.add_cart("积分商品", 0);
				if (addId != null) {
					if (first) {
						cartId = addId;
						first = false;
					} else {
						cartId += "," + addId;
					}
				}
			}

			writeJson(cartId);
		} else {

			writeJson(false);

		}

	}

	/**
	 * 
	 * @Title: addGift2Cart
	 * @Description: 添加赠品到购物车
	 * @return    设定文件
	 * @throws
	 */
	public void addGift2Cart() {
		Map<String, String> dataMap = JSON.parseObject(param, Map.class);
		logger.debug(dataMap);
		for (String id : dataMap.keySet()) {
			this.targetId = id;
			this.amount = Integer.parseInt(dataMap.get(id));
			this.add_cart("赠品", 0);
		}
		writeJson("{success:true}");
	}

	public ShoppingCart getCart() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		ShoppingCart shoppingCart = null;
		try {
			shoppingCart = cartService.getShoppingCart(sessionInfo.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shoppingCart;
	}

	public void getCount() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		int count = 0;

		List<Cart> carts = cartService.getCartsByUserId(sessionInfo.getUserId());
		for (Cart cart : carts) {
			count += cart.getOrderNum();
			if ("手机".equals(cart.getType())) {
				count += cart.getOrderNum();
			}
		}
		writeJson(count);
	}

	/**
	 * @Title: addPj2Cart
	 * @Description: 添加配件到购物车
	 * @return    设定文件
	 * @throws
	 */
	public void addPj2Cart() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> dataMap = JSON.parseObject(param, Map.class);
		logger.debug(dataMap);
		String cartId = null;
		boolean first = true;
		for (String id : dataMap.keySet()) {
			this.targetId = id;
			this.amount = dataMap.get(id);
			String addId = this.add_cart("配件", 0);
			if (addId != null) {
				if (first) {
					cartId = addId;
					first = false;
				} else {
					cartId += "," + addId;
				}
			}

		}
		writeJson(cartId);
	}

	/**
	 * 添加配件到购物车 // 需要添加的目标id和数量
	 * 
	 * @Title: addAccessory_cart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String addAccessory_cart() {
		try {
			add_cart("配件", 0);
			return "cartShow";
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
	}

	/**
	 * 添加商品到购物车通用方法
	 * 
	 * @Title: add_cart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param type 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String add_cart(String type, int rootNumber) {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		Cart cart = null;
		// 插入前检测是否已经存在此商品。如果存在就改变此商品的数量
		List<Cart> carts = cartService.checkExist(sessionInfo.getUserId(), getTargetId(), type);
		String id = null;
		if (carts != null && carts.size() <= 0) {
			cart = new Cart();
			id = ToolsUtil.getUUID();
			cart.setId(id);
			cart.setType(type);
			cart.setTargetId(getTargetId());
			cart.setGoodsRootNum(rootNumber);
			cart.setOrderNum(getAmount());
			cart.setMemberId(sessionInfo.getUserId());
			cart.setCreateIp(request.getRemoteAddr());
			cart.setCreateTime(new Date());
			cart.setMemberType("B2B");
			cartService.saveCart(cart);
		} else {
			cart = carts.get(0);
			id = cart.getId();
			cart.setOrderNum(cart.getOrderNum() + getAmount());
			cartService.updateCart(cart);
		}
		return id;
	}

	/**
	 * 
	 * @Title: updateAmount
	 * @Description: 更新购物车数量    设定文件 void    返回类型
	 * @throws
	 */
	public String updateCart() {
		HttpServletRequest req = getRequest();
		Cart cart = cartService.getCartById(getCartId());
		if (cart != null) {
			req.setAttribute("cart", cart);
			cart.setOrderNum(getAmount());
			cartService.updateCart(cart);
			req.setAttribute("shoppingCart", getCart());
		} else {
			req.setAttribute("error", "没有找到相关商品。");
		}
		return "updateCallback";
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public float getPointCoefficient() {
		return pointCoefficient;
	}

	public void setPointCoefficient(float pointCoefficient) {
		this.pointCoefficient = pointCoefficient;
	}

}
