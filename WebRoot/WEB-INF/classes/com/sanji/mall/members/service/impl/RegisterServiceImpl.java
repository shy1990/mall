package com.sanji.mall.members.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.members.service.RegisterService;
import com.sanji.mall.model.Members;

@Service("registerService")
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl implements RegisterService{

	@Resource
	private MembersMapper membersMapper;
	/**
	 * (non-Javadoc)
	 * @Title:addMembers
	 * @Description: TODO(增加会员)
	 * @param members
	 * @return
	 */
	public void addMembers(Members members) {
		membersMapper.insertSelective(members);
	}
	/**
	 * (non-Javadoc)
	 * @Title:existMemberByUsername
	 * @Description: TODO(验证会员)
	 * @param username
	 * @return
	 */
	public boolean existMember(String username) {
		int num =membersMapper.existMemberByUsername(username);
		return num > 0;
	}
	/**
	 * (non-Javadoc)
	 * @Title:existMemberByUsername
	 * @Description: TODO(验证会员)
	 * @param username
	 * @return
	 */
	public boolean existMobile(String mobile) {
		Long num =membersMapper.existMemberByMobile(mobile);
		return num > 0;
	}

}
