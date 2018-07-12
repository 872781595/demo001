package cn.itcast.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.SysUserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.SysUser;
import cn.itcast.service.SysUserService;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(@Param(value = "username") String username) throws UsernameNotFoundException {
		System.out.println("username" + username);
		SysUser sysUser = sysUserDao.findSysUserByName(username);
		System.out.println("sysUser==" + sysUser);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//通过当前的用户id获得所有的当前用户的所有角色
		List<Role> roles = roleDao.findRolesById(sysUser.getId().intValue());
		for (Role role : roles) {
			//这个方法是通过字符串ROLE_ADMIN这样的形式创建对象的
			//这个对象可以直接添加到权限中,框架可以直接使用
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		//这个user对象是springSecurity自带的属性独享
		//将这个user返回给spring中的security就交给了框架管理
		User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
		return user;
	}

	@Override
	public PageInfo<SysUser> findAllSysUserByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<SysUser> list = sysUserDao.findAllSysUser();
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		return pageInfo;
	}

	@Override
	public void saveUser(SysUser user) {
		String password = user.getPassword();
		String encode = passwordEncoder.encode(password);
		user.setPassword(encode);
		sysUserDao.saveUser(user);
	}

	@Override
	public SysUser findUserDetail(Integer id) {
		return sysUserDao.findUserDetail(id);
	}

	@Override
	public void addUserRole(int userId, int[] roleIds) {
		//需求:更改用户角色
		//第一步:删除之前的所有角色--因为用户的角色都是通过中间表来确定的
		//所以都是修改中间表
		System.out.println("ServiceImpl---userId==="+userId);
		for (int i : roleIds) {
			System.out.println("ServiceImpl---roleIds==="+i);
		}
		
		sysUserDao.deleteUserRoleById(userId);
		//第二部:将进插入的所有角色都添加进入这个用户和角色的中间表
		if (roleIds!=null&&roleIds.length>0) {
			for (int roleId : roleIds) {
				System.out.println("saveUserRoleById---userId=="+userId);
				System.out.println("saveUserRoleById---roleId=="+roleId);
				sysUserDao.saveUserRoleById(userId,roleId);
			}
		}
		
	}

}
