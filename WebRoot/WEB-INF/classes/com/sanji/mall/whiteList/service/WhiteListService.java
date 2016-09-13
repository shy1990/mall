package com.sanji.mall.whiteList.service;
/**
 * @ClassName:WhiteListService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface WhiteListService {
	
	/**
	 * 根据自动获取ip查询白名单ip
	* @Title: gainWhiteListByIp
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return List<Company>    返回类型
	* @author ZhouZhangbao
	* @throws
	 */
	public boolean gainWhiteListByIp(String ip);

}
