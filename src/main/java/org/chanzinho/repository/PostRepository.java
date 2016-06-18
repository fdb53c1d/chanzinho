package org.chanzinho.repository;

import java.util.List;

import org.chanzinho.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  Post findById(Long id);

  Post findByBoardIdAndId(Integer boardId, Long postId);

  List<Post> findByBoardIdAndParentId(Integer boardId, Long parentId);

  List<Post> findByBoardIdAndParentIdAndIsDeleted(Integer boardId, Long parentId, Integer isDeleted);
  
  Page<Post> findByBoardIdAndParentIdAndIsDeleted(Integer boardId, Long parentId, Integer isDeleted,
      Pageable page);
}
