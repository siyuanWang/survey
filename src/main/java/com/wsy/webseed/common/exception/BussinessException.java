package com.wsy.webseed.common.exception;


/**
 *  implemence Bussiness Exception
 * @author wangsy
 *
 */
public class BussinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String serviceMsg;
	private int code;

	public BussinessException(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public BussinessException(String serviceMsg, int code) {
		this.serviceMsg = serviceMsg;
		this.code = code;
	}

	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
