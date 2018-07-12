package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

public interface RoleDao {
	
	@Select("select * from sys_role")
	List<Role> findAllRole();

	@Insert("insert into sys_role values(common_sequence.nextval,#{roleName},#{roleDesc})")
	void saveRole(Role role);
	
//	@Select("select * from sys_role "
//			+ "where id in(select roleid from sys_user_role where userid=#{userId})")
//	@Results({
//		@Result(column="id",property="id"),
//		@Result(column="id",property="permissions",javaType=List.class,
//				many=@Many(select="cn.itcast.dao.PermissionDao.findPermissionsById"))
//	})
	@Select("select * from sys_role " + 
			"    where id in (select roleId from sys_user_role where userid=#{userId})")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="permissions",javaType=List.class,
		many=@Many(select="cn.itcast.dao.PermissionDao.findPermissionsById"))
	})
	public List<Role> findRolesById(Integer userId);

}
