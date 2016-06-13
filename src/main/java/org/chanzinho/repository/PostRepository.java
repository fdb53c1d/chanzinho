package org.chanzinho.repository;

import org.chanzinho.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post findById(Long id);
	
}
