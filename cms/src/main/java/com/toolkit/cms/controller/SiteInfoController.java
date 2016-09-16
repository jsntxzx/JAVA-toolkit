package com.toolkit.cms.controller;

import com.toolkit.cms.support.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toolkit.cms.entity.SiteInfo;
import com.toolkit.cms.service.SiteInfoService;
import com.toolkit.cms.support.Result;

@Controller
@RequestMapping("/siteInfo")
public class SiteInfoController {
	@Autowired
	private SiteInfoService siteInfoService;


	@RequiresPermissions({ "siteInfo/edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String toEdit(Long id, Model model) {
		try {
			SiteInfo entity = siteInfoService.getSiteInfo(id);
			model.addAttribute("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return "common/error";
		}
		return "siteInfo/edit";
	}

	@RequiresPermissions({ "siteInfo/edit" })
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object edit(SiteInfo entity, Model model) {
		try {
			entity = siteInfoService.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e);
		}
		return new Result(true, entity);
	}

}