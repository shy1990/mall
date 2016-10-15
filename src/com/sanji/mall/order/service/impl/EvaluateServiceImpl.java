package com.sanji.mall.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sanji.mall.model.Evaluate;
import com.sanji.mall.order.dao.EvaluateMapper;
import com.sanji.mall.order.service.EvaluateService;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

	@Resource
	private EvaluateMapper evaluateMapper;

	public int add(Evaluate evaluate) {
		// TODO Auto-generated method stub
		return evaluateMapper.insert(evaluate);
	}

	public List<Evaluate> gainEvaluate(Map param) {
		// TODO Auto-generated method stub
		return evaluateMapper.gainEvaluate(param);
	}

	public String gainCountNum(Map param) {
		// TODO Auto-generated method stub
		return evaluateMapper.gainCountNum(param);
	}
}
