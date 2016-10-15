/**  
* @Title: GoodsHotAction.java
* @Package com.sanji.mall.goods.action
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-23 下午2:53:48
* @version V1.0  
*/
package com.sanji.mall.goods.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.brand.action.BrandAction;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.goods.service.GoodsHotService;
import com.sanji.mall.model.Goods;
import com.sanji.mall.model.GoodsHot;
import com.sanji.mall.model.GoodsSku;

/**
 * @ClassName: GoodsHotAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-23 下午2:53:48
 */
@Namespace("/goods")
@Action(value = "goodsHotAction", results = { 
		@Result(name = "succeed", location = "/goods/goods.jsp"),
		@Result(name = "goodsTop", location = "/admin/goods/goodsTop.jsp")})
public class GoodsHotAction extends BaseAction implements ModelDriven<GoodsHot>{
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GoodsHotAction.class);
	
	private GoodsHot goodsHot = new GoodsHot();
	private List<GoodsSku> goodsSkus ;
	
	@Resource
	private GoodsHotService goodsHotService;

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 根据热销类型获取相应的热销商品
	* @Title: gainGoodsHotByType
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void gainGoodsHotByType(){
		try {
			goodsSkus = goodsHotService.gainGoodsHotByType(goodsHot);
			writeJson(goodsSkus);
		} catch (Exception e) {
			logger.error("获取热销产品出现异常："+e);
		}
		
	}
	/**
	 * 根据热销类型获取相应的热销排行
	* @Title: gainGoodsHotByPhone
	* @Title: gainGoodsHotByBrand
	* @Title: gainGoodsHotBy4G
	* @Title: gainGoodsHotByLt3G
	* @Title: gainGoodsHotByYd3G
	* @Title: gainGoodsHotByDx3G
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return list    返回类型
	* @author songbaozhen
	 */
	public String gainGoodsHotTop(){
		try {
			
			request.setAttribute("phoneTopList",goodsHotService.gainGoodsHotByPhone(goodsHot));
			request.setAttribute("brandTopList",goodsHotService.gainGoodsHotByBrand(goodsHot));
			request.setAttribute("fourGTopList", goodsHotService.gainGoodsHotBy4G(goodsHot));
			request.setAttribute("ltThreeGTopList",goodsHotService.gainGoodsHotByLt3G(goodsHot));
			request.setAttribute("ydThreeGTopList", goodsHotService.gainGoodsHotByYd3G(goodsHot));
			request.setAttribute("dxThreeGTopList", goodsHotService.gainGoodsHotByDx3G(goodsHot));
			return "goodsTop";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "goodsTop";
		
	}

	

	

	/**
	 * @return the goodsHot
	 */
	public GoodsHot getGoodsHot() {
		return goodsHot;
	}

	/**
	 * @param goodsHot the goodsHot to set
	 */
	public void setGoodsHot(GoodsHot goodsHot) {
		this.goodsHot = goodsHot;
	}

	/* (非 Javadoc)
	* <p>Title: getModel</p>
	* <p>Description: </p>
	* @return
	* @see com.opensymphony.xwork2.ModelDriven#getModel()
	*/
	
	public GoodsHot getModel() {
		// TODO Auto-generated method stub
		return goodsHot;
	}
	





	/**
	 * @return the goodsSkus
	 */
	public List<GoodsSku> getGoodsSkus() {
		return goodsSkus;
	}





	/**
	 * @param goodsSkus the goodsSkus to set
	 */
	public void setGoodsSkus(List<GoodsSku> goodsSkus) {
		this.goodsSkus = goodsSkus;
	}
	
	
	

}
