package com.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import com.shiro.chapter2.permission.BitPermission;

public class AuthenticatorTest {
	@Test
	public void testAuthenicator(){
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authorizer.ini");
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken("wang","123"));
		Assert.assertTrue(subject.isPermitted(new BitPermission("+user1+2")));
		Assert.assertTrue(subject.isPermitted("+user1+8"));
		Assert.assertTrue(subject.isPermitted("+user1+10"));
		Assert.assertFalse(subject.isPermitted("+user1+4"));
	}
}
