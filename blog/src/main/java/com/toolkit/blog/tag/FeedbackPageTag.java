package com.toolkit.blog.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import com.toolkit.blog.entity.vo.PageVo;
import com.toolkit.blog.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolkit.blog.constant.GuestbookConstant;
import com.toolkit.blog.entity.vo.GuestbookVo;
import com.toolkit.blog.plugin.TagPlugin;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service
public class FeedbackPageTag extends TagPlugin {

	@Autowired
	private GuestbookService messageBoardService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer p = Integer.parseInt(params.get("p").toString());
		PageVo<GuestbookVo> pageVo = messageBoardService.getMessageBoardPage(p,
				GuestbookConstant.status.display, "number");
		env.setVariable("tag_feedback_page", BEANS_WRAPPER.wrap(pageVo));

		body.render(env.getOut());
	}
}
