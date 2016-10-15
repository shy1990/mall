/**  
* @Title: UserAction.java
* @Package com.sanji.mall.test.action
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-7-15 下午3:31:41
* @version V1.0  
*/
package com.sanji.mall.test.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.model.Companys;
import com.sanji.mall.test.service.CompanyService;

/**
 * 测试获取用户信息的ACTION
 * @ClassName: UserAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-15 下午3:31:41
 */
@Namespace("/user")
@Action(value = "userAction", results = {
		@Result(name = "succeed",location = "/mall/succeed.jsp")
})
public class UserAction extends BaseAction{
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	private Companys companys;
	private List<Companys> cList;
	
	@Resource
	private CompanyService companyService;
	
	public String userTologin(){
		System.out.println(companys.getUsername()+"<<");
		cList = companyService.selectCompanyByUserName(companys.getUsername());
		System.out.println(cList.size()+">>>");
		return "succeed";
	}

	/**
	 * @return the companys
	 */
	public Companys getCompanys() {
		return companys;
	}

	/**
	 * @param companys the companys to set
	 */
	public void setCompanys(Companys companys) {
		this.companys = companys;
	}

	/**
	 * @return the cList
	 */
	public List<Companys> getcList() {
		return cList;
	}

	/**
	 * @param cList the cList to set
	 */
	public void setcList(List<Companys> cList) {
		this.cList = cList;
	}

	
	

}
