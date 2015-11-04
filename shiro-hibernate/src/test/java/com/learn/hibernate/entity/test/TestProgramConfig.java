package com.learn.hibernate.entity.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.learn.hibernate.entity.Event;

public class TestProgramConfig {
	private static SessionFactory sf;
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setup(){
	   Configuration cf = new Configuration().addClass(Event.class);
	   cf.configure();
	   sf=cf.buildSessionFactory();
	}
	
	@Test
	public void testSave(){
		Session session=sf.openSession();
		session.getTransaction().begin();
		session.save(new Event("hello configuration", new Date()));
		session.getTransaction().commit();
		session.close();
	}
}
