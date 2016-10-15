package com.sanji.mall.news.service;

import java.util.List;
import java.util.Map;

import com.sanji.mall.model.News;
import com.sanji.mall.model.Views;



/**
 * @ClassName: NewsService
 * @Description: 
 * @author ZhouZhangbao
 * @date 2013-5-7 下午1:07:25
 *
 */
public interface NewsService{
	
	/**
	 * 查询主页的资讯
	* @Title: gainNewsForMainPage
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param news
	* @param @return    设定文件
	* @return List<News>    返回类型
	*/
	public List<News> gainNewsForMainPage(Map<String, Object> map);
	
	/**
	* @Title: gainNewsById
	* @Description: 
	* @param @param id
	* @param @return    设定文件
	* @return News    返回类型
	*/
	public News gainNewsById(String id);
	
	/**
	 * 更新点击数量
	* @Title: updateNewsViewNumById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param news    设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void updateNewsViewNumById(News news);
	
	/**
	 * 增加浏览记录
	* @Title: insetMemberViews
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param memberCollect    设定文件
	* @return void    返回类型
	* @author 周张豹
	*/
	public void insetMemberViews(Views views);
	/**
	 * 获取热点新闻
	* @Title:gainNewsList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param news   设定文件
	* @return List    返回类型
	* @author songbaozhen
	*/
	public List<News> gainNewsList(News news);
	
}
