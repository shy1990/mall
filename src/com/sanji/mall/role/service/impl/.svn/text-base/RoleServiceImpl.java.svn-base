package com.sanji.mall.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.role.dao.RoleMapper;
import com.sanji.mall.role.service.RoleService;
/**
 * @ClassName: RoleServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:51:03
 */
@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	/* (非 Javadoc)
	* <p>Title: gainRoleByAid</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.role.service#gainRoleByAid(String aid)
	*/
	public String gainRoleByAid(String aid) {
		
		String zhiwei = roleMapper.gainByAid(aid);
		
		return zhiwei;
		
	}

}
