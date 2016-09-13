package com.sanji.mall.members.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Members;

@Service("memberService")
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

	@Resource
	private MembersMapper memberMapper;

	public Members getMemberById(String id) {
		return memberMapper.selectByPrimaryKey(id);
	}
	
	public String gainBlackMemberById(String memberId) {
		// TODO Auto-generated method stub
		return memberMapper.gainBlackMemberById(memberId);
	}

	public Members gainMembersDetailById(String id) {
		// TODO Auto-generated method stub
		return memberMapper.gainMembersDetailById(id);
	}

	/**
	 * 获取member表的id和username
	 */
	public List<Members> gainMembers(Members members) {
		return memberMapper.gainMembers(members);
	}

	/**
	 * 根据username进行模糊查询
	 */
	public List<Members> gainMembersByUsername(Members members) {
		return memberMapper.gainMembersByUsername(members);
	}
	/**
	 * 根据主键进行更新
	 */
	public int updateByPrimaryKey(Members record) {
		// TODO Auto-generated method stub
		return memberMapper.updateByPrimaryKey(record);
	}
	/**
	 * 根据rid查询
	 */
	public Members gainRegionsOfUser(String rid, String userId) {
		// TODO Auto-generated method stub
		Map<String, String> idmap = new HashMap<String, String>();
		idmap.put("id", userId);
		idmap.put("rid", rid);
		
		return memberMapper.gainMemberByIdMap(idmap);
	}
	/**
	 * 增加用户
	 */
	public void addMeber(Members member) {
		// TODO Auto-generated method stub
		memberMapper.insertSelective(member);
	}
	/**
	 * 根据删除用户
	 */
	public void deleteMemberByUsername(String userName) {
		// TODO Auto-generated method stub
		memberMapper.deleteByUsername(userName);
	}
	/**
	 * 根据手机号查询用户
	 */
	public boolean exsixtMebByMobile(Members member) {
		// TODO Auto-generated method stub
		long mobileNum = memberMapper.gainMebByMobile(member);
		if(mobileNum > 0){
			return false;
		}
		return true;
	}

	public void deleteByMlistAndPassword(List<Members> mList) {
          if(mList.size() > 0){
        	  memberMapper.deleteByList(mList);
          }      
	}

	public boolean exsixtMemberByMobile(Members member) {
		long mobileNum = memberMapper.gianMemberForOrder(member);
		if(mobileNum > 0){
			return false;
		}
		return true;
	}

	public void EnabledMeb(List<String> mList) {
		memberMapper.updateMemberByList(mList);
	}

	public int updateByPrimaryKeySelective(Members members) {
		// TODO Auto-generated method stub
		return memberMapper.updateByPrimaryKeySelective(members);
	}

	public List<String> getAllMobiles(){
		return memberMapper.getAllMobiles();
	}
	
	public List<Members> addPoint(){
		return memberMapper.addPoint();
	}
}
