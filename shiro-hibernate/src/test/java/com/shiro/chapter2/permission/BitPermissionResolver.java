package com.shiro.chapter2.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class BitPermissionResolver implements PermissionResolver{

	@Override
	public Permission resolvePermission(String permissionString) {
		if(permissionString.startsWith("+")){
			return new BitPermission(permissionString);
		}
//		PermissionResolver resolver= new WildcardPermissionResolver();
//		return resolver.resolvePermission(permissionString);
		return new WildcardPermission(permissionString);
	}
}
