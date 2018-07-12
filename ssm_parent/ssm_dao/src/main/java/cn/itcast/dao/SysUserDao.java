package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.itcast.domain.SysUser;

public interface SysUserDao {
	
	//select * from sys_user where username='admin'
	@Select("select * from sys_user where username=#{username}")
	public SysUser findSysUserByName(String username);

	@Select("select * from sys_user")
	public List<SysUser> findAllSysUser();

	@Insert("insert into sys_user values(common_sequence.nextval,"+
				"#{username},#{email},#{password},#{phoneNum},#{status})")
	public void saveUser(SysUser user);

//	@Select("select * from sys_user where id=#{userId} ")
//	@Results({
//		@Result(column="id",property="id"),
//		@Result(column="id",property="roles",javaType=List.class,
//				many=@Many(select="cn.itcast.dao.RoleDao.findRolesById"))
//	})
	@Select("select * from sys_user where id = #{id}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="roles",javaType=List.class,
		 many=@Many(select="cn.itcast.dao.RoleDao.findRolesById"))
	})
	public SysUser findUserDetail(Integer id);

	@Delete("delete sys_user_role where userid=#{userId}")
	public void deleteUserRoleById(int userId);

	@Insert("insert into sys_user_role values(#{userId},#{roleId})")
	public void saveUserRoleById(@Param("userId")int userId, @Param("roleId")int roleId);

}
