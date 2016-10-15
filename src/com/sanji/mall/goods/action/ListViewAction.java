package com.sanji.mall.goods.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.brand.service.BrandService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.JsonUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.goods.service.CatService;
import com.sanji.mall.goods.service.GoodsService;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Brand;
import com.sanji.mall.model.Cat;
import com.sanji.mall.model.Goods;
import com.sanji.mall.model.Members;
import com.sanji.mall.pojo.Base;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 列表信息
 * 
 * @ClassName: ListView
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-24 下午2:12:56
 * 
 */
@Namespace("/goods")
@Action(value = "listViewAction", results = { @Result(name = "acs", location = "/admin/listView/acs_list.jsp"),
		
		@Result(name = "succeed", location = "/admin/listView/phone_list.jsp") })
public class ListViewAction extends BaseAction implements ModelDriven<Base> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ListViewAction.class);

	private Base base;

	public Base getModel() {
		// TODO Auto-generated method stub
		if (base == null) {
			base = new Base();
		}
		return base;
	}

	@Resource
	private CatService catService;// 分类接口

	@Resource
	private BrandService brandService;// 品牌接口

	@Resource
	private GoodsService goodsService;

	@Resource
	private MemberService memberService;

	private Map<String, Object> paramMap = new HashMap<String, Object>();

	private String paramStr;

	private String showType;// 请求的数据类型acs请求配件数据 goods请求手机数据

	private List<String> catNames;// 跳转到列表页要默认选中的类别名称集合

	private List<String> brandNames;// 跳转到列表页默认选中品牌

	private String defaultPrices;

	private String searchParam;// 默认搜索的参数名称

	/**
	 * 获得商品列表信息，跳转到商品列表信息页面
	 * 
	 * @Title: doNotNeedSession_listView
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String listView() {
		try {

			// System.out.println("当前请求参数：" + getCatNames() + "   " +
			// getBrandNames() + "   " + getDefaultPrices() + "   模糊查找的参数：" +
			// getSearchParam());
			String resultPage = "succeed";// 要跳转的页面 acs跳转到配件列表页

			// 判断请求的是配件列表就剔除手机
			List<Cat> cats = catService.gainAll();
			List<Brand> brands = brandService.gainAll();
			paramMap.put("cats", cats);
			paramMap.put("brands", brands);

			// 默认加载的类型
			if (getCatNames() != null && getCatNames().size() > 0) {// 前台传入的参数为空

				List<String> catIds = new ArrayList<String>();
				if (getCatNames().toString().indexOf("配件") >= 0) {// 加载所有配件数据
					for (int i = 0; i < cats.size(); i++) {
						Cat c = cats.get(i);
						if (c.getName().equals("手机")) {
							cats.remove(i);
						} else {
							catIds.add(c.getId());
						}
					}
					catIds.add("配件");// 前端js判断如果有配件这两个词，那么就不选中任何类型查询所有配件信息
					paramMap.put("defaultCatIds", catIds);
				} else {
					catIds = catService.gainIds(getCatNames());// 根据类型名称查询类型id
				}
				paramMap.put("defaultCatIds", catIds);

				// 判断catIds里面有没有手机的id
				catNames = new ArrayList<String>();
				catNames.add("手机");
				// 如果用户传入的是配件，那么现在catids里面存储的是所有配件的Id不存在手机的Id那么就跳转到配件也
				// 根据类型名称查询的类型id是否包含手机类型的id。如果包含就跳转到手机页面，不包含就跳转到配件页面
				if (catIds.indexOf(catService.gainIds(catNames).get(0).toString()) < 0) {// 查找配件信息
					resultPage = "acs";
				}

			} else {// 把手机放入集合，然后去查找id

				catNames = new ArrayList<String>();
				catNames.add("手机");
				List<String> catIds = catService.gainIds(catNames);
				paramMap.put("defaultCatIds", catIds.toString());
			}

			// 默认要加载的品牌
			if (getBrandNames() != null && getBrandNames().size() > 0) {// 前台传入的参数为空
				// // System.out.println("开始查找对应的品牌id：" + getBrandNames());
				List<String> brandIds = brandService.gainIds(getBrandNames());
				paramMap.put("defaultBrandIds", brandIds);
			}

			if (getDefaultPrices() != null && !getDefaultPrices().trim().equals("")) {
				paramMap.put("defaultPrices", getDefaultPrices());
			}

			// 默认搜索参数
			if (getSearchParam() != null) {
				paramMap.put("searchParam", getSearchParam());
			}

			// 设定列表页初始化查询条件是配件还是手机
			if (getShowType() != null && !getShowType().trim().equals("")) {
				paramMap.put("showType", getShowType());
			} else {
				paramMap.put("showType", "goods");
			}

			return resultPage;
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 获取商品信息
	 * 
	 * @Title: gainGoods
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return List<Goods> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Goods> gainGoods() {
		try {
			logger.info("重新加载数据传入的条件参数：" + getParamStr() + "   order:" + base.getOrder().trim() + "   模糊查找的参数：" + getSearchParam());
			// 把json字符串还原成map对象
			Map<String, Object> map = new HashMap<String, Object>();
			if (getParamStr() != null) {// 前台传入的参数为空
				map = JsonUtil.getMap4Json(getParamStr());
			}

			// 判断传入的类是类型名称name字符串 转化成 类型id字符串存入到map里面
			if (map.containsKey("catNames") && map.get("catNames") != null) {

				// 判断请求的是配件列表就剔除手机
				// 传入的是配件这两个字，查询所有除手机以外的分类id
				if (map.get("catNames").toString().indexOf("配件") >= 0) {// 加载所有配件数据
					// 判断请求的是配件列表就剔除手机
					List<Cat> cats = catService.gainAll();// 所有分类信息
					List<String> catIds = new ArrayList<String>();
					for (int i = 0; i < cats.size(); i++) {
						Cat c = cats.get(i);
						if (c.getName().equals("手机")) {
							cats.remove(i);
						} else {
							catIds.add(c.getId());
						}
					}
					map.put("catIds", catIds);
				} else {
					// 直接把nameList传入到后台，在mapper里面遍历拼接
					List<String> catIds = catService.gainIds((List<String>) map.get("catNames"));
					map.put("catIds", catIds);
				}

			}
			// 根据传入的品牌名称查询品牌id
			if (map.containsKey("branNames")) {
				List<String> brandIds = brandService.gainIds((List<String>) map.get("brandNames"));
				map.put("brandIds", brandIds);
			}
			// 是否分组查询//如果查询条件有单品表字段就分组查询//不用分组查询
			// map.put("grouping", null);
			// 屏幕大小
			if (map.containsKey("screenSize")) {

				String screnSizesStr = map.get("screenSize").toString();
				screnSizesStr = screnSizesStr.substring(2, screnSizesStr.length() - 2);
				String[] screnSizes = screnSizesStr.trim().split("-");
				map.put("maxScreenSize", screnSizes[0]);
				map.put("minScreenSize", screnSizes[1]);
				// map.put("grouping", 1);
			}
			// 如果sort为空那就根据综合数据排序，去获取比重配置数据
			if (base.getSort() == null || "".equals(base.getSort().trim())) {
				map.put("clickNum", ResourceUtil.getSynthesizeSortClickProportion());
				map.put("buyNum", ResourceUtil.getSynthesizeSortBuyProportion());
				map.put("collectNum", ResourceUtil.getSynthesizeSortCollectProportion());
			}

			// 价格区间
			if (map.containsKey("prices")) {
				String pricesStr = map.get("prices").toString().substring(2, map.get("prices").toString().length() - 2);
				String[] prices = pricesStr.split("-");
				if (prices.length > 0) {
					if (prices.length < 2) {// 只有一个最大值
						map.put("maxPrice", Double.parseDouble(prices[0].toString()));
					} else {// 有两个值
						map.put("maxPrice", Double.parseDouble(prices[1].toString()));
						if (Double.parseDouble(prices[0].toString()) <= 0) {
							map.put("minPrice", 0.01);
						} else {
							map.put("minPrice", Double.parseDouble(prices[0].toString()));
						}
					}
				}
			}

			// 根据传入的品牌名称查询品牌id
			if (map.containsKey("netSuitTypes")) {
				List<String> netSuitTypeList = (List<String>) map.get("netSuitTypes");
				String netSuitTypes = "";
				if (netSuitTypeList != null && netSuitTypeList.size() > 0) {
					for (int i = 0; i < netSuitTypeList.size(); i++) {
						String str = netSuitTypeList.get(i).trim();
						netSuitTypes += str;
						if (i < netSuitTypeList.size() - 1) {
							netSuitTypes += "|";
						}
					}
				}
				map.put("netSuitTypes", netSuitTypes);
			}

			// // System.out.println("查看传入的搜索条件：" + getSearchParam());

			map.put("searchParam", getSearchParam().toLowerCase().replaceAll(" ", "%"));

			map.put("sort", base.getSort().trim());
			map.put("orders", base.getOrder().trim());
			// 把当前页数page和每页显示个数rows放进map传入后台查询
			map.put("page", base.getPage());
			map.put("rows", base.getRows());

			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			Members members = memberService.getMemberById(sessionInfo.getUserId());
			map.put("memberId", sessionInfo.getUserId());
			map.put("area", members.getArea());
			map.put("userName", members.getUsername());
			// // System.out.println("开始查询配件信息");
			// logger.info("配件信息：" +
			// JSON.toJSONString(goodsService.gainAccessoriesPage(map)));
			Map m = new HashMap();

			if (map.containsKey("catIds")) {// 判断传入的catid是不是手机，如果是手机或者空就查询goods
											// 否则查询配件
				// 判断cats里面有没有手机的id
				catNames = new ArrayList<String>();
				catNames.add("手机");
				// String shoujiId = catService.gainIds(catNames).get(0);
				String catIds = map.get("catIds").toString();
				if (catIds.indexOf(catService.gainIds(catNames).get(0).toString()) < 0) {// 查找配件信息
					m.put("urlType", "accessory");
					m.put("countNum", goodsService.gainAccessoriesCountNum(map));
					m.put("goodsList", goodsService.gainAccessoriesPage(map));
					// logger.info("查找配件信息列表：" + JSON.toJSONString(m));
					
				} else {// 请求手机goods信息
					m.put("urlType", "goods");
					m.put("countNum", goodsService.gainCountNum(map));
					m.put("goodsList", goodsService.gainByPage(map));// 获取列表信息
				}
			} else {// 查找手机goods信息
				m.put("urlType", "goods");
				m.put("countNum", goodsService.gainCountNum(map));
				List<Goods> goodsList = goodsService.gainByPage(map);
				System.out.println("!!!="+goodsList);
				m.put("goodsList", goodsList);
			}

			//logger.info("商品列表信息：" + JSON.toJSONString(m));

			writeJson(m);

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
		return null;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public List<String> getCatNames() {
		return catNames;
	}

	public void setCatNames(List<String> catNames) {
		this.catNames = catNames;
	}

	public List<String> getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(List<String> brandNames) {
		this.brandNames = brandNames;
	}

	public String getDefaultPrices() {
		return defaultPrices;
	}

	public void setDefaultPrices(String defaultPrices) {
		this.defaultPrices = defaultPrices;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

}
