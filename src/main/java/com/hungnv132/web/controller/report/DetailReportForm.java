package com.hungnv132.web.controller.report;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.hungnv132.core.domain.Report.REPORT_STATUS;
import com.hungnv132.core.domain.Task;

public class DetailReportForm {
	
	private LocalDateTime createAt;
	
	private LocalDateTime updateAt;
	
	private String projectName;
	
	private String confirmBy;
	
	private String userReport;
	
	private REPORT_STATUS  status;
	
	private String comment;
	
	private List<Task> tasks;

	public DetailReportForm(LocalDateTime createAt, LocalDateTime updateAt, String confirmBy, REPORT_STATUS status, String comment,
			List<Task> tasks) {
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.confirmBy = confirmBy;
		this.status = status;
		this.comment = comment;
		this.tasks = tasks;
	}
	
	public DetailReportForm(){
		
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String confirmBy) {
		this.confirmBy = confirmBy;
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

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getUserReport() {
		return userReport;
	}

	public void setUserReport(String userReport) {
		this.userReport = userReport;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	
	

}
