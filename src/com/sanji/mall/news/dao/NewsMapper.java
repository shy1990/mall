package com.sanji.mall.news.dao;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.News;

public interface NewsMapper {
	
	/**
	* @Title: gainNewsForMainPage
	* @Description: 点击主页,查询主页新闻列表
	* @param @param map
	* @param @return    设定文件
	* @return List<News>    返回类型
	*/
	List<News> gainNewsForMainPage(Map<String, Object> map);
	
	/**
	* @Title: gainNewsById
	* @Description: 根据新闻ID查询新闻
	* @param @param id
	* @param @return    设定文件
	* @return News    返回类型
	*/
	News gainNewsById(String id);
	
	/**
	 * 更新点击量
	* @Title: updateNewsViewById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param news    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	void updateNewsViewById(News news);
	/**
	 * 更新点击量
	* @Title: gainNewsList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param news    设定文件
	* @return List    返回类型
	* @author songbaozhen
	 */
	public List<News> gainNewsList(News news);

	
}