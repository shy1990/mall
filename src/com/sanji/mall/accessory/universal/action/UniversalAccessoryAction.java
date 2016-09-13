package com.sanji.mall.accessory.universal.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sanji.mall.accessory.service.AccessoryService;
import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.model.Accessory;
import com.sanji.mall.pojo.Json;

/**
 * 
 * @ClassName: UniversalAccessoryAction
 * @Description: 通用配件action
 * @author WuJiming wzslw_163_com
 * @date 2014年10月28日 上午9:16:40
 */
@Namespace("/accessory")
@Action(value = "universalAccessoryAction", results = { @Result(name = "show", location = "/admin/accessory/universal/show.jsp") })
public class UniversalAccessoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource
	private  AccessoryService accessoryService;
	
	public String show(){
		String cartIds = getRequest().getParameter("cartIds");
		getRequest().setAttribute("cartIds", cartIds);
		return "show";
	}
	/**
	 * 
	* @Title: getCommon
	* @Description: 通用配件    设定文件
	* void    返回类型
	* @throws
	 */
	public void getCommon(){
		String type=getRequest().getParameter("type");
		List<Accessory> pjs=accessoryService.getCommon(type,1,5);
		Json json=new Json();
		if(pjs!=null){
			json.setSuccess(true);
			json.setObj(pjs);
		}else{
			json.setSuccess(false);
		}
		writeJson(json);
	}
}
