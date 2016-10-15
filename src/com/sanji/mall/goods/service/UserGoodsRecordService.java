package com.sanji.mall.goods.service;

import java.util.List;

import com.sanji.mall.model.UserGoodsRecord;

public interface UserGoodsRecordService {
	/**
	 * 保存一个浏览记录
	 * 
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int save(UserGoodsRecord record);

	/**
	 * 批量添加
	 * 
	 * @Title: inserts
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param list
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	int inserts(List<UserGoodsRecord> list);

	/**
	 * 定时自动添加访问记录
	 * 
	 * @Title: autoInsert
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void autoInsert();
}
