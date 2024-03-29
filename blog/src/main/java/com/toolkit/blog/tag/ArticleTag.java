package com.toolkit.blog.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import com.toolkit.blog.entity.Article;
import com.toolkit.blog.exception.ArticleNotFoundException;
import com.toolkit.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolkit.blog.plugin.TagPlugin;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Administrator file标签
 */
@Service
public class ArticleTag extends TagPlugin {

	@Autowired
	private ArticleService articleService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer articleId = Integer
				.parseInt(params.get("articleId").toString());
		// 获取指定的文件
		try {
			Article article = articleService.getArticleById(articleId);
			env.setVariable("tag_article", BEANS_WRAPPER.wrap(article));
		} catch (ArticleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		body.render(env.getOut());
	}
}
