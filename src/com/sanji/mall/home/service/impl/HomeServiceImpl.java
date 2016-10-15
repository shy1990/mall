/**  
* @Title: HomeServiceImpl.java
* @Package com.sanji.mall.home.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-21 下午1:33:20
* @version V1.0  
*/
package com.sanji.mall.home.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.home.service.HomeService;
import com.sanji.mall.members.dao.MembersMapper;

/**
 * @ClassName: HomeServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-21 下午1:33:20
 */
@Service("homeService")
@Transactional(rollbackFor = Exception.class)
public class HomeServiceImpl implements HomeService{
	
	@Resource
	private MembersMapper membersMapper;

	/* (非 Javadoc)
	* <p>Title: gainMemberNum</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.home.service.HomeService#gainMemberNum()
	*/
	
	public Long gainMemberNum() {
		// TODO Auto-generated method stub
		return membersMapper.gainMemberNum();
	}

	public String gainCityById(String id) {
		// TODO Auto-generated method stub
		return membersMapper.gainCityById(id);
	}

}
