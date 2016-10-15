/**  
* @Title: HomeService.java
* @Package com.sanji.mall.home.service
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-21 下午1:32:49
* @version V1.0  
*/
package com.sanji.mall.home.service;

/**
 * @ClassName: HomeService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-21 下午1:32:49
 */
public interface HomeService {

	/**
	 * 查询会员总数
	* @Title: gainMemberNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return Long    返回类型
	* @author ZhouZhangbao
	 */
	public Long gainMemberNum();
	
	/**
	 * 根据会员id查询其所在城市
	* @Title: gainAreaById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    id
	* @return String    返回类型
	* @author peter
	 */
	public String gainCityById(String id);
}
