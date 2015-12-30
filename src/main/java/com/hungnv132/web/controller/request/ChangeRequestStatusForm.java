package com.hungnv132.web.controller.request;

import javax.validation.constraints.NotNull;

import com.hungnv132.core.domain.Request.REQUEST_STATUS;

public class ChangeRequestStatusForm {

	@NotNull
	private Integer id;

	@NotNull
	private REQUEST_STATUS status;

	private String comment;

	public ChangeRequestStatusForm() {
	}

	public ChangeRequestStatusForm(int id, REQUEST_STATUS status) {
		this.id = id;
		this.status = status;
	}

	public REQUEST_STATUS getStatus() {
		return status;
	}

	public void setStatus(REQUEST_STATUS status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
