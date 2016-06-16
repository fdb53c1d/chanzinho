package org.chanzinho.controller;

import org.chanzinho.service.BoardService;
import org.chanzinho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ModelController {

  @Autowired
  BoardService boardService;
  @Autowired
  PostService postService;

  @RequestMapping(value = "/findboard.php", method = RequestMethod.GET)
  public String findBoard(@ModelAttribute("id") Integer id) {
    return boardService.findById(id).toString();
  }

  @RequestMapping(value = "findpost.php", method = RequestMethod.GET)
  public String findPost(@ModelAttribute("id") Long id) {
    return postService.findById(id).toString();
  }

}
