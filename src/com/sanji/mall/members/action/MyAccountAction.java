package com.sanji.mall.members.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.collect.service.CollectService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.members.service.MyAccountService;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.model.MobileCode;
import com.sanji.mall.model.Order;
import com.sanji.mall.msg.service.MessageRecordService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.regions.service.RegionsService;

@Namespace("/member")
@Action(value = "myAccountAction", results = {
		@Result(name = "toMyAccount", location = "/admin/myCenter/accountSecurity/userInfo.jsp"),
		// @Result(name = "toShowMyAccount", location =
		// "/admin/myCenter/accountSecurity/showUserInfo.jsp"),
		@Result(name = "myAccountIndex", location = "/admin/myCenter/myAccountIndex.jsp"),
		@Result(name = "toAccountSecurity", location = "/admin/myCenter/accountSecurity/accountSecurity.jsp"),
		@Result(name = "getMobileCodeUI", location = "/admin/myCenter/accountSecurity/getMobileCode.jsp"),
		@Result(name = "bindMobileUI", location = "/admin/myCenter/accountSecurity/bindMobile.jsp"),
		@Result(name = "bindSuccess", location = "/admin/myCenter/accountSecurity/bindSuccess.jsp"),
		@Result(name = "modifyPswUI", location = "/admin/myCenter/accountSecurity/fixPassword.jsp"),
		@Result(name = "toExistMobileCodeUI", location = "/admin/myCenter/accountSecurity/toFixMobile.jsp"),
		@Result(name = "tofixMobileUI", location = "/admin/myCenter/accountSecurity/fixMobile.jsp"),
		@Result(name = "fixMoblieSeccess", location = "/admin/myCenter/accountSecurity/fixMobileSuccess.jsp"),
		@Result(name = "toEditUserInfo", location = "/admin/myCenter/accountSecurity/editUserInfo.jsp"),
		@Result(name = "toEditBankInfo", location = "/admin/myCenter/accountSecurity/editBankInfo.jsp"),
		@Result(name = "success", type = "redirect", location = "/admin/myCenter/accountSecurity/fixPasswordSuccess.jsp"), @Result(name = "index", location = "/index.jsp"), })
public class MyAccountAction extends BaseAction implements ModelDriven<Members> {
	/**
	 *
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MyAccountAction.class);
	@Resource
	private MyAccountService myAccountService;
	@Resource
	private MessageRecordService messageRecordService;
	@Resource
	private MobileCodeService mobileCodeService;
	@Resource
	private RegionsService regionsService;
	@Resource
	private OrderService orderService;
	@Resource
	private CollectService collectService;
	@Resource
	private MemberService memberService;

	private Members member = new Members();
	private SessionInfo sInfo = null;

	private MessageRecord MR = new MessageRecord();
	private MobileCode mobileCode = new MobileCode();
	private String verificationCode;
	private String toMobile;
	private String oldpassword;
	private String newpassword;
	private String bind;
	private boolean flag;

	public String toUpdateBankInfoUI() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		member = memberService.getMemberById(sessionInfo.getUserId());
		request.setAttribute("member", member);
		return "toEditBankInfo";
	}

	/**
	 * 
	 * @return备用用户修改方法
	 */
	@SuppressWarnings("unchecked")
	public String toUpdateUserInfoUI() {
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		// member = memberService.getMemberById(sessionInfo.getUserId());
		member = myAccountService.gainMobileById(sessionInfo.getUserId());
		request.setAttribute("member", member);
		return "toEditUserInfo";
	}

	public String updateUserInfo() {

		return null;
	}

	// 判断roleId是否已是用户的角色，如果是返回selected字符串----------备用默认选中方法
	public String hasSelected(String rid, Members selectedRegions) {
		if (selectedRegions.getRid().equals(rid)) {
			return "selected";
		}

		return "";
	}

	/*
	 * private List<E> (){ SessionInfo sessionInfo = (SessionInfo)
	 * this.session.get(ResourceUtil.getSessionInfoName()); List selectedRegions
	 * = memberService.gainRegionsOfUser(sessionInfo.getUserId()); return null;
	 * }
	 */
	/**
	 * 跳转到获取手机验证码并验证
	 * 
	 * @Title: toExistMobileCodeUI
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public String toExistMobileCodeUI() {
		getTel();
		return "toExistMobileCodeUI";
	}

	/**
	 * 跳转到修改手机号页面
	 * 
	 * @Title: tofixMobileUI
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhen
	 * @throws
	 */

	public String tofixMobileUI() {

		return "tofixMobileUI";
	}
    
	/**
	 * 跳转到修改手机号页面
	 * 
	 * @Title: tofixMobileUI
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhen
	 * @throws
	 */
	public String fixMobile() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member.setId(sInfo.getUserId());
		String oldPhone=sInfo.getMoblie();
		String newPhone=member.getMobile();
		myAccountService.modifyMember(member);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("oldPhone", oldPhone);
		jsonObj.put("newPhone", newPhone);
		MsgUtil.sendMessageToApps(jsonObj);
		return "fixMoblieSeccess";
	}

	/**
	 * 个人中心首页需要的数据
	 * 
	 * @Title: myAccountIndex
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author 田超强
	 * @throws
	 */
	public String myAccountIndex() {
		try {
			SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
			Map param = new HashMap();
			param.put("userId", sessionInfo.getUserId());
			param.put("page", 1);// 不传入页码默认取第一页数据
			param.put("rows", 3);
			param.put("order", "desc");

			List<Order> orders = orderService.gainPageOrders(param);
			Map m = new HashMap();
			m.put("orders", orders);
			Order order = new Order();
			param.put("payStatus", "1");
			m.put("paidNum", orderService.gainCountNum(param));// 已付款订单数量
			param.put("payStatus", "0");
			m.put("obligationNum", orderService.gainCountNum(param));// 待付款订单数量
			param.put("payStatus", "1");// 已付款
			param.put("shipStatus", "0");// 待发货
			m.put("staySendNum", orderService.gainCountNum(param));// 待发货订单数量
			m.put("page", order.getPage());
			param.put("sort", "COLLECT_TIME");
			param.put("rows", 5);
			param.put("memberId", sessionInfo.getUserId());
			m.put("collects", collectService.gainByMidSku(param));

			m.put("userInfo", memberService.gainMembersDetailById(sessionInfo.getUserId()));

			request.setAttribute("orderMap", m);
			// System.out.println("个人中心首页需要的数据：" + JSON.toJSONString(m));
			return "myAccountIndex";

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
	}

	/**
	 * 
	 * @Title: toMyAccount
	 * @Description: TODO(跳转到账户信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String toMyAccount() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member = myAccountService.gainMobileById(sInfo.getUserId());
		request.setAttribute("member", member);
		return "toMyAccount";
	}

	/**
	 * 
	 * @Title: toShowMyAccount
	 * @Description: TODO(跳转到账户信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	/*
	 * public String toShowMyAccount() { sInfo = (SessionInfo)
	 * session.get(ResourceUtil.getSessionInfoName()); member =
	 * myAccountService.gainMobileById(sInfo.getUserId());
	 * request.setAttribute("member", member); return "toShowMyAccount"; }
	 */

	public void queryProvince() {

		super.writeJson(regionsService.gainProvince());
	}

	public void queryCity() {
		super.writeJson(regionsService.gainCity(member.getPid()));
	}

	public void queryArea() {
		super.writeJson(regionsService.gainArea(member.getPid()));
	}

	public void queryTown() {
		super.writeJson(regionsService.gainTown(member.getPid()));
	}

	public String updateMember() {
		try {
			sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
			member.setId(sInfo.getUserId());
			myAccountService.updateMember(member);

		} catch (Exception e) {
			logger.info("异常：" + e.getMessage());
			return "err";
		}
		return toMyAccount();
	}

	/**
	 * 
	 * @Title: toAccountSecurity
	 * @Description: TODO(跳转到账户安全界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String toAccountSecurity() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member = myAccountService.gainMobileById(sInfo.getUserId());
		if (member.getMobile() != null && !"".equals(member.getMobile())) {
			String phone = member.getMobile().substring(0, 3) + "****" + member.getMobile().substring(7, member.getMobile().length());
			request.setAttribute("phone", phone);
		} else {
			request.setAttribute("phone", null);
		}

		return "toAccountSecurity";
	}

	/**
	 * 
	 * @Title: toBindMobileUI
	 * @Description: TODO(跳转到绑定手机号界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String toBindMobileUI() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member = myAccountService.gainMobileById(sInfo.getUserId());
		request.setAttribute("member", member);
		request.setAttribute("bind", bind);
		return "bindMobileUI";
	}

	/**
	 * 
	 * @Title: bindMobile
	 * @Description: TODO(绑定手机号)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */

	public String bindMobile() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member.setId(sInfo.getUserId());
		myAccountService.addMemberMobile(member);
		if ("true".equals(bind)) {
			return toMobileCodeUI();
		} else {
			return "bindSuccess";
		}
	}

	public void gainMemberMobile() {
		Json json = new Json();
		flag = myAccountService.gainMemberMobile(toMobile);
		try {
			if (flag) {

				json.setSuccess(false);
				json.setMsg("此手机已绑定！");
			} else {
				json.setSuccess(true);
			}

		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("请输入手机号！");
		}
		super.writeJson(json);
	}

	/**
	 * 
	 * @Title: toMobileCodeUI
	 * @Description: TODO(跳转到获取手机密码界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String toMobileCodeUI() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member = myAccountService.gainMobileById(sInfo.getUserId());
		String phone = member.getMobile() != null ? member.getMobile() : "";
		String tel = member.getMobile() != null ? member.getMobile().substring(0, 3) + "****" + member.getMobile().substring(7, member.getMobile().length())
				: "<a style=\"color:red\" href=\"member/toBindMobileUI/true.html\">点击绑定手机</a>";
		ActionContext.getContext().getSession().put("phone", phone);
		ActionContext.getContext().getSession().put("tel", tel);
		return "getMobileCodeUI";
	}

	private void getTel() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		member = myAccountService.gainMobileById(sInfo.getUserId());
		String phone = member.getMobile() != null ? member.getMobile() : "";
		String tel = member.getMobile() != null ? member.getMobile().substring(0, 3) + "****" + member.getMobile().substring(7, member.getMobile().length())
				: "<a style=\"color:red\" href=\"member/toBindMobileUI/true.html\">点击绑定手机</a>";
		ActionContext.getContext().getSession().put("phone", phone);
		ActionContext.getContext().getSession().put("tel", tel);
	}

	/**
	 * 
	 * @Title: modifyPswUI
	 * @Description: TODO(跳转到修改密码界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String modifyPswUI() {
		ActionContext.getContext().getSession().put("mobile", toMobile);
		return "modifyPswUI";
	}

	/**
	 * 
	 * @Title: 根据密码和手机号验证用户
	 * @Description: TODO(根据密码和手机号验证用户)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public void existMemberByPswAndMobile() {
		Json json = new Json();
		flag = myAccountService.gainMemberByPswAndMobile(MD5.encrypt(member.getPassword()), member.getMobile());
		try {
			if (flag) {
				json.setSuccess(true);
			} else {
				json.setSuccess(false);
				json.setMsg("密码不正确！");
			}

		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("请输入密码！");
		}
		super.writeJson(json);
	}

	/**
	 * 
	 * @Title: 根据手机号获取验证码
	 * @Description: TODO( 根据手机号获取验证码)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public void doNotNeedSession_getMobileCodeByMobile() {
		Json json = new Json();
		// member =
		// myAccountService.gainMemberByMobile(member.getMobile());//根据手机查询会员的信息
		if (member.getMobile() != null && !"".equals(member.getMobile())) {

			MessageRecord mr = new MessageRecord();
			mr.setMobiles(member.getMobile());
			mr.setCratetime(new Date());
			// mr = MsgUtil.MsgSender(mr);// 发送短信
			// getRequest().getSession().setAttribute("editPwdCode", code);
			// 发短信

			mr = messageRecordService.MgsCodeSender(member.getMobile());
			mobileCode = getMCode(mr);
			mobileCodeService.addMobileCode(mobileCode);

			json.setSuccess(true);
		} else {
			json.setSuccess(false);
			json.setMsg("请先绑定手机号！");
		}
		super.writeJson(json);

	}

	/**
	 * 向MobileCode添加一条信息
	 * 
	 * @Title: getMobile()
	 * @Description: TODO(向MobileCode添加一条信息)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	private MobileCode getMCode(MessageRecord mr) {

		mobileCode.setId(ToolsUtil.getUUID());
		mobileCode.setMagRecordId(mr.getMsgid());
		mobileCode.setToMobile(member.getMobile());
		mobileCode.setVerificationCode(mr.getCode());
		mobileCode.setCreatetime(new Date());
		return mobileCode;
	}

	/**
	 * 验证手机验证码是否正确
	 * 
	 * @Title: existMobileCode
	 * @Description: TODO(验证手机验证码是否正确)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public void doNotNeedSession_existMobileCode() {
		Json json = new Json();
		List<MobileCode> mclist = mobileCodeService.exsitMobileCodeByMobileAndCod(toMobile, verificationCode);
		try {
			if (mclist != null && mclist.size() > 0) {
				for (MobileCode m : mclist) {
					if (DateUtil.isCheckExpires(m.getCreatetime(), 10 * 60L)) {
						// ActionContext.getContext().getSession().put("mobile",
						// m.getToMobile());
						json.setSuccess(true);
					} else {
						// request.setAttribute("mobile", m.getToMobile());
						json.setSuccess(false);
						json.setMsg("手机验证码超时!");
					}
				}

			} else {
				json.setSuccess(false);
				json.setMsg("手机验证码不正确!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("请输入密码！");
		}

		super.writeJson(json);

	}

	/**
	 * 修改密码
	 * 
	 * @Title:modifyPsw()
	 * @Description: TODO(修改密码)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String modifyPsw() {
		sInfo = (SessionInfo) session.get(ResourceUtil.getSessionInfoName());
		String oldpwd = MD5.encrypt(oldpassword);
		if (null != oldpwd && !"".equals(oldpwd)) {
			if (oldpwd.equals(sInfo.getLoginPassword())) {
				member.setPassword(MD5.encrypt(newpassword));
				member.setId(sInfo.getUserId());
				myAccountService.modifyMember(member);
				return "success";
			} else {
				return "modifyPswUI";
			}
		}
		return "modifyPswUI";

	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public MessageRecord getMR() {
		return MR;
	}

	public void setMR(MessageRecord mR) {
		MR = mR;
	}

	public MobileCode getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(MobileCode mobileCode) {
		this.mobileCode = mobileCode;
	}

	public Members getModel() {
		// TODO Auto-generated method stub
		return member;
	}

	public String getBind() {
		return bind;
	}

	public void setBind(String bind) {
		this.bind = bind;
	}

}
