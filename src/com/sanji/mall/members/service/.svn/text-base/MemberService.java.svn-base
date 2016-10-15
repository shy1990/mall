package com.sanji.mall.members.service;

import java.math.BigDecimal;
import java.util.List;

import com.sanji.mall.model.Members;

public interface MemberService {
	public Members getMemberById(String id);
	
	public String gainBlackMemberById(String memberId);

	/**
	 * 根据用户id获得用户详情
	 * 
	 * @Title: gainMembersDetailById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Members 返回类型
	 * @author 田超强
	 * @throws
	 */
	Members gainMembersDetailById(String id);

	/**
	 * 获取所有members会员的id和username
	 * 
	 * @Title:gainMembersByMemberId
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Members 返回类型
	 * @throws
	 * @param id
	 * @return
	 */
	public List<Members> gainMembers(Members members);

	public List<Members> gainMembersByUsername(Members members);

	/**
	 * 获取members的行数
	 * 
	 * @Title:gainMembersCount
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param members
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @throws
	 * @param members
	 * @return
	 */
	// public Long gainMembersCount(Members members);

	/**
	 * 根据主键更新一条数据
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(根据主键更新一条数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int updateByPrimaryKey(Members record);
	
	int updateByPrimaryKeySelective(Members members);
	/**
	 * 
	 * 
	 * @Title: gainRegionsOfUser
	 * @Description: TODO(根据主键更新一条数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */

	public Members gainRegionsOfUser(String rid, String id);
	/**
	 * 
	 * 
	 * @Title: addMeber
	 * @Description: TODO(增加用户)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */

	public  void addMeber(Members member);

	/**
	 * 
	 * 
	 * @Title: deleteMemberByUsername
	 * @Description: TODO(删除用户)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	public void deleteMemberByUsername(String username);
	/**
	 * 
	 * 
	 * @Title: gainMebByMobile
	 * @Description: TODO(查询用户手机号是否重复)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	public boolean exsixtMebByMobile(Members member);

	//public void deleteByMobileAndPsaaword(String mobile, String string);

	public void deleteByMlistAndPassword(List<Members> list);

	public boolean exsixtMemberByMobile(Members member);

	public void EnabledMeb(List<String> mList);
	
	public List<String> getAllMobiles();
	
	public List<Members> addPoint();
}
