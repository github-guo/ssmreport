package com.shiro.chapter2.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class MyRolePermissionResolver implements RolePermissionResolver{

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)){
			return Arrays.asList(new WildcardPermission("menu:*"));
		}
		return null;
	}

}
