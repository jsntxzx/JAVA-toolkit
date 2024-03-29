package com.toolkit.blog.action.manage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.ActionEnter;
import com.toolkit.blog.constant.SystemConstant;
import com.toolkit.blog.util.HttpUtils;
import com.toolkit.blog.util.MediaUtils;
import com.toolkit.blog.util.PropertyUtils;

@Controller
@RequestMapping("/manage")
public class ManageUEditorAction extends ManageBaseAction {

	@ResponseBody
	@RequestMapping(value = "/ueditor.htm")
	public String config(@RequestParam(value = "action") String action,
			HttpServletResponse response, HttpServletRequest request) {
//		response.setContentType("text/html;charset=UTF-8");
		// String root = HttpUtils.getBasePath(request);
		String root = PropertyUtils.getRoot()
				+ java.io.File.separatorChar;
		// root = root.replace("\\", "/");
		// if (!root.endsWith("/")) {
		// root += "/";
		// }
		logger.info("ueditor root:"+root);
		return new ActionEnter(request, root).exec();
	}

	/**
	 * @see imageManager.jsp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/photo/manager.htm", method = RequestMethod.POST)
	public String photoManager(HttpServletRequest request) {
		String photoUploadPath = HttpUtils.getRealPath()
				+ SystemConstant.UPLOAD_FOLDER;
		List<java.io.File> fileList = MediaUtils.getFiles(
				photoUploadPath, new ArrayList<java.io.File>(),
				MediaUtils.PHOTO_TYPE);
		String imgStr = "";
		for (java.io.File file : fileList) {
			imgStr += file.getPath().replace(
					HttpUtils.getRealPath(), "")
					+ "ue_separate_ue";
		}
		if (imgStr != "") {
			imgStr = imgStr.substring(0,
					imgStr.lastIndexOf("ue_separate_ue"))
					.replace(java.io.File.separator, "/")
					.trim();
		}
		return imgStr;
	}

	// @ResponseBody
	// @RequestMapping(value = "/photo/upload.htm", method =
	// RequestMethod.POST)
	// public String photoUpload(@RequestParam("dir") String dir,
	// @RequestParam("fileName") String fileName,
	// @RequestParam("pictitle") String pictitle,
	// @RequestParam("upFile") MultipartFile upFile,
	// HttpServletRequest request) {
	// JSONObject json = new JSONObject();
	// if (!UploadUtils.isFileType(fileName, UploadUtils.PHOTO_TYPE)) {
	// json.put("state", "不允许的文件格式");
	// return json.toString();
	// }
	// try {
	// Article file = articleService.addUploadFile(upFile,fileName, 0,
	// getAdmin(request)
	// .getAdminId());
	// json.put("original", fileName);
	// json.put("url", SystemConstant.BASE_PATH + file.getContent());
	// json.put("title", pictitle);
	// json.put("state", "SUCCESS");
	// return json.toString();
	// } catch (IllegalStateException e) {
	// json.put("state", e.getMessage());
	// } catch (IOException e) {
	// json.put("state", e.getMessage());
	// } catch (UploadException e) {
	// json.put("state", e.getMessage());
	// }
	// return json.toString();
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/file/upload.htm", method =
	// RequestMethod.POST)
	// public String fileUpload(@RequestParam("Filename") String fileName,
	// @RequestParam("Upload") String Upload,
	// @RequestParam("upfile") MultipartFile upFile,
	// HttpServletRequest request) {
	// JSONObject json = new JSONObject();
	// if (!UploadUtils.isFileType(fileName, UploadUtils.PHOTO_TYPE)) {
	// json.put("state", "不允许的文件格式");
	// return json.toString();
	// }
	// try {
	// Article file = articleService.addUploadFile(upFile, fileName, 0,
	// getAdmin(request)
	// .getAdminId());
	// json.put("original", fileName);
	// json.put("url", SystemConstant.BASE_PATH + file.getContent());
	// json.put("fileType", UploadUtils.getFileExt(fileName));
	// json.put("state", "SUCCESS");
	// return json.toString();
	// } catch (IllegalStateException e) {
	// json.put("state", e.getMessage());
	// } catch (IOException e) {
	// json.put("state", e.getMessage());
	// } catch (UploadException e) {
	// json.put("state", e.getMessage());
	// }
	// return json.toString();
	// }
}
