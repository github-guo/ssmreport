package com.shiro.chapter2;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest extends BaseTest{
	@Test
	public void testHasRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		Assert.assertTrue(getsubject().hasRole("role1"));
	}
}
