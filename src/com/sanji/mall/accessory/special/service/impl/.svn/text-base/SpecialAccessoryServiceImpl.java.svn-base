package com.sanji.mall.accessory.special.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.accessory.dao.AccessoryMapper;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.model.Accessory;

/**
 * 
 * @ClassName: SpecialAccessoryServiceImpl
 * @Description: 专用配件Service实现
 * @author WuJiming wzslw_163_com
 * @date 2014年10月15日 下午5:26:45
 * 
 */
@Service("specialAccessoryService")
@Transactional(rollbackFor = Exception.class)
public class SpecialAccessoryServiceImpl implements SpecialAccessoryService {

	@Resource
	private AccessoryMapper accessoryMapper;

	public List<Accessory> gainDetailByNum(String accessoriesNum) {
		return accessoryMapper.gainDetailByNum(accessoriesNum);
	}

	public int upClickRate(String id) {
		return accessoryMapper.upClickRate(id);
	}

	public List<Accessory> getGift(String goodsId, String type, int page, int size) {
		int start = (page - 1) * size;
		int end = page * size;
		return accessoryMapper.selectGiftByGoodsIdAndType(goodsId, type, start, end);
	}

	public List<Accessory> getPj(String goodsId, String type, int page, int size) {
		int start = (page - 1) * size;
		int end = page * size;
		return accessoryMapper.selectByGoodsIdAndType(goodsId, type, start, end);
	}

	public List<Accessory> gainDetailBySpecCode(String specCode) {
		// TODO Auto-generated method stub
		return accessoryMapper.gainDetailBySpecCode(specCode);
	}

	public List<Map> gainByNum(String num, String id) {
		// TODO Auto-generated method stub
		return accessoryMapper.gainByNum(num, id);
	}
}
