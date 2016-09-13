package com.sanji.mall.whiteList.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.model.WhiteList;
import com.sanji.mall.whiteList.dao.WhiteListMapper;
import com.sanji.mall.whiteList.service.WhiteListService;
@Service("whiteListService")
@Transactional(rollbackFor=Exception.class)
public class WhiteListServiceImpl implements WhiteListService {
	@Resource
	private WhiteListMapper whiteListMapper;

	public boolean gainWhiteListByIp(String ip) {
		// TODO Auto-generated method stub
		if(ip != null && !"".equals(ip)){
			WhiteList wh =  whiteListMapper.gainWhiteListByIp(ip);
			if(wh != null){
				return true;
			}
			return false;
		}
		
		return false;
	}

}
