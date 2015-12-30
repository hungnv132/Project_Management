package com.hungnv132.web.controller.report;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDateTime;

public class EditReportForm {
	
	private int id;
	
	@NotNull
	private LocalDateTime createAt;
	
	@NotNull
	private int pmApprove;
	
	@Valid
	private List<EditTaskForm> tasks;
	
	
	public EditReportForm(LocalDateTime createAt, LocalDateTime updateAt, int pmApprove,  List<EditTaskForm> tasks) {
		this.createAt = createAt;
		this.pmApprove = pmApprove;
		this.tasks = tasks;
	}

	public EditReportForm(){
		
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<EditTaskForm> getTasks() {
		return tasks;
	}

	public void setTasks(List<EditTaskForm> tasks) {
		this.tasks = tasks;
	}

	public int getPmApprove() {
		return pmApprove;
	}

	public void setPmApprove(int pmApprove) {
		this.pmApprove = pmApprove;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	

}
