package com.hungnv132.web.controller.project;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.google.gson.annotations.Expose;
import com.hungnv132.core.domain.Business;
import com.hungnv132.core.domain.Evaluation;
import com.hungnv132.core.domain.User;
import com.hungnv132.core.domain.Project.PROJECT_STATUS;

public class CreateProjectForm {

public enum PROJECT_STATUS{WAITING, RUNNING, FINISHED, FAILED }
	
	@NotEmpty
	private String projectName;
	
	@NotEmpty
	private String projectDesc;
	
	@NotNull
//	@NumberFormat(style=Style.CURRENCY)
	private BigDecimal budget;
	
	@NotNull
	private Integer numOfMem;
	
	@NotNull
	private LocalDateTime createAt;
	
	@NotNull
	private LocalDateTime startDate;
	
	@NotNull
	private LocalDateTime endDate;
	
	@NotEmpty
	private String technology;
	
	@Valid
	private List<BusinessForm> business;

	
	public CreateProjectForm(){}
	public CreateProjectForm(String projectName, String projectDesc, BigDecimal budget, Integer numOfMem,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime closeDate, String technology,
			List<BusinessForm> business) {
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.budget = budget;
		this.numOfMem = numOfMem;
		this.startDate = startDate;
		this.endDate = endDate;
		this.technology = technology;
		this.business = business;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public Integer getNumOfMem() {
		return numOfMem;
	}
	public void setNumOfMem(Integer numOfMem) {
		this.numOfMem = numOfMem;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public List<BusinessForm> getBusiness() {
		return business;
	}
	public void setBusiness(List<BusinessForm> business) {
		this.business = business;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	
	
}
