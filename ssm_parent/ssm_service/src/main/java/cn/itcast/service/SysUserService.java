package cn.itcast.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.SysUser;

public interface SysUserService extends UserDetailsService {

	public PageInfo<SysUser> findAllSysUserByPage(Integer pageNum, Integer pageSize);

	public void saveUser(SysUser user);

	public SysUser findUserDetail(Integer id);

	public void addUserRole(int userId, int[] roleIds);


}
