package com.toolkit.cms.dao;

import java.util.List;

import com.toolkit.cms.entity.SlideImg;
import com.toolkit.cms.support.PageDto;
import com.toolkit.cms.entity.SlideImg;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: SlideImgDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年7月6日 下午4:45:24
 * 
 */
public interface SlideImgDao {

	public SlideImg saveOrUpdate(SlideImg entity);

	public void del(Long id);

	public void delBatch(String ids);

	public SlideImg getSlideImg(Long id);

	public List<SlideImg> findAll();

	public PageDto<SlideImg> findList(Integer pageNo, Integer pageSize, Boolean avarlable);

}
