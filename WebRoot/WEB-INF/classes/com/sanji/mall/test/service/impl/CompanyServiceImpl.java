/**  
* @Title: CompanyServiceImpl.java
* @Package com.sanji.mall.test.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-15 下午3:51:03
* @version V1.0  
*/
package com.sanji.mall.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.model.Companys;
import com.sanji.mall.test.dao.CompanysMapper;
import com.sanji.mall.test.service.CompanyService;

/**
 * @ClassName: CompanyServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:51:03
 */
@Service("companySerice")
@Transactional(rollbackFor=Exception.class)
public class CompanyServiceImpl implements CompanyService{
	
	@Resource
	private CompanysMapper companysMapper;

	/* (非 Javadoc)
	* <p>Title: selectCompanyByUserName</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.test.service.CompanyService#selectCompanyByUserName()
	*/
	
	public List<Companys> selectCompanyByUserName(String userName) {
		// TODO Auto-generated method stub
		return companysMapper.gainCompanysByUserName(userName);
	}

}
