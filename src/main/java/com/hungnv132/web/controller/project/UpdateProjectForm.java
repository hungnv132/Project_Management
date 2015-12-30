package com.hungnv132.web.controller.project;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

import com.hungnv132.core.domain.Project.PROJECT_STATUS;

public class UpdateProjectForm {

	
	@NotNull
	private Integer id;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotNull
	private BigDecimal budget;
	
	@NotNull
	private PROJECT_STATUS status;
	
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
	
	
	public UpdateProjectForm(){}
	public UpdateProjectForm(String name, String description, BigDecimal budget, Integer numOfMem,
			LocalDateTime startDate, LocalDateTime endDate, LocalDateTime closeDate, String technology,
			List<BusinessForm> business) {
		this.name = name;
		this.description = description;
		this.budget = budget;
		this.numOfMem = numOfMem;
		this.startDate = startDate;
		this.endDate = endDate;
		this.technology = technology;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PROJECT_STATUS getStatus() {
		return status;
	}
	public void setStatus(PROJECT_STATUS status) {
		this.status = status;
	}
	
	
	
	
}
