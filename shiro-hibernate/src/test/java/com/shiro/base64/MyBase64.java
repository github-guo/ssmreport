package com.shiro.base64;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Assert;
import org.junit.Test;

public class MyBase64 {
	@Test
	public void testEncode(){
		String str="hello";
		String base64Str=Base64.encodeToString(str.getBytes());
		String str2=Base64.decodeToString(base64Str.getBytes());
		Assert.assertEquals(str, str2);
	}
	
	@Test
	public void testHx(){
		String str="hello";
		String base64Encode=Hex.encodeToString(str.getBytes());
		System.out.println(base64Encode);
	}
	
	@Test
	public void testMD5(){
		String hello ="hello";
		String salt="123";
		String md5=new Md5Hash(hello, salt).toString();
		String md51=new Md5Hash(hello, salt).toString();
		System.out.println(md5);
		Assert.assertEquals(md5, md51);
	}
	
	@Test
	public void testSha256(){
		String hello ="hello";
		String salt="123";
		String sha1 = new Sha256Hash(hello, salt).toString();
		System.out.println(sha1);
	}
}
