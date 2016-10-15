package com.sanji.mall.news.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;


import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.model.News;
import com.sanji.mall.model.Views;
import com.sanji.mall.news.service.NewsService;
import com.sanji.mall.pojo.SessionInfo;



/**
 * @ClassName: NewsAction
 * @Description: 新闻类
 * @author ZhouZhangbao
 * @date 2013-5-7 下午1:15:50
 * 
 */
@Namespace("/news")
@Action(value = "newsAction", results = {
		@Result(name = "toDetailNews", location = "/admin/news/newView.jsp")
		
})
public class NewsAction extends BaseAction {
	private static final long serialVersionUID = 1231118973089103507L;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private NewsService newsService;
	private News news;
	private Map<String, Object> map = new HashMap<String, Object>();
	private String newsCatName;
	private List<?> list;
	

	
	/**
	 * @Title: toNews
	 * @Description: 跳转首页新闻列表
	 * @param @return    设定文件
	 * @return String    返回类型
	 */
	public void toNews() {
		Map<String, Object> newsCatMap = new HashMap<String, Object>();
		Map<String, Object> param;
		newsCatMap.put("0", "业界动态");
		newsCatMap.put("1", "技术科普");
		newsCatMap.put("2", "销售技巧");
		newsCatMap.put("3", "常见问题");
		newsCatMap.put("4", "最新行情");
		newsCatMap.put("5", "评测导购");
		for (int i = 0; i < 6; i++) {
			List<News> list = new ArrayList<News>();
			param = new HashMap<String, Object>();
			param.put("kz1", "true");//获取允许前台显示的新闻
			param.put("newsCatName", String.valueOf(newsCatMap.get(i + "")));
			list.addAll(newsService.gainNewsForMainPage(param));
			list = list.subList(0, list.size() > 5 ? 5 : list.size());
			map.put("news_" + i, list);
		}
		
		super.writeJson(map);
	}
	
	/**
	 * 楼层资讯
	* @Title: gainNewsByFloor
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void gainNewsByFloor(){
		Map<String, Object> newsCatMap = new HashMap<String, Object>();
		Map<String, Object> param;
		newsCatMap.put("0", "1F");
		newsCatMap.put("1", "2F");
		newsCatMap.put("2", "3F");
		newsCatMap.put("3", "4F");
		newsCatMap.put("4", "5F");
		newsCatMap.put("5", "6F");
		for (int i = 0; i < 6; i++) {
			List<News> list = new ArrayList<News>();
			param = new HashMap<String, Object>();
			param.put("kz1", "true");//获取允许前台显示的新闻
			param.put("newsCatName", String.valueOf(newsCatMap.get(i + "")));
			list.addAll(newsService.gainNewsForMainPage(param));
			list = list.subList(0, list.size() > 5 ? 5 : list.size());
			map.put("newsFloor_" + i, list);
		}
		
		super.writeJson(map);
	}

	/**
	 * @Title: toDetailNews
	 * @Description: 点击新闻详情
	 * @param @return    设定文件
	 * @return String    返回类型
	 */
	public String toDetailNews() {
		try {
			news = newsService.gainNewsById(request.getParameter("id"));
			Integer temp=(int)(Math.random()*10)+1;
			news.setViewnum(news.getViewnum()+temp);
			newsService.updateNewsViewNumById(news);
			List<News> nList = newsService.gainNewsList(news);
			request.setAttribute("newsList", nList);
			SessionInfo sessionInfo = (SessionInfo) session.get(ResourceUtil
					.getSessionInfoName());
			if (sessionInfo != null && sessionInfo.getUserId() != null && !"".equals(sessionInfo.getUserId())) {
				//插入浏览记录
				Views views = new Views();
				views.setId(ToolsUtil.getUUID());
				views.setUserId(sessionInfo.getUserId());
				views.setViewId(news.getId());
				views.setCreatetime(new Date());
				views.setType("news");
				newsService.insetMemberViews(views);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "toDetailNews";
	}

	/**
	 * @return the news
	 */
	public News getNews() {
		return news;
	}



	/**
	 * @param news the news to set
	 */
	public void setNews(News news) {
		this.news = news;
	}



	/**
	 * @return the newsCatName
	 */
	public String getNewsCatName() {
		return newsCatName;
	}



	/**
	 * @param newsCatName the newsCatName to set
	 */
	public void setNewsCatName(String newsCatName) {
		this.newsCatName = newsCatName;
	}



	/**
	 * @return the list
	 */
	public List<?> getList() {
		return list;
	}



	/**
	 * @param list the list to set
	 */
	public void setList(List<?> list) {
		this.list = list;
	}



	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}



	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	
	
}
