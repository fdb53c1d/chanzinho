package org.chanzinho.service.impl;

import org.chanzinho.model.Post;
import org.chanzinho.repository.PostRepository;
import org.chanzinho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	public Post findById(Long id) {
		return postRepository.findById(id);
	}
}
