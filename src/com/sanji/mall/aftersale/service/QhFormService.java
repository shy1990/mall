package com.sanji.mall.aftersale.service;

import com.sanji.mall.aftersale.model.QhForm;

/**
 * 地包取货，用户发货。记录
 * 
 * @ClassName: QhFormService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2015-1-9 下午3:12:28
 * 
 */
public interface QhFormService {
	/**
	 * 修改发货记录
	 * 
	 * @Title: edit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void edit(QhForm qhForm);
}
