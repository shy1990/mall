package com.sanji.mall.goodsSku.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.goodsSku.dao.GoodsSkuMapper;
import com.sanji.mall.goodsSku.service.GoodsSkuService;
import com.sanji.mall.model.GoodsSku;

@Service("goodsSkuService")
@Transactional(rollbackFor = Exception.class)
public class GoodsSkuServiceImpl implements GoodsSkuService {

	@Resource
	private GoodsSkuMapper goodsSkuMapper;

	public List<GoodsSku> gainByPrice(Map map) {
		// TODO Auto-generated method stub
		return goodsSkuMapper.gainByPrice(map);
	}

	public List<GoodsSku> getById(String id,String area) {
		return goodsSkuMapper.selectByPrimaryKey(id,area);
	}

}
