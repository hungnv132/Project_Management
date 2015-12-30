package com.hungnv132.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.Expose;
import com.hungnv132.core.support.Json_JodaDate_Serializer;

@MappedSuperclass
public class CommonObject{
	@Expose
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Expose
	@Column(name="create_at")
//	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime createAt;
	
	@Expose
	@Column(name="update_at")
//	@JsonSerialize(using= Json_JodaDate_Serializer.class)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime updateAt;
	
	
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDefaultData() {
		this.setCreateAt(new LocalDateTime());
		this.setUpdateAt(new LocalDateTime());
	}
	public CommonObject(int id) {
		this.id = id;
	}
	public CommonObject(){}
}
