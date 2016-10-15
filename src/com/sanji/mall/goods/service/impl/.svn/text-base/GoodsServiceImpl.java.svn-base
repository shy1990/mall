package com.sanji.mall.goods.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.goods.dao.GoodsMapper;
import com.sanji.mall.goods.service.GoodsService;
import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Goods;
import com.sanji.mall.model.Members;

/**
 * 商品信息
 * 
 * @author 田超强
 * 
 */
@Service("goodsService")
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private MembersMapper membersMaper;

	public List<Goods> select() {
		// TODO Auto-generated method stub
		return goodsMapper.select();
	}

	public Goods selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectByPrimaryKey(id);
	}

	public List<Goods> gainAllGoodsInfoByGoodsNum(String goodsNum) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAllGoodsInfoByGoodsNum(goodsNum);
	}

	public List<Goods> gainAllGoodsInfoByMap(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAllGoodsInfoByMap(param);
	}

	public int upClickRate(String goodsNum) {
		// TODO Auto-generated method stub
		return goodsMapper.upClickRate(goodsNum);
	}

	public List<Goods> gainByPage(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainByPage(param);
	}

	public String gainCountNum(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainCountNum(param);
	}

	public List<Goods> gainAccessoriesPage(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAccessoriesPage(param);
	}

	public String gainAccessoriesCountNum(Map param) {
		// TODO Auto-generated method stub
		return goodsMapper.gainAccessoriesCountNum(param);
	}

	public List<Goods> getHotSale(int page, int size, String brandName) {
		int start = (page - 1) * size;
		int end = page * size;
		List<Goods> goodss = goodsMapper.gainHotByBrandName(start, end, brandName);
		return goodss;
	}

	public List<Map<String, Object>> getBjd(String brandName, String userId,String machineType) {
		Members user = membersMaper.gainMembersDetailById(userId);
		String regionId = user.getArea();
		List<Map<String, Object>> selectBjd = goodsMapper.selectBjd(brandName, userId,regionId,machineType, user.getUsername());
		setHot(selectBjd);
		return selectBjd;
	}

	// 设置热销
	private void setHot(List<Map<String, Object>> hw) {
		int hotCount = 1;
		for (Map<String, Object> map : hw) {
			int saleIndex = ((BigDecimal) map.get("SALE_INDEX")).intValue();
			boolean isHot = new Boolean((String) map.get("IS_QUOTATION_HOT"));
			if (saleIndex <= 3 && hotCount <= 5) {
				map.put("IS_QUOTATION_HOT", true);
				map.put("IS_NEW", false);
				hotCount++;
			} else if (isHot && hotCount <= 5) {
				map.put("IS_QUOTATION_HOT", true);
				map.put("IS_NEW", false);
			} else {
				map.put("IS_QUOTATION_HOT", false);
			}
		}
	}

	public List<Map<String, String>> gainNameBylike(String s) {
		return goodsMapper.gainNameBylike(s);
	}

	public List<Goods> gainAllGoodsInfo2(String memberId, String goodsNum, String area, String userName) {
		return goodsMapper.gainAllGoodsInfo2(memberId, goodsNum, area,userName);
	}

    @Override
    public List<Goods> gainAllShoppingRush(String memberId, String goodsNum, String area, String userName) {
        return gainAllShoppingRush(memberId, userName);
    }

	@Override
	public List<Goods> gainAllShoppingRush(String memberId, String userName) {
		return goodsMapper.gainAllShoppingRush(memberId,userName);
	}
}
