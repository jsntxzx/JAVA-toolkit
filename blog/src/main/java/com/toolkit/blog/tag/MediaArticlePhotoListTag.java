package com.toolkit.blog.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolkit.blog.constant.MediaConstant;
import com.toolkit.blog.entity.Media;
import com.toolkit.blog.plugin.TagPlugin;
import com.toolkit.blog.service.MediaService;

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
public class MediaArticlePhotoListTag extends TagPlugin {

	@Autowired
	private MediaService attachmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		long kindId = Long.parseLong(params.get("kindId").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());

		// 获得目录列表
		List<Media> list = attachmentService.getMediaListByKindAndType(kindId,
				MediaConstant.Kind.article, MediaConstant.Type.photo, rows);
		env.setVariable("tag_attachment_list", DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}

}
