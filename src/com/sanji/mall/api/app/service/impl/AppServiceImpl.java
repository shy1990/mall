package com.sanji.mall.api.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.api.app.dao.AppMapper;
import com.sanji.mall.api.app.service.AppService;
import com.sanji.mall.model.App;

/**
 * 根据登录名、密码获取地推人员信息
 * 
 * 
 * @ClassName:LoginPosServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author yangningning
 * @date 2014-12-8上午11:17:31
 */
@Service("appService")
@Transactional(rollbackFor = Exception.class)
public class AppServiceImpl implements AppService {
	@Resource
	private AppMapper appMapper;

	/**
	 * 根据memberId获取app信息
	 */
	public boolean gainAppByMemberId(String memberId) {
		List<App> list = appMapper.gainAppByMemberId(memberId);
		if (null != list && list.size() > 0) {// 如果存在该app 则返回false
			for (int i = 0; i < list.size(); i++) {
				// System.out.println(list.get(i).getId()+">>>>"+list.get(i).getAddress()+">>>>"+list.get(i).getMemberId());
			}
			return false;
		} else {
			return true;
		}
	}

	public App gainAppIDByMemberId(String memberId) {
		return appMapper.gainAppIDByMemberId(memberId);
	}

	/**
	 * 添加app信息（图片除外）
	 */
	public void addApp(App app) {
		appMapper.addApp(app);
	}

	/**
	 * 修改原有app信息（图片除外）
	 */
	public void updateOriginalApp(App app) {
		appMapper.updateOriginalApp(app);
	}

	/**
	 * 添加app信息（添加图片）
	 */
	public void addAppImg(App app) {
		appMapper.addAppImg(app);
	}

	/**
	 * 修改app信息（修改图片）
	 */
	public void updateAppImg(App app) {
		appMapper.updateAppImg(app);
	}

	/**
	 * 根据店铺名称获取店铺照片url
	 * 
	 * @author SongBaozhen
	 */
	public App gainAppImgByMemeberId(String memberId) {
		if (memberId != null && !"".equals(memberId)) {
			return appMapper.gainAppImgByMemeberId(memberId);
		}

		return null;
	}

	/**
	 * 根据memberId查询会员店铺信息
	 */
	public App gainOnlyAppByMemeberId(String memberId) {
		return appMapper.gainOnlyAppByMemeberId(memberId);
	}

	public AppMapper getAppMapper() {
		return appMapper;
	}

	public void setAppMapper(AppMapper appMapper) {
		this.appMapper = appMapper;
	}

}
