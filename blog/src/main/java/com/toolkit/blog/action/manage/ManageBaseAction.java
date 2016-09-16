package com.toolkit.blog.action.manage;

import javax.servlet.http.HttpServletRequest;

import com.toolkit.blog.constant.SystemConstant;
import com.toolkit.blog.entity.vo.JsonVo;
import com.toolkit.blog.service.ArticleService;
import com.toolkit.blog.service.ConfigService;
import com.toolkit.blog.service.HeadlineService;
import com.toolkit.blog.service.MediaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.toolkit.blog.entity.vo.AdminVo;
import com.toolkit.blog.exception.ValidateException;
import com.toolkit.blog.service.AdminFolderService;
import com.toolkit.blog.service.AdminService;
import com.toolkit.blog.service.FolderService;

@Controller
public class ManageBaseAction {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ConfigService configService;

	@Autowired
	protected FolderService folderService;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected MediaService attachmentService;

	@Autowired
	protected AdminService adminService;

	@Autowired
	protected HeadlineService headlineService;

	@Autowired
	protected AdminFolderService adminFolderService;

	/**
	 * 参数校验
	 * 
	 * @param json
	 *            json数据Bean
	 * @throws ValidateException
	 */
	protected <T> void validate(JsonVo<T> json) throws ValidateException {
		if (json.getErrors().size() > 0) {
			json.setResult(false);
			throw new ValidateException("有错误发生");
		} else {
			json.setResult(true);
		}
	}

	/**
	 * 从session中获得管理员的信息
	 * 
	 * @param request
	 * @return
	 */
	protected AdminVo getAdmin(HttpServletRequest request) {
		AdminVo admin = (AdminVo) request.getSession().getAttribute(
				SystemConstant.SESSION_ADMIN);
		return admin;
	}
}
