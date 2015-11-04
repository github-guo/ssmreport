package com.learn.hibernate.entity.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.learn.hibernate.entity.Event;
import com.learn.hibernate.entity.Person;

public class TestEvent {
	private  static SessionFactory sf;
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setup(){
		sf=new Configuration().configure().buildSessionFactory();
	}
	
	@Test
	public void testSaveEvent(){
		Session session=sf.getCurrentSession();
		/*
		session.beginTransaction();
		session.save(new Person("guo","ding",24));
		session.save(new Event("hello hibernate",new Date()));
		session.getTransaction().commit();
		session = sf.getCurrentSession();
		session.beginTransaction();
		List<Event> events=session.createQuery("from Event").list();
		for(Event event:events){
			System.out.println("Event ("+event.getDate()+"):"+event.getTitle());
		}
		session.getTransaction().commit();*/
//		EventManager em=new EventManager();
//		em.addPersonToEvent(pid, eid);
		session=sf.getCurrentSession();
		session.beginTransaction();
		Event event = new Event("event1", new Date());
		Person person = new Person("guo","ding",25);
		person.getEvents().add(event);
		person.getEmails().add("test@oocl.com");
//		event.setPerson(person);
		session.save(person);
		session.save(event);
//		person.getEvents().remove(event);
//		session.update(person);
		session.getTransaction().commit();
	}
}
