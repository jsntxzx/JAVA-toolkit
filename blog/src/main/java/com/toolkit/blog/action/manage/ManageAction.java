package com.toolkit.blog.action.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.toolkit.blog.entity.Admin;
import com.toolkit.blog.entity.vo.ArticleVo;
import com.toolkit.blog.exception.FolderNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage")
public class ManageAction extends ManageBaseAction {

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap modelMap)
			throws FolderNotFoundException {
		Admin admin = this.getAdmin(request);
		modelMap.put("articleCount", 0);
		modelMap.put("downloadCount", 0);
		modelMap.put("userCount", 0);
		modelMap.put("folderAll", folderService.getAllFolderList(0));
		List<ArticleVo> articleList = articleService
				.getArticleListByAdminIdAndFolderId(admin.getAdminId(), 0,
						null, 0, 10);
		modelMap.put("articleList", articleList);
		return "manage/index";
	}

}
