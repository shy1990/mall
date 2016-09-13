package com.sanji.mall.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.accessory.dao.AccessoryMapper;
import com.sanji.mall.accessory.service.AccessoryService;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.cart.dao.CartMapper;
import com.sanji.mall.cart.dao.IGoodMapper;
import com.sanji.mall.cart.model.Cart;
import com.sanji.mall.cart.model.CartItem;
import com.sanji.mall.cart.model.ShoppingCart;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.goodsSku.dao.GoodsSkuMapper;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.GoodsSku;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.PointGoods;
import com.sanji.mall.point.dao.PointGoodsMapper;

@Service("cartService")
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {
	private static final Logger logger = Logger.getLogger(CartServiceImpl.class);
	@Resource
	private CartMapper cartMapper;
	@Autowired
	private IGoodMapper iGoodMapper;
	@Resource
	private GoodsSkuMapper goodsSkuMapper;
	@Resource
	private AccessoryMapper accessoryMapper;
	@Resource
	private AccessoryService acessoryService;
	@Resource
	private SpecialAccessoryService specialAccessoryService;
	@Resource
	private PointGoodsMapper pointGoodsMaper;
	
	@Resource
	private MemberService memberService;

	public Cart getCartById(String id) {
		return cartMapper.selectByPrimaryKey(id);
	}
	

	public void saveCart(Cart cart) {
		if (cart.getId() == null) {
			String id = ToolsUtil.getUUID();
			logger.debug("id:" + id);
			cart.setId(id);
			;
		}
		logger.debug("cart:" + cart);
		cartMapper.insertSelective(cart);
	}

	public void updateCart(Cart cart) {
		cartMapper.updateByPrimaryKeySelective(cart);
	}

	public void deleteCartById(String id) {
		cartMapper.deleteByPrimaryKey(id);
	}

/*	public List<GoodsSku> getSj(String cartId) {
		String targetId = cartMapper.selectTargetIdPrimaryKey(cartId);
		List<GoodsSku> sku = goodsSkuMapper.selectByPrimaryKey(targetId);
		logger.debug(sku);
		return sku;
	}*/

	public List<Cart> getCartsByUserId(String userId) {
		List<Cart> carts = cartMapper.selectByMemberId(userId);
		return carts;
	}

	public Accessory getPj(String cartId) {
		String targetId = cartMapper.selectTargetIdPrimaryKey(cartId);
		Accessory ac = accessoryMapper.selectByPrimaryKey(targetId);
		return ac;
	}

	public Accessory getGift(String id) {
		String targetId = cartMapper.selectTargetIdPrimaryKey(id);
		return accessoryMapper.selectByGiftPrimaryKey(targetId);
	}

	public List<Cart> checkExist(@Param("userId") String userId, @Param("targetId") String targetId, @Param("type") String type) {
		// TODO Auto-generated method stub
		return cartMapper.checkExist(userId, targetId, type);
	}

	public int getCount(String userId) {
		return cartMapper.selectCountByMemberId(userId);
	}

	public ShoppingCart getShoppingCart(String userId) {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Cart> carts = getCartsByUserId(userId);
		
		for (Cart cart : carts) {
			
			CartItem item = null;
			String type = cart.getType();
			if ("手机".equals(type)) {
				GoodsSku sku = null;
					List<GoodsSku> skus = goodsSkuMapper.selectByPrimaryKey(cart.getTargetId(),userId);
					if (skus.isEmpty()) {
						continue;
					}
					sku = skus.get(0);
					List<Accessory> mygift=new ArrayList<Accessory>();
					List<Accessory> gift = specialAccessoryService.getGift(
							sku.getGoodsId(), "保护膜", 1, 2);
					if (!gift.isEmpty()) {
						mygift.add(gift.get(0));
					}
					List<Accessory> gift1 = specialAccessoryService.getGift(
							sku.getGoodsId(), "保护壳", 1, 2);
					
					if (!gift1.isEmpty()) {
						mygift.add(gift1.get(0));
					}
						sku.setGifts(mygift);
				
				if (sku != null) {
					cart.setGoods(sku);
					item = new CartItem(sku, cart.getOrderNum());
				}

			} else if ("配件".equals(type)) {
				Accessory pj = acessoryService.getById(cart.getTargetId());
				if (pj != null) {
					cart.setGoods(pj);
					item = new CartItem(pj, cart.getOrderNum());
				}

			}else if ("积分商品".equals(type)) {
				PointGoods pj =pointGoodsMaper.selectByPrimaryKey(cart.getTargetId());
				if (pj != null) {
					cart.setGoods(pj);
					item = new CartItem(pj, cart.getOrderNum());
				}

			}
			if(item==null){
				continue;
			}
			item.setId(cart.getId());
			shoppingCart.getItems().add(item);
		}
		return shoppingCart;
	
	}


	public List<Cart> getCartsByIds(String[] ids) {
		List<Cart> carts=new ArrayList<Cart>();
		for (String id : ids) {
			if (id.equals("null")) {
				continue;
			}
			Cart cart=getCartById(id);
			if(cart==null){
				continue;
			}
			String type = cart.getType();
			if ("手机".equals(type)) {
				GoodsSku sku = null;
				try {
					sku = goodsSkuMapper.selectByPrimaryKey(cart.getTargetId(),null).get(0);
					List<Accessory> gift = specialAccessoryService.getGift(
							sku.getGoodsId(), "保护膜", 1, 2);
					List<Accessory> gift1 = specialAccessoryService.getGift(
							sku.getGoodsId(), "保护壳", 1, 2);
					if (gift==null) {
						gift=new ArrayList<Accessory>();
					}
					gift.addAll(gift1);
					if(!gift.isEmpty()){
						sku.setGifts(gift);
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (sku != null) {
					cart.setGoods(sku);
				}

			} else if ("配件".equals(type)) {
				Accessory pj = acessoryService.getById(cart.getTargetId());
				if (pj != null) {
					cart.setGoods(pj);
				}

			}
			carts.add(cart);
		}
		return carts;
	}


	public List<GoodsSku> getSj(String cartId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public int getintegral(String id) {
		
			Map<String,Object> map = iGoodMapper.selectById(id);
			BigDecimal integral = (BigDecimal) map.get("INTEGRAL");
			//int  aa  = BigDecimal
		return integral.intValue();
	}

}
