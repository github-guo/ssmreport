package com.learn.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Person {
	@Id
    @Column(name="PERSON_ID")
	private long id;
    private int age;
    private String firstname;
    private String lastname;
	@OneToMany
    private Set<Event> events = new HashSet<Event>();
	@ElementCollection(targetClass=String.class)
	@Column(name="email")
	private Set<String> emails = new HashSet<String>();
    
    public Person() {}
    public Person(String firstName,String lastName,int age){
    	this.firstname=firstName;
    	this.lastname = lastName;
    	this.age=age;
    }
    
    
	public Set<String> getEmails() {
		return emails;
	}
	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
}
