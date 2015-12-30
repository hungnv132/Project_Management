package com.hungnv132.core.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.gson.annotations.Expose;


@Entity
@Table(name= "projects")
public class Project extends CommonObject {
	
	public enum PROJECT_STATUS{ RUNNING, FINISHED, FAILED }
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="budget")
	private BigDecimal budget;
	
	@Column(name="number_of_members")
	private int numOfMem;
	
	@Column(name="start_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startDate;
	
	@Column(name="end_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime endDate;
	
	@Column(name="close_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime closeDate;
	
	@Column(name="technology")
	private String technology;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private PROJECT_STATUS status;
	
	@Expose(serialize= false)
	@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="project")
	private List<Evaluation> evaluations; 
	
	@Expose(serialize= false)
	@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="project")
	private List<Business> business;
	
	@Expose(serialize= false)
	@OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL, mappedBy="project")
	private List<Report> reports;
	
	public Project(String name) {
		this.name = name;
	}
	
	public Project(int id){
		super(id);
	}
	
	public Project(){}

	public String geName() {
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

	public int getNumOfMem() {
		return numOfMem;
	}

	public void setNumOfMem(int numOfMem) {
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

	public LocalDateTime getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDateTime closeDate) {
		this.closeDate = closeDate;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public PROJECT_STATUS getStatus() {
		return status;
	}

	public void setStatus(PROJECT_STATUS status) {
		this.status = status;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public String getName() {
		return name;
	}

	public List<Business> getBusiness() {
		return business;
	}

	public void setBusiness(List<Business> business) {
		this.business = business;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	
}
