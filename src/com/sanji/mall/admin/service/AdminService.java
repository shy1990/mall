package com.sanji.mall.admin.service;

import java.util.List;

import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Order;

/**
 * @ClassName: AdminService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface AdminService {
	/**
	 * 根据手机号查询信息
	* @Title: getAdminByMoble
	* @Description: TODO(根据手机号查询信息)
	* @param @return    设定文件
	* @return mobile   返回类型
	* @author songbaozhen
	* @throws
	 */
	public Admin getAdminByMoble(String mobile);
	
	/**
	 * 根据id查询信息
	* @Title: getAdminById
	* @Description: TODO(根据手机号查询信息)
	* @param @return    设定文件
	* @return mobile   返回类型
	* @author shihongyue
	* @throws
	 */
	public Admin getAdminById(String id);
	
	/**
	 * 根据登陆名密码查询管理员<BR> 可以是登陆名也可以手机号登录
	* @Title: gainAdminByLogin
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param username
	* @param @param password
	* @param @return    设定文件
	* @return Admin    返回类型
	* @author ZhouZhangbao
	 */
	public Admin gainAdminByLogin(String username ,String password);
	
	/**
	 * 短信通知授权人
	* @Title: gainMsgInfoAdminByRegionsAndType
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param province
	* @param @param city
	* @param @param area
	* @param @param type
	* @param @return    设定文件
	* @return List<String>    返回类型
	* @author ZhouZhangbao
	 */
	public void msgInfoAdminByRegionsAndType(String province ,String city,String area, String type,String userName,Order order);

}
