package com.sanji.mall.order.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Evaluate;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.order.service.EvaluateService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pojo.SessionInfo;

/**
 * 商品评论 action类
 * 
 * @ClassName: EvaluateAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 田超强
 * @date 2014-11-5 上午10:49:49
 * 
 */
@Namespace("/evaluate")
@Action(value = "evaluateAction", results = { @Result(name = "evaluate", location = "/admin/myCenter/evaluate/ok.jsp"),
		@Result(name = "nort", location = "/admin/myCenter/evaluate/noReturn.jsp"), @Result(name = "evaluateList", location = "/admin/myCenter/evaluate/myEvaluates.jsp") })
public class EvaluateAction extends BaseAction implements ModelDriven<Evaluate> {

	private static final Logger logger = Logger.getLogger(EvaluateAction.class);

	private Evaluate evaluate;

	public Evaluate getModel() {
		// TODO Auto-generated method stub
		if (evaluate == null)
			evaluate = new Evaluate();
		return evaluate;
	}

	@Resource
	private EvaluateService evaluateService;

	@Resource
	private OrderService orderService;

	@Resource
	private MemberService memberService;

	// TODO 积分系数未设置
	private float pointCoefficient = 10;

	/**
	 * 添加评论
	 * 
	 * @Title: addEvaluate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String addEvaluate() {

		// 评论之前先检测是否已经评论过了
		Order order1 = orderService.gainOrderALLByID(evaluate.getOrderId());
		String saleType = order1.getSaleType();
		if (saleType.equals("1")) {
			return "nort";
		} else {
			try {
				SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
				evaluate.setId(ToolsUtil.getUUID());
				evaluate.setCreatetime(new Date());
				evaluate.setMemberId(sessionInfo.getUserId());

				evaluateService.add(evaluate);

				// System.out.println("改变订单评论状态");
				// 改变订单状态
				Order order = new Order();
				order.setSaleType("1");
				// order.setShipStatus("2");
				order.setId(evaluate.getOrderId());
				order.setMemberId(sessionInfo.getUserId());
				order.setCreatetime(null);
				orderService.updateByPrimaryKeySelective(order);
				// System.out.println(evaluate.getOrderId());
				order = orderService.getById(evaluate.getOrderId());

				/*
				 * // 更新用户积分 BigDecimal thisPoint =
				 * editPoint(order.getTotalCost(), sessionInfo.getUserId());
				 * request.setAttribute("point", thisPoint.intValue());
				 */
				return "evaluate";
			} catch (Exception e) {
				logger.info("异常：" + e.getMessage());
			}
			return "err";
		}
	}

	/**
	 * 更新用户积分信息
	 * 
	 * @Title: editPoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param money
	 *            退款金额数量
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public BigDecimal editPoint(BigDecimal money, String userId) {
		Members member = memberService.getMemberById(userId);
		BigDecimal point = BigDecimal.ZERO;// 初始化积分变量为0

		point = point.add(money.divide(BigDecimal.valueOf(pointCoefficient)));// 钱数chu积分换算系数=积分数量

		point = BigDecimal.valueOf(Math.floor(point.doubleValue()));// 对计算出的积分取整
		BigDecimal thisPoint = point;
		// 消费积分
		point = member.getPoint().add(point);// 用户已有积分加上本次的积分

		// 更新积分
		// pointGoodsMaper.updatePoint(member.getId(), point);
		member.setPoint(point);
		memberService.updateByPrimaryKey(member);

		return thisPoint;
	}

	/**
	 * 获得所有评论列表
	 * 
	 * @Title: gainEvaluate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String gainEvaluate() {
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			Map evalUateMap = new HashMap();
			evalUateMap.put("evaluates", evaluateService.gainEvaluate(getParam(sessionInfo.getUserId())));
			String countNum = evaluateService.gainCountNum(getParam(sessionInfo.getUserId()));
			int pageCount = (Integer.parseInt(countNum) - 1) / evaluate.getRows() + 1;
			evalUateMap.put("pageCount", pageCount);// 总页数
			evalUateMap.put("page", evaluate.getPage());

			request.setAttribute("evalUateMap", evalUateMap);
			return "evaluateList";
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
	}

	/**
	 * 通过ajax请求评论信息
	 * 
	 * @Title: gainEvaluateAjax
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void gainEvaluateAjax() {
		try {
			Map<String, Object> evalUateMap = new HashMap<String, Object>();
			evalUateMap.put("evaluates", evaluateService.gainEvaluate(getParam(null)));
			Map param = getParam(null);
			// param.put("userId", "");
			String countNum = evaluateService.gainCountNum(param);
			int pageCount = (Integer.parseInt(countNum) - 1) / evaluate.getRows() + 1;
			evalUateMap.put("pageCount", pageCount);// 总页数
			evalUateMap.put("page", evaluate.getPage());
			evalUateMap.put("countNum", countNum);
			writeJson(evalUateMap);
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
	}

	/**
	 * 获得某指定商品、配件 评论总记录数
	 * 
	 * @Title: gainEvaluateCountNum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void gainEvaluateCountNum() {
		try {
			writeJson(evaluateService.gainCountNum(getParam(null)));
		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
		}
	}

	/**
	 * 组合查询条件
	 * 
	 * @Title: param
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return Map 返回类型
	 * @author 田超强
	 * @throws
	 */
	private Map getParam(String userId) {
		// SessionInfo sessionInfo = (SessionInfo)
		// this.session.get(ResourceUtil.getSessionInfoName());
		Map param = new HashMap();
		param.put("userId", userId);

		param.put("targetType", evaluate.getTargetType());
		param.put("targetId", evaluate.getTargetId());

		param.put("sort", evaluate.getSort());// 不传入排序的字段默认时间排序
		param.put("order", evaluate.getOrder());
		param.put("page", evaluate.getPage());// 不传入页码默认取第一页数据
		param.put("rows", evaluate.getRows());

		return param;
	}

}
