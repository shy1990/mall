package com.sanji.mall.test.dao;

import java.util.List;

import com.sanji.mall.model.Companys;



public interface CompanysMapper {
    int deleteByPrimaryKey(String id);

    int insert(Companys record);

    int insertSelective(Companys record);

    Companys selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Companys record);

    int updateByPrimaryKeyWithBLOBs(Companys record);

    int updateByPrimaryKey(Companys record);
    
    public List<Companys> gainCompanysByUserName(String userName);
}