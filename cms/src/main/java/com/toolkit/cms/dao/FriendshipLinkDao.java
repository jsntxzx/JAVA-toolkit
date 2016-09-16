package com.toolkit.cms.dao;

import java.util.List;

import com.toolkit.cms.entity.FriendshipLink;
import com.toolkit.cms.support.PageDto;
import com.toolkit.cms.support.PageDto;

/**
 * @ClassName: FriendshipLinkDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhangp
 * @date 2016年7月5日 上午11:18:56
 * 
 */
public interface FriendshipLinkDao {

	public FriendshipLink saveOrUpdate(FriendshipLink entity);

	public void del(Long id);

	public void delBatch(String ids);

	public FriendshipLink getFriendshipLink(Long id);

	public List<FriendshipLink> findAll();

	public PageDto<FriendshipLink> findList(String siteName, Integer pageNo,
                                            Integer pageSize);

}
