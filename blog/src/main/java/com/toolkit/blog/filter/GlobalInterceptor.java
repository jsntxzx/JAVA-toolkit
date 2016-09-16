package com.toolkit.blog.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toolkit.blog.constant.ConfigConstant;
import com.toolkit.blog.service.ConfigService;
import com.toolkit.blog.util.HttpUtils;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Herbert
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		modelAndView.addObject("BASE_PATH", basePath);
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
		modelAndView
				.addObject(
						"TEMPLATE_BASE_PATH",
						basePath
								+ "/static/template/"
								+ configService
										.getStringByKey(ConfigConstant.BLOG_TEMPLATE));
		modelAndView.addObject("blog_seo_title",
				configService.getStringByKey("blog_seo_title"));
		modelAndView.addObject("blog_seo_description",
				configService.getStringByKey("blog_seo_description"));
		MDC.put("ip", HttpUtils.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
