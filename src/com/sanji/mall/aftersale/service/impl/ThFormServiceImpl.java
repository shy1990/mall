package com.sanji.mall.aftersale.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sanji.mall.aftersale.dao.ThFormMapper;
import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.ThForm;
import com.sanji.mall.aftersale.service.ThFormService;
import com.sanji.mall.common.util.ToolsUtil;
import com.sanji.mall.members.dao.MembersMapper;
import com.sanji.mall.model.Members;
import com.sanji.mall.model.OrderItems;
import com.sanji.mall.order.dao.OrderItemsMapper;
import com.sanji.mall.order.dao.OrderMapper;
import com.sanji.mall.point.dao.PointGoodsMapper;

@Service("thFormService")
public class ThFormServiceImpl implements ThFormService {

	@Resource
	private ThFormMapper thFormMapper;

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private OrderItemsMapper orderItemsMapper;

	@Resource
	private MembersMapper membersMapper;

	@Resource
	private PointGoodsMapper pointGoodsMaper;

	// TODO 积分系数未设置
	private float pointCoefficient = 10;

	public int addThForm(ThForm thForm) throws Exception {

		// System.out.println("service,原因：" + thForm.getRemark());
		thForm.setId(ToolsUtil.getUUID());
		int result = thFormMapper.insertSelective(thForm);

		// 改变订单状态 1 申请退款退货中
		thForm.getOrder().setStatus("1");
		thForm.getOrder().setPayStatus("2");
		orderMapper.updateByPrimaryKeySelective(thForm.getOrder());

		// 保存
		FormItem formItem = new FormItem();

		formItem.setForm(thForm);
		if (thForm.getOrder().getOrderItemss() != null && thForm.getOrder().getOrderItemss().size() > 0) {
			// 遍历订单详情
			for (OrderItems oi : thForm.getOrder().getOrderItemss()) {
				formItem.setId(ToolsUtil.getUUID());
				// 单品
				if (oi.getGoodsSku() != null && oi.getGoodsSku().getGoods() != null) {
					formItem.setName(oi.getGoodsSku().getGoods().getName());// 名称
				}
				// 配件
				if (oi.getAccessory() != null && oi.getAccessory().getName() != null) {
					formItem.setName(oi.getAccessory().getName());// 名称
				}
				// 赠品
				if (oi.getGift() != null && oi.getGift().getAccessory() != null) {
					formItem.setName(oi.getGift().getAccessory().getName());// 名称
				}
				// 积分商品
				if (oi.getPointGoods() != null && oi.getPointGoods().getName() != null) {
					formItem.setName(oi.getPointGoods().getName());// 名称
				}
				// System.out.println("   要保持的订单详情Id:" + oi.getId());

				formItem.setQuantity(oi.getNums().intValue());// 数量
				OrderItems orderItems = orderItemsMapper.selectByPrimaryKey(oi.getId());
				formItem.setOrderItems(orderItems);
				thFormMapper.insertItemSelective(formItem);
			}
		}

		// 如果不是未付款订单就减去相应的积分
		if (!thForm.getOrder().getPayStatus().equals("0")) {
			// editPoint(thForm.getOrder().getTotalCost(),
			// thForm.getOrder().getMemberId());
		}

		// 根据订单Id查询订单信息，根据订单信息查询订单详情列表。遍历列表去名称，然后保存退货详情记录

		return result;
	}

	public int addThForm(ThForm thForm, String oItemsId) throws Exception {
		// System.out.println("service,原因：" + thForm.getRemark());
		thForm.setId(ToolsUtil.getUUID());
		int result = thFormMapper.insertSelective(thForm);

		// 改变订单状态 1 申请退款退货中
		// thForm.getOrder().setStatus("1");// 单个退款就不改变订单状态了吧。。。
		// orderMapper.updateByPrimaryKeySelective(thForm.getOrder());

		// 保存
		FormItem formItem = new FormItem();
		BigDecimal money = BigDecimal.ZERO;

		formItem.setForm(thForm);
		if (thForm.getOrder().getOrderItemss() != null && thForm.getOrder().getOrderItemss().size() > 0) {
			// 遍历订单详情
			for (OrderItems oi : thForm.getOrder().getOrderItemss()) {
				// System.out.println("传入的详情id和遍历的当前id对比：" + oItemsId + "   " +
				// oi.getId());
				money = oi.getAmount();
				// 如果传入的订单详情id和当前遍历的订单详情id相同，那么就记录到数据库
				if (oi.getId().equals(oItemsId)) {
					formItem.setId(ToolsUtil.getUUID());
					// 单品
					if (oi.getGoodsSku() != null && oi.getGoodsSku().getGoods() != null) {
						formItem.setName(oi.getGoodsSku().getGoods().getName());// 名称
					}
					// 配件
					if (oi.getAccessory() != null && oi.getAccessory().getName() != null) {
						formItem.setName(oi.getAccessory().getName());// 名称
					}
					// 赠品
					if (oi.getGift() != null && oi.getGift().getAccessory() != null) {
						formItem.setName(oi.getGift().getAccessory().getName());// 名称
					}
					// 积分商品
					if (oi.getPointGoods() != null && oi.getPointGoods().getName() != null) {
						formItem.setName(oi.getPointGoods().getName());// 名称
					}
					formItem.setQuantity(oi.getNums().intValue());// 数量
					// OrderItems orderItems =
					// orderItemsMapper.selectByPrimaryKey(oi.getId());

					// 修改对应的订单详情退款状态
					String status = thForm.getStatus().name();
					// System.out.println("修改订单详情状态：" + status +
					// "   要保持的订单详情Id:" + oi.getId());
					oi.setStatus(status);
					orderItemsMapper.updateByPrimaryKeySelective(oi);

					formItem.setOrderItems(oi);
					thFormMapper.insertItemSelective(formItem);
				}
			}
		}
		// editPoint(money, thForm.getOrder().getMemberId());
		return result;
	}

	/**
	 * 更新用户积分信息
	 * 
	 * @Title: editPoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param money
	 *            退款金额数量
	 * @return void 返回类型
	 * @author 田超强
	 * @throws
	 */
	public void editPoint(BigDecimal money, String userId) {
		Members member = membersMapper.selectByPrimaryKey(userId);
		BigDecimal point = BigDecimal.ZERO;// 初始化积分变量为0

		point = point.add(money.multiply(BigDecimal.valueOf(pointCoefficient)));// 钱数*积分换算系数=积分数量

		point = BigDecimal.valueOf(Math.floor(point.doubleValue()));// 对计算出的积分取整

		// 消费积分
		point = member.getPoint().subtract(point);// 用户已有积分减去本次退款应减的积分

		// 更新积分
		// pointGoodsMaper.updatePoint(member.getId(), point);
		member.setPoint(point);
		membersMapper.updateByPrimaryKey(member);
	}

	public int addItem(FormItem item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public ThForm gainThFormByOid(String oid) throws Exception {
		// TODO Auto-generated method stub
		return thFormMapper.selectByOid(oid);
	}

	public List<ThForm> selectByOidAdOitemId(String oid, String oItemsId) {
		// TODO Auto-generated method stub
		return thFormMapper.selectByOidAdOitemId(oid, oItemsId);
	}

	public ThForm selectUnfinished(String oid, String oItemsId) {
		// TODO Auto-generated method stub
		return thFormMapper.selectUnfinished(oid, oItemsId);
	}

	public int updateByPrimaryKeySelective(ThForm record) {
		// TODO Auto-generated method stub
		return thFormMapper.updateByPrimaryKeySelective(record);
	}

	public ThForm selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return thFormMapper.selectByPrimaryKey(id);
	}

}
