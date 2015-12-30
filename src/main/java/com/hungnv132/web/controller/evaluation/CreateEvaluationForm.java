package com.hungnv132.web.controller.evaluation;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

public class CreateEvaluationForm {

	@NotNull
	private Integer staffId;
	
	private Integer managerId;
	
	@NotNull 
	private LocalDateTime createAt;
	
//	---------------------------------------- Performace
	@NotNull 
	private Integer projectId;
	
//	@NotEmpty 
	private String position;
	
	@NotNull 
	private Integer taskCompletion;
	
	@NotNull 
	private Integer taskComplexity;
	
	@NotNull 
	private Integer workingDiscipline;
	
/*	@NotEmpty
	private String performanceComment;*/
	
	@NotEmpty
	private String comment;
	
	private String award;
	
//	------------------------------- ---------Skill and Behavior
	@NotNull 
	private Integer skill;
	
	@NotNull 
	private Integer behavior;
	
//	@NotEmpty
//	private String skillAndBehaviorComment;
	
//	---------- ------------------------------training course achevement
//	private String trainingCourseAttended;
	
	private String certificate;
	
	private String languageScope;
	
//	private String trainingComment;
	
	
//	private String awardCourse;
	
//	private LocalDateTime awardCourseDate;
	
//	private String awardCourseContent;
	
	private String penalty;
	
//	private LocalDateTime penaltyDate;
	
	
//	---------- ------------------------------Bonus point
//	@NotEmpty
//	private String trainingCourse;
//	
//	@NotEmpty 
//	private String content;
//	
//	@NotNull 
//	private Integer scoreImpact;
//	
//	@NotEmpty
//	private String description;
//	
//	@NotEmpty 
//	private String benefit;
//	
//	@NotNull 
//	private LocalDateTime submitDate;
//	
//	@NotNull
//	private Integer rank;
//	
//	@NotEmpty 
//	private String award;
//	
//	@NotEmpty 
//	private String penalty;
//	
//	@NotEmpty
//	private String bonusCourseComment;

	public CreateEvaluationForm() {
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getTaskCompletion() {
		return taskCompletion;
	}

	public void setTaskCompletion(Integer taskCompletion) {
		this.taskCompletion = taskCompletion;
	}

	public Integer getTaskComplexity() {
		return taskComplexity;
	}

	public void setTaskComplexity(Integer taskComplexity) {
		this.taskComplexity = taskComplexity;
	}

	public Integer getWorkingDiscipline() {
		return workingDiscipline;
	}

	public void setWorkingDiscipline(Integer workingDiscipline) {
		this.workingDiscipline = workingDiscipline;
	}

//	public String getPerformanceComment() {
//		return performanceComment;
//	}
//
//	public void setPerformanceComment(String performanceComment) {
//		this.performanceComment = performanceComment;
//	}

	public Integer getSkill() {
		return skill;
	}

	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	public Integer getBehavior() {
		return behavior;
	}

	public void setBehavior(Integer behavior) {
		this.behavior = behavior;
	}

//	public String getSkillAndBehaviorComment() {
//		return skillAndBehaviorComment;
//	}
//
//	public void setSkillAndBehaviorComment(String skillAndBehaviorComment) {
//		this.skillAndBehaviorComment = skillAndBehaviorComment;
//	}
//
//	public String getTrainingCourseAttended() {
//		return trainingCourseAttended;
//	}
//
//	public void setTrainingCourseAttended(String trainingCourseAttended) {
//		this.trainingCourseAttended = trainingCourseAttended;
//	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getLanguageScope() {
		return languageScope;
	}

	public void setLanguageScope(String languageScope) {
		this.languageScope = languageScope;
	}

//	public String getTrainingComment() {
//		return trainingComment;
//	}
//
//	public void setTrainingComment(String trainingComment) {
//		this.trainingComment = trainingComment;
//	}

	/*public String getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(String trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getScoreImpact() {
		return scoreImpact;
	}

	public void setScoreImpact(Integer scoreImpact) {
		this.scoreImpact = scoreImpact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public LocalDateTime getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDateTime submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

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

	public String getBonusCourseComment() {
		return bonusCourseComment;
	}

	public void setBonusCourseComment(String bonusCourseComment) {
		this.bonusCourseComment = bonusCourseComment;
	}
*/
	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

//	public String getAwardCourse() {
//		return awardCourse;
//	}
//
//	public void setAwardCourse(String awardCourse) {
//		this.awardCourse = awardCourse;
//	}
//
//	public LocalDateTime getAwardCourseDate() {
//		return awardCourseDate;
//	}
//
//	public void setAwardCourseDate(LocalDateTime awardCourseDate) {
//		this.awardCourseDate = awardCourseDate;
//	}
//
//	public String getAwardCourseContent() {
//		return awardCourseContent;
//	}
//
//	public void setAwardCourseContent(String awardCourseContent) {
//		this.awardCourseContent = awardCourseContent;
//	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

//	public LocalDateTime getPenaltyDate() {
//		return penaltyDate;
//	}
//
//	public void setPenaltyDate(LocalDateTime penaltyDate) {
//		this.penaltyDate = penaltyDate;
//	}
	
	
	
	
}
