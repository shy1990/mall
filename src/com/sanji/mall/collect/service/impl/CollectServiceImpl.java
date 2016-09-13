package com.sanji.mall.collect.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.collect.dao.CollectMapper;
import com.sanji.mall.collect.service.CollectService;
import com.sanji.mall.model.Collect;

@Service("collectService")
@Transactional(rollbackFor = Exception.class)
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectMapper collectMapper;

	public List<Collect> gainByTidMid(Map param) {
		// TODO Auto-generated method stub
		return collectMapper.gainByTidMid(param);
	}

	public int dropById(String id) {
		// TODO Auto-generated method stub
		return collectMapper.dropById(id);
	}

	public int add(Collect record) {
		// TODO Auto-generated method stub
		return collectMapper.add(record);
	}

	public List<Map> gainByMidSku(Map param) {
		// TODO Auto-generated method stub
		return collectMapper.gainByMidSku(param);
	}

	public List<Map> gainByMidAcs(Map param) {
		// TODO Auto-generated method stub
		return collectMapper.gainByMidAcs(param);
	}

	public String gainByMidSkuTatolNum(Map param) {
		// TODO Auto-generated method stub
		return collectMapper.gainByMidSkuTatolNum(param);
	}

	public String gainByMidAcsTatolNum(Map param) {
		// TODO Auto-generated method stub
		return collectMapper.gainByMidAcsTatolNum(param);
	}

}
