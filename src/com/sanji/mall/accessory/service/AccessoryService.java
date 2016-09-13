package com.sanji.mall.accessory.service;

import java.util.List;

import com.sanji.mall.model.Accessory;

/**
 * 
 * @ClassName: AccessoryService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author WuJiming wzslw_163_com
 * @date 2014年11月5日 下午4:32:42
 */
public interface AccessoryService {
	/**
	 * 
	 * @Title: getById
	 * @Description: 通过id获取
	 * @param id
	 * @return    设定文件 Accessory    返回类型
	 * @throws
	 */
	public Accessory getById(String id);

	public Accessory getGiftById(String targetId);

	/**
	 * 
	 * @Title: getCommon
	 * @Description: 通用配件
	 * @param type
	 * @return    设定文件 List<Accessory>    返回类型
	 * @throws
	 */
	public List<Accessory> getCommon(String type, int page, int size);

}
