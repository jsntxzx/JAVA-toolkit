package com.toolkit.cms.service;

import java.util.List;

import com.toolkit.cms.dao.CategoryDao;
import com.toolkit.cms.support.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toolkit.cms.dao.CategoryDao;
import com.toolkit.cms.entity.Category;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年6月17日 上午11:37:15
 * 
 */
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Transactional
	public Category saveOrUpdate(Category entity) {
		return categoryDao.saveOrUpdate(entity);
	}

	@Transactional
	public void del(Long id) {
		categoryDao.del(id);
	}

	@Transactional
	public void delBatch(String ids) {
		categoryDao.delBatch(ids);
	}

	@Transactional
	public Category getRoot() {
		return categoryDao.getRootCategory();
	}

	@Transactional
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional
	public PageDto<Category> findList(String name, String listType, Integer pageNo,
                                      Integer pageSize) {
		return categoryDao.findList(name,listType, pageNo, pageSize);
	}

	@Transactional
	public Category getCategory(Long id) {
		return categoryDao.getCategory(id);
	}

}
