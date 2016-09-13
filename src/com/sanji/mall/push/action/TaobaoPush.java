package com.sanji.mall.push.action;

import org.apache.struts2.convention.annotation.Action;

import com.sanji.mall.common.util.BaseAction;

@Action("taobaoPush")
public class TaobaoPush extends BaseAction {

	private String code;

	public void doNotNeedSession_tuisong() {
		System.out.println("淘宝回调成功：" + getCode());
		writeJson("ok");
		// return "ok";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
