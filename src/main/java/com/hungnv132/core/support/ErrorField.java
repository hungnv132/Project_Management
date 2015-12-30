package com.hungnv132.core.support;

public class ErrorField {
	private String field;
	private String message;
	public ErrorField(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
	public ErrorField(){}
	public String getField() {
		return field;
	}
	public ErrorField setField(String field) {
		this.field = field;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ErrorField setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
	
}
