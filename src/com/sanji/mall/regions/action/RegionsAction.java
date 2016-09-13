/**  
 * @Title: RegionsAction.java
 * @Package com.sanji.mall.regions.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2015-1-19 上午11:09:56
 * @version V1.0  
 */
package com.sanji.mall.regions.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.junit.Test;

import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.model.Regions;
import com.sanji.mall.regions.service.RegionsService;

/**
 * @ClassName: RegionsAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2015-1-19 上午11:09:56
 */
@Namespace("/")
@Action(value = "regionsAction")
public class RegionsAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */

	private static final long serialVersionUID = 1L;
	@Resource
	private RegionsService regionsService;

	/**
	 * @Title: getCitys
	 * @Description: TODO(根据省份获取城市列表)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 周张豹
	 */
	public void getCitys() {
		String province = request.getParameter("province");// 获取省
		List<Regions> regionList = new ArrayList<Regions>();
		if (null != province && !"".equals(province)) {
			regionList = gainCityList(province);
		}
		writeJson(regionList);
	}

	/**
	 * @Title: getAreas
	 * @Description: TODO(根据市获取区列表)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 周张豹
	 */
	public void getAreas() {
		String city = request.getParameter("city");// 获取市
		List<Regions> regionList = new ArrayList<Regions>();
		if (null != city && !"".equals(city)) {
			regionList = gainAreaList(city);
		}
		writeJson(regionList);
	}

	/**
	 * 调用
	 */
	private List<Regions> gainCityList(String proId) {
		return regionsService.gainCityListByPid(proId);
	}

	private List<Regions> gainAreaList(String cityId) {
		return regionsService.gainAreaListByCityId(cityId);
	}

	@Test
	public void _data() {
		List<Regions> regionList = regionsService.gainProvince();
		// System.out.println(regionList);

	}
}