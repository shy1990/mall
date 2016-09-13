package com.sanji.mall.pay.dao;

import com.sanji.mall.model.PayRefund;


public interface PayRefundMapper {

    int insertSelective(PayRefund record);

    PayRefund selectByPrimaryKey(String id);

}