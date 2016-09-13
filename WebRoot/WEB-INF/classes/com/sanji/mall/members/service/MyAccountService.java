package com.sanji.mall.members.service;

import com.sanji.mall.model.Members;
/**
 * @ClassName: myActionService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface MyAccountService {
	/**
	 * 根据用户手机号查询用户信息
	* @Title: gainMemberByMobile
	* @Description: TODO(根据用户手机号查询用户信息)
	* @param @return    设定文件
	* @return Members    返回类型
	* @author songbaozhen
	* @throws
	 */

	public Members gainMemberByMobile(String mobile);
	/**
	 * 修改用户信息(密码)
	* @Title: modifyMember
	* @Description: TODO(修改用户信息(密码))
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	* @throws
	 */
	public void modifyMember(Members member);
	/**
	 * 根据用户手机号和密码查询用户信息
	* @Title: gainMemberByPswAndMobile
	* @Description: TODO(根据用户手机号查询用户信息)
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author songbaozhen
	* @throws
	 */
	public boolean gainMemberByPswAndMobile(String password, String mobile);
	
	/**
	 * 绑定手机号码
	* @Title: gainMemberByPswAndMobile
	* @Description: TODO(绑定手机号码)
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	* @throws
	 */
	public void addMemberMobile(Members member);
	/**
	 * 查询手机号码
	* @Title: gainMemberByPswAndMobile
	* @Description: TODO(查询手机号码)
	* @param @return    设定文件
	* @return String    返回类型
	* @author songbaozhen
	* @throws
	 */
	public Members gainMobileById(String id);
	/**
	 * 
	* @Title: updateMember
	* @Description: TODO(更新会员信息)
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	* @throws
	 */
	public void updateMember(Members member);
	
	/**
	 * 
	* @Title: modifyPasswordByMobile
	* @Description: TODO(更新会员密码)
	* @param @return    设定文件
	* @return void    返回类型
	* @author songbaozhen
	* @throws
	 */
	public void modifyPasswordByMobile(Members member);
	/**
	 * 
	* @Title: existMemberByMobile
	* @Description: TODO(判断手机号是否已被绑定)
	* @param @return    设定文件
	* @return boolean    返回类型
	* @author songbaozhen
	* @throws
	 */
	public boolean gainMemberMobile(String mobile);

}
