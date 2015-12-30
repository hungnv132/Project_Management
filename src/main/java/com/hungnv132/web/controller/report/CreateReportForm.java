package com.hungnv132.web.controller.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hungnv132.core.domain.Report;
import com.hungnv132.core.domain.Task;
import com.hungnv132.core.support.Json_JodaDate_Serializer;
import com.hungnv132.core.support.validator.CheckDate;

public class CreateReportForm {

	@NotNull
	private LocalDateTime createAt;
	
	@NotNull
	private Integer projectId;
	
	@Valid
	private List<TaskForm>  tasks;

	
	public CreateReportForm(){}
	
	public CreateReportForm(LocalDateTime createAt, Integer projectId, List<TaskForm> tasks) {
		this.createAt = createAt;
		this.projectId = projectId;
		this.tasks = tasks;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public List<TaskForm> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskForm> tasks) {
		this.tasks = tasks;
	}
	
	
}
