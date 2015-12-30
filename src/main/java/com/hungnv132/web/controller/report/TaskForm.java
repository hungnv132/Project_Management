package com.hungnv132.web.controller.report;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalTime;

import com.hungnv132.core.domain.Task;

public class TaskForm {


	private int id;

	@NotEmpty
	private String content;

	@NotNull
	private LocalTime timeStart;

	@NotNull
	private LocalTime timeEnd;

	public TaskForm(int id, String content, LocalTime timeStart, LocalTime timeEnd) {
		this.id = id;
		this.content = content;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public TaskForm() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public  Task buildTask(){
		Task task = new Task();
		task.setContent(this.getContent());
		task.setTimeStart(this.getTimeStart());
		task.setTimeEnd(this.getTimeEnd());
		return task;
	}
}
