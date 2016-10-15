/**  
* @Title: MessageRecordService.java
* @Package com.sanji.mall.msg.service
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-8 下午3:42:36
* @version V1.0  
*/
package com.sanji.mall.msg.service;

import com.sanji.mall.model.MessageRecord;

/**
 * @ClassName: MessageRecordService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-8 下午3:42:36
 */
public interface MessageRecordService {
	
	/**
	 * 发送验证码 
	* @Title: MgsCodeSender
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param Mobile
	* @param @return    设定文件
	* @return String    返回类型 ：验证码
	* @author ZhouZhangbao
	 */
	public MessageRecord MgsCodeSender(String mobile);
	/**
	 * 货物签收验证码 
	* @Title: goodsReceiveCodeSender
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param Mobile
	* @param @return    设定文件
	* @return String    返回类型 ：验证码
	* @author songbaozhen
	 */
	public MessageRecord goodsReceiveCodeSender(String mobile);

}
