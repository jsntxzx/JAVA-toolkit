package com.toolkit.blog.tag;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolkit.blog.plugin.TagPlugin;
import com.toolkit.blog.service.FolderService;
import com.toolkit.blog.util.HttpUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * folder标签
 * 
 * @author lqq
 * 
 */
@Service
public class ArticleUrlTag extends TagPlugin {
	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params,
			TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String artcleId = params.get("articleId").toString();
		String basePath = HttpUtils.getBasePath(request);
		env.getOut().write(basePath + "/article/" + artcleId + ".htm");
	}

}
