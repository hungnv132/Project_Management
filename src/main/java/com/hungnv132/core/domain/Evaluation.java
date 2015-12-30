package com.hungnv132.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


@Entity
@Table(name="evaluations")
public class Evaluation extends CommonObject {
	
	@Column(name="task_completion")
	private int taskCompletion;
	
	@Column(name="task_complexity")
	private int taskComplexity;
	
	@Column(name="working_discipline")
	private int workingDiscipline;
	
	@Column(name="manager_comment")
	private String comment;
	
	@Column(name="skill")
	private int skill;
	
	@Column(name="behavior")
	private int behavior;
	
	@Column(name="training_course_attended")
	private String trainingCourseAttended;
	
	@Column(name="certificates")
	private String certificate;
	
//	@Column(name="language_scope")
//	private String languageScope;
	
	@Column(name="award")
	private String award;
	
//	@Column(name="award_course_date")
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime awardCourseDate;
//	
//	@Column(name="award_course_content")
//	private String awardCourseContent;
	
	@Column(name="penalty")
	private String penalty;
	
//	@Column(name="penalty_date")
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime penaltyDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="staff_id")
	private User staff;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="evaluated_by")
	private User evaluatedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="project_id")
	private Project project;
	
	
	
	public Evaluation(){}

	public int getTaskCompletion() {
		return taskCompletion;
	}

	public void setTaskCompletion(int taskCompletion) {
		this.taskCompletion = taskCompletion;
	}

	public int getTaskComplexity() {
		return taskComplexity;
	}

	public void setTaskComplexity(int taskComplexity) {
		this.taskComplexity = taskComplexity;
	}

	public int getWorkingDiscipline() {
		return workingDiscipline;
	}

	public void setWorkingDiscipline(int workingDiscipline) {
		this.workingDiscipline = workingDiscipline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getBehavior() {
		return behavior;
	}

	public void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	public String getTrainingCourseAttended() {
		return trainingCourseAttended;
	}

	public void setTrainingCourseAttended(String trainingCourseAttended) {
		this.trainingCourseAttended = trainingCourseAttended;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

//	public String getLanguageScope() {
//		return languageScope;
//	}
//
//	public void setLanguageScope(String languageScope) {
//		this.languageScope = languageScope;
//	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public User getEvaluatedBy() {
		return evaluatedBy;
	}

	public void setEvaluatedBy(User evaluatedBy) {
		this.evaluatedBy = evaluatedBy;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
}












