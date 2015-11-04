package com.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;



public class LoginLogoutTest {
	@Test
	public void testHelloWorld(){
		//new security manager factory 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// binding factory to securityUtil 
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
		subject.login(usernamePasswordToken);
		subject.logout();
	}
	
	
	@Test
	public void testCustomRealm(){
		//new security manager factory 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		// binding factory to securityUtil 
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
		subject.login(usernamePasswordToken);
		subject.logout();
	}
	
	
	@Test
	public void testMultiRealm(){
		//new security manager factory 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		// binding factory to securityUtil 
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wang", "123");
		subject.login(usernamePasswordToken);
		subject.logout();
	}
	
	
	@Test
	public void testjdbcReaml(){
		//new security manager factory 
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		// binding factory to securityUtil 
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("wang", "123");
		subject.login(usernamePasswordToken);
		subject.logout();
	}
}
