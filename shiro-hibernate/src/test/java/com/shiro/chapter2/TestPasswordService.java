package com.shiro.chapter2;

import org.junit.Test;

public class TestPasswordService extends BaseTest{
	@Test
	public void testpwdService(){
		login("classpath:shiro-passwordService.ini", "guo", "123");
	}
}
