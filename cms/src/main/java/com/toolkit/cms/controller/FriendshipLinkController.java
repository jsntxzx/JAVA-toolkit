package com.toolkit.cms.controller;

import com.toolkit.cms.entity.FriendshipLink;
import com.toolkit.cms.service.FriendshipLinkService;
import com.toolkit.cms.support.ReqDto;
import com.toolkit.cms.support.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toolkit.cms.entity.FriendshipLink;
import com.toolkit.cms.service.FriendshipLinkService;
import com.toolkit.cms.support.ReqDto;
import com.toolkit.cms.support.Result;

@Controller
@RequestMapping("/friendshipLink")
public class FriendshipLinkController {
	@Autowired
	private FriendshipLinkService friendshipLinkService;

	@RequiresPermissions({ "friendshipLink/list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String index(Model model) {
		return "friendshipLink/index";
	}

	@RequiresPermissions({ "friendshipLink/list" })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(String siteName, ReqDto req, Model model) {
		try {
			model.addAttribute("list", friendshipLinkService.findList(siteName,
					req.getPageNo(), req.getPageSize()));
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "friendshipLink/list";
	}

	@RequiresPermissions({ "friendshipLink/add" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAdd(Model model) {
		return "friendshipLink/add";
	}

	@RequiresPermissions({ "friendshipLink/add" })
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(FriendshipLink entity, Model model) {
		try {
			entity = friendshipLinkService.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result(true, entity);
	}

	@RequiresPermissions({ "friendshipLink/edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(Long id, Model model) {
		try {
			FriendshipLink entity = friendshipLinkService.getFriendshipLink(id);
			model.addAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "friendshipLink/edit";
	}

	@RequiresPermissions({ "friendshipLink/edit" })
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(FriendshipLink entity, Model model) {
		try {
			entity = friendshipLinkService.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result(true, entity);
	}

	@RequiresPermissions({ "friendshipLink/del" })
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(String ids, Model model) {
		try {
			friendshipLinkService.delBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result();
	}

}