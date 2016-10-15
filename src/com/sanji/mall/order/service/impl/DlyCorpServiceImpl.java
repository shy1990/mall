package com.sanji.mall.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sanji.mall.model.DlyCorp;
import com.sanji.mall.order.dao.DlyCorpMapper;
import com.sanji.mall.order.service.DlyCorpService;

@Service("dlyCorpService")
public class DlyCorpServiceImpl implements DlyCorpService {

	@Resource
	private DlyCorpMapper dlyCorpMapper;

	public DlyCorp selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dlyCorpMapper.selectByPrimaryKey(id);
	}

	public List<DlyCorp> gainAll() {
		// TODO Auto-generated method stub
		return dlyCorpMapper.gainAll();
	}

}
