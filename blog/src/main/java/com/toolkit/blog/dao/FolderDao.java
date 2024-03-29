package com.toolkit.blog.dao;

import java.util.List;

import com.toolkit.blog.entity.vo.FolderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toolkit.blog.constant.FolderConstant;
import com.toolkit.blog.constant.FolderConstant.status;
import com.toolkit.blog.entity.Folder;

/**
 * 目录服务
 * 
 * @author Harbored
 * 
 */

@Repository
public interface FolderDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////
	/**
	 * 增加目录
	 * 
	 * @return Integer
	 */
	public int addFolder(Folder folder);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////
	/**
	 * 删除目录
	 * 
	 * @param folder
	 * @return boolean
	 */
	public boolean deleteFolder(@Param("folderId") long folderId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * @param folderId
	 * @param name
	 * @param ename
	 * @param content
	 * @param status
	 */
	public void updateFolderById(@Param("folderId") long folderId,
			@Param("name") String name, @Param("ename") String ename,
			@Param("status") FolderConstant.status status,
			@Param("content") String content, @Param("height") int height,
			@Param("width") int width);

	public int updateSort(@Param("folderId") long folderId,
			@Param("sort") int sort);

	public int updateCount(@Param("folderId") long folderId,
			@Param("count") int count);

	// ///////////////////////////////
	// ///// 查询 ////////
	// ///////////////////////////////
	/**
	 * 得到目录
	 * 
	 * @param folderId
	 * @return Folder
	 */
	public FolderVo getFolderById(@Param("folderId") long folderId);

	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 * @return List<FolderVo>
	 */
	public List<FolderVo> getFolderListByFatherId(
			@Param("fatherId") long fatherId,
			@Param("status") FolderConstant.status status);

	/**
	 * 通过ename和fatherId获得指定目录
	 * 
	 * @param ename
	 * @param fatherId
	 * @return
	 */
	public Folder getFolderByEname(@Param("ename") String ename);

	/**
	 * @param folderId
	 * @param status
	 */
	public void updateStatus(@Param("folderId") long folderId,
			@Param("status") status status);

	public int updatePath(@Param("folderId") long folderId,
			@Param("path") String path);

	public List<FolderVo> getAllFolderList();

}
