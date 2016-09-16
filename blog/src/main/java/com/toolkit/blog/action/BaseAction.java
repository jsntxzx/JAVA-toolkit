package com.toolkit.blog.action;

import com.toolkit.blog.service.ArticleService;
import com.toolkit.blog.service.FolderService;
import com.toolkit.blog.service.HeadlineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.toolkit.blog.service.TemplateService;

/**
 * 
 * @author Herbert
 * 
 */
public class BaseAction {

	@Autowired
	protected FolderService folderService;

	@Autowired
	protected ArticleService fileService;

	@Autowired
	protected TemplateService themeService;

	@Autowired
	protected HeadlineService headlineService;

	protected final Logger logger = Logger.getLogger(this.getClass());
}
