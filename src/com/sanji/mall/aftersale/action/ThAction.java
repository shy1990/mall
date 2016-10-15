package com.sanji.mall.aftersale.action;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.FormStatus;
import com.sanji.mall.aftersale.model.ThForm;
import com.sanji.mall.aftersale.service.QhFormService;
import com.sanji.mall.aftersale.service.ThFormService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.EcErpUtil;
import com.sanji.mall.common.util.ExpressUtil;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.Order;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.service.DlyCorpService;
import com.sanji.mall.order.service.OrderItemsService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.point.service.PointService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

@Namespace("/th")
@Action(value = "thAction", results = { @Result(name = "edit", location = "/admin/afterSale/refund_sales.jsp"),
		@Result(name = "success", type = "redirect", location = "${order.orderNum}/process.html"),
		@Result(name = "PROCESS", type = "redirect", location = "${order.orderNum}/process.html"), @Result(name = "thwl", location = "/admin/afterSale/thwl.jsp"),
		@Result(name = "ABORT", type = "redirect", location = "${order.orderNum}/success.html"),
		@Result(name = "COMPLETE", type = "redirect", location = "${order.orderNum}/success.html"), @Result(name = "thSlect", location = "/admin/afterSale/refund_select.jsp"),
		@Result(name = "successMsg", location = "/admin/afterSale/infoMsg.jsp"), @Result(name = "auditreJect", location = "/admin/afterSale/refund_sales_edit.jsp"),
		@Result(name = "rejectClient", location = "/admin/afterSale/refund_sales_kd.jsp") })
public class ThAction extends BaseAction implements ModelDriven<ThForm> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ThAction.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ThFormService thFormService;

	@Autowired
	private OrderItemsService orderItemsService;

	@Autowired
	private PointService pointService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private DlyCorpService dlyCorpService;

	@Autowired
	private QhFormService qhFormService;

	public ThForm model;

	public ThForm getModel() {
		if (model == null) {
			model = new ThForm();
		}
		return model;
	}

	public String edit() {

		String orderId = request.getParameter("orderId");
		if (null == orderId || "".equals(orderId)) {
			orderId = getModel().getOrder().getId();
		}
		Order order = orderService.getById(orderId);
		request.setAttribute("thPrice", order.getTotalCost());

		String oitemId = getModel().getOrder().getItemsId();
		OrderItems orderItems;
		if (null != oitemId && !"".equals(oitemId)) {
			orderItems = orderItemsService.selectByPrimaryKey(oitemId);
			request.setAttribute("showSelect", 3);// 可显示的操作选项个数
			order.setItemsId(oitemId);
			request.setAttribute("thPrice", orderItems.getAmount());
		}

		getModel().setOrder(order);
		return "edit";
	}

	/**
	 * 添加退款退货记录，整单退
	 * 
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String save() {
		// System.out.println("action,原因：" + getModel().getRemark() + " 详情id:" +
		// getModel().getOrder().getItemsId());
		String orderId = getModel().getOrder().getId();
		String itemsId = getModel().getOrder().getItemsId();
		Order order = orderService.getById(orderId);
		getModel().setOrder(order);
		try {
			// 如果订单详情Id不为空那么就是单个退
			if (null != itemsId && !"".equals(itemsId)) {
				thFormService.addThForm(getModel(), itemsId);
				// 单个退。。提交申请--》寄还货物--》审核待处理--》退款退货成功、、这个页面

			} else {// 否则就是整单退
				thFormService.addThForm(getModel());

				// 整单退跳转到。提交申请--》审核待处理--》退款退货成功、、这个页面

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
		// @Result(name = "success", type = "redirect", location =
		// "${order.orderNum}/success.html")
	}

	/**
	 * 修改退款退货记录
	 * 
	 * @Title: editTh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String editTh() {
		Json j = new Json();
		String result = "err";
		try {

			// 根据退货记录id获取退货记录
			ThForm thForm = thFormService.selectByPrimaryKey(getModel().getId());
			thForm.setMoney(getModel().getMoney());
			thForm.setRemark(getModel().getRemark());
			thFormService.updateByPrimaryKeySelective(thForm);
			thForm = thFormService.selectByPrimaryKey(thForm.getId());

			FormStatus formStatus = thForm.getStatus();
			String status = formStatus.name();
			// System.out.println("要把订单改成的状态：" + status);

			// 修改对应的退货详情记录状态
			if (null != thForm.getItems() && thForm.getItems().size() > 0) {
				for (FormItem fi : thForm.getItems()) {
					OrderItems orderItems = orderItemsService.selectByPrimaryKey(fi.getOrderItems().getId());
					// 修改对应的订单详情退款状态
					orderItems.setStatus(status);
					// orderItems.setThStatus(status);
					orderItemsService.updateByPrimaryKeySelective(orderItems);
				}
			}

			j.setMsg("修改后的申请信息提交成功，请你耐心等待售后处理！");
			result = "successMsg";

		} catch (Exception e) {
			logger.info("系统异常：" + e.getMessage());
			j.setMsg("系统异常");
		}
		request.setAttribute("msg", j);
		return result;
	}

	/**
	 * 修改快递记录
	 * 
	 * @Title: editQh
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String editQh() {
		Json j = new Json();
		String result = "err";
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());

			// 根据传入的退货记录id查询退货记录
			ThForm thForm = thFormService.selectByPrimaryKey(getModel().getId());
			thForm.getQhForm().setUserName(sessionInfo.getUserName());
			thForm.getQhForm().setReadUserId(sessionInfo.getUserId());
			thForm.getQhForm().setExpressNumber(getModel().getQhForm().getExpressNumber());

			// System.out.println("修改退货记录状态：" + thForm.getStatus());
			thFormService.updateByPrimaryKeySelective(thForm);

			qhFormService.edit(thForm.getQhForm());

			// Order order =
			// orderService.getById(getModel().getOrder().getId());

			FormStatus formStatus = thForm.getStatus();
			String status = formStatus.name();
			// System.out.println("要把订单改成的状态：" + status);

			// 修改对应的退货详情记录状态
			if (null != thForm.getItems() && thForm.getItems().size() > 0) {
				for (FormItem fi : thForm.getItems()) {
					OrderItems orderItems = orderItemsService.selectByPrimaryKey(fi.getOrderItems().getId());
					// 修改对应的订单详情退款状态
					orderItems.setStatus(status);
					// orderItems.setThStatus(status);
					orderItemsService.updateByPrimaryKeySelective(orderItems);
				}
			}

			// // System.out.println("开始修改订单状态：" + status);
			// order.setStatus(status);
			// orderService.updateByPrimaryKeySelective(order);

			j.setMsg("物流信息提交成功，请你耐心等待售后处理！");
			result = "successMsg";

		} catch (Exception e) {
			logger.info("系统异常：" + e.getMessage());
			j.setMsg("系统异常");
		}
		request.setAttribute("msg", j);
		return result;
	}

	/**
	 * 查看退款退货详情
	 * 
	 * @Title: detail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String detail() {
		String result = null;
		try {

			// 先判断订单状态，如果是，已退款未发货，跳转到退款退货完成。
			String oid = getModel().getOrder().getId();
			ThForm thForm = thFormService.gainThFormByOid(oid);

			FormStatus status = thForm.getStatus();
			// 系统正在处理中
			if (status == FormStatus.INIT || status == FormStatus.PROCESS || status == FormStatus.NOTIFIYED || status == FormStatus.PICKUP) {
				result = "PROCESS";
			}
			// 退货物流，详情页
			if (status == FormStatus.DOWNSENDED || status == FormStatus.UPRECEIVED || status == FormStatus.UPSENDED || status == FormStatus.DOWNRECEIVED) {
				result = "thwl";
				String[] expressNumber = thForm.getQhForm().getExpressNumber().split("-");
				// String[] expressNumber = "yuantong-100033892580".split("-");
				// System.out.println(expressNumber[0] + "    " +
				// expressNumber[1]);
				// 物流信息
				String express = ExpressUtil.getExpressInfo(expressNumber[0].trim(), expressNumber[1].trim());
				request.setAttribute("express", express);
				request.setAttribute("orderNum", getModel().getOrder().getOrderNum());
			}

			// 已终止
			if (status == FormStatus.ABORT) {
				result = "ABORT";
			}

			// 已完成
			if (status == FormStatus.COMPLETE) {
				result = "COMPLETE";
			}

		} catch (Exception e) {
			// System.out.println("异常：" + e.getMessage());
			result = "err";
		}
		// System.out.println("退款退货详情页面：" + result);
		return result != null ? result : "err";
	}

	/**
	 * 根据订单状态跳转到不同的页面or查询页面需要显示的内容
	 * 
	 * @Title: skip
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String skip() {
		Json j = new Json();
		String result = "";
		try {
			String oid = model.getOrder().getId();

			// 根据订单详情Id获取订单详情记录
			// OrderItems orderItems =
			// orderItemsService.selectByPrimaryKey(getoItemsId());
			// System.out.println("订单详情Id:" + model.getOrder().getItemsId());

			// 如果订单未签收，只有订单Id。那么就整单退//如果未发货。也是整单退
			Order order = orderService.getById(oid);
			Members mebers = memberService.getMemberById(order.getMemberId());
			if (null != order) {
				if ("0".equals(order.getShipStatus())) {// 订单状态未发货
					// 管易查询订单状态
					boolean shipStatus = EcErpUtil.gyShipStatus(order.getOrderNum()).getSuccess();
					if (!shipStatus) {// 管易未发货
						// System.out.println(order.getOrderItemss().get(0).getPointGoods());
						// 未发货分为已付款和未付款//如果是已付款就提交到退款退货记录数据库
						if (order.getPayStatus().equals("1")) {// 未发货//已付款
							// 给管易推送消息取消订单
							EcErpUtil.cancelOderById(order.getOrderNum());
							// 改变订单状态取消订单
							order.setStatus("3");
							orderService.updateByPrimaryKeySelective(order);

							// 记录到退货退款记录，整单退//等待后台人工操作
							ThForm thForm = new ThForm();
							thForm.setId(ToolsUtil.getUUID());
							thForm.setRemark("申请退款");
							thForm.setOrder(order);
							thFormService.addThForm(thForm);
							BigDecimal point1 = order.getOrderPoints();
							BigDecimal point2 = mebers.getPoint();
							mebers.setPoint(point1.add(point2));
							memberService.updateByPrimaryKey(mebers);
							j.setMsg("订单:" + order.getOrderNum() + " 申请成功，等待售后处理!");
							result = "successMsg";// 取消订单成功

						} else if (order.getPayStatus().equals("0")) {// 未付款//未发货//无论什么支付方式都直接取消订单
							if (order.getPayMent().equals("1")) {
								EcErpUtil.cancelOderById(order.getOrderNum());
								// 改变订单状态取消订单
								order.setStatus("3");
								orderService.updateByPrimaryKeySelective(order);
								BigDecimal point1 = order.getOrderPoints();
								BigDecimal point2 = mebers.getPoint();
								mebers.setPoint(point1.add(point2));
								memberService.updateByPrimaryKey(mebers);
								j.setMsg("订单:" + order.getOrderNum() + " 取消成功!");
								// String mobile = mebers.getMobile();
								// MsgUtil.MsgConcelOrderToUser(order.getOrderNum(),"18560132053");
								result = "successMsg";// 取消订单成功
							}

							// if (order.getPayMent().equals("0")) {// 网上支付
							// 给管易推送消息取消订单
							EcErpUtil.cancelOderById(order.getOrderNum());
							// 改变订单状态取消订单
							order.setStatus("3");
							orderService.updateByPrimaryKeySelective(order);
							// BigDecimal point1 = order.getOrderPoints();
							// BigDecimal point2 = mebers.getPoint();
							// mebers.setPoint(point1.add(point2));
							// memberService.updateByPrimaryKey(mebers);
							String memberName = mebers.getUsername();
							// 获取客户电话
							String mobile = mebers.getMobile();
							String content1 = "您的订单:" + order.getOrderNum() + " 取消成功!";
							MsgUtil.MsgConcelOrderToUser(order.getOrderNum(), mobile, content1);
							// 获取业务员电话
							Admin admin = adminService.getAdminById(mebers.getAdminId());
							if(admin != null && !"".equals(admin)){
								String mobile2 = admin.getMobilephone();
								String content = "客户:" + memberName + "的订单:" + order.getOrderNum() + " 取消成功!";

								MsgUtil.MsgConcelOrderToUser(order.getOrderNum(), mobile2, content);
								MsgUtil.MsgConcelOrderToApp(mobile2, order,memberName,mobile);//向app推送取消订单
							}
							j.setMsg("订单:" + order.getOrderNum() + " 取消成功!");

							result = "successMsg";// 取消订单成功
							/*
							 * } else if (order.getPayMent().equals("1")) {//
							 * 货到付款 // 给管易推送消息取消订单
							 * EcErpUtil.cancelOderById(order.getOrderNum()); //
							 * 改变订单状态取消订单 order.setStatus("3");
							 * orderService.updateByPrimaryKeySelective(order);
							 * 
							 * // 记录到退货退款记录，整单退//等待后台人工操作 ThForm thForm = new
							 * ThForm(); thForm.setId(ToolsUtil.getUUID());
							 * thForm.setRemark("货到付款申请退款退货！");
							 * thForm.setOrder(order);
							 * thFormService.addThForm(thForm);
							 * 
							 * j.setMsg("订单:" + order.getOrderNum() +
							 * " 申请成功，等待售后处理!"); result = "successMsg";// 取消订单成功
							 * }
							 */
						}

						// 如果未付款那么就判断支付方式是否是货到付款、、如果是货到付款那么就提交到后台、、否则就直接改变订单状态

						// 未付款直接取消订单

					} else {// 已发货 //此时肯定未签收//整单退

						// ////////判断是否选择的支付方式是货到付款、、貌似和是不是货到付款没关系

						// 修改订单状态为已发货
						order.setShipStatus("1");
						orderService.updateByPrimaryKeySelective(order);

						// 到把订单Id传入到前台页面
						// request.setAttribute("orderId", order.getId());

						// 跳转到填写原因和金额页面
						result = "edit";// 跳转到增加退款退货记录页面

						// 、、 整单退记录到数据库

						// 、、 等待后台人工操作//后台处理的时候要先确认开始追货然后查询是否已签收，确定未签收开始退款

					}

				} else if ("1".equals(order.getShipStatus())) {// 已发货，未签收

					// ////////判断是否选择的支付方式是货到付款、、貌似和是不是货到付款没关系

					// 跳转到填写原因和金额页面
					result = "edit";// 跳转到增加退款退货记录页面

					// 整单退记录到数据库//等待后台人工操作

				} else if ("3".equals(order.getShipStatus()) && null == getModel().getOrder().getItemsId()) {// 已签收//并且传入的详情id为空//跳提醒页面//只能进行单个商品售后
					j.setMsg("请你前往订单管理页面选择你要申请售后的商品");
					result = "thformMsg";// 提醒页面//错误信息也可用词页面
				}
				request.setAttribute("thPrice", order.getTotalCost());

			}
			String oitemId = getModel().getOrder().getItemsId();
			if (null != oitemId && !"".equals(oitemId)) {// 当订单详情id不为空的时候才执行下面代码
				// 根据订单Id和订单详情Id获得未完成的退款退货记录列表
				ThForm thForm = thFormService.selectUnfinished(oid, oitemId);

				if (null != thForm) {

					// 如果未完成的订单记录不为空。那么就判断状态调转到对应的操作页面
					/*
					 * if (thForm.getStatus() == FormStatus.AUDITREJECT) {//
					 * 修改订单信息 // 现有的申请信息传到到前台页面
					 * 
					 * result = "addThform";// 修改申请信息 }
					 * 
					 * if (thForm.getStatus() == FormStatus.REJECTCLIENT) {//
					 * 填写物流信息 // 现有的申请信息传到到前台页面 result = "ThformWl";//
					 * 填写物流信息//物流页面需要脱货申请id和脱货申请取货id记录id }
					 */

					model = thForm;
					result = thStatus(thForm, j);// 查询对应的状态
					// request.setAttribute("thForm", thForm);
					j.setObj(thForm);
					request.setAttribute("thPrice", thForm.getMoney());

				} else {// 如果现在没有正在处理中的退款退货记录

					String shipTime = DateUtil.getStringByDate(order.getShipTime());
					// 计算现在距离签收时间
					long date1 = DateUtil.compareDate2(DateUtil.getStringByDate(new Date()), shipTime, 4);

					// 把查询出来的订单记录返回前台。。。计算可以显示的操作记录
					// 由于时间都是根据发货时间来计算所有时间均+1

					OrderItems orderItems = orderItemsService.selectByPrimaryKey(getModel().getOrder().getItemsId());
					request.setAttribute("thPrice", orderItems.getAmount());

					long date2 = 31 * 24 * 60 * 60;
					if (date1 < date2) {// 如果签收时间小于30天那么就不显示退款退货、、只显示换货，维修
						date2 = 8 * 24 * 60 * 60;
						if (date1 < date2) {// 如果签收时间小于7天那么就显示退款退货
							request.setAttribute("showSelect", 3);// 可显示的操作选项个数
						} else {
							request.setAttribute("showSelect", 2);// 可显示的操作选项个数
						}
					} else {// 签收时间已经超过30天
						request.setAttribute("showSelect", 1);// 只显示维护选项
					}

					result = "thSlect";// 售后选择页面
				}

				// 根据订单id和订单详情id获得未完成的订单退款退货记录
				// List<ThForm> thForms =
				// thFormService.selectByOidAdOitemId(oid, getoItemsId());

				// return "";
			}
			// 到把订单Id传入到前台页面
			// request.setAttribute("orderId", order.getId());
			// 订单详情Id
			// request.setAttribute("orderItemsId", getoItemsId());
			order.setItemsId(getModel().getOrder().getItemsId());
			logger.info("开始通知管理人员有订单取消.");
			// 通知管理人员有人取消了订单
			MsgUtil.MsgConcelOrder(order.getOrderNum());
			logger.info("结束通知管理人员有订单取消.");  
			request.setAttribute("order", order);

		} catch (Exception e) {
			logger.info("异常：" + e);
			j.setMsg("系统异常");
			return "err";
		}
		// System.out.println("要跳转的页面：" + result);
		request.setAttribute("msg", j);
		return result;
	}

	// 根据订单详情id查询订单详情信息

	// 根据退款退货记录状态//判断要跳转的页面
	public String thStatus(ThForm thForm, Json j) {
		String result = "";
		FormStatus status = thForm.getStatus();
		if (status == FormStatus.PROCESS || status == FormStatus.EDITPROCESS || status == FormStatus.CLIENTSEND) {
			result = "showThMsg";// 提示页面：正在处理中
			j.setMsg("申请正在处理中");
			if (status == FormStatus.CLIENTSEND) {
				request.setAttribute("kuaiDi", "快递物流信息");
			}
		}
		if (status == FormStatus.AUDITREJECT) {
			result = "auditreJect";// 申请修改页面
			j.setMsg("请修改申请信息");
		}
		if (status == FormStatus.REJECTCLIENT) {
			result = "rejectClient";// 提示页面：跳转到发货信息那个页面
			j.setMsg("填写发货信息");
			request.setAttribute("dlyCorps", dlyCorpService.gainAll());
		}
		if (status == FormStatus.ABORT || status == FormStatus.COMPLETE) {
			result = "showThMsg";// 提示页面：正在处理中
			j.setMsg("申请已处理完成");
			request.setAttribute("kuaiDi", "快递物流信息");
		}
		j.setObj(thForm);

		return result;
	}

}
