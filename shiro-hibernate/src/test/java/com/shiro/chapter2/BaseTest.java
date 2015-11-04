package com.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class BaseTest {
	protected void login(String config,String username,String password){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken(username, password));
	}
	
	protected Subject getsubject() {
		return SecurityUtils.getSubject();
	}
}
