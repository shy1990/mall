package com.sanji.mall.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.goods.dao.UserGoodsRecordMapper;
import com.sanji.mall.goods.service.UserGoodsRecordService;
import com.sanji.mall.goods.thread.UserGoodsRecordCache;
import com.sanji.mall.model.UserGoodsRecord;

@Service("userGoodsRecordService")
@Transactional(rollbackFor = Exception.class)
@Component
public class UserGoodsRecordServiceImpl implements UserGoodsRecordService {

	@Autowired
	private UserGoodsRecordMapper ugrm;

	public int save(UserGoodsRecord record) {
		return ugrm.insert(record);
	}

	public int inserts(List<UserGoodsRecord> list) {
		return ugrm.inserts(list);
	}

	@Scheduled(cron = "0 55 23 * * ? ")
	public void autoInsert() {
		ugrm.inserts(UserGoodsRecordCache.getUserGoodsRecords());
		UserGoodsRecordCache.clear();
	}

}
