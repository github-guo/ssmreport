package com.learn.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EVENTS")
public class Event {
	
//	private Person person;
	private long id;
	private  Date date;
	private String title;
	
	public Event(){
		
	}
	
	public Event(String title,Date timestamp){
		date=timestamp;
		this.title =title;
	}
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="EVENT_DATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

//	@ManyToOne
//	public Person getPerson() {
//		return person;
//	}
//
//	public void setPerson(Person person) {
//		this.person = person;
//	}

//	public Set<Person> getPerson() {
//		return person;
//	}
//
//	public void setPerson(Set<Person> person) {
//		this.person = person;
//	}
}
