package org.chanzinho.service;

import java.util.List;
import java.util.Map;

import org.chanzinho.model.Board;
import org.chanzinho.model.Post;
import org.chanzinho.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ViewService {

  @Autowired
  BoardService boardService;
  @Autowired
  PostService postService;

  public Model getIndexModel(Model model, Integer boardId, Integer pageIndex) {
    Board board = boardService.findById(boardId);
    Map<Section, List<Board>> boardList = boardService.findBoardList();
    Map<Post, List<Post>> ops = postService.findPostsByBoardIdAndPageIndex(3, boardId, pageIndex);

    model.addAttribute("board", board);
    model.addAttribute("charset", "UTF-8");
    model.addAttribute("boardList", boardList);
    model.addAttribute("ops", ops);

    return model;
  }
}
