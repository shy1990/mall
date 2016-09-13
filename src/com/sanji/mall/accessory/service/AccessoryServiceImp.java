package com.sanji.mall.accessory.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.accessory.dao.AccessoryMapper;
import com.sanji.mall.model.Accessory;

@Service("accessoryService")
@Transactional(rollbackFor = Exception.class)
public class AccessoryServiceImp implements AccessoryService {
	@Resource
	private AccessoryMapper accessoryMapper;

	public Accessory getById(String id) {
		return accessoryMapper.selectByPrimaryKey(id);
	}

	public Accessory getGiftById(String targetId) {
		return accessoryMapper.selectByGiftPrimaryKey(targetId);
	}

	public List<Accessory> getCommon(String type, int page, int size) {
		int start = (page - 1) * size;
		int end = page * size;
		return accessoryMapper.selectComm(type, start, end);
	}

}
