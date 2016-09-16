package com.toolkit.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// HttpSession session = request.getSession();
		// session.invalidate();
		// response.addHeader("x-frame-options", "DENY");
		return "common/login";
	}

	@RequestMapping("/deny")
	public String deny() {
		return "deny";
	}

	@RequestMapping("/timeOut")
	public String timeOut() {
		return "timeout";
	}

	@RequestMapping("/404")
	public String e404() {
		return "common/404";
	}
}