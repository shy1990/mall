package com.sanji.mall.goodsSku.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.brand.service.BrandService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.model.GoodsSku;

/**
 * 商品单品信息action类
 * 
 * @ClassName: GoodsSkuAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-16 下午8:03:59
 * 
 */
@Namespace("/goodsSku")
@Action(value = "goodsSkuAction", results = { @Result(name = "succeed", location = "/admin/goods/goods.jsp") })
public class GoodsSkuAction extends BaseAction implements ModelDriven<GoodsSku> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GoodsSkuAction.class);

	private GoodsSku goodsSku;

	public GoodsSku getModel() {
		// TODO Auto-generated method stub
		if (goodsSku == null) {
			goodsSku = new GoodsSku();
		}
		return goodsSku;
	}

	@Resource
	private GoodsSkuService goodsSkuService;

	@Resource
	private BrandService brandService;

	// 根据价格区间查找数据的时候price作为最小数，maxPrice作为最大数
	private Double maxPrice;

	/**
	 * 根据价格获取某区间的单品列表
	 * 
	 * @Title: doNotNeedSession_gainByPrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @author 田超强
	 * @throws
	 */
	public void gainByPrice() {
		try {
			// System.out.println("获取价格区间数据列表：" + getMaxPrice() + " " +
			// goodsSku.getPrice() + "   page:" + goodsSku.getPage());
			Double priceInterval = (double) ResourceUtil.getComparisonInterval();// 价格浮动差值
			Map<String, String> priceSectionParame = new HashMap<String, String>();
			priceSectionParame.put("minPrice", (goodsSku.getPrice().doubleValue() - priceInterval) + "");
			priceSectionParame.put("maxPrice", (getMaxPrice() + priceInterval) + "");
			priceSectionParame.put("page", goodsSku.getPage() + "");
			priceSectionParame.put("rows", goodsSku.getRows() + "");

			List<GoodsSku> goodsSkus = goodsSkuService.gainByPrice(priceSectionParame);

			// logger.info(writeJsonByExcludesProperties(goodsSkus, null));

			String jsonStr = writeJsonByExcludesProperties(goodsSkus, null).replaceAll("	", "").trim();
			// System.out.println("转换成json后的数据：" + jsonStr);

			getResopnse().setContentType("text/html;charset=utf-8");
			getResopnse().getWriter().write(jsonStr);
			getResopnse().getWriter().flush();
			getResopnse().getWriter().close();

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public static void main(String[] args) {
		GoodsSku g = new GoodsSku();
		// System.out.println(JSON.toJSONString(g));
	}

}
