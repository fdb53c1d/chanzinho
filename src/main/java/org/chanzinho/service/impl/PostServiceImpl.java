package org.chanzinho.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.model.Post;
import org.chanzinho.repository.PostRepository;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	@Autowired
	BoardService boardService;
	
	@Override
	public Post findById(Long id) {
		return postRepository.findById(id);
	}
	
	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public List<Post> findOpsByBoardIdAndPageIndex(Integer boardId, Integer index) {
		List<Post> posts = new ArrayList<Post>();
		Page<Post> page = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, 0L, 0, new PageRequest(index, 10, Sort.Direction.DESC, "bumped"));
		for(Post post : page) {
			posts.add(post);
		}
		return posts;
	}
	
	@Override
	public List<Post> findLastPostsByBoardAndThreadId(Integer postCount, Integer boardId, Long threadId) {
		List<Post> posts = new ArrayList<Post>();
		Page<Post> page = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, threadId, 0, new PageRequest(0, postCount, Sort.Direction.DESC, "bumped"));
		for(Post post : page) {
			posts.add(post);
		}
		Collections.reverse(posts);
		return posts;
	}
	
	@Override
	public Map<Post, List<Post>> findPostsByBoardIdAndPageIndex(Integer postCount, Integer boardId, Integer index) {
		List<Post> ops = findOpsByBoardIdAndPageIndex(boardId, index);
		Map<Post, List<Post>> posts = new LinkedHashMap<Post, List<Post>>();
		for(Post op : ops) {
			List<Post> replies = findLastPostsByBoardAndThreadId(postCount, boardId, op.getId());
			posts.put(op, replies);
		}
		return posts;
	}
}
