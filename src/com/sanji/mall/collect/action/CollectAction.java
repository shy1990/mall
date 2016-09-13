package com.sanji.mall.collect.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.collect.service.CollectService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.model.Collect;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 商品收藏类
 * 
 * @ClassName: CollectAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-21 下午9:49:17
 * 
 */
@Namespace("/collect")
@Action(value = "collectAction", results = { @Result(name = "collect", location = "/admin/myCenter/myCollect/collect.jsp") })
public class CollectAction extends BaseAction implements ModelDriven<Collect> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CollectAction.class);

	private Collect collect;

	public Collect getModel() {
		// TODO Auto-generated method stub
		if (collect == null) {
			collect = new Collect();
		}
		return collect;
	}

	@Resource
	private CollectService collectService;

	/**
	 * 
	 * @Title: addCollect添加收藏
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void add_dropCollect() {
		Json j = new Json();
		try {
			// 添加之前判断是否已经添加过了，如果已经添加过了那就执行删除
			List<Collect> collects = gainByTidMid();
			if (collects != null && collects.size() > 0) {// 已经收藏过，删除收藏
				collectService.dropById(collects.get(0).getId());
				j.setMsg("取消收藏");
			} else {// 未收藏过，添加收藏
				collect.setMemberType("B2B");
				collect.setCollectIp(request.getRemoteAddr());
				collect.setCollectTime(new Date());
				collect.setMemberId(gainSessionInfo().getUserId());
				collect.setId(ToolsUtil.getUUID());
				collectService.add(collect);
				j.setMsg("收藏成功");
			}
			j.setSuccess(true);
		} catch (Exception e) {
			logger.info("服务器异常：" + e.getMessage());
			j.setMsg("服务器异常！");
		}
		writeJson(j);
	}

	/**
	 * 根据用户id和商品id获得收藏记录
	 * 
	 * @Title: gainByTidMid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @param type
	 *            类型
	 * @return List<Collect> 返回类型
	 * @author 田超强
	 * @throws
	 */
	public List<Collect> gainByTidMid() {
		Map param = new HashMap();
		param.put("memberId", gainSessionInfo().getUserId());
		param.put("targetId", collect.getTargetId());
		param.put("type", collect.getType());
		return collectService.gainByTidMid(param);
	}

	/**
	 * 用户根据类型获取收藏类表
	 * 
	 * @Title: gainCollectByType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param param
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void gainCollectByType() {
		try {
			// System.out.println("传入的类型是：" + collect.getType());
			Map collectMap = new HashMap();
			if (collect.getType().trim().equals("sku")) {// 单品数据
				// collectMap.put("skus",
				// collectService.gainByMidSku(getParam()));
				// collectMap.put("skuTatolNum",
				// collectService.gainByMidSkuTatolNum(getParam()));
				writeJson(collectService.gainByMidSku(getParam()));
			} else {
				// collectMap.put("acss",
				// collectService.gainByMidAcs(getParam()));
				// collectMap.put("acsTatolNum",
				// collectService.gainByMidAcsTatolNum(getParam()));
				writeJson(collectService.gainByMidAcs(getParam()));
			}

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
	};

	/**
	 * 根据用户id获取用户的单品和配件收藏列表
	 * 
	 * @Title: gainCollects
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String gainCollects() {
		try {
			Map collectMap = new HashMap();
			collectMap.put("skus", collectService.gainByMidSku(getParam()));// 单品收藏列表
			collectMap.put("acss", collectService.gainByMidAcs(getParam()));// 配件收藏列表
			collectMap.put("skuTatolNum", collectService.gainByMidSkuTatolNum(getParam()));
			collectMap.put("acsTatolNum", collectService.gainByMidAcsTatolNum(getParam()));
			// System.out.println("查询用户收藏记录：" + JSON.toJSONString(collectMap));
			request.setAttribute("collects", collectMap);
			return "collect";
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
	}

	/**
	 * 获得查询条件
	 * 
	 * @Title: getParam
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Map 返回类型
	 * @author 田超强
	 * @throws
	 */
	public Map getParam() {
		// 初始化排序方式
		if (collect.getSort() == null) {
			collect.setSort("COLLECT_TIME");
			collect.setOrder("desc");
		}
		Map param = new HashMap();
		param.put("sort", collect.getSort());
		param.put("order", collect.getOrder());
		param.put("page", collect.getPage());
		param.put("rows", 5);
		param.put("memberId", gainSessionInfo().getUserId());
		return param;
	}

	/**
	 * 获取已登录的用户信息
	 * 
	 * @Title: gainSessionInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public SessionInfo gainSessionInfo() {
		return (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
	}

}
