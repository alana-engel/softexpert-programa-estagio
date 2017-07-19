package com.com.softexpert;

public class EntityResponse {
	
	private boolean success;
	private String msg;
	
	public EntityResponse(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public EntityResponse() {
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
