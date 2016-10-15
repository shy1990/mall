package com.sanji.mall.goods.thread;

import com.alibaba.fastjson.JSON;
import com.sanji.mall.goods.service.UserGoodsRecordService;

public class UserGoodsRecordThread extends Thread {

	private UserGoodsRecordService userGoodsRecordService;

	public UserGoodsRecordThread(UserGoodsRecordService userGoodsRecordService) {
		this.userGoodsRecordService = userGoodsRecordService;
	}

	@Override
	public void run() {
		try {
			 System.out.println("插入数据：" + JSON.toJSONString(UserGoodsRecordCache.userGoodsRecords2));
			
			userGoodsRecordService.inserts(UserGoodsRecordCache.userGoodsRecords2);
			UserGoodsRecordCache.clear();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("线程" + Thread.currentThread().getName() +
			// "异常：" + e.getMessage());
		}

	}
}
