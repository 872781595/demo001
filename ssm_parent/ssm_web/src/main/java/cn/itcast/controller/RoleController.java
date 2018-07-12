package cn.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.dao.PermissionDao;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionDao permissionDao;
	
	@RequestMapping("/findAllRole")
	public String findAllRole(Model model) {
		List<Role>  roleList= roleService.findAllRole();
		model.addAttribute("roleList", roleList);
		return "role/roleList";
	}
	
	//添加角色跳转
	@RequestMapping("/addRoleUI")
	public String addRoleUI() {
		return "role/addRole";
	}
	
	@RequestMapping("/addRole")
	public String addRole(Role role) {
		roleService.saveRole(role);
		return "redirect:/role/findAllRole";
	}
	
	//addRolePremissionUI
	@RequestMapping("/addRolePremissionUI")
	public String addRolePremissionUI(int roleId,Model model) {
		//获取所有的角色内容
		Role role = roleService.findRoleByRoleid(roleId);
		//通过角色内容获取包含的权限
		List<Permission> permissions = role.getPermissions();
		//获取数据库中所有的权限信息
		permissionDao.findAllPermission();
		//全部放入model中,传入前端页面
		return "premission/addPremission";
	}
	
}
