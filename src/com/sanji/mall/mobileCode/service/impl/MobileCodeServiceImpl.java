package com.sanji.mall.mobileCode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.mobileCode.dao.MobileCodeMapper;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.MobileCode;
/**
 * @ClassName: MobileCodeServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:51:03
 */
@Service("mobileCodeService")
@Transactional(rollbackFor=Exception.class)
public class MobileCodeServiceImpl implements MobileCodeService {
	@Resource
	private MobileCodeMapper mobileCodeMapper;
	/* (非 Javadoc)
	* <p>Title: addMobileCode</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.mobileCode.service#addMobileCode(MobileCode mc)
	*/
	public void addMobileCode(MobileCode mc){
		 mobileCodeMapper.insertSelective(mc);
	}

	/* (非 Javadoc)
	* <p>Title: exsitMobileCodeByMobileAndCod</p>
	* <p>Description: </p>
	* @return
	* @see com.sanji.mall.mobileCode.service#exsitMobileCodeByMobileAndCod(String mobile,String verificationCode)
	*/
	public List<MobileCode> exsitMobileCodeByMobileAndCod(String toMobile,
			String verificationCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", toMobile);
		map.put("code", verificationCode);
		return mobileCodeMapper.gainCodeByMap(map);
		
		
	}
    
	/**
	 * 验证签收验证码
	* @Title: existVerificationCode
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author songbaozhen
	 */
	public Long existVerificationCode(String code) {
		 
		return mobileCodeMapper.gainVerificationCode(code);
	}
}
