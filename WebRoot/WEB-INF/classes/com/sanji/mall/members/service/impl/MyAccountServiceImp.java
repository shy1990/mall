package com.sanji.mall.members.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.members.service.MyAccountService;
import com.sanji.mall.model.Members;

/**
 * @ClassName: myAccountServiceImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:51:03
 */
@Service("myAccountService")
@Transactional(rollbackFor = Exception.class)
public class MyAccountServiceImp implements MyAccountService {
	@Resource
	private MembersMapper membersMapper;

	/*
	 * (非 Javadoc) <p>Title:gainMemberByMobile</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#gainMemberByMobile
	 */
	@SuppressWarnings("unused")
	public Members gainMemberByMobile(String mobile) {
		Members member = membersMapper.gainByMobile(mobile);
		if (member != null) {
			return member;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title:modifyMember</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.members.service.MyAccountService#updateByPrimaryKeySelective
	 */
	public void modifyMember(Members member) {
		membersMapper.updateByPrimaryKeySelective(member);
	}

	/*
	 * (非 Javadoc) <p>Title:gainMemberByPswAndMobile</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.members.service.MyAccountService#gainMemberByPswAndMobile
	 */
	public boolean gainMemberByPswAndMobile(String password, String mobile) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("mobile", mobile);
		Long mNum = membersMapper.gainMemberByMap(map);
		if (mNum > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (非 Javadoc) <p>Title:addMemberMobile</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#addMemberMobile
	 */
	public void addMemberMobile(Members member) {
		membersMapper.updateMember(member);
	}

	/*
	 * (非 Javadoc) <p>Title:gainMobileById</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#gainMobileById
	 */
	public Members gainMobileById(String id) {
		Members member = membersMapper.gainMobileById(id);
		if (member != null && !"".equals(member)) {
			return member;
		}
		return null;
	}

	/*
	 * (非 Javadoc) <p>Title:updateMember</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#updateMember
	 */
	public void updateMember(Members member) {
		membersMapper.updateByPrimaryKeySelective(member);
		
	}
	/*
	 * (非 Javadoc) <p>Title:modifyPasswordByMobile</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#modifyPasswordByMobile
	 */
	public void modifyPasswordByMobile(Members member) {
		membersMapper.modifyPasswordByMobile(member);
	}
	/*
	 * (非 Javadoc) <p>Title:existMemberByMobile</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sanji.mall.members.service.MyAccountService#existMemberByMobile
	 */
	public boolean gainMemberMobile(String mobile) {
		// TODO Auto-generated method stub
		Long mobileNum = membersMapper.gainMemberMobile(mobile);
		if (mobileNum > 0) {
			return true;
		}
		return false;
		
	}

}
