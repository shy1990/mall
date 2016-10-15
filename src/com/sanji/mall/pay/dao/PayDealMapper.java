package com.sanji.mall.pay.dao;

import java.util.Map;

import com.sanji.mall.model.PayDeal;


public interface PayDealMapper {
   
    int insertSelective(PayDeal record);

    /**
     * 根据支付
    * @Title: gainPayDealByDealID
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param dealId
    * @param @return    设定文件
    * @return PayDeal    返回类型
    * @author ZhouZhangbao
     */
    public PayDeal gainPayDealByDealID(Map<String, Object> map);
}