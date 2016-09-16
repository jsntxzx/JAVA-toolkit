package com.toolkit.cms.dao;

import java.util.List;

import com.toolkit.cms.entity.Permission;
import com.toolkit.cms.support.PageDto;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: PermissionDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年6月16日 下午5:44:48
 * 
 */
public interface PermissionDao {

	public Permission saveOrUpdate(Permission permission);

	public void del(Long permissionId);

	public void delBatch(String ids);

	public Permission getPermission(Long id);

	public Permission getRootPermission();

	public List<Permission> findAll();

	public PageDto<Permission> findList(String desc, Integer pageNo,
                                        Integer pageSize);

}
