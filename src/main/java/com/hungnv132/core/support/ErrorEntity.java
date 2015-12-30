package com.hungnv132.core.support;

import java.util.List;

public class ErrorEntity extends Response {

	private List<ErrorField> errorList;

	public ErrorEntity() {
	}

	public ErrorEntity(List<ErrorField> errorList) {
		this.errorList = errorList;

	}
	public List<ErrorField> getErrorList() {
		return errorList;
	}

	public ErrorEntity setErrorList(List<ErrorField> errorList) {
		this.errorList = errorList;
		return this;
	}

}
