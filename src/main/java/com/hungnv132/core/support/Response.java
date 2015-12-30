package com.hungnv132.core.support;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.Expose;
import com.hungnv132.core.domain.User;
import com.hungnv132.web.controller.user.UpdateUserForm;

public class Response {

	public enum RES_STATUS {
		FAILED, SUCCESS
	}
	
	@Expose()
	private String message;
	
	@Expose
	private RES_STATUS status;
	
	@Expose
	private Object returnedData;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public RES_STATUS getStatus() {
		return status;
	}

	public Response setStatus(RES_STATUS status) {
		this.status = status;
		return this;
	}

	public Object getReturnedData() {
		return returnedData;
	}

	public void setReturnedData(Object returnedData) {
		this.returnedData = returnedData;
	}
	
	

	/*
	 * public static UpdateUserForm copyFromUser(User user){ UpdateUserForm form
	 * = new UpdateUserForm(); BeanUtils.copyProperties(user, form); return
	 * form; }
	 */

}
