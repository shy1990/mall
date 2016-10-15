package com.sanji.mall.regions.service;

import java.util.List;

import com.sanji.mall.model.Regions;


/**
 * @ClassName: RegionsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface RegionsService {
	/**
	 * 查询省级
	* @Title: gainProvince
	* @Description: TODO(查询省级)
	* @param @return    设定文件
	* @return List    返回类型
	* @author songbaozhen
	* @throws
	 */
	public List gainProvince();
	/**
	 * 根据id查询市级
	* @Title: gainCity
	* @Description: TODO(根据id查询市级)
	* @param @return    设定文件
	* @return List    返回类型
	* @author songbaozhen
	* @throws
	 */
	public List gainCity(String id);
	/**
	 * 根据id查询地区
	* @Title: gainArea
	* @Description: TODO(根据id查询地区)
	* @param @return    设定文件
	* @return List    返回类型
	* @author songbaozhen
	* @throws
	 */
	public List gainArea(String id);
	/**
	 * 根据id查询乡镇
	* @Title: gainArea
	* @Description: TODO(根据id查询乡镇)
	* @param @return    设定文件
	* @return List    返回类型
	* @author yinhsong
	* @throws
	 */
	public List gainTown(String id);
	
	/**
	 * @Title: gainCityListByPid
	 * @Description: TODO(根据省份id查询其市列表)
	 * @param @param pid
	 * @param @return    设定文件
	 * @return List<Regions>    返回类型
	 */
	public List<Regions> gainCityListByPid(String pid);
	/**
	 * @Title: gainAreaListByCityId
	 * @Description: TODO(根据省份id查询其市列表)
	 * @param @param cityId
	 * @param @return    设定文件
	 * @return List<Regions>    返回类型
	 */
	public List<Regions> gainAreaListByCityId(String cityId);

}
