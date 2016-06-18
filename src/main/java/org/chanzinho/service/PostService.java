package org.chanzinho.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.model.Post;
import org.chanzinho.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  @Autowired
  PostRepository postRepository;
  @Autowired
  BoardService boardService;

  public Post findById(Long id) {
    return postRepository.findById(id);
  }

  public Post save(Post post) {
    return postRepository.save(post);
  }

  public List<Post> findOpsByBoardIdAndPageIndex(Integer boardId, Integer index) {
    List<Post> posts = new ArrayList<Post>();
    Page<Post> page = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, 0L, 0,
        new PageRequest(index, 10, Sort.Direction.DESC, "bumped"));
    for (Post post : page) {
      posts.add(post);
    }
    return posts;
  }

  public List<Post> findLastPostsByBoardAndThreadId(Integer postCount, Integer boardId,
      Long threadId) {
    List<Post> posts = new ArrayList<Post>();
    Page<Post> page = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, threadId, 0,
        new PageRequest(0, postCount, Sort.Direction.DESC, "bumped"));
    for (Post post : page) {
      posts.add(post);
    }
    Collections.reverse(posts);
    return posts;
  }

  public Map<Post, List<Post>> findPostsByBoardIdAndPageIndex(Integer postCount, Integer boardId,
      Integer index) {
    List<Post> ops = findOpsByBoardIdAndPageIndex(boardId, index);
    Map<Post, List<Post>> posts = new LinkedHashMap<Post, List<Post>>();
    for (Post op : ops) {
      List<Post> replies = findLastPostsByBoardAndThreadId(postCount, boardId, op.getId());
      posts.put(op, replies);
    }
    return posts;
  }

  public Post findOp(Integer boardId, Long threadId) {
    Post op = postRepository.findByBoardIdAndId(boardId, threadId);
    
    if(op.getParentId() != 0) {
      return null;
    }
    
    return op;
  }
  
  public List<Post> findReplies(Integer boardId, Long threadId) {
    List<Post> replies = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, threadId, 0);

    return replies;
  }
}
