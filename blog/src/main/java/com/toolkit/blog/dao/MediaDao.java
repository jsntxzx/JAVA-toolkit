package com.toolkit.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toolkit.blog.constant.MediaConstant;
import com.toolkit.blog.constant.MediaConstant.Kind;
import com.toolkit.blog.constant.MediaConstant.Type;
import com.toolkit.blog.entity.Media;

@Repository
public interface MediaDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * @param att
	 * @return
	 */
	public int addMedia(Media att);

	// ///////////////////////////////
	// ///// 删除 ////////
	// ///////////////////////////////

	/**
	 * @param mediaId
	 * @return
	 */
	public int deleteMedia(@Param("mediaId") long mediaId);

	// ///////////////////////////////
	// ///// 修改////////
	// ///////////////////////////////

	public int updateLinkByMediaId(@Param("mediaId") long mediaId,
			@Param("link") String link);

	public int updateNameByMediaId(@Param("mediaId") long mediaId,
			@Param("name") String name);

	// ///////////////////////////////
	// ///// 查询////////
	// ///////////////////////////////

	/**
	 * @param mediaId
	 * @return
	 */
	public Media getMediaById(@Param("mediaId") long mediaId);

	/**
	 * @param folderId
	 * @return
	 */
	public int getMediaCountByKindId(@Param("kindId") long kindId,
			@Param("kind") MediaConstant.Kind kind);

	/**
	 * @param folderId
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Media> getMediaListByKindId(@Param("kindId") long kindId,
			@Param("kind") MediaConstant.Kind kind,
			@Param("offset") int offset, @Param("rows") int rows);

	/**
	 * @param kindId
	 * @param kind
	 * @param type
	 * @param status
	 * @return
	 */
	public int getMediaCountByKindAndType(@Param("kindId") long kindId,
			@Param("kind") Kind kind, @Param("type") Type type);

	/**
	 * @param kindId
	 * @param kind
	 * @param type
	 * @param status
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Media> getMediaListByKindAndType(@Param("kindId") long kindId,
			@Param("kind") Kind kind, @Param("type") Type type,
			@Param("offset") int offset, @Param("rows") int rows);

}
