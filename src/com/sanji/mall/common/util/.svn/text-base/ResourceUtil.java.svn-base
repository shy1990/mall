package com.sanji.mall.common.util;

import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;

/**
 * 项目参数工具类
 * 
 * @ClassName: ResourceUtil
 * @Description: TODO(获取系统级别的参数)
 * @author ZhouZhangbao
 * @date 2014-7-14 下午3:25:22
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	public static final String getRegisterMobileCode() {
		return bundle.getString("registerMobileCode");
	}

	/**
	 * 获取tomcat目录�?webapp的路�?
	 */
	public static final String getWebAppPath() {
		String path = ServletActionContext.getServletContext().getRealPath("") + "/";
		return path;
	}

	public static final String getGoods_Img_Directory() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * FTP所需
	 * 
	 * @Title: getWaterPath
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 */
	public static String getWaterPath() {
		// TODO Auto-generated method stub
		return bundle.getString("waterPath");
	}

	public static String getWebPath() {
		return bundle.getString("webpath");
	}

	public static String getFtpIp() {
		return bundle.getString("ftpIp");
	}

	public static String getFtpUserName() {
		return bundle.getString("ftpUserName");
	}

	public static String getFtpPassword() {
		return bundle.getString("ftpPassword");
	}

	// public static String get

	/**
	 * 通过KEY获取值
	 * 
	 * @Title: get
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param key
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 * @throws
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

	/**
	 * 编辑器所需要
	 * 
	 * @Title: getKindeditorFieldName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static final String getKindeditorFieldName() {
		return bundle.getString("kindeditorFieldName");
	}

	public static final String getKindeditorUploadFileExts() {
		return bundle.getString("kindeditorUploadFileExts");
	}

	public static final String getDomain() {
		return bundle.getString("domain");
	}

	/**
	 * @Title: getRandNum
	 * @Description: TODO(获取有效时间)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wangmei
	 */
	public static final String getValidTime(String type) {
		if ("1".equals(type)) {
			return bundle.getString("validTimeForMobile");
		} else {
			return bundle.getString("validTimeForEmail");
		}
	}

	/**
	 * 获取联行支付的MerId
	 * 
	 * @Title: getLhMerId
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author ZhouZhangbao
	 */
	public static final String getLhMerId() {
		return bundle.getString("LhMerId");
	}

	/**
	 * 获取联行支付的授权码key
	 * 
	 * @Title: getLhKey
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author ZhouZhangbao
	 */
	public static final String getLhKey() {
		return bundle.getString("LhKey");
	}

	/**
	 * 支付完成后支付结果返回到该url，主要用于结果展�?
	 * 
	 * @Title: getLhDealReturn
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author ZhouZhangbao
	 */
	public static final String getLhDealReturn() {
		return bundle.getString("LhDealReturn");
	}

	/**
	 * 支付完成后支付结果�?知到该url，主要用于�?知接�?
	 * 
	 * @Title: getLhDealNotify
	 * @Description: TODO(这里用一句话描述这个方法的作�?
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @author ZhouZhangbao
	 */
	public static final String getLhDealNotify() {
		return bundle.getString("LhDealNotify");
	}

	/**
	 * 获取同类商品对比价格区间
	 * 
	 * @Title: getComparisonInterval
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author ZhouZhangbao
	 */
	public static final Integer getComparisonInterval() {
		String comparisonInterval = bundle.getString("comparisonInterval");
		return Integer.valueOf(comparisonInterval);
	}

	/**
	 * 获取默认邮费价格
	 * 
	 * @Title: getPostage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Double 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static final Double getPostagePrice() {
		String postagePrice = bundle.getString("postagePrice");
		return Double.valueOf(postagePrice);
	}

	/**
	 * 手机root服务的价格
	 * 
	 * @Title: getMobilePhoneRootPrice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Double 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static final Double getMobilePhoneRootPrice() {
		String mobilePhoneRootPrice = bundle.getString("mobilePhoneRootPrice");
		return Double.valueOf(mobilePhoneRootPrice);
	}

	/**
	 * 获取综合排序点击量比重
	 * 
	 * @Title: getSynthesizeSortClickProportion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static final String getSynthesizeSortClickProportion() {
		return bundle.getString("synthesizeSortClickProportion");
	}

	/**
	 * 获取综合排序购买量比重
	 * 
	 * @Title: getSynthesizeSortBuyProportion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static final String getSynthesizeSortBuyProportion() {
		return bundle.getString("synthesizeSortBuyProportion");
	}

	/**
	 * 获取综合排序收藏量比重
	 * 
	 * @Title: getSynthesizeSortCollectProportion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public static final String getSynthesizeSortCollectProportion() {
		return bundle.getString("synthesizeSortCollectProportion");
	}

	/**
	 * 获取e宝支付的商户号
	* @Title: getYEEPayMerId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static final String getYEEPayMerId(){
		return bundle.getString("yeePay_p1_MerId");
	}
	
	/**
	 * 获取E宝支付的支付成功通知URL
	* @Title: getYEEPayUrl
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static final String getYEEPayUrl(){
		return bundle.getString("yeePay_p8_Url");
	}
	
	/**
	 * 获取e宝支付的KEY
	* @Title: getYEEPayKey
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static final String getYEEPayKey(){
		return bundle.getString("yeePay_keyValue");
	}
	
	public static final String getSandPayKey(){
		return bundle.getString("sandPayKey");
	}
	public static final String getMemberDianPuImage(){
		return bundle.getString("memberDianPuImage");
	}
	public static final String getMemberDPImagesExt(){
		return bundle.getString("memberDPImagesExt");
	}
	
	/**
	 * yeePay 退款地址
	* @Title: getYEEPayRefundURL
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static final String getYEEPayRefundURL(){
		return bundle.getString("yeePay_refundURL");
	}
	/**
	 * 管理员手机号
	* @Title: getAdminMobile
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public static final String getAdminMobile(){
		return bundle.getString("adminMobile");
	}
	
	public static final String getYeePayPosKEY(){
		return bundle.getString("yeePayPosKey");
	}
}
