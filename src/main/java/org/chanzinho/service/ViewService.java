package org.chanzinho.service;

import java.util.List;
import java.util.Map;

import org.chanzinho.model.Board;
import org.chanzinho.model.Post;
import org.chanzinho.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ViewService {

  @Autowired
  Environment env;
  @Autowired
  BoardService boardService;
  @Autowired
  PostService postService;

  public Model getIndexModel(Model model, String boardName, Integer pageIndex) {
    Board board = boardService.findByName(boardName);

    if (board == null) {
      return null;
    }

    Map<Section, List<Board>> boardList = boardService.findBoardList();
    Map<Post, List<Post>> ops = postService.findPostsByBoardIdAndPageIndex(
        Integer.parseInt(env.getProperty("chanzinho.indexreplies")), board.getId(), pageIndex);

    if (ops.isEmpty()) {
      return null;
    }

    model.addAttribute("maxpages", env.getProperty("chanzinho.maxpages"));
    model.addAttribute("page", pageIndex+1);
    model.addAttribute("board", board);
    model.addAttribute("charset", "UTF-8");
    model.addAttribute("boardList", boardList);
    model.addAttribute("ops", ops);

    return model;
  }

  public Model getThreadModel(Model model, String boardName, Long threadNum) {
    Board board = boardService.findByName(boardName);

    if (board == null) {
      return null;
    }
    
    Map<Section, List<Board>> boardList = boardService.findBoardList();
    Post op = postService.findOp(board.getId(), threadNum);
    
    if(op == null || op.getIsDeleted() == 1) {
      return null;
    }
    
    List<Post> replies = postService.findReplies(board.getId(), threadNum);

    if (replies == null) {
      return null;
    }

    model.addAttribute("board", board);
    model.addAttribute("charset", "UTF-8");
    model.addAttribute("boardList", boardList);
    model.addAttribute("op", op);
    model.addAttribute("replies", replies);

    return model;
  }
}
