package cn.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Role;
import cn.itcast.domain.SysUser;
import cn.itcast.service.RoleService;
import cn.itcast.service.SysUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private RoleService roleService;
	
	//分页查询所有的用户数据
	@RequestMapping("/findAllUser")
	public String findAllSysUserByPage(Model model,
			@RequestParam(defaultValue="1")Integer pageNum,
			@RequestParam(defaultValue="5")Integer PageSize) {
		
		//得到security上下文对象
		SecurityContext context = SecurityContextHolder.getContext();
		//使用security上下文对象得到登录的用户对象(用户对象类型是security中的User类型)
		User user = (User) context.getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println(username);
		
		PageInfo<SysUser> users = sysUserService.findAllSysUserByPage(pageNum,PageSize);
		model.addAttribute("pageInfo", users);
		return "user/userList";
	}
	
	//添加用户跳转页面
	@RequestMapping("/addUserUI")
	public String addUserUI() {
		return "user/addUser";
	}
	
	//保存用户
	@RequestMapping("/saveUser")
	public String saveUser(SysUser user) {
		sysUserService.saveUser(user);
		return "redirect:/sysUser/findAllUser";
	}
	
	//用户详情页查询
	@RequestMapping("/findUserDetail")
	public String findUserDetail(Integer id,Model model) {
		SysUser sysUser = sysUserService.findUserDetail(id);
		model.addAttribute("user", sysUser);
		return "user/userDetail";
	}
	
	@RequestMapping("/addUserRoleUI")
	public String addUserRoleUI(Integer id,Model model) {
		//需要获取到SysyUser的名称
		//这个方法获取的是所有的用户信息,及其包含的所有角色和权限信息
		SysUser sysUser = sysUserService.findUserDetail(id);
		model.addAttribute("user", sysUser);
		//需要获取的SysyUser对象的所有角色
		//可以使用上面获取到的SysUser对象获取到去之前赋予的角色
		List<Role> roles = sysUser.getRoles();
		//在页面中没有办法判断之前SysUser对象中带有的角色是否存在
		//需要在java代码中拼接,然后在前端页面中使用函数进行存在判断
		//StringBuilder性能最好,但是线程不安全,没有办法加线程锁
		//StringBuffer性能次之
		//String性能最次,不是变量,每次JVM都需要创建一个新的同名的String对象,然后将就得String对象加上
		//需要添加上去的字符串组成新的字符串,然后在将就得String对象删除,所以性能是最低的
		StringBuilder sb = new StringBuilder();
		//循环遍历所有的Role,进行添加进SB对象中
		if (roles!=null&&roles.size()>0) {
			for (Role role : roles) {
				sb.append(role.getRoleName());
			}
			String roleStr = sb.toString();
			model.addAttribute("roleStr", roleStr);
		}
		//需要获取到程序中所有的角色对象
		//需要常见一个RoleService对象创建一个Dao层的方法查询所有的角色信息
		//之前查询过每个userId的角色信息
		//通过自动注入获得RoleService对象
		List<Role> allRoles = roleService.findAllRole();
		model.addAttribute("roles", allRoles);
		//最后需要判断这个用户之前有的角色,之前有的角色需要checked勾选上
		//修改的步骤是先删除所有的角色,然后按照现在传过来的所有角色,进行insert
		return "user/addUserRole";
	}
	
	//addUserRole管理用户角色
	//接收用户的id,接收这个用户想要得到的所有角色,对象为数组
	@RequestMapping("/addUserRole")
	public String addUserRole(int userId,int[] roleIds) {
		System.out.println("userId"+userId);
		for (int i : roleIds) {
			System.out.println(i);
		}
		sysUserService.addUserRole(userId,roleIds);
		return "redirect:/sysUser/findAllUser";
	}
	
}
