package com.sanji.mall.aftersale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sanji.mall.aftersale.dao.WxFormMapper;
import com.sanji.mall.aftersale.model.FormItem;
import com.sanji.mall.aftersale.model.WxForm;
import com.sanji.mall.aftersale.service.WxFormService;
import com.sanji.mall.common.util.ToolsUtil;

@Service("wxFormService")
public class WxFormServiceImpl implements WxFormService {
	@Resource
	private WxFormMapper wxFormMapper;

	public int addWxForm(WxForm wxForm) throws Exception {
		wxForm.setId(ToolsUtil.getUUID());
		int result = wxFormMapper.insertSelective(wxForm);
		// 保存
		List<FormItem> items = wxForm.getItems();
		for (FormItem formItem : items) {
			formItem.setId(ToolsUtil.getUUID());
			formItem.setForm(wxForm);
			wxFormMapper.insertItemSelective(formItem);
		}
		return result;
	}

	public int addItem(FormItem item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
