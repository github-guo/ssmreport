package com.learn.hibernate.entity;


import org.hibernate.Session;

import com.learn.hibernate.util.HibernateUtil;

public class EventManager {
	
	public void addPersonToEvent(long pid,long eid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person aPerson = (Person) session.load(Person.class, pid);
		Event aEvent =(Event) session.load(Event.class, eid);
		aPerson.getEvents().add(aEvent);
		
		session.getTransaction().commit();
	}
}
