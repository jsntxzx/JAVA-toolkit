package com.toolkit.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toolkit.blog.util.HttpUtils;
import org.apache.log4j.Logger;

/**
 * 
 * Ftl文件安全过滤器
 * 
 * @author Herbert
 * 
 */
public class FtlFilter implements Filter {

	protected final Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.sendRedirect(HttpUtils.getBasePath(request) + "/404.htm");
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
