package com.sanji.mall.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.goods.dao.CatMapper;
import com.sanji.mall.goods.service.CatService;
import com.sanji.mall.model.Cat;

@Service("catService")
@Transactional(rollbackFor = Exception.class)
public class CatServiceImpl implements CatService {

	@Resource
	private CatMapper catMapper;

	public List<Cat> gainAll() {
		// TODO Auto-generated method stub
		return catMapper.gainAll();
	}

	public List<String> gainIds(List<String> names) {
		// TODO Auto-generated method stub
		return catMapper.gainIds(names);
	}

}
