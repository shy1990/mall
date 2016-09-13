package com.sanji.mall.whiteList.dao;

import com.sanji.mall.model.WhiteList;


public interface WhiteListMapper {
    int insert(WhiteList record);

    int insertSelective(WhiteList record);

	WhiteList gainWhiteListByIp(String ip);
}