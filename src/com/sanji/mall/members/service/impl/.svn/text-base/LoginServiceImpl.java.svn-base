/**  
* @Title: CompanyServiceImpl.java
* @Package com.sanji.mall.test.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-15 下午3:51:03
* @version V1.0  
*/
package com.sanji.mall.members.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.members.service.LoginService;
import com.sanji.mall.model.Members;

/**
 * @ClassName: LoginServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:51:03
 */
@Service("loginService")
@Transactional(rollbackFor=Exception.class)
public class LoginServiceImpl implements LoginService{
	
	@Resource
	private MembersMapper membersMapper;

	/* (非 Javadoc)
	* <p>Title: gainCompanyByUsernameAndPassword</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.login.service.LoginService#gainCompanyByUsernameAndPassword()
	*/
	public Members gainMembersByUsernameAndPassword(String username,
			String password) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			map.put("password", password);
			
		return  membersMapper.gainMembersByMap(map);
	}


}
