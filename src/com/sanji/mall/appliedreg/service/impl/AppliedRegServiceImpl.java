package com.sanji.mall.appliedreg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.appliedreg.dao.AppliedRegMapper;
import com.sanji.mall.appliedreg.service.AppliedRegService;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MyAccountService;
import com.sanji.mall.model.AppliedReg;
import com.sanji.mall.pojo.Json;

/**
 * 
 * @author Administrator
 *
 */
@Service("appliedRegService")
@Transactional(rollbackFor = Exception.class)
public class AppliedRegServiceImpl implements AppliedRegService {
	
	@Resource
	private AppliedRegMapper appliedRegMapper;
	@Resource
	private MyAccountService myAccountService;

	@Override
	public Json addAppliedReg(AppliedReg appliedReg)throws Exception {
		Json json = new Json();
		String mobile = appliedReg.getMobile();
		if (!myAccountService.gainMemberMobile(mobile)) {//判断是否是已经注册会员
			if (exsistReg(mobile)) {//判断是否提交过申请
				appliedReg.setId(ToolsUtil.getUUID());
				appliedReg.setApplyTime(new Date());
				appliedRegMapper.insertSelective(appliedReg);
				String address = appliedRegMapper.gainAppliedRegAddress(mobile);//18561243795
				MsgUtil.MsgAppliedRegisterSuccess("18561243795",
						appliedReg.getShopName(), appliedReg.getTrueName(),
						appliedReg.getMobile(),address);
			}
			json.setMsg("您已申请成功,请耐心等待");
			json.setSuccess(true);
			 return json;
		}
		json.setMsg("您已经是三际会员");
		json.setSuccess(false);
		 return json;
	}

	public boolean exsistReg(String phoneNum) {
		Long count = appliedRegMapper.gainAppliedRegByMobile(phoneNum);
		if(count > 0){
			return false;
		}
		return true;
	}

	
}
