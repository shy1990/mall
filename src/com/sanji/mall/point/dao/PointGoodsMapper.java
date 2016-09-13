package com.sanji.mall.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sanji.mall.model.PointGoods;

public interface PointGoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PointGoods record);

    int insertSelective(PointGoods record);

    PointGoods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PointGoods record);

    int updateByPrimaryKey(PointGoods record);
    
    List<PointGoods> selectAllByType(@Param("type") String type, @Param("start") int start, @Param("end") int end);
}