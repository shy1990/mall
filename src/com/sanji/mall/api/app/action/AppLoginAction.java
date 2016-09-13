/**  
 * @Title: SandPayAction.java
 * @Package com.sanji.mall.api.pos.action
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-12-4 下午3:02:05
 * @version V1.0  
 */
package com.sanji.mall.api.app.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.sanji.mall.admin.service.AdminService;
import com.sanji.mall.api.app.service.AppService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.FtpUtil;
import com.sanji.mall.common.util.ImageUtils;
import com.sanji.mall.common.util.JsonUtil;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.service.MemberService;
import com.sanji.mall.mobileCode.service.MobileCodeService;
import com.sanji.mall.model.Admin;
import com.sanji.mall.model.App;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.MessageRecord;
import com.sanji.mall.model.MobileCode;
import com.sanji.mall.model.Order;
import com.sanji.mall.msg.service.MessageRecordService;
import com.sanji.mall.order.service.OrderService;
import com.sanji.mall.pojo.Json;
import com.sanji.mall.pojo.ShopImgNode;

@Namespace("/app")
@Action(value = "appLoginAction", results = { @Result(name = "login", location = "/admin/pos/success.jsp") })
public class AppLoginAction extends BaseAction implements ModelDriven<Admin> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private AdminService adminService;
	@Resource
	private OrderService orderService;
	@Resource
	private AppService appService;
	@Resource
	private MemberService memberService;
	@Resource
	private MessageRecordService messageRecordService;
	@Resource
	private MobileCodeService mobileCodeService;
	private Admin admin = new Admin();
	private App app = new App();
	private Members members = new Members();
	private Order order = new Order();
	// private String userId;//"3e3807282423416fabbb264871ce8b8e";//操作人
	// private String orderNo = "20141126114417295";
	// private String shipStatus = "3";
	// private String memberId;//="c7e437246e864c259ffc8a1753a88296";//会员id
	// 9ff9ecb30db440eea26fc41a534d5e7f 95c92a22d7dd42df877440f196a209dc
	private File file;// 接收图片文件路径
	private String fileFileName;
	private String fileContentType;
	private String imgType = "1";// 图片类型（1.正面图片 2.合影 3.前面图片 4.后面图片 5.左面图片
									// 6.右面图片）
	private List<Members> list;// 接收用户注册的信息列表
	private boolean flag = false;
	private String feedbackJson;// 接收json字符串
	private MobileCode mobileCode = new MobileCode();
	private MessageRecord MR = new MessageRecord();

	private String orderCode;// 管易订单号或DD号
	private String overMan;// 操作人
	private String verificationCode;// 验证码
	private String mid;// 参数 mid as memberId

	/**
	 * 地包APP登录接口
	 * 
	 * @Title:doNotNeedSession_dituiLogin
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @return
	 */
	public void doNotNeedSession_dituiLogin() {
		Json json = new Json();
		try {
			if (null != admin.getUsername() && null != admin.getPassword()) {
				// //
				// System.out.println("密码===="+MD5.encrypt(admin.getPassword().trim()));
				admin = adminService.gainAdminByLogin(admin.getUsername(), admin.getPassword().trim());
				if (null != admin && null != admin.getId() && !"".equals(admin.getId())) {
					// json.setObj(admin.getUserId());
					json.setObj(admin.getId());
					json.setMsg("信息填写正确！");
					json.setSuccess(true);
				} else {
					json.setMsg("用户名或密码不正确！");
					json.setSuccess(false);
				}
			} else {
				json.setMsg("用户名或密码不能为空！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("APP登录接口出现异常：" + e);
		}
		writeJson(json);
	}

	/**
	 * APP推送order状态
	 * 
	 * @Title:doNotNeedSession_shipStatus
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void doNotNeedSession_shipStatus() {
		Json json = new Json();
		try {
			if (null != admin.getOrderNum() && !"".equals(admin.getOrderNum()) && null != admin.getShipStatus() && !"".equals(admin.getShipStatus())) {
				order = orderService.gainOrderByOrderNo(admin.getOrderNum());
				if (null != order && null != order.getId() && !"".equals(order.getId())) {
					order.setShipStatus(admin.getShipStatus());
					orderService.updateShipStatusByOrderNo(order);
					json.setMsg("订单发货状态修改成功！");
					json.setSuccess(true);
					// json.setObj(order);
				} else {
					json.setMsg("订单发货状态修改失败！");
					json.setSuccess(false);
				}
			} else {
				json.setMsg("该订单不存在！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_shipStatus出现异常：" + e);
		}
		writeJson(json);
	}

	/**
	 * 添加app信息(除图片之外的信息)
	 * 
	 * @Title:doNotNeedSession_updateApp
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void doNotNeedSession_addApp() {
		Json json = new Json();
		try {
			// System.out.println("feedbackJson===" + feedbackJson);
			if (null != feedbackJson && !"".equals(feedbackJson)) {
				app = (App) JsonUtil.getObject4JsonString(feedbackJson, App.class);
				String add = app.getAddress();
				// System.out.println("add:" + add);
			}
			if (null != app.getMemberId() && !"".equals(app.getMemberId())) {
				flag = appService.gainAppByMemberId(app.getMemberId());// 如果flag=false，表示存在；反之flag=true,表示不存在
				// System.out.println("flag==" + flag);
				if (flag) {// 不存在
					// System.out.println("这个flag===" + flag);
					app.setId(ToolsUtil.getUUID());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String shopkeeperBirthStr = app.getShopkeeperBirthStr();
					if (!"".equals(shopkeeperBirthStr)) {
						Date shopkeeperBirth = format.parse(shopkeeperBirthStr);
						app.setShopkeeperBirth(shopkeeperBirth);
					}
					String anniversaryBirthStr = app.getAnniversaryBirthStr();
					if (!"".equals(anniversaryBirthStr)) {
						Date anniversaryBirth = format.parse(anniversaryBirthStr);
						app.setAnniversaryBirth(anniversaryBirth);
					}

					appService.addApp(app);
					json.setObj(app);
					json.setMsg("客户信息上传成功！");
					json.setSuccess(true);
				} else {
					App a = appService.gainAppIDByMemberId(app.getMemberId());
					if (null != a && null != a.getId() && !"".equals(a.getId())) {
						app.setId(a.getId());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String shopkeeperBirthStr = app.getShopkeeperBirthStr();
						if (!"".equals(shopkeeperBirthStr)) {
							Date shopkeeperBirth = format.parse(shopkeeperBirthStr);
							app.setShopkeeperBirth(shopkeeperBirth);
						}
						String anniversaryBirthStr = app.getAnniversaryBirthStr();
						if (!"".equals(anniversaryBirthStr)) {
							Date anniversaryBirth = format.parse(anniversaryBirthStr);
							app.setAnniversaryBirth(anniversaryBirth);
						}
						appService.updateOriginalApp(app);
						json.setObj(app);
						json.setMsg("客户信息添加成功！");
						json.setSuccess(true);
					}
				}
			} else {
				json.setMsg("客户注册信息不存在！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_addApp出现异常：" + e);
		}
		writeJson(json);
	}

	/**
	 * 添加app信息（即添加图片）
	 * 
	 * @Title:doNotNeedSession_updateAppImg
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型 F:\apache-tomcat802\webapps\attached\img
	 * @throws http
	 *             ://192.168.2.141:802/attached/imgTest/
	 *             p197nkl53ooq81h4g11huhic1n1p1.jpg 图片类型（1.正面图片 2.合影 3.前面图片
	 *             4.后面图片 5.左面图片 6.右面图片）
	 */
	public void doNotNeedSession_addAppImg() {
		Json json = new Json();
		try {
			// 上传图片
			String path = this.uploadFileByFile(file, fileFileName);
			String spath = this.uploadThumbnail(file, fileFileName, path.substring(24));
			if (null != admin.getMemberId() && !"".equals(admin.getMemberId()) && null != admin.getUserId() && !"".equals(admin.getUserId())) {
				flag = appService.gainAppByMemberId(admin.getMemberId());// 如果flag=false，表示存在；反之flag=true,表示不存在
				// System.out.println("flag==" + flag);
				if (flag) {// 不存在
					app.setId(ToolsUtil.getUUID());
					app.setMemberId(admin.getMemberId());
					app.setUserId(admin.getUserId());
					if ("1".equals(imgType)) {
						app.setZhengmian(path);
						app.setsZhengmian(spath);
					} else if ("2".equals(imgType)) {
						app.setHeying(path);
						app.setsHeying(spath);
					} else if ("3".equals(imgType)) {
						app.setQianmian(path);
						app.setsQianmian(spath);
					} else if ("4".equals(imgType)) {
						app.setHoumian(path);
						app.setHoumian(spath);
					} else if ("5".equals(imgType)) {
						app.setZuomian(path);
						app.setsZuomian(spath);
					} else if ("6".equals(imgType)) {
						app.setYoumian(path);
						app.setsYoumian(spath);
					} else {
						json.setMsg("没有该类型图片！");
						json.setSuccess(false);
					}
					// System.out.println("imgType:" + imgType);
					app.setImgType(imgType);
					appService.addAppImg(app);
					json.setObj(app);
					json.setMsg("图片信息上传成功！");
					json.setSuccess(true);
				} else {
					App a = appService.gainAppIDByMemberId(admin.getMemberId());
					if (null != a && null != a.getId() && !"".equals(a.getId())) {
						app.setId(a.getId());
						app.setMemberId(admin.getMemberId());
						app.setUserId(admin.getUserId());
						if ("1".equals(imgType)) {
							app.setZhengmian(path);
							app.setsZhengmian(spath);
						} else if ("2".equals(imgType)) {
							app.setHeying(path);
							app.setsHeying(spath);
						} else if ("3".equals(imgType)) {
							app.setQianmian(path);
							app.setsQianmian(spath);
						} else if ("4".equals(imgType)) {
							app.setHoumian(path);
							app.setsHoumian(spath);
						} else if ("5".equals(imgType)) {
							app.setZuomian(path);
							app.setsZuomian(spath);
						} else if ("6".equals(imgType)) {
							app.setYoumian(path);
							app.setsYoumian(spath);
						} else {
							json.setMsg("没有该类型图片！");
							json.setSuccess(false);
						}
						app.setImgType(imgType);
						appService.updateAppImg(app);
						json.setObj(app);
						json.setMsg("图片信息添加成功！");
						json.setSuccess(true);
					}
				}
			} else {
				json.setMsg("客户注册信息不存在！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_addAppImg出现异常：" + e);
		}
		writeJson(json);
	}

	/**
	 * 根据memberId获取members表的id和username
	 * 
	 * @Title:doNotNeedSession_gainMembers
	 * @Description:TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void doNotNeedSession_gainMembers() {
		Json json = new Json();
		try {
			if (null != admin.getUsername() && !"".equals(admin.getUsername())) {
				members.setUsername(admin.getUsername());
				list = memberService.gainMembersByUsername(members);
				if (null != list && list.size() > 0) {
					writeJson(list);
					// System.out.println("模糊查询成功！");
				} else {
					writeJson(null);
					// System.out.println("没有查询结果！");
				}
			} else if (admin.getPage() != 0 && admin.getRows() != 0) {
				members.setPage(admin.getPage());
				members.setRows(admin.getRows());
				list = memberService.gainMembers(members);
				if (null != list && list.size() > 0) {
					writeJson(list);
					// System.out.println("分页查询成功！");
				} else {
					writeJson(null);
					// System.out.println("分页查询失败！");
				}
			}
		} catch (Exception e) {
			// // System.err.println(e);
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_gainMembers出现异常：" + e);
		}
		writeJson(json);
	}

	/**
	 * 根据memberId查询用户店铺信息
	 * 
	 * @Title:gainAppIDByMemberId
	 * @Description:TODO(根据memberId查询用户店铺信息)
	 * @param @return
	 * @return App
	 * @throws
	 * @return
	 */
	public void doNotNeedSession_gainOnlyAppByMemeberId() {
		Json j = new Json();
		try {
			App a = appService.gainOnlyAppByMemeberId(admin.getMemberId());
			if (a != null) {
				j.setObj(a);
				j.setSuccess(true);
			} else {
				// j.setObj(null);
				j.setSuccess(false);
			}
		} catch (Exception e) {
			j.setMsg("未知错误，请联系相关技术人员！");
			j.setSuccess(false);
			log.error("gainOnlyAppByMemeberId出现异常：" + e);
		}
		writeJson(j);
	}

	/*
	 * public String Test(){ if (null != file) { //
	 * System.out.println(file+">>>>>>>>>>>>>>>>>>>>>>"); //
	 * System.out.println(uploadFileByFile(file,fileFileName)); }
	 * 
	 * return ""; }
	 */

	/**
	 * 上传图片，并返回地址<br>
	 * 为APP开发接口
	 * 
	 * @Title: uploadFileByFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param uploadFile
	 * @param @param uploadFileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String uploadFileByFile(File uploadFile, String uploadFileName) {
		String uploadURL = null;
		if (uploadFile != null) {
			String extName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1).toLowerCase();
			FtpUtil ftp = new FtpUtil(ResourceUtil.getFtpIp(), ResourceUtil.getFtpUserName(), ResourceUtil.getFtpPassword());
			ftp.connectServer();
			String savePath = ResourceUtil.getMemberDianPuImage();
			String saveUrl = ResourceUtil.getWebPath() + savePath;
			if (!ftp.isDirExist(savePath)) {
				ftp.createDir(savePath);
			}
			ftp.cd(savePath);
			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extName;
			if (!Arrays.<String> asList(ResourceUtil.getMemberDPImagesExt().split(",")).contains(extName)) {
				this.uploadError("上传文件扩展名是不允许的扩展名。\n只允许" + ResourceUtil.getMemberDPImagesExt() + "格式！");
				return null;
			}
			ftp.upload(uploadFile.getPath(), newFileName);
			uploadURL = saveUrl + newFileName;
			// System.out.println("uploadURL:" + uploadURL);
		}
		return uploadURL;
	}

	/**
	 * 上传图片缩略图，并返回地址<br>
	 * 为APP开发接口
	 * 
	 * @Title: uploadFileByFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param uploadFile
	 * @param @param uploadFileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public String uploadThumbnail(File uploadFile, String uploadFileName, String drpath) {

		String suploadURL = null;

		String ThumbnailFileName = "s_" + drpath.substring(31);
		String saveThumbnailPath = ResourceUtil.getMemberDianPuImage();// /attached/memberDianPuImage/img/
		String saveThumbnailUrl = ResourceUtil.getWebPath() + saveThumbnailPath;// http://image.3j1688.com
		suploadURL = saveThumbnailUrl + ThumbnailFileName;// 数据库需要存储的完整路径

		String yaSuoSaveRootPath = "D:/apache-tomcat7-803/webapps" + saveThumbnailPath + ThumbnailFileName;

		ImageUtils.zoomImage(uploadFile, yaSuoSaveRootPath, 120, 120, "jpg");

		// 返回保存后的服务器地址
		return suploadURL;
	}

	/**
	 * @Title: uploadError
	 * @Description: TODO上传失败
	 * @param @param err
	 * @param @param msg    设定文件
	 * @return void    返回类型
	 */
	private void uploadError(String err) {
		this.uploadError(err, "");
	}

	/**
	 * @Title: uploadError
	 * @Description: TODO上传失败
	 * @param @param err
	 * @param @param msg    设定文件
	 * @return void    返回类型
	 */
	private void uploadError(String err, String msg) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("err", err);
		m.put("msg", msg);
		super.writeJson(m);
	}

	/**
	 * @Title:doNotNeedSession_getOrderInfo
	 * @deprecated:根据订单号查询订单信息
	 * @param:app
	 * @return: void
	 * @author:songbaozhen
	 */

	public void doNotNeedSession_getOrderInfo() {
		Json json = new Json();
		try {
			// app = (App) JsonUtil.getObject4JsonString(feedbackJson,
			// App.class);
			// flag = checkShipStatus(this.orderCode);
			Order orderIfo = orderService.gainOrderInfoByOrderCode(this.orderCode);
			if (!"3".equals(orderIfo)) {
				if (orderIfo != null) {
					MR = messageRecordService.goodsReceiveCodeSender(orderIfo.getShipTel());
					mobileCode = getMobile(MR);
					mobileCodeService.addMobileCode(mobileCode);
					json.setObj(orderIfo);
					json.setMsg("订单信息获取成功!");
					json.setSuccess(true);
				} else {
					json.setMsg("订单不存在或订单号有误,请联系客服人员!");
					// app = (App) JsonUtil.getObject4JsonString(feedbackJson,
					// App.class);
					// flag = checkShipStatus(this.orderCode);
				}

			} else {
				json.setMsg("客户已签收!");
				json.setSuccess(false);
			}

		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_getOrderNum出现异常：" + e);
			log.error("doNotNeedSession_getOrderInfo出现异常：" + e);
		}

		writeJson(json);
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
		mobileCode.setToMobile(MR.getMobiles());
		mobileCode.setVerificationCode(MR.getCode());
		mobileCode.setCreatetime(new Date());
		return mobileCode;
	}

	/**
	 * 完成订单在线支付闭环，订单完成。
	 * 
	 * @Title: doNotNeedSession_goodsReceiveComplete()
	 * @Description: TODO(完成订单在线支付闭环，订单完成。)
	 * @param @return 设定文件
	 * @return void 返回类型
	 * @author songbaozhen
	 */
	public void doNotNeedSession_goodsReceiveComplete() {
		Json json = new Json();
		// app = (App) JsonUtil.getObject4JsonString(feedbackJson, App.class);
		Long code = checkCode(this.verificationCode);
		try {
			if (code > 0) {
				order.setShipStatus("3");
				order.setOrderNum(this.orderCode);
				order.setOverMan(this.overMan);
				order.setOverTime(new Date());
				orderService.updateShipStatusByOrderNo(order);
				json.setMsg("客户已确认签收！");
				json.setSuccess(true);
			} else {
				json.setMsg("验证码不正确，请重新输入！");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			json.setMsg("未知错误，请联系相关技术人员！");
			json.setSuccess(false);
			log.error("doNotNeedSession_goodsReceiveComplete出现异常：" + e);
		}
		super.writeJson(json);
	}

	/**
	 * 判断验正码是否存在
	 * 
	 * @Title :checkCode()
	 * @deprecated :(根据验证码查询一条信息)
	 * @param :code
	 * @return :long 返回类型
	 * @author :SongBaoZhen
	 */
	private Long checkCode(String code) {

		return mobileCodeService.existVerificationCode(code);
	}

	/**
	 * 根据店铺名称返回店铺照片url
	 * 
	 * @Title :doNotNeedSession_getShopImgUrl()
	 * @deprecated :(根据验证码查询一条信息)
	 * @param :username
	 * @return :long 返回类型
	 * @author :SongBaoZhen
	 */
	public void doNotNeedSession_getShopImgUrl() {
		try {
			super.writeJson(buildeShopImgList());
		} catch (Exception e) {
			// System.out.println("doNotNeedSession_getShopImgUrl异常：" +
			// e.getMessage());
		}

	}

	/**
	 * 创建店铺照片list结构
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List buildeShopImgList() {
		App appShopImgUrl = appService.gainAppImgByMemeberId(this.mid);
		List imglist = new ArrayList();
		ShopImgNode imgNode = new ShopImgNode();
		imgNode.setName("正面");
		ShopImgNode imgNode2 = new ShopImgNode();
		imgNode2.setName("合影");
		ShopImgNode imgNode3 = new ShopImgNode();
		imgNode3.setName("左面");
		ShopImgNode imgNode4 = new ShopImgNode();
		imgNode4.setName("右面");
		ShopImgNode imgNode5 = new ShopImgNode();
		imgNode5.setName("前面");
		ShopImgNode imgNode6 = new ShopImgNode();
		imgNode6.setName("后面");

		if (null == appShopImgUrl) {
			appShopImgUrl = new App();
		}

		if (appShopImgUrl.getsZhengmian() != null) {
			imgNode.setUrl(appShopImgUrl.getsZhengmian());
		} else {
			imgNode.setUrl("url");
		}
		if (appShopImgUrl.getsHeying() != null) {
			imgNode2.setUrl(appShopImgUrl.getsHeying());
		} else {
			imgNode2.setUrl("url");
		}
		if (appShopImgUrl.getsZuomian() != null) {

			imgNode3.setUrl(appShopImgUrl.getsZuomian());
		} else {
			imgNode3.setUrl("url");
		}
		if (appShopImgUrl.getsYoumian() != null) {

			imgNode4.setUrl(appShopImgUrl.getsYoumian());
		} else {
			imgNode4.setUrl("url");
		}
		if (appShopImgUrl.getsQianmian() != null) {

			imgNode5.setUrl(appShopImgUrl.getsQianmian());
		} else {
			imgNode5.setUrl("url");
		}
		if (appShopImgUrl.getsHoumian() != null) {

			imgNode6.setUrl(appShopImgUrl.getsHoumian());
		} else {
			imgNode6.setUrl("url");
		}
		imglist.add(0, imgNode);
		imglist.add(1, imgNode2);
		imglist.add(2, imgNode3);
		imglist.add(3, imgNode4);
		imglist.add(4, imgNode5);
		imglist.add(5, imgNode6);
		return imglist;

	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getOverMan() {
		return overMan;
	}

	public void setOverMan(String overMan) {
		this.overMan = overMan;
	}

	public Admin getModel() {
		return admin;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public List<Members> getList() {
		return list;
	}

	public void setList(List<Members> list) {
		this.list = list;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public String getFeedbackJson() {
		return feedbackJson;
	}

	public void setFeedbackJson(String feedbackJson) {
		this.feedbackJson = feedbackJson;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
