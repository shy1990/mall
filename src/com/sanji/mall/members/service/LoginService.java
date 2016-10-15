/**  
* @Title: CompanyService.java
* @Package com.sanji.mall.test.service
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-15 下午3:48:23
* @version V1.0  
*/
package com.sanji.mall.members.service;

import java.util.List;

import com.sanji.mall.model.Members;


/**
 * @ClassName: LoginService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface LoginService {
	
	/**
	 * 根据用户 登陆名查询用户信息
	* @Title: gainMembersByUsernameAndPassword
	* @Description: TODO(根据用户 登陆名查询用户信息)
	* @param @return    设定文件
	* @return Companys    返回类型
	* @author songbaozhen
	* @throws
	 */

	public Members gainMembersByUsernameAndPassword(String username,
			String password);
	
}
