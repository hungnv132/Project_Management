package com.hungnv132.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "content")
	private String content;

	@Column(name = "time_start")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	private LocalTime timeStart;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	@Column(name = "time_end")
	private LocalTime timeEnd;

	@Expose(serialize = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "report_id")
	private Report report;

	public Task(String content, LocalTime timeStart, LocalTime timeEnd) {
		this.content = content;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public Task() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Task))
			return false;

		Task t = (Task) o;

		if (t.id == this.id)
			return true;

		return false;
	}
	@Override
	public int hashCode() {
		return this.id;
	}
}
