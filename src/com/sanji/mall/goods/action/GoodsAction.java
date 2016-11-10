package com.sanji.mall.goods.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.brand.service.BrandService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.goods.service.GoodsService;
import com.sanji.mall.goods.service.UserGoodsRecordService;
import com.sanji.mall.goods.thread.UserGoodsRecordCache;
import com.sanji.mall.goods.thread.UserGoodsRecordThread;
import com.sanji.mall.home.service.HomeService;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.*;
import com.sanji.mall.order.service.OrderItemsService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Namespace("/goods")
@Action(value = "goodsAction", results = {@Result(name = "succeed", location =
        "/admin/goods/goods.jsp"), @Result(name = "hotSale", location = "/admin/goods/hotsale" +
		".jsp"),
        @Result(name = "bjd2", location = "/admin/goods/bjd2.jsp"), @Result(name = "bjd3",
		location = "/admin/goods/bjd3.jsp"),
        @Result(name = "bjdjson", location = "/admin/goods/bjdjson.jsp"), @Result(name = "bjd4",
		location = "/admin/goods/bjd4.jsp"),
        @Result(name = "bjd5", location = "/admin/goods/bjd5.jsp"), @Result(name = "bjd6",
		location = "/admin/goods/bjd6.jsp"),
        @Result(name = "bjd7", location = "/admin/goods/bjd7.jsp"), @Result(name =
		"bjdjsonByBrand", location = "/admin/goods/bjdjsonByBrand.jsp"),
        @Result(name = "bjd8", location = "/admin/goods/bjd8.jsp"), @Result(name = "shoprush",
        location = "/admin/goods/shoprush.jsp")})
public class GoodsAction extends BaseAction implements ModelDriven<Goods> {
    private static final Logger logger = Logger.getLogger(GoodsAction.class);
    private Goods goods;

    public Goods getModel() {
        if (goods == null) {
            goods = new Goods();
        }
        return goods;
    }

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderItemsService orderItemsService;

    @Resource
    private BrandService brandService;

    @Resource
    private MemberService memberService;

    @Resource
    private HomeService homeService;

    @Autowired
    private UserGoodsRecordService ugrs;

    public String bjd() {
        SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
        String col = getRequest().getParameter("col");
        String jx = getRequest().getParameter("jx");
        request.setAttribute("jx", jx);
        col = "2".equals(col) ? "2" : "3";

        String city = homeService.gainCityById(sessionInfo.getUserId());
        request.setAttribute("city", city);
        return "bjd" + col;
    }

    /**
     * @return 专题页
     * @Title:bjd
     */
    public String bjdtwo() {
        String yemian = getRequest().getParameter("yemian");
        // System.out.println("****************"+yemian);
        return "bjd" + 7;
    }

    /**
     * 第二个专题页
     */
    public String bjdthree() {
        return "bjd" + 6;
    }

    /**
     * 第三个专题页
     */
    public String bjdfour() {
        return "bjd" + 8;
    }

    public String bjdjson() {
        List<Map<String, List<Map<String, Object>>>> bjd = new ArrayList<Map<String,
				List<Map<String, Object>>>>();
        String userId = gainSessionInfo().getUserId();
        String jx = getRequest().getParameter("jx");
        // 苹果-三星-小米-华为-联想-荣耀-酷派-大神
        List<String> brandSquen = Arrays.asList("苹果", "三星", "小米", "华为", "联想", "荣耀", "酷派", "大神");
        brandSquen = new ArrayList<String>(brandSquen);
        if ("etj,lnj,ptgnj".contains(jx)) {
            brandSquen.addAll(0, Arrays.asList("多美达", "爱我", "米多"));
        } else if ("lj".equals(jx)) {
            brandSquen = Arrays.asList("苹果", "华为", "荣耀", "小米", "魅族诺基亚", "神舟", "酷派", "大神", "三星",
					"中兴", "努比亚", "HTC", "摩托", "一加", "飞利浦", "锤子", "大可乐", "TCL");
        }

        List<Brand> brands = brandService.gainAll();
        List<String> allBrandName = new ArrayList<String>();
        for (Brand brand : brands) {
            allBrandName.add(brand.getName());
        }
        allBrandName.removeAll(brandSquen);// 数据库查出来的数据比写死的多出来的品牌

        for (String string : brandSquen) {
            List<Map<String, Object>> hw = goodsService.getBjd(string, userId, jx);
            Map<String, List<Map<String, Object>>> m1 = new HashMap<String, List<Map<String,
					Object>>>();
            if (!hw.isEmpty()) {
                m1.put(string, hw);
                bjd.add(m1);
            }
        }
        for (String string : allBrandName) {
            List<Map<String, Object>> hw = goodsService.getBjd(string, userId, jx);
            Map<String, List<Map<String, Object>>> m1 = new HashMap<String, List<Map<String,
					Object>>>();
            if (!hw.isEmpty()) {
                m1.put(string, hw);
                bjd.add(m1);
            }

        }
        request.setAttribute("bjd", bjd);
        return "bjdjson";
    }

    public String bjdjsonByBrand() {
        List<Map<String, List<Map<String, Object>>>> bjd = new ArrayList<Map<String,
				List<Map<String, Object>>>>();
        String userId = gainSessionInfo().getUserId();
        String jx = getRequest().getParameter("jx");
        // 苹果-三星-小米-华为-联想-荣耀-酷派-大神
        String brandName = getRequest().getParameter("brandName");

        List<Map<String, Object>> hw = goodsService.getBjd(brandName, userId, jx);
        Map<String, List<Map<String, Object>>> m1 = new HashMap<String, List<Map<String,
				Object>>>();
        if (!hw.isEmpty()) {
            m1.put(brandName, hw);
            bjd.add(m1);
        }

        request.setAttribute("bjd", bjd);
        return "bjdjsonByBrand";
    }

    public String showHotSale() {
        List<Brand> hotSaleBrand = brandService.getHotSaleBrand();
        request.setAttribute("brand", hotSaleBrand);
        Map<String, List<Goods>> goodss = new HashMap<String, List<Goods>>();
        for (Brand brand : hotSaleBrand) {
            List<Goods> goods = goodsService.getHotSale(1, 20, brand.getName());
            goodss.put(brand.getName(), goods);
        }
        request.setAttribute("goodss", goodss);
        return "hotSale";
    }
    
    /**
     * 保存用户浏览记录
     */
    public void scanLog(){
    	try {
    		Members user = memberService.getMemberById(gainSessionInfo().getUserId());
    		String id = request.getParameter("id");
    		goods = goodsService.selectByPrimaryKey(id);
        	userSeeGoods(user);
        	writeJson("ok");
		} catch (Exception e) {
			e.printStackTrace();
			writeJson("err");
		}
    }

    /**
     * 根据商品编号获得该商品所有相关详细信息
     *
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: gainAllGoodsInfoByGoodsNum
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 田超强
     */
    public String gainAllGoodsInfoByGoodsNum() {
        try {

            // 那个用户在什么时候访问的要记录下来

			/*
			 * Map m = new HashMap(); m.put("goodsNum", goods.getGoodsNum()); if
			 * (gainSessionInfo() != null) { m.put("memberId",
			 * gainSessionInfo().getUserId()); } else { m.put("memberId", null);
			 * } List<Goods> goodss = goodsService.gainAllGoodsInfoByMap(m);
			 */
        	//Members user = memberService.getMemberById(gainSessionInfo().getUserId());
        	//userSeeGoods(user);
            // ugrs.save(ugr);

            //List<Goods> goodss = goodsService.gainAllGoodsInfo2(user.getId(), goods.getGoodsNum()	, user.getArea(), user.getUsername());
        	 List<Goods> goodss = goodsService.gainAllGoodsInfoByGoodsNum(goods.getGoodsNum());

            Goods g = null;
            if (goods != null && goodss.size() > 0) {
                g = goodss.get(0);
                goodsService.upClickRate(goods.getGoodsNum());// 修改商品点击数

                if (null != goodss.get(0).getGoodsSkus() && 0 < goodss.get(0).getGoodsSkus().size())
                    // System.out.println("单品数量："+
                    // goodss.get(0).getGoodsSkus().size());
                    // 遍历单品列表把已经下架的单品删除
                    for (int i = 0; i < goodss.get(0).getGoodsSkus().size(); i++) {
                        GoodsSku sku = goodss.get(0).getGoodsSkus().get(i);
                        // System.out.println("单品Id:" + sku.getId());
                        if ("false".equals(sku.getShelves())) {
                            goodss.get(0).getGoodsSkus().remove(sku);
                        }
                    }
            }

            // System.out.println("是否已经收藏：" + g.getCollectId());

            g.setGoodsJsonStr(writeJsonByExcludesProperties(g, new String[]{"goodsDetail",
					"userId", "goodsPics"}));
            // System.out.println("查询出来的商品详情：" + JSON.toJSONString(g));
            request.setAttribute("goods", g);
           /* Map param = new HashMap();
            param.put("targetType", "sku");
            param.put("targetId", g.getId());
            request.setAttribute("goodsDealCountNum", orderItemsService.gainDealNum(param));//
			// 所有成交记录数量
            param.put("minTime", DateUtil.getDateStrToDate(DateUtil.currentMonFirstDay(),
					"yyyy-MM-dd"));
            param.put("maxTime", DateUtil.getDateStrToDate(DateUtil.currentMonLastDay(),
					"yyyy-MM-dd"));
            request.setAttribute("goodsDealNum", orderItemsService.gainDealNum(param));// 月成交记录
*/
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("异常：" + e.getMessage());
        }

        return "succeed";
    }

	private Members userSeeGoods(Members user) {
		String source = request.getParameter("s");
		UserGoodsRecord ugr = new UserGoodsRecord();
		ugr.setId(ToolsUtil.getUUID());
		ugr.setGoodsNum(goods.getGoodsNum());
		ugr.setUserId(user.getId());
		ugr.setCreateTime(new Date());
		ugr.setSource(source==null?"默认页面":source);
		int size = UserGoodsRecordCache.add(ugr);
		// System.out.println("用户浏览记录个数：" + size);
		if (size > 1000) {// 超过十条记录就保存一次
		    // System.out.println("保存用户浏览记录");
		    // ugrs.inserts(userGoodsRecords);
		    ExecutorService pool = Executors.newFixedThreadPool(3);// 创建一个固定大小为3的线程池
		    pool.submit(new UserGoodsRecordThread(ugrs));// 批量保存多线程
		    pool.shutdown();
		}
		return user;
	}

    /**
     * 获取已登录的用户信息
     *
     * @param
     * @return void 返回类型
     * @throws
     * @Title: gainSessionInfo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 田超强
     */
    public SessionInfo gainSessionInfo() {
        return (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
    }

    public void gainNameBylike() {
        Json j = new Json();
        try {
            // System.out.println("传入的模糊匹配参数：" + goods.getS());
            List<Map<String, String>> list = goodsService.gainNameBylike(goods.getS().replaceAll(" ", "%").toLowerCase());
            j.setObj(list);
            j.setSuccess(true);
        } catch (Exception e) {
            // TODO: handle exception
            j.setMsg("系统异常");
            // System.out.println("系统异常：" + e.getMessage());
        }
        writeJson(j);
    }

    public void gainAllShoppingRush() {
        Json j = new Json();

        List<Goods> goodsList = goodsService.gainAllShoppingRush(gainSessionInfo().getUserId(),
                gainSessionInfo().getLoginName());

        if (goodsList != null) {
            for (Goods good : goodsList) {
                good.setGoodsDetail(good.getGoodsDetail() == null ? "" : good.getGoodsDetail().replaceAll("\"", "'").replaceAll("\t", ""));
            }

            j.setObj(goodsList.size() > 6 ? goodsList.subList(0, 5) : goodsList);
        }
        j.setMsg((new Date().getTime()) + "");
        writeJson(j);
    }

    public String showShopRush() {
        return "shoprush";
    }

    public void getSysTime() {
        Json j = new Json();
        j.setObj((new Date().getTime()) + "");
        writeJson(j);
    }
}
