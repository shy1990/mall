package com.sanji.mall.brand.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.model.Brand;

/**
 * 品牌类
 * 
 * @ClassName: BrandAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-13 下午8:12:41
 * 
 */
@Namespace("brandAction")
@Action(value = "brandAction", results = { @Result(name = "succeed", location = "/goods/goods.jsp") })
public class BrandAction extends BaseAction implements ModelDriven<Brand> {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BrandAction.class);

	private Brand brand;

	public Brand getModel() {
		// TODO Auto-generated method stub
		if (brand == null) {
			brand = new Brand();
		}
		return brand;
	}

}
