package com.hungnv132.core.support;

public class ReturnEntity extends Response {

	private Object data;

	public ReturnEntity() {
	}

	public ReturnEntity(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/*
	 * public static UpdateUserForm copyFromUser(User user){ UpdateUserForm form
	 * = new UpdateUserForm(); BeanUtils.copyProperties(user, form); return
	 * form; }
	 */
}
