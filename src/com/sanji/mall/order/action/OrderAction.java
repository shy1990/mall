package com.sanji.mall.order.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.accessory.service.AccessoryService;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.cart.model.Cart;
import com.sanji.mall.cart.model.CartItem;
import com.sanji.mall.cart.model.PriceCalculator;
import com.sanji.mall.cart.model.ShoppingCart;
import com.sanji.mall.cart.service.CartService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.EcErpUtil;
import com.sanji.mall.common.util.ExpressUtil;
import com.sanji.mall.common.util.JsonUtil;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.goods.dao.GoodsMapper;
import com.sanji.mall.goodsSku.dao.GoodsSkuMapper;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.DlyCorp;
import com.sanji.mall.model.Evaluate;
import com.sanji.mall.model.Goods;
import com.sanji.mall.model.GoodsSku;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.model.PayDeal;
import com.sanji.mall.model.PointGoods;
import com.sanji.mall.order.service.DlyCorpService;
import com.sanji.mall.order.service.EvaluateService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pay.service.PayService;
import com.sanji.mall.point.service.PointService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.qb.service.HbService;

/**
 * 订单相关action类
 * 
 * @ClassName: OrderAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-1 上午10:19:18
 * 
 */
@Namespace("/order")
@Action(value = "orderAction", results = { @Result(name = "orders", location = "/admin/myCenter/order/orders.jsp"),
		@Result(name = "detail", location = "/admin/myCenter/order/detail.jsp"),
		@Result(name = "evaluate", location = "/admin/myCenter/evaluate/evaluate.jsp"),
		@Result(name = "nort", location = "/admin/myCenter/evaluate/noReturn.jsp"),
		@Result(name = "queren", location = "/admin/order/queren.jsp"),
		@Result(name = "success", type = "redirect", location = "/order/${id}/success.html"),
		@Result(name = "success_page", type = "chain", location = "/pay/yeePayAction!toPayByOrderId.action"),
		@Result(name = "fail", location = "/admin/order/fail.jsp"),
		@Result(name = "cerr", location = "/admin/afterSale/errMsg.jsp"),
		@Result(name = "csuccess", type = "redirect", location = "/th/${orderNum}/success.html"),
		@Result(name = "refund", location = "/admin/afterSale/refund.jsp"),
		@Result(name = "refundSales", type = "redirect", location = "/th/${id}/add.html"),
		@Result(name = "orderState", location = "/admin/order/orderState.jsp") })
public class OrderAction extends BaseAction implements ModelDriven<Order> {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OrderAction.class);
	// TODO 积分系数未设置
	private float pointCoefficient = 0.1f;
	@Resource
	private CartService cartService;
	@Resource
	private GoodsSkuService skuService;
	@Resource
	private AccessoryService acessoryService;
	@Resource
	private PointService pointService;
	@Resource
	private MemberService memberService;
	@Resource
	private SpecialAccessoryService specialAccessoryService;
	@Resource
	private EvaluateService evaluateService;

	@Resource
	private GoodsSkuMapper goodsSkuMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private HbService hbService;

	private Order order;

	private Evaluate evaluate;

	public Evaluate getModel1() {
		// TODO Auto-generated method stub
		if (evaluate == null)
			evaluate = new Evaluate();
		return evaluate;
	}

	public Order getModel() {
		if (order == null) {
			order = new Order();
		}
		return order;
	}

	@Resource
	private OrderService orderService;

	@Resource
	private DlyCorpService dlyCorpService;
	@Resource
	private PayService payService;
	
	@Resource
	private AdminService adminService;

	private int selected = 0;// 0全部订单//1已付款//2代付款//3待发货

	private String searchParam;// 搜索条件搜索订单//模糊匹配名称 准确匹配订单编号
	

	/**
	 * 手动推送漏单
	 * 
	 * @Title: doNotNeedSession_rePushOrder @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param 设定文件 @return void 返回类型 @author
	 * Songbaozhen @throws
	 */
	public void doNotNeedSession_rePushOrder() {
		Json json = new Json();
		try {
			Order o = orderService.gainOrderALLByID(order.getId());
			
			Members m = memberService.gainMembersDetailById(o.getMemberId());
			Admin admin = adminService.getAdminById(m.getAdminId());
			
			Map<String, Object> map = EcErpUtil.OrderAddNew(o, o.getUserName(), "", "", "", "", o.getPayMent(),admin!=null?admin.getTruename():"没有对应业务名称");
			if (null != map.get("tid") && !"".equals(map.get("tid"))) {
				order.setEcerpNo(map.get("tid") + "");
				order.setEcerpCreated(map.get("created") + "");
				orderService.updateEcerpById(order);
				// 更库存
				PayDeal payDeal = new PayDeal();
				payDeal.setId(ToolsUtil.getUUID());
				payDeal.setOrderId(order.getId());
				payService.updateInventory(payDeal);
				json.setMsg("订单:"+o.getOrderNum()+" 推送成功");
				//服务站经理存在就将此订单向APP推送
				if(admin != null && !"".equals(admin)){
					String mobile2 = admin.getMobilephone();
					MsgUtil.MsgInfoXDApp(mobile2, o, m);
				}
				json.setSuccess(true);
			} else {
				order.setEcerpError(map.get("ERROR") + "");
				order.setPayMent(o.getPayMent());
				orderService.updateEcerpById(order);
				json.setMsg(map.get("ERROR") + "订单推异常,请联系技术人员!");
				json.setSuccess(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("doNotNeedSession_rePushOrder() occur error. ", e);
			json.setMsg("订单推异常,请联系技术人员!");
			json.setSuccess(false);
		}
		super.writeJson(json);
	}

	// 提交订单去付款
	public String success() {
	    Json j = new Json();
		try {
		    
		    //检测是否有抢购商品//判断是否还可以买抢购商品

			// 在这个地方切入红包
			String hbNo = order.getHbNo();
			System.out.println(hbNo);
			
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			getRequest().setAttribute("userInfo", memberService.gainMembersDetailById(sessionInfo.getUserId()));
			Order order = (Order) getSession().get("order");
			BigDecimal zpoint = (BigDecimal) getSession().get("zpoint");
			// System.out.println(zpoint);
			order.setOrderPoints(zpoint);
			BigDecimal costPoint = (BigDecimal) getSession().get("costPosin");
			Members member = memberService.getMemberById(sessionInfo.getUserId());

			if (hbNo != null && !"".equals(hbNo)) {
				
				// TODO 调用远程红包//如果成功就继续执行不成功则返回到错误信息提示页面
				Map<String, Object> hbMap = (Map<String, Object>) request.getSession().getAttribute(hbNo);
				if (hbMap != null) {
					// 获取使用后的红包金额
					j = hbService.useHb(hbNo, sessionInfo.getUserId());
					if (j.getSuccess()) {// 成功
						request.getSession().removeAttribute(hbNo);
						order.setHbNum(new BigDecimal(hbMap.get("amount").toString()));
						order.setHbNo(hbNo);
					} else {// 失败
						request.setAttribute("json", j);
						return "orderState";
					}
				} else {
					// 已经被使用
					j.setMsg("红包已被使用！");
					request.setAttribute("json", j);
					return "orderState";
				}
			} else {
				order.setHbNo(null);
				order.setHbNum(null);
			}

			//检查库存
			 List<OrderItems> ois = order.getOrderItemss();
	            if(ois!=null&&ois.size()>0){
	                String goodsId = "";
	                int buyNum = 0;
	                for(OrderItems oi : ois){
	                    if(!oi.getTargetType().equals("sku")){
	                        break;
	                    }
	                    GoodsSku sku = skuService.getById(oi.getTargetId(), sessionInfo.getUserId()).get(0);
	                    if(null!=order.getRemark()&&order.getRemark().indexOf("活动商品")>-1){
	                        goodsId = sku.getGoodsId();
	                        buyNum+=oi.getNums().intValue();
	                    }
	                    System.out.println(oi.getNums()+"      "+sku.getStock());
	                    int inum = oi.getNums().intValue();
	                    int snum = sku.getStock().intValue();
	                    
	                    if(inum>snum){//fuck脑抽判断错了。。。既然不用转换成int比较的。。。
	                        j.setMsg("商品 "+sku.getGoodsName()+"库存不足！");
	                        request.setAttribute("json", j);
	                        return "orderState";
	                    }
	                }
	                if(null!=order.getRemark()&&order.getRemark().indexOf("活动商品")>-1){
                        buyNum += orderService.gainYetBuyNum(goodsId, null);//已经购买的台数
                        order.setRemark(order.getRemark()+"  "+buyNum);
                    }
	            }
			
			// 更新积分
			// pointService.updatePoint(member.getId(), costPoint);
			orderService.save(order);// 保存订单信息
			String address = getModel().getAddress();
			String shipName = getModel().getShipName();
			order.setAddress(address);
			order.setShipName(shipName);
			orderService.updateShipAdd(order);
			String[] cartIds = (String[]) getRequest().getSession().getAttribute("cartIds");
			if(cartIds!=null&&cartIds.length>0){
    			for (String string : cartIds) {
    				cartService.deleteCartById(string);
    			}
			}
			// getRequest().getParameterMap().put("orderId", order.getId());
			session.put("orderId",order.getId());

			getRequest().getRequestDispatcher("/pay/yeePayAction!toPayByOrderId.action").forward(getRequest(),
					getResopnse());
		} catch (Exception e) {
			// System.out.println("提交订单异常");
			e.printStackTrace();
		}
		return null;
	}
	
	   // 检查是否达到了活动商品提醒
    public void checkHdOrderMsg() {
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
           // getRequest().setAttribute("userInfo", memberService.gainMembersDetailById(sessionInfo.getUserId()));
            Order order = (Order) getSession().get("order");
            List<OrderItems> ois = order.getOrderItemss();
            if(ois!=null&&ois.size()>0){
                String skuId = ois.get(0).getTargetId();
                GoodsSku sku = skuService.getById(skuId, sessionInfo.getUserId()).get(0);
                String goodsId = sku.getGoodsId();
                System.out.println("商品id："+goodsId);
                //获取数量
                int buyNum = orderService.gainYetBuyNum(goodsId, null);//已经购买的台数
                for(OrderItems oi: ois){
                    buyNum+=oi.getNums().intValue();
                }
               
                if(buyNum<201){
                    order.setRemark(order.getRemark()+" 当天发货 ");
                    json.setSuccess(true);
                    writeJson(json);
                    return;
                }
                
                if(buyNum>200&&buyNum<=600){
                    order.setRemark(order.getRemark()+" 次日发货 ");
                    json.setMsg("您拍下的商品次日发货!");
                    json.setObj(true);//标示库存不足
                    writeJson(json);
                    return;
                }
                
                if(buyNum>600){
                    json.setMsg("库存不足");
                    json.setObj(false);//标示库存不足
                    writeJson(json);
                    return;
                }
                getSession().put("order", order);
            }
            
        } catch (Exception e) {
            // System.out.println("提交订单异常");
            e.printStackTrace();
        }
    }

	/**
	 * 去付款
	 * 
	 * @Title: goPay @Description: TODO(这里用一句话描述这个方法的作用) @param 设定文件 @return
	 * void 返回类型 @author 田超强 @throws
	 */
	public void goPay() {
		try {
			getRequest().getParameterMap().put("orderId", order.getId());
			// System.out.println("开始去付款+++++++++++++++++++++++++++++++++++++++++++++++++++");
			getRequest().getRequestDispatcher("/pay/yeePayAction!toPayByOrderId.action").forward(getRequest(),
					getResopnse());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 生成订单号加锁，防止重复
	 * 
	 * @Title: getOrderNum @Description: TODO(这里用一句话描述这个方法的作用) @param @return
	 * 设定文件 @return String 返回类型 @author 田超强 @throws
	 */
	public String getOrderNum() {
//		byte[] lock = new byte[0]; 
		synchronized(this) {  
		
		String orderNum = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
		
		return orderNum;
		}
	}

	/**
	 * 
	 * @Title: add @Description: 添加订单 @return    设定文件 String    返回类型 @throws
	 */
	public String add() {
		String cartIdsStr = getRequest().getParameter("cartIds");
		String[] cartIds = cartIdsStr.split(",");
		getRequest().getSession().setAttribute("cartIds", cartIds);

		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		ShoppingCart shooppingCart = cartService.getShoppingCart(sessionInfo.getUserId());
		shooppingCart = filterShoppingCart(shooppingCart, cartIds);
		getRequest().setAttribute("shoppingCart", shooppingCart);
		Members user = memberService.gainMembersDetailById(sessionInfo.getUserId());
		getRequest().setAttribute("userInfo", user);
		try {
			Members member = memberService.getMemberById(sessionInfo.getUserId());
			String orderId = ToolsUtil.getUUID();
			order.setId(orderId);
			order.setAddress(member.getAddress());
			order.setSourceType("0");
			order.setShipName(member.getTruename());
			order.setArea(member.getArea());
			order.setShipZip(member.getZip());
			order.setShipTel(member.getMobile());
			order.setShipEmail(member.getEmail());
			order.setProvince(member.getProvince());
			order.setCity(member.getCity());
			order.setAddress(member.getAddress());
			order.setMemberType("1");
			order.setMemberId(member.getId());

			List<Cart> carts = cartService.getCartsByIds(cartIds);
			List<OrderItems> items = new ArrayList<OrderItems>();
			BigDecimal totalCost = BigDecimal.ZERO;
			BigDecimal point = BigDecimal.ZERO;
			Double rootPrice = ResourceUtil.getMobilePhoneRootPrice();
			int costPoint = 0;
			int skuCount = 0;
			BigDecimal pjCost = BigDecimal.ZERO;
			PriceCalculator prc = PriceCalculator.getInstance();
			for (Cart cart : carts) {
				OrderItems item = new OrderItems();
				item.setId(ToolsUtil.getUUID());
				String type = cart.getType();
				if ("手机".equals(type)) {
					GoodsSku sku = skuService.getById(cart.getTargetId(), sessionInfo.getUserId()).get(0);
					if (sku != null) {
						item.setGoodsSku(sku);
						skuCount += Integer.valueOf(cart.getOrderNum());
						item.setTargetType("sku");
						item.setTargetId(sku.getId());
						item.setMarketbalePrice(BigDecimal.valueOf(prc.calcSinglePrice(sku, cart.getOrderNum())));
						item.setAmount(BigDecimal.valueOf(
								prc.calcTotalPrice(sku, cart.getOrderNum()) + rootPrice * cart.getGoodsRootNum()));
						item.setRootNum(BigDecimal.valueOf(cart.getGoodsRootNum()));
						item.setDealPrice(BigDecimal.valueOf(prc.calcSinglePrice(sku, cart.getOrderNum())));
						// 添加贈品

						List<Accessory> mygift = new ArrayList<Accessory>();
						List<Accessory> gifts = specialAccessoryService.getGift(sku.getGoodsId(), "保护膜", 1, 2);
						List<Accessory> gifts1 = specialAccessoryService.getGift(sku.getGoodsId(), "保护壳", 1, 2);
						if (!gifts.isEmpty()) {
							mygift.add(gifts.get(0));
						}
						if (!gifts1.isEmpty()) {
							mygift.add(gifts1.get(0));
						}
						for (Accessory gift : mygift) {
							OrderItems giftItem = new OrderItems();
							giftItem.setAccessory(gift);
							giftItem.setId(ToolsUtil.getUUID());
							giftItem.setIsgift("true");
							giftItem.setTargetType("gift");
							giftItem.setTargetId(gift.getId());
							giftItem.setMarketbalePrice(gift.getPrice());
							giftItem.setAmount(gift.getPrice().multiply(BigDecimal.valueOf(cart.getOrderNum())));
							giftItem.setRootNum(BigDecimal.valueOf(cart.getGoodsRootNum()));
							giftItem.setDealPrice(gift.getPrice());
							giftItem.setOrderId(orderId);
							giftItem.setNums(BigDecimal.valueOf(cart.getOrderNum()));

							totalCost = totalCost.add(giftItem.getAmount());
							items.add(giftItem);
						}
					} else {
						continue;
					}
				} else if ("配件".equals(type) && cart.getOrderNum() > 0) {
					Accessory pj = acessoryService.getById(cart.getTargetId());
					if (pj != null) {
						item.setAccessory(pj);
						item.setTargetType("accessories");
						item.setTargetId(pj.getId());
						item.setMarketbalePrice(pj.getPrice());
						item.setAmount(pj.getPrice().multiply(BigDecimal.valueOf(cart.getOrderNum())));
						item.setRootNum(BigDecimal.valueOf(cart.getGoodsRootNum()));
						item.setDealPrice(pj.getPrice());
						pjCost = pjCost.add(item.getAmount());
					} else {
						continue;
					}
				} else if ("积分商品".equals(type) && cart.getOrderNum() > 0) {
					PointGoods pointGoods = pointService.getPointGoods(cart.getTargetId());
					if (pointGoods != null) {
						item.setTargetType("point");
						item.setTargetId(pointGoods.getId());
						item.setMarketbalePrice(BigDecimal.ZERO);
						item.setAmount(BigDecimal.ZERO);
						item.setRootNum(BigDecimal.ZERO);
						item.setDealPrice(BigDecimal.ZERO);
						costPoint += cart.getOrderNum() * pointGoods.getIntegral().intValue();
					} else {
						continue;
					}
				}
				item.setNums(BigDecimal.valueOf(cart.getOrderNum()));
				item.setOrderId(orderId);
				// 计算获得
				totalCost = totalCost.add(item.getAmount());
				items.add(item);
				// cartService.deleteCartById(cart.getId());
			}
			if (skuCount >= 1 || pjCost.doubleValue() >= 300) {
				order.setCarriage(BigDecimal.valueOf(0));
			} else {
				order.setCarriage(BigDecimal.valueOf(6));
				// totalCost = totalCost.add(BigDecimal.valueOf(10));
				totalCost = totalCost.add(BigDecimal.valueOf(6));
			}
			request.getSession().setAttribute("zpoint", new BigDecimal(costPoint));
			order.setOrderPoints(new BigDecimal(costPoint));
			order.setTotalCost(totalCost);
			order.setOrderPoints(BigDecimal.valueOf(Math.floor(point.doubleValue())));
			order.setOrderNum(getOrderNum());
			order.setOrderItemss(items);

			point = point.add(totalCost.multiply(BigDecimal.valueOf(pointCoefficient)));
			point = BigDecimal.valueOf(Math.floor(point.doubleValue()));
			// 消费积分
			BigDecimal memberPoint = member.getPoint();
			if (memberPoint == null) {
				memberPoint = BigDecimal.ZERO;
			}
			point = memberPoint.subtract(BigDecimal.valueOf(costPoint)).add(point);

			if (!items.isEmpty()) {
				request.getSession().setAttribute("order", order);
				request.getSession().setAttribute("costPosin", point);

				// orderService.save(order);
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		// return "success";

		return "queren";
	}
	
	
	//活动页添加订单
	   /**
     * 
     * @Title: add @Description: 添加订单 @return    设定文件 String    返回类型 @throws
     */
    public String hdadd() {
        
        System.out.println(order.getHdSkus());
        
        System.out.println(getSearchParam());
        
        //获取购买的活动商品以及数量 hdSkus=[{"goodsId":xxxx,skus:[{skuId:xxxx,num:xx},{skuId:xxxx,num:xx}]}]
        
        //获取此用户可以购买的活动商品
        
        //遍历检测并且放到items里面
        
       // String cartIdsStr = getRequest().getParameter("cartIds");
       // String[] cartIds = cartIdsStr.split(",");
       // getRequest().getSession().setAttribute("cartIds", cartIds);

        SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
       // ShoppingCart shooppingCart = cartService.getShoppingCart(sessionInfo.getUserId());
       // shooppingCart = filterShoppingCart(shooppingCart, cartIds);
       // getRequest().setAttribute("shoppingCart", shooppingCart);
        Members user = memberService.gainMembersDetailById(sessionInfo.getUserId());
        getRequest().setAttribute("userInfo", user);
        try {
            
            //检查是否可以购买，这段代码比较乱，凑合着用吧。
           Json json = checkHdCanBuyNum2();
           if(!json.getSuccess()){//等于false的时候不是超过了数量就是异常
               request.setAttribute("json", json);
               return "orderState";
           }
            
            Members member = memberService.getMemberById(sessionInfo.getUserId());
            String orderId = ToolsUtil.getUUID();
            order.setId(orderId);
            order.setAddress(member.getAddress());
            order.setSourceType("0");
            order.setShipName(member.getTruename());
            order.setArea(member.getArea());
            order.setShipZip(member.getZip());
            order.setShipTel(member.getMobile());
            order.setShipEmail(member.getEmail());
            order.setProvince(member.getProvince());
            order.setCity(member.getCity());
            order.setAddress(member.getAddress());
            order.setMemberType("1");
            order.setMemberId(member.getId());
            order.setRemark("活动商品订单");

            //List<Cart> carts = cartService.getCartsByIds(cartIds);
            List<OrderItems> items = new ArrayList<OrderItems>();
            BigDecimal totalCost = BigDecimal.ZERO;
            BigDecimal point = BigDecimal.ZERO;
            Double rootPrice = ResourceUtil.getMobilePhoneRootPrice();
            int costPoint = 0;
            int skuCount = 0;
            BigDecimal pjCost = BigDecimal.ZERO;
            PriceCalculator prc = PriceCalculator.getInstance();
            
            //用户的活动商品
            List<Goods>  userHdGoods = goodsMapper.gainAllShoppingRush(user.getId(), user.getUsername());
            List<String> userHdGoodsIds = new ArrayList<String>();
            if(userHdGoods!=null&&userHdGoods.size()>0){
                for(Goods g:userHdGoods)
                userHdGoodsIds.add(g.getId());
            }
            
            JSONArray hdSkuArray = JSONArray.parseArray(order.getHdSkus());//用户要购买的活动商品集合
            
          //  ShoppingCart shooppingCart = cartService.getShoppingCart(sessionInfo.getUserId());
           // shooppingCart = filterShoppingCart(shooppingCart, cartIds);
            getRequest().setAttribute("hdOrder", true);
            
            if(hdSkuArray!=null&&hdSkuArray.size()>0){
                for(Object goodsObject:hdSkuArray){
                    JSONObject goodsObj =(JSONObject) goodsObject;
                    String goodsId = goodsObj.getString("goodsId");
                    int yetBuyNum = orderService.gainYetBuyNum(goodsId, user.getId());//已经购买的此商品数量
                    
                    //for
                    /*if (!userHdGoodsIds.contains(goodsId)) {//如果数据库里面没有针对此用户的活动商品
                        Json j = new Json();
                        j.setSuccess(false);
                        j.setMsg("没有权限购买此活动商品");
                        request.setAttribute("json", j);
                        return "orderState";
                    }*/
                    
                    JSONArray skuArray = goodsObj.getJSONArray("skus");
                    for(Object skuObj : skuArray){//遍历要购买的商品数据
                        JSONObject skuJsonObj = (JSONObject) skuObj;
                        String skuId = skuJsonObj.getString("skuId");
                        int num = skuJsonObj.getIntValue("num");
                        
                        //生成订单详情项
                        OrderItems item = new OrderItems();
                        item.setId(ToolsUtil.getUUID());
                        GoodsSku sku = skuService.getById(skuId, sessionInfo.getUserId()).get(0);
                        if (sku != null) {
                            item.setGoodsSku(sku);
                            skuCount += num;
                            item.setTargetType("sku");
                            item.setTargetId(sku.getId());
                            item.setMarketbalePrice(BigDecimal.valueOf(prc.calcSinglePrice(sku, num)));
                            item.setAmount(BigDecimal.valueOf(
                                    prc.calcTotalPrice(sku, num) /*+ rootPrice * cart.getGoodsRootNum()*/));
                            item.setRootNum(BigDecimal.valueOf(/*cart.getGoodsRootNum()*/0));
                            item.setDealPrice(BigDecimal.valueOf(prc.calcSinglePrice(sku, num)));
                            
                            item.setNums(BigDecimal.valueOf(num));
                            item.setOrderId(orderId);
                            // 计算获得
                            totalCost = totalCost.add(item.getAmount());
                            items.add(item);
                        }
                        yetBuyNum+=num;//已经购买的加上此次购买的数量
                    }
                    
                    //查出当前数据库查出的用户活动商品
                    /*for(Goods g:userHdGoods){
                        if(goodsId.equals(g.getId())){
                            if(yetBuyNum>=g.getCanBuyNum()){
                                //重定向到提醒页面，已经达到抢购最大个数
                                Json j = new Json();
                                j.setSuccess(false);
                                j.setMsg(g.getName()+"商品已经达到今天活动可购买上限！");
                                request.setAttribute("json", j);
                                return "orderState";
                            }
                        }
                    }*/
                }
            }
            
            if (skuCount >= 1 || pjCost.doubleValue() >= 300) {
                order.setCarriage(BigDecimal.valueOf(0));
            } else {
                order.setCarriage(BigDecimal.valueOf(6));
                // totalCost = totalCost.add(BigDecimal.valueOf(10));
                totalCost = totalCost.add(BigDecimal.valueOf(6));
            }
            request.getSession().setAttribute("zpoint", new BigDecimal(costPoint));
            order.setOrderPoints(new BigDecimal(costPoint));
            order.setTotalCost(totalCost);
            order.setOrderPoints(BigDecimal.valueOf(Math.floor(point.doubleValue())));
            order.setOrderNum(getOrderNum());
            order.setOrderItemss(items);

            point = point.add(totalCost.multiply(BigDecimal.valueOf(pointCoefficient)));
            point = BigDecimal.valueOf(Math.floor(point.doubleValue()));
            // 消费积分
            BigDecimal memberPoint = member.getPoint();
            if (memberPoint == null) {
                memberPoint = BigDecimal.ZERO;
            }
            point = memberPoint.subtract(BigDecimal.valueOf(costPoint)).add(point);

            if (!items.isEmpty()) {
                request.getSession().setAttribute("order", order);
                request.getSession().setAttribute("costPosin", point);

                getRequest().setAttribute("orderItems", items); 
                
                // orderService.save(order);
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        // return "success";

        return "queren";
    }
    
    /**
     * 检查还可以购买的活动商品数量
     * @return
     */
    public Json checkHdCanBuyNum2() {
        
        System.out.println(order.getHdSkus());

        SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
        Members user = memberService.gainMembersDetailById(sessionInfo.getUserId());
        
        try {
            
            //用户的活动商品
            List<Goods>  userHdGoods = goodsMapper.gainAllShoppingRush(user.getId(), user.getUsername());
            List<String> userHdGoodsIds = new ArrayList<String>();
            if(userHdGoods!=null&&userHdGoods.size()>0){
                for(Goods g:userHdGoods)
                userHdGoodsIds.add(g.getId());
            }
            
            JSONArray hdSkuArray = JSONArray.parseArray(order.getHdSkus());//用户要购买的活动商品集合
            
            getRequest().setAttribute("hdOrder", true);
            
            if(hdSkuArray!=null&&hdSkuArray.size()>0){
                for(Object goodsObject:hdSkuArray){
                    JSONObject goodsObj =(JSONObject) goodsObject;
                    String goodsId = goodsObj.getString("goodsId");
                    int yetBuyNum = orderService.gainYetBuyNum(goodsId, user.getId());//已经购买的此商品数量
                    
                    //yetBuyNum已经购买的台数，//repayCanNum还可以购买的台数//wantBuyNum要购买的数量
                    
                    int wantBuyNum = 0;//wantBuyNum要购买的数量
                    JSONArray skuArray = goodsObj.getJSONArray("skus");
                    for(Object skuObj : skuArray){//遍历要购买的单品sku数据
                        JSONObject skuJsonObj = (JSONObject) skuObj;
                        wantBuyNum += skuJsonObj.getIntValue("num");//要购买的数量
                    } 
                    
                    //查出当前数据库查出的用户活动商品
                    for(Goods g:userHdGoods){
                        if(goodsId.equals(g.getId())){
                        	long nowTime = new Date().getTime();
                        	if(g.getShoppingRushEndTime().getTime()<nowTime){
                        		 Json j = new Json(); 
                                 j.setSuccess(false);
                                 j.setMsg(g.getName()+"抢购时间已过");
                                 return j;
                        	}
                            //已经购买的+要购买的大于今天可以购买的那么就提醒用户
                            if(yetBuyNum+wantBuyNum>g.getCanBuyNum()){
                                Json j = new Json();
                                j.setSuccess(false);
                               /* Map<String, Object> map = new HashMap<String, Object>();
                                map.put("goodsName",g.getName());
                                map.put("yetBuyNum", yetBuyNum);//已经购买的数量
                                map.put("wantBuyNum", wantBuyNum);//还可以购买的数量
                                map.put("repayCanNum", g.getCanBuyNum()-yetBuyNum);//可以购买的数量-已经购买的数量=还可以购买的数量
                                j.setObj(map);*/
                                j.setMsg(g.getName()+"已经购买"+yetBuyNum+"台,还可以购买"+(g.getCanBuyNum()-yetBuyNum)+"台");
                                return j;
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            Json j = new Json();
            j.setSuccess(false);
            j.setMsg("系统异常请稍后重试！");
            return j;
        }
        Json j = new Json();
        j.setSuccess(true);
        j.setMsg("可以购买！");
        return j;
    }
    
    /**
     * 检查还可以购买的活动商品数量
     * @return
     */
    public void checkHdCanBuyNum() {
        
        System.out.println(order.getHdSkus());

        SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
        Members user = memberService.gainMembersDetailById(sessionInfo.getUserId());
        
        try {
            
            //用户的活动商品
            List<Goods>  userHdGoods = goodsMapper.gainAllShoppingRush(user.getId(), user.getUsername());
            List<String> userHdGoodsIds = new ArrayList<String>();
            if(userHdGoods!=null&&userHdGoods.size()>0){
                for(Goods g:userHdGoods)
                userHdGoodsIds.add(g.getId());
            }
            
            JSONArray hdSkuArray = JSONArray.parseArray(order.getHdSkus());//用户要购买的活动商品集合
            
            getRequest().setAttribute("hdOrder", true);
            
            if(hdSkuArray!=null&&hdSkuArray.size()>0){
                for(Object goodsObject:hdSkuArray){
                    JSONObject goodsObj =(JSONObject) goodsObject;
                    String goodsId = goodsObj.getString("goodsId");
                    int yetBuyNum = orderService.gainYetBuyNum(goodsId, user.getId());//已经购买的此商品数量
                    
                    /*if (!userHdGoodsIds.contains(goodsId)) {//如果数据库里面没有针对此用户的活动商品
                        Json j = new Json();
                        j.setSuccess(false);
                        j.setMsg("没有权限购买此活动商品");
                        request.setAttribute("json", j);
                        return "orderState";
                    }*/
                    
                    //yetBuyNum已经购买的台数，//repayCanNum还可以购买的台数//wantBuyNum要购买的数量
                    
                    int wantBuyNum = 0;//wantBuyNum要购买的数量
                    JSONArray skuArray = goodsObj.getJSONArray("skus");
                    for(Object skuObj : skuArray){//遍历要购买的单品sku数据
                        JSONObject skuJsonObj = (JSONObject) skuObj;
                        wantBuyNum += skuJsonObj.getIntValue("num");//要购买的数量
                    }
                    
                    //查出当前数据库查出的用户活动商品
                    for(Goods g:userHdGoods){
                        if(goodsId.equals(g.getId())){
                            //已经购买的+要购买的大于今天可以购买的那么就提醒用户
                            if(yetBuyNum+wantBuyNum>g.getCanBuyNum()){
                                Json j = new Json();
                                j.setSuccess(false);
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("goodsName",g.getName());
                                map.put("yetBuyNum", yetBuyNum);//已经购买的数量
                                map.put("wantBuyNum", wantBuyNum);//还可以购买的数量
                                map.put("repayCanNum", g.getCanBuyNum()-yetBuyNum);//可以购买的数量-已经购买的数量=还可以购买的数量
                                j.setObj(map);
                                writeJson(j);
								return;
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            Json j = new Json();
            j.setSuccess(false);
            j.setMsg("系统异常请稍后重试！");
            writeJson(j);
        }
        Json j = new Json();
        j.setSuccess(true);
        j.setMsg("可以购买！");
        writeJson(j);
    }
	

	private ShoppingCart filterShoppingCart(ShoppingCart shooppingCart, String[] cartIds) {
		List<CartItem> items = new ArrayList<CartItem>();
		for (CartItem cartItem : shooppingCart.getItems()) {
			if (ArrayUtils.contains(cartIds, cartItem.getId())) {
				items.add(cartItem);
			}
		}
		shooppingCart.setItems(items);
		return shooppingCart;
	}

	/**
	 * 获得订单类表
	 * 
	 * @Title: gainOrders @Description: TODO(这里用一句话描述这个方法的作用) @param @return
	 * 设定文件 @return String 返回类型 @author 田超强 @throws
	 */
	public String gainOrders() {
		try {
			// System.out.println("获取订单列表传入的参数：" + param());
			List<Order> orders = orderService.gainPageOrders(param());

			// 遍历订单集合，判断签收时间是否超过7天。
			/*
			 * for (Order o : orders) { if (o.getSignForTime() != null) {//
			 * 如果签收时间不为空，那么就对比时间是否超过了7天 long date1 =
			 * DateUtil.compareDate2(DateUtil.getStringByDate(new Date()),
			 * DateUtil.getStringByDate(o.getSignForTime()), 4); long date2 = 7
			 * * 24 * 60 * 60; if (date1 > date2) {// 如果签收时间大于7天那么就不显示退款退货
			 * o.setShowQuit(2); } else { o.setShowQuit(1); } } else {//
			 * 如果数据库没有签收时间那么、显示退款退货 o.setShowQuit(1); } }
			 */

			String countNum = orderService.gainCountNum(param());
			Map m = new HashMap();
			m.put("orders", orders);
			m.put("countNum", countNum);// 订单总数量
			int pageCount = (Integer.parseInt(countNum) - 1) / order.getRows() + 1;
			m.put("pageCount", pageCount);// 总页数

			order.setPayStatus("1");
			m.put("paidNum", orderService.gainCountNum(param()));// 已付款订单数量
			order.setPayStatus("0");
			m.put("obligationNum", orderService.gainCountNum(param()));// 待付款订单数量
			order.setPayStatus("1");// 已付款
			order.setShipStatus("0");// 待发货
			m.put("staySendNum", orderService.gainCountNum(param()));// 待发货订单数量
			m.put("page", order.getPage());
			// 默认选中//全部订单//已付款//待付款//待发货//
			m.put("selected", getSelected());
			m.put("searchParam", getSearchParam());
			// System.out.println("格式化过的时间：" +
			// DateUtil.getStringByDate(order.getSearchStartTime()));
			m.put("searchStartTime", DateUtil.getStringByDate(order.getSearchStartTime()));
			m.put("searchEndTime", DateUtil.getStringByDate(order.getSearchEndTime()));

			request.setAttribute("orderMap", m);
			// System.out.println("订单列表数据" + JSON.toJSONString(orders));
			// writeJson(m);
			return "orders";
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
	}

	/**
	 * 获取订单详情
	 * 
	 * @Title: gainOrder @Description: TODO(这里用一句话描述这个方法的作用) @param 设定文件 @return
	 * void 返回类型 @author 田超强 @throws
	 */
	public String gainOrderDetail() {
		try {
			// System.out.println("开始查询订单详情++++++++++++++++++++++");

			List<Order> orders = orderService.gainDetail(param());

			if (orders != null && orders.size() > 0) {
				Map m = new HashMap();
				m.put("order", orders.get(0));
				// 如果快递单号不为空，那么就查快递信息
				if (orders.get(0).getExpressId() != null && orders.get(0).getExpressNum() != null) {
					DlyCorp dlyCorp = dlyCorpService.selectByPrimaryKey(orders.get(0).getExpressId());
					// 快递公司编号
					String com = dlyCorp.getType();
					Map expressMap = JsonUtil
							.getMap4Json(ExpressUtil.getExpressInfo(com, orders.get(0).getExpressNum()));
					expressMap.put("expressName", dlyCorp.getName());
					m.put("express", expressMap);
				}
				// logger.info("订单详情：" + m);
				request.setAttribute("orderMap", m);
				request.setAttribute("order", orderService.getById(order.getId()));
				// System.out.println("订单详情：" + JSON.toJSONString(m));
				// writeJson(m);
				return "detail";
			}
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
		return "err";
	}

	/**
	 * 去添加评论
	 * 
	 * @Title: goEvaluate @Description: TODO(这里用一句话描述这个方法的作用) @param @return
	 * 设定文件 @return String 返回类型 @author 田超强 @throws
	 */
	public String goEvaluate() {
		try {
			List<Order> orders = orderService.gainDetail(param());
			if (!orders.get(0).getSaleType().equals("1")) {
				if (orders != null && orders.size() > 0) {
					Map m = new HashMap();
					m.put("order", orders.get(0));

					request.setAttribute("orderMap", m);
					// System.out.println(m);
					return "evaluate";
				}
			} else {
				return "nort";
			}
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
		return "err";
	}

	/**
	 * 组合查询条件
	 * 
	 * @Title: param @Description: TODO(这里用一句话描述这个方法的作用) @param @return
	 * 设定文件 @return Map 返回类型 @author 田超强 @throws
	 */
	private Map param() {

		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		Map param = new HashMap();
		param.put("userId", sessionInfo.getUserId());
		param.put("id", order.getId());
		param.put("orderNum", order.getOrderNum());
		param.put("saleType", order.getSaleType());
		param.put("status", order.getStatus());
		param.put("payStatus", order.getPayStatus());
		param.put("shipStatus", order.getShipStatus());
		param.put("sort", order.getSort());// 不传入排序的字段默认时间排序
		param.put("order", order.getOrder());
		param.put("page", order.getPage());// 不传入页码默认取第一页数据
		param.put("rows", order.getRows());
		if (getSearchParam() != null) {
			param.put("searchParam", getSearchParam().trim());// 搜索条件
		}
		if (order.getSearchStartTime() != null && order.getSearchEndTime() != null) {
			param.put("searchStartTime", order.getSearchStartTime());
			param.put("searchEndTime", order.getSearchEndTime());
		}
		return param;
	}

	/**
	 * 伪删除订单
	 * 
	 * @Title: delOrder @Description: TODO(这里用一句话描述这个方法的作用) @param 设定文件 @return
	 * void 返回类型 @author 田超强 @throws
	 */
	public void delOrder() {
		Json json = new Json();
		try {
			// System.out.println("要删除的订单id：" + order.getIds());
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());

			String[] idsArray = order.getIds().split(",");

			if (idsArray != null && idsArray.length > 0) {
				for (String id : idsArray) {
					Order o = orderService.gainOrderByID(id);
					orderService.upDisabledByUid(sessionInfo.getUserId(), id);
					MsgUtil.delOrder(o.getOrderNum(), sessionInfo.getLoginName());
				}
			}

			Map m = new HashMap();
			order.setPayStatus("1");
			m.put("paidNum", orderService.gainCountNum(param()));// 已付款订单数量
			order.setPayStatus("0");
			m.put("obligationNum", orderService.gainCountNum(param()));// 待付款订单数量
			order.setPayStatus("1");// 已付款
			order.setShipStatus("0");// 待发货
			m.put("staySendNum", orderService.gainCountNum(param()));// 待发货订单数量

			json.setMsg("删除成功");
			json.setSuccess(true);
			json.setObj(m);
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
		writeJson(json);
	}

	/**
	 * 取消订单/退货退款
	 * 
	 * @Title: concelOrder @Description: TODO(这里用一句话描述这个方法的作用) @param
	 * 设定文件 @return void 返回类型 @author 田超强 @throws
	 */
	/*
	 * public String concelOrder() { String result = null; Json json = new
	 * Json(); try {
	 * 
	 * // System.out.println("查看要退款退货的订单：" + order.getId());
	 * 
	 * if (order.getId() == null || order.getId().equals("")) {
	 * json.setMsg("请选择要申请退货的订单"); request.setAttribute("msg", json); return
	 * "cerr"; }
	 * 
	 * List<Order> orders = orderService.gainDetail(param()); if (orders != null
	 * && orders.size() > 0) { Order order = orders.get(0); Order order2; int
	 * shipStatus = Integer.parseInt(order.getShipStatus()); int payStatus =
	 * Integer.parseInt(order.getPayStatus()); //
	 * 无论网上支付还是，货到付款。只要支付状态是3已退款，货物状态是：5已退货。那就重定向页面到。此订单已申请过退款退货 if (payStatus
	 * == 3 && shipStatus == 5) { // 重定向到已经申请过退款退货页面
	 * json.setMsg("不可重复申请退货退款申请"); result = "cerr"; }
	 * 
	 * // 0网上支付 if (Integer.parseInt(order.getPayMent()) == 0) { // 0未付款取消订单 if
	 * (payStatus == 0) { // 把订单状态改为3 order.setStatus("3");
	 * editOrderStatus(order); // 由于是付款成功以后管易才会有记录所以，这里不用推送消息告诉管易取消订单 //
	 * 不用记录此消息不用通知客服
	 * 
	 * json.setMsg("订单已成功取消"); json.setSuccess(true);
	 * 
	 * // 跳转到订单取消成功页面 result = "csuccess"; }
	 * 
	 * // 1已付款 , 用户提交退货原因等申请，向管易发送退货消息通知，待售后审核后确定退款 if (payStatus == 1) { //
	 * 0未发货直接跳转到退款页面系统 if (shipStatus == 0) { // 跳转到退款申请页面//让用户提交退款申请 // result
	 * = "refund";
	 * 
	 * // 直接把订单状态改为已退款 order.setPayStatus("3"); editOrderStatus(order); //
	 * 通知管易取消订单 informGY(); // 调用退款接口
	 * 
	 * // 执行成功向页面返回消息 json.setMsg("订单退款成功！"); json.setSuccess(true);
	 * 
	 * // 跳转到订单取消成功页面 result = "csuccess"; }
	 * 
	 * // 1已发货 2已送达(地包) 3已收货 //跳转到退货退款申请页面 if (shipStatus == 1 || shipStatus ==
	 * 2 || shipStatus == 3) { // 跳转到退款退货申请页面//让用户提交退款申请 result = "refundSales";
	 * } }
	 * 
	 * } else if (Integer.parseInt(order.getPayMent()) == 1) {// 货到付款
	 * 
	 * boolean gyStatus = true;// 管易查询的订单发货状态
	 * 
	 * if (!gyStatus) {// 没有发货 // 改变订单状态//取消订单 order.setStatus("3");
	 * editOrderStatus(order); // 通知管易订单取消 informGY(); // 跳转到取消成功页面 result =
	 * "csuccess"; } else {// 管易显示已经发货 // 判断此时订单表里面的发货状态是否已发货 if (shipStatus ==
	 * 0) {// 订单状态未发货 // 修改订单状态为已发货 order.setShipStatus("1");
	 * editOrderStatus(order); // 跳转到申请退货退款页面 result = "refundSales"; } else if
	 * (shipStatus == 1 || shipStatus == 2 || shipStatus == 3) {//
	 * 1已发货，2已送达(地包)，3已收货。跳转到退货退款申请页面 // 跳转到退货退款申请页面 result = "refundSales"; } }
	 * } } else { json.setMsg("订单操作异常！"); result = "cerr"; } } catch (Exception
	 * e) { logger.info("异常：" + e.getMessage()); json.setMsg("系统异常"); //
	 * json.setObj(0); } // writeJson(json);
	 * 
	 * if (result != null && result.equals("refundSales")) {
	 * request.setAttribute("dlyCorps", dlyCorpService.gainAll());// 物流公司列表信息 }
	 * json.setObj(order); request.setAttribute("msg", json); //
	 * System.out.println("跳转前看看要跳转到那个页面：" + result); return result != null ?
	 * result : "cerr"; }
	 */

	/**
	 * 取消订单/退货退款
	 * 
	 * @Title: concelOrder @Description: TODO(这里用一句话描述这个方法的作用) @param
	 * 设定文件 @return void 返回类型 @author 田超强 @throws
	 */
	public String concelOrder() {
		String result = null;
		Json json = new Json();
		try {

			// System.out.println("查看要退款退货的订单：" + order.getId());

			if (order.getId() == null || order.getId().equals("")) {
				json.setMsg("请选择要申请退货的订单");
				request.setAttribute("msg", json);
				return "cerr";
			}

			List<Order> orders = orderService.gainDetail(param());
			if (orders != null && orders.size() > 0) {
				order = orders.get(0);
				int shipStatus = Integer.parseInt(order.getShipStatus());// 发货状态
				int payStatus = Integer.parseInt(order.getPayStatus());// 支付状态
				int status = Integer.parseInt(order.getStatus());// 订单状态

				// 无论网上支付还是，货到付款。只要支付状态是3已退款，货物状态是：5已退货。那就重定向页面到。此订单已申请过退款退货
				if (payStatus == 3 && shipStatus == 5) {
					// 重定向到已经申请过退款退货页面
					json.setMsg("不可重复申请退货退款申请");
					result = "cerr";
				}

				// 0网上支付
				if (Integer.parseInt(order.getPayMent()) == 0) {
					// 0未付款取消订单
					if (payStatus == 0) {
						// 把订单状态改为3
						order.setStatus("3");
						editOrderStatus(order);
						// 由于是付款成功以后管易才会有记录所以，这里不用推送消息告诉管易取消订单
						// 不用记录此消息不用通知客服

						json.setMsg("订单已成功取消");
						json.setSuccess(true);

						// 跳转到订单取消成功页面
						result = "csuccess";
					}

					// 1已付款
					if (payStatus == 1) {

						// 0未发货
						if (shipStatus == 0) {

							// 直接把订单状态改为已退款
							order.setPayStatus("3");
							editOrderStatus(order);
							// 通知管易取消订单
							informGY(order.getOrderNum());
							// 调用退款接口

							// 执行成功向页面返回消息
							json.setMsg("订单退款成功！");
							json.setSuccess(true);

							// 跳转到订单取消成功页面
							result = "csuccess";
						}

						// 1已发货 2已送达(地包) 3已签收 //跳转到退货退款申请页面
						if (shipStatus == 1 || shipStatus == 2 || shipStatus == 3) {
							// 跳转到退款退货申请页面//让用户提交退款申请
							result = "refundSales";
						}
					}

				} else if (Integer.parseInt(order.getPayMent()) == 1) {// 货到付款

					// 查询管易是否已经发货
					Json js = EcErpUtil.gyShipStatus(order.getOrderNum());

					boolean gyStatus = js.getSuccess();// 管易查询的订单发货状态

					if (!gyStatus) {// 没有发货
						// 改变订单状态//取消订单
						order.setStatus("3");
						editOrderStatus(order);
						// 通知管易订单取消
						informGY(order.getOrderNum());
						// 跳转到取消成功页面
						result = "csuccess";
					} else {// 管易显示已经发货
						// 判断此时订单表里面的发货状态是否已发货
						if (shipStatus == 0) {// 订单状态未发货
							// 修改订单状态为已发货
							order.setShipStatus("1");
							editOrderStatus(order);
						}
						result = "refundSales";
					}
				}

				// 通知管理人员有人取消了订单
				MsgUtil.MsgConcelOrder(order.getOrderNum());

			} else {
				json.setMsg("订单操作异常！");
				result = "cerr";
			}
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			json.setMsg("系统异常");
		}

		// json.setObj(order);
		request.setAttribute("order", order);
		// System.out.println("跳转前看看要跳转到那个页面：" + result + " 当前订单编号：" +
		// order.getOrderNum());
		return result != null ? result : "cerr";
	}

	// 修改订单信息、、取消订单
	private void editOrderStatus(Order order) {
		orderService.updateByPrimaryKeySelective(order);
	}

	/**
	 * 通知管易
	 * 
	 * @Title: informGY @Description: TODO(这里用一句话描述这个方法的作用) @param 设定文件 @return
	 * void 返回类型 @author 田超强 @throws
	 */
	private void informGY(String orderNum) {

		EcErpUtil.cancelOderById(orderNum);
		// // System.out.println("通知管易订单：" + order.getId() + " 取消！");
	}

	/**
	 * 确认收货
	 * 
	 * @Title: concelOrder @Description: TODO(这里用一句话描述这个方法的作用) @param
	 * 设定文件 @return void 返回类型 @author 田超强 @throws
	 */
	public void affirmGoods() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			List<Order> orders = orderService.gainDetail(param());
			if (orders != null && orders.size() > 0) {
				Order order = orders.get(0);
				Order order2;
				// 把货物状态改为3已收货
				order2 = new Order();
				order2.setMemberId(sessionInfo.getUserId());
				order2.setId(order.getId());
				order2.setShipStatus("3");
				orderService.updateByPrimaryKeySelective(order2);
				json.setMsg("已经确认收货");
				json.setSuccess(true);
				json.setObj(1);
			}
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			json.setMsg("系统异常");
			json.setObj(0);
		}
		writeJson(json);
	}
	
	/**
	 * 检测订单是否有活动商品
	 */
	public void checkerOrderThereHdGoods(){
	    SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
	    try {
            writeJson(orderService.checkerOrderThereHdGoods(sessionInfo.getUserName(), order.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            writeJson(false);
        }
	}

	/**
	 * 检查发货状态
	 * 
	 * @Title: checkShipStatus @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @return 设定文件 @return @author 田超强 @throws
	 */
	public void checkShipStatus() {
		Json j = new Json();
		try {
			// System.out.println("订单Id:" + order.getId());
			List<Order> orders = orderService.gainDetail(param());
			if (orders != null && orders.size() > 0) {
				Order order = orders.get(0);

				if (Integer.parseInt(order.getShipStatus()) == 0) {// 如果订单状态是未发货那么就去查询管易
					// 查询管易是否已经发货
					Json js = EcErpUtil.gyShipStatus(order.getOrderNum());
					if (js.getSuccess()) {
						order.setShipStatus("1");
						order.setShipTime(DateUtil.getDateStrToDate(js.getObj().toString()));
						orderService.updateByPrimaryKeySelective(order);
						// 如果管易已发货。把订单状态改为已发货
						j.setMsg("卖家已发货");
						j.setSuccess(true);
						j.setObj(2);
					} else {
						j.setMsg("未发货");
						j.setObj(1);
					}
					;

				} else {
					j.setMsg("卖家已发货");
					j.setSuccess(true);
					j.setObj(2);
				}
			}
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			j.setMsg("系统异常");
			j.setObj(0);
		}
		writeJson(j);
	}
	
	/**
	 * 
	 * @Title: add @Description: 添加订单 @return    设定文件 String    返回类型 @throws
	 */
	public void doNotNeedSession_addNewOrder() {
		
		if(!StringUtils.isEmpty(order.getOrderNum()) && !StringUtils.isEmpty(order.getTotalCost()+"")){
			   Members member = memberService.getMemberById("94a9cc9699304809b9038c5fc476d013");
				String orderId = ToolsUtil.getUUID();
				order.setId(orderId);
				order.setShipName(member.getTruename());
				order.setArea(member.getArea());
				//order.setShipZip(member.getZip());
				order.setShipTel(member.getMobile());
				//order.setShipEmail(member.getEmail());
				order.setProvince(member.getProvince());
				order.setCity(member.getCity());
				order.setAddress(member.getAddress());
				order.setMemberType("1");
				order.setMemberId(member.getId());
	            order.setOrderNum(order.getOrderNum());
			    order.setCreatetime(new Date());
			    order.setTotalCost(order.getTotalCost());
			    orderService.saveOrder(order);
		   }
			
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	
}
