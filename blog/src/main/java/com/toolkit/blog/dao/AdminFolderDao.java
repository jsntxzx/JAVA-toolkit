package com.toolkit.blog.dao;

import java.util.List;

import com.toolkit.blog.entity.AdminFolder;
import com.toolkit.blog.entity.vo.AdminFolderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminFolderDao {

	public int addAdminFolder(AdminFolder adminFolder);

	public int deleteAdminFolder(@Param("adminId") long adminId,
			@Param("folderId") long folderId);

	public List<AdminFolderVo> getAdminFolderListById(
			@Param("adminId") long adminId);

	public AdminFolderVo getAdminFolderById(@Param("adminId") long adminId,
			@Param("folderId") long folderId);
}
