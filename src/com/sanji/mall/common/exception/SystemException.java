package com.sanji.mall.common.exception;

/**
 * 异常类
 * @author ZhouZhangbao
 * 2014-7-14 上午11:52:31
 * TODO
 */
public class SystemException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public SystemException(){
		super();
	}
	public SystemException(String mesg){
		super(mesg);
	}
	public SystemException(String mesg, Throwable rootCause) {
        super(mesg);
        rootCause.printStackTrace();
	}
}
