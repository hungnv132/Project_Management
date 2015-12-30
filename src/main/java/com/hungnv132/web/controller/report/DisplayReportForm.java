package com.hungnv132.web.controller.report;

import org.joda.time.LocalDateTime;

import com.hungnv132.core.domain.Report.REPORT_STATUS;

public class DisplayReportForm {

	private int id;

	private LocalDateTime createAt;

	private REPORT_STATUS status;

	private String projectName;

	public DisplayReportForm() {
	}

	public DisplayReportForm(int id, LocalDateTime createAt, REPORT_STATUS status, String projectName) {
		this.id = id;
		this.createAt = createAt;
		this.status = status;
		this.projectName = projectName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public REPORT_STATUS getStatus() {
		return status;
	}

	public void setStatus(REPORT_STATUS status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
