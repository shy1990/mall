/**  
* @Title: HomeAction.java
* @Package com.sanji.mall.home.action
* @Description: TODO(用一句话描述该文件做什么)
* @author ZhouZhangbao  
* @date 2014-10-20 下午4:23:15
* @version V1.0  
*/
package com.sanji.mall.home.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.sanji.mall.common.util.BaseAction;
import com.sanji.mall.common.util.ResourceUtil;
import com.sanji.mall.home.service.HomeService;
import com.sanji.mall.members.action.LongAction;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.service.OrderItemsService;
import com.sanji.mall.pojo.SessionInfo;

/**
 *  商城主页Action
 * @ClassName: HomeAction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-10-20 下午4:23:15
 */
@Namespace("/home")
@Action(value = "homeAction", results = {
		@Result(name = "toHome",location = "/admin/home/home.jsp")
})
public class HomeAction extends BaseAction{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HomeAction.class);
	@Resource
	private HomeService homeService;
	@Resource
	private OrderItemsService orderItemsService;
	
	private OrderItems orderItems = new OrderItems();
	/**
	 * 跳转到主页
	* @Title: toHome
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @author ZhouZhangbao
	 */
	public String toHome(){
		SessionInfo sessionInfo = (SessionInfo) this.session.get(ResourceUtil.getSessionInfoName());
		try {
			List<OrderItems> itemList =	orderItemsService.gainOrderItemAllSku(orderItems);
			request.setAttribute("itemList",itemList);
			
			String city=homeService.gainCityById(sessionInfo.getUserId());
			request.setAttribute("city",city);
			return "toHome";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("异常+++++++++++++++++++++：" + e.getMessage());
			return "err";
		}
	}
	
	/**
	 * 获取会员总数
	* @Title: gainMemberNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     设定文件
	* @return void    返回类型
	* @author ZhouZhangbao
	 */
	public void gainMemberNum(){
		try {
			super.writeJson(homeService.gainMemberNum());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public OrderItems getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItems orderItems) {
		this.orderItems = orderItems;
	}


}
