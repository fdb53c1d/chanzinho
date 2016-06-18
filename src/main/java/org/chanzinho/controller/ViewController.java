package org.chanzinho.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ViewController {

  @Autowired
  Environment env;
  @Autowired
  BoardService boardService;
  @Autowired
  ViewService viewService;

  @RequestMapping(value = {"/{board}", "/{board}/index.html"}, method = RequestMethod.GET)
  public String indexFallback(@PathVariable("board") String boardName, Model model,
      @CookieValue(value = "postpassword", required = false) String cookiePassword,
      HttpServletResponse response) {
    model = viewService.getIndexModel(model, boardName, 0);

    if (model == null) {
      return "404";
    }

    if (cookiePassword == null) {
      cookiePassword = RandomStringUtils.randomAlphanumeric(10);
      Cookie cookie = new Cookie("postpassword", cookiePassword);
      response.addCookie(cookie);
    }

    model.addAttribute("postpassword", cookiePassword);

    return "img_index";
  }

  @RequestMapping(value = "/{board}/{index}.html", method = RequestMethod.GET)
  public String indexPage(@PathVariable("board") String boardName,
      @PathVariable("index") String index, Model model,
      @CookieValue(value = "postpassword", required = false) String cookiePassword,
      HttpServletResponse response) {

    int idx;

    try {
      idx = Integer.parseInt(index);
    } catch (NumberFormatException e) {
      return "404";
    }

    if (idx < 1 || idx > Integer.parseInt(env.getProperty("chanzinho.maxpages"))) {
      return "404";
    }

    model = viewService.getIndexModel(model, boardName, idx - 1);

    if (model == null) {
      return "404";
    }

    if (cookiePassword == null) {
      cookiePassword = RandomStringUtils.randomAlphanumeric(10);
      Cookie cookie = new Cookie("postpassword", cookiePassword);
      response.addCookie(cookie);
    }

    model.addAttribute("postpassword", cookiePassword);

    return "img_index";
  }

  @RequestMapping(value = "/{board}/res/{threadId}.html")
  public String threadPage(@PathVariable("board") String boardName,
      @PathVariable("threadId") String threadId, Model model,
      @CookieValue(value = "postpassword", required = false) String cookiePassword,
      HttpServletResponse response) {

    Long threadNum;

    try {
      threadNum = Long.parseLong(threadId);
    } catch (NumberFormatException e) {
      return "404";
    }

    model = viewService.getThreadModel(model, boardName, threadNum);

    if (model == null) {
      return "404";
    }

    if (cookiePassword == null) {
      cookiePassword = RandomStringUtils.randomAlphanumeric(10);
      Cookie cookie = new Cookie("postpassword", cookiePassword);
      response.addCookie(cookie);
    }

    model.addAttribute("postpassword", cookiePassword);

    return "img_thread";
  }
}
