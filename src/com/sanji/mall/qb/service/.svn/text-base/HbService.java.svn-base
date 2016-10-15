package com.sanji.mall.qb.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sanji.mall.pojo.Json;

public interface HbService {

	/**
	 * 获取用户个人红包数据
	 * @param userId 用户id
	 * @return
	 */
	public List<Map<String, Object>> getHb(String userId);
	
	public Json checkUserIfHasHb(String userId);
	
	/**
	 * 使用用户红包
	 * @param hbId
	 * @param userId
	 * @return
	 */
	public Json useHb(String hbId,String userId);
	
}
