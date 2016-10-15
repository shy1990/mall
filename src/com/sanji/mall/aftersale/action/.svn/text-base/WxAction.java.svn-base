package com.sanji.mall.aftersale.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.aftersale.model.WxForm;
import com.sanji.mall.aftersale.service.WxFormService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.service.DlyCorpService;
import com.sanji.mall.order.service.OrderItemsService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pojo.SessionInfo;

@Namespace("/wx")
@Action(value = "wxAction", results = { @Result(name = "edit", location = "/admin/wx/edit.jsp"), @Result(name = "success", type = "redirect", location = "success.html") })
public class WxAction extends BaseAction implements ModelDriven<WxForm> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WxFormService wxFormService;
	@Autowired
	private DlyCorpService dlyCorpService;
	@Autowired
	private OrderItemsService orderItemsService;

	public WxForm model;

	public WxForm getModel() {
		if (model == null) {
			model = new WxForm();
		}
		return model;
	}

	public String edit() {
		try {
			request.setAttribute("dlyCorps", dlyCorpService.gainAll());

			String oitemId = getModel().getOrderItems().getId();
			OrderItems orderItems = null;
			if (null != oitemId && !"".equals(oitemId)) {
				orderItems = orderItemsService.selectByPrimaryKey(oitemId);
			}

			request.setAttribute("orderItems", orderItems);

			return "edit";
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println("系统异常：" + e.getMessage());
			return "err";
		}
	}

	public String save() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		getModel().setUserId(sessionInfo.getUserId());
		try {
			wxFormService.addWxForm(getModel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
