package com.toolkit.cms.dao;

import java.util.List;

import com.toolkit.cms.entity.Article;
import com.toolkit.cms.support.PageDto;
import com.toolkit.cms.entity.Article;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: ArticleDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年6月27日 下午4:45:48
 * 
 */
public interface ArticleDao {

	public Article saveOrUpdate(Article article);

	public void del(Long id);

	public void delBatch(String ids);

	public Article getArticle(Long id);

	public List<Article> findAll();

	public PageDto<Article> findList(Long categoryId, String title, String tag, String remark,
                                     Integer pageNo, Integer pageSize);

}
