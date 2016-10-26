package com.sanji.mall.members.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sanji.mall.model.Members;

public interface MembersMapper {
	/**
	 * 根据主键删除一条数据
	 * 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO(根据主键删除一条数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入一条新数据
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(插入一条新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int insert(Members record);

	/**
	 * 有选择性的插入一条新数据
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(插入一条新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int insertSelective(Members record);

	/**
	 * 根据主键查询
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO(根据主键查询)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Members 返回类型
	 * @author songbaozhen
	 */
	Members selectByPrimaryKey(String memberId);
	
	/**
	 * 查询在黑名单的用户
	 * 
	 * @Title: gainBlackMemberById
	 * @Description: TODO(查询)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author yinhsong
	 */
	String gainBlackMemberById(String memberId);

	/**
	 * 根据主键有选择性的更新一条新数据
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: TODO(根据主键更新一条新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int updateByPrimaryKeySelective(Members record);

	/**
	 * 根据主键更新一条新数据
	 * 
	 * @Title: updateByPrimaryKeyWithBLOBs
	 * @Description: TODO(根据主键更新一条新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	int updateByPrimaryKeyWithBLOBs(Members record);

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

	/**
	 * 插入一条新数据
	 * 
	 * @Title: insertSelective
	 * @Description: TODO(插入一条新数据)
	 * @param @param record
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	public List<Members> gainMembersByUserName(String userName);

	/**
	 * 根据map查询用户数据
	 * 
	 * @Title: gainMembersByMap
	 * @Description: TODO(根据map查询用户数据)
	 * @param @param map
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */

	public Members gainMembersByMap(Map<String, String> map);

	/**
	 * 根据用户名判断用户是否存在
	 * 
	 * @Title: existMemberByUsername
	 * @Description: TODO(根据用户名判断用户是否存在)
	 * @param @param username
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author songbaozhen
	 */
	public int existMemberByUsername(String username);

	/**
	 * 根据手机号判断用户是否存在
	 * 
	 * @Title: gainMemberByMoblie
	 * @Description: TODO(根据手机号判断用户是否存在)
	 * @param @param mobile
	 * @param @return 设定文件
	 * @return Members 返回类型
	 * @author songbaozhen
	 */

	public Members gainByMobile(String mobile);

	/**
	 * @Title: gainMemberNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @author ZhouZhangbao
	 */
	public Long gainMemberNum();

	/**
	 * @Title: gainMemberByMap
	 * @Description: TODO(根据map查询用户)
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @author ZhouZhangbao
	 */
	public Long gainMemberByMap(Map<String, String> map);

	/**
	 * 
	 * @Title: selectPointByPrimaryKey
	 * @Description: 查询积分
	 * @param userId
	 * @return    设定文件 int    返回类型
	 * @throws
	 */
	public int selectPointByPrimaryKey(String id);

	public void updateMember(Members member);

	public Members gainMobileById(String id);

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
	public Members gainMembersDetailById(String id);
	/**
	 * 根据用户手机号修改密码
	 * 
	 * @Title: modifyPasswordByMobile
	 * @Description: TODO(这根据用户手机号修改密码)
	 * @param @param member
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public void modifyPasswordByMobile(Members member);
	
	/**
	 * 获取所有members会员的id和username
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
	 * @Title:gainMembersCount
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param members
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @throws
	 * @param members
	 * @return
	 */
	//public Long gainMembersCount(Members members);
	/**
	 * 获取members的行数
	 * @Title:gainMembersCount
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param members
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @throws
	 * @param members
	 * @return
	 */
	public Long gainMemberMobile(String mobile);
	/**
	 * 获取members的行数
	 * @Title:gainMemberByIdMap
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param members
	 * @param @return 设定文件
	 * @return Long 返回类型
	 * @throws
	 * @param members
	 * @return
	 */
	public Members gainMemberByIdMap(Map<String, String> idmap);
	/**
	 * 根据用户名删除
	 * deleteByUsername
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param username
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param userName
	 * @return
	 */
	void deleteByUsername(String userName);
	/**
	 * 根据电话号码查询用户
	 * gainMebByMobile
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param member
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param member
	 * @return
	 */
	long gainMebByMobile(Members member);
	/**
	 * 批量删除用户(逻辑删除)
	 * deleteByList
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param mList
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param mList
	 * @return
	 */
	public void deleteByList(List<Members> mList);
	/**
	 * 判断用户是否下过单
	 * gianMemberForOrder
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param member
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param member
	 * @return
	 */
	long gianMemberForOrder(Members member);
	/**
	 * 单条或批量更新用户
	 * updateMemberByList
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param mList
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param mList
	 * @return
	 */
	void updateMemberByList(List<String> mList);
	/**
	 * 根据电话号码查询用户，判断是否已存在
	 * updateMemberByList
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @param mList
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @throws
	 * @param mList
	 * @return
	 */
	Long existMemberByMobile(String mobile);
	
	List<String> getAllMobiles();
	
	/**
	 * 活动时给用户加积分
	 * @return
	 */
	public List<Members> addPoint();
	
	/**
	 * 根据Id查询用户所在城市
	 * @Title: gainAreaById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return id
	 * @return String 返回类型
	 * @author peter
	 */
	public String gainCityById(String id);

	public String gainMobileByOrderNum(String orderNum);

	public String gainCityByOrderId(String orderId);
	
}