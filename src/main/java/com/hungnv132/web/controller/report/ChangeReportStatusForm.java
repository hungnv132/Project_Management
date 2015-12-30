package com.hungnv132.web.controller.report;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hungnv132.core.domain.Report.REPORT_STATUS;

public class ChangeReportStatusForm {

	@NotNull
	private Integer id;
	
	@NotNull
	private REPORT_STATUS status;
	
	private String comment;

	public ChangeReportStatusForm(){}
	
	
	public ChangeReportStatusForm(int id, REPORT_STATUS status) {
		this.id = id;
		this.status = status;
	}

	public REPORT_STATUS getStatus() {
		return status;
	}

	public void setStatus(REPORT_STATUS status) {
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
