package com.toolkit.blog.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import com.toolkit.blog.service.ConfigService;
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
public class ConfigTag extends TagPlugin {

	@Autowired
	private ConfigService configService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		String key = params.get("key").toString();
		String value = configService.getStringByKey(key);
		env.setVariable("tag_value", BEANS_WRAPPER.wrap(value));
		body.render(env.getOut());
	}
}
