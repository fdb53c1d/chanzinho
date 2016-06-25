package org.chanzinho.service;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.chanzinho.model.Board;
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

  public Post insertPost(Post post) {
    Long id = postRepository.insertPost(post.getBoardId(), post.getParentId(), post.getName(),
        post.getTripcode(), post.getEmail(), post.getSubject(), post.getMessage(),
        post.getPassword(), post.getFile(), post.getFileMd5(), post.getFileType(),
        post.getFileOriginal(), post.getFileSize(), post.getFileSizeFormatted(), post.getImageW(),
        post.getImageH(), post.getThumbW(), post.getThumbH(), post.getIp(), post.getIpMd5(),
        post.getTag(), post.getTimestamp(), post.getStickied(), post.getLocked(),
        post.getPosterAuthority(), post.getReviewed(), post.getDeletedTimestamp(),
        post.getIsDeleted(), post.getBumped());
    return postRepository.findById(id);
  }

  public Post save(Post post) {
    post = postRepository.save(post);
    postRepository.flush();
    return post;
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
      List<Post> replies = findLastPostsByBoardAndThreadId(postCount, boardId, op.getPostId());
      posts.put(op, replies);
    }
    return posts;
  }

  public Post findByBoardIdAndPostId(Integer boardId, Long id) {
    return postRepository.findByBoardIdAndPostId(boardId, id);
  }

  public Post findOp(Integer boardId, Long threadId) {
    Post op = postRepository.findByBoardIdAndPostId(boardId, threadId);

    if (op == null || op.getParentId() != 0) {
      return null;
    }

    return op;
  }

  public List<Post> findReplies(Integer boardId, Long threadId) {
    List<Post> replies = postRepository.findByBoardIdAndParentIdAndIsDeleted(boardId, threadId, 0);

    return replies;
  }

  public int getPageCount(Board board) {
    Long threads = postRepository.countByBoardIdAndIsDeletedAndParentId(board.getId(), 0, 0L);
    return (int) Math.ceil(threads.doubleValue() / 10.0);
  }

  public Long removePost(Integer boardId, Long postId) {
    return postRepository.deleteByBoardIdAndPostId(boardId, postId);
  }

  public String deletePost(Integer boardId, Long postId, String delPassword, String fileOnly) {
    Post post = postRepository.findByBoardIdAndPostId(boardId, postId);
    Board board = boardService.findById(boardId);

    if (post == null || board == null || post.getIsDeleted() == 1) {
      return "Post inválido.";
    }

    if (!post.getPassword().equals(delPassword)) {
      return "Senha incorreta.";
    }

    if (post.getParentId() == 0 && fileOnly == null) {
      List<Post> replies = findReplies(boardId, post.getPostId());
      for (Post reply : replies) {
        deletePost(boardId, reply.getPostId(), delPassword, null);
      }
    }

    if (fileOnly == null) {
      if (!post.getFileMd5().equals("l") && !post.getFile().equals("removed")) {
        File file = new File(String.format("resources/%s/src/%s.%s", board.getName(),
            post.getFile(), post.getFileType()));
        File thumb;
        if (post.getFileType().equals("jpg") || post.getFileType().equals("png")
            || post.getFileType().equals("gif")) {
          thumb = new File(String.format("resources/%s/thumb/%ss.%s", board.getName(),
              post.getFile(), post.getFileType()));
        } else {
          thumb = new File(
              String.format("resources/%s/thumb/%ss.%s", board.getName(), post.getFile(), "jpg"));
        }
        file.delete();
        thumb.delete();
      }
      post.setIsDeleted(1);
      post.setDeletedTimestamp(new Long(Instant.now().getEpochSecond()).intValue());
      postRepository.save(post);
      return "Post apagado.";
    } else {
      if (!post.getFileMd5().equals("")) {
        if(post.getFile().equals("removed")) {
          return "Arquivo já foi apagado";
        }
        File file = new File(String.format("resources/%s/src/%s.%s", board.getName(),
            post.getFile(), post.getFileType()));
        File thumb;
        if (post.getFileType().equals("jpg") || post.getFileType().equals("png")
            || post.getFileType().equals("gif")) {
          thumb = new File(String.format("resources/%s/thumb/%ss.%s", board.getName(),
              post.getFile(), post.getFileType()));
        } else {
          thumb = new File(
              String.format("resources/%s/thumb/%ss.%s", board.getName(), post.getFile(), "jpg"));
        }
        file.delete();
        thumb.delete();
        post.setFile("removed");
        postRepository.save(post);
        return "Arquivo apagado.";
      } else {
        return "Post não contém arquivo.";
      }
    }

  }

}
