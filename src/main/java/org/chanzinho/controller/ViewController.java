package org.chanzinho.controller;

import javax.servlet.http.HttpServletResponse;

import org.chanzinho.model.Board;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ViewController {

  @Autowired
  BoardService boardService;
  @Autowired
  ViewService viewService;

  @RequestMapping(value = "/{board}/{index}.html", method = RequestMethod.GET)
  public String indexPage(@PathVariable("board") String boardName,
      @PathVariable("index") String index, Model model, HttpServletResponse response) {

    Board board = boardService.findByName(boardName);
    if (board == null) {
      return "error";
    }

    if (index.equals("index")) {
      model = viewService.getIndexModel(model, board.getId(), 0);
      return "img_index";
    }
    try {
      int idx = Integer.parseInt(index);
      if (idx >= 1 && idx < 11) {
        model = viewService.getIndexModel(model, board.getId(), idx - 1);

        return "img_index";
      }
    } catch (NumberFormatException e) {

    }

    return "error";
  }

  @RequestMapping(value = "/{board}/res/{threadId}.html")
  public String threadPage(@PathVariable("board") String boardName,
      @PathVariable("threadId") String threadId, Model model, HttpServletResponse response) {
    return "";
  }
}
