package cn.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissiomService;

	@RequestMapping("/findAllPermission")
	public String findAllPermission(Model model) {
		List<Permission> permissionList = permissiomService.findAllPermission();
		model.addAttribute("permissionList", permissionList);
		return "permission/permissionList";
	}
	//添加权限的跳转
	@RequestMapping("/addPermissionUI")
	public String addPermissionUI() {
		return "permission/addPermission";
	}
	
	//增加权限
	@RequestMapping("/savepermission")
	public String savepermission(Permission permission) {
		permissiomService.savepermission(permission);
		return "redirect:/permission/findAllPermission";
	}
}
