package com.sanji.mall.appliedreg.service;

import com.sanji.mall.model.AppliedReg;
import com.sanji.mall.pojo.Json;

public interface AppliedRegService {
	
	public Json addAppliedReg(AppliedReg appliedReg) throws Exception;
	
	public boolean exsistReg(String mobile);

}
