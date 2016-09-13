package com.sanji.mall.goods.thread;

import java.util.ArrayList;
import java.util.List;

import com.sanji.mall.model.UserGoodsRecord;

/**
 * 用户浏览记录缓存数据
 * 
 * @ClassName: UserGoodsRecordCache
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2015-8-31 上午10:39:30
 * 
 */
public class UserGoodsRecordCache {
	private static List<UserGoodsRecord> userGoodsRecords = new ArrayList<UserGoodsRecord>();
	public static List<UserGoodsRecord> userGoodsRecords2 = new ArrayList<UserGoodsRecord>();

	/**
	 * 添加一个用户访问记录信息，并返回当前值
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param ugr
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public synchronized static int add(UserGoodsRecord ugr) {
		userGoodsRecords.add(ugr);
		int size = userGoodsRecords.size();
		if (size > 1000) {
			userGoodsRecords2.addAll(userGoodsRecords);
			userGoodsRecords.removeAll(userGoodsRecords);
		}
		return size;
	}

	public static void clear() {
		userGoodsRecords2.removeAll(userGoodsRecords2);
	}

	public static List<UserGoodsRecord> getUserGoodsRecords() {
		userGoodsRecords2.addAll(userGoodsRecords);
		userGoodsRecords.removeAll(userGoodsRecords);
		return userGoodsRecords2;
	}
}
