package com.hungnv132.core.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "business")
public class Business extends CommonObject {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

//	@Column(name = "start_date")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime startDate;
//
//	@Column(name = "end_date")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	private LocalDateTime endDate;

	@Expose(serialize= false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name="project_id")
	private Project project;
	
//	@Expose(serialize= false)
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinTable(
			name="assignment",
			joinColumns={@JoinColumn(name="business_id")},
			inverseJoinColumns={@JoinColumn(name="member_id")}
			)
	private List<User> members;
	
	@Column(name = "volume")
	private int volume;

	public Business(String name) {
		this.name = name;
	}

	public Business(int id) {
		super(id);
	}

	public Business() {
	}

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

/*	public LocalDateTime getStartDate() {
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
	}*/

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	};


	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	};
	
}
