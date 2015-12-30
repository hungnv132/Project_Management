package com.hungnv132.web.controller.report;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.support.Json_JodaDate_Serializer;

public class UpdateReportForm {
	
	private int id;
	
	@NotNull
	private LocalDateTime createAt;
	
	@NotNull
	private int projectId;
	
	@Valid
	private List<TaskForm> tasks;
	
	
	public UpdateReportForm(LocalDateTime createAt, LocalDateTime updateAt, int projectId,  List<TaskForm> tasks) {
		this.createAt = createAt;
		this.projectId = projectId;
		this.tasks = tasks;
	}

	public UpdateReportForm(){
		
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<TaskForm> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskForm> tasks) {
		this.tasks = tasks;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	

}
