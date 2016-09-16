package com.toolkit.blog.dao;

import java.util.List;

import com.toolkit.blog.entity.Headline;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toolkit.blog.entity.vo.HeadlineVo;

@Repository
public interface HeadlineDao {

	public int addHeadline(Headline headline);

	public List<HeadlineVo> getHeadlineList();

	public HeadlineVo getHeadlineById(@Param("headlineId") long headlineId);

	public int deleteHeadline(@Param("headlineId") long headlineId);

	public int updateHeadlineById(@Param("headlineId") long headlineId,
			@Param("name") String name, @Param("picture") String picture,
			@Param("url") String url);

	public void updateSortById(@Param("headlineId") long headlineId,
			@Param("sort") int sort);

}
