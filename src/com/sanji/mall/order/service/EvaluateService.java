package com.sanji.mall.order.service;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.Evaluate;

/**
 * 商品评论接口类
 * 
 * @ClassName: EvaluateService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-5 上午10:37:42
 * 
 */

public interface EvaluateService {

	/**
	 * 添加评论
	 * 
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param evaluate
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author 田超强
	 * @throws
	 */
	public int add(Evaluate evaluate);

	/**
	 * 查询评论列表
	 * 
	 * @Title: gainEvaluate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return List<Evaluate> 返回类型
	 * @author 田超强
	 * @throws
	 */
	List<Evaluate> gainEvaluate(Map param);

	/**
	 * 查询、统计数据总条数
	 * 
	 * @Title: gainCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	String gainCountNum(Map param);

}
