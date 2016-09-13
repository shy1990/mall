package com.sanji.mall.msg.dao;

import com.sanji.mall.model.MessageRecord;


public interface MessageRecordMapper {
    
	/**
	 * 新增一条短信发送记录
	* @Title: insertSelective
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param record
	* @param @return    设定文件
	* @return int    返回类型
	* @author ZhouZhangbao
	 */
    public int insertSelective(MessageRecord record);

    
}