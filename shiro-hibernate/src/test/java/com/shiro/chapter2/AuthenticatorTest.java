package com.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class AuthenticatorTest {
	private  void login(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory();
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken("zhang","123"));
	}
}
