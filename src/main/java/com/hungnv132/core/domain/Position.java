package com.hungnv132.core.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="positions")
public class Position extends CommonObject{

	@Column(name="name")
	private String name;

	@Expose(serialize= false)
	@OneToMany(mappedBy="position", fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<User> members;
	
	public Position(int  id) {
		super(id);
	}
	public Position(){}
	
	public Position(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}
	
	
}
