package com.sanji.mall.appliedreg.dao;

import com.sanji.mall.model.AppliedReg;

public interface AppliedRegMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppliedReg record);

    int insertSelective(AppliedReg record);

    AppliedReg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppliedReg record);

    int updateByPrimaryKey(AppliedReg record);

	Long gainAppliedRegByMobile(String mobile);

	String gainAppliedRegAddress(String mobile);
}