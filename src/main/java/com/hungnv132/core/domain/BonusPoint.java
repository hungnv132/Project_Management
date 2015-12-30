package com.hungnv132.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.gson.annotations.Expose;
/*
@Entity
@Table(name="bonus_point")*/
public class BonusPoint  {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="training_course")
	private String trainingCourse;
	
	@Column(name="content")
	private String content;
	
	@Column(name="score_impact")
	private int scoreImpact;
	
	@Column(name="description")
	private String description;
	
	@Column(name="benefit")
	private String benefit;
	
	@Column(name="submit_date")
	@Type(type= "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime submitDate;
	
	@Column(name="rank")
	private int rank;
	
	@Column(name="award")
	private String award;
	
	@Column(name="penalty")
	private String penalty;
	
	@Column(name="comment")
	private String bonusCourseComment;
	
	@Expose(serialize= false)
	@OneToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="evaluation_id")
	private Evaluation evaluation;

	public BonusPoint(String trainingCourse, String content, int scoreImpact, String description, String benefit,
			LocalDateTime submitDate, int rank, String award, String penalty, String bonusCourseComment) {
		this.trainingCourse = trainingCourse;
		this.content = content;
		this.scoreImpact = scoreImpact;
		this.description = description;
		this.benefit = benefit;
		this.submitDate = submitDate;
		this.rank = rank;
		this.award = award;
		this.penalty = penalty;
		this.bonusCourseComment = bonusCourseComment;
	}
	
	public BonusPoint(){}

	public String getTrainingCourse() {
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

	public int getScoreImpact() {
		return scoreImpact;
	}

	public void setScoreImpact(int scoreImpact) {
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	
}
