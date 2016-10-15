package com.sanji.mall.regions.dao;

import java.util.List;

import com.sanji.mall.model.Regions;

public interface RegionsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Regions record);

    int insertSelective(Regions record);

    Regions selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Regions record);

    int updateByPrimaryKey(Regions record);
    /**
	 *查询省级
	* @Title: gainProvince
	* @Description: TODO(查询省级)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
	public List<Regions> gainProvince();
	/**
	 *根据id查询市级
	* @Title: gainCity
	* @Description: TODO(根据id查询市级)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
	public List<Regions> gainCity(String id);
	/**
	 *根据id查询区级
	* @Title: gainCity
	* @Description: TODO(根据id查询区级)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author songbaozhen
	 */
	public List<Regions> gainArea(String id);
	/**
	 *根据id查询乡镇街道
	* @Title: gainCity
	* @Description: TODO(根据id查询乡镇街道)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型
	* @author yinhsong
	 */
	public List<Regions> gainTown(String id);
	
	/**
     * @Title: gainCityListByPid
     * @Description: TODO(根据省份id查询其市列表)
     * @param @param pid
     * @param @return    设定文件
     * @return List<Regions>    返回类型
     */
    public List<Regions> gainCityListByPid(String pid);
    
    public List<Regions> gainAreaListByCityId(String cityId);
}