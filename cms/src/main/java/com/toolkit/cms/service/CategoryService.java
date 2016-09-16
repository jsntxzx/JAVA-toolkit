package com.toolkit.cms.service;

import java.util.List;

import com.toolkit.cms.entity.Category;
import com.toolkit.cms.support.PageDto;
import com.toolkit.cms.entity.Category;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: PermissionService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年6月21日 下午2:14:41
 * 
 */
public interface CategoryService {

	public Category saveOrUpdate(Category entity);

	public void del(Long id);

	public void delBatch(String ids);

	public Category getCategory(Long id);

	public Category getRoot();

	public List<Category> findAll();

	public PageDto<Category> findList(String name, String listType, Integer pageNo,
                                      Integer pageSize);
}
