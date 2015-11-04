package com.learn.hibernate.entity.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.learn.hibernate.entity.Event;

public class TestJPA {
	private static EntityManagerFactory ef;
	@BeforeClass
	public static void setup(){
		 ef = Persistence.createEntityManagerFactory("hibernate");
	}
	
	@Test
	public void testSave(){
		EntityManager em = ef.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Event("hello jpa", new Date()));
		em.getTransaction().commit();
		em.close();
		em=ef.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Event> events=(List<Event>) em.createQuery("from Event",Event.class);
		for(Event event:events){
			System.out.println(event.getDate()+":"+event.getTitle());
		}
		em.getTransaction().commit();
		em.close();
	}
}
