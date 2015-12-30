package com.hungnv132.web.controller.report;

import org.joda.time.LocalDateTime;

import com.hungnv132.core.domain.Report.REPORT_STATUS;
import com.hungnv132.core.domain.User;

public class ReportListForManagerForm {

	private int id;

	private LocalDateTime createAt;

	private String projectName;
	
	private User staff;

	private REPORT_STATUS status;



	public ReportListForManagerForm(int id, LocalDateTime createAt, User staff, REPORT_STATUS status) {
		this.id = id;
		this.createAt = createAt;
		this.staff = staff;
		this.status = status;
	}

	public ReportListForManagerForm() {

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


	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


}
