package com.sanji.mall.point.service;

import java.math.BigDecimal;
import java.util.List;

import com.sanji.mall.model.PointGoods;


public interface PointService {
	public int getPoint(String userId);
	public List<PointGoods> getPointGoods(String type,int page,int size);
	public PointGoods getPointGoods(String id);
	public void updatePoint(String id, BigDecimal point);
}
