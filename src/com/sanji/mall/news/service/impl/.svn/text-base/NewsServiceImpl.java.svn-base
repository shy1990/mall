package com.sanji.mall.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanji.mall.members.dao.ViewsMapper;
import com.sanji.mall.model.News;
import com.sanji.mall.model.Views;
import com.sanji.mall.news.dao.NewsMapper;
import com.sanji.mall.news.service.NewsService;

/**
 * @ClassName: NewsServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2013-5-7 下午1:07:35
 * 
 */
@Service("newsService")
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private ViewsMapper viewsMapper;

	/*
	 * (非 Javadoc) <p>Title: gainNewsForMainPage</p> <p>Description: </p>
	 * 
	 * @param map
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.news.service.NewsService#gainNewsForMainPage(java.util
	 * .Map)
	 */

	public List<News> gainNewsForMainPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return newsMapper.gainNewsForMainPage(map);
	}

	/*
	 * (非 Javadoc) <p>Title: gainNewsById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.sanji.mall.news.service.NewsService#gainNewsById(java.lang.String)
	 */

	public News gainNewsById(String id) {
		return newsMapper.gainNewsById(id);
	}

	/*
	 * (非 Javadoc) <p>Title: updateNewsViewNumById</p> <p>Description: </p>
	 * 
	 * @param news
	 * 
	 * @see
	 * com.sanji.mall.news.service.NewsService#updateNewsViewNumById(com.sanji
	 * .mall.model.News)
	 */

	public void updateNewsViewNumById(News news) {
		newsMapper.updateNewsViewById(news);

	}

	/*
	 * (非 Javadoc) <p>Title: insetMemberViews</p> <p>Description: </p>
	 * 
	 * @param views
	 * 
	 * @see
	 * com.sanji.mall.news.service.NewsService#insetMemberViews(com.sanji.mall
	 * .model.Views)
	 */

	public void insetMemberViews(Views views) {
		Integer i = viewsMapper.selectViewsLang(views);
		if (i > 0) {
			viewsMapper.updateViews(views);
		} else {
			viewsMapper.insert(views);
		}

	}

	/*
	 * (非 Javadoc) <p>Title: gainNewsList</p> <p>Description: </p>
	 * 
	 * @param news
	 * 
	 * @see
	 * com.sanji.mall.news.service.NewsService#gainNewsList(com.sanji.mall.model
	 * .News)
	 */

	public List<News> gainNewsList(News news) {
		List<News> nList = newsMapper.gainNewsList(news);
		// System.out.println("==========================="+nList.size());
		if (nList != null && !"".equals(nList)) {
			return nList;
		} else {
			return null;
		}

	}

}
