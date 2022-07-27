/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.swen90007sm2.core.exception;

/**
 * customized Exception
 *
 * @author xiaotian
 */
public class OtherException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public OtherException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public OtherException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public OtherException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public OtherException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
