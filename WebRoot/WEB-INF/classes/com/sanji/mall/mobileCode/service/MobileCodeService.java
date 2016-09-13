package com.sanji.mall.mobileCode.service;

import java.util.List;

import com.sanji.mall.model.MobileCode;
/**
 * @ClassName: MobileCodeService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author songbaozhen
 * @date 2014-7-15 下午3:48:23
 */
public interface MobileCodeService {
	/**
	 * 增加手机验证码
	* @Title: addMobileCode
	* @Description: TODO(根据手机号查询信息)
	* @param @return    设定文件
	* @return mc   返回类型
	* @author songbaozhen
	* @throws
	 */
	public void addMobileCode(MobileCode mc);
	/**
	 * 根据手机号和救济验证码验证此手机验证码是否存在
	* @Title: exsitMobileCodeByMobileAndCod
	* @Description: TODO(根据手机号查询信息)
	* @param @return    设定文件
	* @return List<MobileCode>   返回类型
	* @author songbaozhen
	* @throws
	 */
	public List<MobileCode> exsitMobileCodeByMobileAndCod(String toMobile,
			String verificationCode);
	
	public Long existVerificationCode(String code);

}
