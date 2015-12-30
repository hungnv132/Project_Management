package com.hungnv132.core.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reports")
public class Report extends CommonObject {

	public enum REPORT_STATUS {
		 APPROVED, REJECTED, WAITING
	};

	@Column(name = "manager_comment")
	private String comment;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private REPORT_STATUS status;

	@JsonBackReference("")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_report")
	private User memberReport;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "confirm_by")
	private User confirmBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;
	
	@JsonManagedReference
	@OneToMany(mappedBy="report", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval= true)
	@Fetch(FetchMode.SUBSELECT)
	private List<Task> tasks;

	public Report(String content, String comment, REPORT_STATUS status, User memberReport, User confirmBy) {
		this.comment = comment;
		this.memberReport = memberReport;
		this.confirmBy = confirmBy;
	}

	public Report() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public REPORT_STATUS getStatus() {
		return status;
	}

	public void setStatus(REPORT_STATUS status) {
		this.status = status;
	}

	public User getMemberReport() {
		return memberReport;
	}

	public void setMemberReport(User memberReport) {
		this.memberReport = memberReport;
	}
	
	public User getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(User confirmBy) {
		this.confirmBy = confirmBy;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public void setDefaultData() {
		this.setStatus(REPORT_STATUS.WAITING);
		this.setUpdateAt(new LocalDateTime());
	}

	
}
