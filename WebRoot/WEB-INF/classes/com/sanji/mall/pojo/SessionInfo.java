package com.sanji.mall.pojo;





/**
 * 登录信息
* @ClassName: SessionInfo
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 周张豹
* @date 2014-7-15 下午1:47:09
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;// 用户ID
	private String loginName;// 用户登录名称
	private String loginPassword;// 登录密码
	private String loginNickName;//昵称
	private String userName;
	private String ip;// IP地址
	private String userType;//用户类别：个人会员——'member'，企业会员——'company'
    private String area;//用户所属区县

	private String moblie;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	private String msg;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the loginPassword
	 */
	public String getLoginPassword() {
		return loginPassword;
	}
	/**
	 * @param loginPassword the loginPassword to set
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginNickName() {
		return loginNickName;
	}
	public void setLoginNickName(String loginNickName) {
		this.loginNickName = loginNickName;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	
	
}
