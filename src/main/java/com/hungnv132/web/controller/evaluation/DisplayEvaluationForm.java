package com.hungnv132.web.controller.evaluation;


import java.math.BigDecimal;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DisplayEvaluationForm {

	private int id;
	
	private int totalPoint;
	
	private LocalDateTime createAt;
	
	private String firstName;
	
	private String lastName;
	
	private String midName;
	private String projectName;

	public DisplayEvaluationForm(int id, int totalPoint, LocalDateTime createAt, String firstName, String lastName,
			String midName) {
		this.id = id;
		this.totalPoint = totalPoint;
		this.createAt = createAt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midName = midName;
	}
	
	public DisplayEvaluationForm(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		
		this.id = id;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint.intValue();
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		this.createAt = formatter.parseLocalDateTime(createAt);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	

	
	
	
}
