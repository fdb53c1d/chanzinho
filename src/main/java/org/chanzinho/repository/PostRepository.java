package org.chanzinho.repository;

import org.chanzinho.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  Post findById(Long id);

  Page<Post> findByBoardIdAndParentIdAndIsDeleted(Integer boardId, Long parentId, Integer isDeleted,
      Pageable page);
}
