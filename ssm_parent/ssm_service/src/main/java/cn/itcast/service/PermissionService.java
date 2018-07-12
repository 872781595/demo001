package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Permission;


public interface PermissionService {

	public List<Permission> findAllPermission();

	public void savepermission(Permission permission);

}
