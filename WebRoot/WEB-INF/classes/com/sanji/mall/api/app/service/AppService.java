package com.sanji.mall.api.app.service;

import com.sanji.mall.model.App;

public interface AppService {
	/**
	 * 根据memberId查询app信息
	 * @Title:gainAppByMemberId
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param memberId
	 * @param @return 设定文件
	 * @return  App 返回类型
	 * @throws
	 * @param memberId
	 * @return
	 */
	public boolean gainAppByMemberId(String memberId);

	public App gainAppIDByMemberId(String memberId);
	/**
	 * 添加客户信息(除图片之外)
	 * @Title:addApp
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param app 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param app
	 */
	public void addApp(App app);
	
	/**
	 * 修改已经添加的用户信息(除图片之外)
	 * @Title:updateOriginalApp
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param app 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param app
	 */
	public void updateOriginalApp(App app);
	
	/**
	 * 添加客户信息(添加图片)
	 * @Title:addAppImg
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param app 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param app
	 */
	public void addAppImg(App app);
	/**
	 * 修改客户信息(修改图片)
	 * @Title:updateApp
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param app 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param app
	 */
	public void updateAppImg(App app);
	/**
	 * 根据商铺名称获取商铺照片
	 * @Title:gainAppImgByMemeberId
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param shopName 设定文件
	 * @return:App 返回类型
	 * @throws
	 */
	public App gainAppImgByMemeberId(String memberid);
	/**
	 * 根据memberId查询用户店铺信息
	 * @Title:gainOnlyAppByMemeberId
	 * @Description:TODO(根据memberId查询用户店铺信息)
	 * @param @param memberId 
	 * @return void 
	 * @throws
	 * @param memberId
	 */
	public App gainOnlyAppByMemeberId(String memberId);
}
