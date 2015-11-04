
package com.learn.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sf = buildSessionFactory();
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		
		return  new Configuration().configure().buildSessionFactory();
		
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}
	
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
	}
}
