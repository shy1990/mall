package com.sanji.mall.members.service;

import com.sanji.mall.model.Members;

public interface RegisterService {
	
	/**
	 * 增加会员
	 * @Title: addMembers
	 * @Description: TODO(增加会员)
	 * @param members
	 * @return    设定文件
	 * @return void    返回类型
	 */
	public void addMembers(Members members);
	/**
	 * 验证会员是否存在
	 * @Title: existMember
	 * @Description: TODO(验证会员是否存在)
	 * @param username
	 * @return    设定文件
	 * @return void    返回类型
	 */
	public boolean existMember(String username);
	/**
	 * 验证手机号是否存在
	 * @Title: existMobile
	 * @Description: TODO(验证会员是否存在)
	 * @param mobile
	 * @return    设定文件
	 * @return void    返回类型
	 */
	public boolean existMobile(String mobile);

}
