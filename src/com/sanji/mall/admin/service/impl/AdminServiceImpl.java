package com.sanji.mall.admin.service.impl;

import com.sanji.mall.admin.dao.AdminMapper;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @ClassName: AdminServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:51:03
 */
@Service("adminService")
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminMapper adminMapper;
	@Resource
	private MembersMapper membersMapper;
	/* (非 Javadoc)
	* <p>Title: getAdminByMoble</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.admin.service#getAdminByMoble(String mobile)
	*/
	public Admin getAdminByMoble(String mobile){
	 
	 Admin	admin = adminMapper.gainByMoble(mobile);
	 if(admin != null){
		 return admin;
	 }
	 return null;
	}
	/* (非 Javadoc)
	* <p>Title: gainAdminByLogin</p>
	* <p>Description: </p>
	* @param username
	* @param password
	* @return
	* @see com.sanji.mall.admin.service.AdminService#gainAdminByLogin(java.lang.String, java.lang.String)
	*/
	
	public Admin gainAdminByLogin(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		return adminMapper.gainAdminBylogin(admin);
	}

	/* (非 Javadoc)
	* <p>Title: msgInfoAdminByRegionsAndType</p>
	* <p>Description: </p>
	* @param province
	* @param city
	* @param area
	* @param type
	* @param order
	* @see com.sanji.mall.admin.service.AdminService#msgInfoAdminByRegionsAndType(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.sanji.mall.model.Order)
	*/
	public void msgInfoAdminByRegionsAndType(String province, String city,
		String area, String type,String userName, Order order) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("province", province);
		map.put("city", city);
		map.put("area", area);
		map.put("type", type);
		String mobiles = "";
		String proFile = ResourceUtil.get("proFile");
		if("test".equals(proFile)){
			List<String> listMobile = adminMapper.gainMsgInfoAdminByRegionsAndTypeNOCONCAT(map);
			if (listMobile.size() > 0) {
				for (String mobile : listMobile) {
					mobiles += mobile + ",";
				}
			}
		}else{
			mobiles =	adminMapper.gainMsgInfoAdminByRegionsAndType(map);
		}
		if (mobiles != null && !"".equals(mobiles)) {
			if ("1".equals(type)) {
				MsgUtil.MsgInfoAdminZC(mobiles, userName);
			}else if("2".equals(type)) {
				Members members = membersMapper.selectByPrimaryKey(order.getMemberId());
				MsgUtil.MsgInfoAdminXD(mobiles, order,members);
			}
		}
	}

	public Admin getAdminById(String id) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(id);
	}
}
