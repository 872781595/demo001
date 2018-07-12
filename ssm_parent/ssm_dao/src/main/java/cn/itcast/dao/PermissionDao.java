package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.Permission;

public interface PermissionDao {

	@Select("select * from sys_permission")
	public List<Permission> findAllPermission();

	@Insert("insert into sys_permission values(common_sequence.nextval,#{permissionName},#{url})")
	public void savepermission(Permission permission);
	
//	@Select("select * from sys_permission "
//			+ "where id in (select permissionId from sys_role_permission where roleId=#{roleId} )")
	@Select("select * from sys_permission " + 
			"    where id in (select p.permissionid from sys_role_permission p where p.roleid = #{roleId} )")
	public List<Permission> findPermissionsById(Integer roleId);

}
