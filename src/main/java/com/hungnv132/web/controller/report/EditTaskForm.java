package com.hungnv132.web.controller.report;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class EditTaskForm {

	@NotNull
	private int id;

	@NotEmpty
	private String content;

	@NotNull
	private String timeStart;

	@NotNull
	private String timeEnd;

	public EditTaskForm(int id, String content, String timeStart, String timeEnd) {
		this.id = id;
		this.content = content;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public EditTaskForm() {
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

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

}
