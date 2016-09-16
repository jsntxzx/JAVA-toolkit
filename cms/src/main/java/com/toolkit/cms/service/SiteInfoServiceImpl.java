package com.toolkit.cms.service;

import com.toolkit.cms.entity.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toolkit.cms.dao.SiteInfoDao;
import com.toolkit.cms.entity.SiteInfo;

/**
 * @ClassName: SiteInfoServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年7月5日 下午2:17:15
 * 
 */
@Service("siteInfoService")
public class SiteInfoServiceImpl implements SiteInfoService {

	@Autowired
	private SiteInfoDao siteInfoDao;

	@Transactional
	public SiteInfo saveOrUpdate(SiteInfo entity) {
		return siteInfoDao.saveOrUpdate(entity);
	}

	@Transactional
	public SiteInfo getSiteInfo(Long id) {
		return siteInfoDao.getSiteInfo(id);
	}

}
