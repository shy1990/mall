package com.sanji.mall.members.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.DateUtil;
import com.sanji.mall.common.util.IpUtil;
import com.sanji.mall.common.util.MD5;
import com.sanji.mall.common.util.MsgUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MyAccountService;
import com.sanji.mall.members.service.RegisterService;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.model.MobileCode;
import com.sanji.mall.msg.service.MessageRecordService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.SessionInfo;
import com.sanji.mall.regions.service.RegionsService;
import com.sanji.mall.role.service.RoleService;

/**
 * 会员注册类
 * 
 * @ClassName: RegisterAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author SongBaoZhen
 * @date 2014-9-19 下午3:31:41
 */
@Namespace("/member")
@Action(value = "registerAction", results = { @Result(name = "succeed", type = "redirect", location = "/member/home.html"), @Result(name = "regUI", location = "/register.jsp"),
		@Result(name = "message", location = "/checkuser.jsp") })
public class RegisterAction extends BaseAction implements ModelDriven<Members> {
	private static final Logger logger = Logger.getLogger(RegisterAction.class);

	private static final long serialVersionUID = 1L;

	private Members members = new Members();
	private MobileCode mobileCode = new MobileCode();
	private MessageRecord MR = new MessageRecord();
	private Admin admin = new Admin();

	private String toMobile;

	private String verificationCode;

	private boolean flag;
	@Resource
	private RegisterService registerService;
	@Resource
	private AdminService adminService;
	@Resource
	private RoleService roleService;
	@Resource
	private MessageRecordService messageRecordService;
	@Resource
	private MobileCodeService mobileCodeService;
	@Resource
	private MyAccountService myAccountService;
	@Resource
	private RegionsService regionsService ;

	/**
	 * 
	 * @Title: doNotNeedSession_regMemberUI
	 * @Description: TODO(跳转到用户注册界面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String doNotNeedSession_regMemberUI() {
		
		return "regUI";
	}
	/**
	 * 
	 * @Title: doNotNeedSession_gainProvince
	 * @Description: TODO(获取省级地区列表)
	 * @param @return 设定文件
	 * @return list 返回类型
	 * @author songbaozhne
	 */
	public void doNotNeedSession_gainProvince(){
		super.writeJson(regionsService.gainProvince());
	}
	/**
	 * 
	 * @Title: doNotNeedSession_gainCityByRid
	 * @Description: TODO(获取市级地区列表)
	 * @param @return 设定文件
	 * @return list 返回类型
	 * @author songbaozhne
	 */
	public void doNotNeedSession_gainCityByRid(){
		super.writeJson(regionsService.gainCity(members.getPid()));
	}
	/**
	 * 
	 * @Title: doNotNeedSession_gainAreaByRid
	 * @Description: TODO(获取县级地区列表)
	 * @param @return 设定文件
	 * @return list 返回类型
	 * @author songbaozhne
	 */
	public void doNotNeedSession_gainAreaByRid(){
	
	super.writeJson(regionsService.gainArea(members.getPid()));
    }
	/**
	 * 
	 * @Title: doNotNeedSession_gainTownByRid
	 * @Description: TODO(获取乡镇街道地区列表)
	 * @param @return 设定文件
	 * @return list 返回类型
	 * @author yinhsong
	 */
	public void doNotNeedSession_gainTownByRid(){
	
	super.writeJson(regionsService.gainTown(members.getPid()));
    }

/**
	 * 用户 注册
	 * 
	 * @Title: doNotNeedSession_regMember()
	 * @Description: TODO(验证admin是否存在，存在判断是否有发送手机密码权限，有发送密码)
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @author songbaozhne
	 */
	public String doNotNeedSession_regMember() {

		if ((members.getUsername() != null && !"".equals(members.getUsername())) && (members.getPassword() != null && !"".equals(members.getPassword()))) {
			List<MobileCode> mbList = mobileCodeService.exsitMobileCodeByMobileAndCod(toMobile, verificationCode);
			if (registerService.existMember(members.getUsername())) {
				request.setAttribute("error", "该会员已存在!!!");
				return "regUI";
			}else if(registerService.existMobile(members.getMobile())){
				request.setAttribute("error", "该手机号已存在!!!");
				return "regUI";
			} else if (mbList != null && mbList.size() > 0) {
				for (MobileCode mobileCode : mbList) {
					if (DateUtil.isCheckExpires(mobileCode.getCreatetime(), 10 * 60L)) {
						SessionInfo sessionInfo = new SessionInfo();
						members.setId(ToolsUtil.getUUID());
						members.setPassword(MD5.encrypt(members.getPassword()));
						members.setPoint(new BigDecimal(5000));
						members.setRegTime(new Date());
						members.setRegIp(IpUtil.getIpAddr(request));
						sessionInfo.setUserId(members.getId());
						sessionInfo.setLoginName(members.getUsername());
						sessionInfo.setLoginPassword(members.getPassword());
						sessionInfo.setIp(IpUtil.getIpAddr(request));
						session.put(ResourceUtil.getSessionInfoName(), sessionInfo);
						try {
							registerService.addMembers(members);
							MsgUtil.MsgRegisterSuccess(members.getMobile());
							adminService.msgInfoAdminByRegionsAndType(members.getProvince(),members.getCity(),members.getArea(),"1",members.getUsername(),null);
						} catch (Exception e) {
							// TODO: handle exception
						}
						return "succeed";
					} else {
						request.setAttribute("error", "手机验证码超时!");
						return "regUI";
					}
				}

			} else {
				request.setAttribute("error", "手机验证码错误!");
				return "regUI";
			}
			return "regUI";
		} else {
			request.setAttribute("error", "用户名或密码不能为空!");
			return "regUI";
		}

	}
	
	/**
	 * 验证手机号是否存在，存在判断是否有发送手机密码权限，有权限发送密码
	 * 
	 * @Title: doNotNeedSession_exsitAdmin()
	 * @Description: TODO(验证admin是否存在，存在判断是否有发送手机密码权限，有发送密码)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhen
	 */
	public void doNotNeedSession_exsitAdmin() {
		Json json = new Json();
		admin = adminService.getAdminByMoble(toMobile);// 根据手机查询”地推_“的Admin的信息

		if (admin != null && !"".equals(admin.getMobilephone())) {
			// 发短信
			MR = messageRecordService.MgsCodeSender(toMobile);
			if(MR != null){
				mobileCodeService.addMobileCode(getMobile(MR));
				json.setSuccess(true);
				json.setMsg(roleService.gainRoleByAid(admin.getId()));
				json.setObj(admin);
			}else{
				json.setSuccess(false);
				json.setMsg("短信发送失败！");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("该手机号没有权限！");
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
	 * @author songbaozhen
	 */
	private MobileCode getMobile(MessageRecord MR) {

		mobileCode.setId(ToolsUtil.getUUID());
		mobileCode.setMagRecordId(MR.getMsgid());
		mobileCode.setToMobile(toMobile);
		mobileCode.setVerificationCode(MR.getCode());
		mobileCode.setCreatetime(new Date());
		return mobileCode;
	}

	//

	/**
	 * 检测用户名是否已注册
	 * 
	 * @Title: doNotNeedSession_checkMobile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void doNotNeedSession_checkMobile() {
		Json j = new Json();
		try {
			if (null == toMobile || "".equals(toMobile.trim())) {
				j.setMsg("手机号不可为空");
			} else {
				flag = myAccountService.gainMemberMobile(toMobile);
				if (flag) {
					j.setMsg("此手机已存在！");
				} else {
					j.setSuccess(true);
				}
			}
		} catch (Exception e) {
			logger.info("系统异常：" + e.getMessage());
			j.setMsg("系统异常!");
		}
		writeJson(j);
	}

	/**
	 * 验证用户名是否存在
	 * 
	 * @Title: doNotNeedSession_exsitUsername()
	 * @Description: TODO(验证用户名是否存在)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author songbaozhne
	 */
	public String doNotNeedSession_exsitUsername() {
		request.setAttribute("exsit", registerService.existMember(members.getUsername()));
		return "message";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public Members getModel() {

		return members;
	}

}
