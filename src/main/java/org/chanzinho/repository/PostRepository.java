package org.chanzinho.repository;

import java.util.List;

import org.chanzinho.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

  Post findById(Long id);

  Post findByBoardIdAndPostId(Integer boardId, Long postId);

  List<Post> findByBoardIdAndParentId(Integer boardId, Long parentId);

  List<Post> findByBoardIdAndParentIdAndIsDeleted(Integer boardId, Long parentId,
      Integer isDeleted);

  Page<Post> findByBoardIdAndParentIdAndIsDeleted(Integer boardId, Long parentId, Integer isDeleted,
      Pageable page);

  Long countByBoardIdAndIsDeletedAndParentId(Integer boardId, Integer isDeleted, Long parentId);

  Long deleteByBoardIdAndPostId(Integer boardId, Long postId);
  
  @Procedure(name = "Post.insertPost")
  Long insertPost(@Param("_boardid") Integer boardId, @Param("_parentid") Long parentId,
      @Param("_name") String name, @Param("_tripcode") String tripcode,
      @Param("_email") String email, @Param("_subject") String subject,
      @Param("_message") String message, @Param("_password") String password,
      @Param("_file") String file, @Param("_file_md5") String filemd5,
      @Param("_file_type") String fileType, @Param("_file_original") String fileOriginal,
      @Param("_file_size") Integer fileSize,
      @Param("_file_size_formatted") String fileSizeFormatted, @Param("_image_w") Integer imageW,
      @Param("_image_h") Integer imageH, @Param("_thumb_w") Integer thumbW,
      @Param("_thumb_h") Integer thumbH, @Param("_ip") String ip, @Param("_ipmd5") String ipmd5,
      @Param("_tag") String tag, @Param("_timestamp") Integer timestamp,
      @Param("_stickied") Integer stickied, @Param("_locked") Integer locked,
      @Param("_posterauthority") Integer posterAuthority, @Param("_reviewed") Integer reviewed,
      @Param("_deleted_timestamp") Integer deletedTimestamp,
      @Param("_IS_DELETED") Integer isDeleted, @Param("_bumped") Integer bumped);
}
