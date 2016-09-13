package com.sanji.mall.accessory.special.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.accessory.special.service.SpecialAccessoryService;
import com.sanji.mall.collect.service.CollectService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.model.Collect;
import com.sanji.mall.order.service.OrderItemsService;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 配件信息相关action
 * 
 * @ClassName: AccessoryAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-10-29 上午10:54:59
 * 
 */

@Namespace("/accessory")
@Action(value = "accessoryAction", results = { @Result(name = "detail", location = "/admin/accessory/detail.jsp"), @Result(name = "err", location = "/error/error.jsp") })
public class AccessoryAction extends BaseAction implements ModelDriven<Accessory> {
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AccessoryAction.class);

	private Accessory accessory;

	public Accessory getModel() {
		// TODO Auto-generated method stub
		if (accessory == null)
			accessory = new Accessory();
		return accessory;
	}

	@Resource
	private SpecialAccessoryService specialAccessoryService;

	@Resource
	private CollectService collectService;

	@Resource
	private OrderItemsService orderItemsService;

	/**
	 * 根据配件编号获取配件详情
	 * 
	 * @Title: gainDetailByNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String gainDetailByNum() {
		try {
			// // System.out.println("配件编号：" + accessory.getAccessoriesNum());
			// List<Accessory> accessories =
			// specialAccessoryService.gainDetailByNum(accessory.getAccessoriesNum());
			List<Accessory> accessories = specialAccessoryService.gainDetailBySpecCode(accessory.getSpecCode());
			if (accessories != null && accessories.size() > 0) {

				// System.out.println("配件详情：" + JSON.toJSONString(accessories));

				request.setAttribute("accessorie", accessories.get(0));
				List<Map> acssMap = specialAccessoryService.gainByNum(accessories.get(0).getAccessoriesNum(), accessories.get(0).getId());
				request.setAttribute("accessories", acssMap);

				SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
				// 检测此单品是否已收藏
				Map param = new HashMap();
				param.put("memberId", sessionInfo.getUserId());
				param.put("targetId", accessories.get(0).getId());
				param.put("type", "accessory");

				List<Collect> collects = collectService.gainByTidMid(param);
				if (collects != null && collects.size() > 0) {// 已经收藏过，删除收藏
					request.setAttribute("collect", true);
				} else {
					request.setAttribute("collect", false);
				}

				// 获取详情以后增加点击数
				specialAccessoryService.upClickRate(accessories.get(0).getId());

				param.put("targetType", "acs");
				param.put("targetId", accessories.get(0).getId());
				request.setAttribute("goodsDealCountNum", orderItemsService.gainDealNum(param));// 所有成交记录数量
				param.put("minTime", DateUtil.getDateStrToDate(DateUtil.currentMonFirstDay(), "yyyy-MM-dd"));
				param.put("maxTime", DateUtil.getDateStrToDate(DateUtil.currentMonLastDay(), "yyyy-MM-dd"));
				request.setAttribute("goodsDealNum", orderItemsService.gainDealNum(param));// 月成交记录

			}
			// logger.info("查询出来的配件详情：" + JSON.toJSONString(accessories));

			return "detail";
		} catch (Exception e) {
			logger.info("获取配件详情异常：" + e.getMessage());
		}
		return "err";
	}
}
