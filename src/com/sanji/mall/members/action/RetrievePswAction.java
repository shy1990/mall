package com.sanji.mall.members.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.members.service.MyAccountService;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.model.MobileCode;
import com.sanji.mall.msg.service.MessageRecordService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;

@Namespace("/member")
@Action(value = "retrievePswAction", results = { 
		@Result(name = "toGetPhoneCode", location = "/admin/forgetPassword/toRetrievePsw.jsp"),
		@Result(name = "toRetrievePsw", location = "/admin/forgetPassword/retrievePsw.jsp"),
		@Result(name = "success", type = "redirect", location = "/admin/forgetPassword/retrievePswSuccess.jsp"), @Result(name = "index", location = "/index.jsp") })
public class RetrievePswAction extends BaseAction implements ModelDriven<Members> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RetrievePswAction.class);
	@Resource
	private MyAccountService myAccountService;
	@Resource
	private MessageRecordService messageRecordService;
	@Resource
	private MobileCodeService mobileCodeService;

	private Members member = new Members();
	private SessionInfo sInfo = null;

	private MessageRecord MR = new MessageRecord();
	private MobileCode mobileCode = new MobileCode();
	private String verificationCode;
	private String toMobile;
	private String oldpassword;
	private String newpassword;
	private String repassword;
	private String bind;
	private boolean flag;


	/**
	 * 
	 * @Title: toRetrievePsw
	 * @Description: TODO(跳转到获取手机密码界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String doNotNeedSession_toGetPhoneCode() {
		return "toGetPhoneCode";
	}

	/**
	 * 
	 * @Title: modifyPswUI
	 * @Description: TODO(跳转到修改密码界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String doNotNeedSession_toRetrievePsw() {
		return "toRetrievePsw";
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
	public void doNotNeedSession_getPhoneCode() {
		Json json = new Json();
		 member =  myAccountService.gainMemberByMobile(member.getMobile());//根据手机查询会员的信息
		if (member != null && !"".equals(member.getMobile())) {
			// 发短信
			MR = messageRecordService.MgsCodeSender(member.getMobile());
			mobileCode = getMCode();
			mobileCodeService.addMobileCode(mobileCode);
			json.setSuccess(true);
		} else {
			json.setSuccess(false);
			json.setMsg("手机号未绑定！");
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
	private MobileCode getMCode() {

		mobileCode.setId(ToolsUtil.getUUID());
		mobileCode.setMagRecordId(MR.getMsgid());
		mobileCode.setToMobile(member.getMobile());
		mobileCode.setVerificationCode(MR.getCode());
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

	public void doNotNeedSession_existPhoneCode() {
		Json json = new Json();
		List<MobileCode> mclist = mobileCodeService.exsitMobileCodeByMobileAndCod(toMobile, verificationCode);
		try {
			if (mclist != null && mclist.size() > 0) {
				for (MobileCode m : mclist) {
					if (DateUtil.isCheckExpires(m.getCreatetime(), 10 * 60L)) {
						 ActionContext.getContext().getSession().put("mobile",
						 m.getToMobile());
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
	public String doNotNeedSession_modifyPsw() {
		  
		
			try {
//				if (newpassword != null && !"".equals(newpassword)) {
					if(member.getMobile() != null && !"".equals(member.getMobile())){
						member.setPassword(MD5.encrypt(newpassword));
						myAccountService.modifyPasswordByMobile(member);
						return "success";
					}
//				} 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "toGetPhoneCode";
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
