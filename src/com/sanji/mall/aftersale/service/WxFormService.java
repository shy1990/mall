package com.sanji.mall.aftersale.service;

import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.WxForm;

/**
 * 维修service类
 * 
 * @ClassName: WxFormService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 武继明
 * @date 2014-12-3 上午10:15:54
 * 
 */
public interface WxFormService {
	/**
	 * 添加维修记录
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 武继明
	 * @throws
	 */
	public int addWxForm(WxForm thForm) throws Exception;

	/**
	 * 添加维修详情记录
	 * 
	 * @Title: addItem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 武继明
	 * @throws
	 */
	public int addItem(FormItem item) throws Exception;

}
