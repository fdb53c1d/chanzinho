package org.chanzinho.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chanzinho.exception.ChanzinhoException;
import org.chanzinho.model.Board;
import org.chanzinho.model.Post;
import org.chanzinho.service.BoardService;
import org.chanzinho.service.PostProcessor;
import org.chanzinho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.HtmlUtils;

@RestController
@EnableWebMvc
public class PostController {

  @Autowired
  BoardService boardService;
  @Autowired
  PostService postService;
  @Autowired
  PostProcessor postProcessor;

  // TODO tag?, process tripcode, generate ip hash, sticky/locked, message parsing, authority,
  // desnoko
  @RequestMapping(value = "/board.php", method = RequestMethod.POST,
      produces = "text/plain;charset=UTF-8")
  public String postHandler(@RequestParam("board") String boardName,
      @RequestParam("replythread") String replyThread, @RequestParam("em") String email,
      @RequestParam("name") String name, @RequestParam("subject") String subject,
      @RequestParam("message") String message, @RequestParam("postpassword") String postPassword,
      @RequestParam("imagefile") MultipartFile imageFile, HttpServletRequest request,
      HttpServletResponse response) {

    try {
      postProcessor.validatePost(boardName, replyThread, email, name, subject, message,
          postPassword, imageFile, request);
    } catch (ChanzinhoException e) {
      return e.getMessage();
    }

    Post post = new Post();
    Board board = boardService.findByName(boardName);
    Long threadNum = Long.parseLong(replyThread);

    post.setBoardId(board.getId());
    post.setSubject(subject);
    post.setName(name);
    post.setTripcode("");
    post.setEmail(email);
    post.setMessage(HtmlUtils.htmlEscape(message).replaceAll("\n", "<br/>"));
    post.setPassword(postPassword);
    Long timestamp = Instant.now().getEpochSecond();
    post.setBumped(timestamp.intValue());
    post.setTimestamp(timestamp.intValue());
    post.setDeletedTimestamp(0);
    post.setIsDeleted(0);
    post.setReviewed(0);
    post.setPosterAuthority(0);
    post.setIp(request.getRemoteAddr());
    post.setIpMd5("");
    post.setTag("");

    post.setFile(String.valueOf(timestamp) + String.format("%03d", new Random().nextInt(99)));

    try {
      postProcessor.processFile(imageFile, board, post, timestamp);
    } catch (ChanzinhoException e) {
      return e.getMessage();
    }

    if (threadNum == 0) {
      post.setParentId(0L);
      post.setLocked(0);
      post.setStickied(0);
    }

    else {
      post.setParentId(threadNum);
      post.setLocked(0);
      post.setStickied(0);
      Post parent = postService.findByBoardIdAndPostId(board.getId(), threadNum);
      parent.setBumped(timestamp.intValue());
      postService.save(parent);
    }

    post = postService.insertPost(post);

    try {
      if (threadNum == 0) {
        response.sendRedirect(String.format("/%s/res/%d.html", boardName, post.getPostId()));
      } else {
        response.sendRedirect(String.format("/%s/res/%d.html", boardName, threadNum));
      }
    } catch (IOException e) {
      return "Erro de redirecionamento";
    }

    return "";
  }

  @RequestMapping(value = "/delete.php", params = {"delete", "!report"},
      method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String delete(@RequestParam(value="post", required = false) List<String> post,
      @RequestParam(value = "delpassword", required = false) String delPassword,
      @RequestParam(value = "board", required = false) String boardName,
      @RequestParam(value = "fileonly", required = false) String fileOnly) {
    
    if (post == null) {
      return "Selecione um post.";
    }
    if (delPassword == null || delPassword.trim().isEmpty()) {
      return "Preencha o campo de senha.";
    }
    if (boardName == null || boardName.trim().isEmpty()) {
      return "Faltam campos.";
    }
    Board board = boardService.findByName(boardName);
    if (board == null) {
      return "Board inválida.";
    }

    StringBuilder sb = new StringBuilder();
    for (String s : postProcessor.deletePosts(board.getId(), post, delPassword, fileOnly)) {
      sb.append(s);
      sb.append("\n");
    }
    return sb.toString();
  }

  @RequestMapping(value = "/delete.php", params = {"!delete", "report"},
      method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String report(@RequestParam Map<String, String> params) {

    return "Vou implementar ainda";
  }
}
