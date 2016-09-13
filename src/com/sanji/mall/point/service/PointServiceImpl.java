package com.sanji.mall.point.service;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.PointGoods;
import com.sanji.mall.point.dao.PointGoodsMapper;

@Service("pointService")
@Transactional(rollbackFor = Exception.class)
public class PointServiceImpl implements PointService {
	@Resource
	private MembersMapper membersMaper;
	@Resource
	private PointGoodsMapper pointGoodsMaper;
	public int getPoint(String userId){
		return membersMaper.selectPointByPrimaryKey(userId);
	}

	public List<PointGoods> getPointGoods(String type,int page,int size) {
		int start=(page-1)*size;
		int end=page*size;
		return pointGoodsMaper.selectAllByType("%"+type+"%",start,end);
	}

	public PointGoods getPointGoods(String id) {
		return pointGoodsMaper.selectByPrimaryKey(id);
	}

	public void updatePoint(String id, BigDecimal point) {
		Members m = membersMaper.selectByPrimaryKey(id);
		m.setPoint(point);
		membersMaper.updateByPrimaryKey(m);
	}


}
