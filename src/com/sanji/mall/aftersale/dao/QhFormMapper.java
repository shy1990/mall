package com.sanji.mall.aftersale.dao;

import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.QhForm;

public interface QhFormMapper {
	int deleteByPrimaryKey(String id);

	int insertSelective(QhForm record);
	
	QhForm selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(QhForm record);

	int insertItemSelective(FormItem item);
}