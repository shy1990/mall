package com.sanji.mall.aftersale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanji.mall.aftersale.dao.QhFormMapper;
import com.sanji.mall.aftersale.model.QhForm;
import com.sanji.mall.aftersale.service.QhFormService;

@Service("qhFormService")
public class QhFormServiceImpl implements QhFormService {

	@Autowired
	private QhFormMapper qhFormMapper;

	public void edit(QhForm qhForm) {
		qhFormMapper.updateByPrimaryKeySelective(qhForm);
	}
}
