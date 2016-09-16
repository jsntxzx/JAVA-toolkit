package com.toolkit.cms.controller;

import com.toolkit.cms.entity.User;
import com.toolkit.cms.service.UserService;
import com.toolkit.cms.support.ReqDto;
import com.toolkit.cms.support.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toolkit.cms.entity.User;
import com.toolkit.cms.service.RoleService;
import com.toolkit.cms.service.UserService;
import com.toolkit.cms.support.ReqDto;
import com.toolkit.cms.support.Result;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequiresPermissions({ "user/list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String index(Model model) {
		return "user/index";
	}

	@RequiresPermissions({ "user/list" })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(User user, ReqDto req, Model model) {
		try {
			model.addAttribute(
					"list",
					userService.findList(user, req.getPageNo(),
							req.getPageSize()));
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "user/list";
	}

	@RequiresPermissions({ "user/add" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		try {
			model.addAttribute("roles", roleService.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "user/add";
	}

	@RequiresPermissions({ "user/add" })
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(User user, Model model, Long... roleIds) {
		try {
			user = userService.add(user, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result(true, user);
	}

	@RequiresPermissions({ "user/edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(Long id, Model model) {
		try {
			model.addAttribute("roles", roleService.findAll());
			model.addAttribute("userRoles", userService.findRolesById(id));
			model.addAttribute("entity", userService.getUser(id));
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "user/edit";
	}

	@RequiresPermissions({ "user/edit" })
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(User user, Model model, Long... roleIds) {
		try {
			user = userService.update(user, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result(true, user);
	}

	@RequiresPermissions({ "user/del" })
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(String ids, Model model) {
		try {
			userService.delBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result();
	}

}