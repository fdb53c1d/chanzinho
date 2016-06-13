package org.chanzinho.service;

import java.util.List;
import java.util.Map;

import org.chanzinho.model.Post;

public interface PostService {

	Post findById(Long id);
	
	List<Post> findOpsByBoardIdAndPageIndex(Integer boardId, Integer index);
	
	List<Post> findLastPostsByBoardAndThreadId(Integer postCount, Integer boardId, Long threadId);
	
	Map<Post, List<Post>> findPostsByBoardIdAndPageIndex(Integer postCount, Integer boardId, Integer index);
}
