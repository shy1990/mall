package com.sanji.mall.brand.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.brand.dao.BrandMapper;
import com.sanji.mall.brand.service.BrandService;
import com.sanji.mall.model.Brand;

/**
 * 品牌
 * 
 * @ClassName: BrandServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-13 下午7:59:36
 * 
 */
@Service("brandService")
@Transactional(rollbackFor = Exception.class)
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandMapper brandMapper;

	public List<Brand> gainAll() {
		// TODO Auto-generated method stub
		return brandMapper.gainAll();
	}

	public List<String> gainIds(List<String> names) {
		// TODO Auto-generated method stub
		return brandMapper.gainIds(names);
	}

	public List<Brand> getHotSaleBrand() {
		return brandMapper.selectHotSaleBrand();  
	}

}
