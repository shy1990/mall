package com.sanji.mall.members.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.appliedreg.service.AppliedRegService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.model.AppliedReg;
import com.sanji.mall.pojo.Json;

/**
 * 用户申请注册类
 * 
 * @ClassName: appliedRegisterAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author SongBaoZhen
 * @date 2014-9-19 下午3:31:41
 */
@Namespace("/member")
@Action(value = "appliedRegAction", results = {
		@Result(name = "succeed", type = "redirect", location = "/member/home.html"),
		@Result(name = "appliedRegUI", location = "/appliedRegister.jsp"),
		@Result(name = "result", location = "/applyResult.jsp") })
public class appliedRegAction extends BaseAction implements ModelDriven<AppliedReg> {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(appliedRegAction.class);

	@Resource
	private AppliedRegService appliedRegService;
	
	private AppliedReg appliedReg = new AppliedReg();
	
	private String mobilePhone;
	
	private boolean flag;
	
	public void doNotNeedSession_checkAppliedRegMobile(){
		Json j = new Json();
		try {
			if (null == mobilePhone || "".equals(mobilePhone.trim())) {
				j.setMsg("手机号不可为空");
			} else {
				flag = appliedRegService.exsistReg(mobilePhone.trim());
				if (flag) {
					j.setSuccess(true);
				} else {
					j.setMsg("此手机已存在！");
				}
			}
		} catch (Exception e) {
			logger.info("系统异常：===========" + e.getMessage());
			j.setMsg("系统异常!");
		}
		writeJson(j);
	}
	
	public void doNotNeedSession_addAppliedReg(){
		 Json json = null ;
		try {
			
			json = appliedRegService.addAppliedReg(appliedReg);
		} catch (Exception e) {
			logger.error("doNotNeedSession_addAppliedReg() occur error. "+e.getMessage());
			json = new Json();
			json.setMsg("申请异常");
			json.setSuccess(false);
		}
		
		writeJson(json);
	}
	
	

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public AppliedReg getModel() {
		return appliedReg;
	}

}
