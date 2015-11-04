package com.shiro.base64;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

public class EncodeAndEncrypt {
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
	
	@Test
	public void testHashService(){
		// 默认算法SHA-512
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		// 设置生成公盐
		hashService.setGeneratePublicSalt(true);
		// 私盐 默认无
		hashService.setPrivateSalt(new SimpleByteSource("123"));
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		// 生成hash值的迭代次数
		hashService.setHashIterations(1);
		
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello"))
				.setSalt(ByteSource.Util.bytes("123"))
				.setIterations(2).build();
		String hex=hashService.computeHash(request).toHex();
		System.out.println(hex);
	}
	
	@Test 
	// 随机生成数 并使用十六进制编码
	public void testSecureRandomNumber(){
		SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
		generator.setSeed("123".getBytes());
		String hex=generator.nextBytes().toHex();
		System.out.println(hex);
		Assert.assertNotNull(hex);
	}
	
	@Test
	public void encrypt(){
		AesCipherService aes= new AesCipherService();
		aes.setKeySize(128);
		// 生成key
		Key key = aes.generateNewKey();
		String text="hello";
		// 利用key 去加密文本
		String encrptText=aes.encrypt(text.getBytes(),key.getEncoded()).toHex();
		// 解密
		String text2=new String(aes.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
		Assert.assertEquals(text, text2);
	}
}
