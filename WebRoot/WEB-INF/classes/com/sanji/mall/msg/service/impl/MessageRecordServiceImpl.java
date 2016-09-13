/**  
 * @Title: MessageRecordServiceImpl.java
 * @Package com.sanji.mall.msg.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-10-8 下午3:48:58
 * @version V1.0  
 */
package com.sanji.mall.msg.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.msg.dao.MessageRecordMapper;
import com.sanji.mall.msg.service.MessageRecordService;

/**
 * @ClassName: MessageRecordServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-8 下午3:48:58
 */
@Service("messageRecordService")
@Transactional(rollbackFor = Exception.class)
public class MessageRecordServiceImpl implements MessageRecordService {
	@Resource
	private MessageRecordMapper messageRecordMapper;

	/**
	 * 发送手机验证码
	 * 
	 * @Title: MgsCodeSender
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public MessageRecord MgsCodeSender(String mobile) {
		MessageRecord msg = new MessageRecord();
		msg.setId(ToolsUtil.getUUID());
		msg.setCratetime(new Date());
		msg.setMobiles(mobile);
		msg = MsgUtil.MsgSenderCode(msg);
		messageRecordMapper.insertSelective(msg);
		return msg;
	}

	/**
	 * 发送签收验证码
	 * 
	 * @Title: MgsCodeSender
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public MessageRecord goodsReceiveCodeSender(String mobile) {
		MessageRecord msg = new MessageRecord();
		msg.setId(ToolsUtil.getUUID());
		msg.setCratetime(new Date());
		msg.setMobiles(mobile);
		msg = MsgUtil.GoodsReceiveSender(msg);
		messageRecordMapper.insertSelective(msg);
		return msg;
	}

}
